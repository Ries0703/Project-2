package com.javaweb.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignment_building")
@Getter
@Setter
@Deprecated
public class AssignmentBuildingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY) // Optional: Configure fetch type
	@JoinColumn(name = "staffid")
	private UserEntity staff; // Assuming StaffEntity exists

	@ManyToOne(fetch = FetchType.LAZY) // Optional: Configure fetch type
	@JoinColumn(name = "buildingid")
	private BuildingEntity building; // Assuming BuildingEntity exists

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "modifieddate")
	private LocalDateTime modifiedDate;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "modifiedBy")
	private String modifiedBy;
}
