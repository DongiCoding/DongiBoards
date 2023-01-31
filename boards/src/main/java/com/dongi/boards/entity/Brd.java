package com.dongi.boards.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamicInsert
@Entity                                						// 엔티티 선언
@Table(name="T_BRD")                   						// 테이블 네임								  					    // lombok: getter, setter
@SequenceGenerator(                    						// increment보다 유용하다, 조작 가능
		name="BrdSequenceGenerator",
		sequenceName="T_BRD_SQN",
		initialValue=1,               						// 1부터 시작
		allocationSize=1               						// 1씩 증가
		)
@Data
@NoArgsConstructor                     						// lombok: 기본생성자
@AllArgsConstructor                   					 	// lombok: 모든 매개변수를 받는 생성자
@Builder                               						// lombok: 객체 생성, DTO 변경시 편함
public class Brd {
	@Id                                						// primary key 설정
	@GeneratedValue(										// SequenceGenerator 적용
			strategy=GenerationType.SEQUENCE,				// 전략: 시퀀스(연속)
			generator="BrdSequenceGenerator"                // 제네레이터명 귀속
	)
	private int brdNm;                                      // 게시글 번호, primary key
	
	@Column(nullable = false)                               // NotNull
	@ColumnDefault("0")
	private int brdCnt;                                     // 게시글 조회수
	
	@Column(length = 1000, nullable = false)				// NotNull
	private String brdCntnt;								// 게시글 내용
	
	@Column(nullable = false)                               // NotNull
	private LocalDateTime brdDttm;                         // 게시글 등록시간
	
	@Column(nullable = false)                               // NotNull
	private String brdTtl;                                  // 게시글 제목
	
	@Column(nullable = false)                               // NotNull
	private String brdWrtr;                                 // 게시글 작성자
	
	@Column(nullable = false)                               // NotNull
	private int brdCtgry;                                   // 게시글 카테고리
	
	@Column(nullable = false)                               // NotNull
	private int brdOrgnNm;                                  // 게시글 본래의 번호
	
	@Column(nullable = false)                               // NotNull
	@ColumnDefault("0")
	private int brdGrpOrdr;                                 // 게시글의 답글이 달릴 때 그것을 그룹으로 묶는다, 그 그룹안에서의 순서
	
	@Column(nullable = false)                               // NotNull
	@ColumnDefault("0")
	private int brdGrpLyr;                                  // 게시글 답글의 층계, 예를 들어 답글의 답글을 달 수 있게 만듬
}
