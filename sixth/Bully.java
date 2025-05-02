import java.util.*;

public class Bully {
    static boolean[] up = { true, true, true, true, true };

    static void up(int p) {
        up[p - 1] = true;
        System.out.println("P" + p + " starts election.");
        for (int i = p; i < 5; i++) {
            System.out.println("P" + p + " -> P" + (i + 1));
        }
        for (int i = 5; i > p; i--) {
            if (up[i - 1]) {
                System.out.println("P" + i + " is new coordinator.");
                break;
            }
        }
    }

    static void down(int p) {
        up[p - 1] = false;
        System.out.println("P" + p + " is down.");
    }

    static void msg(int p) {
        if (!up[p - 1]) {
            System.out.println("P" + p + " is down.");
        } else if (up[4]) {
            System.out.println("Coordinator (P5) is alive.");
        } else {
            up(p);
        }
    }

    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Up 2.Down 3.Msg 4.Exit");
            int ch = sc.nextInt();
            if (ch == 4)
                break;
            System.out.print("Process (1-5): ");
            int p = sc.nextInt();
            if (p < 1 || p > 5)
                continue;
            if (ch == 1)
                up(p);
            else if (ch == 2)
                down(p);
            else if (ch == 3)
                msg(p);
        }
    }
}