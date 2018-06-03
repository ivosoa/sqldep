/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml;

import com.iso.db.conditions.PostCondition;
import com.iso.db.conditions.PreCondition;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isoares
 */
public class SQLBean {
    private List<PreCondition> preconditions = new ArrayList<PreCondition>();
    private List<PostCondition> postconditions = new ArrayList<PostCondition>();
    private String sqlStatement;
    private Boolean repeatable;
    private Boolean continueOnError;
    private Boolean continueOnFailedConditions;
    
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

}
