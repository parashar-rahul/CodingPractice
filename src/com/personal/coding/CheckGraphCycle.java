import java.util.*;

class CheckGraphCycle {
    
    public static void main(String[] args) {
        
        Random r = new Random();
        
        int v = r.nextInt(10);
        List<List<Integer>> edges = new ArrayList<>();
        
        for (int i=0; i<v; i++) {
            for (int j=0; j<v; j++) {
                if (i != j && Math.random() < 0.25) {
                    edges.add(Arrays.asList(i, j));
                }
            }
        }
        
        System.out.println(hasCycle(v, edges));
    }
    
    private static boolean hasCycle(int v, List<List<Integer>> edges) {
    
        Map<Integer, List<Integer>> adj = new HashMap<>();
        
        for (List<Integer> edge: edges) {
            adj.putIfAbsent(edge.get(0), new ArrayList<>());
            adj.get(edge.get(0)).add(edge.get(1));
        }
        
        System.out.println(v);
        System.out.println(adj);
        
        int[] state = new int[v];
        
        for (int i=0; i<v; i++) {
            if (hasCycle(i, adj, state)) {
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean hasCycle(int u, Map<Integer, List<Integer>> adj, int[] state) {
    
        if (state[u] == 0) {
            state[u] = 1;
        } else {
            return state[u] == 1;
        }
        
        for (int v : adj.getOrDefault(u, new ArrayList<>())) {
            if (hasCycle(v, adj, state)) {
                return true;
            }
        }
        
        state[u] = 2;
        return false;
    }
    
}
