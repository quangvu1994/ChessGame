/*
 * Rock.java
 * 
 * Versions:
 * $Id:
 * 
 * Revisions:
 * $Log:
 * 
 */

import java.util.ArrayList;
/**
 * Rock represents the Rock chess piece.
 * @author Quang Vu 
 *
 */

public class Rock extends ChessPieces {
	private String ID;
	
	public Rock(int row, int col){
		super(row, col);
		this.ID = "Rock";
	}
	@Override
	public  ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor(int row, int col, ArrayList<ArrayList<ChessPieces>> board) {
		ArrayList<ArrayList<ArrayList<ChessPieces>>> neighbor = new ArrayList<ArrayList<ArrayList<ChessPieces>>>();
		
		// Move upward
		for(int i = 1; i <= row; i++){
			if(!board.get(row - i).get(col).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row - i).set(col, this);
				deepCopy.get(row).set(col, new Dot(row , col));
				neighbor.add(deepCopy);
				break;
			}
				
		}
		// Move downward
		for(int i = 1; i < (board.size() - row); i++ ){
			if(!board.get(row + i).get(col).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row + i).set(col, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
				break;
			}
		}
		// Move left
		for(int i = 1; i <= col; i++){
			if(!board.get(row).get(col - i).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row).set(col - i, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
				break;
			}
		}
		// Move right
		for(int i = 1; i < (board.get(row).size()) - col; i++){
			if(!board.get(row).get(col + i).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row).set(col + i, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
				break;
			}
		}
			
		return neighbor;
	}
	public String getID() {
		return ID;
	}
	@Override
	public int getRow() {
		return row;
	}
	@Override
	public int getCol() {
		return col;
	}
	
}
