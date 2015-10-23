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
		String command = "l";
		
		String expected = "(0,0,W)";
		String res = explorer.executeCommand("l");
		assertEquals(expected,res);
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
