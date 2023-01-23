package com.dongi.boards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongi.boards.entity.Brd;


// MySQL과 Server 사이에서 일을 해줄 Repository
public interface BrdRepository extends JpaRepository<Brd, Integer> {

}
