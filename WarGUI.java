package hw10;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WarGUI extends JFrame
{
	private WarGame game;
	private JPanel layout = new JPanel(new GridBagLayout());
	private GridBagConstraints c, p1HandC, titleC, p1CardC, warStack1C, warStack2C, p2HandC, p2CardC, flipButtonC, turnLabelC, blank2C;
	private JLabel p1Hand, p2Hand, title, p1Card, p2Card, warStack1, warStack2, turnLabel, blank2;
	private JButton flipButton;
	
	private boolean shortGame;
	private int turns;
	
	public WarGUI()
	{
		super("WAR GAME");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		game = new WarGame();
		turns = 0;		
		shortGame = false;
		
		p1Hand = new JLabel(game.getP1().getCard(0).getBack(), JLabel.CENTER);
		p2Hand = new JLabel(game.getP2().getCard(0).getBack(), JLabel.CENTER);
		title = new JLabel("war the card game",JLabel.CENTER);
		p1Card = new JLabel("",new ImageIcon("src/hw10/pictures/games.jpg"),JLabel.CENTER);
		p1Card.setHorizontalAlignment(JLabel.RIGHT);
		p2Card = new JLabel("",new ImageIcon("src/hw10/pictures/games.jpg"),JLabel.CENTER);
		warStack1 = new JLabel("",JLabel.CENTER);
		warStack2 = new JLabel("",JLabel.CENTER);
		turnLabel = new JLabel(""+turns,JLabel.CENTER);
		blank2 =new JLabel(new ImageIcon("src/hw10/pictures/games.jpg"));
		flipButton  = new JButton("flip");
		flipButton.addActionListener(new ButtonListener());
		 
		setConstraints();
		
		layout.add(p1Hand, p1HandC);
		layout.add(p2Hand, p2HandC);
		layout.add(title, titleC);
		layout.add(p1Card, p1CardC);
		layout.add(p2Card, p2CardC);
		layout.add(flipButton, flipButtonC);
		layout.add(warStack1, warStack1C);
		layout.add(warStack2, warStack2C);
		layout.add(turnLabel, turnLabelC);
//		layout.add(blank2, blank2C);
		
		add(layout);
		pack();
		setResizable(false);
		setVisible(true);
		
	}
	
	public void setConstraints()
	{
		c = new GridBagConstraints();		
		
		p1HandC = new GridBagConstraints();
		p1HandC.gridx=0;
		p1HandC.gridy=0;
		p1HandC.gridwidth=2;
		p1HandC.ipadx = 3;
		//p1HandC.ipady = 3;
		p1HandC.weightx=.3;

		titleC = new GridBagConstraints();
		titleC.gridx = 2;
		titleC.gridwidth = 2;
		titleC.fill = GridBagConstraints.HORIZONTAL;
		titleC.ipadx = 20;

		p1CardC = new GridBagConstraints();
		p1CardC.gridx = 0;
		p1CardC.gridy = 1;
		p1CardC.gridwidth = 1;
		p1CardC.fill = GridBagConstraints.NONE;
		p1CardC.insets = new Insets(2,3,2,3);
		//p1CardC.ipady = 3;
		p1CardC.anchor=GridBagConstraints.LINE_END;
		p1CardC.weightx=.5;
		
		p2CardC = new GridBagConstraints();
		p2CardC.gridx = 1;
		p2CardC.gridy = 1;
		p2CardC.gridwidth = 1;
		p2CardC.fill = GridBagConstraints.NONE;
		p2CardC.insets = new Insets(2,3,2,3);
		//p2CardC.ipadx = 3;
		//p2CardC.ipady = 3;
		
		warStack1C = new GridBagConstraints();
		warStack1C.gridx = 2;
		warStack1C.gridy = 1;
		p2CardC.insets = new Insets(2,3,2,3);
		
		
		warStack2C = new GridBagConstraints();
		warStack2C.gridx = 3;
		warStack2C.gridy = 1;
		p2CardC.insets = new Insets(2,3,2,3);
		
		
		//p2HandC = (GridBagConstraints) p1HandC.clone();
		p2HandC = new GridBagConstraints();
		p2HandC.gridx = 0;
		p2HandC.gridy = 2;
		p2HandC.gridwidth = 2;
		p2HandC.ipadx = 3;
		//p2HandC.ipady = 3;
		p2HandC.weightx = .5;
		
		flipButtonC = new GridBagConstraints();
		flipButtonC.gridx=2;
		flipButtonC.gridy=2;
		flipButtonC.fill = GridBagConstraints.HORIZONTAL;


		
		turnLabelC = new GridBagConstraints();
		turnLabelC.gridx=3;
		turnLabelC.gridy=2;
//		
//		blank2C = new GridBagConstraints();
//		blank2C.gridx=4;
//		blank2C.gridy=1;
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			turns++;
			if(!game.getWinner())
			{
				turnLabel.setText(""+turns);
				CardPile flip = game.flip();
				if(flip!=null)
				{
					if(turns%2==0)
						title.setText("life during war time");
					else
						title.setText("war of the worlds");
					p1Card.setIcon(flip.getCard(0).getFront());
					p2Card.setIcon(flip.getCard(1).getFront());
					if(game.checkWar()&&!game.getWinner())
					{
						p1Card.setIcon(Deck.CARDBACK);
						p2Card.setIcon(Deck.CARDBACK);
						title.setText("war city");
						
						flip.addToTop(game.war());
						warStack1.setIcon(flip.getCard(0).getFront());
						warStack2.setIcon(flip.getCard(1).getFront());
						
					}
					else
					{
						warStack1.setIcon(new ImageIcon());
						warStack2.setIcon(new ImageIcon());
						flip.removeFromBottom(flip.cardsLeft());
					}
				}
				if(turns%200==0 && turns>0 && game.getP1().cardsLeft()>=10 && game.getP2().cardsLeft()>=10 && shortGame)
				{
					game.getP1().removeFromTop(10);
					game.getP2().removeFromTop(10);
				}
			}
			else if(game.getWhoWins().equals("P1"))
			{
				title.setText("player 1 wins");
				p2Hand.setIcon(new ImageIcon());
				flipButton.setEnabled(false);
			}else if(game.getWhoWins().equals("P2"))
			{
				title.setText("player 2 wins");
				p1Hand.setIcon(new ImageIcon());
				flipButton.setEnabled(false);
			}
				
			
		}
		
	}
	
	
	public static void main(String[] args)
	{
		new WarGUI();

	}

	
	
}
