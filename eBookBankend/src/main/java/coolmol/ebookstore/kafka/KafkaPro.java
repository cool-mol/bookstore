package coolmol.ebookstore.kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;

public class KafkaPro {
  public static void main(String[] args) throws Exception {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.setProperty("transactional.id", "my-transactional-id");

    Producer<String, String> producer = null;

    try {
      producer = new KafkaProducer<String, String>(props, new StringSerializer(), new StringSerializer());
      producer.initTransactions();
      producer.beginTransaction();
      for (int i = 0; i < 100; i++) {
        System.out.println("Order"+ i +":Enter Order details(amount,book_id,order_id:)");
        Scanner in = new Scanner(System.in);
        String order_details = in.nextLine();
        producer.send(new ProducerRecord<>("test", Integer.toString(i),  order_details));
        System.out.println("Placing the order successfully!");
      }
      producer.commitTransaction();
    } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
      assert producer != null;
      producer.close();
    } catch (KafkaException e) {
      assert producer != null;
      producer.abortTransaction();
    }
    producer.close();
  }
}
