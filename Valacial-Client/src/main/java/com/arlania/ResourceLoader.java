package com.arlania;


import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

/**
 * This class grabs resources from the 'images' folder in the res package.
 * @author Gabriel Hannason
 */
public class ResourceLoader {

	private static final HashMap<String, Image> loadedImages = new HashMap<String, Image>();

	public Image getImage(String imageName) {
		if(loadedImages.containsKey(imageName))
			return loadedImages.get(imageName);
		Image img = null;
		try {
			img = Toolkit.getDefaultToolkit().getImage(signlink.findcachedir() + ""+imageName+".png");
		} catch(Exception e) {
			e.printStackTrace();
			img = null;
		}
		if(img != null)
			loadedImages.put(imageName, img);
		return img;
	}

	public static byte[] getFile(String fileName) {
		URL url = null;
				try {
					url = ClassLoader.getSystemClassLoader().getResource(fileName);
				}catch (Exception e) {
					e.printStackTrace();
				}
				if(url == null) {
					System.out.println("Error getting file:"+fileName);
				}



		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		try {
			int nRead;
			InputStream stream = url.openStream();

			byte[] data = new byte[16384];

			while ((nRead = stream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
		}catch (Exception e) {
			System.out.println("error reading file:"+fileName);
			e.printStackTrace();
		}

		return buffer.toByteArray();

	}
	
	public static Image loadImage(String imageName) {
		URL url = null;
		try {

			url = ClassLoader.getSystemClassLoader().getResource(""+imageName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (url == null) {
			System.err.println("null image:"+imageName);
			return null;
		}
		return Toolkit.getDefaultToolkit().getImage(url);
	}
	
}
