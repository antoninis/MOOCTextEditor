package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
   
    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	public boolean addWord(String word)
	{
		word = word.toLowerCase();
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
//		for(char c: word.toCharArray()) {
			if (current.getValidNextCharacters().contains(c))
				current = current.getChild(c);
			else
				current = current.insert(c);
		}
		
		if (!current.endsWord()) {
			current.setEndsWord(true);
			size++;
			return true;
		}
		else 
			return false;
	}
	
	public int size()
	{
	    return size;
	}
	
	
	@Override
	public boolean isWord(String s) 
	{
		s = s.toLowerCase();
		TrieNode current = root;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
//		for(char c: s.toCharArray()) {
			if (current.getValidNextCharacters().contains(c))
				current = current.getChild(c);
			else
				return false;
		}
		
		return current.endsWord();	}

@Override
    public List<String> predictCompletions(String prefix, int numCompletions) 
{
	prefix = prefix.toLowerCase();
	TrieNode current = root;
	List<String> result = new LinkedList<String>();
	
	for (int i = 0; i < prefix.length(); i++) {
		char c = prefix.charAt(i);
		if (current.getValidNextCharacters().contains(c)) 
			current = current.getChild(c);
		else 	
			return result;
	 }
	
	int count = 0;
	if (current.endsWord()) {
		result.add(current.getText());
		count++;
	}
	
	List<TrieNode> Queue = new LinkedList<TrieNode>();
	List<Character> childrenC = new LinkedList<Character>(current.getValidNextCharacters());
	 
	for (int i = 0; i < childrenC.size(); i++) {
		char c = childrenC.get(i);
		Queue.add(current.getChild(c));
	}
	 
	while (!Queue.isEmpty() && count < numCompletions) {
		TrieNode P = Queue.remove(0);
		if (P.endsWord()) {
			result.add(P.getText());
			count++;
		}
		List<Character> cs = new LinkedList<Character>(P.getValidNextCharacters());
		for (int i = 0; i < cs.size(); i++) {
			char c = cs.get(i);
			Queue.add(P.getChild(c));
			}
		}
    return result;
}

 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
}