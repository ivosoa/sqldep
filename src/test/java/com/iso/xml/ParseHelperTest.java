/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml;

import com.iso.db.beans.DBConfig;
import java.io.File;
import static org.junit.Assert.assertEquals;
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
}
