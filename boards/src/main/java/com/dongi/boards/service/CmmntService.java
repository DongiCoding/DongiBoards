package com.dongi.boards.service;

import java.util.List;

import com.dongi.boards.dto.CmmntDTO;

public interface CmmntService {
	
	// 댓글 목록 조회
	List<CmmntDTO> gtcmmntList(int brdNm);
	
	// 댓글 등록
	CmmntDTO insrtCmmnt(int brdNm, CmmntDTO cmmntDTO);
	
	// 댓글 삭제
	CmmntDTO dltCmmnt(CmmntDTO cmmntDTO);

}
