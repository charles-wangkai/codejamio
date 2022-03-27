import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int D = sc.nextInt();
      int N = sc.nextInt();
      int U = sc.nextInt();
      int[] M = new int[D];
      int[] L = new int[D];
      int[] E = new int[D];
      for (int i = 0; i < D; ++i) {
        M[i] = sc.nextInt();
        L[i] = sc.nextInt();
        E[i] = sc.nextInt();
      }
      int[] O = new int[N];
      for (int i = 0; i < O.length; ++i) {
        O[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(M, L, E, O, U)));
    }

    sc.close();
  }

  static int solve(int[] M, int[] L, int[] E, int[] O, int U) {
    List<Event> events = new ArrayList<>();
    for (int i = 0; i < M.length; ++i) {
      events.add(new Event(M[i], i, false));
    }
    for (int Oi : O) {
      events.add(new Event(Oi, -1, true));
    }
    Collections.sort(
        events,
        Comparator.comparing((Event event) -> event.time).thenComparing(event -> event.isOrder));

    int result = 0;
    PriorityQueue<Integer> spoilTimes = new PriorityQueue<>();
    for (Event event : events) {
      if (event.isOrder) {
        while (!spoilTimes.isEmpty() && spoilTimes.peek() <= event.time) {
          spoilTimes.poll();
        }

        if (spoilTimes.size() < U) {
          break;
        }

        for (int i = 0; i < U; ++i) {
          spoilTimes.poll();
        }
        ++result;
      } else {
        for (int i = 0; i < L[event.deliveryIndex]; ++i) {
          spoilTimes.offer(event.time + E[event.deliveryIndex]);
        }
      }
    }

    return result;
  }
}

class Event {
  int time;
  int deliveryIndex;
  boolean isOrder;

  Event(int time, int deliveryIndex, boolean isOrder) {
    this.time = time;
    this.deliveryIndex = deliveryIndex;
    this.isOrder = isOrder;
  }
}