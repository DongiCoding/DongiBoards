package com.dongi.boards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongi.boards.entity.Cmmnt;

public interface CmmntRepository extends JpaRepository<Cmmnt, Integer> {
	
	@Query(value=""
			+ "SELECT *\r\n"
			+ "FROM T_CMMNT\r\n"
			+ "WHERE BRD_NM = :brdNm", nativeQuery=true)
	List<Cmmnt> findByBrdNm(@Param("brdNm")int brdNm);

}
