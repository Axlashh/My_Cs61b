public class Palindrome {
   public Deque<Character> wordToDeque(String word) {
       Deque<Character> ret = new ArrayDeque();
       char[] t = word.toCharArray();
       for (int i = 0; i < word.length(); i += 1) {
           ret.addLast(t[i]);
       }
       return ret;
   }

   public boolean isPalindrome(String word) {
       Deque<Character> t = wordToDeque(word);
       int len = t.size();
       for (int i = 0; i < len/2; i += 1){
           if (t.get(i) != t.get(len - i - 1)) {
               return false;
           }
       }
       return true;
   }

   public boolean isPalindrome(String word, CharacterComparator cc) {
       Deque<Character> t = wordToDeque(word);
       int len = t.size();
       for (int i = 0; i < len/2; i += 1) {
           if(!cc.equalChars(t.get(i), t.get(len - i - 1)))
               return false;
       }
       return true;
   }
}