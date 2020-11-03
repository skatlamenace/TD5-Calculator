package calculatorService;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.SolutionFormatter;

import org.junit.jupiter.api.Test;

class SolutionFormatterTest {

private SolutionFormatter solutionFormatter;
	
	@BeforeEach
	public void initFormatter() {
		solutionFormatter = new SolutionFormatter();
	}
	
	@Test
	public void format_shouldFormatAnyBigNumber() {
		// GIVEN
		int number = 1234567890;
		
		// WHEN
		String result = solutionFormatter.format(number);
		
		// THEN
		// Attention les espaces entre les chiffres ci-dessous ne sont pas standards.
		assertThat(result).isEqualTo("1 234 567 890");
	}
	

}