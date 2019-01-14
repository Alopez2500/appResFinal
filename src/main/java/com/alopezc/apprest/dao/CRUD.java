/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alopezc.apprest.dao;


import com.alopezc.apprest.utilies.BeanCrud;
import com.alopezc.apprest.utilies.BeanPagination;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author AlopezCarrillo2500
 */
public interface CRUD<T> {
    BeanPagination getPagination(HashMap <String,Object>  parameters, Connection conn) throws SQLException;
    
    BeanCrud getPagination(HashMap <String,Object>  parameters) throws SQLException;
    
    BeanCrud add(T t,HashMap <String,Object>  parameters)throws SQLException;
    BeanCrud update(T t,HashMap <String,Object>  parameters)throws SQLException;
    BeanCrud delete(Integer id, HashMap <String,Object> parameters)throws SQLException;
    
    T getForId(Long id);
   
}
