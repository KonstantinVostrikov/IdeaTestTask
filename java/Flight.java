import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@JsonAutoDetect
public class Flight  implements Comparable{
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate departureDate;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime departureTime;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate arrivalDate;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime arrivalTime;
    private String carrier;
    private int stops;
    private int price;


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginName() {
        return originName;
    }

    @JsonProperty("origin_name")
    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    @JsonProperty("destination_name")
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    @JsonProperty("departure_date")
    @JsonFormat(pattern = "dd.MM.yy")
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    @JsonProperty("departure_time")
    @JsonFormat(pattern = "H:mm")
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }


    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    @JsonProperty("arrival_date")
    @JsonFormat(pattern = "dd.MM.yy")
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    @JsonProperty("arrival_time")
    @JsonFormat(pattern = "H:mm")
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Duration getDuration(){
        LocalDateTime dep = LocalDateTime.of(this.getDepartureDate(), this.getDepartureTime());
        LocalDateTime arr = LocalDateTime.of(this.getArrivalDate(), this.getArrivalTime());
        Duration duration = Duration.between(dep, arr);

        return duration;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Flight f = (Flight) o;
        Duration first = this.getDuration();
        Duration second = f.getDuration();
        return first.compareTo(second);
    }
}
