package calculator;

public class StringCalculator {
    StringCalculator() {
    }

    // TODO: 향후 여기에 파싱, 예외처리, 덧셈 로직이 추가됩니다.
    public int add(String text) {
        int sum = 0;

        // text가 null이거나 비워있으면 0을 반환한다.
        if (text == null || text.isEmpty()){ return 0; }

        // 기본 구분자 ,와 : 관리
        String[] numbers = text.split("[,:]");

        for (String number : numbers) {
            try {
                //여기서 예외 발생 시 catch로 이동한다.
                int numberValue = Integer.parseInt(number);

                //음수체크 로직.
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
}
