package telegrambot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.controller.KeyHandler;
import telegrambot.controller.UserController;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {
    private PropertyBot property = new PropertyBot();
    private KeyHandler handler = new KeyHandler();
    private UserController userController = new UserController();

    @SneakyThrows
    @Override
    public synchronized void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            if(!userController.isUserStatus()) {
                if (update.getMessage().getText().equals("/start")) {
                    sendMsg(update.getMessage().getChatId().toString(), userController.userGreetings(update.getMessage().getChatId().toString(), update.getMessage().getFrom().getFirstName()));
                }
            }
            if(update.getMessage().getText().equals("/bot") || update.getMessage().getText().equals("/key")) {
                sendKeyboard(update.getMessage().getChatId().toString(), handler.getMenuName());
            }
        } else if(update.hasCallbackQuery()){
            String temp = handler.ikeyBoardHandler(update.getCallbackQuery());
            if(temp != null) {
                sendMsg(update.getCallbackQuery().getMessage().getChatId().toString(), temp);
            } else {
                int msgID = Integer.parseInt(update.getCallbackQuery().getMessage().getMessageId().toString());
                execute(new DeleteMessage(update.getCallbackQuery().getMessage().getChatId().toString(), msgID));
                sendKeyboard(update.getCallbackQuery().getMessage().getChatId().toString(), handler.getMenuName());
            }
        }
    }

    @SneakyThrows
    public synchronized void sendMsg(String chatID, String msg){
        var sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatID);
        sendMessage.setText(msg);
        execute(sendMessage);
    }

    @SneakyThrows
    public synchronized void sendKeyboard(String chatID, String msg){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatID);
        sendMessage.setText(msg);
        sendMessage.setReplyMarkup(handler.getNewKeyBoard());
        execute(sendMessage);
    }

    // Send msg to Forester channel
    public void groupAlert(String text){
        sendMsg(property.getForestChannelID(), text);
    }

    // Alert all users about new data of update
    public void usersAlert(Map<String, Integer> users){
        for(Map.Entry<String, Integer> item: users.entrySet()){
            if(item.getValue() > 0) {
                sendMsg(item.getKey(), "New news: " + item.getValue());
            }
        }
    }

    public String getBotUsername() {
        return property.getBotName();
    }

    public String getBotToken() {
        return property.getToken();
    }
}
