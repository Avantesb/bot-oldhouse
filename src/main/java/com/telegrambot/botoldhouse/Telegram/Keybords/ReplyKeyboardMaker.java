package com.telegrambot.botoldhouse.Telegram.Keybords;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ReplyKeyboardMaker {
    private LocalDate ld = LocalDate.now();
    private String[] monts = new String[]{"", "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август",
            "Сентябрь", "Октябрь", "Ноябрь", "Декабрь",};
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd ММММ yyyy", new Locale("ru"));

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(monts[ld.getMonth().getValue()]));
        row1.add(new KeyboardButton(monts[ld.plusMonths(1).getMonth().getValue()]));
        row1.add(new KeyboardButton(monts[ld.plusMonths(2).getMonth().getValue()]));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}