/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml.handler;

import com.iso.db.conditions.PostCondition;
import com.iso.db.conditions.PreCondition;
import com.iso.xml.beans.SQLBean;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;

/**
 *
 * @author isoares
 */
public class SqlContentHandler extends AbstractHandler{
    private final static String ROOT_TAG = "sql:statements";
    private final static String SQL_STATEMENT_TAG = "sql:statement";
    private final static String PRECONDITION_TAG = "sql:precondition";
    private final static String POSTCONDITION_TAG = "sql:postcondition";
    private final static String CONDITION_EXPECTED_ATTRIBUTE = "expected";
    private final static String REPEATABLE_ATTRIBUTE = "repeatable";
    private final static String CONTINUE_ON_ERROR_ATTRIBUTE = "continueOnError";
    private final static String CONTINUE_ON_FAILED_CONDITIONS_ATTRIBUTE = "continueOnFailedConditions";
    
    private final StringBuilder tempCondVal = new StringBuilder();
    
    private List<SQLBean> sqlBeans = new ArrayList<SQLBean>();
    private SQLBean sqlBean;
    private PreCondition precondition;
    private PostCondition postcondition;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
      String parentTag = peekTag();
      pushTag(qName);
      
      if(qName.equals(SQL_STATEMENT_TAG) && parentTag.equals(ROOT_TAG)){
          sqlBean = new SQLBean();
          String repeatable = attributes.getValue(REPEATABLE_ATTRIBUTE);
          String continueOnError = attributes.getValue(CONTINUE_ON_ERROR_ATTRIBUTE);
          String continueOnFailedConditions = attributes.getValue(CONTINUE_ON_FAILED_CONDITIONS_ATTRIBUTE);
          sqlBean.setRepeatable(Boolean.valueOf(repeatable));
          sqlBean.setContinueOnError(Boolean.valueOf(continueOnError));
          sqlBean.setContinueOnFailedConditions(Boolean.valueOf(continueOnFailedConditions));
          
          tempVal.setLength(0);
      }
      else if(qName.equals(PRECONDITION_TAG) && parentTag.equals(SQL_STATEMENT_TAG)){
          precondition = new PreCondition();
          String expected = attributes.getValue(CONDITION_EXPECTED_ATTRIBUTE);
          precondition.setExpected(expected);
          
          tempCondVal.setLength(0);
      }
      else if(qName.equals(POSTCONDITION_TAG) && parentTag.equals(SQL_STATEMENT_TAG)){
          postcondition = new PostCondition();
          String expected = attributes.getValue(CONDITION_EXPECTED_ATTRIBUTE);
          postcondition.setExpected(expected);
          
          tempCondVal.setLength(0);
      }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName){
        String tag = peekTag();
        if(!qName.equals(tag)){
            throw new InternalError(); 
        }
        
        popTag();
        
        if(qName.equals(SQL_STATEMENT_TAG) && containsTag(ROOT_TAG)){
            sqlBean.setSqlStatement(tempVal.toString().trim());
            sqlBeans.add(sqlBean);
        }
        else if(qName.equals(SQL_STATEMENT_TAG) && containsTag(SQL_STATEMENT_TAG)){
            precondition.setSqlStatement(tempVal.toString().trim());
            sqlBean.addPrecondition(precondition);
        }
        else if(qName.equals(PRECONDITION_TAG) && containsTag(SQL_STATEMENT_TAG)){
            precondition.setSqlStatement(tempCondVal.toString().trim());
            sqlBean.addPrecondition(precondition);
        }
        else if(qName.equals(POSTCONDITION_TAG) && containsTag(SQL_STATEMENT_TAG)){
            postcondition.setSqlStatement(tempCondVal.toString().trim());
            sqlBean.addPostcondition(postcondition);
        }
    }
    
    @Override
    public void characters(char ch[], int start, int length){
        String tag = peekTag();
        
        if(SQL_STATEMENT_TAG.equals(tag)){
            tempVal.append(ch, start, length);
        }
        else{
            tempCondVal.append(ch, start, length); 
        } 
    }
    
    public List<SQLBean> getSqlBeans(){
        return sqlBeans;
    }
}
