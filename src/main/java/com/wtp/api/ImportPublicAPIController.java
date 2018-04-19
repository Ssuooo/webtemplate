package com.wtp.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
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
	private static final String apiUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent?";
	// serviceKey=XXX
	// LAWD_CD=11110&	// 지역 코드 
	// DEAL_YMD=201512";// 계약월 
	
	public void importPublicAPI (@RequestParam String apiUrl) {
		HttpURLConnection con = null;
		InputStream inStream = null;
		BufferedReader in = null;
		
	// pseudo coding
		//	1. www.data.go.kr에서 api 읽기
		try {
			// api 호출 
			URL conUrl = new URL(apiUrl.replaceAll(" ", ""));
			con = (HttpURLConnection)conUrl.openConnection();
			
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.connect();

			inStream = con.getInputStream();
			in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
			in.toString();
			
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(in);
/*
 * 
<response>
	<header>
		<resultCode>00</resultCode>
		<resultMsg>NORMAL SERVICE.</resultMsg>
	</header>
	<body>
		<items>
			<item>
				<건축년도>2007</건축년도>
				<년>2015</년>
				<법정동>필운동</법정동>
				<보증금액>65,000</보증금액>
				<아파트>신동아블루아광화문의 꿈</아파트>
				<월>12</월>
				<월세금액>0</월세금액>
				<일>1~10</일>
				<전용면적>111.97</전용면적>
				<지번>254</지번>
				<지역코드>11110</지역코드>
				<층>7</층>
			</item>
			<item>
				<건축년도>2008</건축년도>
				<년>2015</년>
				<법정동>사직동</법정동>
				<보증금액>60,000</보증금액>
				<아파트>광화문풍림스페이스본(9-0)</아파트>
				<월>12</월>
				<월세금액>20</월세금액>
				<일>1~10</일>
				<전용면적>94.28</전용면적>
				<지번>9</지번>
				<지역코드>11110</지역코드>
				<층>2</층>
			</item>
			<item>
				<건축년도>2008</건축년도>
				<년>2015</년>
				<법정동>사직동</법정동>
				<보증금액>30,000</보증금액>
				<아파트>광화문풍림스페이스본(9-0)</아파트>
				<월>12</월>
				<월세금액>170</월세금액>
				<일>1~10</일>
				<전용면적>108.07</전용면적>
				<지번>9</지번>
				<지역코드>11110</지역코드>
				<층>7</층>
			</item>
		</items>
		<numOfRows>10</numOfRows>
		<pageNo>1</pageNo>
		<totalCount>98</totalCount>
	</body>
</response>
 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//	2. api 읽어서 객체로 변환
		
		// 	3. db 저장 
	
	}
}
