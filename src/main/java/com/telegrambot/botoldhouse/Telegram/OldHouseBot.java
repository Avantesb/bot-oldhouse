package com.telegrambot.botoldhouse.Telegram;


import com.telegrambot.botoldhouse.Service.SeanseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;


public class OldHouseBot extends SpringWebhookBot {

    static Logger userLogger = LoggerFactory.getLogger("telegram_bot");
    public static Logger logger = LoggerFactory.getLogger(OldHouseBot.class);

    private String botPath;
    private String botUsername;
    private String botToken;

    @Autowired
    SeanseService seanseService;

    @Autowired
    MessageHandler messageHandler;
//    @Autowired
    CallbackQueryHandler callbackQueryHandler;

    public OldHouseBot(SetWebhook setWebhook, MessageHandler messageHandler,CallbackQueryHandler callbackQueryHandler) {
        super(setWebhook);
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    public void setBotPath(String botPath) {
        this.botPath = botPath;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }


    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            if (update.hasCallbackQuery()) {

                userLogger.debug("User {}, {}, нажал кнопку {}", update.getCallbackQuery().getMessage().getChatId(),
                        update.getCallbackQuery().getMessage().getChat().getFirstName(),
                        update.getCallbackQuery().getMessage().getReplyMarkup().getKeyboard().get(0).get(0).getText());



                String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
                String[] arrData = update.getCallbackQuery().getData().split(",");
                int page = Integer.parseInt(arrData[0]);
                int month = Integer.parseInt(arrData[1]);

                List<SendMessage> messageList2 = seanseService.getByMontPageble(month, chatId, (page+1));
                for (SendMessage s: messageList2){
                    execute(s);
                }

            } else if (update.getMessage() != null && update.getMessage().getText() != null) {

                for (SendMessage sendMessage : messageHandler.answerMessage(update.getMessage())){
                        execute(sendMessage);
                    }
            }


        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return new SendMessage(update.getMessage().getChatId().toString(),
                    "Попробуйте воспользоваться клавиатурой");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new SendMessage(update.getMessage().getChatId().toString(),
                    "Что то пошло не так");
        }
        return null;
    }


}
