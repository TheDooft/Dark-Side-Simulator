package dss.tools;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class ImageTools {

	private static Map<IconIndex, ImageIcon> iconCache = new HashMap<IconIndex, ImageIcon>();

	
	public static ImageIcon getLargeIcon(String path) {
		return  resizeIcon(path, 50);
	}
	
	public static ImageIcon getMediumIcon(String path) {
		return  resizeIcon(path, 38);
	}
	
	public static ImageIcon getSmallIcon(String path) {
		return  resizeIcon(path, 22);
	}
	
	public static ImageIcon getTinyIcon(String path) {
		return  resizeIcon(path, 14);
	}
	
	public static ImageIcon resizeIcon(String path, int size) {

		IconIndex iconIndex = new IconIndex(path, size);
		if (iconCache.containsKey(iconIndex)) {
			return iconCache.get(iconIndex);
		} else {

			ImageIcon icon = new ImageIcon(path);
			if(icon.getIconWidth() == -1) {
				return icon;
			}
			
			Image img = icon.getImage();  
			Image newimg = img.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);  
			ImageIcon newIcon = new ImageIcon(newimg);
			
			iconCache.put(iconIndex, newIcon);
			return newIcon;
		}
	}

	private static class IconIndex {

		private final String path;
		private final int size;

		public IconIndex(final String path, final int size) {
			this.path = path;
			this.size = size;

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((path == null) ? 0 : path.hashCode());
			result = prime * result + size;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IconIndex other = (IconIndex) obj;
			if (path == null) {
				if (other.path != null)
					return false;
			} else if (!path.equals(other.path))
				return false;
			if (size != other.size)
				return false;
			return true;
		}

	}
}
