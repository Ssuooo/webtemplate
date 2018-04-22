package com.wtp.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wtp.api.PublicApiDAO;
import com.wtp.api.bo.ApiListBO;

/**
 *  공공 API 를 읽어서 db에 저장한다. 
 *	TODO
 *	데이터 가져오기 - https://www.data.go.kr/
 * 	db 저장
 */

@Controller
@RequestMapping("/api")
public class PublicAPIController {
	private static final String apatmentTradeUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade?";
	private static final String apatmentRentUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent?";

	// serviceKey=XXX
	// LAWD_CD=11110&	// 지역 코드 
	// DEAL_YMD=201512";// 계약월 
	
	@Resource(name="publicApiDAO")
	private PublicApiDAO publicApiDAO;
	
	@Resource(name="apiListBO")
	private ApiListBO apiListBO;

	// cost 미만 매매 목록 가져오기 
	// Model만 사용하기 
	@RequestMapping("/get/trade/cost/{cost}")
	public String getTradeListbyCost(@PathVariable String cost, Model model) throws Exception {
		HashMap param = new HashMap();
		param.put("cost", cost);
		model.addAttribute("resultList", publicApiDAO.selectApartTradeList(param));
		return "/api/apiList";
	}
	
	// 해당 지역코드의 매매가 목록 가져오기 
	// ModelAndView 사용하기
	@RequestMapping("/get/trade/regionCode/{regionCode}")
	public ModelAndView getTradeListbyRegionCode(@PathVariable String regionCode)  throws Exception {//, Model model) throws Exception {
		HashMap param = new HashMap();
		param.put("region_code", regionCode);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("resultList", publicApiDAO.selectApartTradeList(param));
		mav.setViewName("api/apiList");
		return mav;
	}
	
	@RequestMapping("/import")
	public void viewImportList(Model model) throws Exception {
		model.addAttribute("result", "API를 선택해 주세요.");
	}
	
	// get/deposit/30000 : 보증금 30000만원 미만
	
	// get/monthlyRent/30 : 월 30만원 미만

