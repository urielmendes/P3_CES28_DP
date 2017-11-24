package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.Vector;
import presenter.IView;
import presenter.Presenter;

public class View implements IView {
	
	private Presenter presenter;
	private File _file;
	private Vector<Double> x;
	private Vector<Double> y;
	private FileReader input;
	private BufferedReader bufRead;
	private StringTokenizer xy;
	private DecimalFormat formatResult = new DecimalFormat("####.######");
	
	public void setPresenter(Presenter presenter){
		this.presenter = presenter;
	}

	//public InterpolationMethod getMethod() { return interpolationModel; }
    public void getMethod(String method) {
        this.presenter.getMethod(method);
        
    }
    
    private void buildDataPoints(File file) {
        if(file == null)
           return;
        else _file = file;

        x = new Vector<Double>();
        y = new Vector<Double>();
        try {
            input = new FileReader(_file);
		    /* Filter FileReader through a Buffered read to read a line at a time */
            bufRead = new BufferedReader(input);
            // Read first line
            String line = bufRead.readLine();
            // Read through file one line at time.
            while (line != null){
                xy = new StringTokenizer(line, "\t ");
                while(xy.hasMoreTokens()) {
                    x.addElement(Double.parseDouble(xy.nextToken()));
                    y.addElement(Double.parseDouble(xy.nextToken()));
                }
                line = bufRead.readLine();
            }
            bufRead.close();
        } catch (IOException e) { // If another exception is generated, print a stack trace
            e.printStackTrace();
        }
    }

    public void calculateResult(float value, File file) {
        buildDataPoints(file);
        
        this.presenter.calculateResult(value, file, x, y);
    }
    
    public void printResult(double _value, double result) {
    	System.out.println("***********************");
    	System.out.println("DataFile: " + getDataFile());
    	System.out.println("Interp at " + formatResult.format(_value) + " ;"
    			+ " result = " + formatResult.format(result));
	}

	private File getDataFile() {
		return _file;
	}
    
    
}
