package com.dongi.boards.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dongi.boards.dto.BrdDTO;
import com.dongi.boards.service.BrdService;

@RestController
@RequestMapping("/brd")
public class BrdController {
	// 보드 서비스 객체
	@Autowired private BrdService brdService;
	
	// 보드 리스트를 불러오는 맵핑
	@GetMapping("/brdList")
	public ModelAndView gtBrdList(
									BrdDTO brdDto,                                           // 화면단에 정보를 뿌려주기 위한 틀
									@PageableDefault(page = 0, size = 10) @SortDefault.SortDefaults({
										@SortDefault(sort = "brdOrgnNm", direction = Sort.Direction.DESC),
										@SortDefault(sort = "brdGrpOrdr", direction = Sort.Direction.ASC)
									}) Pageable pageable) {
		// 받아온 리스트를 Page<Brd>
		Page<BrdDTO> brdListDTO = brdService.getBrdList(pageable);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/mainBoard.html");
		mv.addObject("brdList", brdListDTO);
		return mv;
	}
	
	// 보드 작성 페이지 불러오기
	@GetMapping("/wrtBrd")
	public ModelAndView wrtBrdView() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/writeBoard.html");
		return mv;
	}
	
	// 작성한 보드 인서트하는 맵핑
	@PostMapping("/insrtBrd")
	public void insrtBrd(BrdDTO brdDTO, HttpServletResponse response) throws IOException {
		brdService.insrtBrd(brdDTO);
		
		response.sendRedirect("/brd/brdList");
	}
	
	// 게시글의 제목 클릭시 조회수 증가
	@GetMapping("/updtBrdCnt/{brdNm}")
	public void updtBrdCnt(@PathVariable int brdNm, HttpServletResponse response) throws IOException {
		brdService.updtBrdCnt(brdNm);
		
		response.sendRedirect("/brd/brd/" + brdNm);
	}
	
	// 게시글의 제목 클릭시 게시글 상세조회
	@GetMapping("/brd/{brdNm}")
	public ModelAndView gtBrd(@PathVariable int brdNm) {
		// 화면단에 보여주기 위해 SERVICE에서 DTO로 변환할 예정
		BrdDTO brdDTO = brdService.gtBrd(brdNm);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/viewBoard.html");
		mv.addObject("brd", brdDTO);
		
		return mv;
	}
	
	// 게시글 수정 페이지 이동
	@GetMapping("/brd/edt/{brdNm}")
	public ModelAndView gtBrdEdt(@PathVariable int brdNm) {
		// 화면단에 보여주기 위해 SERVICE에서 DTO로 변환할 예정
		BrdDTO brdDTO = brdService.gtBrd(brdNm);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/editBoard.html");
		mv.addObject("brd", brdDTO);
		
		return mv;
	}
	
	// 특정 게시글 삭제
	@DeleteMapping("/dltBrd")
	public void dltBrd(@RequestParam("brdNm") int brdNm) {
		brdService.dltBrd(brdNm);
	}
	
	// 특정 게시글 수정
    @PostMapping("/updtBrd")
    public ResponseEntity<BrdDTO> updtBrd(BrdDTO brdDTO) {
    	BrdDTO rtrndBrdDTO = brdService.updtBrd(brdDTO);
    	
		return ResponseEntity.status(HttpStatus.OK).body(rtrndBrdDTO); 	
    }
    
    // 게시글의 답변 작성 페이지로 이동 (원글의 정보를 담아간다.)
    @GetMapping("/brd/rply/{brdNm}")
    public ModelAndView gtOrgnBrd(@PathVariable int brdNm) {
    	BrdDTO rtrndBrdDTO = brdService.gtBrd(brdNm);
    	
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/board/replyBoard.html");
    	mv.addObject("brd", rtrndBrdDTO);
    	
    	return mv;
    }
    
    // 작성한 답글 보드 인서트(native query로 작성)
    @PostMapping("/insrtRplyBrd")
    public ResponseEntity<BrdDTO> isrtRplyBrd(@RequestBody BrdDTO brdDTO) {
    	BrdDTO insrtdDTO = brdService.isrtRplyBrd(brdDTO);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(insrtdDTO);
    }
    
	
}
