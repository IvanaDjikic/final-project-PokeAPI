
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.Test;

public class SetupTest {

	@Test
	public void connectingTest() throws MalformedURLException, IOException {
		assertTrue(BerryFinder.connecting("https://pokeapi.co/api/v2/berry/").contains("count"));
		assertFalse(BerryFinder.connecting("https://pokeapi.co/api/v2/berry/").contains("size"));
		assertTrue(BerryFinder.connecting("https://pokeapi.co/api/v2/berry/1/").contains("size"));

	}

	@Test
	public void BerryToStringTest() {
		assertTrue("named: wepear,  growth time: 2, size: 74".equals(new Berry("wepear", 2, 74).BerryToString()));
		assertFalse("named: wepear,  growth time: 2, size: 74".equals(new Berry("cheri", 3, 20).BerryToString()));
	}

	@Test
	public void gettingBerriesFromApiTest() throws MalformedURLException, IOException {
		assertEquals(new Berry("cheri", 3, 20).BerryToString(),
				BerryFinder.gettingBerriesFromApi().get(0).BerryToString());
		assertEquals(new Berry("leppa", 4, 28).BerryToString(),
				BerryFinder.gettingBerriesFromApi().get(5).BerryToString());
		assertNotEquals(new Berry("leppa", 4, 28).BerryToString(),
				BerryFinder.gettingBerriesFromApi().get(20).BerryToString());

	}

	@Test
	public void createListOfBerriesWithShortestGrowthTimeTest() {
		ArrayList<Berry> berryList = new ArrayList<Berry>();
		berryList.add(new Berry("berry1", 5, 10));
		berryList.add(new Berry("berry2", 3, 8));
		berryList.add(new Berry("berry3", 7, 4));
		berryList.add(new Berry("berry4", 3, 15));
		assertEquals(3, BerryFinder.createListOfBerriesWithShortestGrowthTime(berryList).get(0).growTime);
		assertNotEquals(5, BerryFinder.createListOfBerriesWithShortestGrowthTime(berryList).get(0).growTime);

	}

	@Test
	public void arrangeBerriesWithShortestGrowthTimeBySizeTest() {
		ArrayList<Berry> berryList = new ArrayList<Berry>();
		berryList.add(new Berry("berry1", 5, 10));
		berryList.add(new Berry("berry2", 3, 8));
		berryList.add(new Berry("berry3", 7, 4));
		berryList.add(new Berry("berry4", 3, 15));
		ArrayList<Berry> berryList1 = BerryFinder.createListOfBerriesWithShortestGrowthTime(berryList);

		assertEquals(15, BerryFinder.arrangeBerriesWithShortestGrowthTimeBySize(berryList1).get(0).size);
		assertFalse(BerryFinder.arrangeBerriesWithShortestGrowthTimeBySize(berryList1).get(0).size < BerryFinder
				.arrangeBerriesWithShortestGrowthTimeBySize(berryList1).get(1).size);

	}

}
