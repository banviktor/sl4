package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import phoebe.basic.Vector;
import phoebe.game.Map;

public class MapTest {
	Map map = null;

	@Before
	public void setUp() throws Exception {
		map = new Map("map.xml");
	}

	@Test
	public void testMap() {
		assertEquals( map.getRounds(), 20 );
		assertEquals( map.getLines().size(), 4 );
		assertEquals( map.getSmudgesAt(new Vector(0,0)).size(), 0 );
		assertEquals( map.isOnRoad(new Vector(0,0)), false );
		assertEquals( map.isOnRoad(new Vector(0.1,0.1)), true);
	}

}
