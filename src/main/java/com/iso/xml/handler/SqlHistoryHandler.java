/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml.handler;

import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;

/**
 *
 * @author isoares
 */
public class SqlHistoryHandler extends AbstractHandler{
    private final static String ROOT_TAG = "history:statements";
    private final static String HISTORY_STATEMENT_TAG = "history:statement";
    private final static String KEY_ATTRIBUTE = "key";
    
    private final Map<String, String> historyMap = new HashMap<String, String>();
    private String currentKey;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
      String parentTag = peekTag();
      pushTag(qName);
      
      if(parentTag.equals(ROOT_TAG) && qName.equals(HISTORY_STATEMENT_TAG)){
          currentKey = attributes.getValue(KEY_ATTRIBUTE);
          tempVal.setLength(0);
      }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName){
        String tag = peekTag();
        
        if(!qName.equals(tag)){
            throw new InternalError(); 
        }
        
        popTag();
        
        if(qName.equals(HISTORY_STATEMENT_TAG)){
            historyMap.put(currentKey, tempVal.toString().trim());
        }
    }
    
    @Override
    public void characters(char ch[], int start, int length){
        tempVal.append(ch, start, length);
    }

    public Map<String, String> getHistoryMap() {
        return historyMap;
    }
}
