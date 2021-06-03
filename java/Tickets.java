import com.fasterxml.jackson.annotation.JsonAutoDetect;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@JsonAutoDetect
public class Tickets {

    private List<Flight> tickets;


    public Tickets() {
        this.tickets = new ArrayList<>();
    }

    public void setTickets(List<Flight> tickets) {
        this.tickets = tickets;
    }

    public List<Flight> getTickets() {
        return tickets;
    }


    public String getAverageFlightTime() {
        String result = null;

        int count = 0;
        long average;
        long sum = 0;

        for (Flight flight : this.getTickets()) {
            Duration duration = flight.getDuration();
            sum += duration.toMillis();
            count++;
        }

        average = sum / count;

        int hours = (int) (average / (60 * 60 * 1000));
        int minutes = (int) (average / (60 * 1000) % 60);

        result = "Average flight duration is " + hours + " hours " + minutes + " minutes";
        return result;
    }


    public String getPercentile(double percentile){
        String result = null;

        ArrayList<Flight> flights = new ArrayList(this.getTickets());
        Collections.sort(flights);

        int index = (int) Math.ceil(percentile / 100.0 * flights.size());
        Flight necessary = flights.get(index - 1);

        result =  percentile + "th percentile of flight time between Vladivostok and Tel Aviv is " + necessary.getDuration().toHoursPart()  + " hours " + necessary.getDuration().toMinutesPart() + " minutes";

        return result;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "flights=" + tickets +
                '}';
    }


    public static Tickets getInstance() {
        Tickets d = null;
        System.out.println("Give the json destination, please");
        Scanner scanner = new Scanner(System.in);
        String destination = scanner.nextLine();

        while (!destination.endsWith(".json")){
            System.out.println("Give the json destination, please");
            destination = scanner.nextLine();
        }


        try {
            ObjectMapper mapper = new ObjectMapper();
            d = mapper.readValue(new File(destination), Tickets.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }
}
