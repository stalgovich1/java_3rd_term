import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import
//step 1
class StringCalculator
{
    public int add(String digit)
    {
        List<String> delimiters = new ArrayList<String>(); //making lists for delimiters
        List<Integer> negatives = new ArrayList<Integer>(); //making lists for negatives

        int result = 0, symbol;

        if (digit.isEmpty())
        {
            return 0; //step 2
        }

        //step 4
        delimiters.add(","); //adding standart delimiters, working till the end of the program
        delimiters.add("\n");

        if (digit.charAt(0) == '/' && digit.charAt(1) == '/')//checking if slashes on the first position
        {
            //step 8
            String pat1 = "\\[(.*?)\\]";//group delims in square brackets
            String pat2 = "\\n(.+)"; //group all numbers after "\n"
            String pat3 = "//(.*?)\\n"; //group delims without square brackets

            // making pattern from string

            Pattern delimiters_pattern = Pattern.compile(pat1);
            Pattern delimiters_pattern_without_brackets = Pattern.compile(pat3);
            Pattern numbers_pattern = Pattern.compile(pat2);

            //looking for pattern
            Matcher delimiters_matcher = delimiters_pattern.matcher(digit.substring(2));
            Matcher delimiters_matcher_without_brackets = delimiters_pattern_without_brackets.matcher(digit);
            Matcher numbers_matcher = numbers_pattern.matcher(digit);

            //just looking for numbers in pattern
            if (numbers_matcher.find())
            {
                digit = numbers_matcher.group(1);
            }

            //just looking for delims in pattern

            if (!delimiters_matcher.find())
            {
                while (delimiters_matcher_without_brackets.find())
                {
                    delimiters.add(delimiters_matcher_without_brackets.group(1));
                }
            }else {
                do
                {
                    delimiters.add(delimiters_matcher.group(1));
                }while (delimiters_matcher.find());
            }
        }

        //sorting so that there is no mistake with different-length delims

        Collections.sort(delimiters, Collections.reverseOrder()); //sorting delimiters in reverse order
        for (String j : delimiters)
        {
            digit = digit.replace(j, " ");// Replacing delimiters with spaces
        }
        //step 3

        String[] split_numbers = digit.split(" ", -1); // Final split part

        //we receive an array with numbers that must be summed

        for (String character : split_numbers) //go through an array
        {
            if (character.isEmpty())
            {
                System.out.println("error, there is no number between delimiters!!!");
                return 0;
            }

            if (int_check(character))
            {
                symbol = Integer.parseInt(character);
                if (symbol >= 0) //positive check
                {
                    if (symbol <= 1000) result += symbol;
                }else
                {
                    negatives.add(Integer.parseInt(character));
                }
            }else
            {
                System.out.printf("error, not a number\n", character);
            }
        }
        if (!negatives.isEmpty())//checking if there is any negatives numbers in the list
        {

            System.out.println("Lower then 0: ");
        }

        return result;
    }

    public static boolean int_check(String number)
    {
        try
        {
            Integer.parseInt(number);
            return true;
        }catch (NumberFormatException ex)
        {
            return false;
        }
    }

}

public class Main
{
    public static void main(String[] gin)
    {
        StringCalculator calculator = new StringCalculator();
        System.out.println(calculator.add("//[***][*][**][123]\n2*6***2123-22")); //step 7
    }
}