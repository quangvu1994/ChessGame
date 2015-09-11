/*
 * GViewControl.java
 * 
 * Versions:
 * $Id:
 * 
 * Revisions:
 * $Log:
 * 
 */

// Any imports go here:
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * GViewControl class display the GUI version of the chess solitaire game.
 * @author Quang Vu
 *
 */

public class GViewControl extends JFrame implements Observer {
	
	// Fields or variable members of the class
	private Chess model;
	private ArrayList<ArrayList<JButton>> board = new ArrayList<ArrayList<JButton>>();
	private int click = 0;
	private int row1;
	private int col1;
	private int row2;
	private int col2;
	public ArrayList<ArrayList<ChessPieces>> snapShotBoard;
	private int numMove = 0;
	
	/**
	 * Constructor --- Initialize all variables, and set up the GUI.
	 * @param name --- name of the GUI
	 * @param c  --- Model or the chess puzzle.
	 */
	public GViewControl(String name, Chess c){
		super(name);
		this.model = c;
		this.model.addObserver(this);
		snapShotBoard = model.getStart();
		setLayout(new BorderLayout(0,0));
		setSize(500,500);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JTextArea display = new JTextArea();
		display.setText("Total Move: " + numMove + "  ----- Please select a chess piece-----");
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(model.row, model.col, 1, 1));
		for(int i = 0; i < model.row; i++){
			ArrayList<JButton> button = new ArrayList<JButton>();
			for(int n = 0; n < model.col; n++){
				final ChessButton pieces = new ChessButton(i, n);
				if(!model.currentConfig.get(i).get(n).getID().equals("Dot")){
					pieces.setText(model.currentConfig.get(i).get(n).getID());					
				}
				pieces.setBackground(Color.WHITE);
				button.add(pieces);
				grid.add(pieces);
				pieces.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// if it the board is not the goal, it is available for next step.
						if(!model.isGoal(snapShotBoard)){
							click++;
							//get button position
							//find piece at that position
							numMove++;
							if(click == 1){
								display.setText("Total move :" + numMove + " ------ Please select your your move ------");
								row1 = pieces.getRowPosition();
								col1 = pieces.getColPosition();
								
							}
							if( click == 2){
								display.setText("Total move :" + numMove + " ------ Please select a chess piece  ------");
								//get second position
								row2 = pieces.getRowPosition();
								col2 = pieces.getColPosition();
								// Checking if the second move is valid.
								boolean found = model.getCurrentConfig(row1, col1, row2, col2);
								// if not a valid move, show a invalid message for the player/
								if(!found){
									JOptionPane.showMessageDialog(null, "INVALID MOVE");
								}
								click = 0;
							}
						}
						// If the board is the goal, show a message that the user have won the game.
						else{
							JOptionPane.showMessageDialog(null, "YOU HAVE WON!");
						}
					}
				});
			}
			board.add(button);
		}
		// helper panel including all helper functions of the game.
		JPanel helper = new JPanel();
		helper.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		
		// First helper function is to reset the game.
		JButton reset = new JButton("Reset");
		reset.setBackground(Color.GRAY);
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				numMove = 0;
				display.setText("Total Move: " + numMove + "  ----- Please select a chess piece-----");
				model.reset();
			}
		});
		// Second helper function is to find the next best move.
		JButton nextMove = new JButton("Next Move");
		nextMove.setBackground(Color.GRAY);
		nextMove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!model.isGoal(snapShotBoard)){
					model.nextMove(model, snapShotBoard);					
				}
				else{
					JOptionPane.showMessageDialog(null, "YOU HAVE WON!");
				}
				if(model.solution == false){
					JOptionPane.showMessageDialog(null, "NO SOLUTION");
				}
					
			}
		});
		
		// Third helper function is to quit the game.
		JButton quit = new JButton("Quit");
		quit.setBackground(Color.GRAY);
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		// Fourth helper function is the display the rule of the game.
		JButton displayRule = new JButton("Display Rule");
		displayRule.setBackground(Color.GRAY);
		displayRule.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "Please checkout this website for the rule: http://www.thinkfun.com/solitairechess");
			}
		});
		helper.add(displayRule);
		helper.add(reset);
		helper.add(nextMove);
		helper.add(quit);
		
		// Organize the GUI.
		add(display, BorderLayout.NORTH);
		add(grid, BorderLayout.CENTER);
		add(helper, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	
	/**
	 * update function updates the current state of board.
	 */
	@Override
	public void update(Observable t, Object o) {
		
		snapShotBoard = model.currentConfig;
		for(int x = 0; x < model.row; x++){
			for(int y = 0; y < model.col;  y++ ){
				if(!model.currentConfig.get(x).get(y).getID().equals("Dot")){
					board.get(x).get(y).setText(model.currentConfig.get(x).get(y).getID());
				}
				else{
					board.get(x).get(y).setText("");
				}
			}
		}
		
	}
	
	public static void main(String args[]){
		// Checking the correct input agrument.
		if(args.length != 1){
			System.out.println("usage: java Chess input-file");
			return;
		}
		Chess c = new Chess(args[0]);
		// Quit the game if there was no file.
		if(c.currentConfig.size() ==0){
			return;
		}
		Solver<ArrayList<ArrayList<ChessPieces>>> s = new Solver<ArrayList<ArrayList<ChessPieces>>>(c);
		ArrayList<ArrayList<ArrayList<ChessPieces>>> result = s.solve();
		GViewControl GUI = new GViewControl("Solitaire Chess / Quang Vu / qdv2130@rit.edu", c);
	}
}
