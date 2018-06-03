/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml.handler;

import com.iso.db.beans.DBConfig;
import org.xml.sax.Attributes;


/**
 *
 * @author isoares
 */
public class ConfigHandler extends AbstractHandler{
    
    private final static String ROOT_TAG = "db:configuration";
    private final static String DB_URL_TAG = "db:url";
    private final static String DB_USER_TAG = "db:user";
    private final static String DB_PASSWORD_TAG = "db:password";
    private final static String DB_JDBC_DRIVER_TAG = "db:jdbdriver";
    
    private DBConfig dbConfig;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
      String parentTag = peekTag();
      pushTag(qName);
      
      if(qName.equals(ROOT_TAG) && parentTag.isEmpty()){
          dbConfig = new DBConfig();
      }
      else if(parentTag.equals(ROOT_TAG)){
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
        
        switch (qName) {
            case DB_URL_TAG:
                dbConfig.setDbUrl(tempVal.toString().trim());
                break;
            case DB_USER_TAG:
                dbConfig.setUser(tempVal.toString().trim());
                break;
            case DB_PASSWORD_TAG:
                dbConfig.setPassword(tempVal.toString().trim());
                break;
            case DB_JDBC_DRIVER_TAG:
                dbConfig.setJdbcDriver(tempVal.toString().trim());
                break;
            default:
                break;
        }
    }
    
    @Override
    public void characters(char ch[], int start, int length){
        tempVal.append(ch, start, length);
    }
    
    public DBConfig getDbConfig() {
        return dbConfig;
    }
}
