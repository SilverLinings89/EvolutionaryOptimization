package com.kraft.evolopti.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.kraft.evolopti.*;

class CheckSpecialFunctions {

	@Test
	void testSinus() {
		VariableValue vv = new VariableValue(1);
		FunctionType ft = FunctionType.Sin;
		SpecialFunctions sf = new SpecialFunctions(vv, ft, 2);
		assertEquals(sf.evaluateForX(0), 0);
		// Check that sin(pi/2) == 1. 
		// Have to check "less than something" rather then equals because of floating point numbers.
		double diff = Math.abs(sf.evaluateForX(Math.PI / 2) - 1.0);
		assertEquals(diff < 0.00001, true);
		assertEquals(sf.isConstant(), false);
	}
	
	@Test
	void testCosinus() {
		VariableValue vv = new VariableValue(1);
		FunctionType ft = FunctionType.Cos;
		SpecialFunctions sf = new SpecialFunctions(vv, ft, 2);
		assertEquals(sf.evaluateForX(0), 1.0);
		// Check that cos(pi/2) == 0. 
		// Have to check "less than something" rather then equals because of floating point numbers.
		double diff = Math.abs(sf.evaluateForX(Math.PI / 2));
		assertEquals(diff < 0.00001, true);
		assertEquals(sf.isConstant(), false);
	}
	
	@Test
	void testExp() {
		VariableValue vv = new VariableValue(1);
		FunctionType ft = FunctionType.Exp;
		SpecialFunctions sf = new SpecialFunctions(vv, ft, 2);
		assertEquals(sf.evaluateForX(0), 1.0);
		// Have to check "less than something" rather then equals because of floating point numbers.
		double diff = Math.abs(sf.evaluateForX(Math.PI) - Math.exp(Math.PI));
		assertEquals(diff < 0.00001, true);
		assertEquals(sf.isConstant(), false);
	}
	
	@Test
	void testSqrt() {
		VariableValue vv = new VariableValue(1);
		FunctionType ft = FunctionType.Sqrt;
		SpecialFunctions sf = new SpecialFunctions(vv, ft, 2);
		assertEquals(sf.evaluateForX(4), 2);
		assertEquals(sf.evaluateForX(16), 4);
		assertEquals(sf.isConstant(), false);
	}
	
	@Test
	void testSqr() {
		VariableValue vv = new VariableValue(1);
		FunctionType ft = FunctionType.Sqr;
		SpecialFunctions sf = new SpecialFunctions(vv, ft, 2);
		assertEquals(sf.evaluateForX(4), 16);
		assertEquals(sf.evaluateForX(2), 4);
		assertEquals(sf.isConstant(), false);
	}

	@Test
	void testSqrOfSqrt() {
		VariableValue vv = new VariableValue(1);
		Constant c = new Constant(1, 1);
		Sum s1 = new Sum(vv, c, 2);
		FunctionType ft1 = FunctionType.Sqr;
		SpecialFunctions sf1 = new SpecialFunctions(s1, ft1, 2);
		FunctionType ft2 = FunctionType.Sqrt;
		SpecialFunctions sf2 = new SpecialFunctions(sf1, ft2, 3);
		
		assertEquals(sf2.evaluateForX(4), 4+1);
		assertEquals(sf2.evaluateForX(8), 8+1);
	}
	
	@Test
	void mutation() {
		VariableValue vv = new VariableValue(0);
		FunctionType ft1 = FunctionType.Sqr;
		SpecialFunctions sf1 = new SpecialFunctions(vv, ft1, 1);
		Expression sf_clone = sf1.cloneExpression();
		boolean has_mutated = false;
		while(!has_mutated) {
			has_mutated = sf1.mutate();
		}
		// The mutation could either change the function type or change the inner expression to a constant. So in any case the function may not behave like a parabola.
		boolean is_x_squared = sf1.evaluateForX(0)==0 && sf1.evaluateForX(1)==1 && sf1.evaluateForX(2)==4;
		boolean clone_is_x_squared = sf_clone.evaluateForX(0)==0 && sf_clone.evaluateForX(1)==1 && sf_clone.evaluateForX(2)==4;
		
		assertFalse(is_x_squared);
		assertTrue(clone_is_x_squared);
	}
	
}
