import dao.repository.DataSourcePostgreSQL;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import telegrambot.TelegramBot;
import utils.EventTimer;


public class Launcher {
 //   private static Logger log = LoggerFactory.getLogger(Launcher.class);
    private static DataSourcePostgreSQL dataSource = new DataSourcePostgreSQL();
    @SneakyThrows
    public static void main(String[] args){

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(new TelegramBot());
        System.out.println("Bot init complite");
    //    log.info("Bot init complite");

        // Timer init
        EventTimer timer = new EventTimer();
        timer.UpdateTimer();
    }
}
