package lab_exam_april17th;

public class MyMath1 implements MyMathInterface{

	@Override
	public double square(double d)	{
		return d * d;
	}
	
	@Override
	public double cubic(double d) {
		return d * d * d;
	}

}
