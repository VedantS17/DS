import java.io.*;
import java.util.*;

public class Berkley {

    static int getSeconds(int h, int m, int s) {
        return h * 3600 + m * 60 + s;
    }

    static void printTime(String label, int seconds) {
        int h = (seconds / 3600) % 24;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        System.out.println(label + " ---> " + h + " : " + m + " : " + s);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Date date = new Date();
        int serverTime = getSeconds(date.getHours(), date.getMinutes(), date.getSeconds());

        System.out.print("Enter number of nodes: ");
        int n = Integer.parseInt(br.readLine());
        int[] nodeTimes = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter time for node " + (i + 1));
            System.out.print("Hours: ");
            int h = Integer.parseInt(br.readLine());
            System.out.print("Minutes: ");
            int m = Integer.parseInt(br.readLine());
            System.out.print("Seconds: ");
            int s = Integer.parseInt(br.readLine());
            nodeTimes[i] = getSeconds(h, m, s);
        }

        float totalDiff = 0;
        for (int i = 0; i < n; i++) {
            int diff = serverTime - nodeTimes[i];
            totalDiff += diff;
            printTime("Time Server sent time to node " + (i + 1), serverTime);
            System.out.println("Node " + (i + 1) + " sent time difference of " + diff + " seconds to Time Server.");
        }

        float avgDiff = totalDiff / (n + 1);
        System.out.println("The average of all time differences is " + avgDiff);

        // Sync times
        int newServerTime = serverTime + (int) avgDiff;
        printTime("Time Server", newServerTime);

        for (int i = 0; i < n; i++) {
            nodeTimes[i] += avgDiff;
            printTime("Node " + (i + 1), nodeTimes[i]);
        }
    }
}
