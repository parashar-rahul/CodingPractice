import java.util.*;

class PickStones {
    public static void main(String[] args) {
        
        Random r = new Random();
        
        int n = r.nextInt(10);
        int[] stones = new int[n];
        for (int i=0; i<n; i++) {
            stones[i] = 1 + r.nextInt(20);
        }
        
        System.out.println(Arrays.toString(stones));
        System.out.println();
        
        getWinner(stones);
        
    }
    
    private static void getWinner(int[] stones) {
        
        int n = stones.length;
        if (n == 0) {
            System.out.println("DRAW");
            return;
        }
        
        Score[][] scores = new Score[n][n];
        
        for (int i=0; i<n; i++) {
            scores[i][i] = new Score(stones[i], 0, i);
        }        
        
        for (int l=2; l<=n; l++) {
            for (int i=0; i<=n-l; i++) {
                int j = i+l-1;
                int fi = stones[i] + scores[i+1][j].second;
                int fj = stones[j] + scores[i][j-1].second;
                if (fi > fj) {
                    scores[i][j] = new Score(fi, scores[i+1][j].first, i);
                } else {
                    scores[i][j] = new Score(fj, scores[i][j-1].first, j);
                }
            }
        }
        
        printPicks(stones, scores);
        
        if (scores[0][n-1].first > scores[0][n-1].second) System.out.println("Winner: P1");
        else if (scores[0][n-1].first < scores[0][n-1].second) System.out.println("Winner: P2");
        else System.out.println("DRAW");
    }
    
    private static void printPicks(int[] stones, Score[][] scores) {
                
        int n = stones.length;
        int i=0, j=n-1;
        boolean p1 = true;
        while (n-- > 0) {
            int pick = scores[i][j].pick;
            System.out.println(String.format("%s picked %s", p1 ? "P1" : "P2", stones[pick]));
            p1 = !p1;
            if (pick == i) i++;
            else j--;
        }
        
        System.out.println();
    }
    
    private static class Score {
        int first, second, pick;
        Score(int f, int s, int p) {
            this.first = f;
            this.second = s;
            this.pick = p;
        }
    }  
}
