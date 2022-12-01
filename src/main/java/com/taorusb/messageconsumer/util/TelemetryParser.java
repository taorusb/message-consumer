package com.taorusb.messageconsumer.util;

import com.taorusb.messageconsumer.domain.Agent;
import com.taorusb.messageconsumer.domain.Telemetry;
import com.taorusb.messageconsumer.dto.MessageDto;

import java.util.List;

public class TelemetryParser {

	public TelemetryParser() { }

	public static Telemetry parse(MessageDto messageDto) {
		Telemetry telemetry = new Telemetry();
		Agent agent = new Agent(messageDto.getAgentId(), List.of(telemetry));
		telemetry.setAgent(agent);
		telemetry.setActiveService(messageDto.getActiveService());
		telemetry.setQualityScore(messageDto.getQualityScore());
		return telemetry;
	}
}
