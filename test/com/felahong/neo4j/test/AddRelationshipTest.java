package com.felahong.neo4j.test;

import com.felahong.utils.Neo4JUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddRelationshipTest {

    public static void main(String[] args) throws Exception{
        String path = "resources/kenan_relationship_data.txt";

        List<String> lines = getData(path);
//        lines.forEach(System.out::println);
        Neo4JUtil.addRelationship(lines);

    }

    private static List<String> getData(String path) throws Exception {
        BufferedReader reader;
        List<String> lines = new ArrayList<>();

        // 获取数据
        InputStreamReader read = new InputStreamReader(new FileInputStream(path));
        reader = new BufferedReader(read);
        String line;
        while((line = reader.readLine()) != null){
            if(line.trim().length() > 0){
//                System.out.println(line);
                lines.add(line);
            }

        }
        return lines;
    }

}
