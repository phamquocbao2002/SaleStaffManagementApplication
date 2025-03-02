/**
 * 
 */
/**
 * 
 */
module quanlykinhdoanh {
	requires java.desktop;   
    requires jxl;       
    requires itextpdf;
	requires poi;
	requires aspose.cells;

    opens model.function to java.desktop;
}