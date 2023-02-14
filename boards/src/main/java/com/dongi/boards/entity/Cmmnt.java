package com.dongi.boards.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamicInsert
@Entity
@Table(name="T_CMMNT")
@SequenceGenerator(
		name="CmmntSequenceGenerator",
		sequenceName="T_CMMNT_SQN",
		initialValue=1,
		allocationSize=1
		)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(CmmntId.class)
public class Cmmnt {
	@Id
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator="CmmntSequenceGenerator"
	)
	private int cmmntId;
	@Id
	@ManyToOne
	@JoinColumn(name="BRD_NM")
	private Brd brd;
	
	@Column(nullable = false)
	private String cmmntWrtr;
	
	@Column(length = 3000, nullable = false)
	private String cmmntCntnt;
}
