package com.felahong.utils;

import org.neo4j.driver.v1.*;

import java.util.List;

public class Neo4JUtil {

    static Driver driver;

    public static Driver getDriver(){
        return GraphDatabase.driver("bolt://oda.com:7687", AuthTokens.basic("neo4j", "123456"));
    }

    /**
     * 获取连接
     * @param uri
     * @param user
     * @param password
     * @return
     */
    public static Driver getDriver(String uri, String user, String password){

        return GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    /**
     * 添加节点
     * @param cythers cyther 语句列表
     */
    public static void addRelationship(List<String> cythers){
        driver = getDriver();
        try (Session session = driver.session())
        {
            try (Transaction tx = session.beginTransaction())
            {
                for(String cyther : cythers){
                    // x 表示一个占位符
                    tx.run(cyther);
                }
                tx.success();
                System.out.println("添加成功");
            }catch (Exception e){
                System.err.println("添加失败");
                e.printStackTrace();
            }
        }
        close();
    }

    public static StatementResult match(String cyther){

        Session session = getDriver().session();
        return session.run(cyther);
    }

    public static void close()
    {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }

}
