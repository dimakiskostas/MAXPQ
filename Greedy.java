
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Greedy {

    public static void main(String[] args) {
        int numCPUs = 0;
        int numProcesses = 0;
        double duration;
        int processID = 0;
        Scanner in;

        MaxPQ<Processor> processorHeap = null;

        Processor[] cpus = null;

        try {
            File file = new File("timeplan.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                counter++;

                switch (counter) {
                    case 1:
                        numCPUs = Integer.parseInt(line);
                        cpus = new Processor[numCPUs];
                        processorHeap = new MaxPQ<>(numCPUs);
                        
                        for (int i = 0; i < numCPUs; i++) {
                            cpus[i] = new Processor();
                            processorHeap.insert(cpus[i]);
                        }
                        break;
                    case 2:
                        numProcesses = Integer.parseInt(line);
                        break;
                    default:
                        in = new Scanner(line);
                        int id = in.nextInt();
                        duration = in.nextDouble();

                        Process p = new Process(id, -duration); // reverse heap
                        Processor cpu = processorHeap.getMax();
                        cpu.getProcessed_jobs().addLast(p);
                        processorHeap.insert(cpu);
                        break;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        double max = 0;

        for (int i = 0; i < numCPUs; i++) {
            if (Math.abs(cpus[i].getActiveTime()) > max) {
                max = Math.abs(cpus[i].getActiveTime());
            }
        }

        if (numProcesses < 100) {
            while (processorHeap != null && !processorHeap.empty()) {
                Processor cpu = processorHeap.getMax();

                System.out.print("CPU id " + cpu.getId() + ",load=" +  Math.abs(cpu.getActiveTime()) + ":  ");
                cpu.getProcessed_jobs().printQueue(System.out);
                System.out.println();
            }
        }
        
        System.out.println("maketime = " + max);
    }

}
