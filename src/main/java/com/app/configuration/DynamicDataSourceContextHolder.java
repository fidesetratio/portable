package com.app.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.common.DataSourceKey;


public class DynamicDataSourceContextHolder {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    private static int counter = 0;

    /**
     * Maintain variable for every thread, to avoid effect other thread
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceKey.master::name);


    
    
    public static void setCurrentDb(String typeDatabase) {
    	CONTEXT_HOLDER.set(typeDatabase);
    }
    
    
    public static String getCurrentDb() {
        return CONTEXT_HOLDER.get();
    }    
    
    public static void clear() {
    	CONTEXT_HOLDER.remove();
    }
}
