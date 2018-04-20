package com.wtp.common;

import javax.annotation.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AbstractDAO {
	@Resource(name = "masterSqlMapClient")
	protected SqlMapClient master;
	
	@Resource(name = "slaveSqlMapClient")
	protected SqlMapClient slave;
}
