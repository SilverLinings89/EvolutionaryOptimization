package com.kraft.evolopti;

public class ExpressionGenerator {
	public static Expression Generate (int in_depth) {
		if(in_depth <= 0) {
			return new Constant(in_depth);
		}
		int typeOfExpression = Expression.random.nextInt(Expression.number_of_expression_types);
		switch (typeOfExpression) {
			case 0: {
				return new Constant(in_depth);
			}
			case 1: {
				return new Multiplication(in_depth);
			}
			case 2: {
				return new SpecialFunctions(in_depth);
			}
			case 3: {
				return new Sum(in_depth);
			}
			case 4: {
				return new VariableValue(in_depth);
			}
			
			default:
				return new Constant(1);
		}
	}
	
	public static Expression GenerateInitial(int in_depth) {
		if(in_depth <= 0) {
			return new Constant(in_depth);
		}
		int typeOfExpression = Expression.random.nextInt(Expression.number_of_expression_types-2);
		switch (typeOfExpression) {
			case 0: {
				return new Multiplication(in_depth);
			}
			case 1: {
				return new SpecialFunctions(in_depth);
			}
			case 2: {
				return new Sum(in_depth);
			}
			default:
				return new Constant(1);
		}
	}
}
