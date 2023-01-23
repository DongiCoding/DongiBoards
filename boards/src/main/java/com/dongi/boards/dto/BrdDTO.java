package com.dongi.boards.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data								
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrdDTO {
	private int brdNm;
	private int brdCnt;
	private String brdCntnt;
	private LocalDateTime brdDttme;
	private String brdTtl;
	private String brdWrtr;
	private int brdCtgry;
	private int brdOrgnNm;
	private int brdGrpOrdr;
	private int brdGrpLyr;
}
