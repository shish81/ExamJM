package com.company;

import java.util.List;
import java.util.Scanner;

class CalcForJm {

    Scanner scanner = new Scanner(System.in);

    boolean isFail = false;
    int num1 = 0;
    int num2 = 0;
    String operation = "";

    boolean isRim1 = false;
    boolean isRim2 = false;

    // Конструктор
 public CalcForJm() {

        while (true) {
            isFail = false;
            num1 = 0;
            num2 = 0;
            operation = "";
            isRim1 = false;
            isRim2 = false;
            System.out.println("Input:");


            // Получаем сроку ввода калькулятора
            String cmdCalc = scanner.nextLine();

            ComeHere cmd = new ComeHere(cmdCalc);


            // Получаем массив значений (разбиваем строку калькулятора на числа и операции над ними)
            List<String> dataCalc = cmd.readComeHere();

            if (dataCalc == null){
                return;
            }

            if (isFail) return;

            parserDataCmd(dataCalc);
            if (isFail) return;

            if(num1 < 0 || num1 > 10 || num2 <0 || num2 > 10){
                System.out.println("Введенный числа должны быть в диапазоне от 0 до 10");
                return;
            }


int resultOperation = runOperation();
            if (isFail) return;

            if (isRim1 && !isRim2)
            {
                System.out.println("Не верный формат.");
                return;
            }
            if (!isRim1 && isRim2)
            {

                System.out.println("Не верный формат.");
                return;
            }

            // Если два числа явяллись римскими то ответ будет выведен в римские символы
            if (isRim1 && isRim2)
            {
                int[] mas1 = {1000, 900, 500,400,100,90,50,40,10,9,5,4,1};
                String[] mas2 = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
                int i;
                i = 0;
                String s = "";
                // Крутим цикл пока n > 0
                while (resultOperation > 0)
                {
                    if (mas1[i] <= resultOperation) {
                        // i - элемент массива арабских цифр меньше либо равен числу n то
                        // от числа вычитаем его эквивалент в массиве арабском
                        resultOperation = resultOperation - mas1[i];
                        // в строку записываем его римское значение
                        s = s + mas2[i];
                    }
else {
                        i++;
                    }
                }

                System.out.println("Output:");
                System.out.println(s);}
else {
                System.out.println("Output:");
                System.out.println(resultOperation);
            }

        }

    }



    void parserDataCmd(List<String> data){
        num1 = getNum(data.get(1));
        if (isFail) return;
        num2 = getNum(data.get(2));
        if (isFail) return;
        operation = data.get(0);

    }

    /**
     * Получить число для калькулятора
     *data Строка с числом
     * Полученное число
     */
    int getNum(String data){

        if (tryParseInt(data)) {
            // Работа с арабскими числами
            return Integer.parseInt(data);
        }else{

            // Работа с римскими числами
            int[] arab = new int[] {10,1,2,3,4,5,6,7,8,9};
            String[] rome = new String[] { "X","I","II","III","IV","V","VI","VII","VIII","IX"};

            // Проверяем римские числа
            for (int i = 0; i < rome.length; i++){
                if (data.equals(rome[i])){

                    // Проверяем сколько числ являлись римскими
                    if (isRim1 && !isRim2) isRim2 = true;
                    if (!isRim1 && !isRim2) isRim1 = true;
                    return arab[i];
                }
            }

            // Римское число не найденно
            System.out.println("Недопустимое значение одной из переменной (пример, 3 или III)");
            isFail = true;
            return 0;
        }
    }

    /**
     Выполение операции в калькуляторе
     Результат операции
     */


    int runOperation(){
        switch (operation){
            case "+":
                // Сложение
                return num1 + num2;
            case "-":
                // Вычетание
                return num1 - num2;
            case "*":
                // Уножение
                return num1 * num2;
            case "/":
                // Деление
                try {
                    return num1 / num2;
                }
                catch (Exception ex){
                    System.out.println("Ошибка при операции деления");
                    System.out.println(ex.getMessage());
                    isFail = true;
                    return 0;
                }
            default:
                // Недопустимое значение операции
                System.out.println("Недопустимое значение операции (пример, +, -, * или -)");
                isFail = true;
                return 0;
        }
    }


    /**
     * Проверка на парсер строки в число
     value Строка с числом
     TRUE|FALSE
     */
    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}