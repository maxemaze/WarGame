package hw10;

import java.util.ArrayList;

public class CardPile
{
	private ArrayList<Card> pile;
	

	public CardPile()
	{
		pile = new ArrayList<Card>();
	}
	
	public CardPile(Card... cards)
	{
		pile = new ArrayList<Card>();
		for(Card newCard : cards)
		{
			pile.add(newCard);
		}
	}
	
	public CardPile(ArrayList<Card> cardList)
	{
		pile = cardList;
	}
	
	public CardPile(CardPile cards)
	{
		pile = new ArrayList<Card>();
		for(Card newCard : cards.getCardlist())
		{
			pile.add(0, newCard);
		}
	}
	
	public void addToTop(CardPile cards)
	{
		for(Card newCard : cards.getCardlist())
		{
			pile.add(0, newCard);
		}
	}
	
	public void addToTop(Card newCard)
	{
		pile.add(0, newCard);
	}
	
	public void addToBottom(CardPile cards)
	{
		for(Card newCard : cards.getCardlist())
			pile.add(newCard);
	}
	
	public void addToBottom(Card newCard)
	{
		pile.add(newCard);
	}
	
	public CardPile removeFromTop(int amt)
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		try
		{
			if(amt<=0 || amt>pile.size())
				throw new IllegalArgumentException();
			for(int i=0; i<amt; i++)
				cards.add(pile.remove(0));
		}catch(IllegalArgumentException|ArrayIndexOutOfBoundsException o)
		{
			System.out.println("there is not that many cards");
		}
		return new CardPile(cards);
	}
	
	public CardPile removeFromBottom(int amt)
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		try
		{
			if(amt<=0 || amt>pile.size())
				throw new IllegalArgumentException();
			for(int i=0; i<amt; i++)
				cards.add(pile.remove(pile.size()-1));
		}catch(IllegalArgumentException|ArrayIndexOutOfBoundsException o)
		{
			System.out.println("IllegalArgumentException: dont work");
		}
		return new CardPile(cards);
	}
	
	public ArrayList<Card> getCardlist()
	{
		return pile;
	}
	
	public Card getCard(int index)
	{
		return pile.get(index);
	}
	
	public int cardsLeft()
	{
		return pile.size();
	}
	
	public String toString()
	{
		String deckString="";
		
		for(int i=0; i < pile.size(); i++)
		{
			deckString = deckString.concat(pile.get(i)+", ");
			if((i+1)%13==0 && i>0)
				deckString = deckString.concat("\n");
		}
		return deckString;
	}
}
