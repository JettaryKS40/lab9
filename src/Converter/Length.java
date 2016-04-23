package Converter;

public enum Length implements Unit{
	
	METER      ( "Meter"     , 1.0      ),
	CENTIMETER ( "Centimeter", 0.01     ),
	KILOMETER  ( "Kilometer" , 1000.0   ),
	MILE       ( "Mile"      , 1609.344 ),
	FOOT       ( "Foot"      , 0.30480  ),
	WA         ( "Wa"        , 2.0      ),
	LIGHTYEAR  ( "Light-year", 9460730472580800.0      ),
	MICRON     ( "Micron"    , 1.0E-6   );
	
	private final String name;
	private final double value;
	
	Length ( String name, double value ) {
		this.name = name;
		this.value = value;
		
	}
	
	public double getValue() {
		return this.value;
		
	}
	
}
