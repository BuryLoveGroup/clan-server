package com.clan.database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by robot on 2017/11/6.
 */
public class MapperFactory {

    private static SqlSessionFactory sqlSessionFactory;
    private static final String CONFIG_PATH = "mybatis.xml";

    static {
        InputStream stream = null;
        try {
            stream = Resources.getResourceAsStream(CONFIG_PATH);
        } catch (IOException e) {
            //todo 日志记录
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
    }

    public static <T> T createMapper(Class<T> cls) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        T mapper = sqlSession.getMapper(cls);
        return mapper;
    }

}
