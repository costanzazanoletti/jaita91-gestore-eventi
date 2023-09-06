package org.learning;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jdk.jshell.execution.Util;

public class Event {
  // ATTRIBUTI
  private String title;
  private LocalDate date;
  private int totalSeats;

  private int bookedSeats;

  // COSTRUTTORI

  public Event(String title, LocalDate date, int totalSeats) throws InvalidEventParametersException{
    // faccio le verifiche
    boolean invalid = Utilities.isDateInThePast(date) || Utilities.isNotPositiveNumber(totalSeats) || Utilities.isEmptyString(title);
    if(invalid){
      List<String> messages = new ArrayList<>();
      // qui devo tirare un'eccezione
      if(Utilities.isDateInThePast(date)){
        // la data parametro è nel passato
        messages.add("invalid date");
      }
      if(Utilities.isNotPositiveNumber(totalSeats)){
        // i posti totali non sono validi
       messages.add("invalid seats");
      }
      if(Utilities.isEmptyString(title)){
        messages.add("invalid title");
      }
      // adesso lancio l'eccezione concanenando tutti i messaggi
      throw new InvalidEventParametersException(String.join(", ", messages));
    }

    this.title = title;
    this.date = date;
    this.totalSeats = totalSeats;
    this.bookedSeats = 0;
  }

  // GETTER SETTER

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    // valido il parametro title
    if(Utilities.isEmptyString(title)){
      throw new IllegalArgumentException();
    }
    this.title = title;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    // valido il parametro date
    if(Utilities.isDateInThePast(date)){
      throw new IllegalArgumentException();
    }
    this.date = date;
  }


  public int getTotalSeats() {
    return totalSeats;
  }

  public int getBookedSeats() {
    return bookedSeats;
  }

  public int getAvailableSeats(){
    return totalSeats - bookedSeats;
  }

  // METODI
  public void addBookings(int numSeats) throws RuntimeException{
      // verifico se ho abbastanza posti disponibili
      if(getAvailableSeats() < numSeats){
        throw new RuntimeException("Not enough available seats");
      }
      if(Utilities.isDateInThePast(this.date)){
        throw new RuntimeException("Event is over");
      }
      bookedSeats += numSeats;
  }

  public void cancelBookings(int numSeats){
    // verifico se ho abbastanza prenotazioni da disdire
    if(bookedSeats < numSeats){
      throw new RuntimeException("Not enough bookings");
    }
    if(Utilities.isDateInThePast(this.date)){
      throw new RuntimeException("Event is over");
    }
    bookedSeats -= numSeats;
  }

  @Override
  public String toString() {
    return title + " - " + date;
  }
}
