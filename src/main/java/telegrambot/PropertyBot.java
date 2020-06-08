package telegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyBot {
    private static Logger log = LoggerFactory.getLogger(PropertyBot.class);
    Properties properties = new Properties();
    {
        final ClassLoader loader = getClass().getClassLoader();
        try {
            try (InputStream config = loader.getResourceAsStream("prop.properties")) {
                properties.load(config);
            }
        } catch (IOException exception) {
            log.error(exception.getMessage(), exception);
        }
    }

    public String getToken() {
        return properties.getProperty("token");
    }
    public String getBotName() {
        return properties.getProperty("username");
    }
    public String getForestChannelID() {
        return properties.getProperty("forestchannel");
    }
}