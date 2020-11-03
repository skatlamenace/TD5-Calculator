package calculatorService;

import java.util.Locale;

public class SolutionFormatterImpl implements SolutionFormatter {

	@Override
	public String format(int solution) {
		return String.format(Locale.FRENCH, "%,d", solution);
	}
}