/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml;

import com.iso.db.beans.DBConfig;
import java.io.File;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author isoares
 */
public class ParseHelperTest {
    
    private ParseHelper pHelper;
    
    @Before
    public void setUp(){
        pHelper = new ParseHelper();
    }
    
    @Test
    public void testParseConfig(){
        File configFile = new File(getClass().getResource("dbconfig.xml").getPath());
        
        DBConfig config = pHelper.parseConfig(configFile);
        
        assertEquals("urltest", config.getDbUrl());
        assertEquals("usertest", config.getUser());
        assertEquals("passwordtest", config.getPassword());
        assertEquals("jdbcDriverTest", config.getJdbcDriver());
    }
    
    @Test
    public void testParseConfigNull(){
        File configFile = null;
        
        DBConfig config = pHelper.parseConfig(configFile);
        assertNull(config);
    }
    
    @Test
    public void testParseEmptyConfig(){
        File configFile = new File(getClass().getResource("dbconfigempty.xml").getPath());
        
        DBConfig config = pHelper.parseConfig(configFile);
        
        assertNotNull(config);
        
        assertNull(config.getDbUrl());
        assertNull(config.getUser());
        assertNull(config.getPassword());
        assertNull(config.getJdbcDriver());
    }
    
    @Test
    public void testParseEmptyEntries(){
        File configFile = new File(getClass().getResource("dbconfigemptyentries.xml").getPath());
        
        DBConfig config = pHelper.parseConfig(configFile);
        
        assertNotNull(config);
        
        assertEquals("", config.getDbUrl());
        assertEquals("", config.getUser());
        assertEquals("", config.getPassword());
        assertEquals("", config.getJdbcDriver());
    }
    
    @Test
    public void testSQLStatements(){
        File file = new File(getClass().getResource("sql.xml").getPath());
        
        List<SQLBean> sqlBeans = pHelper.parseSqlStatements(file);
        
        assertNotNull(sqlBeans);
        
        for(SQLBean sqlBean: sqlBeans){
            assertNotNull(sqlBean);
            assertNotNull(sqlBean.getSqlStatement());
            assertEquals(1, sqlBean.getPreconditions().size());
            assertEquals(1, sqlBean.getPostconditions().size());
            assertEquals(true, sqlBean.getRepeatable());
        }
    }
    
    @Test
    public void testSQLHistory(){
        File file = new File(getClass().getResource("executionhistory.xml").getPath());
        
        Map<String, String> history = pHelper.parseSqlHistory(file);
        
        assertNotNull(history);
        
        Map.Entry<String, String> entry = history.entrySet().iterator().next();
        
        String key = entry.getKey();
        
        assertEquals("testkey", key);
    }
}
