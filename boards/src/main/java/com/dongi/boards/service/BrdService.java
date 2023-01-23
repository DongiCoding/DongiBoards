package com.dongi.boards.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dongi.boards.dto.BrdDTO;

public interface BrdService {
	
	// 보드 리스트를 불러오기
	Page<BrdDTO> getBrdList(Pageable pageable);
	
	// 작성한 보드 등록
	void insrtBrd(BrdDTO brdDTO);

}
