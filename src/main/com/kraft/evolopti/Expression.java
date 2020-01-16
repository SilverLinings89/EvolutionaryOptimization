package com.kraft.evolopti;
import java.util.Random;

public abstract class Expression {
	public abstract double evaluateForX(double x);
	public abstract boolean isConstant();
	public abstract Expression cloneExpression();
	public abstract boolean mutate();
	static Random random = new Random();
	public Expression optimizedSelf() {
		boolean constant = true;
		for(int i = 0; i < NumberOfChildExpressions; i++) {
			if(!expressions[i].isConstant()) constant = false;
		}
		if(constant) {
			return new Constant(this.evaluateForX(0), depth);
		} else {
			return this;
		}
	}

	public void simplify() {
		for(int i = 0; i < NumberOfChildExpressions; i++) {
			expressions[i].simplify();
			if(expressions[i].isConstant()) {
				expressions[i] = expressions[i].optimizedSelf();
			}
		}
	}
	
	public Expression[] expressions;
	public int NumberOfChildExpressions;
	int depth;
	static float mutation_threshold = 0.05f;
	static int depth_max = 10;
	static int number_of_expression_types = 5;
}