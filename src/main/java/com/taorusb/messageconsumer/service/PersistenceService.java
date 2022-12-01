package com.taorusb.messageconsumer.service;

import com.taorusb.messageconsumer.domain.Telemetry;
import com.taorusb.messageconsumer.dto.MessageDto;
import com.taorusb.messageconsumer.repository.AgentRepository;
import com.taorusb.messageconsumer.repository.TelemetryRepository;
import com.taorusb.messageconsumer.util.TelemetryParser;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class PersistenceService {

	private final AgentRepository agentRepository;
	private final TelemetryRepository telemetryRepository;

	@Async
	@Transactional
	public CompletableFuture<Void> saveMessage(MessageDto messageDto) {
		Telemetry telemetry = TelemetryParser.parse(messageDto);
		try {
			agentRepository.save(telemetry.getAgent());
			telemetryRepository.save(telemetry);
		} catch (Exception e) {
			return CompletableFuture.failedFuture(new Exception(e.getMessage()));
		}
		return CompletableFuture.allOf();
	}
}
