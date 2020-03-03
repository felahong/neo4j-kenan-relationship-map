package com.felahong.neo4j.test;

import com.felahong.utils.Neo4JUtil;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Path;

import java.util.List;

public class Test4All {

    public static void main(String[] args) {

        StatementResult result = Neo4JUtil.match("MATCH p=(n:People)-[]-() RETURN p");
        while (result.hasNext()) {
            Record record = result.next();
//            System.out.println(record);   // Record<{p: path[(107)<-[3209:前下属]-(1088)]}>

            List<Value> values = record.values();
//            values.forEach(System.out::println);    // path[(107)<-[3209:前下属]-(1088)]

            values.forEach( v->{
                Path p = v.asPath();
                p.nodes().forEach(n -> {
//                    System.out.println("label: " + n.labels());
//                    System.out.println("size: " + n.size());    // 2，分别是image 和name

                    System.out.println("n.id: " + n.id());
                    n.keys().forEach(k->{
                        System.out.println("k: " + k + "; n.get(k): " + n.get(k));
                    });

                });

                p.relationships().forEach(r->{
                    System.out.println("start to end: " + r.startNodeId() + " -> " + r.endNodeId());
                    System.out.println(r.type());
                });

                System.out.println("---");

            });

        }
    }

}
