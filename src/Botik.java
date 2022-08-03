import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Botik {

//        4. Заменить 244451297 на реальный updateId
//        5. Антимат
//        6. Мини-игра на угадывание слова

  public static void main(String[] args)
      throws URISyntaxException, IOException, InterruptedException {

    System.out.println(TelegramClient.getMessagesUpdate());
    long updateId = 244451297;

    for (; 1 < 2; ) {

      String[] messages = TelegramClient.getNewMessages(updateId);
      replyMessages(messages);
      if (messages.length > 0) {
        updateId++;
        System.out.println("Waiting for updateId = " + updateId);
      } else {
        Thread.sleep(1_000);
      }
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

