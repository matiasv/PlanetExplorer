package org.unioulu.tol.sqat2015.planetExplorer.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.unioulu.tol.sqat2015.planetExplorer.PlanetExplorer;

public class TestPlanetExplorer {

	@Test
	public void testExecuteCommandErronneousInput() {
		PlanetExplorer explorer = new PlanetExplorer(10,10,"");
		String res = explorer.executeCommand(null);
		assertEquals(null,res);
		res = explorer.executeCommand("");
		assertEquals(null,res);
		res = explorer.executeCommand("ffraf");
		assertEquals(null,res);
	}
	
	@Test
	public void testExecuteCommandUpdateFacingToLeft() {
		PlanetExplorer explorer = new PlanetExplorer(10,10,"");
		
		String expected = "(0,0,W)";
		String res = explorer.executeCommand("l");
		assertEquals(expected,res);
		
		res = explorer.executeCommand("l");
		assertEquals("(0,0,S)",res);
		res = explorer.executeCommand("l");
		assertEquals("(0,0,E)",res);
		res = explorer.executeCommand("l");
		assertEquals("(0,0,N)",res);
		
		explorer =  new PlanetExplorer(10,10,"");
		//llll should return (0,0,N)
		expected = "(0,0,N)";
		res = explorer.executeCommand("llll");
		assertEquals(expected,res);
		explorer =  new PlanetExplorer(10,10,"");
	
	}
	
	@Test
	public void testExecuteCommandUpdateFacingToRight() {
		PlanetExplorer explorer = new PlanetExplorer(10,10,"");
		String res = explorer.executeCommand("r");
		assertEquals("(0,0,E)",res);
		res = explorer.executeCommand("r");
		assertEquals("(0,0,S)",res);
		res = explorer.executeCommand("r");
		assertEquals("(0,0,W)",res);
		res = explorer.executeCommand("r");
		assertEquals("(0,0,N)",res);
	}
	
	@Test
	public void testExecuteCommandMoveForwardCrossingTheGrid() {
		PlanetExplorer explorer = new PlanetExplorer(5,5,"");
		String res = explorer.executeCommand("f");
		assertEquals(res,"(0,4,N)");
		res = explorer.executeCommand("f");
		assertEquals(res,"(0,3,N)");
		
		//the same thing moving across the grid to the top
		//note: also changing direction here (to South)
		//String cmd ="rrf";
		String cmd = "rrff";
		res = explorer.executeCommand(cmd);
		assertEquals("(0,0,S)",cmd);
		
		res = explorer.executeCommand(cmd);
		assertEquals("(0,4,S)",res);
		cmd = "f";
		res = explorer.executeCommand(cmd);
		assertEquals("(0,0,S)",res);
		
		//assertEquals(res,"(0,0,S)");
		//assertEquals("(0,0,S)",res);
		
		//and from right side of the grid to the left side
		//change direction to east with "l"
		cmd = "l";
	}
	
	/*
	@Test
	public void testUpdateFacingToLeft() {
		PlanetExplorer explorer = new PlanetExplorer(10,10,"");
		String command = "l";
		
		String expected = "(0,0,W)";
		String res = explorer.executeCommand("l");
		assertEquals(expected,res);
	}*/
	

}
