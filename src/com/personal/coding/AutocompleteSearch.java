import java.util.*;
import java.util.stream.*;
import org.apache.commons.lang3.*;

public class AutocompleteSearch {
    
    private static Trie root;
    
    public static void main(String[] args) {
        
        List<String> words = IntStream.range(0, 10000)
            .mapToObj(i -> RandomStringUtils.randomAlphabetic(3, 10).toLowerCase())
            .collect(Collectors.toList());
        String prefix = RandomStringUtils.randomAlphabetic(3).toLowerCase();
        
        root = new Trie();
        insertWords(words);
        
        System.out.println(autocomplete(prefix, words));
    }
    
    private static void insertWords(List<String> words) {
        for (int i=0; i<words.size(); i++) {
            String w = words.get(i);
            double l = w.length();
            Trie ins = root;
            for (int c=0; c < w.toCharArray().length; c++) {
                int ci = w.charAt(c) - 'a';
                if (ins.next[ci] == null) {
                    ins.next[ci] = new Trie();
                }
                ins = ins.next[ci];
                ins.wordProb.put(i, (c+1)/l);
            }
        }
    }
    
    private static List<String> autocomplete(String pref, List<String> words) {
        List<String> matches = new ArrayList<>();
        Trie find = root;
        for (char c : pref.toCharArray()) {
            int ci = c - 'a';
            if (find.next[ci] == null) {
                return matches;
            } else {
                find = find.next[ci];
            }
        }
        
        find.wordProb.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEach(e -> matches.add(words.get(e.getKey())));
        
        return matches;
    }
    
    private static class Trie {
        Trie[] next;
        Map<Integer, Double> wordProb;
        Trie() {
            next = new Trie[26];
            wordProb = new HashMap<>();
        }
    }
}
