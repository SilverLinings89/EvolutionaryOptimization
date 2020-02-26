package com.kraft.evolopti;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class OptimizationRunner {
	List<Point2D.Double> EvaluationPoints;
	OptimizationCase optimizationCase;
	
	public static void main(String[] args) throws Exception {
		OptimizationRunner runner = new OptimizationRunner();
		runner.generateEvaluationPoints();
		runner.run();		
	}
	
	public void setEvaluationPoints(List<Point2D.Double> inEvaluationPoints) {
		this.EvaluationPoints = inEvaluationPoints;
	}
	
	// This function generates the evaluation points and pushes them into an array. 
	// Every point consists of an x and a y value.
	// The more Points are added the longer the optimization will take.
	List<Point2D.Double> generateEvaluationPoints() {
		EvaluationPoints = new ArrayList<Point2D.Double>();
		EvaluationPoints.add(new Point2D.Double(0.0,0.5));
		EvaluationPoints.add(new Point2D.Double(1.0,1.5));
		EvaluationPoints.add(new Point2D.Double(2.0,4.5));
		return EvaluationPoints;
	}
	
	public void run() throws Exception {
		int CandidatesAtStart = 1000;
		int SurvivorsPerGeneration = 200;
		int ProtectedMembersPerGeneration = 30;
		int NewRandomCandidatesPerGeneration = 300;
		int ChildrenPerSurvivor = 5;
		int MaxOptimizationSteps = 1000;
		int MaxExpressionDepth = 10;
		double Residual = 0.0001; // This value describes at how small of an error the scheme should stop. The smaller this value is, the longer the computation might take.
		optimizationCase = new OptimizationCase(CandidatesAtStart, SurvivorsPerGeneration, ProtectedMembersPerGeneration, NewRandomCandidatesPerGeneration, ChildrenPerSurvivor, MaxOptimizationSteps, MaxExpressionDepth, EvaluationPoints, Residual);
		optimizationCase.run();
	}
	
}
