package com.wtp;

import javax.annotation.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AbstractDAO {
	@Resource(name = "masterSqlMapClient")
	protected SqlMapClient master;
	
	@Resource(name = "slaveSqlMapClient")
	protected SqlMapClient slave;
}
