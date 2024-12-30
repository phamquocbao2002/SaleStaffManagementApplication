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

    opens model.function to java.desktop;
}