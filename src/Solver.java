/*
 * Solver.java
 * 
 * Version:
 * $Id: Solver.java,v 1.1 2013/05/04 01:33:03 p243-01n Exp $
 * 
 * Revisions:
 * $Log: Solver.java,v $
 * Revision 1.1  2013/05/04 01:33:03  p243-01n
 * Project Part3
 *
 * Revision 1.1  2013/04/19 00:03:39  p243-01n
 * Project Part2
 *
 * Revision 1.3  2013/04/18 23:04:28  qdv2130
 * Project Part 2
 *
 * Revision 1.2  2013/04/03 20:17:21  qdv2130
 * Project Part 1
 *
 * Revision 1.1  2013/04/03 16:01:54  qdv2130
 * Project part 1
 *
 * 
 */

// Import go here, if any
import java.util.*;
/**
 * Solver class takes the clock puzzle, and use the solve method to solve it.
 * 
 * @author quangvu
 * @e-mail qdv2130@rit.edu
 */

public class Solver<E>{
    /** Member fields */
	private Puzzle<E> c;
	/**
	 * Constructor - Initialize all member fields.
	 * @param c Type general, initial object of member variable.
	 */
	public Solver(Puzzle<E> c){
		this.c = c;
	}
	/**
	 * Solve method ---- solve the puzzle
	 * Takes a generic type of puzzle and solve it by using BFS.
	 * @return the path.
	 * @return null if there is no path to solve it.
	 */
	public ArrayList<E> solve(){
		ArrayList<E> path = new ArrayList<E>();
		// Creating a Queue which is a generic type
		ArrayList<E> queue = new ArrayList<E>(); 
		HashMap<E, E> predecessor = new HashMap<E,E>();
		E current = c.getStart();
		//Enqueue the starting config.
		predecessor.put(current, null);
		queue.add(current); 
		// Initialize the found to false as not found yet.
		boolean found  = false;
		
		/** Loop through the queue until it is empty or we found a path to solve the puzzle */
		while(queue.size() != 0 && !found){
			current = queue.remove(0);
			/** Loop through every neighbors of the config */
			for( E neighbor : c.getNeighbor(current)){
				if(c.isGoal(neighbor)){
					found = true;
					predecessor.put(neighbor, current);
					current = neighbor;
					break;
				}
				if(!predecessor.containsKey(neighbor)){
					predecessor.put(neighbor, current);
					queue.add(neighbor);
				}
			}
		}
		
		// Constructing the path from the predecessor map.
		if(found){
			while(predecessor.get(current) != null){
				path.add(current);
				current = predecessor.get(current);
			}
			path.add(current);
			Collections.reverse(path);
		}
		
		return path;
				
	
	}
}
