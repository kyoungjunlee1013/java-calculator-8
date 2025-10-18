package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    StringCalculator() {
    }

    public int add(String text) {
        int sum = 0;

        // null 또는 빈 문자열일 경우 0을 반환한다
        if (text == null || text.isEmpty()){
            return 0;
        }

        String[] numbers = splitByCustomDelimiter(text);

        for (String number : numbers) {
            try {
                //예외 발생 시 catch로 이동한다
                int numberValue = Integer.parseInt(number);

                //음수체크 로직
                if (numberValue < 0) {
                    throw new IllegalArgumentException("음수(" + numberValue + ")는 사용할 수 없습니다.");
                }

                sum += numberValue;

            }catch(NumberFormatException e){
                throw new IllegalArgumentException("숫자 이외의 값(\"" + number + "\")은 입력할 수 없습니다.", e);
            }
        }
        return sum;
    }
    //커스텀 구분자를 처리하고 숫자 문자열 배열을 반환하는 역할
    private String[] splitByCustomDelimiter(String text){
    // 커스텀 구분자가 없을 경우 기본 구분자
    String delimiter ="[,:]";
    String numberString = text;
    // 커스텀 구분자 패턴 확인
    if (text.startsWith("//") && text.contains("\n")){
        int firstNewLineIndex = text.indexOf("\n");

        // '//' 다음(인덱스 2)부터 '\n' 전까지의 문자를 커스텀 구분자로 추출
        String customDelimiter = text.substring(2, firstNewLineIndex);

        // '\n' 다음 위치부터 실제 계산할 숫자 문자열로 지정
        numberString = text.substring(firstNewLineIndex+1);

        //Pattern.quote문을 사용하여 커스텀 구분자를 안전하게 인용한다(정규표현식은 오류를 발생시킬 수 있기 때문.)
        delimiter = Pattern.quote(customDelimiter) + "|[,:]";
    }
    return numberString.split(delimiter);
    }
}
