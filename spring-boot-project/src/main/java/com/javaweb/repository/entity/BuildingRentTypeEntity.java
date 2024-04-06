package com.javaweb.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "buildingrenttype")
@Deprecated
public class BuildingRentTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull

	private Long buildingId;


	private Long rentTypeId;

}
