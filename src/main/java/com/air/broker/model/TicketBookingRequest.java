package com.air.broker.model;

import javax.persistence.*;

@Entity
@Table(name = "ticket_booking_request")
public class TicketBookingRequest {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "ticket_id")
    private String ticketId;
    @Column(name = "ticket_count")
    private String ticketCount;
    @Column(name = "is_booked")
    private Boolean isBooked;
    @Column(name = "is_paid")
    private Boolean isPaid;
    @Column(name = "amount")
    private String amount;

    public TicketBookingRequest(String userId, String ticketId, String ticketCount, Boolean isBooked, Boolean isPaid, String amount) {
        this.userId = userId;
        this.ticketId = ticketId;
        this.ticketCount = ticketCount;
        this.isBooked = isBooked;
        this.isPaid = isPaid;
        this.amount = amount;
    }

    public TicketBookingRequest() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(String ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TicketBookingRequest{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", ticketId='" + ticketId + '\'' +
                ", ticketCount='" + ticketCount + '\'' +
                ", isBooked='" + isBooked + '\'' +
                ", isPaid='" + isPaid + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
