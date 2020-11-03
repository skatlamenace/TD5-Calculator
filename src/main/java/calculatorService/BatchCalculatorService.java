package calculatorService;

import java.util.List;
import java.util.stream.Stream;

import calculator.model.CalculationModel;

public interface BatchCalculatorService {
	public List<CalculationModel> batchCalculate(Stream<String> operations);
}
