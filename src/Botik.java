import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Botik {


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        System.out.println(TelegramClient.getMessagesUpdate());
        for (;1 < 2;) {
            replyMessages();
        }


    }
    public static void replyMessages() throws URISyntaxException, IOException, InterruptedException {

        String[] messages = TelegramClient.getMessagesUpdate();
//        4. Отвечать на сообщения в реальном времени
//        5. Антимат
//        6. Мини-игра на угадывание слова
        Arrays.stream(messages)
                .map(TelegramParser::parseChatId)
                // если я напишу 2 сообщения, то мне придет 2 ответа (Stream)
                .distinct()
                .forEach(TelegramClient::sendMessage);

    }
}

