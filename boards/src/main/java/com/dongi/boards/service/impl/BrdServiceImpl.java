package com.dongi.boards.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dongi.boards.dto.BrdDTO;
import com.dongi.boards.entity.Brd;
import com.dongi.boards.repository.BrdRepository;
import com.dongi.boards.service.BrdService;

@Service
public class BrdServiceImpl implements BrdService {
	
	// DB와 서비스 사이에서 교류 일을 해줄 일꾼
	@Autowired BrdRepository brdRepository;
	
	// 게시글 리스트 불러오는 메서드
	@Override
	public Page<BrdDTO> getBrdList(Pageable pageable) {
		// Page 형태의 BrdEntity
		Page<Brd> pgBrdList = brdRepository.findAll(pageable);
		
		// Page 형태의 DTO로 변환
		Page<BrdDTO> pgBrdListDTO = pgBrdList.map(pgBrd -> 
												  BrdDTO.builder()
												        .brdNm(pgBrd.getBrdNm())
												        .brdCnt(pgBrd.getBrdCnt())
												        .brdCntnt(pgBrd.getBrdCntnt())
												        .brdDttm(pgBrd.getBrdDttm())
												        .brdTtl(pgBrd.getBrdTtl())
												        .brdWrtr(pgBrd.getBrdWrtr())
												        .brdCtgry(pgBrd.getBrdCtgry())
												        .brdOrgnNm(pgBrd.getBrdGrpOrdr())
												        .brdGrpOrdr(pgBrd.getBrdGrpOrdr())
												        .brdGrpLyr(pgBrd.getBrdGrpLyr())
												        .build()
				                                 );
		// Page 형태의 brdDTO 반환
		return pgBrdListDTO;
	}
	
	// 작성한 보드 등록
	@Transactional // 예외나 에러 등장시 롤백
	@Override
	public void insrtBrd(BrdDTO brdDTO) {
		// DTO를 ENTITY로 변환
		Brd brd = Brd.builder()
				     .brdNm(brdDTO.getBrdNm())
				     .brdCntnt(brdDTO.getBrdCntnt())
				     .brdDttm(LocalDateTime.now())
				     .brdTtl(brdDTO.getBrdTtl())
				     .brdWrtr(brdDTO.getBrdWrtr())
				     .brdCtgry(brdDTO.getBrdCtgry())
				     .brdOrgnNm(brdDTO.getBrdNm())
				     .build();
		
		brdRepository.save(brd);
		brdRepository.flush();
		
	}
	
	// 게시글의 제목 클릭시 조회수 증가
	@Override
	public void updtBrdCnt(int brdNm) {
		brdRepository.updtBrdCnt(brdNm);
		
	}
	
	// 게시글의 제목 클릭시 게시글 상세조회
	@Override
	public BrdDTO gtBrd(int brdNm) {
		// 게시글이 존재하지 않을 경우 null
		Brd brd = brdRepository.findById(brdNm).orElse(null);
		
		// ENTITY를 DTO로 변환
		BrdDTO brdDTO = BrdDTO.builder()
				              .brdNm(brd.getBrdNm())
				              .brdCnt(brd.getBrdCnt())
				              .brdCntnt(brd.getBrdCntnt())
				              .brdDttm(brd.getBrdDttm())
				              .brdTtl(brd.getBrdTtl())
				              .brdWrtr(brd.getBrdWrtr())
				              .brdCtgry(brd.getBrdCtgry())
				              .brdOrgnNm(brd.getBrdOrgnNm())
				              .brdGrpOrdr(brd.getBrdGrpOrdr())
				              .brdGrpLyr(brd.getBrdGrpLyr())
				              .build();
		// 변환한 DTO를 반환
		return brdDTO;
	}
	
	// 특정 게시글 삭제
	@Transactional
	@Override
	public void dltBrd(int brdNm) {
		brdRepository.deleteById(brdNm);
	}
	


}
