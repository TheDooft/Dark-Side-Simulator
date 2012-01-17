package dss.tools;

public class MathTools {
	public double round (double number,int dec){
		double coef = Math.pow(10, dec);
		number *= coef;
		number = Math.round(number);
		return (number / coef);
	}
}
