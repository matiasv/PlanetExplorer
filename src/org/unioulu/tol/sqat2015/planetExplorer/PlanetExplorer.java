package org.unioulu.tol.sqat2015.planetExplorer;

// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID: 2158866
// Finish time:
public class PlanetExplorer {
	public static String STARTING_POSITION = "(0,0)";
	
	private static final char FORWARD = 'f';
	private static final char BACKWARD = 'b';
	private static final char LEFT = 'l';
	private static final char RIGHT = 'r';
	
	public enum Direction {
		North, South, East, West
	}
	
	private static class Position {
	    public int x;
	    public int y;
	    
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private Position  currentPos = new Position(0,0);
	//where the obstacle is facing
	private Direction facing;
	
	public PlanetExplorer(int x, int y, String obstacles){
	/*	x and y represent the size of the grid.
	 *  Obstacles is a String formatted as follows: "(obs1_x,obs1_y)(obs2_x,obs2_y)...(obsN_x,obsN_y)" with no white spaces. 
	 *  
		Example use:
		PlanetExplorer explorer = new PlanetExplorer(100,100,"(5,5)(7,8)")  //A 100x100 grid with two obstacles at coordinates (5,5) and (7,8) 
	 */
		this.facing = Direction.North;
	}
	
	public String executeCommand(String command){

		/* The command string is composed of "f" (forward), "b" (backward), "l" (left) and "r" (right)
		 * Example: 
		 * The explorer is on a 100x100 grid at location (0, 0) and facing NORTH. 
		 * The explorer is given the commands "ffrff" and should end up at (2, 2) facing East.
		 
		 * The return string is in the format: "(pos_x,pos_y,facing)(obs1_x,obs1_y)(obs2_x,obs2_y)..(obsN_x,obsN_y)" 
		 * Where pos_x and pos_y are the final coordinates, facing is the current direction the explorer is pointing to (N,S,W,E).
		 * The return string should also contain a list of coordinates of the encountered obstacles. No white spaces.
		 */
		
		if(command == null || !command.matches("^[lrfb]+$")) {
			return null;
		}
		
		for(int i = 0; i < command.length(); i++) {
			char c = command.charAt(i);
			
			if(c == LEFT || c == RIGHT) {
				updateFacing(c);
			}
		}
		
		return null;
	}
	
	private void updateFacing(char direction) {
		if(direction == LEFT) {
			updateFacingToLeft();
		} else if(direction == RIGHT) {
			//updateFacingToRight();
		}
	}
	
	private void updateFacingToLeft() {
		switch(facing) {
			case North:
				this.facing = Direction.West;
				break;
			case West:
				this.facing = Direction.South;
				break;
			case South:
				this.facing = Direction.East;
				break;
			case East:
				this.facing = Direction.North;
				break;
			default:
				break;
		}
	}
}
