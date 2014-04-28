package hw10;

import java.util.Scanner;

public class WarGame
{
	Deck deck;
	CardPile p1, p2;
	CardPile stack;
	boolean winner = false;
	String whoWins;
	static int turns = 0;
	
	public WarGame()
	{
		deck = new Deck(true);
		p1 = deck.cut();
		p2 = deck.cut();		
		
		stack = new CardPile();
	}
	
	public CardPile getP1()
	{
		return p1;
	}
	
	public CardPile getP2()
	{
		return p2;
	}
	
	public Card draw(CardPile hand)
	{
		return hand.removeFromTop(1).getCard(0);
	}
	
	public CardPile draw2(CardPile hand)
	{
		CardPile stack = new CardPile();
		if(hand.cardsLeft()>=2)
		{
			stack.addToTop(draw(hand));
			stack.addToTop(draw(hand));
		}else
		{	
			winner = true;
			//return null;
		}
		return stack;
	}
	
	public void flip()
	{
		if(p1.cardsLeft()==0)
		{
			winner = true;
			whoWins = "P2";
		}
		else if(p2.cardsLeft() == 0)
		{
			winner = true;
			whoWins = "P1";
		}
		else
		{
			Card play1 = draw(p1);
			Card play2 = draw(p2);
			stack.addToTop(new CardPile(play1, play2));
			System.out.println("P1 card: "+play1+" P2 card: "+play2);
			System.out.println("stack: "+stack.cardsLeft());
			
			if(play1.isBiggerThan(play2))
			{
				p1.addToBottom(stack);
				System.out.println("p1 wins battle");
			}
			else if(play2.isBiggerThan(play1))
			{
				p2.addToBottom(stack);
				System.out.println("p2 wins battle");
			}
			else
			{
				System.out.println("war city");
				war(stack);
			}
			
			stack.getCardlist().clear();
			System.out.println("P1 cards: "+p1.cardsLeft());
			System.out.println("P2 cards: "+p2.cardsLeft());
			System.out.println("total: "+(p1.cardsLeft()+p2.cardsLeft())+"\n");
			turns++;
		}

	}
		
	/**
	 * This method accepts a CardPile which subsequent drawn cards are added to and is ultimately added to the bottom of whoever wins the war.
	 * It relies on the boolean winner to keep track if either player runs out during the war.
	 * @param warCards the two drawn cards with the same rank that started the war
	 */
	public void war(CardPile warCards)
	{
		//P1 draws two cards. if this doesn't cause an endgame situation (having 1 or 0 cards entering a war) then P2 gets to draw2
		//if P1 runs out of cards then P2 wins
		warCards.addToTop(draw2(p1));
		System.out.println("P1 cards: "+p1.cardsLeft());
		if(!winner)
		{
			warCards.addToTop(draw2(p2));
			System.out.println("P1 cards: "+p2.cardsLeft());
				
			//P2 might run out of cards during their draw2 so check again to see if anyone's run out
			//if so, P1 wins. if not, check if someone gets the pile.
			if(!winner)
			{
				//if no one has run out of cards, enter a while loop where draw2 is called, which has the potential to decide a winner
				//the most recent card on the pile (index 0) is P2's face up card, and P1's face up card is +2 indices from P2's
				//so, as long as either player doesn't run out of cards and they keep drawing the same rank face up, 
				//they will continue to draw2 from their hands and add them to the warCards pile
				if(warCards.getCard(0).equals(warCards.getCard(2)) && !winner)
				{
					System.out.println("more war");
					war(warCards);
				}
				else
				{
					//so now if there's no more equal ranking face up cards, 
					//check if P2's card is bigger-- if so, P2 gets the pile and the method exits
					//if not, check if P1's card is bigger. if so, P1 gets the pile and the method exits
					if(warCards.getCard(0).isBiggerThan(warCards.getCard(2)))
					{
						p2.addToBottom(warCards);
						System.out.println("P2 wins the war and gets "+warCards.cardsLeft()+" cards");
					}
					else if(warCards.getCard(2).isBiggerThan(warCards.getCard(0)))
					{
						p1.addToBottom(warCards);
						System.out.println("P1 wins the war and gets "+warCards.cardsLeft()+" cards");
					}
				}
			}
			else
				whoWins = "P1";
		}
		else
			whoWins = "P2";
	}
	
	public boolean getWinner()
	{
		return winner;
	}
	
	public String getWhoWins()
	{
		return whoWins;
	}
	
	
	public static void main(String[] args)
	{
		WarGame game = new WarGame();
		Scanner scan = new Scanner(System.in);
		
		while(!game.getWinner())
		{
			//for(int i=0; i<40; i++)
				game.flip();
			scan.nextLine();
		}
		System.out.println(turns);

	}

}
