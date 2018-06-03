/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author isoares
 */
public class SqlHistoryWriter {
    private final static String ROOT_TAG = "history:statements";
    private final static String HISTORY_STATEMENT_TAG = "history:statement";
    private final static String KEY_ATTRIBUTE = "key";
    
    public void writeHistory(File file, Map<String, String> history){
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            
            Document document = docBuilder.newDocument();
            
            Element rootElement = document.createElement(ROOT_TAG);
            document.appendChild(rootElement);
            
            for(Map.Entry<String,String> entry: history.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                
                Element statementElement = document.createElement(HISTORY_STATEMENT_TAG);
                statementElement.setAttribute(KEY_ATTRIBUTE, key);
                statementElement.setTextContent(value);
                
                rootElement.appendChild(statementElement);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            
            transformer.transform(source, result);
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SqlHistoryWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SqlHistoryWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(SqlHistoryWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
