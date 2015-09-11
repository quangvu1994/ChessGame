/*
 * Puzzle.java
 * 
 * Version:
 * $Id: Puzzle.java,v 1.1 2013/05/04 01:33:04 p243-01n Exp $
 * 
 * Revisions:
 * $Log: Puzzle.java,v $
 * Revision 1.1  2013/05/04 01:33:04  p243-01n
 * Project Part3
 *
 * Revision 1.1  2013/04/19 00:03:41  p243-01n
 * Project Part2
 *
 * Revision 1.2  2013/04/18 23:04:29  qdv2130
 * Project Part 2
 *
 * Revision 1.1  2013/04/03 16:01:54  qdv2130
 * Project part 1
 *
 */

// Imports go here, if any
import java.util.ArrayList;

/**
 * Puzzle is an interface that declare three methods. 
 * 
 * @author quangvu
 * @param <E>
 * @e-mail: qdv2130@rit.edu
 *
 */
public interface Puzzle<E> {
	/**
	 * 
	 * @return the goal config from this Puzzle
	 */
	boolean isGoal(E config);
	/**
	 * 
	 * @param from incoming config --> generate
	 * @return all direct neighbors of the config
	 */
	ArrayList<E> getNeighbor(E config);
	/**
	 * 
	 * @return the starting config from this Puzzle
	 */
	E getStart();
}
