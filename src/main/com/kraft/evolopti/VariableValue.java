package com.kraft.evolopti;

public class VariableValue extends Expression {
	int depth;
	
	public VariableValue(int in_depth) {
		NumberOfChildExpressions = 0;
		this.depth = in_depth;
	}
	
	@Override
	public double evaluateForX(double x) {
		return x;
	}

	@Override
	public boolean mutate() {
		return false;
	}
	
	@Override
	public Expression cloneExpression() {
		return new VariableValue(depth);
	}

	@Override
	public String toString() {
		return "x";
	}

	@Override
	public boolean isConstant() {
		return false;
	}
}
