package com.wtp.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  공공 API 를 읽어서 db에 저장한다. 
 *	TODO
 *	데이터 가져오기 - https://www.data.go.kr/
 * 	db 저장
 */

@Controller
@RequestMapping("/importapi")
public class ImportPublicAPIController {
	public void importPublicAPI (@RequestParam String apiUrl) {
	// pseudo coding
		//	1. www.data.go.kr에서 api 읽기
		
		//	2. api 읽어서 객체로 변환
		
		// 	3. db 저장 
	
	}
}
