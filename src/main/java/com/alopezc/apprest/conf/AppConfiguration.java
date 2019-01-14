/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alopezc.apprest.conf;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author AlopezCarrillo2500
 * 
 */

@ApplicationPath("/api")
public class AppConfiguration extends ResourceConfig{

    public AppConfiguration() {
        packages("com.alopezc.apprest");
    }
    
}
