import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OffByOne implements CharacterComparator {
   public boolean equalChars(char a, char b) {
       if (a - b == 1 || a - b == -1)
           return true;
       return false;
   }
  @Test
   public void testOffByOne() {
       assertTrue(equalChars('a','a'));
  }
}