import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnalizierString {
    private String input;
    private String arrInput[];
    private String output1;
    private String output2;
    private String znak;
    private CalcArab calcArab;
    private CalcRim calcRim;
    private String resultString;
    private List<Character> listChar = new ArrayList<>();

    public AnalizierString(String input) {
        this.input = input;
        analize();
    }

    void analize() {
        boolean argument1 = false;
        boolean argument2 = false;
        int digit1 = 0;
        int digit2 = 0;

        if (input.contains("+")) {
            znak = "+";
        } else if (input.contains("*")) {
            znak = "*";
        } else if (input.contains("/")) {
            znak = "/";
        } else if (input.contains("-")) {
            znak = "-";
        } else {
            throw new IllegalArgumentException();
        }

        arrInput = input.split("[+*/-]"); //парсим входную строку на массив строк

        if (arrInput.length > 2) { //если массив больше двух, исключение.
            throw new IllegalArgumentException();
        } else { // если все проверки пройдены, присваиваем значения из массива
            output1 = arrInput[0].trim();
            output2 = arrInput[1].trim();
        }

        try { //парсим число из строки, если все ок, кладем в digit1 число
            digit1 = Integer.parseInt(output1);
            argument1 = true; //булевому значению присваиваем true
        } catch (NumberFormatException e) {
            argument1 = false; //если парсинг не прошел, тогда false
        }

        try { //парсим второй
            digit2 = Integer.parseInt(output2);
            argument2 = true;
        } catch (NumberFormatException e) {
            argument2 = false;
        }

        if (argument1 && argument2) { //если первый и второй argument true, значит на входе два числа
            if (digit1 < 1 || digit2 < 1 || digit1 > 10 || digit2 > 10) { //если первое и второе число меньше 1 или больше 10, исключение
                throw new IllegalArgumentException();
            }
            calcArab = new CalcArab(digit1, digit2, znak); //вычисляем значение
            resultString = String.valueOf(calcArab.getResult()); //присваимваем результат выходной строке
        } else if (!argument1 && !argument2) { //если первый и второй argument false, значит на входе две строки
            listChar.add('I'); //заполняем лист римскими знаками
            listChar.add('V');
            listChar.add('X');
            listChar.add('L');
            listChar.add('C');
            listChar.add('D');
            listChar.add('M');

            rimEquals(arrInput[0].trim().toCharArray()); //проверяем первую строку на соответсвие римским знакам
            rimEquals(arrInput[1].trim().toCharArray()); ////проверяем вторую строку на соответсвие римским знакам
            calcRim = new CalcRim(arrInput[0].trim(), arrInput[1].trim(), znak); //если rimEquals не выкинул исключение, вычисляем значение
            resultString = calcRim.getResult(); //присваимваем результат выходной строке
        } else { //если на входе число + строка или наоборот, выкидываем исключение
            throw new IllegalArgumentException();
        }
    }

    public void rimEquals(char arr[]) { //проверка на совпадение входных данных с римскими цифрами
        for (int i = 0; i < arr.length; i++) {
            if (listChar.contains(arr[i])) { //если List ссодержит текущий элемент продолжаем цикл
                continue;
            } else { //при первом несовпадении выкидываем исключение
                throw new IllegalArgumentException();
            }
        }
    }

    public String returnResult() {
        return resultString;
    }
}
