package dao.repository;

import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static DataSourcePostgreSQL dataSource = new DataSourcePostgreSQL();

    @SneakyThrows
    public boolean userIdentification(String userID){
        Statement statement = dataSource.getConnect().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT chat_id FROM telegram_users");
        String user_id;
        while(resultSet.next()){
            user_id = resultSet.getString("chat_id");
            if(userID.equals(user_id)){
                return true;
            }
        }
        return false;
    }

    @SneakyThrows
    public void userRegistration(String userID, String userName, String ticketID){
        Connection connection = dataSource.getConnect();
            PreparedStatement t_users = connection.prepareStatement("INSERT INTO telegram_users(chat_id, name, last_ticket_id) values(?, ?, ?)");
            t_users.setString(1, userID);
            t_users.setString(2, userName);
            t_users.setString(3, ticketID);
            t_users.executeUpdate();
    }

    @SneakyThrows
    public String checkLastTicketID(String userID){
        Statement statement = dataSource.getConnect().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT last_ticket_id, chat_id FROM telegram_users");
        String user_id = "";
        String ticket_id = "";
        while(resultSet.next()){
            user_id = resultSet.getString("chat_id");
            if(userID.equals(user_id)){
                ticket_id = resultSet.getString("last_ticket_id");
            }
        }
        return ticket_id;
    }

    @SneakyThrows
    public void setUserLastTicketID(String userID, String ticketID){
        Connection connection = dataSource.getConnect();
        PreparedStatement t_users = connection.prepareStatement("update telegram_users set last_ticket_id=? WHERE chat_id=?");
        t_users.setString(1, ticketID);
        t_users.setString(2, userID);
        t_users.executeUpdate();
        System.out.println("Last ticket ID changed");
    }

    @SneakyThrows
    public List<String> getUserChatIDList(){
        Statement statement = dataSource.getConnect().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT chat_id FROM telegram_users");
        List<String> userList = new ArrayList<>();
        while(resultSet.next()){
           userList.add(resultSet.getString("chat_id"));
        }
        return userList;
    }

    @SneakyThrows
    public String fileTransfer(String data, String userID) {
        String fname = "info" + userID + ".txt";
        System.out.println(fname);
        FileOutputStream fos=new FileOutputStream(fname);
            // перевод строки в байты
            byte[] buffer = data.getBytes();
            fos.write(buffer, 0, buffer.length);

        System.out.println("The file has been written");
        return fname;
    }
}
