package com.zlx.pls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    public static void testInsert() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        User zlx = new User("ssdx", "11");
        int insert = userMapper.insert(zlx);
        System.out.println(insert);
//        自动会会写主键
        System.out.println(zlx);


    }

    //查询是QueryWrapper，更改删除添加是updateWrapper,last无脑拼接
    public static void testQueryWrap() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //.默认是and,用lamba表达式加括号，
        userQueryWrapper.between("id", 2, 5)
                .or(i -> i.ge("age", 11)
                        .le("age", 20))
        .like("name","s").last("limit 2,1");
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);


    }

    public static void testDleList() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        //删除集合中的内容
        userMapper.deleteBatchIds(list);
    }

    public static void testDelMap() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "zlx");//and 的效果
        userMapper.deleteByMap(map);

    }

    //ar编程模式，不需要mapper，实体类直接操作数据库，只需要实体类继承
    public static void testAR(){
        //数据库信息在Spring里面，要写这句话
        //必须在ioc容器里AR
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user=new User("ooo","30");
        user.insert();
    }
    public static void testDeleteAR(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user=new User();
        user.deleteById();//主键是序列化类型,可以接收8个基本类型和String，都继承自它，T和Object也行
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.lambda().like(User::getUserName,"s");
        //相当于
//        userQueryWrapper.like("name","s");

        List<User> users = user.selectList(userQueryWrapper);
        System.out.println(users);
    }

    //分页
    public static void testPage(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user=new User();
        //第二页，三条数据
        //limit：第几行数据，一共显示几条
        IPage<User> userIPage = user.selectPage(new Page<User>(2, 3), null);
        System.out.println("当前页数据"+userIPage.getRecords());
        System.out.println("当前页码"+userIPage.getCurrent());
        System.out.println("所有页"+userIPage.getPages());
        System.out.println("总数"+userIPage.getTotal());
        System.out.println("当前页数量"+userIPage.getSize());

    }
    //阻断拦截器,Prohibition of full table deletion
    public static void testDelAll(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user=new User();
        user.delete(null);
    }

    public static void main(String[] args) throws SQLException {
//        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//        ComboPooledDataSource datasource = (ComboPooledDataSource) context.getBean("datasource");
//        System.out.println("================="+datasource);
//        Connection connection =
//                datasource.getConnection();
//        System.out.println("================="+connection);
//        testInsert();

//        testQueryWrap();/
        testPage();
    }

}
