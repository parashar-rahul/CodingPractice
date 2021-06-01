import static java.lang.System.*;
import java.util.*;


class BestBlock {
    
    private static final String FARTHEST = "F";

    public static void main(String[] args) {

        Random r = new Random();
        List<String> reqs = Arrays.asList("A", "B", "C", "D", "E");
        List<Map<String, Boolean>> blocks = new ArrayList<>();

        for (int i=0; i<10; i++) {
            Map<String, Boolean> block = new HashMap<>();
            for (String req : reqs) {
                block.put(req, r.nextBoolean());
            }
            blocks.add(block);
        }

        out.println("BLOCKS:");
        for (Map<String, Boolean> block : blocks) {
            out.println(block.toString());
        }

        out.println("BEST BLOCK = " + bestAptBlock(blocks, reqs));
    }

    private static int bestAptBlock(List<Map<String, Boolean>> blocks, List<String> reqs) {

        int n = blocks.size();
        int farthest = 0;

        List<Map<String, Integer>> ldist = new ArrayList<>();
        List<Map<String, Integer>> rdist = new ArrayList<>();

        Map<String, Integer> dist1 = new HashMap<>();
        for (String req : reqs) {
            int dist = blocks.get(0).get(req) ? 0 : 1000;
            dist1.put(req, dist);
            farthest = Math.max(dist, farthest);
        }

        dist1.put(FARTHEST, farthest);
        ldist.add(dist1);

        for (int i=1; i<n; i++) {
            Map<String, Integer> reqDist = new HashMap<>();
            farthest = 0;
            for (String req : reqs) {
                int dist = blocks.get(i).get(req) ? 0 : ldist.get(i-1).get(req) + 1;
                reqDist.put(req, dist);
                farthest = Math.max(dist, farthest);
            }
            reqDist.put(FARTHEST, farthest);
            ldist.add(reqDist);
        }

        Map<String, Integer> distN = new HashMap<>();
        farthest = 0;

        for (String req : reqs) {
            int dist = blocks.get(n-1).get(req) ? 0 : 1000;
            distN.put(req, dist);
            farthest = Math.max(dist, farthest);
        }

        distN.put(FARTHEST, farthest);
        rdist.add(distN);

        for (int i=1; i<n; i++) {
            Map<String, Integer> reqDist = new HashMap<>();
            farthest = 0;
            for (String req : reqs) {
                int dist = blocks.get(n-i-1).get(req) ? 0 : rdist.get(i-1).get(req) + 1;
                reqDist.put(req, dist);
                farthest = Math.max(dist, farthest);
            }
            reqDist.put(FARTHEST, farthest);
            rdist.add(reqDist);
        }

        Collections.reverse(rdist);

        out.println("LEFT:");

        for (Map<String, Integer> dist : ldist) {
            out.println(dist.toString());
        }

        out.println("RIGHT:");
        for (Map<String, Integer> dist : rdist) {
            out.println(dist.toString());
        }

        int bestBlock = -1;
        farthest = 10000;
        for (int i=0; i<n; i++) {
            if (farthest > Math.min(ldist.get(i).get(FARTHEST), rdist.get(i).get(FARTHEST))) {
                farthest = Math.min(ldist.get(i).get(FARTHEST), rdist.get(i).get(FARTHEST));
                bestBlock = i;
            }
        }

        return bestBlock;
    }
}
