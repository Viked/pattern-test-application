
package com.example.patternapplication.model.data;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class CloudsDAO extends BaseDaoImpl<Clouds, Integer> {

    protected CloudsDAO(ConnectionSource connectionSource, Class<Clouds> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }



}
