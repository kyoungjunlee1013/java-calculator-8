package calculator;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 문자열을 입력받아 숫자들의 합을 계산하는 계산기 클래스.
 * 순수한 계산 로직의 책임만을 가진다.
 */
public class StringCalculator {

    // 코드의 의도를 명확하게 드러내는 상수 정의
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String DELIMITER_SUFFIX = "\n";
    private static final String DEFAULT_DELIMITERS_REGEX = "[,:]";
    private static final int CUSTOM_DELIMITER_START_INDEX = 2;

    StringCalculator() {
    }

    /**
     * 문자열에 포함된 숫자들의 합을 반환하는 메인 메서드.
     * 전체적인 작업 흐름을 제어한다.
     * @param text 계산할 숫자 문자열
     * @return 숫자들의 합
     */
    public int add(String text) {
        // 1. 입력값이 null이거나 비어있으면 0을 반환 (Guard Clause)
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        String processedText = text.replace("\\n", "\n");
        if(processedText.trim().isEmpty()){
            return 0;
        }

        // 2. 문자열을 숫자 문자열 배열로 분리
        String[] stringNumbers = split(processedText);

        // 3. 숫자 문자열 배열을 정수 배열로 변환 (음수 검증 포함)
        int[] numbers = toInts(stringNumbers);

        // 4. 정수 배열의 합계를 반환
        return sum(numbers);
    }

    /**
     * 입력된 문자열을 규칙에 따라 숫자 문자열 배열로 분리한다.
     * 커스텀 구분자와 기본 구분자 로직을 명확히 분리한다.
     */
    private String[] split(String text) {
        String[] numbers;
        if (text.startsWith(CUSTOM_DELIMITER_PREFIX) && text.contains(DELIMITER_SUFFIX)) {
            int endOfDelimiterIndex = text.indexOf(DELIMITER_SUFFIX);
            String customDelimiter = text.substring(CUSTOM_DELIMITER_START_INDEX, endOfDelimiterIndex);
            String numberString = text.substring(endOfDelimiterIndex + 1);

            numbers = numberString.split(Pattern.quote(customDelimiter));
        } else {
            numbers = text.split(DEFAULT_DELIMITERS_REGEX);
        }

        return Arrays.stream(numbers)
                .filter(number -> !number.isEmpty())
                .toArray(String[]::new);
    }

    /**
     * 문자열 배열을 정수 배열로 변환한다.
     * 변환 과정은 parseAndValidate 메서드에 위임한다.
     */
    private int[] toInts(String[] stringNumbers) {
        return Arrays.stream(stringNumbers)
                .mapToInt(this::parseAndValidate)
                .toArray();
    }

    /**
     * 문자열 하나를 정수로 변환하고 음수인지 검증한다.
     */
        private int parseAndValidate(String number) {
        try {
            int parsedNumber = Integer.parseInt(number);
            if (parsedNumber < 0) {
                // 요구사항에 따라 잘못된 값 입력 시 IllegalArgumentException 발생
                throw new IllegalArgumentException("음수는 사용할 수 없습니다.");
            }
            return parsedNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 이외의 값은 입력할 수 없습니다.", e);
        }
    }

    /**
     * 정수 배열의 합계를 구한다.
     */
    private int sum(int[] numbers) {
        return Arrays.stream(numbers).sum();
    }
}