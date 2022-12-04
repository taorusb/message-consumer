package com.taorusb.messageconsumer.consumer;

import com.taorusb.messageconsumer.dto.MessageDto;
import com.taorusb.messageconsumer.service.PersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Log4j2
@Component
public class KafkaConsumer {

	private final PersistenceService persistenceService;

	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.consumer.group}")
	public void listen(MessageDto message, Acknowledgment acknowledgment) {
		persistenceService.saveMessage(message).whenComplete((result, ex) -> {
			if (Objects.nonNull(ex)) {
				log.error("IN listen : Exception occurred. message: " + message + " reason is " + ex.getMessage());
			} else {
				log.info("IN listen : Successful persisted message: " + message);
				acknowledgment.acknowledge();
			}
		});

	}
}
