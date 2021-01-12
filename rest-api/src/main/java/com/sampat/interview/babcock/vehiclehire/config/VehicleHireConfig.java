package com.sampat.interview.babcock.vehiclehire.config;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleHireConfig {
  @Bean
  public Clock systemClock() {
    return Clock.systemDefaultZone();
  }
}
