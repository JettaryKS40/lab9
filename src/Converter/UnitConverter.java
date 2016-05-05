package Converter;

/** This class will call by converter to get a units and return the value
 *  to the converter GUI.
 * 
 * @author 8.1
 */

public class UnitConverter {
	
	// this return the unit after converted.
	
	public double convert( double amount, Unit fromUnit, Unit toUnit){
		double newUnits = ( amount * fromUnit.getValue() ) / toUnit.getValue();
		return newUnits;
	}
	
	//this get a units from the length class
	
	public Unit[] getUnits() {
		return Length.values();
		
	}
}
