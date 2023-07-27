package com.systemdesign.model;

import java.util.Date;

public class Show {
    private static int idCounter;
    private int id;
    private Date showTime;
    private int availableSeats;

    private Movie movie;
    private Theatre theatre;

    public Show(Date showTime, Movie movie, Theatre theatre) {
        idCounter++;
        this.id = idCounter;
        this.showTime = showTime;
        this.availableSeats = theatre.getCapacity();
        this.movie = movie;
        this.theatre = theatre;
        theatre.getShows().add(this);
    }

    public int getId() {
        return id;
    }


    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Ticket bookTicket(int seats, RegisteredUser user) throws Exception {
        if(this.availableSeats < seats){
            throw new Exception("Seats not available");
        }
        else{
            Ticket ticket = new Ticket(user.getName(), new Date(), seats, this.theatre);
            this.availableSeats -= seats;
            System.out.println("Ticket booked successfully");

            return ticket;
        }
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", showTime=" + showTime +
                ", availableSeats=" + availableSeats +
                ", movie=" + movie +
                ", theatre=" + theatre +
                '}';
    }
}
