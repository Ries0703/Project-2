package com.javaweb.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "renttype")
public class RentTypeEntity {
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
