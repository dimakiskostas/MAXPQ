
public class Process implements Comparable<Process> {

    private int id;
    private double duration;

    public Process(int id, double duration) {
        this.id = id;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(Math.abs(duration)) + " ";
    }

    @Override
    public int compareTo(Process other) {
        // xrisimopoieitai otan sigkrinoume 2 Processes
        int k;
        if (this.duration < other.duration) {
            k = -1;
        } else if (this.duration == other.duration) {
            k = 0;
        } else {
            k =  1;
        }
        
//        System.out.println(this.duration + " vs " + other.duration + " " + k);
        return k;
    }

}
