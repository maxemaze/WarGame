/*
 * Maxe Mazelis
 * CS 110
 * a game of war. it has a Deck, two hands for the players, and a stack which contains any cards not in a hand.
 * the three principal methods are flip(), checkWar(), and war()
 */
public class WarGame
{
	private Deck deck;
	private CardPile p1, p2;
	private CardPile stack;
	private boolean winner = false;
	private boolean inWar = false;
	private String whoWins;
	
	/**
	 * default constructor which creates a new shuffled Deck and cuts it, giving half to p1 and half to p2
	 * an empty CardPile stack is set aside to hold all cards out of the player's hands
	 */
	public WarGame()
	{
		deck = new Deck(true);
		p1 = deck.cut();
		p2 = deck.cut();		
		
		stack = new CardPile();
	}
	
	/**
	 * takes a card off the top of the passed CardPile
	 * @param hand the CardPile to be drawn from
	 * @return the Card on top of hand
	 */
	public Card draw(CardPile hand)
	{
		return hand.removeFromTop(1).getCard(0);
	}
	
	/**
	 * first checks either player has run out of cards or is about to enter a war without enough cards.
	 * then draws a card from p1 and p2 and puts them into a CardPile, which is added to the stack of cards in the middle.
	 * @return null if there is a winner, or the two newly flipped card if the game is continuing
	 */
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
			CardPile drawn = new CardPile(draw(p1), draw(p2));
			stack.addToTop(drawn);
			return drawn;
		}
		return null;
	}
	
	/**
	 * checks if either Cards on top of the stack are bigger than the other. 
	 * if so, the winning player gets the stack added to the bottom of their hand.
	 * else, go to war
	 * @return true if neither top cards are bigger, false if a player has won the battle
	 */
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
		}
	}
	
	/**
	 * first copies the stack into another CardPile, then flips two cards and adds them to it.
	 * @return
	 */
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

		return warCards;
	}
	
	/**
	 * accessor for p1
	 * @return the CardPile for player 1
	 */
	public CardPile getP1()
	{
		return p1;
	}
	
	/**
	 * accessor for p2
	 * @return the CardPile for player 2
	 */
	public CardPile getP2()
	{
		return p2;
	}
	
	/**
	 * accessor for winner field
	 * @return winner
	 */
	public boolean getWinner()
	{
		return winner;
	}
	
	/**
	 * accessor for whoWins field
	 * @return the winner player
	 */
	public String getWhoWins()
	{
		return whoWins;
	}
}
