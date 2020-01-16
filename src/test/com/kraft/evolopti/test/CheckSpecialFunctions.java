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

}
