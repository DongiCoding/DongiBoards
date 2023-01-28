package com.dongi.boards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 로그인 기능이 추가 될 때 이용할 홈 컨트롤러, 2023/01/28 기준 홈으로 돌아가는 매핑용으로 사용중
@Controller
public class HomeController {
	@GetMapping("/")
	public String goHome() {
		return "home";
	}
}
