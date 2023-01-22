package com.dongi.boards.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

// HiddenHttpMethodFilter를 사용하면 View단에서 FORM으로 GET 또는 POST 전송후
// PUT, DELETE, PATCH등으로 타입을 변환하여 받을 수 있음
/*
<form action="#" th:action="@{/test/{testId}(tesetId=${test.id})}" th:object="${test}" method="post">
	<input type="hidden" name="_method" value="put"> <!-- HiddenHttpMethodFilter 사용 -->
	-> 하지만 바로 아랫줄을 보면 form태그 안에 같이 hidden타입으로 name="_method",  value="받고자 하는 타입"으로 하는 
	태그를 추가 해주기만 하면 서버단에서는 개발자가 원하는 메서드타입으로 받을 수 있다.
*/
@Configuration
public class WebMvcConfiguration {
	@Bean
	public HiddenHttpMethodFilter httpMethodFilter() {
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		return hiddenHttpMethodFilter;
	}
}
