package com.taorusb.messageconsumer.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "agents")
public class Agent {
	@Id
	@Column(name = "agent_id")
	private String id;
	@OneToMany(mappedBy = "agent")
	private List<Telemetry> telemetries;

	@Override
	public String toString() {
		return "Agent{" +
				"id='" + id + '\'' +
				", telemetries=" + telemetries +
				'}';
	}
}
