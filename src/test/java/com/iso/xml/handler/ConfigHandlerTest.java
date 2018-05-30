/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author isoares
 */
public class ConfigHandlerTest {
    
    @Test
    public void testNullConfiguration(){
        ConfigHandler handler = new ConfigHandler();

        assertNull(handler.getDbConfig());
    }
    
    @Test
    public void testEmptyConfiguration(){
        
//        private final static String CONFIG_ROOT_TAG = "db:configuration";
//    private final static String CONFIG_URL_TAG = "db:url";
//    private final static String CONFIG_USER_TAG = "db:user";
//    private final static String CONFIG_PASSWORD_TAG = "db:password";
//    private final static String CONFIG_JDBC_DRIVER_TAG = "db:jdbdriver";
        ConfigHandler handler = new ConfigHandler();
        handler.startElement(null, null, "db:configuration", null);
        handler.endElement(null, null, "db:configuration");
        
        assertNotNull(handler.getDbConfig());
        assertNull(handler.getDbConfig().getDbUrl());
        assertNull(handler.getDbConfig().getUser());
        assertNull(handler.getDbConfig().getPassword());
        assertNull(handler.getDbConfig().getJdbcDriver());
    }
    
    @Test
    public void testUrlTagContent(){
        ConfigHandler handler = new ConfigHandler();
        handler.startElement(null, null, "db:configuration", null);
        handler.startElement(null, null, "db:url", null);
        
        String url = "this is the url";
        handler.characters(url.toCharArray(), 0, url.length());
        
        handler.endElement(null, null, "db:url");
        handler.endElement(null, null, "db:configuration");
        
        assertNotNull(handler.getDbConfig());
        assertEquals(url, handler.getDbConfig().getDbUrl());
    }
    
    @Test
    public void testUrlTagContentDuplicate(){
        ConfigHandler handler = new ConfigHandler();
        handler.startElement(null, null, "db:configuration", null);
        handler.startElement(null, null, "db:url", null);
        
        String url = "this is the url";
        handler.characters(url.toCharArray(), 0, url.length());
        
        handler.endElement(null, null, "db:url");
        handler.startElement(null, null, "db:url", null);
        
        String url2 = "this is the url";
        handler.characters(url2.toCharArray(), 0, url.length());
        
        handler.endElement(null, null, "db:url");
        handler.endElement(null, null, "db:configuration");
        
        assertNotNull(handler.getDbConfig());
        assertEquals(url2, handler.getDbConfig().getDbUrl());
    }
}
