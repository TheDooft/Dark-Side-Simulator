package dss.tools;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LanguageTools {

	private static ResourceBundle bundle = ResourceBundle.getBundle("translation");

	public static String translate(String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			System.err.println("Missing translation for: " + key);
			return key;
		}
	}
}
