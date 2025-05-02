import java.util.*;

public class Ring {
    static class Process {
        int id;
        String state = "active";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int num = sc.nextInt();
        Process[] proc = new Process[num];

        for (int i = 0; i < num; i++) {
            proc[i] = new Process();
            System.out.print("Enter ID for process " + (i + 1) + ": ");
            proc[i].id = sc.nextInt();
        }

        proc[num - 1].state = "inactive";
        System.out.println("Coordinator: Process " + proc[num - 1].id);

        while (true) {
            System.out.println("\n1. Election  2. Quit");
            if (sc.nextInt() == 2)
                break;

            System.out.print("Enter process initiating election: ");
            int init = sc.nextInt() - 1;
            int current = init, next = (init + 1) % num;

            do {
                if ("active".equals(proc[next].state) && proc[next].id != proc[num - 1].id) {
                    System.out.println(proc[current].id + "->" + proc[next].id);
                    current = next;
                }
                next = (next + 1) % num;
            } while (next != init);
            System.out.println(proc[current].id + "->" + proc[init].id);

            int coordinator = -1;
            for (int i = 0; i < num; i++) {
                if ("active".equals(proc[i].state)) {
                    coordinator = Math.max(coordinator, proc[i].id);
                }
            }
            System.out.println("process " + coordinator + " selected as coordinator.");

            for (int i = 0; i < num; i++) {
                if (proc[i].id == coordinator) {
                    proc[i].state = "inactive";
                    break;
                }
            }
        }
    }
}