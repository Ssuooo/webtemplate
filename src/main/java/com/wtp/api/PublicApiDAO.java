package com.wtp.api;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtp.common.AbstractDAO;

@Repository("publicApiDAO")
public class PublicApiDAO extends AbstractDAO {	
	private final String NAMESPACE = "PublicAPI.";
	
	public void insertApartDealingCost(HashMap param) throws Exception {
		master.insert(NAMESPACE  +"insertApartDealingCost", param);
	}
	
	public List<HashMap<String, Object>> selectApartDealingCostList(HashMap param) throws Exception {
		return slave.queryForList(NAMESPACE +"selectApartDealingCostList", param);
	}
}
