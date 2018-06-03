/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml.beans;

import com.iso.db.conditions.PostCondition;
import com.iso.db.conditions.PreCondition;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isoares
 */
public class SQLBean {
    private final static String ALGORITHM = "MD5"; 
    private List<PreCondition> preconditions = new ArrayList<PreCondition>();
    private List<PostCondition> postconditions = new ArrayList<PostCondition>();
    private String sqlStatement;
    private Boolean repeatable;
    private Boolean continueOnError;
    private Boolean continueOnFailedConditions;
    private String key;
    private String cleanedUpStatement;
    
    public void addPrecondition(PreCondition precondition){
        preconditions.add(precondition);
    }
    
    public void addPostcondition(PostCondition postcondition){
        postconditions.add(postcondition);
    }

    public List<PreCondition> getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(List<PreCondition> preconditions) {
        this.preconditions = preconditions;
    }

    public List<PostCondition> getPostconditions() {
        return postconditions;
    }

    public void setPostconditions(List<PostCondition> postconditions) {
        this.postconditions = postconditions;
    }

    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }
    
    public Boolean getRepeatable() {
        return repeatable;
    }

    public void setRepeatable(Boolean repeatable) {
        this.repeatable = repeatable;
    }

    public Boolean getContinueOnError() {
        return continueOnError;
    }

    public void setContinueOnError(Boolean continueOnError) {
        this.continueOnError = continueOnError;
    }

    public Boolean getContinueOnFailedConditions() {
        return continueOnFailedConditions;
    }

    public void setContinueOnFailedConditions(Boolean continueOnFailedConditions) {
        this.continueOnFailedConditions = continueOnFailedConditions;
    }

    public String getKey(){
        if(key != null || getCleanedUpStatement() == null){
            return key;
        }
        
        try {
            
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(getCleanedUpStatement().getBytes());
            
            StringBuilder sb = new StringBuilder();
            for(byte b: md.digest()){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1){
                    sb.append("0");
                }
                sb.append(hex);
            }
            
            key = sb.toString();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SQLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return key;
    }
    
    private String getCleanedUpStatement(){
        if(sqlStatement == null || sqlStatement.isEmpty()){
            return null;
        }
        
        if(cleanedUpStatement != null && !cleanedUpStatement.isEmpty()){
            return cleanedUpStatement;
        }
        
        cleanedUpStatement = sqlStatement.trim()
                .replaceAll("\\r\\n", " ")
                .replaceAll("\\r", " ")
                .replaceAll("\\n", " ")
                .replaceAll("\\s\\s", " ");
        
        if(cleanedUpStatement.isEmpty()){
            return null;
        }
        
        return cleanedUpStatement;
    }
}
