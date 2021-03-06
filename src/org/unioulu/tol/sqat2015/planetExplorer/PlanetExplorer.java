package org.unioulu.tol.sqat2015.planetExplorer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID: 2158866
// Finish time: 13:34
public class PlanetExplorer {
	public static String STARTING_POSITION = "(0,0)";
	
	private static final char FORWARD = 'f';
	private static final char BACKWARD = 'b';
	private static final char LEFT = 'l';
	private static final char RIGHT = 'r';
	
	public enum Direction {
		North, South, East, West;
	}
	
	private static class Position {
	    private int x;
	    private int y;
	    
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int x() { return x; }
		public int y() { return y; }
	}
	
	private Position  currentPos = new Position(0,0);
	//where the obstacle is facing
	private Direction facing = Direction.North;
	
	private int gridSizeX;
	private int gridSizeY;
	private Set<String> obstaclePositions = new HashSet<String>();
	
	
	/*
	 * TODO:
	 * grid 
	 */
	
	public PlanetExplorer(int x, int y, String obstacles){
	/*	x and y represent the size of the grid.
	 *  Obstacles is a String formatted as follows: "(obs1_x,obs1_y)(obs2_x,obs2_y)...(obsN_x,obsN_y)" with no white spaces. 
	 *  
		Example use:
		PlanetExplorer explorer = new PlanetExplorer(100,100,"(5,5)(7,8)")  //A 100x100 grid with two obstacles at coordinates (5,5) and (7,8) 
	 */
		this.gridSizeX = x;
		this.gridSizeY = y;
		this.parseObstaclePositions(obstacles);
	}
	
	private void parseObstaclePositions(String obstacles) {
		if(obstacles == null || obstacles.length() == 0) {
			return;
		}
		
		String[] temp = obstacles.split("[,\\)\\(]");
		List<Integer> coords = new ArrayList<Integer>();
		
		for(String t: temp) {
			try {
				int coord = Integer.parseInt(t.trim());
				coords.add(coord);
			} catch(NumberFormatException ne) {}
		}
		
		if(coords.size() % 2 != 0 || coords.size() == 0) {
			return;
		}
		
		for(int i = 0; i < coords.size()-1; i+=2) {
			int x = coords.get(i);
			int y = coords.get(i+1);
			this.obstaclePositions.add("("+x+","+y+")");
		}
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
			} else if(c == FORWARD || c == BACKWARD) {
				/*
				 * Moving the explorer
				 */
				
				
				/*
				 * 1. get move direction
				 * 2. get the coordinate from that
				 * 3. if it contains an obstacle, add that to the string of obstacles
				 * 4. otherwise move to that spot - updating currentPos!
				 */
				
				//1
				Direction moveDir = getMoveDirection(c);
				//2 get the next coordinate
				
				//3 NOT IMPLEMENTED YET
				
				//4 Move the explorer to the new position
				Position nextPos = this.getPositionAfterMove(moveDir);
				this.currentPos = nextPos;	
			}	
		}
		
		String result = this.formatCurrentPosition();
		
		return result;
	}
	
	//TODO: Implement all cases!
	private Position getPositionAfterMove(Direction moveDir) {
		Position nextPos = null;
		if(moveDir == Direction.North) {
			//java's % can return negative numbers so it's just easier this way	
			if(currentPos.y() == 0) {
				nextPos = new Position(currentPos.x(),this.gridSizeY-1);
			} else {
				nextPos = new Position(currentPos.x,currentPos.y()-1);
			}
		} else if(moveDir == Direction.South) {
			nextPos = new Position(currentPos.x(),(currentPos.y()+1) % this.gridSizeY );
		}
		
		return nextPos;
	}
	
	private void move(char direction) {
		Direction moveDir = getMoveDirection(direction);
		
		/*
		 * don't handle 
		 */
		
		if(direction == FORWARD) {
			
		} else if(direction == BACKWARD) {
			
		}
	}
	
	private Direction getMoveDirection(char direction) {
		if(facing == Direction.North && direction == FORWARD) {
			return Direction.North;
		} else if(facing == Direction.North && direction == BACKWARD) {
			return Direction.South;
		} else if(facing == Direction.South && direction == FORWARD) {
			return Direction.South;
		} else if(facing == Direction.South && direction == BACKWARD) {
			return Direction.North;
		} else if(facing == Direction.East && direction == FORWARD) {
			return Direction.East;
		} else if(facing == Direction.East && direction == BACKWARD) {
			return Direction.West;
		} else if(facing == Direction.West && direction ==  FORWARD) {
			return Direction.West;
		} else if(facing == Direction.West && direction == BACKWARD) {
			return Direction.East;
		}
		
		return null;
	}
	
	private void updateFacing(char direction) {
		if(direction == LEFT) {
			updateFacingToLeft();
		} else if(direction == RIGHT) {
			updateFacingToRight();
		}
	}
	
	private void updateFacingToRight() {
		switch(facing) {
		case North:
			this.facing = Direction.East;
			break;
		case East:
			this.facing = Direction.South;
			break;
		case South:
			this.facing = Direction.West;
			break;
		case West:
			this.facing = Direction.North;
			break;
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
	
	private String formatCurrentPosition() {
	String result = "(" + this.currentPos.x + "," + this.currentPos.y + ",";
		
		if(this.facing == Direction.North) {
			result += "N";
		} else if(this.facing == Direction.South) {
			result += "S";
		} else if(this.facing == Direction.East) {
			result += "E";
		} else {
			result += "W";
		}
		
		result += ")";
		
		return result;
	}
}
