/*
 * Queen.java
 * 
 * Versions:
 * $Id:
 * 
 * Revisions:
 * $Log:
 * 
 */

import java.util.*;
/**
 * Queen represents the Queen chess piece.
 * @author Quang Vu 
 *
 */
public class Queen extends ChessPieces{
	private String ID;
	
	public Queen(int row, int col){
		super(row, col);
		this.ID = "Queen";
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
		// Move diagonal toward North East.
		int i = 1;
		int j = 1;
		for( ; ; ){
			if(i > row || j == (board.get(row).size() - col)){
				break;
			}
					
			if(!board.get(row - i).get(col + j).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row - i).set(col + j, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
				break;
			}
			i ++;
			j ++;
		}
		// move diagonal toward North West.
		int i1 = 1;
		int j1 = 1;
		for( ; ; ){
			if( i1 > row || j1 > col){
				break;
			}
					
			if(!board.get(row - i1).get(col - j1).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
						
				deepCopy.get(row - i1).set(col - j1, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
				break;
			}
			i1++;
			j1++;
		}
		// move diagonal toward South West.
		int i2 = 1;
		int j2 = 1;
		for( ; ; ){
			if( i2 == (board.size() - row) || j2 > col){
				break;
			}
			if(!board.get(row + i2).get(col - j2).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
						
				deepCopy.get(row + i2).set(col - j2, this);
				deepCopy.get(row).set(col, new Dot(row, col));
				neighbor.add(deepCopy);
				break;
			}
			i2++;
			j2++;
		}
		// move diagonal toward South East.
		
		int i3 = 1;
		int j3 = 1;
		for( ; ; ){
			if(i3 == (board.size() - row) || j3 == (board.get(row).size() - col)){ 
				break;
			}
			if(!board.get(row + i3).get(col + j3).getID().equals("Dot")){
				ArrayList<ArrayList<ChessPieces>> deepCopy = new ArrayList<ArrayList<ChessPieces>>(); 
				for(ArrayList<ChessPieces> lists : board){
					ArrayList<ChessPieces> objectCopy = new ArrayList<ChessPieces>();
					objectCopy.addAll(lists);
					deepCopy.add(objectCopy);
				}
				
				deepCopy.get(row + i3).set(col + j3,this);
				deepCopy.get(row).set(col, new Dot(row,col));
				neighbor.add(deepCopy);
				break;
			}
			i3++;
			j3++;
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
