package utils;

import dao.repository.UserRepository;
import telegrambot.TelegramBot;
import telegrambot.controller.UserController;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class EventTimer {
    TelegramBot telegramBot = new TelegramBot();
    UserController userController = new UserController();

    public void UpdateTimer() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Task performed on " + new Date());
                telegramBot.usersAlert(userController.discoverNews());
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000000L;
        long period = 1000L *  60L *  60L *  24L; //  set delay for timer (24 hours like this set)
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }
}
