import java.text.NumberFormat;
import java.util.*;

public class Main {

    private static long number;

    public static void main(String[] args) {

        printMenu();
    }

    private static void printMenu() {
        System.out.println("Welcome to Amazing Numbers!\n");
        printInstructions();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("\nEnter a request: ");
            String stringInput = scanner.nextLine();

            if(stringInput.isBlank()){
                printInstructions();
                continue;
            }

            if(stringInput.equals("0")) break;

            String[] input = stringInput.split(" ");

            Validator validator = new Validator(input);

            if (!validator.validation()) {
                continue;
            }

            switch (input.length){
                case 1:
                    printPropertiesOfNumber(Long.parseLong(input[0]));
                    break;
                case 2:
                    propertiesOfRange(input);
                    break;
                default:
                    numOfSpecificProperties(input);
            }

        }
        scanner.close();
        System.out.println("\nGoodbye!");
    }

    private static void printInstructions() {
        System.out.println(" Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    private static void propertiesOfRange(String[] input){
        long firstNum = Long.parseLong(input[0]);
        long secondNum = Long.parseLong(input[1]);
        if(firstNum > secondNum) {
            secondNum += firstNum - 1;
        }
        for (;firstNum <= secondNum; firstNum++) {
            number = firstNum;
            printPropertiesOfNumberInLine();
        }
    }
//check if current property is true for the number
    private static boolean checkParameter(String parameter) {
        switch (parameter) {
            case "HAPPY":
                if (isHappy(number)){
                    return true;
                }
                break;
            case "SAD":
                if (!isHappy(number)) {
                    return true;
                }
                break;
            case "JUMPING":
                if (isJumping()) {
                    return true;
                }
                break;
            case "SUNNY":
                if (isSunny()) {
                   return true;
                }
                break;
            case "SQUARE":
                if (isSquare()) {
                    return true;
                }
                break;
            case "BUZZ":
                if (isBuzz()) {
                    return true;
                }
                break;
            case "DUCK":
                if (isDuck()) {
                    return true;
                }
                break;
            case "PALINDROMIC":
                if (isPalindromic()) {
                    return true;
                }
                break;
            case "GAPFUL":
                if (isGapful()) {
                    return true;
                }
                break;
            case "SPY":
                if (isSpy()) {
                    return true;
                }
                break;
            case "EVEN":
                if (isEven()) {
                    return true;
                }
                break;
            case "ODD":
                if (!isEven()) {
                    return true;
                }
                break;
            default:
                System.out.println("Check out isValid() method");
        }
        return false;
    }

    private static void numOfSpecificProperties(String[] input) {
        number = Long.parseLong(input[0]);
        int quantity = Integer.parseInt(input[1]);
        while (quantity > 0)
        point: {

            for (int i = 2; i < input.length; i++) {
                String property = input[i].toUpperCase();
                if (property.startsWith("-")){
                    if (checkParameter(property.substring(1))) {
                        number++;
                        break point;
                    }
                } else if (!checkParameter(property)) {
                    number++;
                    break point;
                }

            }
            printPropertiesOfNumberInLine();
            quantity--;
            number++;
        }
    }

    private static void printPropertiesOfNumberInLine() {
        StringBuilder property = new StringBuilder(number + " is ");

        if (isHappy(number)) property.append("happy, ");
        if (!isHappy(number)) property.append("sad, ");
        if (isJumping()) property.append("jumping, ");
        if (isSunny()) property.append("sunny, ");
        if (isSquare()) property.append("square, ");
        if (isBuzz()) property.append("buzz, ");
        if (isDuck()) property.append("duck, ");
        if (isPalindromic()) property.append("palindromic, ");
        if (isGapful()) property.append("gapful, ");
        if (isSpy()) property.append("spy, ");
        if (isEven()) property.append("even, ");
        else property.append("odd, ");

        property.delete(property.length() - 2, property.length() - 1);
        System.out.println(property);
    }

    private static void printPropertiesOfNumber(long num) {
        number = num;
        NumberFormat format = NumberFormat.getInstance(new Locale("en", "US"));

        System.out.println("Properties of " + format.format(num));

        System.out.printf("%13s %b%n", "happy:", isHappy(num));
        System.out.printf("%13s %b%n", "sad:", !isHappy(num));
        System.out.printf("%13s %b%n", "jumping:", isJumping());
        System.out.printf("%13s %b%n", "sunny:", isSunny());
        System.out.printf("%13s %b%n", "square:", isSquare());
        System.out.printf("%13s %b%n", "buzz:", isBuzz());
        System.out.printf("%13s %b%n", "duck:", isDuck());
        System.out.printf("%13s %b%n", "palindromic:", isPalindromic());
        System.out.printf("%13s %b%n", "gapful:", isGapful());
        System.out.printf("%13s %b%n", "even:", isEven());
        System.out.printf("%13s %b%n", "odd:", !isEven());
        System.out.printf("%13s %b%n", "spy:", isSpy());

    }

    private static boolean isNatural() {
        return number > 0;
    }

    private static boolean isSpy() {
        char[] numberInChar = String.valueOf(number).toCharArray();
        int sum = 0;
        int product = 1;
        for (char ch:
             numberInChar) {
            int value = Character.getNumericValue(ch);
            sum += value;
            product *= value;
        }
        return sum == product;
    }

    private static boolean isPalindromic() {
        String numberInString = String.valueOf(number);
        for (int i = 0; i < numberInString.length(); i++) {
            if(numberInString.charAt(i) != numberInString.charAt(numberInString.length() - (i + 1))) return false;
        }
        return true;
    }

    private static boolean isDuck() {
        String numberInString = String.valueOf(number);
        return numberInString.indexOf('0', 1) != -1;
    }

    private static boolean isEven() {
        return number % 2 == 0;
    }

    private static boolean isBuzz() {
        return number % 7 == 0 || number % 10 == 7;

    }

    private static boolean isGapful() {
        String numberInString = String.valueOf(number);
        if (numberInString.length() < 3) {
            return false;
        }
        int divider = Integer.parseInt(new String(new char[]{numberInString.charAt(0),
                                                                numberInString.charAt(numberInString.length() - 1)}));
        return number % divider == 0;

    }

    private static boolean isSquare() {
        return Math.sqrt(number) == (int) Math.sqrt(number);
    }

    private static boolean isSunny() {
        return Math.sqrt(++number) == (int) Math.sqrt(number--);
    }

    private static boolean isJumping() {
        String[] numInArray = String.valueOf(number).split("");
        if (numInArray.length > 1) {
            for (int i = 0; i < numInArray.length - 1; i++) {
                int digit = Integer.parseInt(numInArray[i]);
                int nextDigit = Integer.parseInt(numInArray[i + 1]);
                if (Math.abs(digit - nextDigit) != 1) return false;
            }
        }
        return true;
    }

    private static boolean isHappy(long num) {
        if (num == 1) {
            return true;
        }
        if (num == 37) {
            return false;
        }
        String[] numberInString = String.valueOf(num).split("");
        long sum = 0;
        for (String digit :
                numberInString) {
            sum += (long) Math.pow(Integer.parseInt(digit), 2);
        }
        return isHappy(sum);
    }

}

