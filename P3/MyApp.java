import java.io.File;

import presenter.Presenter;
import view.*;;

public class MyApp {

	public static void main(String[] args) {
		View view = new View();
		
		view.setPresenter(new Presenter(view));
		
		view.getMethod("Lagrange");
		view.calculateResult(10.3f, new File("./data.dat"));
		
		view.getMethod("CubicSpline");
		view.calculateResult(10.3f, new File("./data.dat"));
	}

}
