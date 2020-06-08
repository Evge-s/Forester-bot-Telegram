package dao.repository;

import dao.model.Ticket;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketsRepository {
    private static DataSourcePostgreSQL dataSource = new DataSourcePostgreSQL();

    @SneakyThrows
    public Integer getNewTicketsCount(String ticket_id){
        Statement statement = dataSource.getConnect().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM tickets ORDER BY -id");
        Integer count = 0;
        while(resultSet.next()){
            if(ticket_id.equals(resultSet.getString("id"))){
                return count;
            }
            count++;
        }
        return count;
    }

    @SneakyThrows
    public List<Ticket> getTickets(Integer count) {
        List<Ticket> tickets = new ArrayList<>();
        if(count <= 0){
         //   tickets = null;
            return tickets;
        }
        Statement statement = dataSource.getConnect().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT tickets.id, tickets.number, tickets.region, tickets.forest_user, tickets.start_date, " +
                "tickets.finish_date, tickets.forestry, tickets.cutting_type, tickets.ticket_status," +
                " tickets.cutting_status FROM tickets ORDER BY -id");

        for (int i = 0; i < count; i++) {
            resultSet.next();
            Ticket ticket = new Ticket();
            ticket.setId(resultSet.getString("id"));
            ticket.setNumber(resultSet.getString("number"));
            ticket.setRegion(resultSet.getString("region"));
            ticket.setForestUser(resultSet.getString("forest_user"));
            ticket.setStartDate(resultSet.getString("start_date"));
            ticket.setFinishDate(resultSet.getString("finish_date"));
            ticket.setForestry(resultSet.getString("forestry"));
            ticket.setCuttingType(resultSet.getString("cutting_type"));
            ticket.setTicketStatus(resultSet.getString("ticket_status"));
            ticket.setCuttingStatus(resultSet.getString("cutting_status"));

            tickets.add(ticket);
        }
        return tickets;
    }
}
