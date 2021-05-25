package app.controller;

import java.awt.Dimension;
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
			BarcodeImageHandler.saveJPEG(bc, new File(filePath));
		} catch (BarcodeException e) {
			throw new Exception("Error generating barcode");
		}
	}

}
