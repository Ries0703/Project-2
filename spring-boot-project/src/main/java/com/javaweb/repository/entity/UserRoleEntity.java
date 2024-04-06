package com.javaweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@Deprecated
public class UserRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY) // Optional: Configure fetch type
	@JoinColumn(name = "roleid")
	private RoleEntity role; // Assuming RoleEntity exists

	@ManyToOne(fetch = FetchType.LAZY) // Optional: Configure fetch type
	@JoinColumn(name = "userid")
	private UserEntity user; // Assuming UserEntity exists

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "modifieddate")
	private LocalDateTime modifiedDate;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "modifiedBy")
	private String modifiedBy;
}
