package com.dongi.boards.dto;

import com.dongi.boards.entity.Cmmnt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CmmntDTO {
	private int cmmntId;
	private int brdNm;
	private String cmmntWrtr;
	private String cmmntCntnt;
	
	// Entity를 DTO로 변환해주는 메서드
	public static CmmntDTO trnsfrTCmmntDTO(Cmmnt cmmnt) {
		return new CmmntDTO(
					cmmnt.getCmmntId(),
					cmmnt.getBrd().getBrdNm(),
					cmmnt.getCmmntWrtr(),
					cmmnt.getCmmntCntnt()
				);
	}
}
