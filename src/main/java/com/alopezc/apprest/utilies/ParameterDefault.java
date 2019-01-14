/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alopezc.apprest.utilies;

import java.util.HashMap;

/**
 *
 * @author AlopezCarrillo2500
 */
public class ParameterDefault {

    public static HashMap<String, Object> getParameterCategoria() {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("FILTER", "");
        parameters.put("SQL_ORDER_BY", " ORDER BY NOMBRE ASC");
        parameters.put("SQL_PAGINATION", "LIMIT 10 OFFSET 0");
        return parameters;
    }
}
