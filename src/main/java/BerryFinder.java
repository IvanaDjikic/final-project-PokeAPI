import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class BerryFinder {

	static String rootURL = "https://pokeapi.co/api/v2/berry/";

	static String connecting(String URL) throws MalformedURLException, IOException {
		URL request = new URL(URL);
		InputStream openStream = request.openStream();
		String response = IOUtils.toString(openStream);
		return response;
	}

	static ArrayList<Berry> gettingBerriesFromApi() throws MalformedURLException, IOException {

		JSONObject root = new JSONObject(connecting(rootURL));

		int numberOfBerries = (int) root.get("count");

		ArrayList<Berry> berries = new ArrayList<Berry>();
		for (int i = 1; i <= numberOfBerries; i++) {

			rootURL += Integer.toString(i) + "/";
			JSONObject root1 = new JSONObject(connecting(rootURL));

			String name = (String) root1.get("name");
			int growthTime = (int) root1.get("growth_time");
			int size = (int) root1.get("size");

			berries.add(new Berry(name, growthTime, size));

			rootURL = "https://pokeapi.co/api/v2/berry/";
		}
		return berries;
	}

	static ArrayList<Berry> createListOfBerriesWithShortestGrowthTime(ArrayList<Berry> berries) {

		Collections.sort(berries, (b1, b2) -> b1.growTime - b2.growTime);
		int MinGrowthTime = berries.get(0).growTime;
		ArrayList<Berry> berriesWithShortestGrowthTime = new ArrayList<Berry>();
		for (int i = 0; i < berries.size(); i++) {
			if (berries.get(i).growTime == MinGrowthTime) {
				berriesWithShortestGrowthTime.add(berries.get(i));
			}

		}
		return berriesWithShortestGrowthTime;
	}

	static ArrayList<Berry> arrangeBerriesWithShortestGrowthTimeBySize(ArrayList<Berry> berriesWithShortestGrowthTime) {
		Collections.sort(berriesWithShortestGrowthTime, (b1, b2) -> b2.size - b1.size);
		return berriesWithShortestGrowthTime;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ArrayList<Berry> berries = gettingBerriesFromApi();
		ArrayList<Berry> berriesWithShortestGrowthTime = createListOfBerriesWithShortestGrowthTime(berries);
		arrangeBerriesWithShortestGrowthTimeBySize(berriesWithShortestGrowthTime);

		System.out.println("The largest berry that can grow in the shortest time is berry "
				+ berriesWithShortestGrowthTime.get(0).BerryToString());

	}

}