	@RequestMapping(value="/importRent", method=RequestMethod.POST)
	public String importApartRentAPI (@RequestParam(required=true, value="serviceKey") String serviceKey, 
								 @RequestParam(required=false, value="lawdCd") String lawdCd, 
								 @RequestParam(required=false, value="dealYmd") String dealYmd,
								 Model model) throws Exception {
		HttpURLConnection con = null;
		InputStream inStream = null;
		BufferedReader in = null;

		//	1. www.data.go.kr에서 api 읽기
		try {
			// api 호출 
			String apiUrl = apatmentRentUrl.replaceAll(" ", "");
			apiUrl += "serviceKey=" + serviceKey +"&LAWD_CD=" + lawdCd + "&DEAL_YMD=" + dealYmd;
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
			Element responseBody = (Element) document.getRootElement().getChildren("body").get(0);	// rootElm.getChildren("body");
			Element responseItems = (Element) responseBody.getChildren("items").get(0);
			List<Element> itemList = responseItems.getChildren("item");
			
			if (itemList.size() > 0) {
				for (Element item : itemList) {
					HashMap<String, Object> savingMap = new HashMap<String, Object>();
					
					savingMap.put("building_year", item.getChildText("건축년도"));
					savingMap.put("contract_year", item.getChildText("년"));
					savingMap.put("dong", item.getChildText("법정동"));
					savingMap.put("deposit", item.getChildText("보증금액").replaceAll(",", ""));
					savingMap.put("building_name", item.getChildText("아파트"));
					savingMap.put("contract_month", item.getChildText("월"));
					savingMap.put("rent", item.getChildText("월세금액"));
					savingMap.put("contract_date", item.getChildText("일"));
					savingMap.put("private_area", item.getChildText("전용면적"));
					savingMap.put("bunji", item.getChildText("지번"));
					savingMap.put("region_code", item.getChildText("지역코드"));
					savingMap.put("floor", item.getChildText("층"));
					
					publicApiDAO.insertApartRent(savingMap);
				}
			}
			model.addAttribute("result", URLEncoder.encode("아파트 전월세가 API를 "+ itemList.size() + "개 저장", "UTF-8"));
/*	<response>
		<header>
			<resultCode>00</resultCode>
			<resultMsg>NORMAL SERVICE.</resultMsg>
		</header>
		<body>
			<items>
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
			</items>
			<numOfRows>10</numOfRows>
			<pageNo>1</pageNo>
			<totalCount>98</totalCount>
		</body>
	</response>	 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
			if (inStream != null) {
				inStream.close();
			}
			if (in != null) {
				in.close();
			}
		}
		
		return "redirect:/api/import";
	}

	@RequestMapping(value="/importTrade", method=RequestMethod.POST)
	public String importTradeApi (@RequestParam(required=true, value="serviceKey") String serviceKey, 
								 @RequestParam(required=false, value="lawdCd") String lawdCd, 
								 @RequestParam(required=false, value="dealYmd") String dealYmd,
								 Model model) throws Exception {
		HttpURLConnection con = null;
		InputStream inStream = null;
		BufferedReader in = null;
		
		try {
			// api 호출 
			String apiUrl = apatmentTradeUrl.replaceAll(" ", "");
			apiUrl += URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey;
			apiUrl += "&"+ URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(lawdCd, "UTF-8");
			apiUrl += "&"+ URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYmd, "UTF-8");
			URL conUrl = new URL(apiUrl.replaceAll(" ", ""));
			
			con = (HttpURLConnection)conUrl.openConnection();
			
			con.setRequestMethod("GET");
//			con.setDoOutput(true);
//			con.setDoInput(true);
			con.setRequestProperty("Content-type", "application/xml");
//			con.connect();
			System.out.println("ResponseCode:"+ con.getResponseCode() +", " + con.getResponseMessage());
			
			inStream = con.getInputStream();
			in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
			in.toString();
			
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(in);
			Element responseBody = (Element) document.getRootElement().getChildren("body").get(0);	// rootElm.getChildren("body");
			Element responseItems = (Element) responseBody.getChildren("items").get(0);
			List<Element> itemList = responseItems.getChildren("item");
			
/*<item>
  	<거래금액>17,900</거래금액>
	<건축년도>1999</건축년도>
	<년>2018</년>
	<법정동>누상동</법정동>
	<아파트>청호그린빌</아파트>
	<월>3</월>
	<일>21~31</일>
	<전용면적>29.76</전용면적>
	<지번>40</지번>
	<지역코드>11110</지역코드>
	<층>2</층>
</item>	 */
			if (itemList.size() > 0) {
				for (Element item : itemList) {
					HashMap<String, Object> savingMap = new HashMap<String, Object>();
					
					savingMap.put("cost", item.getChildText("거래금액").replaceAll(",", ""));
					savingMap.put("building_year", item.getChildText("건축년도"));
					savingMap.put("contract_year", item.getChildText("년"));
					savingMap.put("dong", item.getChildText("법정동"));
					savingMap.put("building_name", item.getChildText("아파트"));
					savingMap.put("contract_month", item.getChildText("월"));
					savingMap.put("contract_date", item.getChildText("일"));
					savingMap.put("private_area", item.getChildText("전용면적"));
					savingMap.put("bunji", item.getChildText("지번"));
					savingMap.put("region_code", item.getChildText("지역코드"));
					savingMap.put("floor", item.getChildText("층"));
					
					publicApiDAO.insertApartTrade(savingMap);
				}
			} else {
				model.addAttribute("result", "아파트 매매가 API 결과가 없습니다.");		// URLEncoder.encode("아파트 매매가 API 결과가 없습니다.", "UTF-8"));
			}
			model.addAttribute("result", "아파트 매매가 API를 "+ itemList.size() + "개 저장");	// URLEncoder.encode("아파트 매매가 API를 "+ itemList.size() + "개 저장", "UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", URLEncoder.encode("아파트 매매가 API 저장에 실패했습니다. => " + con.getResponseMessage(), "UTF-8"));
		} finally {
			if (con != null) {
				con.disconnect();
			}
			if (inStream != null) {
				inStream.close();
			}
			if (in != null) {
				in.close();
			}
		}
		
		return "redirect:/api/import";
	}
	

}
