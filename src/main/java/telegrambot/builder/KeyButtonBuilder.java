package telegrambot.builder;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KeyButtonBuilder {
    private Map keydata = new HashMap();

    @SneakyThrows  // parse property to Map and return
    public Map SetMenu(String link){
        Properties prop = new Properties();
        final ClassLoader loader = getClass().getClassLoader();
            try (InputStream config = loader.getResourceAsStream(link)) {
                prop.load(config);
            }
                keydata.clear();
                keydata = prop;
            return keydata;
    }
}
