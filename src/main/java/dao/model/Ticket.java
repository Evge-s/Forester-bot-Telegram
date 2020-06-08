package dao.model;

import java.util.Objects;

public class Ticket {
    private String id;
    private String number;
    private String region;
    private String forestUser;
    private String startDate;
    private String finishDate;
    private String forestry;
    private String cuttingType;
    private String ticketStatus;
    private String cuttingStatus;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getForestUser() {
        return forestUser;
    }

    public void setForestUser(String forestUser) {
        this.forestUser = forestUser;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getForestry() {
        return forestry;
    }

    public void setForestry(String forestry) {
        this.forestry = forestry;
    }

    public String getCuttingType() {
        return cuttingType;
    }

    public void setCuttingType(String cuttingType) {
        this.cuttingType = cuttingType;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getCuttingStatus() {
        return cuttingStatus;
    }

    public void setCuttingStatus(String cuttingStatus) {
        this.cuttingStatus = cuttingStatus;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number='" + number + '\'' +
                ", region='" + region + '\'' +
                ", forestUser='" + forestUser + '\'' +
                ", startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", forestry='" + forestry + '\'' +
                ", cuttingType='" + cuttingType + '\'' +
                ", ticketStatus='" + ticketStatus + '\'' +
                ", cuttingStatus='" + cuttingStatus + '\'' +
                '}';
    }

    public String showTicket() {
        return "Квиток номер: " +
                number + "\n" +
                forestUser + "\n" +
                forestry + ", " +
                cuttingType + ",\n" +
                startDate + " - " + finishDate + ".\n" +
                "статус: " + ticketStatus + ", " + "активність: " + cuttingStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(number, ticket.number) &&
                Objects.equals(region, ticket.region) &&
                Objects.equals(forestUser, ticket.forestUser) &&
                Objects.equals(startDate, ticket.startDate) &&
                Objects.equals(finishDate, ticket.finishDate) &&
                Objects.equals(forestry, ticket.forestry) &&
                Objects.equals(cuttingType, ticket.cuttingType) &&
                Objects.equals(ticketStatus, ticket.ticketStatus) &&
                Objects.equals(cuttingStatus, ticket.cuttingStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, region, forestUser, startDate, finishDate, forestry, cuttingType, ticketStatus, cuttingStatus);
    }
}
