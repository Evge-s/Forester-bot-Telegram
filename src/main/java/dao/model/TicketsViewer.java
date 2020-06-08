package dao.model;

import java.util.List;

public class TicketsViewer {

    // get all new ticket for user
    public String getTickets(List<Ticket> tickets){
        if(tickets.size() <= 0){
            return "no new news";
        }
        String ticketinfo = "";
        for (Ticket item : tickets ){
            ticketinfo += item.showTicket() + "\n" + "\n";
        }
        return ticketinfo;
    }

    // get last ticket ID in db for identification new news
    public String getLastTicketID(List<Ticket> tickets){
        if(tickets.size() != 1){
            return "no data";
        }
        return tickets.get(0).getId();
    }
}
