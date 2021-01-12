package com.sampat.interview.babcock.vehiclehire.config;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleHireTestConfig {
  @Bean
  public Clock clock() {
    return Clock.fixed(Instant.parse("2021-01-12T06:00:00Z"), ZoneOffset.systemDefault());
  }
}
