package com.dongi.boards.common;

import java.util.HashMap;

import org.springframework.jdbc.support.JdbcUtils;

// Entity를 DTO로 쉽게 변환하기 위한 클래스
@SuppressWarnings("serial")
public class CamelHashMap extends HashMap<String, Object> {
	@Override
	public Object put(String key, Object value) {
		return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(key), value);
	}
}