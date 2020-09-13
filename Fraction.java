/*
CMSC350 
 23 Feb 2020
 Shaun Reid
 
 The Fraction class is used to instantiate a fraction object and to compare fraction objects*/


public class Fraction implements Comparable<Fraction> {

	Integer numerator = new Integer(0);
	Integer denominator = new Integer(1);
	
	
	
	//constructor that accepts a fraction string
	public Fraction(String str) {
		String[] numbers = str.split("/");
		if(numbers.length != 2) {
			throw new NumberFormatException("Improperly Formatted Fraction");
		}
		numerator = Integer.valueOf(numbers[0]);
		denominator = Integer.valueOf(numbers[1]);
		
		if(denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}
		
		if(denominator == 0) {
			throw new IllegalArgumentException();
		}
		
	}
	
	//toString method
	public String toString() {
		return numerator + "/" + denominator;
	}
	
	//get numerator
	public Integer getNumerator() {
		return numerator;
	}
	
	//get denominator
	public Integer getDenominator() {
		return denominator;
	}
	
	//compareTo implementation
	public int compareTo(Fraction obj2) {
		
		Fraction fraction2 = obj2;
		
		int leftCrossProd = numerator * fraction2.getDenominator();
		int rightCrossProd = fraction2.getNumerator() * denominator;
		
		int result = leftCrossProd - rightCrossProd;
		
		return result;
	}

	

}
