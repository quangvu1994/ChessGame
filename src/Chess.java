/*
 * Chess.java
 * 
 * Versions:
 * $Id:
 * 
 * Revisions:
 * $Log:
 * 
 */

// Any imports go here
import java.util.ArrayList;
import java.util.Observable;
import java.io.*;

/**
 * Chess class implements the chess puzzle. 
 * @author Quang Vu
 */

public class Chess extends Observable implements Puzzle<ArrayList<ArrayList<ChessPieces>>>{
	// Fields and variable memebers
	public int row;
	public int col;
	private ArrayList<ArrayList<ChessPieces>> board;
	public  ArrayList<ArrayList<ChessPieces>> currentConfig;
	private ArrayList<ArrayList<ChessPieces>> twinBoard;
	public boolean solution = true;

	/**
	 * Constructor -- Initialize all members/ class variables.
	 * 			   -- Read a file to 
	 * @param fileName
	 */
	public Chess(String fileName){
		createBoard(fileName);
		this.currentConfig = board;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * createBoard -- read the file and create the board.
	 * @param fileName
	 */
	public void createBoard(String fileName){

		board = new ArrayList<ArrayList<ChessPieces>>();
		twinBoard = new ArrayList<ArrayList<ChessPieces>>();
		int rVal = 0;
		int cVal = 0;
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String value = in.readLine();
			String[] tokens = value.split("\\s+");
			row = Integer.parseInt(tokens[0]);
			col = Integer.parseInt(tokens[1]);
			
			String a;
			while((a = in.readLine()) != null){
				String[] tokens2 = a.split("\\s+");
				ArrayList<ChessPieces> chessPieces = new ArrayList<ChessPieces>();
				for(String str : tokens2){
					if(str.equals("B")){
						Bishop b = new Bishop(rVal, cVal);
						chessPieces.add(b);
					}
					else if(str.equals("K")){
						King k = new King(rVal, cVal);
						chessPieces.add(k);
					}
					else if(str.equals("N")){
						Knight n = new Knight(rVal, cVal);
						chessPieces.add(n);
					}
					else if(str.equals("P")){
						Pawn p = new Pawn(rVal, cVal);
						chessPieces.add(p);
					}
					else if(str.equals("R")){
						Rock r = new Rock(rVal, cVal);
						chessPieces.add(r);
					}
					else if(str.equals("Q")){
						Queen q = new Queen(rVal, cVal);
						chessPieces.add(q);
					}
					else{
						Dot d = new Dot(rVal, cVal);
						chessPieces.add(d);
					}
					cVal += 1;
				}
				board.add(chessPieces);
				twinBoard.add(chessPieces);
				cVal = 0;
				rVal+= 1;
			}
			in.close();
		}catch(FileNotFoundException e){
			System.out.println("{input-file} not found.");
		}catch(IOException e){
			e.getStackTrace();
		}
		
	}
	/**
	 * Check the config if that is the goal or not.
	 */
	@Override
	public boolean isGoal(ArrayList<ArrayList<ChessPieces>> config) {
		int count = 0;
		for(ArrayList<ChessPieces> a : config){
			for(ChessPieces c : a){
				if(!c.getID().equals("Dot")){
					count += 1;
				}
			}
		}
		if(count == 1 || count == 0){
			setChanged();
			notifyObservers();
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * getNeighbor will get all possible neighbors of the config.
	 */
	@Override
	public ArrayList<ArrayList<ArrayList<ChessPieces>>> getNeighbor(ArrayList<ArrayList<ChessPieces>> config) {
		
		ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor = new ArrayList<ArrayList<ArrayList<ChessPieces>>>();
		
		for(int x = 0; x < row; x++){
			for(int y = 0; y < col; y++){
			// Checking for what type is the chess pieces for a certain movement.
				if(config.get(x).get(y).getID().equals("Queen")){
					neighbor.addAll(config.get(x).get(y).neighbor(x, y, config));
				}
				else if(config.get(x).get(y).getID().equals("King")){
					neighbor.addAll(config.get(x).get(y).neighbor(x, y, config));
				}	
				else if(config.get(x).get(y).getID().equals("Knight")){
					neighbor.addAll(config.get(x).get(y).neighbor(x, y, config));
					
				}else if (config.get(x).get(y).getID().equals("Pawn")){
					neighbor.addAll(config.get(x).get(y).neighbor(x,y,config));
					
				}else if(config.get(x).get(y).getID().equals("Rock")){
					neighbor.addAll(config.get(x).get(y).neighbor(x, y, config));
						
				}else if (config.get(x).get(y).getID().equals("Bishop")){
					neighbor.addAll(config.get(x).get(y).neighbor(x, y, config));
				}else{
					continue;
				}
			}	
		}
			return neighbor;
	}
	
	/**
	 * getStart will get the starting config.
	 * @return board config.
	 */
	@Override
	public ArrayList<ArrayList<ChessPieces>> getStart() {
		return board;
	}
	
	/**
	 * getCurrentConfig check if the second click of the user is valid or not, and update the current board
	 * for the GUI.
	 * @param row1 - First click row coordinate.
	 * @param col1 - First click col coordinate.
	 * @param row2 - Second click row coordinate.
	 * @param col2 - Second click col coordinate.
	 * @return true or false.
	 */
	public boolean getCurrentConfig(int row1, int col1, int row2, int col2){
		
		boolean found = false;
		ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor = new ArrayList<ArrayList<ArrayList<ChessPieces>>>();	
		// Checking what kind of piece at the the first click's coordinate
		if(currentConfig.get(row1).get(col1).getID().equals("Bishop")){
			ChessPieces c = currentConfig.get(row1).get(col1);
			// Get all possible neighbor boards / possible move.
			neighbor.addAll(currentConfig.get(row1).get(col1).neighbor(row1, col1, currentConfig));
			// Check all the possible move/ possible boards.
			// If the coordinate of the second click is one of the possible move ---- >>> It is valid.
			for(ArrayList<ArrayList<ChessPieces>> possibleBoards : neighbor){
				if(possibleBoards.get(row2).get(col2).equals(c)){
					currentConfig = possibleBoards;
					found = true;
					break;
				}
			}
		}
		else if(currentConfig.get(row1).get(col1).getID().equals("King")){
			ChessPieces c = currentConfig.get(row1).get(col1);
			// Get all possible neighbor boards / possible move.
				neighbor.addAll(currentConfig.get(row1).get(col1).neighbor(row1, col1, currentConfig));
				// Check all the possible move/ possible boards.
				// If the coordinate of the second click is one of the possible move ---- >>> It is valid.
				for(ArrayList<ArrayList<ChessPieces>> possibleBoards : neighbor){
					if(possibleBoards.get(row2).get(col2).equals(c)){
						currentConfig = possibleBoards;
						found = true;
						break;
					}
				}
		}
		else if(currentConfig.get(row1).get(col1).getID().equals("Rock")){
			ChessPieces c = currentConfig.get(row1).get(col1);
			// Get all possible neighbor boards / possible move.
				neighbor.addAll(currentConfig.get(row1).get(col1).neighbor(row1, col1, currentConfig));
				// Check all the possible move/ possible boards.
				// If the coordinate of the second click is one of the possible move ---- >>> It is valid.
				for(ArrayList<ArrayList<ChessPieces>> possibleBoards : neighbor){
					if(possibleBoards.get(row2).get(col2).equals(c)){
						currentConfig = possibleBoards;
						found = true;
						break;
					}
				}
		}
		else if(currentConfig.get(row1).get(col1).getID().equals("Pawn")){
			ChessPieces c = currentConfig.get(row1).get(col1);
			// Get all possible neighbor boards / possible move.
				neighbor.addAll(currentConfig.get(row1).get(col1).neighbor(row1, col1, currentConfig));
				// Check all the possible move/ possible boards.
				// If the coordinate of the second click is one of the possible move ---- >>> It is valid.
				for(ArrayList<ArrayList<ChessPieces>> possibleBoards : neighbor){
					if(possibleBoards.get(row2).get(col2).equals(c)){
						currentConfig = possibleBoards;
						found = true;
						break;
					}
				}
		}
		else if(currentConfig.get(row1).get(col1).getID().equals("Knight")){
			ChessPieces c = currentConfig.get(row1).get(col1);
			// Get all possible neighbor boards / possible move.
				neighbor.addAll(currentConfig.get(row1).get(col1).neighbor(row1, col1, currentConfig));
				// Check all the possible move/ possible boards.
				// If the coordinate of the second click is one of the possible move ---- >>> It is valid.
				for(ArrayList<ArrayList<ChessPieces>> possibleBoards : neighbor){
					if(possibleBoards.get(row2).get(col2).equals(c)){
						currentConfig = possibleBoards;
						found = true;
						break;
					}
				}
		}
		else if(currentConfig.get(row1).get(col1).getID().equals("Queen")){
				ChessPieces c = currentConfig.get(row1).get(col1);
				// Get all possible neighbor boards / possible move.
				neighbor.addAll(currentConfig.get(row1).get(col1).neighbor(row1, col1, currentConfig));
				// Check all the possible move/ possible boards.
				// If the coordinate of the second click is one of the possible move ---- >>> It is valid.
				for(ArrayList<ArrayList<ChessPieces>> possibleBoards : neighbor){
					if(possibleBoards.get(row2).get(col2).equals(c)){
						currentConfig = possibleBoards;
						found = true;
						break;
					}
				}
		}
		else{
			
		}
		setChanged();
		notifyObservers();
		return found;
	}
	/**
	 * nextMove get the next best move for the user. Update the current config.
	 * @param model -  Chess puzzle
	 * @param snapShotBoard - a snap shot of the last current board.
	 */
	public void nextMove(Chess model, ArrayList<ArrayList<ChessPieces>> snapShotBoard){
		model.board = snapShotBoard;
		Solver<ArrayList<ArrayList<ChessPieces>>> s = new Solver<ArrayList<ArrayList<ChessPieces>>>(model);
		ArrayList<ArrayList<ArrayList<ChessPieces>>> result = s.solve();
		if(result.size() >= 1){
			currentConfig = result.get(1);
		}
		else{
			solution = false;
		}
		setChanged();
		notifyObservers();
	}
	/**
	 * reset will reset the entire game. By setting the current config equal to the start board. 
	 * 
	 */
	public void reset(){
		currentConfig = twinBoard;
		setChanged();
		notifyObservers();
	}
}