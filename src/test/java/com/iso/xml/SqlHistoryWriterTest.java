/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author isoares
 */
public class SqlHistoryWriterTest {
    
    private SqlHistoryWriter writer;
    private String resourcePath;
    private final String historyTestFileName = "xmlHistoryTest.xml";
    
    @Before
    public void setUp(){
        writer = new SqlHistoryWriter();
        
        resourcePath = getClass().getResource("/").getPath();
        
        File file = new File(resourcePath + historyTestFileName);
        
        if(file.exists()){
            file.delete();
        }
    }
    
    @Test
    public void testWriteXml(){
        File file = new File(resourcePath + historyTestFileName);
        
        Map<String, String> history = new HashMap<String,String>();
        history.put("1", "test");
        
        assertEquals(false, file.exists());
        
        writer.writeHistory(file, history);
        
        assertEquals(true, file.exists());
    }
}
