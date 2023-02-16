package com.dongi.boards.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongi.boards.dto.CmmntDTO;
import com.dongi.boards.entity.Brd;
import com.dongi.boards.entity.Cmmnt;
import com.dongi.boards.repository.BrdRepository;
import com.dongi.boards.repository.CmmntRepository;
import com.dongi.boards.service.CmmntService;

@Service
public class CmmntServiceImpl implements CmmntService {
	
	// // DB와 서비스 사이에서 교류 일을 해줄 일꾼
	@Autowired
	BrdRepository brdRepository;
	
	@Autowired
	CmmntRepository cmmntRepository;
	
	// 댓글 목록 조회
	@Override
	public List<CmmntDTO> gtcmmntList(int brdNm) {
		List<Cmmnt> cmmntList = cmmntRepository.findByBrdNm(brdNm);
		return cmmntList.stream()
				        .map(cmmnt -> CmmntDTO.trnsfrTCmmntDTO(cmmnt))
				        .collect(Collectors.toList());
	}
	
	// 댓글 등록
	@Transactional
	@Override
	public CmmntDTO insrtCmmnt(int brdNm, CmmntDTO cmmntDTO) {
		// 게시글이 없는 경우 예외 처리
		Brd brd = brdRepository.findById(brdNm)
				.orElseThrow(() -> new IllegalArgumentException("There is no brd that what you are looking for"));
		
		// DTO를 ENTITY로 변환
		Cmmnt cmmnt = Cmmnt.builder()
				           .cmmntId(cmmntDTO.getCmmntId())
				           .brd(brd)
				           .cmmntWrtr(cmmntDTO.getCmmntWrtr())
				           .cmmntCntnt(cmmntDTO.getCmmntCntnt())
				           .build();
		
		// DB에 저장된 ENTITY를 받아옴
		Cmmnt insrtdCmmnt = cmmntRepository.save(cmmnt);
		
		// 저장된 ENTITY를 DTO로 변환해서 반환
		return CmmntDTO.trnsfrTCmmntDTO(insrtdCmmnt);
	}
	
	// 댓글 삭제
	@Transactional
	@Override
	public CmmntDTO dltCmmnt(CmmntDTO cmmntDTO) {
		// 보드 넘버 변수
		int brdNm = cmmntDTO.getBrdNm();
		
		// 삭제할 댓글 아이디
		int cmmntId = cmmntDTO.getCmmntId();
		
		// 삭제할 댓글 찾기
		Cmmnt dltdCmmnt = cmmntRepository.findCmmntWithBrdNmCmmntId(brdNm, cmmntId);
		
		// 댓글 삭제하기
		cmmntRepository.delete(dltdCmmnt);
		
		return CmmntDTO.trnsfrTCmmntDTO(dltdCmmnt);
		
		
	}

	@Override
	public CmmntDTO edtCmmnt(CmmntDTO cmmntDTO) {
		// 보드 넘버 가져오기
		int brdNm = cmmntDTO.getBrdNm();
		
		// 게시글이 없는 경우 예외 처리
		Brd brd = brdRepository.findById(brdNm)
				.orElseThrow(() -> new IllegalArgumentException("There is no brd that what you are looking for"));
		
		// DTO를 ENTITY로 변환
		Cmmnt cmmnt  = Cmmnt.builder()
							.brd(brd)
							.cmmntId(cmmntDTO.getCmmntId())
							.cmmntWrtr(cmmntDTO.getCmmntWrtr())
							.cmmntCntnt(cmmntDTO.getCmmntCntnt())
				            .build();
		
		// 변경된 댓글 저장
		cmmntRepository.save(cmmnt);
		
		return CmmntDTO.trnsfrTCmmntDTO(cmmnt);
	}

}
