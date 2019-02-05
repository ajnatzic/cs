package lab_exam_april17th;

//import java.math.*;

public class MyMath2 implements MyMathInterface{

	@Override
	public double square(double d)	{
		return Math.pow(d, 2);
	}
	
	@Override
	public double cubic(double d)	{
		return Math.pow(d, 3);
	}
}
