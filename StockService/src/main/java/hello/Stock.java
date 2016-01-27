package hello;

public class Stock {

	private String Name;
	private double value;
	private double oneYearHigh;
	private double oneYearLow;
	
	public Stock(Stock s) {
		this.Name=s.getName();
		this.value =s.getValue();
		this.oneYearHigh=s.oneYearHigh;
		this.oneYearLow=s.oneYearLow;
		
	}
	public Stock() {
		
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double d) {
		if(d<this.oneYearLow)
			this.oneYearLow=d;
		
		if(d>this.oneYearHigh)
			this.oneYearHigh=d;
		
		this.value = d;
	}
	
	public double getOneYearHigh() {
		return oneYearHigh;
	}
	public void setOneYearHigh(double oneYearHigh) {
		this.oneYearHigh = oneYearHigh;
	}
	public double getOneYearLow() {
		return oneYearLow;
	}
	public void setOneYearLow(double oneYearLow) {
		this.oneYearLow = oneYearLow;
	}
	@Override
	public String toString() {
		return "Stock Name: " + this.getName() + " Stock Price : "+ this.getValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		if(obj instanceof Stock)
		{
			
			return this.Name.equals(((Stock)obj).getName());
			
		}
		else return false;
		
	}
	
}
