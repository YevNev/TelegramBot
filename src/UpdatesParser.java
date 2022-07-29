import java.util.Arrays;

public class UpdatesParser {



    public static void main(String[] args) {
        System.out.println(Arrays.toString(new int[]{1, 2, 3}));
        String[] messages = parseMessages(RESPONSE);
        String arrayAsString = Arrays.toString(messages);
        System.out.println(arrayAsString);

    }

    public static String[] parseMessages(String response) {

        String marker = "#";
        String regex = """
"message":\\{"message_id":\\d+,"from":\\{"id":\\s*(\\d+)""";
        String replacement = marker +"!$0";
        String markedMessages =
                response.replaceAll(regex, replacement);

        String[] maybeMessages = markedMessages.split(marker);

        return maybeMessages;

    }

    public static String parseChatId(String updates) {
        String marker = "#";
        String regex = "\"id\":\\s*(\\d+)";
        String replacement = marker +"$1"+ marker;
        String markedUpdates =
                updates.replaceFirst(regex, replacement);

        String[] splittedText = markedUpdates.split(marker);
        String chatId = splittedText[1];


        return chatId;
//                  0           1          2
//                "text1", "235135142", "text2"



















    }public static final String RESPONSE = "{\"ok\":true,\"result\":[{\"update_id\":244451289,\n" +
            "\"message\":{\"message_id\":9,\"from\":{\"id\":252314731,\"is_bot\":false,\"first_name\":\"\\u0415\\u0432\\u0433\\u0435\\u043d\\u0438\\u0439\",\"username\":\"zovitemenyasemero\",\"language_code\":\"en\"},\"chat\":{\"id\":252314731,\"first_name\":\"\\u0415\\u0432\\u0433\\u0435\\u043d\\u0438\\u0439\",\"username\":\"zovitemenyasemero\",\"type\":\"private\"},\"date\":1658862092,\"text\":\"/start\",\"entities\":[{\"offset\":0,\"length\":6,\"type\":\"bot_command\"}]}},{\"update_id\":244451290,\n" +
            "\"message\":{\"message_id\":11,\"from\":{\"id\":221287654,\"is_bot\":false,\"first_name\":\"Dm\\u0443troK\",\"last_name\":\"\\ud83c\\uddfa\\ud83c\\udde6\",\"username\":\"Pakirava_Datsuma\",\"language_code\":\"uk\"},\"chat\":{\"id\":221287654,\"first_name\":\"Dm\\u0443troK\",\"last_name\":\"\\ud83c\\uddfa\\ud83c\\udde6\",\"username\":\"Pakirava_Datsuma\",\"type\":\"private\"},\"date\":1658863706,\"text\":\"/start\",\"entities\":[{\"offset\":0,\"length\":6,\"type\":\"bot_command\"}]}}]}";
}