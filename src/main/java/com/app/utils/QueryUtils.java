package com.app.utils;

import java.util.HashMap;

public class QueryUtils {
	
		public static String createQueryBasedParameter(String query, HashMap<String,Object> parameters) {
			String q = query;
			if(parameters.size()>0) {
				for(String ks:parameters.keySet()) {
					if(parameters.get(ks) != null) {
						String var="\\$\\{"+ks+"\\}";
						Object o = parameters.get(ks);
						if(o!=null) {
							q = q.replaceAll(var,"'"+o.toString()+"'");
						};
						};
				}
			}
			
			
			return q;
		}

		public static void main(String args[]) {
			HashMap<String,Object> parameters = new HashMap<String, Object>();
			parameters.put("id", "pta");
			String q  = QueryUtils.createQueryBasedParameter("select * from database where id = ${id} ", parameters);
			System.out.println(q);
		}
}
