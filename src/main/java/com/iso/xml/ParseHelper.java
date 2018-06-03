/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml;

import com.iso.db.beans.DBConfig;
import com.iso.xml.handler.ConfigHandler;
import com.iso.xml.handler.SqlHistoryHandler;
import com.iso.xml.handler.SqlContentHandler;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author isoares
 */
public class ParseHelper {
    
    public DBConfig parseConfig(File configFile){
        
        if(configFile == null){
            return null;
        }
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            
            ConfigHandler configHandler = new ConfigHandler();
            parser.parse(configFile, configHandler);
            
            
            return configHandler.getDbConfig();
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ParseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ParseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public List<SQLBean> parseSqlStatements(File file){
        if(file == null){
            return null;
        }
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            
            SqlContentHandler handler = new SqlContentHandler();
            parser.parse(file, handler);
            
            
            return handler.getSqlBeans();
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ParseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ParseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    } 
    
    public Map<String, String> parseSqlHistory(File file){
        if(file == null){
            return null;
        }
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            
            SqlHistoryHandler handler = new SqlHistoryHandler();
            parser.parse(file, handler);
            
            
            return handler.getHistoryMap();
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ParseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ParseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    } 
}
