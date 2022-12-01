package com.taorusb.messageconsumer.util;

import com.taorusb.messageconsumer.dto.MessageDto;
import org.assertj.core.api.JUnitJupiterSoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TelemetryParserTest {

	@Test
	void parse() {
		MessageDto messageDto = new MessageDto();
		messageDto.setActiveService("youtube");
		messageDto.setUuid(UUID.randomUUID().toString());
		messageDto.setQualityScore(1);
		messageDto.setAgentId("PC");
		messageDto.setPreviousMessageTimestamp(123L);
		Assertions.assertNotNull(TelemetryParser.parse(messageDto));
	}
}