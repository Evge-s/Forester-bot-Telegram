package telegrambot.controller;

import dao.model.TicketsViewer;
import dao.repository.TicketsRepository;
import dao.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    private boolean userStatus = false;
    private UserRepository userRepository = new UserRepository();
    private TicketsRepository ticketsRepository = new TicketsRepository();
    private TicketsViewer ticketsViewer = new TicketsViewer();

    public boolean isUserStatus() {
        return userStatus;
    }

    public String userGreetings(String userID, String userName){
        if(userRepository.userIdentification(userID)) {
            System.out.println("Login" + userID + "\n" + userName);
            userStatus = true;
            return "Привіт " + userName + "\n/key - викликати панель керування";
        } else {
            System.out.println("unLogin" + userID + "\n" + userName);
            userRepository.userRegistration(userID, userName, ticketsViewer.getLastTicketID(ticketsRepository.getTickets(1)));
            userStatus = true;
            System.out.println("complite" + userID + "\n" + userName);
            return "Радий бачити нового користувача! Привіт " + userName + "\n/key - викликати панель керування";
        }
    }

    // return new tickets data
    public String getNewTickets(String userID){
        String ticketsinfo = "";
        ticketsinfo = ticketsViewer.getTickets(ticketsRepository.getTickets(ticketsRepository.getNewTicketsCount(userRepository.checkLastTicketID(userID))));
        if(!ticketsinfo.equals("no new news")) {
            userRepository.setUserLastTicketID(userID, ticketsViewer.getLastTicketID(ticketsRepository.getTickets(1)));
        }
        return ticketsinfo;
    }

    // count the amount of news for users
    public Map discoverNews(){
        Map usersNotification = new HashMap();
        List<String> userChatID = userRepository.getUserChatIDList();
        for(String item : userChatID) {
            usersNotification.put(item, ticketsRepository.getNewTicketsCount(userRepository.checkLastTicketID(item)));
        }
        return usersNotification;
    }
}
