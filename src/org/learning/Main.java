package org.learning;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // devo creare un evento, lo inizializzo a null
    Event event = null;

    // menu interattivo testuale
    do {
      try {
        System.out.println("Create new event");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);

        System.out.print("Total seats: ");
        int totalSeats = Integer.parseInt(scanner.nextLine());

        event = new Event(title, date, totalSeats); // solo se eseguo questa istruzione l'evento non è più null
      } catch (InvalidEventParametersException ex) {
        System.out.println("Unable to create event: " + ex.getMessage());
      } catch(DateTimeParseException de){
        System.out.println("Invalid date format");
      } catch(NumberFormatException ne) {
        System.out.println("Invalid number");
      } catch(Exception exception){
        System.out.println("Generic error");
      }
    } while (event == null);

    // fuori dal menu ho l'evento valorizzato
    System.out.println(event);




    scanner.close();
  }
}
