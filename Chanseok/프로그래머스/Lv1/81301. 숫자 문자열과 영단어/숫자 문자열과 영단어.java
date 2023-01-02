import java.util.*;
class Solution {
    public int solution(String s) {
        String answer = "";

        // 1. 첫 알파벳과 해당 알파벳으로 시작하는 영단어 매칭
        Map<Character, List<String>> eng_word = new HashMap<>();
        eng_word.put('z', new ArrayList<String>(){{add("zero");}});
        eng_word.put('o', new ArrayList<String>(){{add("one");}});
        eng_word.put('t', new ArrayList<String>(){{add("two");add("three");}});
        eng_word.put('f', new ArrayList<String>(){{add("four");add("five");}});
        eng_word.put('s', new ArrayList<String>(){{add("six");add("seven");}});
        eng_word.put('e', new ArrayList<String>(){{add("eight");}});
        eng_word.put('n', new ArrayList<String>(){{add("nine");}});

        // 2. 영단어와 숫자 매칭
        Map<String, String> eng_num = new HashMap<>();
        eng_num.put("zero", "0"); eng_num.put("one", "1"); eng_num.put("two", "2"); eng_num.put("three", "3"); eng_num.put("four", "4");
        eng_num.put("five", "5"); eng_num.put("six", "6"); eng_num.put("seven", "7"); eng_num.put("eight", "8"); eng_num.put("nine", "9");

        char c;
        List<String> words;
        int sLen = s.length();
        String word;
        int wordLen;
        for(int i = 0; i < sLen; ++i) {
            c = s.charAt(i);

            if(c >= '0' && c<= '9') answer += c;
            else {
                words = eng_word.get(c);
                word = words.get(0);
                wordLen = word.length();

                if(!s.substring(i, i+wordLen).equals(word)) {
                    word = words.get(1);
                    wordLen = word.length();
                }
                answer += eng_num.get(word);
                i = i + wordLen - 1;

            }
        }

        return Integer.parseInt(answer);
    }
}
