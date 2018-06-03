/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.xml.handler;

import java.util.Arrays;
import java.util.Stack;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author isoares
 */
public abstract class AbstractHandler extends DefaultHandler{
    
    protected final Stack<String> tagsStack = new Stack<String>();
    
    protected void pushTag(String tag){
        tagsStack.push(tag);
    }
    
    protected String popTag(){
        return tagsStack.pop();
    }
    
    protected boolean containsTag(String tag){
        if(!tagsStack.isEmpty()){
            return Arrays.asList(tagsStack.toArray()).contains(tag);
        }
        
        return false;
    }
    
    protected String peekTag(){
        if(!tagsStack.isEmpty()){
            return tagsStack.peek();
        }

        return "";
    }
    
}
