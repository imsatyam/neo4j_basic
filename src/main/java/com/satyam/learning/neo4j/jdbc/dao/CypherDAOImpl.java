package com.satyam.learning.neo4j.jdbc.dao;

import com.satyam.learning.neo4j.jdbc.exception.DataAccessException;
import com.satyam.learning.neo4j.jdbc.util.ApplicationUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * Created by Satyam on 3/19/2016.
 */
public class CypherDAOImpl implements CypherDAO{

    public void createNodesAndRelationships(List<String> cypherList) {
        Connection conn = null;
        Statement statement = null;
        try{
            conn = getConnection();
            statement = conn.createStatement();
            statement.execute(StringUtils.join(cypherList, "\n"));
        }catch(SQLException e){
            throw new DataAccessException(e.getMessage(), e);
        }
        finally {
            doCleanup(null, statement, conn);
        }
    }

    public void executeCypher(String cypher) {
        Connection conn = null;
        Statement statement = null;
        try{
            conn = getConnection();
            statement = conn.createStatement();
            statement.execute(cypher);
        }catch(SQLException e){
            throw new DataAccessException(e.getMessage(), e);
        }
        finally {
            doCleanup(null, statement, conn);
        }
    }

    public Iterator<Map<String, Object>> query(String query, Map<String, Object> params) {
        try {
            final Connection conn = getConnection();
            final PreparedStatement statement = conn.prepareStatement(query);
            setParameters(statement, params);
            final ResultSet result = statement.executeQuery();
            return new Iterator<Map<String, Object>>() {

                boolean hasNext = result.next();
                public List<String> columns;

                public boolean hasNext() {
                    return hasNext;
                }

                private List<String> getColumns() throws SQLException {
                    if (columns != null) return columns;
                    ResultSetMetaData metaData = result.getMetaData();
                    int count = metaData.getColumnCount();
                    List<String> cols = new ArrayList<String>(count);
                    for (int i = 1; i <= count; i++) cols.add(metaData.getColumnName(i));
                    return columns = cols;
                }

                public Map<String, Object> next() {
                    try {
                        if (hasNext) {
                            Map<String, Object> map = new LinkedHashMap<String, Object>();
                            for (String col : getColumns()) map.put(col, result.getObject(col));
                            hasNext = result.next();
                            if (!hasNext) {
                                result.close();
                                statement.close();
                            }
                            return map;
                        } else throw new NoSuchElementException();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                public void remove() {
                }
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setParameters(PreparedStatement statement, Map<String, Object> params) throws SQLException {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            int index = Integer.parseInt(entry.getKey());
            statement.setObject(index, entry.getValue());
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(
                ApplicationUtil.getNeo4jUrl(),
                ApplicationUtil.getNeo4jUsername(),
                ApplicationUtil.getNeo4jPassword());
    }

    private void doCleanup(ResultSet rs, Statement statement, Connection connection){
        try {
            if(null != rs){
                rs.close();
            }
            if(null != statement){
                statement.close();
            }
            if(null != connection){
                connection.close();
            }
        }catch(Exception exception){
            System.out.println("Error while doing cleanup...");
        }
    }
}
