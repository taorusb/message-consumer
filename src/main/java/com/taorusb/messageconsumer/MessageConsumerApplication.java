package com.taorusb.messageconsumer;

import com.taorusb.messageconsumer.dto.MessageDto;
import com.taorusb.messageconsumer.service.PersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import java.util.Objects;

@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class MessageConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageConsumerApplication.class, args);
	}

	private final PersistenceService persistenceService;

	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.consumer.group}")
	void listen(MessageDto message, Acknowledgment acknowledgment) {
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
