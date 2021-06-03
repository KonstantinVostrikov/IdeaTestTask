


public class Main {
    public static void main(String[] args) {

        Tickets d = Tickets.getInstance();

        String averageTime = d.getAverageFlightTime();
        String percentile = d.getPercentile(90);
        System.out.println(averageTime);
        System.out.println(percentile);
    }
}
