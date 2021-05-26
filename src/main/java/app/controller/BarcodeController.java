package app.controller;

import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class BarcodeController {

	public static void generateBarcode(String barcode) throws Exception {
		try {
			String filePath = String.format("C:\\Dados\\Barcode_%s.jpeg", barcode);
			Barcode bc = BarcodeFactory.createUPCA(barcode);
        	bc.setPreferredBarHeight(100);
        	BarcodeImageHandler.savePNG(bc, new File(filePath));
		} catch (Exception e) {
			throw new Exception("Error generating barcode");
		}
	}

}
