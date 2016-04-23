package Converter;

public class UnitConverter {
	
	public double convert( double amount, Unit fromUnit, Unit toUnit){
		double newUnits = ( amount * fromUnit.getValue() ) / toUnit.getValue();
		return newUnits;
	}
	
	public Unit[] getUnits() {
		return Length.values();
		
	}
}
