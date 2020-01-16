import java.util.Random;

abstract class Expression {
	abstract double evaluateForX(double x);
	abstract boolean isConstant();
	abstract Expression cloneExpression();
	abstract boolean mutate();
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
	int NumberOfChildExpressions;
	int depth;
	static float mutation_threshold = 0.05f;
	static int depth_max = 10;
	static int number_of_expression_types = 5;
}