import java.util.HashMap;
import java.util.Map;

public class CalcRim {
    private CalcArab calcArab;
    private String digitRim1;
    private String digitRim2;
    private String znak;
    private int result;
    private String resultString;
    private static Map<Character, Integer> mapRimArab;

    public CalcRim(String digitRim1, String digitRim2, String znak) {
        this.digitRim1 = digitRim1;
        this.digitRim2 = digitRim2;
        this.znak = znak;
        calculate();
    }

    void calculate() { //основной метод для расчета
        mapRimArab = new HashMap<>(); //Мап со значениями римских знаков и соответсвующим значением числа
        mapRimArab.put('I', 1);
        mapRimArab.put('V', 5);
        mapRimArab.put('X', 10);
        mapRimArab.put('L', 50);
        mapRimArab.put('C', 100);
        mapRimArab.put('D', 500);
        mapRimArab.put('M', 1000);

        int number1 = rimToArab(digitRim1); //получаем первое число
        int number2 = rimToArab(digitRim2); //получаем второе число
        calcArab = new CalcArab(number1, number2, znak); //передаем аргументы в класс для расчета челочисленного значения
        result = calcArab.getResult(); //получаем результат
        //System.out.println(number1 + " " + this.znak + " " + number2 + " = " + result);
        if (result < 1) {
            throw new ArithmeticException();
        }
        resultString = arabToRim(result); //преобразуем челочисленный результат в римские цифры
    }

    int rimToArab(String rim) { //перевод римских знаков в целочисленное значение
        int result = 0;
        for (int i = 0; i < rim.length(); i++) {
            if (i == 0 || mapRimArab.get(rim.charAt(i)) <= mapRimArab.get(rim.charAt(i - 1))) //первый символ ложим в результат из мапы,
                //если символ не первый, сравниваем с предыдущим, если меньше или равно плюсуем к результату
                result += mapRimArab.get(rim.charAt(i));
            else
                result += mapRimArab.get(rim.charAt(i)) - 2 * mapRimArab.get(rim.charAt(i - 1));
            //если текущее число больше предыдущего, от текущего вычитаем двойной результат предыдущего
        }
        return result;
    }

    String arabToRim(int number) { //переводим целочисленное число в римское представление
        StringBuilder strBuilder = new StringBuilder();

        while (number >= 1000) {
            strBuilder.append("M");
            number -= 1000;
        }
        while (number >= 900) {
            strBuilder.append("CM");
            number -= 900;
        }
        while (number >= 500) {
            strBuilder.append("D");
            number -= 500;
        }
        while (number >= 400) {
            strBuilder.append("CD");
            number -= 400;
        }
        while (number >= 100) {
            strBuilder.append("C");
            number -= 100;
        }
        while (number >= 90) {
            strBuilder.append("XC");
            number -= 90;
        }
        while (number >= 50) {
            strBuilder.append("L");
            number -= 50;
        }
        while (number >= 40) {
            strBuilder.append("XL");
            number -= 40;
        }
        while (number >= 10) {
            strBuilder.append("X");
            number -= 10;
        }
        while (number >= 9) {
            strBuilder.append("IX");
            number -= 9;
        }
        while (number >= 5) {
            strBuilder.append("V");
            number -= 5;
        }
        while (number >= 4) {
            strBuilder.append("IV");
            number -= 4;
        }
        while (number >= 1) {
            strBuilder.append("I");
            number -= 1;
        }

        return strBuilder.toString();
    }

    public String getResult() {
        return resultString;
    }
}
