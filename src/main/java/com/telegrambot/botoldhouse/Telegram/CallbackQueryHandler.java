package com.telegrambot.botoldhouse.Telegram;

import com.telegrambot.botoldhouse.Service.SeanseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.IOException;

@Component
public class CallbackQueryHandler {

    @Autowired
    SeanseService seanseService;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {



            return null;
        }


}