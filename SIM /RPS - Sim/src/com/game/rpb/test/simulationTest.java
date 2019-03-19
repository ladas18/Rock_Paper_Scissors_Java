package com.game.rpb.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.game.rpb.Main;

class simulationTest {
	
	Main test = new Main();
	int output;

	@Test
	void testPaper() {
		output = test.simulationTest(1);
		assertEquals(0, output);
	}
	
	@Test
	void testRock() {
		output = test.simulationTest(0);
		assertEquals(2, output);
	}
	
	@Test
	void testScissor() {
		output = test.simulationTest(2);
		assertEquals(1, output);
	}
	
	@Test
	void testInvalidNumbers() {
		for(int i = 3; i < 100; i++) {
			output = test.simulationTest(i);
			assertEquals(-1, output);
		}
	}
	

}
