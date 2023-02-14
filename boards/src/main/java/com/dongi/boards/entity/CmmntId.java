package com.dongi.boards.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CmmntId implements Serializable {
	private int cmmntId;
	private int brd;
}
