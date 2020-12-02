package spelling;

import java.util.TreeSet;

public class DictionaryBST implements Dictionary 
{
	private TreeSet<String> dict;
   
	public DictionaryBST() {
	   dict = new TreeSet<>();
   }
	
	public boolean addWord(String word) {
    	return dict.add(word.toLowerCase());
    }
   
	public int size(){
    	return dict.size();
    }

	public boolean isWord(String s) {
    	return dict.contains(s.toLowerCase());
    }

}
