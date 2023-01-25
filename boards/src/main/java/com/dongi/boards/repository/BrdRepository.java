package com.dongi.boards.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongi.boards.entity.Brd;


// MySQL과 Server 사이에서 일을 해줄 Repository
@Transactional
public interface BrdRepository extends JpaRepository<Brd, Integer> {
	
	@Modifying
	@Query(value="UPDATE T_BRD SET BRD_CNT = BRD_CNT + 1 WHERE BRD_NM = :brdNm", nativeQuery=true)
	void updtBrdCnt(@Param("brdNm") int brdNm);

}
