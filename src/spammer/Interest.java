package spammer;

import java.util.HashSet;

import backend.MathHelper;

public class Interest
{
	private String expressions[];
	private HashSet<String> words;
	
	public Interest(InterestData idata)
	{
		expressions = idata.expressions;
		words = new HashSet<String>();
		for(int i = 0; i < idata.words.length; i++)
			words.add(idata.words[i]);
	}

	public boolean contains(String word)
	{
		if(words != null)
			return words.contains(word);
		return false;
	}
	
	public String getRandomExpression()
	{
		if(expressions != null)
			return expressions[MathHelper.randInt(0, expressions.length - 1)];
		return "I am not expressive.";
	}

}
