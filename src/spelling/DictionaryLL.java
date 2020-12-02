package spelling;

import java.util.LinkedList;

public class DictionaryLL implements Dictionary 
{
	private LinkedList<String> dict;
	
	public DictionaryLL() {
		dict = new LinkedList<String>();
	}
	
    public boolean addWord(String word) {
    	return dict.add(word.toLowerCase());
    }

    public int size()
    {
    	return dict.size();
    }

    public boolean isWord(String s) {
    	return dict.contains(s.toLowerCase());
    }

    
}
