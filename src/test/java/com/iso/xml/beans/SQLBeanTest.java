/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author isoares
 */
public class SQLBeanTest {
    private SQLBean sqlBean;
    
    @Before
    public void setUp(){
        sqlBean = new SQLBean();
    }
    
    @Test
    public void testKeyisNotNull(){
        assertNotNull(sqlBean);
        
        sqlBean.setSqlStatement("test");
        
        assertNotNull(sqlBean.getKey());
    }
    
    @Test
    public void testKey(){
        assertNotNull(sqlBean);
        
        sqlBean.setSqlStatement("test string");
        
        assertNotNull(sqlBean.getKey());
        
        assertEquals("6f8db599de986fab7a21625b7916589c", sqlBean.getKey());
    }
    
    @Test
    public void testKeyWithEmptyString(){
        assertNotNull(sqlBean);
        
        sqlBean.setSqlStatement("     ");
        
        assertNull(sqlBean.getKey());
    }
    
    @Test
    public void testKeyWithNewLine(){
        assertNotNull(sqlBean);
        
        StringBuilder sb = new StringBuilder();
        sb.append("test");
        sb.append("\r\n");
        sb.append("\r");
        sb.append("\n");
        sb.append("string");
        
        sqlBean.setSqlStatement(sb.toString());
        
        assertNotNull(sqlBean.getKey());
        
        assertEquals("6f8db599de986fab7a21625b7916589c", sqlBean.getKey());
    }
    
     @Test
    public void testKeyWithLeadingAndTrailingSpaces(){
        assertNotNull(sqlBean);
        
        sqlBean.setSqlStatement("   test string  ");
        
        assertNotNull(sqlBean.getKey());
        
        assertEquals("6f8db599de986fab7a21625b7916589c", sqlBean.getKey());
    }
}
