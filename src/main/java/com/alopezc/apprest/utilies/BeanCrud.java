/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alopezc.apprest.utilies;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author AlopezCarrillo2500
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeanCrud {
    private String menssage_server;
    private BeanPagination beanPagination;

    public BeanCrud() {
    }

    public BeanCrud(String menssage_server, BeanPagination beanPagination) {
        this.menssage_server = menssage_server;
        this.beanPagination = beanPagination;
    }

    public String getMenssage_server() {
        return menssage_server;
    }

    public void setMenssage_server(String menssage_server) {
        this.menssage_server = menssage_server;
    }

    public BeanPagination getBeanPagination() {
        return beanPagination;
    }

    public void setBeanPagination(BeanPagination beanPagination) {
        this.beanPagination = beanPagination;
    }
    
    
}
