
import java.util.Random;


public class Processor implements Comparable<Processor>{

    private static int counter = 0;
    private int id;

    private DoubleEndedQueueImpl<Process> processed_jobs = new DoubleEndedQueueImpl<>();

//    private int at;
    
    public Processor() {
        this.id = ++counter;
        
//        at = new Random().nextInt(1000);
    }

    public int getId() {
        return id;
    }

    public DoubleEndedQueueImpl<Process> getProcessed_jobs() {
        return processed_jobs;
    }

    public double getActiveTime() {
//        return at;
        if (!processed_jobs.isEmpty()) {
            DoubleLinkedListNode<Process> ptr = processed_jobs.getHead();

            double sum = 0;

            while (ptr != null) {
                sum = sum + ptr.data.getDuration();
                ptr = ptr.nextNode;
            }
            return sum;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(Processor other) {
        // xrisimopoieitai otan sigkrinoume 2 processors
        if (this.getActiveTime() > other.getActiveTime()) {
            return 1;
        } else if (this.getActiveTime() == other.getActiveTime()) {
            return 0;
        } else {
            return -1;
        }
    }

}
