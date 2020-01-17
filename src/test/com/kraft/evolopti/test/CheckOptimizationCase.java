package com.kraft.evolopti.test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

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

}
