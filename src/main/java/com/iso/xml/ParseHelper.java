/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml;

import com.iso.db.beans.DBConfig;
import com.iso.xml.handler.ConfigHandler;
import java.io.File;
import java.io.IOException;
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
    
}
