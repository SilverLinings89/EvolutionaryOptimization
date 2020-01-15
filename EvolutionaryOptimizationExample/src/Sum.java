import java.util.Random;

public class Sum extends Expression {
	private int depth;
	
	Sum(int in_depth) {
		NumberOfChildExpressions = 2;
		expressions = new Expression[2];
		depth = in_depth;
		expressions[0] = ExpressionGenerator.Generate(depth - 1);
		expressions[1] = ExpressionGenerator.Generate(depth - 1);
	};
	
	Sum(Expression in_left_hand_side, Expression in_right_hand_side, int in_depth) {
		NumberOfChildExpressions = 2;
		expressions = new Expression[2];
		expressions[0] = in_left_hand_side;
		expressions[1] = in_right_hand_side;
		depth = in_depth;
	}
	
	@Override
	public double evaluateForX(double x) {
		return expressions[0].evaluateForX(x) + expressions[1].evaluateForX(x);
	}

	@Override
	public boolean mutate() {
		Random random = new Random();
		boolean mutate_left = random.nextFloat() < Expression.mutation_threshold ;
		boolean mutate_right = random.nextFloat() < Expression.mutation_threshold ;
		boolean mutated_inner = false;
		if(mutate_left) {
			expressions[0] = ExpressionGenerator.Generate(depth - 1); 
		} else {
			if(expressions[0].mutate()) mutated_inner = true;
		}
		if(mutate_right) {
			expressions[1] = ExpressionGenerator.Generate(depth - 1);
		} else {
			if(expressions[1].mutate()) mutated_inner = true;
		}
		return mutate_left || mutate_right || mutated_inner;
	}

	@Override
	public Expression cloneExpression() {
		return new Sum(expressions[0].cloneExpression(), expressions[1].cloneExpression(), depth);
	}
	
	@Override
	public String toString() {
		return "("+expressions[0].toString() + " + " + expressions[1].toString()+")"; 
	}
	
	@Override
	boolean isConstant() {
		return expressions[0].isConstant() && expressions[1].isConstant(); 
	}

}
