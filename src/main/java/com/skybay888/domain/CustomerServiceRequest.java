package com.skybay888.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="customer_service_requests")
@Getter @Setter @NoArgsConstructor
public class CustomerServiceRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="customer_service_request_id")
	private Integer customerServiceRequestId;
    
  	@Column(name="request_type")
	private String requestType;
    
  	@Column(name="request_date")
	private Date requestDate;
    
	




}
