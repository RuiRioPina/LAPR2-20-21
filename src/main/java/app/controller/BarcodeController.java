package app.controller;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import app.domain.shared.Configuration;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class BarcodeController {

	private static String BasePath;
	private static Method createUPCA;
	private static Method setPrefferedBarHeight;
	private static Method savePNG;
	
	static {
		BasePath = System.getProperty("user.dir") + "\\Barcodes";
		File directory = new File(BasePath);
		if (!directory.exists()) {
			directory.mkdir();
		}
		
		try {
			File jar = new File(Configuration.getBarcodeApiJarPath());
    		URL url = jar.toURI().toURL();
    		ClassLoader loader = new URLClassLoader(new URL[] {url});
    		
    		Class<?> cls = loader.loadClass(Configuration.getBarcodeApiCreator());
    		createUPCA = cls.getMethod(Configuration.getBarcodeApiCreatorMethod(), String.class);
    		
    		Class<?> cls2 = loader.loadClass(Configuration.getBarcodeApiObject());
        	setPrefferedBarHeight = cls2.getMethod(Configuration.getBarcodeApiObjectMethod(), int.class);
        	
        	Class<?> cls3 = loader.loadClass(Configuration.getBarcodeApiImage());
        	savePNG = cls3.getMethod(Configuration.getBarcodeApiImageMethod(), cls2, File.class);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	public static void generateBarcode(String barcode) throws Exception {
		try {
			
			String filePath = String.format("%s\\Barcode_%s.jpeg", BasePath, barcode);
			
//			If you want to use by POM
//			Barcode bc = BarcodeFactory.createUPCA(barcode);
//        	bc.setPreferredBarHeight(100);
//        	BarcodeImageHandler.savePNG(bc, new File(filePath));
        	       	
        	Object bc2 = createUPCA.invoke(null, barcode);
        	
        	setPrefferedBarHeight.invoke(bc2, 100);
        	
        	savePNG.invoke(null, bc2, new File(filePath));
        	
		} catch (Exception e) {
			throw new Exception("Error generating barcode");
		}
	}

}
