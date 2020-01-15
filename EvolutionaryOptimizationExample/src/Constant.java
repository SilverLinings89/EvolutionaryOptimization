import java.util.Random;

public class Constant extends Expression {
	double value;
	int depth;
	
	Constant(int in_depth) {
		NumberOfChildExpressions = 0;
		Random random = new Random();
		value = random.nextDouble() * 20 - 10;
		this.depth = in_depth;
	}
	
	Constant(double in_value, int in_depth) {
		value = in_value;
		depth = in_depth;
	}
	
	@Override
	public double evaluateForX(double x) {
		return value;
	}

	@Override
	public boolean mutate() {
		Random random = new Random();
		boolean mutate_value = random.nextFloat() < Expression.mutation_threshold ;
		if(mutate_value) {
			this.value = this.value + random.nextGaussian();
		}
		return mutate_value;
	}
	
	@Override
	public String toString() {
		return "" + value; 
	}
	
	@Override
	public Expression cloneExpression() {
		return new Constant(value, depth);
	}

	@Override
	boolean isConstant() {
		return true;
	}
}
