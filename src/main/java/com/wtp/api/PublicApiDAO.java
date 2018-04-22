package com.wtp.api;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtp.common.AbstractDAO;

@Repository("publicApiDAO")
public class PublicApiDAO extends AbstractDAO {	
	private final String NAMESPACE = "PublicAPI.";
	
	public void insertApartRent(HashMap param) throws Exception {
		master.insert(NAMESPACE  +"insertApartRent", param);
	}
	
	public void insertApartTrade(HashMap param) throws Exception {
		master.insert(NAMESPACE  +"insertApartTrade", param);
	}
	
	public List<HashMap<String, Object>> selectApartRentList(HashMap param) throws Exception {
		return slave.queryForList(NAMESPACE +"selectApartRentList", param);
	}
	
	public List<HashMap<String, Object>> selectApartTradeList(HashMap param) throws Exception {
		return slave.queryForList(NAMESPACE +"selectApartTradeList", param);
	}
	
	public List<HashMap<String, Object>> selectRegionCodeList(HashMap param) throws Exception {
		return slave.queryForList(NAMESPACE +"selectRegionCodeList", param);
	}
}
