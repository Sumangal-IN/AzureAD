package com.kingfisher.agile.auth.client.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ACCESS_LOG")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccessLog {

	@Id
	@Column(name = "JSESSIONID")
	String jSessionID;

	@Column(name = "REPLY_URL")
	String replyURL;

	@Column(name = "REQUEST_DATE")
	Date requestedOn;

	@Setter
	@Column(name = "SUCCESS")
	boolean success;

	@Column(name = "SUCCESS_DATE")
	Date succeededOn;

}
