package com.taorusb.messageconsumer.repository;

import com.taorusb.messageconsumer.domain.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {
}
