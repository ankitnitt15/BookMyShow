package com.systemdesign.model;

import java.util.Date;

public class Ticket {
    private static int idCounter;
    private int id;
    private String ownerName;
    private Date bookingTime;
    private int noOfSeats;
    private Theatre theatre;

    public Ticket(String ownerName, Date bookingTime, int noOfSeats, Theatre theatre) {
        idCounter++;
        this.id = idCounter;
        this.ownerName = ownerName;
        this.bookingTime = bookingTime;
        this.noOfSeats = noOfSeats;
        this.theatre = theatre;
    }

    public String getTicketInfo(){
        return "Ticket booked for "+this.ownerName+" in theatre "+this.theatre.getName()+" for "+this.noOfSeats+ " seats";
    }

    public void cancelTicket(){
        this.theatre = null;
        this.noOfSeats = 0;
        this.ownerName = null;

        System.out.println("Ticket cancelled successfully");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }


}
