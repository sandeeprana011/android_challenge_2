package com.example.bhiwalakhil.myapplication;

/**
 * Created by sandeeprana on 11/04/16.
 */
public class RevString {
   char[] letters = "acdegilmnoprstuw".toCharArray();



   public long hash(String s) {
	  long h = 7;

	  for (int i = 0; i < s.length(); i++) {
//            letters[s.toCharArray()[i]]
		 int count = 0;
		 while (letters[count] != s.toCharArray()[i]) {
			count++;
		 }
		 h = (h * 37 + (count));
	  }
	  return h;
   }

   public String nextUpString(String s) {
//	  char[] letters = "acdegilmnoprstuw".toCharArray();
	  int length = s.length();
	  char c = s.charAt(length - 1);

	  if (c == 'w')
		 return length > 1 ? nextUpString(s.substring(0, length - 1)) + 'a' : "aa";

	  return s.substring(0, length - 1) + letters[indexOfChar(c, letters)+1];
   }


   private int indexOfChar(char ch, char[] letters) {

	  int incr = 0;
	  while (!(ch == letters[incr])) {
		 incr++;
	  }
	  return incr;
   }

}
