package presenter;

import java.io.File;
import java.util.Vector;

import model.CubicSpline;
import model.InterpolationMethod;
import model.Lagrange;

public class Presenter {
	
	private IView view;
	private InterpolationMethod interpMethod;
	
	public Presenter(IView view){
		this.view = view;
	}
	
	public InterpolationMethod getMethod() { return interpMethod; }
    
	public InterpolationMethod getMethod(String method) {
        switch (method) {
            case "Lagrange":
                interpMethod = (InterpolationMethod) new Lagrange();
                break;
            case "CubicSpline":
                interpMethod = (InterpolationMethod) new CubicSpline();
                break;
            default:
            	System.out.println("Unknown method " + method);
        }

        return interpMethod;
    }
	
	public void calculateResult(float value, File file, Vector<Double> x, Vector<Double> y) {
		double result;
        if(getMethod() != null) {
            result = getMethod().calculateResult(value, x, y);
            this.view.printResult(value, result);
            
        } else {
            System.out.println("It is not defined an interpolation method.");
        }
        
    }
	
}
