package com.javaweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "role")
@Getter
@Setter
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false) // Not null
	private String name;

	@Column(name = "code", nullable = false, unique = true) // Not null and unique
	private String code;

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "modifieddate")
	private LocalDateTime modifiedDate;

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "modifiedby")
	private String modifiedBy;
}
