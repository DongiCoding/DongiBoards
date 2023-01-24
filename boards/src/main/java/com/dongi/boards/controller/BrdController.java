package com.dongi.boards.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView getBrdList(
									BrdDTO brdDto,                                           // 화면단에 정보를 뿌려주기 위한 틀
									@PageableDefault(page = 0, size = 10) Pageable pageable  // 페이지는 0부터 10개의 게시글
								  ) {
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
}
