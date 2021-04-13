package coolmol.ebookstore.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.text.MessageFormat;
import java.util.List;

@Component
public class MyChannelInterceptor implements ChannelInterceptor{
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
    StompCommand command = accessor.getCommand();

    //用户已经断开连接
    if(StompCommand.DISCONNECT.equals(command)){
      String user = "";
      Principal principal = accessor.getUser();
      if(principal != null){
        user = principal.getName();
      }else{
        user = accessor.getSessionId();
      }

      System.out.println(MessageFormat.format("用户{0}的WebSocket连接已经断开", user));
    }
  }

}
