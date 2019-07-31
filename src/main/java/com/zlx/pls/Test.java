package com.zlx.pls;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zlx.pls.entity.User;
import com.zlx.pls.mapper.UserMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void testInsert(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        User zlx = new User("ssx", "11");
        int insert = userMapper.insert(zlx);
        System.out.println(insert);
//        自动会会写主键
        System.out.println(zlx);


    }

        public static void testDleList(){
            ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
            UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
            List<Integer> list=new ArrayList<>();
            list.add(1);
            list.add(3);
            //删除集合中的内容
            userMapper.deleteBatchIds(list);
        }
        public static void testDelMap(){
            ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
            UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
            Map<String,Object> map=new HashMap<>();
            map.put("id",1);
            map.put("name","zlx");//and 的效果
            userMapper.deleteByMap(map);

        }

    public static void main(String[] args) throws SQLException {
//        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//        ComboPooledDataSource datasource = (ComboPooledDataSource) context.getBean("datasource");
//        System.out.println("================="+datasource);
//        Connection connection =
//                datasource.getConnection();
//        System.out.println("================="+connection);
        testInsert();
    }
}
