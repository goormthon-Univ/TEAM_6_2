package com.LikeCloud.LikeCloud.service;


import com.LikeCloud.LikeCloud.domain.entity.DailyPlan;
import com.LikeCloud.LikeCloud.domain.entity.MonthlyPlan;
import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import com.LikeCloud.LikeCloud.domain.type.Day;
import com.LikeCloud.LikeCloud.dto.MainResDto;
import com.LikeCloud.LikeCloud.dto.MainResDto.MainListRes;
import com.LikeCloud.LikeCloud.repository.DailyPlanRepository;
import com.LikeCloud.LikeCloud.repository.MonthlyPlanRepository;
import com.LikeCloud.LikeCloud.repository.ShortPlanRepository;
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

    /**
     * 메인화면 API
     * 현재 년, 월, 일 및 userID에 해당하는 목표(1년, 단기)들을 반환.
     * @return 목표들(1년, 단기목표)
     */
    public MainListRes getPlanList() {
        //현재 날짜(년, 월, 일)
        LocalDate now = LocalDate.now();
        Integer year = now.getYear();
        Integer month = now.getMonthValue();
        Integer day = now.getDayOfWeek().getValue();

        //enum 타입 추출
        List<Day> dayList =  new ArrayList<>(Arrays.asList(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY));

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

}
