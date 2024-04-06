package com.javaweb.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Table(name = "rentarea")
public class RentAreaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "value")
	private Integer value;

	@ManyToOne
	@JoinColumn(name = "districtid")
	private BuildingEntity building;

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "modifieddate")
	private LocalDateTime modifiedDate;

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "modifiedby")
	private String modifiedBy;

}
