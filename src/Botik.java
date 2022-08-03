import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Botik {

//        4. Отвечать на сообщения в реальном времени
//        5. Антимат
//        6. Мини-игра на угадывание слова

  public static void main(String[] args)
      throws URISyntaxException, IOException, InterruptedException {

    System.out.println(TelegramClient.getMessagesUpdate());
    for (; 1 < 2; ) {

      String[] messages = TelegramClient.getMessagesUpdate();
      replyMessages(messages);
    }


  }

  public static void replyMessages(String[] messages)
      throws URISyntaxException, IOException, InterruptedException {

    Arrays.stream(messages)
        .map(TelegramParser::parseChatId)
        // если я напишу 2 сообщения, то мне придет 2 ответа (Stream)
        .distinct()
        .forEach(TelegramClient::sendMessage);

  }
}

