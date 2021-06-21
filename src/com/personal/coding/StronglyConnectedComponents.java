import java.util.*;

class StronglyConnectedComponents {
    
    private static Map<Integer, List<Integer>> adj, radj, cadj;
    private static Deque<Integer> stack;
    private static Queue<Integer> component;
    private static Set<Integer> vis;
    
    public static void main(String[] args) {
        
        Random r = new Random();
        
        int v = 1 + r.nextInt(10);
        List<List<Integer>> edges = new ArrayList<>();
        
        for (int i=0; i<v; i++) {
            for (int j=0; j<v; j++) {
                if (i != j && Math.random() < 0.25) {
                    edges.add(Arrays.asList(i, j));
                }
            }
        }
        
        getConnectedComponents(v, edges);
        
    }
    
    private static void getConnectedComponents(int v, List<List<Integer>> edges) {
        
        adj = new HashMap<>();
        radj = new HashMap<>();
        cadj = new HashMap<>();
        
        for (List<Integer> edge : edges) {
            adj.putIfAbsent(edge.get(0), new ArrayList<>());
            adj.get(edge.get(0)).add(edge.get(1));
            radj.putIfAbsent(edge.get(1), new ArrayList<>());
            radj.get(edge.get(1)).add(edge.get(0));
        }
        
        System.out.println(v);
        System.out.println(adj);
        
        stack = new ArrayDeque<>();
        component = new LinkedList<>();
        vis = new HashSet<>();
        
        for (int i=0; i<v; i++) {
            if (!vis.contains(i)) {
                dfs1(i);
            }
        } 
        
        vis.clear();
        
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (!vis.contains(u)) {
                dfs2(u);
                int root = component.peek();
                cadj.put(root, new ArrayList<>());
                while (!component.isEmpty()) {
                    cadj.get(root).add(component.poll());
                }
            }
        }
        
        System.out.println(cadj);
        
    }
    
    private static void dfs1(int u) {
        
        vis.add(u);
        
        for (int v : adj.getOrDefault(u, new ArrayList<>())) {
            if (!vis.contains(v)) {
                dfs1(v);
            }
        }
    
        stack.push(u);
    }
    
    private static void dfs2(int u) {
    
        vis.add(u);
        component.offer(u);
        
        for (int v : radj.getOrDefault(u, new ArrayList<>())) {
            if (!vis.contains(v)) {
                dfs2(v);
            }
        }
    }

}