class Validator {

    private boolean isFirst = true;
    private boolean isSecond = true;
    //user input in parts, divided by " "
    private final String[] input;
    //list of finding out exclusives pair of properteis
    private final List<String> exclusives = new ArrayList<>(6);
    //the list of finding out invalid properties
    private final List<String> mismatches = new ArrayList<>();
    //the list of valid properties
    private final List <String> listOfProperties = new ArrayList<>(Arrays.asList("BUZZ", "DUCK",
            "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SUNNY",
            "SQUARE", "JUMPING", "HAPPY", "SAD")) ;

    public Validator(String[] input){
        this.input = input;
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].toUpperCase();
        }
    }

    public boolean validation() {

        if(input.length >= 1) isFirst = validFirstProperty(input[0]);
        if (input.length >= 2) isSecond = validSecondProperty(input[1]);
        if (input.length > 2) validMultiProperties();
        if (input.length > 3) checkExclusiveProperties();
        printErrors();
        return isFirst && isSecond && (mismatches.size() == 0) && (exclusives.size() == 0); //return true if validation is success.
    }
//print out errors of input
    private void printErrors() {
        if(!isFirst) {
            System.out.println("The first parameter should be a natural number or zero.");
        }
        if(!isSecond) {
            System.out.println("The second parameter should be a natural number.");
        }
        if (mismatches.size() > 1) {
            System.out.println("The properties " + mismatches + " are wrong.\n" +
                    "Available properties: " + listOfProperties);
        }

        if (exclusives.size() > 0) {
            System.out.println("The request contains mutually exclusive properties: " + exclusives);
            System.out.println("There are no numbers with these properties.");
        }



        if (mismatches.size() == 1) {
            System.out.println("The property " + mismatches + " is wrong.\n" +
                    "Available properties: " + listOfProperties);

        }
    }
//find out invalid first number
    private boolean validFirstProperty(String  property) {

        return property.matches("\\d+");
    }
//find out invalid second number
    private boolean validSecondProperty(String property) {

        return property.matches("[1-9]\\d*");
    }
//find out invalid properties. compare user's properties to the list of available properties
    private void validMultiProperties() {

        for (int i = 2; i < input.length; i++) {
            String property = input[i];
            if (property.startsWith("-")){
                property = property.substring(1);
            }
            if (!listOfProperties.contains(property)) {
                mismatches.add(input[i]);
            }
        }
    }

    private void checkExclusiveProperties() {
//list of user's properties
        List<String> list = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(input, 2, input.length)));
//check if the user's list contains opposite properties f.e. -property property
        //take fist prop and find out if it has un opposite pair and so on till the penultimate
        for (int i = 0; i < list.size() - 1; i++) {
            String property = list.get(i);
            if (property.startsWith("-")) {
                String oppositeProp = property.substring(1);
                if (list.contains(oppositeProp)) {
                    exclusives.add(property + ", " + oppositeProp);
                }
            } else {
                String oppositeProp = "-" + property;
                if (list.contains(oppositeProp)) {
                    exclusives.add(oppositeProp + ", " + property);
                }
            }
        }
// check if the user's list contains exclusive properties
        if (list.contains("-ODD") && list.contains("-EVEN")) exclusives.add("-ODD, -EVEN");
        if (list.contains("ODD") && list.contains("EVEN")) exclusives.add("ODD, EVEN");
        if (list.contains("SPY") && list.contains("DUCK")) exclusives.add("SPY, DUCK");
        if (list.contains("SUNNY") && list.contains("SQUARE")) exclusives.add("SUNNY, SQUARE");
        if (list.contains("HAPPY") && list.contains("SAD")) exclusives.add("HAPPY, SAD");
        if (list.contains("-HAPPY") && list.contains("-SAD")) exclusives.add("-HAPPY, -SAD");

    }

}

