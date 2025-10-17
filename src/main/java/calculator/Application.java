package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해주세요.");
        String text = Console.readLine();

        StringCalculator calculator = new StringCalculator();
        try {
            int result = calculator.add(text);

            //계산된 값을 출력.
            System.out.println("결과: " + result);
        }catch (IllegalArgumentException e){
            //StringCalculator에서 던지는 예외 처리를 하는 곳.
            System.out.println("오류 발생: " + e.getMessage());
        }
    }
}