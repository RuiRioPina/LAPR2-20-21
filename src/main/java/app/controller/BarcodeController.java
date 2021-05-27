package app.controller;

import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class BarcodeController {

	public static void generateBarcode(String barcode) throws Exception {
		try {
			String path = System.getProperty("user.dir");
			File directory = new File(path + "\\Barcodes");
			if (!directory.exists()) {
				directory.mkdir();
			}
			String filePath = String.format("%s\\Barcodes\\Barcode_%s.jpeg", path, barcode);
			Barcode bc = BarcodeFactory.createUPCA(barcode);
        	bc.setPreferredBarHeight(100);
        	BarcodeImageHandler.savePNG(bc, new File(filePath));
		} catch (Exception e) {
			throw new Exception("Error generating barcode");
		}
	}

}
