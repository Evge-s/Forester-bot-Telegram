package telegrambot.builder;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KeyBoardBuilder {
    private InlineKeyboardMarkup inlineKeyboardMarkup;

    public KeyBoardBuilder(Map<String,String> menu){
        setInlineKeyboardMarkup(menu);
    }

    public void setInlineKeyboardMarkup(Map<String,String> menu){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        // creates rows of key buttons
        var rowList1 = new ArrayList<InlineKeyboardButton>();
        var rowList2 = new ArrayList<InlineKeyboardButton>();

        for(Map.Entry<String, String> item : menu.entrySet()){
        // add buttons to rows
            if(rowList1.size() < 2) {
                rowList1.add(new InlineKeyboardButton(item.getKey()).setCallbackData(item.getValue()));
            }else{
                rowList2.add(new InlineKeyboardButton(item.getKey()).setCallbackData(item.getValue()));
            }
        }
        // add rows to keyboard
        if(rowList2.size() != 0){
            inlineKeyboardMarkup.setKeyboard(List.of(rowList1, rowList2));
        }else {
            inlineKeyboardMarkup.setKeyboard(Collections.singletonList(rowList1));
        }
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        return inlineKeyboardMarkup;
    }
}

