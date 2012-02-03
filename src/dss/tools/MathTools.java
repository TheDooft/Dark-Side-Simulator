package dss.tools;

public class MathTools {
	public double round (double number,int dec){
		double coef = Math.pow(10, dec);
		number *= coef;
		number = Math.round(number);
		return (number / coef);
	}
	
	public boolean chance (double chancePercent){
		double rand = this.round(Math.random()*100.0,0) + 1;
		if (rand <= chancePercent) {
			return true;
		} else {
			return false;
		}
	}
}
