import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodySubscribers;


public class TelegramClient {

  private static void getMe() throws URISyntaxException, IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .GET().uri(new URI("https://api.telegram.org/bot1987821823:AAG5hLjUfmx4-kzV-_GDIxJPuUrvNHjYNsM/getMe"))
        .build();
    HttpResponse<String> response = client.send(request,
        responseInfo -> BodySubscribers.ofString(UTF_8));
    System.out.println(response.body());
  }

  static void sendMessage(String chatId) {
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
        "supports_inline_queries": false } }
        */
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


  static String[] getMessagesUpdate() throws URISyntaxException, IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(new URI("https://api.telegram.org/bot1987821823:AAG5hLjUfmx4-kzV-_GDIxJPuUrvNHjYNsM/getUpdates"))
        .build();
    HttpResponse<String> response = client.send(request,
        responseInfo -> BodySubscribers.ofString(UTF_8));
    String[] messages = TelegramParser.parseMessages(response.body());


    return messages;
  }
}
