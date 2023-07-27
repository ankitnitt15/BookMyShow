package com.systemdesign.model;

import com.systemdesign.enums.Genre;
import com.systemdesign.enums.Language;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookMyShow {
    private ArrayList<User> users;
    private ArrayList<Theatre> theatres;
    private Map<String, ArrayList<Show>> movieMap;

    public BookMyShow(ArrayList<Theatre> theatres) {
        this.users = new ArrayList<>();
        this.theatres = theatres;
        this.movieMap = new HashMap<>();
        generateMovieMap();
    }

    private void generateMovieMap(){
        for(Theatre theatre : theatres){
            for(Show show: theatre.getShows()){
                if(this.movieMap.containsKey(show.getMovie().getName())){
                    this.movieMap.get(show.getMovie().getName()).add(show);
                }
                else{
                    this.movieMap.put(show.getMovie().getName(), new ArrayList<Show>());
                }
            }
        }
    }

    public ArrayList<Show> searchShows(String movie) throws Exception{
        if(this.movieMap.containsKey(movie)){
            return this.movieMap.get(movie);
        }
        else{
            throw new Exception("No shows found");
        }
    }

    public Ticket bookTicket(Show show, User user, int seats) throws Exception {
        if(user instanceof RegisteredUser){
            return show.bookTicket(seats, (RegisteredUser) user);
        }
        else{
            throw new Exception("Please register first to book tickets");
        }
    }

    public static void main(String[] args) throws ParseException {
        //Initialize all model objects
        //Create object of BookMyShow
        //Use it to book ticket and search shows

        //User
        RegisteredUser john = new RegisteredUser("John");
        RegisteredUser tom = new RegisteredUser("Tom");
        GuestUser harry = new GuestUser("Harry");

        //Movie
        Movie thor = new Movie("Thor", Language.ENGLISH, Genre.ACTION);
        Movie batman = new Movie("Batman", Language.ENGLISH, Genre.ACTION);
        Movie barbie = new Movie("Barbie", Language.ENGLISH, Genre.COMEDY);

        //Theatre
        Theatre imax = new Theatre("IMAX", "Delhi", 100);
        Theatre pvr = new Theatre("PVR", "Noida", 50);

        //Show
        SimpleDateFormat format = new SimpleDateFormat("EEEE MMM, dd yyyy, HH:MM:SS a");
        Show thorShow1 = new Show(format.parse("Friday Jul, 15 2023, 10:00:00 AM"), thor, imax);
        Show thorShow2 = new Show(format.parse("Friday Jul, 15 2023, 11:00:00 AM"), thor, imax);
        Show thorShow3 = new Show(format.parse("Friday Jul, 15 2023, 10:30:00 AM"), thor, pvr);
        Show batmanShow1 = new Show(format.parse("Friday Jul, 15 2023, 11:00:00 AM"), batman, imax);
        Show batmanShow2 = new Show(format.parse("Friday Jul, 15 2023, 11:00:00 AM"), batman, pvr);
        Show barbieShow1 = new Show(format.parse("Friday Jul, 15 2023, 09:00:00 AM"), barbie, imax);
        Show barbieShow2 = new Show(format.parse("Friday Jul, 15 2023, 09:00:00 AM"), barbie, pvr);


        //Bookmyshow
        ArrayList<Theatre> theatres = new ArrayList<>();
        theatres.add(imax);
        theatres.add(pvr);
        BookMyShow bms = new BookMyShow(theatres);

        //Search
        try{
            ArrayList<Show> shows = bms.searchShows("Thor");
            System.out.println("Shows :"+shows);
        } catch(Exception e){
            System.out.println("Something went wrong");
        }

        //Book ticket
        try{
            Ticket ticket1 = bms.bookTicket(thorShow1, john, 10);
            System.out.println(ticket1.getTicketInfo());
        }catch(Exception e){
            System.out.println("Sold out");
        }

        try{
            Ticket ticket2 = bms.bookTicket(thorShow1, john, 10);
            System.out.println(ticket2.getTicketInfo());
        }catch(Exception e){
            System.out.println("Not registered user");
        }


    }
}
