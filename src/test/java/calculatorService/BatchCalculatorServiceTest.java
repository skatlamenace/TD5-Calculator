package calculatorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import calculator.Calculator;
import calculator.model.CalculationModel;
import calculator.model.CalculationType;



public class BatchCalculatorServiceTest {

	BatchCalculatorService batchCalculatorServiceNoMock;

	@BeforeEach
	public void init() {
		batchCalculatorServiceNoMock = new BatchCalculatorServiceImpl(
				new CalculatorServiceImpl(new Calculator(),
						new SolutionFormatterImpl()));
	}

	@Test
	public void givenOperationsList_whenbatchCalculate_thenReturnsCorrectAnswerList()
			throws IOException, URISyntaxException {
		// GIVEN
		final Stream<String> operations =
		    Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();

		// WHEN
		final List<CalculationModel> results =
		    batchCalculatorServiceNoMock.batchCalculate(operations);

		// THEN
		assertThat(results)
		    .extracting(CalculationModel::getSolution)
		    .containsExactly(4, 1, 48, 3);
	}

}


