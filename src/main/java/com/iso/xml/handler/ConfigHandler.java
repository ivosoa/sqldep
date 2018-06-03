/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml.handler;

import com.iso.db.beans.DBConfig;
import java.util.Stack;
import org.xml.sax.Attributes;


/**
 *
 * @author isoares
 */
public class ConfigHandler extends AbstractHandler{
    
    private final static String CONFIG_ROOT_TAG = "db:configuration";
    private final static String CONFIG_URL_TAG = "db:url";
    private final static String CONFIG_USER_TAG = "db:user";
    private final static String CONFIG_PASSWORD_TAG = "db:password";
    private final static String CONFIG_JDBC_DRIVER_TAG = "db:jdbdriver";
    
    private DBConfig dbConfig;
    private final StringBuilder tempVal = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
      String parentTag = peekTag();
      pushTag(qName);
      
      if(qName.equals(CONFIG_ROOT_TAG) && parentTag.isEmpty()){
          dbConfig = new DBConfig();
      }
      else if(parentTag.equals(CONFIG_ROOT_TAG)){
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
            case CONFIG_URL_TAG:
                dbConfig.setDbUrl(tempVal.toString().trim());
                break;
            case CONFIG_USER_TAG:
                dbConfig.setUser(tempVal.toString().trim());
                break;
            case CONFIG_PASSWORD_TAG:
                dbConfig.setPassword(tempVal.toString().trim());
                break;
            case CONFIG_JDBC_DRIVER_TAG:
                dbConfig.setJdbcDriver(tempVal.toString().trim());
                break;
            default:
                break;
        }
    }
    
    @Override
    public void characters(char ch[], int start, int length){
        String tag = peekTag();
        
        tempVal.append(ch, start, length);
    }
    
    public DBConfig getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }
}
