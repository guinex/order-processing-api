import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

class TrieNode {
    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord;

    Map<Character, TrieNode> getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String word) {
        TrieNode current = root;

        for (char l : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
        }
        current.setEndOfWord(true);
    }

    boolean delete(String word) {
        return delete(root, word, 0);
    }

    boolean containsNode(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    boolean isEmpty() {
        return root == null;
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }

}

public class Test {

    public static void main(String[] args)
    {
        Map<Character, List<String>> numberMap = new HashMap<>();
        numberMap.put('2', new ArrayList<>(Arrays.asList("a","b","c")));
        numberMap.put('3', new ArrayList<>(Arrays.asList("d","e","f")) {});
        numberMap.put('4', new ArrayList<>(Arrays.asList("g","h","i")) {});
        numberMap.put('5', new ArrayList<>(Arrays.asList("j","k","l")) {});
        numberMap.put('6', new ArrayList<>(Arrays.asList("m","n","o")) {});
        numberMap.put('7', new ArrayList<>(Arrays.asList("p","q","r","s")) {});
        numberMap.put('8', new ArrayList<>(Arrays.asList("t","u","v")) {});
        numberMap.put('9', new ArrayList<>(Arrays.asList("w","x","y","z")) {});

        // Input number here
        String s = args[0];

        List<String> numberStrings = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if(c == '-'){
                continue;
            }

            if (!numberMap.containsKey(c)) {
                return;
            }

            List<String> charList = numberMap.get(c);

            if (numberStrings.isEmpty()) {
                numberStrings.addAll(charList);
            }
            else {
                List<String> tempStrings = new ArrayList<>(numberStrings.size() * charList.size());

                for (String numberString : numberStrings) {
                    for (String t : charList) {
                        tempStrings.add(numberString + t);
                    }
                }

                numberStrings = tempStrings;
            }
        }

        Trie trie = new Trie();

        for (String numberString : numberStrings) {
            trie.insert(numberString);
        }

        // Input file here

        try {
            File file = new File("src/sample_dictionary.txt");
            BufferedReader br=new BufferedReader(new FileReader(file));

            String input = "";
            while ((input = br.readLine()) != null) {
                if (trie.containsNode(input)) {
                    System.out.println(input);
                }
            }

        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}