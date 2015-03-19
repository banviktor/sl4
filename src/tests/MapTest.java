package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import phoebe.game.Map;

public class MapTest {

	@Before
	public void setUp() throws Exception {
		Map map = new Map("map.xml");
	}

	@Test
	public void testMap() {
		fail("Not yet implemented");
	}

}
