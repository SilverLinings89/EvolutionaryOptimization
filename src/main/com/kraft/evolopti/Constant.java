package com.kraft.evolopti;

public class Constant extends Expression {
	double value;
	int depth;
	
	public Constant(int in_depth) {
		NumberOfChildExpressions = 0;
		value = random.nextDouble() * 20 - 10;
		this.depth = in_depth;
	}
	
	public Constant(double in_value, int in_depth) {
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
			double old = this.value;
			while((old - this.value) == 0) {
				this.value = (random.nextDouble()- 0.5)*5.0;
			}
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
	public boolean isConstant() {
		return true;
	}
}
