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
   
   /**
    * initializes a Card object with a suit and rank and null icon values
    * @param sui int representing the suit
    * @param ran int representing the rank, 1 is ace, 11-13 are faces
    */
   public Card(int sui, int ran)
   {
      suit = sui;
      rank = ran;
      front = null;
      back = null;
   }
   
   /**
    * initializes a Card with a suit, rank, and ImageIcons for the front and back of the Card
    * @param sui int representing the suit
    * @param ran int representing the rank, 1 is ace, 11-13 are faces
    * @param fron reference to an ImageIcon of the front of the Card
    * @param bac reference to an ImageIcon of the back of the Card
    */
   public Card(int sui, int ran, ImageIcon fron, ImageIcon bac)
   {
   	suit = sui;
   	rank = ran;
   	front = fron;
   	back = bac;
   }
   
   /**
    * accessor for suit field
    * @return suit value of the Card
    */
   public int getSuit()
   {
      return suit;
   }

   /**
    * accessor for rank field
    * @return rank value of the Card
    */
   public int getRank()
   {
      return rank;
   }
   
   /**
    * accessor for front field
    * @return ImageIcon on the front of the Card
    */
   public ImageIcon getFront()
   {
   	return front;
   }

   /**
    * accessor for back field
    * @return ImageIcon on the back of the Card
    */
   public ImageIcon getBack()
   {
   	return back;
   }
   
   /**
    * converts the Card object into a String formatted "RANK OF SUIT"
    */
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
   
   /**
    * checks if this Card is equal in rank to an otherCard
    * @param otherCard a Card object to be compared
    * @return true if they are equal in rank
    */
   public boolean equals(Card otherCard)
   {
      return (rank == otherCard.getRank());
   }
   
   /**
    * checks if this Card is equal in both rank and suit to an otherCard
    * @param otherCard Card object to be comapared
    * @return true if they are equal in both rank and suit
    */
   public boolean equalsSuit(Card otherCard)
   {
      return ((rank == otherCard.getRank())&&(suit == otherCard.getSuit()));
   }
   
   /**
    * checks if this card is bigger (ace is biggest) than an otherCard
    * @param otherCard Card object to be compared
    * @return true if this Card's rank is bigger
    */
   public boolean isBiggerThan(Card otherCard)
   {
   	return (rank > otherCard.getRank() && otherCard.getRank() > 1) || (rank==1 && otherCard.getRank() > 1);
   }

}