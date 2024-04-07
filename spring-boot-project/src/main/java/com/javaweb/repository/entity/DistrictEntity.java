package com.javaweb.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;




@Entity
@Table(name = "district")
@Getter
@Setter
public class DistrictEntity {
	
	@OneToMany(mappedBy = "districtEntity")
	private List<BuildingEntity> buildingEntityList;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "code")
	@NotNull
	private String code;

	@Column(name = "name")
	@NotNull
	private String name;
}
