package com.game.rpb;

// imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;



// Main class
public class Main {
	
	public static final int ROCK = 0;
	public static final int PAPER = 1;
	public static final int SCISSOR = 2;
	
	// Main frame
	JFrame frame;
	
	JButton rock, paper, scissor;
	
	JLabel computerPoints, playerPoints;
	JTextArea area;
	
	Random random;
	
	// Constructor
	public Main() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Set System Look And Feel
		
		random = new Random();
		random.setSeed(System.currentTimeMillis());
		
		frame = new JFrame("Rock,Paper,Scissor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminates the program at the end.
		frame.setPreferredSize(new Dimension(300, 480));
		
		GridLayout layout = new GridLayout(1, 3);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		
		area = new JTextArea();
		area.setEditable(false);
		area.setFont(new Font("Consolas", Font.PLAIN, 12)); // Font style
		//area.setText("Hello World");
		
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
		
		computerPoints = new JLabel("0");
		computerPoints.setBorder(BorderFactory.createLoweredBevelBorder());
		playerPoints = new JLabel("0");
		playerPoints.setBorder(BorderFactory.createLoweredBevelBorder());
		
		panel3.add(new JLabel("Computer: "));
		panel3.add(computerPoints);
		panel3.add(new JLabel("Player: "));
		panel3.add(playerPoints);
		frame.add(panel3, BorderLayout.NORTH);
		
		rock = new JButton("Rock");
		rock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rockEvent();
			}
			
		});
		
		paper = new JButton("Paper");
		paper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paperEvent();
			}
			
		});
		
		scissor = new JButton("Scissor");
		scissor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scissorEvent();
			}
			
		});
		panel2.add(rock);
		panel2.add(paper);
		panel2.add(scissor);
		
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
	
	public int winner(int com, int player) {
		if (com == player) { // Check for tie
			return 2;
		}
		if (com == ROCK && player == PAPER) {
			return 1; // player wins
		} else if ( com == PAPER && player == ROCK) {
			return 0;
		} else if (com == SCISSOR && player == PAPER) {
			return 0;
		} else if (com == PAPER && player == SCISSOR ) {
			return 1;
		} else if (com == ROCK && player == SCISSOR ) {
			return 0;
		} else if (com == SCISSOR && player == ROCK ) {
			return 1;
		} else {
			return -1; // Invalid input
		}
	}
	
	public String move2text(int i) {
		switch(i) {
		case ROCK:
			return "Rock";
		case PAPER:
			return "Paper";
		case SCISSOR:
			return "Scissor";
		default:
			return "Invalid move!";
		}
	}
	
	public void randomTurn(int player) {
		int com = random.nextInt(3); // Computer randomly chooses Rock,Paper, or Scissor
		area.append("\n- Computer Chose " + move2text(com) + ".");
		if (winner(com, player) == 0) {
			computerPoints.setText(String.valueOf(Integer.parseInt(computerPoints.getText()) + 1 ));
			area.append("\n Computer Won!");
		} else if (winner(com, player) == 1 ) {
			playerPoints.setText(String.valueOf(Integer.parseInt(playerPoints.getText()) + 1 ));
			area.append("\n You Won!");
		} else {
			area.append("\n Tie.");
		}
	}
	
	public void rockEvent() {
		if (!area.getText().isEmpty()){
			area.append("\n\n- You chose Rock.");
		} else {
			area.append("- You chose Rock.");
		}
		randomTurn(ROCK);
	}
	
	public void paperEvent() {
		if (!area.getText().isEmpty()){
			area.append("\n\n- You chose Paper.");
		} else {
			area.append("- You chose Paper.");
		}
		randomTurn(PAPER);
	}
	
	public void scissorEvent() {
		if (!area.getText().isEmpty()){
			area.append("\n\n- You chose Scissor.");
		} else {
			area.append("- You chose Scissor.");
		}
		randomTurn(SCISSOR);
	}
	
	// Main method
	public static void main(String[] args) throws Exception {
		new Main();
	}
	
}
