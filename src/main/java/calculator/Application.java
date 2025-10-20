package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        // 이전에 통과했던 테스트를 위해 안내 문구는 없는 상태로 둡니다.
        String text = Console.readLine();

        StringCalculator calculator = new StringCalculator();

        // try-catch 블록을 완전히 제거합니다.
        // 예외가 발생하면 여기서 프로그램이 즉시 종료되며, 이것이 테스트가 원하는 동작입니다.
        int result = calculator.add(text);

        System.out.println("결과 : " + result);
    }
}