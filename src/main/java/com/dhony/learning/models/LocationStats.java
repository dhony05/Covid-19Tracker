package com.dhony.learning.models;


public class LocationStats {
	
	
	private  String country;
	private  String state;
	
    
    private double latitude;
    
    private double longitude;
    
    private int previousDayConfiCases;
    
    private  int latestTotalCases;
    
    private int previousDayDeathCases;
    
    private int latestDeathCases;
    

	
    
	private int latestRecoveredCases;

    
    public int getLatestDeathCases() {
		return latestDeathCases;
	}

	public void setLatestDeathCases(int latestDeathCases) {
		this.latestDeathCases = latestDeathCases;
	}

	public int getLatestRecoveredCases() {
		return latestRecoveredCases;
	}

	public void setLatestRecoveredCases(int latestRecoveredCases) {
		this.latestRecoveredCases = latestRecoveredCases;
	}

    
    public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}



	public int getPreviousDayDeathCases() {
		return previousDayDeathCases;
	}

	public void setPreviousDayDeathCases(int previousDayDeathCases) {
		this.previousDayDeathCases = previousDayDeathCases;
	}

	public int getPreviousDayConfiCases() {
		return previousDayConfiCases;
	}

	public void setPreviousDayConfiCases(int previousDayConfiCases) {
		this.previousDayConfiCases = previousDayConfiCases;
	}

	@Override
	public String toString() {
		return "LocationStats [country=" + country + ", state=" + state + ", latitude=" + latitude + ", longitude="
				+ longitude + ", previousDayConfiCases=" + previousDayConfiCases + ", latestTotalCases="
				+ latestTotalCases + ", previousDayDeathCases=" + previousDayDeathCases + ", latestDeathCases="
				+ latestDeathCases + ", latestRecoveredCases=" + latestRecoveredCases + "]";
	}


	
}
