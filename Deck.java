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
	//private ArrayList<Card> deck;

	public Deck()
	{
		super();
		addToBottom(buildDeck());
	}
	/**
	 *  constructor that initializes an ArrayList of Cards.
	 * outer loop iterates 4 times for each suit, inner iterates 13 for each rank.
	 * SPADES - CLUBS - HEARTS - DIAMONDS
	 * aces-kings
	 */
	public Deck(boolean shuffle)
	{
		super();
		
		addToBottom(buildDeck());
		
		if(shuffle)
			shuffle();
	}
	
	public CardPile buildDeck()
	{
		ArrayList<Card> deck = new ArrayList<Card>();
		//for(int i=1; i <= 4; i++)
		for(int i=1; i<2; i++)
		{
			//for(int j=1; j <= 13; j++)
			for(int j=1; j<=5; j++)
			{
				switch(i)
				{
					case 1: deck.add(new Card(i,j,new ImageIcon("pix/"+j+"s.jpg"),new ImageIcon("pix/back.jpg")));
								break;
					case 2: deck.add(new Card(i,j,new ImageIcon("pix/"+j+"c.jpg"),new ImageIcon("pix/back.jpg")));
								break;
					case 3: deck.add(new Card(i,j,new ImageIcon("pix/"+j+"h.jpg"),new ImageIcon("pix/back.jpg")));
								break;
					case 4: deck.add(new Card(i,j,new ImageIcon("pix/"+j+"d.jpg"),new ImageIcon("pix/back.jpg")));
								break;
				}
				deck.add(new Card(i,j));
			}
		}
		return new CardPile(deck);
	}
	
	
	public void shuffle()
	{
		Collections.shuffle(getCardlist());
	}
	
	public void reshuffle()
	{
		getCardlist().clear();
		addToBottom(buildDeck());
		shuffle();
	}
			
	public CardPile cut()
	{
		return removeFromTop(cardsLeft()/2);
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