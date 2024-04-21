
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class Sort {

    public static void main(String[] args) {
        int numCPUs = 0;
        int numProcesses = 0;
        double duration;
        int processID = 0;

        MaxPQ<Processor> processorHeap = null;
        MaxPQ<Process> processHeap = null;

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
                        processHeap = new MaxPQ<>(numProcesses);
                        break;
                    default:
                        duration = Double.parseDouble(line);
                        Process p = new Process(++processID, -duration); // reverse heap
                        processHeap.insert(p);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("------------------");

        // heapsort
        while (!processHeap.empty()) {
            Process p = processHeap.getMax();
            System.out.println(p);

            Processor cpu = processorHeap.getMax();
            cpu.getProcessed_jobs().addLast(p);

            processorHeap.insert(cpu);
        }

        System.out.println("------------------");

        double max = 0;

        for (int i = 0; i < numCPUs; i++) {
            if (Math.abs(cpus[i].getActiveTime()) > max) {
                max = Math.abs(cpus[i].getActiveTime());
            }
        }

        if (numProcesses < 100) {
            while (processorHeap != null && !processorHeap.empty()) {
                Processor cpu = processorHeap.getMax();

                System.out.print("CPU id " + cpu.getId() + ",load=" + Math.abs(cpu.getActiveTime()) + ":  ");
                cpu.getProcessed_jobs().printQueue(System.out);
                System.out.println();
            }
        }

        System.out.println("maketime = " + max);
    }
}
