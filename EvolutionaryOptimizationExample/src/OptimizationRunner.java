import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OptimizationRunner {
	static int ExpressionsAtStart = 1000;
	int MaxSteps = 10000;
	int MaxDepth = 10;
	int EvaluationPoints = 20;
	static int ChildrenPerSurvivor = 5;
	static int UnmutatedInNextGeneration = 30;
	static int ExpressionsToKeep = 200;
	int NewExpressionsPerGeneration = 300;
	double PerfectionThreshold = 0.0001;
	List<Expression> expressions;
	double[] x_coords, y_coords;
	double last_error = 0;
	
	public static void main(String[] args) {
		OptimizationRunner runner = new OptimizationRunner();
		runner.run();		
	}
	
	OptimizationRunner() {
		initialize_expressions();
		initialize_evaluation_points();
	}
	
	void initialize_expressions() {
		expressions = new ArrayList<Expression>();
		for(int i = 0; i < ExpressionsAtStart; i++) {
			expressions.add(ExpressionGenerator.GenerateInitial(MaxDepth)); 
		}
	}
	
	void initialize_evaluation_points() {
		x_coords = new double[EvaluationPoints];
		y_coords = new double[EvaluationPoints];
		Random random = new Random();
		for(int i = 0; i < EvaluationPoints; i++) {
			x_coords[i] = -5 + i;
			y_coords[i] = random.nextDouble() ;
		}
	}
	
	void run() {
		for(int step = 0; step < MaxSteps; step++) {
			mutate_expressions();
			if(check_perfect()) break;
			sort_expression_list();
			remove_bad_expressions();
			fill_expressions_list();
			simplify_expressions();
			write_outputs(step);
		}
	}
	
	boolean check_perfect() {
		for(int i = 0; i < expressions.size(); i++) {
			if(compute_error_for_expression(expressions.get(i)) < PerfectionThreshold) {
				System.out.println("Found perfect solution: " + expressions.get(i).toString());
				System.out.println("Error: " + compute_error_for_expression(expressions.get(i)));
				return true;
			};
		}
		return false;
	}
	
	void simplify_expressions() {
		for(int i = 0; i < expressions.size(); i++) {
			expressions.get(i).simplify();
		}
	}
	
	void sort_expression_list() {
		Collections.sort(expressions, (d1, d2) -> {
			return compare(d1,d2);
		});
		Collections.reverse(expressions);
	}

	void remove_bad_expressions() {
		;
		while(expressions.size() > ExpressionsToKeep) {
			expressions.remove(ExpressionsToKeep);
		}
	}
	
	void fill_expressions_list() {
		for(int i = 0; i < ExpressionsToKeep; i++) {
			for(int j = 0; j < ChildrenPerSurvivor; j++) {
				Expression new_expression = expressions.get(i).cloneExpression();
				expressions.add(new_expression);	
			}
			
		}
		for(int i = 0; i < NewExpressionsPerGeneration; i++) {
			Expression new_expression = ExpressionGenerator.GenerateInitial(MaxDepth);
			expressions.add(new_expression);
		}
	}
	
	void mutate_expressions() {
		for(int i = UnmutatedInNextGeneration; i < expressions.size(); i++) {
			boolean has_mutated = false;
			while(!has_mutated) {
				has_mutated = expressions.get(i).mutate();
			}	
		}
	}
	
	void write_outputs(int step) {
		double current_best_approximation = compute_error_for_expression(expressions.get(0));
		if(current_best_approximation != last_error) {
			System.out.println("Report for step: " + step);
			System.out.println("Best expression: " + expressions.get(0).toString());
			System.out.println("Error evaluates to: " + current_best_approximation);
			last_error = current_best_approximation;
		}
	}
	
	int compare(Expression a, Expression b) {
		double error_a = compute_error_for_expression(a);
		double error_b = compute_error_for_expression(b);
		if(Double.isNaN(error_a)) {
			if(Double.isNaN(error_b)) {
				return 0;
			} else {
				return -1;
			}
		}
		if(Double.isNaN(error_b)) {
			return 1;
		}
		if(error_a == error_b) {
			return 0;
		}
		if(error_a <= error_b) {
			return 1;
		}
		return -1;
	}
	
	double compute_error_for_expression(Expression expression) {
		double error = 0;
		for(int i = 0; i < EvaluationPoints; i++) {
			error += Math.abs(expression.evaluateForX(x_coords[i]) - y_coords[i]);
		}
		return error;
	}
}
