package com.taorusb.messageconsumer.repository;

import com.taorusb.messageconsumer.domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, String> {
}
