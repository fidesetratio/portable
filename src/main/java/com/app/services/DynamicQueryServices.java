package com.app.services;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.configuration.DynamicDataSourceContextHolder;
import com.app.model.DynamicQuery;
import com.app.model.DynamicResultQuery;
import com.app.model.SQLColumn;
import com.app.utils.QueryUtils;

@Service
public class DynamicQueryServices {

	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private DashboardServices dashboardServices;
	
	
	public DynamicResultQuery query(String query) {
		DynamicResultQuery result = new DynamicResultQuery();
			
		try {
		
		
					template.query(query, new ResultSetExtractor() {
						@Override
						public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							
							ResultSetMetaData rsmd = rs.getMetaData();
							int columnCount = rsmd.getColumnCount();
				            for(int i = 1 ; i <= columnCount ; i++){
				                SQLColumn column = new SQLColumn();
				                column.setName(rsmd.getColumnName(i));
				                column.setAutoincrement(rsmd.isAutoIncrement(i));
				                column.setType(rsmd.getColumnTypeName(i));
				                column.setTypeCode(rsmd.getColumnType(i));
				                result.getHeaders().add(column);
				            };
				            
				            int total = 0;
				            while(rs.next()) {
				            	 HashMap<String,Object> data = new HashMap<String, Object>();
				  	           
				            	for(SQLColumn column:result.getHeaders()) {
				            		data.put(column.getName(), rs.getObject(column.getName()));
				            	};
				            	
				            	result.getBody().add(data);
				            	total = total + 1;
				            }
				            result.setTotal(total);
				            return columnCount;
						}
						
					});
		}catch(Exception e) {
			result.setStatus(1);
			result.setStatusMessage(e.getMessage());
		}
		finally {
			
		}
		return result;
	}
	
	
	public DynamicResultQuery executeQuery(Integer queryId) {
		HashMap<String,Object> params = new HashMap<String, Object>();
		return this.executeQuery(queryId, params);
	}
	
	
	public DynamicResultQuery executeQuery(Integer queryId, HashMap<String,Object> params) {
	
		DynamicResultQuery d = new DynamicResultQuery();
		try {
			DynamicQuery q = dashboardServices.getQueryById(queryId);
			if(q != null) {
					if(params.size()>0) {
						if(q.getQueryString() != null) {
							if(!StringUtils.isEmpty(q.getQueryString())) {
								String qs = QueryUtils.createQueryBasedParameter(q.getQueryString(), params);
								if(!StringUtils.isEmpty(q.getDatasource_name())) {
									String dsName = q.getDatasource_name();
									DynamicDataSourceContextHolder.setCurrentDb(dsName);
									d = this.query(qs);

								}
									
							}
						}
						
					}else {
						if(q.getQueryString() != null) {
							if(!StringUtils.isEmpty(q.getQueryString())) {
								String qs = QueryUtils.createQueryBasedParameter(q.getQueryString(), params);
								if(!StringUtils.isEmpty(q.getDatasource_name())) {
									String dsName = q.getDatasource_name();
									DynamicDataSourceContextHolder.setCurrentDb(dsName);
									d = this.query(qs);
									
								}
							}
						}
						
					}
			}else {
				d.setStatus(-1);
				d.setStatusMessage("Query is not available");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return d;
	}
}
