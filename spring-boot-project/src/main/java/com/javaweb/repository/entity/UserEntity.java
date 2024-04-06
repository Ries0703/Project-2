package com.javaweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // Mark this class as a JPA entity
@Table(name = "user") // Specify the corresponding database table name
@Getter
@Setter
public class UserEntity {

	@Id // Mark this field as the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment the ID
	@Column(name = "id")
	private Long id;

	@Column(name = "username", nullable = false, unique = true) // Not null and unique
	private String username;

	@Column(name = "password", nullable = false) // Not null
	private String password; // Consider using a secure password hashing mechanism

	@Column(name = "fullname")
	private String fullName; // Use camelCase for better readability

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "status", nullable = false) // Not null
	private Integer status;

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "modifieddate")
	private LocalDateTime modifiedDate;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "modifiedBy")
	private String modifiedBy;
}