package com.zerock.service;

import java.util.ArrayList;

import com.zerock.command.ScoreVO;

public interface ScoreService {

	
	public void scoreRegist(ScoreVO dao);     //점수등록
	public ArrayList<ScoreVO> scoreResult();  //점수결과
	public void scoredelete(String num);      //점수삭제 
	
	
	
	
}
