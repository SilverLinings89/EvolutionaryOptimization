package com.kraft.evolopti.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.kraft.evolopti.*;

class CheckExpression {

	@Test
	@DisplayName("Checking Optimization of Expressions via checking constant property.")
	void optimizationTest() {
		Constant expression_c_1 = new Constant(1);
		Constant expression_c_2 = new Constant(1);
		VariableValue expression_v_1 = new VariableValue(1);
		
		Expression expression_sum_1 = new Sum(expression_c_1, expression_c_2, 2);
		Expression expression_sum_2 = new Sum(expression_c_1, expression_v_1, 2);
		
		assertEquals(expression_sum_1.isConstant(), true);
		assertEquals(expression_sum_2.isConstant(), false);
		
		expression_sum_1 = expression_sum_1.optimizedSelf();
		expression_sum_2 = expression_sum_2.optimizedSelf();
		
		assertEquals(expression_sum_1.NumberOfChildExpressions, 0);
		assertEquals(expression_sum_2.NumberOfChildExpressions, 2);
	}

	@Test
	@DisplayName("Checking Optimization of Expressions via checking constant property.")
	void simplificationTest() {
		Constant expression_c_1 = new Constant(1);
		Constant expression_c_2 = new Constant(1);
		Constant expression_c_3 = new Constant(1);
		VariableValue expression_v_1 = new VariableValue(1);
		
		Expression expression_sum_1 = new Sum(expression_c_1, expression_c_2, 2);
		Expression expression_sum_2 = new Sum(expression_c_3, expression_v_1, 2);
		Expression expression_sum_3 = new Sum(expression_sum_1, expression_sum_2, 3);
		
		assertEquals(expression_sum_1.isConstant(), true);
		assertEquals(expression_sum_2.isConstant(), false);
		assertEquals(expression_sum_3.isConstant(), false);
		
		// The term is now (c1 + c2) + (c3 + x). Since only basic simplification is implemented, this should reduce to c4 + (c3 + x).
		expression_sum_3.simplify();
		
		assertEquals(expression_sum_3.expressions[0].NumberOfChildExpressions, 0);
	}

}
