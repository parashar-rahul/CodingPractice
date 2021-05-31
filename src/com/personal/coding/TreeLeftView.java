package com.personal.coding;

import java.util.*;

class TreeLeftView {
    
    private static Random R = new Random();
    private static int N = 20;
    private static int I = 1;

    public static void main(String[] args) {
        
        TreeNode root = TreeNode.binaryTreeGenerator(R.nextInt(N));
    
        System.out.println(TreeNode.getLeftView(root));
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

            TreeNode root = new TreeNode(I++);

            int leftN = R.nextInt(n);

            root.left = binaryTreeGenerator(leftN);
            root.right = binaryTreeGenerator(n - leftN - 1);

            System.out.println(String.format("%s -> (%s, %s)", 
                                             root.val, 
                                             root.left == null ? "null" : root.left.val, 
                                             root.right == null ? "null" : root.right.val));
            
            return root;
        }
        
        public static Map<Integer, Integer> getLeftView(TreeNode root) {
            
            if (root == null) return null;
            
            Queue<TreeNode> level = new LinkedList<>();
            Map<Integer, Integer> levelView = new HashMap<>();
            level.offer(root);
            
            int currLevel = 0;
            
            while (!level.isEmpty()) {
                int levelSize = level.size();
                while (levelSize-- > 0) {
                    TreeNode curr = level.poll();
                    if (!levelView.containsKey(currLevel)) {
                        levelView.put(currLevel, curr.val);
                    }
                    if (curr.left != null) level.offer(curr.left);
                    if (curr.right != null) level.offer(curr.right);
                }
                currLevel++;
            }
            
            return levelView;
        }
    } 
}
