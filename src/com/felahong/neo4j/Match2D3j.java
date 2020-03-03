package com.felahong.neo4j;

import com.felahong.utils.Neo4JUtil;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;

import java.io.FileOutputStream;
import java.util.List;

public class Match2D3j {

    public static void main(String[] args) {
        Match2D3j d3j = new Match2D3j();
        d3j.gernerateJsonFile();
    }

    // 界面传回操作请求，拼成Match语句查库，查库结果拼成json格式写json文件
    public void gernerateJsonFile() {

        // 获取图路径
        StatementResult result = Neo4JUtil.match("MATCH p=(n:Kenan)-[]-() RETURN p");

        // 需要拼成{"nodes":[{"image": "kenan.png", "name":"柯南", "id":0}, {...}]} 的格式
        StringBuffer nodes = new StringBuffer();
        // 需要拼成{"links":[{"source": 0, "target":2, "type":"情侣"}, {...}]} 的格式
        StringBuffer links = new StringBuffer();

        nodes.append("\"nodes\":[");    // 构造D3j 要求的格式
        links.append("\"links\":[");

        while (result.hasNext()) {
            Record record = result.next();
//            System.out.println(record);
            List<Value> list = record.values();
            for(Value v : list)     // 每一个value 都是一个path
            {
                Path p = v.asPath();

                // 遍历路径下所有节点
                for(Node n : p.nodes()){
//                    System.out.println(n.labels());
                    nodes.append("{");      // 构造D3j 要求的格式
//                    System.out.println(n.size());
                    int num = 0;
                    for(String k : n.keys()){   // 获取每个属性

//                        System.out.println(k+"-"+n.get(k));
                        nodes.append("\"" + k + "\":" + n.get(k)+",");  // 属性 + 属性值
                        num ++ ;
                        // 手动添加id
                        if(num == n.size()) {
                            nodes.append("\"id\":" + n.id());
                        }
                    }

                    nodes.append("},");     // 拼上大括号和逗号，继续循环，添加其它节点
                }
//                System.out.println(nodes.toString());
                // 截掉逗号
                nodes = new StringBuffer(nodes.toString().substring(0, nodes.toString().length()-1));
//                System.out.println(nodes.toString());


                // 遍历路径下所有关系
                for(Relationship r : p.relationships()) {
//                    System.out.println(n.labels());
                    links.append("{");
//                    System.out.println(r);
                    int num = 0;
                    links.append("\"source\":" + r.startNodeId() + "," + "\"target\":" + r.endNodeId());
                    links.append(",\"type\":\"" + r.type() + "\"");
                    links.append("},");
                }
                // 截掉逗号
                links=new StringBuffer(links.toString().substring(0, links.toString().length()-1));

            }
            // 一个result 之后，加一个逗号
            nodes.append(",");
            links.append(",");

        }

        // 截掉逗号
        nodes=new StringBuffer(nodes.toString().substring(0, nodes.toString().length()-1));
        links=new StringBuffer(links.toString().substring(0, links.toString().length()-1));

        nodes.append("]");
        links.append("]");
//        System.out.println(nodes.toString());
//        System.out.println(links.toString());
        String resultJson = "{" + nodes + "," + links + "}";
        System.out.println(resultJson);
        System.out.println(nodes.toString());

        try {
            FileOutputStream fos = new FileOutputStream("web/Neo4jSon.json");
            fos.write(resultJson.getBytes());
            fos.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
