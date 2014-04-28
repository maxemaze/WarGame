package hw10;

import javax.swing.ImageIcon;

public class Card
{

   public static final int SPADES   = 1;
   public static final int CLUBS    = 2;
   public static final int HEARTS   = 3;
   public static final int DIAMONDS = 4;
   
   public static final int ACE      = 1;
   public static final int JACK     = 11;
   public static final int QUEEN    = 12;
   public static final int KING     = 13;
   
   private int rank;
   private int suit;
   private ImageIcon front,back;
   
   public Card(int sui, int ran)
   {
      suit = sui;
      rank = ran;
      front = null;
      back = null;
   }
   
   public Card(int sui, int ran, ImageIcon fron, ImageIcon bac)
   {
   	suit = sui;
   	rank = ran;
   	front = fron;
   	back = bac;
   }
   
   public int getSuit()
   {
      return suit;
   }
   
   public int getRank()
   {
      return rank;
   }
   
   public ImageIcon getFront()
   {
   	return front;
   }
   
   public ImageIcon getBack()
   {
   	return back;
   }
   
   public String toString()
   {
      String cardInfo;
      switch(rank)
      {
      case ACE:   cardInfo = "ACE OF ";
                break;
      case JACK:  cardInfo = "JACK OF ";
                break;
      case QUEEN: cardInfo = "QUEEN OF ";
                break;
      case KING:  cardInfo = "KING OF ";
                break;
      default:    cardInfo = rank+" OF ";
                break;
      }
      
      switch(suit)
      {
      case SPADES:   cardInfo = cardInfo.concat("SPADES");
                   break;
      case CLUBS:    cardInfo = cardInfo.concat("CLUBS");
                   break;
      case HEARTS:   cardInfo = cardInfo.concat("HEARTS");
                   break;
      case DIAMONDS: cardInfo = cardInfo.concat("DIAMONDS");
                   break;
      }
      
      return cardInfo;
   }
   
   public boolean equals(Card otherCard)
   {
      return (rank == otherCard.getRank());
   }
   
   public boolean equalsSuit(Card otherCard)
   {
      return ((rank == otherCard.getRank())&&(suit == otherCard.getSuit()));
   }
   
   public boolean isBiggerThan(Card otherCard)
   {
   	if((rank > otherCard.getRank() && otherCard.getRank() > 1) || (rank==1 && otherCard.getRank() > 1))
   		return true;
   	else
   		return false;
   		//return ((rank > otherCard.getRank()) || (rank == 1 && otherCard.getRank() > 1));
   }

}