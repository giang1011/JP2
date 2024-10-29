import java.util.HashSet;
import java.util.Set;

public class Main {


    public static boolean isDivisibleByThree(int x) {
        int sumOfDigits = 0;


        while (x > 0) {
            sumOfDigits += x % 10;
            x /= 10;
        }


        while (sumOfDigits >= 3) {
            sumOfDigits -= 3;
        }


        return sumOfDigits == 0;
    }

    public static void main(String[] args) {

        Set<Integer> numbers = new HashSet<>();
        numbers.add(123);
        numbers.add(1234);
        numbers.add(12345);


        Set<Integer> divisibleByThreeSet = new HashSet<>();


        for (int number : numbers) {
            if (isDivisibleByThree(number)) {
                divisibleByThreeSet.add(number);
            }
        }


        System.out.println("Các số chia hết cho 3: " + divisibleByThreeSet);
    }
}
