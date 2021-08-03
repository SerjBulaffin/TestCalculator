public class CalcArab {
    private int digit1;
    private int digit2;
    private String znak;
    private int result;

    public CalcArab(int digit1, int digit2, String znak) {

        this.digit1 = digit1;
        this.digit2 = digit2;
        this.znak = znak;
        calculation(digit1, digit2, znak);
    }

    int calculation(int digit1, int digit2, String znak) {
        switch (znak) {
            case "+":
                result = digit1 + digit2;
                break;
            case "-":
                result = digit1 - digit2;
                break;
            case "*":
                result = digit1 * digit2;
                break;
            case "/":
                result = digit1 / digit2;
        }
        return result;
    }


    int getResult() {
        return result;
    }
}
