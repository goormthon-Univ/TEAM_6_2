package com.LikeCloud.LikeCloud.service;


import com.LikeCloud.LikeCloud.domain.entity.DailyPlan;
import com.LikeCloud.LikeCloud.domain.entity.MonthlyPlan;
import com.LikeCloud.LikeCloud.domain.entity.MyCloud;
import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import com.LikeCloud.LikeCloud.domain.type.CloudType;
import com.LikeCloud.LikeCloud.domain.type.Day;
import com.LikeCloud.LikeCloud.dto.DailyDoneReqDto.CancelDailyDoneReq;
import com.LikeCloud.LikeCloud.dto.DailyDoneReqDto.DailyDoneReq;
import com.LikeCloud.LikeCloud.dto.MainResDto;
import com.LikeCloud.LikeCloud.dto.MainResDto.MainListRes;
import com.LikeCloud.LikeCloud.dto.MainResDto.waterDropRes;
import com.LikeCloud.LikeCloud.repository.DailyPlanRepository;
import com.LikeCloud.LikeCloud.repository.MonthlyPlanRepository;
import com.LikeCloud.LikeCloud.repository.MyCloudRepository;
import com.LikeCloud.LikeCloud.repository.ShortPlanRepository;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import com.LikeCloud.LikeCloud.repository.YearPlanRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainService {

    private final YearPlanRepository yearPlanRepository;
    private final ShortPlanRepository shortPlanRepository;
    private final MonthlyPlanRepository monthlyPlanRepository;
    private final DailyPlanRepository dailyPlanRepository;
    private final UserRepository userRepository;
    private final MyCloudRepository myCloudRepository;

    //현재 날짜(년, 월, 일)
    LocalDate now = LocalDate.now();
    Integer year = now.getYear();
    Integer month = now.getMonthValue();
    Integer day = now.getDayOfWeek().getValue();
    List<Day> dayList =  new ArrayList<>(Arrays.asList(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY));


    /**
     * 메인화면 API
     * 현재 년, 월, 일 및 userID에 해당하는 목표(1년, 단기)들을 반환.
     * @return 목표들(1년, 단기목표)
     */
    public MainListRes getPlanList() {

        //1년 목표, 단기목표 조회
        List<YearPlan> yearPlanList = yearPlanRepository.findByUserId(1L);
        List<ShortPlan> shortPlanList = shortPlanRepository.findByUserId(1L);

        List<Long> yearPlanIds = yearPlanList.stream().map(YearPlan::getId).collect(Collectors.toList());

        MonthlyPlan monthlyPlan = null;
        DailyPlan dailyPlan = null;

        //오늘날짜를 기준으로 1년 목표와 연관된 월별, 일별 목표 조회
        List<MainResDto.YearPlanListRes> yearPlanListRes =
            yearPlanList.stream()
                .map(yearPlan -> MainResDto.YearPlanListRes.from(yearPlan,
                        monthlyPlanRepository.findByYearDate(yearPlan.getId(), year, month).orElse(monthlyPlan),
                        dailyPlanRepository.findByYearAndDate(yearPlan.getId(), dayList.get(day-1)).orElse(dailyPlan)))
                .collect(Collectors.toList());

        //오늘날짜를 기준으로 단기 목표와 연관된 월별, 일별 목표 조회
        List<MainResDto.ShortPlanListRes> shortPlanListRes =
            shortPlanList.stream()
                .map(shortPlan -> MainResDto.ShortPlanListRes.from(shortPlan,
                        dailyPlanRepository.findByShortAndDate(shortPlan.getId(), dayList.get(day-1)).orElse(dailyPlan)))
                .collect(Collectors.toList());

        return new MainListRes(yearPlanListRes, shortPlanListRes);
    }

    /**
     * 메인화면에서 오늘 목표를 달성했을 때 호출되는 API
     * 수증기, 미니구름, 구름의 개수를 update한다.
     * @param type
     */
    @Transactional
    public void dailyDone(Integer type, Integer exception, DailyDoneReq dailyDoneReq) {
        Integer[] cloudNums;
        if (dailyDoneReq.getYear_plan_id() != null) {
            YearPlan yearPlan = findYearPlan(Long.valueOf(dailyDoneReq.getYear_plan_id()));

            DailyPlan dailyPlan = dailyPlanRepository.findByYearAndDate(yearPlan.getId(), dayList.get(day-1))
                .orElseThrow(() -> new RuntimeException("오늘 설정한 목표가 없습니다."));

            if (yearPlan.getMiniCloud() == 13 || yearPlan.getBigCloud() == 1) {
                yearPlan.updateDone();
                throw new RuntimeException("이미 달성한 목표입니다.");
            }

            //변경되어야 할 구름 개수들 확인
           cloudNums = checkCloudType(yearPlan.getWaterDrop(), yearPlan.getSteam(), yearPlan.getMiniCloud()
           , yearPlan.getBigCloud(), type);

           yearPlan.updateCloud(cloudNums);
           dailyPlan.updateDone(exception);
           postMyCloud(yearPlan, null, dailyDoneReq.getImage_num(), type);

        } else if (dailyDoneReq.getShort_plan_id() != null) {
            ShortPlan shortPlan = findShortPlan(Long.valueOf(dailyDoneReq.getShort_plan_id()));

            DailyPlan dailyPlan = dailyPlanRepository.findByShortAndDate(shortPlan.getId(), dayList.get(day-1))
                .orElseThrow(() -> new RuntimeException("오늘 설정한 목표가 없습니다."));

            if (shortPlan.getMiniCloud() == shortPlan.getPeriod() ) {
                shortPlan.updateDone();
                throw new RuntimeException("이미 달성한 목표입니다.");
            }

            cloudNums = checkCloudType(shortPlan.getWaterDrop(), shortPlan.getSteam(), shortPlan.getMiniCloud(), 0, type);

            shortPlan.updateCloud(cloudNums);
            dailyPlan.updateDone(exception);
            postMyCloud(null, shortPlan, dailyDoneReq.getImage_num(), type);
        }
    }

    /**
     * 오늘 달성한 목표 취소
     * 상태값 및 waterDrop 개수 update
     * @param cancelDailyDoneReq
     * @param exception
     * @return
     */
    @Transactional
    public MainResDto.waterDropRes cancelDailyDone(CancelDailyDoneReq cancelDailyDoneReq, Integer exception) {
        if (cancelDailyDoneReq.getYear_plan_id() != null) {
            YearPlan yearPlan = findYearPlan(Long.valueOf(cancelDailyDoneReq.getYear_plan_id()));
            if (yearPlan.getSteam() != 0 || yearPlan.getMiniCloud() != 0 || yearPlan.getBigCloud() != 0) {
                throw new RuntimeException("수증기 또는 구름이 생겼을 때는 목표 취소가 불가합니다");
            }
            yearPlanRepository.updateWaterDrop(yearPlan.getId());
            DailyPlan dailyPlan = yearPlan.getDailyPlans().stream()
                .filter(a -> a.getDay() == dayList.get(day-1) && a.getYearPlan() == yearPlan).findAny().get();
            dailyPlan.updateException(exception);
            return new MainResDto.waterDropRes(yearPlan.getWaterDrop()-1);

        } else if(cancelDailyDoneReq.getShort_plan_id() != null) {
            ShortPlan shortPlan = findShortPlan(Long.valueOf(cancelDailyDoneReq.getShort_plan_id()));
            if (shortPlan.getSteam() != 0 || shortPlan.getMiniCloud() != 0) {
                throw new RuntimeException("수증기 또는 구름이 생겼을 때는 목표 취소가 불가합니다");
            }
            shortPlanRepository.updateWaterDrop(shortPlan.getId());
            DailyPlan dailyPlan = shortPlan.getDailyPlans().stream()
                .filter(a -> a.getDay() == dayList.get(day-1) && a.getShortPlan() == shortPlan).findAny().get();
            dailyPlan.updateException(exception);
            return new MainResDto.waterDropRes(shortPlan.getWaterDrop()-1);
        }
        return null;
    }

    /**
     * 현재 물방울, 수증기, 미니구름, 구름 개수 현황을 바탕으로 type에 따라 증가시킨다.
     * @param waterDrop
     * @param steam
     * @param miniCloud
     * @param bigCloud
     * @param type
     * @return 증가된 물방울, 수증기, 미니구름, 구름 개수를 담은 리스트
     */
    @Transactional
    public Integer[] checkCloudType(Integer waterDrop, Integer steam, Integer miniCloud, Integer bigCloud, Integer type) {
        //물방울, 수증기, 미니구름, 구름 개수 현황
        Integer[] cloudNum = new Integer[4];
        if (type == 0) {
            waterDrop += 1;
            cloudNum = new Integer[] {waterDrop, steam, miniCloud, bigCloud};
        } else if(type == 1) {
            steam += 1;
            cloudNum = new Integer[] {0, steam, miniCloud, bigCloud};
        } else if(type == 2) {
            miniCloud += 1;
            cloudNum = new Integer[] {0, 0, miniCloud, bigCloud};
        } else {
            bigCloud += 1;
            cloudNum = new Integer[] {0, 0, miniCloud, bigCloud};
        }
        return cloudNum;
    }

    /**
     * 미니구름, 구름 생성 시 구름 히스토리 내역 저장
     * @param yearPlan
     * @param shortPlan
     * @param cloudImage
     * @param type
     */
    @Transactional
    public void postMyCloud(YearPlan yearPlan, ShortPlan shortPlan, Integer cloudImage, Integer type) {
        if (type == 2 || type == 3) {
            MyCloud myCloud = MyCloud.builder()
                .user(findUser())
                .yearPlan(yearPlan)
                .shortPlan(shortPlan)
                .cloudImage(cloudImage)
                .cloudType(findCloudType(type))
                .build();

            myCloudRepository.save(myCloud);
        }
    }

    public CloudType findCloudType(Integer type) {
        CloudType cloudType = Arrays.stream(CloudType.values())
            .filter(c -> c.getNum() == type)
            .findAny().get();
        return cloudType;
    }

    public User findUser() {
        User user = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("User를 찾을 수 없습니다."));
        return user;
    }

    public YearPlan findYearPlan(Long yearPlanId) {
        YearPlan yearPlan = yearPlanRepository.findById(
                Long.valueOf(yearPlanId))
            .orElseThrow(() -> new RuntimeException("1년 목표를 찾을 수 없습니다."));

        return yearPlan;
    }

    public ShortPlan findShortPlan(Long shortPlanId) {
        ShortPlan shortPlan = shortPlanRepository.findById(
            Long.valueOf(shortPlanId))
            .orElseThrow(() -> new RuntimeException("단기 목표를 찾을 수 없습니다."));

        return shortPlan;
    }
}
