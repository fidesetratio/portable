package com.app.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
	 private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		   logger.debug("Current DataSource is [{}]", DynamicDataSourceContextHolder.getCurrentDb());
	        return DynamicDataSourceContextHolder.getCurrentDb();
	}

}
