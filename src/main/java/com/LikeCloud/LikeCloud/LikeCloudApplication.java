package com.LikeCloud.LikeCloud;

import java.time.OffsetDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LikeCloudApplication {

	public static void main(String[] args) {
		System.out.println(OffsetDateTime.now());
		SpringApplication.run(LikeCloudApplication.class, args);
	}

}
