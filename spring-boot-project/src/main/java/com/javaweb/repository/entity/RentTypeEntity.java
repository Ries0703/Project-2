package com.javaweb.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "renttype")
public class RentTypeEntity {

	@ManyToMany(mappedBy = "rentTypeEntities")
	private List<BuildingEntity> buildingEntities;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "code")
	private String code;

	@NotNull
	@Column(name = "name")
	private String name;

}
