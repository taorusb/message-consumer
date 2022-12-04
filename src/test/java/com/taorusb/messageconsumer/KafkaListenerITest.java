package com.taorusb.messageconsumer;

import com.taorusb.messageconsumer.domain.Agent;
import com.taorusb.messageconsumer.domain.Telemetry;
import com.taorusb.messageconsumer.repository.AgentRepository;
import com.taorusb.messageconsumer.repository.TelemetryRepository;
import org.awaitility.Durations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class KafkaListenerITest {

	@Value("${kafka.topic.name}")
	private String topic;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private TelemetryRepository telemetryRepository;
	private String payload = "{\"uuid\": \"abc\", \"agentId\": \"PC\", \"previousMessageTimestamp\": 123, \"activeService\": \"youtube\", \"qualityScore\": 1}";

	@Test
	public void myTest() throws Exception {
		kafkaTemplate.send(topic, payload);
		await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
			List<Agent> agents = agentRepository.findAll();
			Assertions.assertEquals(1, agents.size());
			List<Telemetry> telemetries = telemetryRepository.findAll();
			Assertions.assertEquals(1, telemetries.size());
		});
	}
}
