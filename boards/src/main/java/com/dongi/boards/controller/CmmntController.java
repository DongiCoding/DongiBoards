package com.dongi.boards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dongi.boards.dto.CmmntDTO;
import com.dongi.boards.service.CmmntService;

@RestController
@RequestMapping("/brd")
public class CmmntController {
	
	@Autowired
	private CmmntService cmmntService;
	
	// 댓글 등록
	@PostMapping("/brd/{brdNm}/insrtCmmnt")
	public ResponseEntity<CmmntDTO> insrtCmmnt(@PathVariable int brdNm, 
			                                   @RequestBody CmmntDTO cmmntDTO) {
		CmmntDTO svdCmmntDTO = cmmntService.insrtCmmnt(brdNm, cmmntDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(svdCmmntDTO);
	}
}
