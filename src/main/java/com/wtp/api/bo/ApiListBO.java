package com.wtp.api.bo;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wtp.api.PublicApiDAO;

@Service("apiListBO")
public class ApiListBO {
	@Resource(name="publicApiDAO")
	private PublicApiDAO publicApiDAO;
	
	public List<HashMap<String, Object>> selectApartRentList(HashMap param) throws Exception {
		return publicApiDAO.selectApartRentList(param);
	}
}
