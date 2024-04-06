package com.javaweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@Deprecated
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "note")
	private String note;

	@ManyToOne(fetch = FetchType.LAZY) // Optional: Configure fetch type for customer
	@JoinColumn(name = "customerid")
	private CustomerEntity customer; // Assuming CustomerEntity exists

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "modifieddate")
	private LocalDateTime modifiedDate;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "modifiedBy")
	private String modifiedBy;
}
