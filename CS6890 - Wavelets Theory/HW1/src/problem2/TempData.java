package problem2;

public class TempData {
	
	private String dataTime;
	private double temperature;
	
	public TempData(String dataTime, double temperature) {
		super();
		this.dataTime = dataTime;
		this.temperature = temperature;
	}

	public String getDataTime() {
		return dataTime;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	
	
}
