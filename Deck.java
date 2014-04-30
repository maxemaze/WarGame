package hw10;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;

/*
 * Maxe Mazelis
 * CS 110
 *
 */

public class Deck extends CardPile
{
	public static final ImageIcon CARDBACK = new ImageIcon("src/hw10/pictures/back.jpg");

	
	/**
	 * default constructor which creates a CardPile of 52 uniquely initialized Cards 
	 * the default deck is ordered SPADES - CLUBS - HEARTS - DIAMONDS and from ACE to KING
	 */
	public Deck()
	{
		super();
		addToBottom(buildDeck());
	}
	
	/**
	 *  constructor that initializes a standard 52 Card deck and shuffles it
	 */
	public Deck(boolean shuffle)
	{
		super();
		addToBottom(buildDeck());
		
		if(shuffle)
			shuffle();
	}
	
	/**
	 * initializes an ArrayList<Card> of a 52 Card deck and returns a new CardPile with those Cards
	 * outer loop iterates 4 times for each suit, inner iterates 13 for each rank.
	 * SPADES - CLUBS - HEARTS - DIAMONDS and ACE - KING
	 * @return a new CardPile with a full deck
	 */
	public CardPile buildDeck()
	{
		ArrayList<Card> deck = new ArrayList<Card>();
		for(int i=1; i <= 4; i++)
		//for(int i=1; i<2; i++)
		{
			for(int j=1; j <= 13; j++)
			//for(int j=1; j<=5; j++)
			{
				switch(i)
				{
					case 1: deck.add(new Card(i,j,new ImageIcon("src/hw10/pictures/"+j+"s.jpg"),CARDBACK));
								break;
					case 2: deck.add(new Card(i,j,new ImageIcon("src/hw10/pictures/"+j+"c.jpg"),CARDBACK));
								break;
					case 3: deck.add(new Card(i,j,new ImageIcon("src/hw10/pictures/"+j+"h.jpg"),CARDBACK));
								break;
					case 4: deck.add(new Card(i,j,new ImageIcon("src/hw10/pictures/"+j+"d.jpg"),CARDBACK));
								break;
				}
				
			}
		}
		return new CardPile(deck);
	}
	
	/**
	 * shuffle's the CardPile's ArrayList<Card> simulating a deck shuffle
	 */
	public void shuffle()
	{
		Collections.shuffle(getCardlist());
	}
	
	/**
	 * clears the Deck of any Cards, builds a new one, and shuffles
	 */
	public void reshuffle()
	{
		getCardlist().clear();
		addToBottom(buildDeck());
		shuffle();
	}
		
	/**
	 * removes half the deck and returns it
	 * @return the top half of the deck
	 */
	public CardPile cut()
	{
		return removeFromTop(26);
	}
	

	
	public static void main(String[]args)
	{
		Deck dec = new Deck(false);
		System.out.println(dec);
		
		System.out.println("shuffling...");
		dec.shuffle();
		
		System.out.println(dec);
		
		System.out.println("draw a card from the deck: "+ dec.removeFromTop(1)
								+"\ndraw 3 cards from the deck: "+ dec.removeFromTop(3)
								+"\n"+dec
								+"\nthere are "+dec.cardsLeft()+" cards left in the deck");
		
		System.out.println("reshuffling all cards...");
		
		dec.reshuffle();
		
		System.out.println(dec+"\nthere are "+dec.cardsLeft()+" cards left in the deck");
		
	}
}