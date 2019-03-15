package com.game.rpb;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {
	
	public static final int ROCK = 0;
	public static final int PAPER = 1;
	public static final int SCISSOR = 2;
	
	// Main frame
	JFrame frame;
	
	JButton play;
	
	JLabel playerAPoints, playerBPoints, tied;
	JTextArea area;
	
	Random random;
	
	// Constructor
	public Main() {
		
	random = new Random();
	random.setSeed(System.currentTimeMillis());
	
	frame = new JFrame("Rock,Paper,Scissor");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminates the program at the end.
	frame.setPreferredSize(new Dimension(600, 480));
	
	GridLayout layout = new GridLayout(1, 3);
	JPanel panel1 = new JPanel();
	panel1.setLayout(new BorderLayout());
	
	area = new JTextArea();
	area.setEditable(false);
	area.setFont(new Font("Consolas", Font.PLAIN, 12)); // Font style
	
	//JPanel adds a scroll bar to text area
	panel1.add(new JScrollPane(area), BorderLayout.CENTER);
	frame.add(panel1, BorderLayout.CENTER);
	
	JPanel panel2 = new JPanel(); // 
	panel2.setLayout(layout); // set the grid layout we had created earlier
	frame.add
	(panel2, BorderLayout.SOUTH);
	
	JPanel panel3 = new JPanel();
	
	panel3.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	GridLayout layout2 = new GridLayout(1, 4);
	panel3.setLayout(layout2);
	
	playerAPoints = new JLabel("0");
	playerAPoints.setBorder(BorderFactory.createLoweredBevelBorder());
	tied = new JLabel("0");
	tied.setBorder(BorderFactory.createLoweredBevelBorder());
	playerBPoints = new JLabel("0");
	playerBPoints.setBorder(BorderFactory.createLoweredBevelBorder());
	
	panel3.add(new JLabel("Player A: "));
	panel3.add(playerAPoints);
	panel3.add(new JLabel("Ties: "));
	panel3.add(tied);
	panel3.add(new JLabel("Player B: "));
	panel3.add(playerBPoints);
	frame.add(panel3, BorderLayout.NORTH);
	
	play = new JButton("Play");
	play.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			area.setText(null);
			results();
		}
		
	});
	panel2.add(play);
	
	frame.pack();
	frame.setVisible(true);

	}
	
	// Game logic
	// 9 possible outcomes
	// Rock - Rock
	// Rock - Paper
	// Rock - Scissor
	// Paper - Rock
	// Paper - Paper
	// Paper - Scissor
	// Scissor - Rock
	// Scissor - Paper
	// Scissor - Scissor
	
	// Return 0 if computer wins, 1 if player wins, and 2 if tied.
	
	public int simulation() {
		int pAChoice = PAPER;
		int pBChoice = random.nextInt(3);
		//System.out.println(pBChoice);
		if (pAChoice == pBChoice) { // Tie
			return 0;
		} else if (pBChoice == SCISSOR) {
			return 1; // Player B wins
		} else if ( pBChoice == ROCK) {
			return 2; // Player A wins
		} else {
			return -1; // Invalid input
		}
	}
	
	public void results() {
		int r;
		int pAWins = 0;
		int pBWins = 0;
		int ties = 0;
		for (int i = 0; i < 100; i++) {
			r = simulation();
			if (r == 1) {
				pBWins++;
			} else if (r == 2) {
				pAWins++;
			} else {
				ties++;
			}
		}
		area.append("\n Player A wins " + pAWins + " of 100 games." );
		area.append("\n Player B wins " + pBWins + " of 100 games." );
		area.append("\n Tie: " + ties + " of 100 games." );
		if(pAWins > pBWins) {
			area.append("\n Winner is: Player A (" + pAWins + " to " + pBWins + ")" );
			playerAPoints.setText(String.valueOf(Integer.parseInt(playerAPoints.getText()) + 1 ));
		} else if(pBWins > pAWins) {
			area.append("\n Winner is: Player B (" + pBWins + " to " + pAWins + ")" );
			playerBPoints.setText(String.valueOf(Integer.parseInt(playerBPoints.getText()) + 1 ));
		} else {
			area.append("\n Tied Game! How Amazing!");
			tied.setText(String.valueOf(Integer.parseInt(tied.getText()) + 1 ));
		}
	}
	
	// Main method
	public static void main(String[] args) throws Exception {
		new Main();
	}

}
