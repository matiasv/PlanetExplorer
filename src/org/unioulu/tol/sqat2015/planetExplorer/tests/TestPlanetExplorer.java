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
