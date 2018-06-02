package com.iso;

public class Application {
    
    public static void main(String[] args) {
        Application splDep = new Application();
        splDep.init();
    }
    
    private void init(){
        String config = System.getProperty("config");
    }
}
