package com.kraft.evolopti.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kraft.evolopti.OptimizationRunner;

class CheckOptimizationRunner {

	@Test
	void passWrongEvaluationPoints() {
		boolean errorWasThrown = false;
		OptimizationRunner or = new OptimizationRunner();
		
		List<Point2D.Double> EvaluationPoints = new ArrayList<Point2D.Double>();
		EvaluationPoints.add(new Point2D.Double(0.0,0.5));
		EvaluationPoints.add(new Point2D.Double(0.0,1.5));
		EvaluationPoints.add(new Point2D.Double(2.0,4.5));
		or.setEvaluationPoints(EvaluationPoints);
		try {
			or.run();
		} catch(Exception e) {
			errorWasThrown = true;
		}
		assertTrue(errorWasThrown);
	}

}
