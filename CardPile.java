package hw10;

import java.util.ArrayList;

public class CardPile
{
	private ArrayList<Card> pile;
	
	/**
	 * default constructor initializes an empty ArrayList<Card> pile simulating an empty CardPile
	 */
	public CardPile()
	{
		pile = new ArrayList<Card>();
	}
	
	/**
	 * accepts varargs of type Card and initializes the pile with those cards
	 * @param cards any number of Cards to be added to the CardPile
	 */
	public CardPile(Card... cards)
	{
		pile = new ArrayList<Card>();
		for(Card newCard : cards)
		{
			pile.add(newCard);
		}
	}
	
	/**
	 * copy constructor which initializes a new pile with the same cards in the CardPile passed to the constructor
	 * @param cards CardPile to be copied
	 */
	public CardPile(CardPile cards)
	{
		pile = new ArrayList<Card>();
		for(Card newCard : cards.getCardlist())
		{
			pile.add(newCard);
		}
	}
	
	/**
	 * initializes the ArrayList<Card> pile to the value of the passed ArrayList<Card>
	 * @param cardList cards in the list
	 */
	public CardPile(ArrayList<Card> cardList)
	{
		pile = cardList;
	}
	
	/**
	 * accepts a Card object and adds it to the first index in the pile
	 * @param newCard to be added to the top of the pile
	 */
	public void addToTop(Card newCard)
	{
		pile.add(0, newCard);
	}
	
	/**
	 * accepts a CardPile object and adds its Cards to the first index of this pile
	 * the card at the bottom of the passed CardPile will be the top card in this pile
	 * @param cards pile to be added to the top of the pile
	 */
	public void addToTop(CardPile cards)
	{
		for(Card newCard : cards.getCardlist())
		{
			pile.add(0, newCard);
		}
	}

	/**
	 * accepts a Card object and adds it to the last index in the pile
	 * @param newCard to be added to the bottom of the pile
	 */
	public void addToBottom(Card newCard)
	{
		pile.add(newCard);
	}	
	
	/**
	 * accepts a CardPile object and adds its Cards to the last index of this pile
	 * the card at the bottom of the passed CardPile will be the last card in this pile
	 * @param cards pile to be added to the bottom of the pile
	 */
	public void addToBottom(CardPile cards)
	{
		for(Card newCard : cards.getCardlist())
			pile.add(newCard);
	}
	
	/**
	 * removes amt Cards from the top of the pile and individualy adds them to a new CardPile
	 * @param amt a positive non zero amount to remove from the top that doesn't exceed the size of the pile
	 * @return the Cards removed from the Pile
	 */
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
	
	/**
	 * removes amt Cards from the bottom of the pile and individualy adds them to a new CardPile
	 * @param amt a positive non zero amount to remove from the top that doesn't exceed the size of the pile
	 * @return the Cards removed from the Pile
	 */
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
	
	/**
	 * accessor for the pile ArrayList
	 * @return pile
	 */
	public ArrayList<Card> getCardlist()
	{
		return pile;
	}
	
	/**
	 * accesses the Card in the pile located at index
	 * @param index 
	 * @return Card at index
	 */
	public Card getCard(int index)
	{
		return pile.get(index);
	}
	
	/**
	 * @return number of Cards in the CardPile
	 */
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
