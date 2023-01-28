package com.dongi.boards.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dongi.boards.dto.BrdDTO;

public interface BrdService {
	
	// 보드 리스트를 불러오기
	Page<BrdDTO> getBrdList(Pageable pageable);
	
	// 작성한 보드 등록
	void insrtBrd(BrdDTO brdDTO);
	
	// 게시글의 제목 클릭시 조회수 증가
	void updtBrdCnt(int brdNm);
	
	// 게시글의 제목 클릭시 게시글 상세조회
	BrdDTO gtBrd(int brdNm);
	
	// 특정 게시글 삭제
	void dltBrd(int brdNm);
	
	// 특정 게시글 수정
	BrdDTO updtBrd(BrdDTO brdDTO);

}
