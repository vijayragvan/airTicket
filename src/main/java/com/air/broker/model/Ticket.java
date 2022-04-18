package com.air.broker.model;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "source")
    private String source;
    @Column(name = "sourceTerminal")
    private String sourceTerminal;
    @Column(name = "destination")
    private String destination;
    @Column(name = "destinationTerminal")
    private String destinationTerminal;
    @Column(name = "airline")
    private String airline;
    @Column(name = "flightNumber")
    private String flightNumber;
    @Column(name = "departureTime")
    private String departureTime;
    @Column(name = "arrivalTime")
    private String arrivalTime;

    public Ticket(String source, String sourceTerminal, String destination, String destinationTerminal, String airline, String flightNumber, String departureTime, String arrivalTime) {
        this.source = source;
        this.sourceTerminal = sourceTerminal;
        this.destination = destination;
        this.destinationTerminal = destinationTerminal;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Ticket() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceTerminal() {
        return sourceTerminal;
    }

    public void setSourceTerminal(String sourceTerminal) {
        this.sourceTerminal = sourceTerminal;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationTerminal() {
        return destinationTerminal;
    }

    public void setDestinationTerminal(String destinationTerminal) {
        this.destinationTerminal = destinationTerminal;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "ticket{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", sourceTerminal='" + sourceTerminal + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationTerminal='" + destinationTerminal + '\'' +
                ", airline='" + airline + '\'' +
                ", flightNumber=" + flightNumber +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }
}
