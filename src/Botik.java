import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import static java.net.http.HttpResponse.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Botik {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        String response = getUpdates();
        String[] messages = UpdatesParser.parseMessages(response);
//        7. Добавить папку Идеа в гитигнор
//        4. Отвечать на сообщения в реальном времени
//        5. Антимат
//        6. Мини-игра на угадывание слова
        Arrays.stream(messages)
                .skip(1) //  TODO скип1 нужен, чтоб убрать хвост, который не является нужным сообщением(нет chatId)
                .map(UpdatesParser::parseChatId)
        // если я напишу 2 сообщения, то мне придет 2 ответа (Stream)
                .distinct()
                .forEach(Botik::sendMessage);

//        for (int i = 1; i < messages.length; i++) {
//            String message = messages[i];
//            String chatId = UpdatesParser.parseChatId(message);
//            boolean isSeenchatId = ;
//            if (isSeenchatId)
//                sendMessage(chatId);
//        }
    }



    /*
    {
        "ok": true,
        "result": {
        "id": 1987821823,
        "is_bot": true,
        "first_name": "Studybot",
        "username": "Rad_study_bot",
        "can_join_groups": true,
        "can_read_all_group_messages": false,
        "supports_inline_queries": false
}
    }

    */


    private static void getMe() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET().uri(new URI("https://api.telegram.org/bot1987821823:AAG5hLjUfmx4-kzV-_GDIxJPuUrvNHjYNsM/getMe"))
                .build();
        HttpResponse<String> response = client.send(request,
                responseInfo -> BodySubscribers.ofString(UTF_8));
        System.out.println(response.body());
    }

    private static void sendMessage(String chatId) {
        try {
            doSendMessage(chatId);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void doSendMessage(String chatId) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("https://api.telegram.org/bot1987821823:AAG5hLjUfmx4-kzV-_GDIxJPuUrvNHjYNsM/sendMessage?chat_id=" +
                        chatId +
                        "&text=" + chatId))
                .build();
        HttpResponse<String> response = client.send(request,
                responseInfo -> BodySubscribers.ofString(UTF_8));
        System.out.println("http://sendMessage > " + response.body());
    }

    private static String getUpdates() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("https://api.telegram.org/bot1987821823:AAG5hLjUfmx4-kzV-_GDIxJPuUrvNHjYNsM/getUpdates"))
                .build();
        HttpResponse<String> response = client.send(request,
                responseInfo -> BodySubscribers.ofString(UTF_8));

        return response.body();
    }


}

