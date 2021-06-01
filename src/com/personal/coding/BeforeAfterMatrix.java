import java.util.*;

class BeforeAfterMatrix {
    
    public static void main(String[] args) {
        
        Random r = new Random();
        int m = 1+r.nextInt(10);
        int n = 1+r.nextInt(10);
        
        System.out.println(m + " x " + n);
        
        int[][] mat = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                mat[i][j] = r.nextInt(20);
            }
        }
        
        int[][] afterMat = getAfterMat(mat, m, n);
        
        int[][] beforeMat = getBeforeMat(afterMat, m, n);
        
        for (int i=0; i<m; i++) {
            System.out.println(String.format("%s -> %s -> %s", 
                Arrays.toString(mat[i]), Arrays.toString(afterMat[i]), Arrays.toString(beforeMat[i])));
        }
    }
    
    private static int[][] getAfterMat(int[][] mat, int m, int n) {
        int[][] afterMat = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                afterMat[i][j] = mat[i][j];
            }
        }
        for (int i=1; i<m; i++) {
            afterMat[i][0] += afterMat[i-1][0];
        }
        for (int j=1; j<n; j++) {
            afterMat[0][j] += afterMat[0][j-1];
        }
        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {
                afterMat[i][j] += (afterMat[i-1][j] + afterMat[i][j-1] - afterMat[i-1][j-1]);
            }
        }
        return afterMat;
    }
    
    private static int[][] getBeforeMat(int[][] mat, int m, int n) {
        int[][] beforeMat = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                beforeMat[i][j] = mat[i][j];
            }
        }
        for (int i=m-1; i>0; i--) {
            for (int j=n-1; j>0; j--) {
                beforeMat[i][j] -= (beforeMat[i-1][j] + beforeMat[i][j-1] - mat[i-1][j-1]);
            }
        }
        for (int i=m-1; i>0; i--) {
            beforeMat[i][0] -= mat[i-1][0];
        }
        for (int j=n-1; j>0; j--) {
            beforeMat[0][j] -= mat[0][j-1];
        }
        return beforeMat;
    }
}
