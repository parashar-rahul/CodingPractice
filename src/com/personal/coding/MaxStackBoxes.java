package com.personal.coding;

import java.util.*;

class MaxStackBoxes {

    public static void main(String[] args) {

        Random R = new Random();
        int N = 1 + R.nextInt(10);    // Number of boxes
        int D = 1 + R.nextInt(15);    // Maximum dimension of the box
        
        int[][] boxes = new int[N][3];
        for (int i=0; i<N; i++) {
            int[] box = new int[3];
            for (int j=0; j<3; j++) {
                box[j] = 1 + R.nextInt(D);
            }
            System.out.println(Arrays.toString(box));
            boxes[i] = box;
        }
        
        System.out.println(getMaxHeight(boxes));
        
    }
    
    private static int getMaxHeight(int[][] boxes) {
    
        Arrays.sort(boxes, (b1, b2) -> b1[0] != b2[0] ? b1[0] - b2[0] : b2[1] - b1[1]);
        
        int n = boxes.length, h=0;
        int[] height = new int[n];
        for (int i=0; i<n; i++) {
            int above = 0;
            for (int j=0; j<i; j++) {
                if (boxes[j][1]<boxes[i][1]) {
                    above = Math.max(above, height[j]);
                }
            }
            height[i] = boxes[i][2] + above;
        }
        
        return Arrays.stream(height).max().getAsInt();
    }
}
