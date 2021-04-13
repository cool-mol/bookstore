package coolmol.ebookstore.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.sql.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class KafkaCon {
  public static void main(String[] args) throws Exception {
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    String USER = "root";
    String PASS = "hahaha123456";

    Properties props = new Properties();
    props.setProperty("bootstrap.servers", "localhost:9092");
    props.setProperty("group.id", "test");
    props.setProperty("enable.auto.commit", "true");
    props.setProperty("auto.commit.interval.ms", "1000");
    props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

    Connection conn = null;
    Statement stmt = null;
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Arrays.asList("test"));
    PreparedStatement preparedStatement = null;

    while (true) {
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
      for (ConsumerRecord<String, String> record : records) {
        Scanner s1 = new Scanner(record.key());
        int id = s1.nextInt();
        Scanner s2 = new Scanner(record.value());
        int amount = s2.nextInt();
        int book_id = s2.nextInt();
        int order_id = s2.nextInt();

        try {
          Class.forName(JDBC_DRIVER);
          // 打开链接
          System.out.println("连接数据库...");
          conn = DriverManager.getConnection(DB_URL,USER,PASS);
          // 执行查询
          System.out.println(" 实例化Statement对象...");
          stmt = conn.createStatement();
          String sql1;
          sql1 = ("insert into `order_item` values(" + id + "," + amount + "," + book_id + "," + order_id  + ")");
          System.out.println(sql1);
          // 完成后关闭
          stmt.execute(sql1);
          conn.commit();
          stmt.close();
          conn.close();
          System.out.println("Successfully writing to the database");
        }  catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
