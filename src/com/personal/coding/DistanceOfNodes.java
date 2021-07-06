import java.util.*;

class DistanceOfNodes {
    
    private static final Random r = new Random();
    private static int num = 1;
    
    public static void main(String[] args) {
        
        int n = 1 + r.nextInt(100);
        System.out.println("Number of nodes: " + n);
        
        Node root = constructTree(n);
        System.out.println(root);

        int n1 = 1 + r.nextInt(n), n2 = 1 + r.nextInt(n);
        
        findDistance(root, n1, n2);
    }
    
    private static void findDistance(Node root, int n1, int n2) {
        
        Node lca = getLCA(root, n1, n2);
        
        System.out.println(String.format("LCA of %d and %d = %d", n1, n2, lca.val));
        
        int d1 = getLevelDist(lca, n1, 0);
        int d2 = getLevelDist(lca, n2, 0);
        
        System.out.println(String.format("Distance of %d from %d = %d", n1, lca.val, d1)); 
        System.out.println(String.format("Distance of %d from %d = %d", n2, lca.val, d2)); 

        System.out.println(String.format("Distance = %d", d1 + d2)); 
    }
    
    private static Node getLCA(Node node, int n1, int n2) {
        
        if (node == null || node.val == n1 || node.val == n2) {
            return node;
        }
        
        Node left = getLCA(node.left, n1, n2);
        Node right = getLCA(node.right, n1, n2);
        
        if (left != null && right != null) return node;
        else return left != null ? left : right;
    }
    
    private static int getLevelDist(Node node, int n, int l) {
        
        if (node == null) return -1;
        if (node.val == n) return l;
        
        int leftDist = getLevelDist(node.left, n, l+1);
        return leftDist != -1 ? leftDist : getLevelDist(node.right, n, l+1);
    }
    
    private static Node constructTree(int n) {
        
        if (n == 0) return null;
        
        Node root = new Node(num++);
        
        int nl = r.nextInt(n);
        root.left = constructTree(nl);
        
        int rl = n - nl - 1;
        root.right = constructTree(rl);
                
        return root;
    }
    
    private static class Node {
        int val;
        Node left, right;
        Node(int v) {
            this.val = v;
        }
        @Override public String toString() { return this == null ? null : 
                               String.format("%d -> (%s, %s)", this.val, String.valueOf(this.left), String.valueOf(this.right)); }
    }
}
