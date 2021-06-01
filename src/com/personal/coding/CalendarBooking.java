import static java.lang.System.*;
import java.util.*;

class CalendarBooking {

    public static void main(String[] args) {

        List<Integer> db1 = parseToMinutes(Collections.singletonList(Arrays.asList("9:00", "21:00"))).get(0);
        List<List<Integer>> cal1 = parseToMinutes(Arrays.asList(
                Arrays.asList("9:30", "10:00"),
                Arrays.asList("12:00", "13:45"),
                Arrays.asList("14:30", "15:30")
        ));

        List<Integer> db2 = parseToMinutes(Collections.singletonList(Arrays.asList("8:30", "18:45"))).get(0);
        List<List<Integer>> cal2 = parseToMinutes(Arrays.asList(
                Arrays.asList("10:30", "11:00"),
                Arrays.asList("15:30", "16:30")
        ));

        int duration = 30;

        List<List<Integer>> freeSlots = getFreeSlots(cal1, db1, cal2, db2, duration);
        out.println(parseToString(freeSlots));
    }

    private static List<List<Integer>> getFreeSlots(List<List<Integer>> cal1, List<Integer> db1,
            List<List<Integer>> cal2, List<Integer> db2, int duration) {

        List<List<Integer>> free1 = getFreeSlots(cal1, db1, duration), free2 = getFreeSlots(cal2, db2, duration);

        if (free1.isEmpty() || free2.isEmpty()) {
            return new ArrayList<>();
        }

        int i=0, j=0;
        List<List<Integer>> freeSlots = new ArrayList<>();
        while (i<free1.size() && j<free2.size()) {
            List<Integer> f1 = free1.get(i), f2 = free2.get(j);
            if (f1.get(0) >= f2.get(1)) {
                j++;
            } else if (f1.get(1) <= f2.get(0)) {
                i++;
            } else {
                int start = Math.max(f1.get(0), f2.get(0)), end = Math.min(f1.get(1), f2.get(1));
                if (end - start >= duration) {
                    freeSlots.add(Arrays.asList(start, end));
                }
                if (f1.get(1) > f2.get(1)) {
                    j++;
                } else {
                    i++;
                }
            }
        }

        return freeSlots;
    }

    private static List<List<Integer>> getFreeSlots(List<List<Integer>> cal, List<Integer> db, int duration) {

        List<List<Integer>> freeSlots = new ArrayList<>();
        int start = db.get(0);
        for (List<Integer> integers : cal) {
            int free = integers.get(0) - start;
            if (free >= duration) {
                freeSlots.add(Arrays.asList(start, integers.get(0)));
            }
            start = integers.get(1);
        }

        if (db.get(1) - start > duration) {
            freeSlots.add(Arrays.asList(start, db.get(1)));
        }

        return freeSlots;
    }

    private static List<List<Integer>> parseToMinutes(List<List<String>> times) {

        List<List<Integer>> ints = new ArrayList<>();
        for (List<String> time : times) {
            String t1 = time.get(0);
            int t1s = Integer.parseInt(t1.split(":")[0]) * 60;
            int t1e = Integer.parseInt(t1.split(":")[1]);
            String t2 = time.get(1);
            int t2s = Integer.parseInt(t2.split(":")[0]) * 60;
            int t2e = Integer.parseInt(t2.split(":")[1]);
            ints.add(Arrays.asList(t1s+t1e, t2s+t2e));
        }

        return ints;
    }

    private static List<List<String>> parseToString(List<List<Integer>> times) {

        List<List<String>> ans = new ArrayList<>();
        for (List<Integer> t : times) {
            int s = t.get(0), e = t.get(1);
            String ss = String.valueOf(s / 60) + ':' + String.format("%02d", s % 60);
            String es = String.valueOf(e / 60) + ':' + String.format("%02d", e % 60);
            ans.add(Arrays.asList(ss, es));
        }

        return ans;
    }
}
