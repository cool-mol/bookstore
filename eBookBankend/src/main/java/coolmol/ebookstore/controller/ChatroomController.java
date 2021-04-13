package coolmol.ebookstore.controller;

import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@RestController
public class ChatroomController {
  public ArrayList<String> users;

  public ChatroomController() {
    this.users = new ArrayList<String>();;
  }

  @MessageExceptionHandler(MessageConversionException.class)
  @MessageMapping(value = "/all") //@注解2
  @SendTo(value = "/topic/all") //@注解3
  public Map<String, String> post(@Payload Map<String, String> message) {
//    message.put("timestamp", Long.toString(System.currentTimeMillis()));
    System.out.println(message);
    if(Objects.equals(message.get("user"), "系统消息")) {
      if(Objects.equals(message.get("message").split(" ")[0], "欢迎")) {
        String s = message.get("message").split(" ")[1];
        users.add(s);
        System.out.println(users);
      }
      System.out.println("222");
      if(Objects.equals(message.get("message").split(" ")[0], "用户")) {
        for (int i = 0; i < users.size(); i++) {
          if (Objects.equals(users.get(i), message.get("message").split(" ")[1])) {
            users.remove(i);
            break;
          }
        }
      }
    }

    String userItems = "";
    for (String user : users) {
      userItems += user;
      userItems += "\n";
    }
    message.put("userItems", userItems);
    System.out.println(message);
    return message;
  }
}
