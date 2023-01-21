package com.dongi.boards;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// AWS ec2로 배포 예정
// 전통적인 방법인 war 파일로 배포하기 위한 클래스 (SpringBootServletInitializer를 상속 받아야한다.)
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BoardsApplication.class);
	}

}
