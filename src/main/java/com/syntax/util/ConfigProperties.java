/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntax.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author olugbenga.falodun
 */
public enum ConfigProperties {

    INSTANCE;
    private final Properties properties;

    ConfigProperties() {
        properties = new Properties();
        try {

            properties.load(getClass().getClassLoader().getResourceAsStream("ListNavigatorConfig.properties"));
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * 
     * @return Integer
     */
    public int getPageSize() {
        String pageSize = properties.getProperty("config.PAGE_SIZE");

        return pageSize == null ? 5 : Integer.parseInt(pageSize);
    }

    public String getItemsConfig() {
        return properties.getProperty("config.ITEMS");
    }
    
}
