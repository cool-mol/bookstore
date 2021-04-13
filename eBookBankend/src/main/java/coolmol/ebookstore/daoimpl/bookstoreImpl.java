package coolmol.ebookstore.daoimpl;

import coolmol.ebookstore.dao.bookstore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class bookstoreImpl extends UnicastRemoteObject implements bookstore {

    public bookstoreImpl() throws RemoteException
    {
    }

    public List<String> getbook(String description) throws RemoteException
    {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASS = "hahaha123456";

        Connection conn = null;
        Statement stmt = null;
        List<String> books = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql1;
            sql1 = "SELECT id, author, description, price FROM book WHERE name='" + description + "'";
            System.out.println(sql1);
            ResultSet rs = stmt.executeQuery(sql1);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                String id  = rs.getString("id");
                String author = rs.getString("author");
                String des = rs.getString("description");
                String price = rs.getString("price");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print("autohr: " + author);
                System.out.print("des: " + des);
                System.out.print("price: " + price);
                System.out.print("\n");
                books.add(id);
                books.add(author);
                books.add(des);
                books.add(price);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

}
