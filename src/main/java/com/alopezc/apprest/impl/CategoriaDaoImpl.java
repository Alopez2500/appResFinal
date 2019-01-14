/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alopezc.apprest.impl;


import com.alopezc.apprest.dao.CategoriaDao;
import com.alopezc.apprest.dao.SQLCloseable;
import com.alopezc.apprest.model.Categoria;
import com.alopezc.apprest.utilies.BeanCrud;
import com.alopezc.apprest.utilies.BeanPagination;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author AlopezCarrillo2500
 */
public class CategoriaDaoImpl implements CategoriaDao {
    
    private static final Logger LOG = Logger.getLogger(CategoriaDaoImpl.class.getName());
    
    private final DataSource pool;
    
    public CategoriaDaoImpl(DataSource pool) {
        this.pool = pool;
    }

    @Override
    public BeanPagination getPagination(HashMap<String, Object> parameters, Connection conn) throws SQLException {
         BeanPagination bean_pagination = new BeanPagination();
        List<Categoria> list = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = conn.prepareStatement("SELECT COUNT (IDCATEGORIA) AS COUNT  FROM CATEGORIA WHERE NOMBRE LIKE CONCAT ('%',?,'%')");
            pst.setString(1, String.valueOf(parameters.get("FILTER")));
            LOG.info(pst.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                LOG.info(String.valueOf(rs.getInt("COUNT")));
                if (rs.getInt("COUNT") > 0) {
                    bean_pagination.setCount_filter(rs.getInt("COUNT"));
                    pst = conn.prepareStatement("SELECT * FROM CATEGORIA WHERE NOMBRE LIKE CONCAT ('%',?,'%')"
                            + String.valueOf(parameters.get("SQL_ORDER_BY")) + " " + String.valueOf(parameters.get("SQL_PAGINATION")));
                    pst.setString(1, String.valueOf(parameters.get("FILTER")));
                    LOG.info(pst.toString());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Categoria categoria = new Categoria();
                        categoria.setIdcategoria(rs.getInt("IDCATEGORIA"));
                        categoria.setNombre(rs.getString("NOMBRE"));
                        list.add(categoria);
                    }
                }
            }
            bean_pagination.setList(list);
            rs.close();
            pst.close();
        } catch (SQLException e) {
            throw e;
        }
        return bean_pagination;
    }

    @Override
    public BeanCrud getPagination(HashMap<String, Object> parameters) throws SQLException {
        BeanCrud beanCrud = new BeanCrud();
        try (Connection conn = this.pool.getConnection()) {
            beanCrud.setBeanPagination(getPagination(parameters, conn));
        } catch (SQLException e) {
            throw e;
        }
        return beanCrud;
    }

    @Override
    public BeanCrud add(Categoria t, HashMap<String, Object> parameters) throws SQLException {
        BeanCrud bean_crud = new BeanCrud();
        PreparedStatement pst;
        ResultSet rs;
        try (Connection conn = this.pool.getConnection();
                SQLCloseable finish = conn::rollback;) {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("SELECT COUNT (IDCATEGORIA) FROM CATEGORIA WHERE NOMBRE = ? ");
            pst.setString(1, t.getNombre());
            LOG.info(pst.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("COUNT") == 0) {
                    pst = conn.prepareCall("INSERT INTO CATEGORIA (NOMBRE) VALUES(?)");
                    pst.setString(1, t.getNombre());
                    LOG.info(pst.toString());
                    pst.executeUpdate();
                    conn.commit();
                    bean_crud.setMenssage_server("ok");
                    bean_crud.setBeanPagination(getPagination(parameters, conn));
                    
                } else {
                    bean_crud.setMenssage_server("No se registro, ya existe una Categoria con el nombre ingresado");
                }
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return bean_crud;
    }

    @Override
    public BeanCrud update(Categoria t, HashMap<String, Object> parameters) throws SQLException {
         BeanCrud bean_crud = new BeanCrud();
        PreparedStatement pst;
        ResultSet rs;
        try (Connection conn = this.pool.getConnection();
                SQLCloseable finish = conn::rollback;) {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("SELECT COUNT (IDCATEGORIA) FROM CATEGORIA WHERE NOMBRE = ? AND IDCATEGORIA = ?");
            pst.setString(1, t.getNombre());
            pst.setInt(2, t.getIdcategoria());
            LOG.info(pst.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("COUNT") == 0) {
                    pst = conn.prepareCall("UPDATE CATEGORIA SET NOMBRE = ? WHERE IDCATEGORIA=?");
                    pst.setString(1, t.getNombre());
                    pst.setInt(2, t.getIdcategoria());
                    LOG.info(pst.toString());
                    pst.executeUpdate();
                    conn.commit();
                    bean_crud.setMenssage_server("ok");
                    bean_crud.setBeanPagination(getPagination(parameters, conn));
                    
                } else {
                    bean_crud.setMenssage_server("No se modifico, ya existe una Categoria con el nombre ingresado");
                }
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return bean_crud;
    }

    @Override
    public BeanCrud delete(Integer id, HashMap<String, Object> parameters) throws SQLException {
         BeanCrud bean_crud = new BeanCrud();
        try (Connection conn = this.pool.getConnection();
                SQLCloseable finish = conn::rollback;) {
            conn.setAutoCommit(false);
            try (PreparedStatement pst = conn.prepareStatement("delete from categoria where idcategoria = ? ")) {
                pst.setInt(1, id);
                LOG.info(pst.toString());
                pst.executeUpdate();
                conn.commit();
                bean_crud.setMenssage_server("ok");
                bean_crud.setBeanPagination(getPagination(parameters, conn));
            }
        } catch (SQLException e) {
            throw e;
        }
        return bean_crud;
    }

    @Override
    public Categoria getForId(Long id) {
          throw new UnsupportedOperationException();
    }
    
  
    
}
