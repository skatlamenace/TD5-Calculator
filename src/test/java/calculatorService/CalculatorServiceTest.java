package calculatorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.Random;

import calculator.Calculator;
import calculator.model.CalculationModel;
import calculator.model.CalculationType;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {


	@Mock
	Calculator calculator;

	@Mock
	calculator.SolutionFormatter solutionFormatter;

	CalculatorService classUnderTest;

	@BeforeEach
	public void init() {
		classUnderTest = new CalculatorServiceImpl(calculator, solutionFormatter);
	}

	@Test
	public void calculate_shouldUseCalculator_forAddition() {
		// GIVEN
		when(calculator.add(1, 2)).thenReturn(3);

		// WHEN
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();

		// THEN
		verify(calculator).add(1, 2);
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	public void calculate_shouldUseCalculator_forAddition_withNegativeNumber() {
		// GIVEN
		when(calculator.add(-1, 2)).thenReturn(1);

		// WHEN
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, -1, 2)).getSolution();

		// THEN
		verify(calculator).add(-1, 2);
		assertThat(result).isEqualTo(1);
	}
	
	@Test
	public void calculate_shouldUseCalculator_forSubstraction() {
		// GIVEN
		when(calculator.sub(3, 2)).thenReturn(1);

		// WHEN
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.SUBTRACTION, 3, 2))
				.getSolution();

		// THEN
		verify(calculator).sub(3, 2);
		assertThat(result).isEqualTo(1);
	}

	@Test
	public void calculate_shouldUseCalculator_forMultiplication() {
		// GIVEN
		when(calculator.multiply(-3, 2)).thenReturn(-6);

		// WHEN
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.MULTIPLICATION, -3, 2))
				.getSolution();

		// THEN
		verify(calculator).multiply(-3, 2);
		assertThat(result).isEqualTo(-6);
	}

	@Test
	public void calculate_shouldUseCalculator_forDivision() {
		// GIVEN
		when(calculator.divide(6, 3)).thenReturn(2);

		// WHEN
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.DIVISION, 6, 3))
				.getSolution();

		// THEN
		verify(calculator).divide(6, 3);
		assertThat(result).isEqualTo(2);
	}
	
	@Test
	public void calculate_shouldUseCalculator_forAnyAddition() {
		// GIVEN
		final Random r = new Random();
		when(calculator.add(any(Integer.class), any(Integer.class))).thenReturn(3);

		// WHEN
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION,
				    r.nextInt(), r.nextInt())).getSolution();

		// THEN
		verify(calculator, times(1)).add(any(Integer.class), any(Integer.class));
		verify(calculator, never()).sub(any(Integer.class), any(Integer.class));
		assertThat(result).isEqualTo(3);
	}
	
	@Test
	public void calculate_shouldThrowIllegalArgumentException_forADivisionBy0() {
		// GIVEN
		when(calculator.divide(1, 0)).thenThrow(new ArithmeticException());

		// WHEN
		assertThrows(IllegalArgumentException.class, () -> classUnderTest.calculate(
				new CalculationModel(CalculationType.DIVISION, 1, 0)));

		// THEN
		verify(calculator, times(1)).divide(1, 0);
	}
	
	@Test
	public void calculate_shouldFormatSolution_forAnAddition() {
		// GIVEN
		when(calculator.add(10000, 3000)).thenReturn(13000);
		when(solutionFormatter.format(13000)).thenReturn("13 000");

		// WHEN
		final String formattedResult = classUnderTest
		    .calculate(new CalculationModel(CalculationType.ADDITION, 10000, 3000))
		    .getFormattedSolution();

		// THEN
		assertThat(formattedResult).isEqualTo("13 000");
	}

	@Test
	public void add_returnsTheSum_ofZeroAndZero() {
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, 0, 0)).getSolution();
		assertThat(result).isEqualTo(0);
	}

}

