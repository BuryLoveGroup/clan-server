package com.clan.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by robot on 2017/11/5.
 */
public class DruidDataSourceFactory implements DataSourceFactory {

    private Properties props;

    public void setProperties(Properties properties) {
        this.props = properties;
    }

    public DataSource getDataSource() {
        {
            DruidDataSource dds = new DruidDataSource();
            dds.setDriverClassName(this.props.getProperty("driver"));
            dds.setUrl(this.props.getProperty("url"));
            dds.setUsername(this.props.getProperty("username"));
            dds.setPassword(this.props.getProperty("password"));
            try {
                dds.init();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return dds;
        }
    }
}
