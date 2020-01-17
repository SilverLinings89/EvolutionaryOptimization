package com.kraft.evolopti.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kraft.evolopti.Expression;
import com.kraft.evolopti.OptimizationCase;
import com.kraft.evolopti.OptimizationRunner;

class CheckOptimizationCase {

	@Test
	void compareExpressions() {
		OptimizationRunner or = new OptimizationRunner();
		boolean errorWasThrown = false;
		List<Point2D.Double> EvaluationPoints = new ArrayList<Point2D.Double>();
		EvaluationPoints.add(new Point2D.Double(0.0,0.0));
		EvaluationPoints.add(new Point2D.Double(1.0,1.0));
		EvaluationPoints.add(new Point2D.Double(2.0,4.0));
		or.setEvaluationPoints(EvaluationPoints);
		try {
			or.run();
		} catch(Exception e) {
			errorWasThrown = true;
		}
		assertFalse(errorWasThrown);
	}
	
	@Test
	void run30Steps() {
		List<Point2D.Double> EvaluationPoints = new ArrayList<Point2D.Double>();
		EvaluationPoints.add(new Point2D.Double(0.0,0.5));
		EvaluationPoints.add(new Point2D.Double(1.0,1.5));
		EvaluationPoints.add(new Point2D.Double(2.0,4.5));
		boolean error_thrown = false;
		OptimizationCase optimizationCase;
		try {
			optimizationCase = new OptimizationCase(100, 200, 30, 300, 5, 30, 10, EvaluationPoints, 0.01);
			optimizationCase.run();
			double Residual = optimizationCase.getBestResiudal();
			Expression Solution = optimizationCase.getBestSolution();
			
			assertTrue(Residual < 1.5);
			assertFalse(Solution.toString().contentEquals(""));
		} catch (Exception e) {
			error_thrown = true;
		}
		assertFalse(error_thrown);	
	}

}
