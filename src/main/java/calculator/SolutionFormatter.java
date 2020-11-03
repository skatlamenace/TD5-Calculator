package calculator;

public class SolutionFormatter implements calculatorService.SolutionFormatter {
    public String format(int solution) {
        return String.format("%,d", solution);
    }
}
