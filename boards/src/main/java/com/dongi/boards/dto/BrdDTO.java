package com.dongi.boards.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongi.boards.entity.Brd;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime brdDttm;
	private String brdTtl;
	private String brdWrtr;
	private int brdCtgry;
	private int brdOrgnNm;
	private int brdGrpOrdr;
	private int brdGrpLyr;
	
	// Entity를 DTO로 변환해주는 메서드
	public static BrdDTO trnsfrTBrdDTO(Brd brd) {
		return new BrdDTO(
					brd.getBrdNm(),
					brd.getBrdCnt(),
					brd.getBrdCntnt(),
					brd.getBrdDttm(),
					brd.getBrdTtl(),
					brd.getBrdWrtr(),
					brd.getBrdCtgry(),
					brd.getBrdOrgnNm(),
					brd.getBrdGrpOrdr(),
					brd.getBrdGrpLyr()
				);
	}
}
