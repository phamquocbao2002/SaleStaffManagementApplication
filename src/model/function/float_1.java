package model.function;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class float_1 {
	static Float fl;
    public float_1(Float fl) {
        this.fl = fl;
    }
    public Float getvalue(){
        return fl;
    }
    public String tostring(){
    	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
        return decimalFormat.format(Math.round(fl));
    }

}
