package telegrambot.controller;

import dao.repository.TicketsRepository;
import dao.repository.UserRepository;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import telegrambot.TelegramBot;
import telegrambot.builder.KeyBoardBuilder;
import telegrambot.builder.KeyButtonBuilder;

public class KeyHandler {
    private KeyButtonBuilder mb = new KeyButtonBuilder();
    private String menuName = "Головне меню";
    private String data = "";
    private String navigationLink = "botMainMenu.properties";
    private KeyBoardBuilder inlineKeyBoard = new KeyBoardBuilder(mb.SetMenu(navigationLink));
    private TicketsRepository ticketsRepository = new TicketsRepository();
    private UserController userController = new UserController();
    private UserRepository userRepository = new UserRepository();

    // menu title generation
    public void setMenuName(String navigationLink) {
        switch (navigationLink) {
            case "botMainMenu.properties":
                menuName = "Головне меню";
                break;
            case "botTicketMenu.properties":
                menuName = "Новини";
                break;
        }
    }

    public String getMenuName() {
        return menuName;
    }

    @SneakyThrows
    public synchronized String ikeyBoardHandler(CallbackQuery callbackQuery){
        switch (callbackQuery.getData()) {
            case "getNewTickets":
                return userController.getNewTickets(callbackQuery.getMessage().getChatId().toString());
            case "downloadTickets": // TEMP
                System.out.println(userRepository.fileTransfer("1111", callbackQuery.getMessage().getChatId().toString()));
                break;
            case "botFilterMenu.properties": // TEMP
                Location loc = callbackQuery.getMessage().getLocation();
                System.out.println(loc.toString());
                break;
            default:
            inlineKeyBoard.setInlineKeyboardMarkup(mb.SetMenu(callbackQuery.getData()));
            navigationLink = callbackQuery.getData();
            setMenuName(navigationLink);
            data = null;
        }
        return data;
    }

    public InlineKeyboardMarkup getNewKeyBoard(){
        return inlineKeyBoard.getInlineKeyboardMarkup();
    }
}
