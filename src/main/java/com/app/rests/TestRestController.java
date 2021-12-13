package com.app.rests;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.DataSourceKey;
import com.app.configuration.DynamicDataSourceContextHolder;
import com.app.model.DatabaseDatasource;
import com.app.model.DynamicQuery;
import com.app.model.DynamicResultQuery;
import com.app.services.DashboardServices;
import com.app.services.DynamicQueryServices;

@RestController
public class TestRestController {
	
	@Autowired
	private DashboardServices dashboardServices;
	
	@Autowired
	private DynamicQueryServices dynamicQueryService;
	
	
	
	@GetMapping("/test")
	public List<DatabaseDatasource> test(@RequestParam(name="datasource",defaultValue="master") String datasource) {
		System.out.println("dataSource:"+DataSourceKey.slaveAlpha.name());
		DynamicDataSourceContextHolder.setCurrentDb(datasource);
		return dashboardServices.getListDatasources();
	}

	@GetMapping("/testdq")
	public DynamicQuery testdq(@RequestParam(name="queryId",defaultValue="1") Integer queryId) {
		return dashboardServices.getQueryById(queryId);
	}

	
	@GetMapping("/test2")
	public DynamicResultQuery test2(@RequestParam(name="datasource",defaultValue="master") String datasource) {
		System.out.println("dataSource:"+DataSourceKey.slaveAlpha.name());
		DynamicDataSourceContextHolder.setCurrentDb(datasource);
		return dynamicQueryService.query("select * from lst_users");
	}
	
	
	@GetMapping("/test3")
	public DynamicResultQuery test3(@RequestParam(name="queryId",defaultValue="1") Integer queryId) {
		return dynamicQueryService.executeQuery(queryId);
	}
	

}
