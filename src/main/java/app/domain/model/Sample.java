package app.domain.model;

public class Sample {

	/**
     * Object oriented Class to the creation of a sample in a company context.
     */

	private final String barcodeNumber;
	
	/**
     * Constructor for the sample.
     * @param barcodeNumber - code of the barcode of the sample.
     */
	public Sample (String barcodeNumber) {		
		this.barcodeNumber = barcodeNumber;
	}

	/**
     * Returns the barcode of the sample.
     * @return barcode of the sample.
     */
	public String getBarcode() {
		return this.barcodeNumber;
	}
}
