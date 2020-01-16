
public class Constant extends Expression {
	double value;
	int depth;
	
	Constant(int in_depth) {
		NumberOfChildExpressions = 0;
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
		boolean mutate_value = random.nextFloat() < Expression.mutation_threshold ;
		if(mutate_value) {
			double new_value = this.value;
			while(Math.abs(new_value - this.value) < 0.0001 ) {
				new_value = this.value + random.nextGaussian();
			}
			this.value = new_value;
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
