package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	private List<ListNode> wordList; 
	private String starter;
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	@Override
	public void train(String sourceText)
	{
		if (sourceText.length() == 0) {
			System.out.println("There is no input string!");
		} else {
			String[] sourceWords = sourceText.split("[\\s]+");
			starter = sourceWords[0];
			String prevWord = starter;
			String w;
			ListNode node;
			for (int i = 1; i <= sourceWords.length; i++) {
				if (i == sourceWords.length) {
					w = sourceWords[0];
				} else {
					w = sourceWords[i];
				}
				
				node = findNode(prevWord);
				if (node == null) {
					node = new ListNode(prevWord);
					node.addNextWord(w);
					wordList.add(node);
				} else {
					node.addNextWord(w);
				}
				prevWord = w;
				
			}
		}
	}
	
	private ListNode findNode(String word) {
		for (ListNode node : wordList) {
			if (word.equals(node.getWord())) {
				return node;
				}
			}
		return null;
		}		

	private ListNode findWordListNode(String word) {
		ListNode listNode = null;

		for (ListNode l : wordList) {
			if (l.getWord().equals(word)) {
				listNode = l;
				break;
			}
		}

		if (listNode == null) {
			listNode = new ListNode(word);
			wordList.add(listNode);
		}

		return listNode;
	}
	
	@Override
	public String generateText(int numWords) {
		StringBuilder output = new StringBuilder();

		if (!starter.equals("")) {
			String currWord = starter;
			while (numWords-- > 0) {
				output.append(currWord);
				if (numWords != 0) {
					output.append(" ");
				}
				currWord = findWordListNode(currWord).getRandomNextWord(rnGenerator);
			}

		}

		return output.toString();
	}
	
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}

	public static void main(String[] args)
	{
		
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

class ListNode
{
	private String word;
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int size = nextWords.size();
		int index = generator.nextInt(size);
	    return nextWords.get(index);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


