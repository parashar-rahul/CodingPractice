package com.personal.coding;

import java.util.*;

class PrimeTreeNodes {
    
    private static Random R;
    private static List<Integer> primeNodes;
    
    public static void main(String[] args) {
        
        R = new Random();
        primeNodes = new ArrayList<>();
        
        getPrimeNodes(TreeNode.binaryTreeGenerator(R.nextInt(15)));
        
        System.out.println(System.lineSeparator() + "Prime Nodes: " + primeNodes);
    }
    
    private static void getPrimeNodes(TreeNode root) {
        if (root != null) {
            if (isPrime(root.val)) {
                primeNodes.add(root.val);
            } 
            getPrimeNodes(root.left);
            getPrimeNodes(root.right);
        }
    }
    
    private static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) 
                return false;
        }
        return n > 1;
    }
    
    private static class TreeNode {
        
        int val;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int x) { 
            val = x; 
        }
            
        public static TreeNode binaryTreeGenerator (int n) {

            if (n == 0) return null;

            TreeNode root = new TreeNode(1 + R.nextInt(50));

            int leftN = R.nextInt(n);

            root.left = binaryTreeGenerator(leftN);
            root.right = binaryTreeGenerator(n - leftN - 1);

            System.out.println(String.format("%s -> (%s, %s)", 
                                             root.val, 
                                             root.left == null ? "null" : root.left.val, 
                                             root.right == null ? "null" : root.right.val));
            
            return root;
        }
    } 
}
