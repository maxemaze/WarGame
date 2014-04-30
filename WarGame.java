package hw10;

import java.util.Scanner;

public class WarGame
{
	private Deck deck;
	private CardPile p1, p2;
	private CardPile stack;
	private boolean winner = false;
	private boolean inWar = false;
	private String whoWins;
	
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
	
	public CardPile flip()
	{
		if(p1.cardsLeft()==0 || (inWar && p1.cardsLeft()<=1))
		{
			winner = true;
			whoWins = "P2";
		}
		else if(p2.cardsLeft()==0 || (inWar && p2.cardsLeft()<=1))
		{
			winner = true;
			whoWins = "P1";
		}
		else
		{
			//if(!inWar)
			//	stack.getCardlist().clear();
			Card play1 = draw(p1);
			Card play2 = draw(p2);
			CardPile drawn = new CardPile(play1, play2);
			stack.addToTop(drawn);
			return drawn;
		}
		return null;
	}
	
	public boolean checkWar()
	{
		Card cardP1 = stack.getCard(1);
		Card cardP2 = stack.getCard(0);
		System.out.println("P1 card: "+cardP1+" \nP2 card: "+cardP2);
		
		if(cardP1.isBiggerThan(cardP2))
		{
			p1.addToBottom(stack);
			if(!inWar)
				System.out.print("  p1 wins battle.");
			else
				System.out.print("  p1 wins the war!");
			System.out.println(" p1 gets "+stack.cardsLeft()+" cards");
			System.out.println(" P1 cards: "+p1.cardsLeft()+"\n P2 cards: "+p2.cardsLeft()+"\n total: "+(p1.cardsLeft()+p2.cardsLeft()));	
			stack.getCardlist().clear();
			inWar = false;
			return inWar;
		}
		else if(cardP2.isBiggerThan(cardP1))
		{
			p2.addToBottom(stack);
			if(!inWar)
				System.out.print("  p2 wins battle.");
			else
				System.out.print("  p2 wins the war!");
			System.out.println(" p2 gets "+stack.cardsLeft()+" cards");
			System.out.println(" P1 cards: "+p1.cardsLeft()+"\n P2 cards: "+p2.cardsLeft()+"\n total: "+(p1.cardsLeft()+p2.cardsLeft()));
			stack.getCardlist().clear();
			inWar = false;
			return inWar;
		}
		else
		{
			inWar = true;
			System.out.println("war city");
			return inWar;
			//stack.addToTop(trueWar());
		}
	}
	
	public CardPile war()
	{
		inWar = true;
		CardPile warCards =  new CardPile(stack);
		CardPile tempPile = flip();
		if(tempPile!=null)
			warCards.addToTop(tempPile);
		
		tempPile = flip();
		if(tempPile!=null)
			warCards.addToTop(tempPile);
		
		System.out.println(" P1 cards: "+p1.cardsLeft()+"\n P2 cards: "+p2.cardsLeft()+"\n total: "+(p1.cardsLeft()+p2.cardsLeft()));

		inWar = false;
		
		return warCards;
	}
	
	public CardPile fakeFlip()
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
			if(!inWar)
			{
				stack.getCardlist().clear();
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
					stack.addToTop(fakeWar());
				}
				

				System.out.println("P1 cards: "+p1.cardsLeft());
				System.out.println("P2 cards: "+p2.cardsLeft());
				System.out.println("total: "+(p1.cardsLeft()+p2.cardsLeft())+"\n");
			}
			else
			{
				
			}
		}

		return stack;
	}
		
	/**
	 * This method accepts a CardPile which subsequent drawn cards are added to and is ultimately added to the bottom of whoever wins the war.
	 * It relies on the boolean winner to keep track if either player runs out during the war.
	 * @param warCards the two drawn cards with the same rank that started the war
	 */
	public CardPile fakeWar()
	{
		inWar = true;
		//P1 draws two cards. if this doesn't cause an endgame situation (having 1 or 0 cards entering a war) then P2 gets to draw2
		//if P1 runs out of cards then P2 wins
		stack.addToTop(draw2(p1));
		System.out.println("P1 cards: "+p1.cardsLeft());
		if(!winner)
		{
			stack.addToTop(draw2(p2));
			System.out.println("P2 cards: "+p2.cardsLeft());
				
			//P2 might run out of cards during their draw2 so check again to see if anyone's run out
			//if so, P1 wins. if not, check if someone gets the pile.
			if(!winner)
			{
				//if no one has run out of cards, enter a while loop where draw2 is called, which has the potential to decide a winner
				//the most recent card on the pile (index 0) is P2's face up card, and P1's face up card is +2 indices from P2's
				//so, as long as either player doesn't run out of cards and they keep drawing the same rank face up, 
				//they will continue to draw2 from their hands and add them to the warCards pile
				if(stack.getCard(0).equals(stack.getCard(2)) && !winner)
				{
					System.out.println("more war");
					fakeFlip();
				}
				else
				{
					//so now if there's no more equal ranking face up cards, 
					//check if P2's card is bigger-- if so, P2 gets the pile and the method exits
					//if not, check if P1's card is bigger. if so, P1 gets the pile and the method exits
					if(stack.getCard(0).isBiggerThan(stack.getCard(2)))
					{
						p2.addToBottom(stack);
						System.out.println("P2 wins the war and gets "+stack.cardsLeft()+" cards");
					}
					else if(stack.getCard(2).isBiggerThan(stack.getCard(0)))
					{
						p1.addToBottom(stack);
						System.out.println("P1 wins the war and gets "+stack.cardsLeft()+" cards");
					}
				}
			}
			else
				whoWins = "P1";
		}
		else
			whoWins = "P2";
		inWar = false;
		return stack;
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
		//Scanner scan = new Scanner(System.in);
		int turns = 0;
		do
		{
			if(game.flip()!=null)
			{
				while(game.checkWar() && !game.getWinner())
				{
					game.war();
					game.flip();
				}
			}
			turns++;
		}while(!game.getWinner() && turns <=500);
		
//		while(!game.getWinner())
//		{
//			//for(int i=0; i<40; i++)
//				game.flip();
//			scan.nextLine();
//		}
		System.out.println(game.getWhoWins()+" won in "+turns+" turns");

	}

}
