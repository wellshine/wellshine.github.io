package beans;

public class Node {
	private int numid;//id
	private int temperature;//温度
	private float humidity;//湿度
//	private float concentration;//浓度
	private String date;//时间
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNumid() {
		return numid;
	}
	public void setNumid(int numid) {
		this.numid = numid;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
//	public float getConcentration() {
//		return concentration;
//	}
//	public void setConcentration(float concentration) {
//		this.concentration = concentration;
//	}
}
