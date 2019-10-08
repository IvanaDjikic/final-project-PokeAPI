
public class Berry {
	String name;
	int growTime;
	int size;

	public Berry(String name, int growTime, int size) {
		super();
		this.name = name;
		this.growTime = growTime;
		this.size = size;

	}

	public String BerryToString() {
		return "named: " + this.name + ",  growth time: " + Integer.toString(this.growTime) + ", size: "
				+ Integer.toString(this.size);
	}

}
