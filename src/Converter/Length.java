package Converter;

/** This class contains all methods that will provide the unit and some functions
 *  for the GUI 
 *  
 *  @author Keetawat Srichonpoo - 5810545840
 */

public enum Length implements Unit{

	// a enum of length units
	
	METER ( "Meter" , 1.0 ) ,
	CENTIMETER ( "Centimeter" , 0.01 ) ,
	KILOMETER ( "Kilometer" , 1000.0 ) ,
	MILE ( "Mile" , 1609.344 ) ,
	FOOT ( "Foot" , 0.30480 ) ,
	WA ( "Wa" , 2.0 ) ,
	LIGHTYEAR ( "Light-year" , 9460730472580800.0 ) ,
	MICRON ( "Micron" , 1.0E-6 ) ;

	private final String name;
	private final double value;

	Length ( String name, double value ) {
		this.name = name;
		this.value = value;

	}

	//this return the value of the length units.
	
	public double getValue() {
		return this.value;

	}

}
