package hw10;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WarGUI extends JFrame
{
	private WarGame game;
	private JPanel layout = new JPanel(new GridLayout(3,3,2,2));
	private JLabel p1Cards, p2Cards, title, warCity, stack, warStack, blank, blank2;
	private JButton flip = new JButton("flip");
	
	
	public WarGUI()
	{
		super("WAR GAME");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		game = new WarGame();
		
		p1Cards = new JLabel("PLAYER 1", game.getP1().getCard(0).getBack(), JLabel.CENTER);
		p2Cards = new JLabel("PLAYER 2", game.getP2().getCard(0).getBack(), JLabel.CENTER);
		warCity = new JLabel();
		title = new JLabel("war the card game");
		stack = new JLabel();
		warStack = new JLabel();
		warStack.setBorder();
		blank = new JLabel(new ImageIcon("pix/games.png"));
		blank2 =new JLabel(new ImageIcon("pix/games.png"));
		
		
		layout.add(p1Cards);
		layout.add(warCity);
		layout.add(title);
		
		layout.add(stack);
		layout.add(warStack);
		layout.add(blank);
		
		layout.add(p2Cards);
		layout.add(flip);
		layout.add(blank2);
		
		add(layout);
		pack();
		setVisible(true);
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			if(!game.getWinner())
			{
				game.flip();
				blank.
			}
			else if(game.getWhoWins().equals("P1"))
			{
				warCity = new JLabel("player 1 wins");
				flip.setEnabled(false);
			}else if(game.getWhoWins().equals("P2"))
			{
				warCity = new JLabel("player 2 wins");
				flip.setEnabled(false);
			}
				
			
		}
		
	}
	
	
	public static void main(String[] args)
	{
		new WarGUI();

	}

	
	
}
