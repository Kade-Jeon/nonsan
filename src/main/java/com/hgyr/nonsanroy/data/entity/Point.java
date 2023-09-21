package com.hgyr.nonsanroy.data.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * @author: 전준형
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100)
	private String uid;

	private Long point;

	private Long addPoint;

	private Long minusPoint;

	@Column(length = 300)
	private String nickName;

	@CreationTimestamp
	private LocalTime insertDateTime;
}