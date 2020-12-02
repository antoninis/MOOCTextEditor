package document;

import java.util.List;

public class EfficientDocument extends Document {

	private int numWords=0;
	private int numSentences=0;
	private int numSyllables=0; 
	
	public EfficientDocument(String text)
	{
		super(text);
		processText();
	}
	
	private boolean isWord(String tok)
	{
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	
	private void processText()
	{
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		for (String word: tokens) {
			if (isWord(word)) {
				numWords++;
			}
			else numSentences++;
			numSyllables += countSyllables(word);
		}
		
		int length = tokens.size();
		if (length>0 && isWord(tokens.get(length-1))) numSentences++; 
	}

	@Override
	public int getNumSentences() {
	    return numSentences;
	}

	@Override
	public int getNumWords() {
	    return numWords;
	}

	@Override
	public int getNumSyllables() {
		return numSyllables;
	}
	
	public static void main(String[] args)
	{
	    testCase(new EfficientDocument("This is a test.  How many???  "
                + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCase(new EfficientDocument(""), 0, 0, 0);
        testCase(new EfficientDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2); 
        testCase(new EfficientDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new EfficientDocument("Segue"), 2, 1, 1);
		testCase(new EfficientDocument("Sentence"), 2, 1, 1);
		testCase(new EfficientDocument("Sentences?!"), 3, 1, 1);
		testCase(new EfficientDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		
	}
	

}
