import java.util.Random;

public class SpecialFunctions extends Expression {
	enum FunctionType {
		Sin, Cos, Exp, Sqrt, Sqr
	}
	
	FunctionType type;
	int depth;
	
	SpecialFunctions(int in_depth) {
		NumberOfChildExpressions = 1;
		this.depth = in_depth;
		expressions = new Expression[1];
		expressions[0] = ExpressionGenerator.Generate(depth - 1);
		this.set_random_type();
	}
	
	SpecialFunctions(Expression in_innerExpression, FunctionType in_type, int in_depth) {
		depth = in_depth;
		type = in_type;
		NumberOfChildExpressions = 1;
		expressions = new Expression[1];
		expressions[0] = in_innerExpression;
	}
	
	@Override
	public double evaluateForX(double x) {
		double inner_value = expressions[0].evaluateForX(x);
		switch (type) {
			case Sin:
				return Math.sin(inner_value);
			case Cos:
				return Math.cos(inner_value);
			case Exp:
				return Math.exp(inner_value);
			case Sqrt:
				return Math.sqrt(inner_value);
			case Sqr:
				return inner_value*inner_value;
				
			default:
				throw new IllegalArgumentException("Unexpected type in SpecialFunctions.evaluateForX");
		}
	}
	
	void set_random_type() {
		Random random = new Random();
		int new_type = random.nextInt(5);
		switch (new_type) {
		case 0: {
			this.type = FunctionType.Sin;
			break;
		}
		case 1: {
			this.type = FunctionType.Cos;
			break;
		}
		case 2: {
			this.type = FunctionType.Exp;
			break;
		}
		case 3: {
			this.type = FunctionType.Sqrt;
			break;
		}
		case 4: {
			this.type = FunctionType.Sqr;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected new_type in SpecialFunctions.mutate: " + new_type);
		}
	}

	@Override
	public boolean mutate() {
		Random random = new Random();
		boolean mutate_type = random.nextFloat() < Expression.mutation_threshold ;
		if(mutate_type) {
			set_random_type();
		}
		boolean mutated_inner = expressions[0].mutate();
		return mutate_type || mutated_inner;
	}
	
	@Override
	public Expression cloneExpression() {
		return new SpecialFunctions(expressions[0].cloneExpression(), type, depth);
	}

	@Override
	public String toString() {
		String inner = expressions[0].toString();
		switch (type) {
			case Sin:
				return "sin("+inner+")";
			case Cos:
				return "cos("+inner+")";
			case Exp:
				return "e^("+inner+")";
			case Sqrt:
				return "sqrt("+inner+")";
			case Sqr:
				return "("+inner+")^2";
			default:
				throw new IllegalArgumentException("Unexpected type in SpecialFunctions.evaluateForX");
		}
	}

	@Override
	boolean isConstant() {
		return expressions[0].isConstant(); 
	}
}
