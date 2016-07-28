import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;
import java.lang.Integer;

public class IntegerSums {
    public static void main(String args[]) {

        IntegerSums t = new IntegerSums();
    	System.out.println("Enter target integer, followed by");
        System.out.println("2 to 5 integers, separated by spaces:");
        System.out.println("Sample Input: 12 2 24 2 -13 1");
        System.out.println("(where 12 is the target and the rest are usable ints)");

        Scanner in = new Scanner(System.in);
		String input = in.nextLine();

        String soln = t.evaluate(input);

        //Print solution or "No Solution Found."
        System.out.println(soln);

        //FOR TESTING:
        //t.test();
    }

    //Starts the program
    String evaluate(String input){
        String[] num_str = input.split(" ");

        ArrayList<String> nums = new ArrayList<String>();

        int target = 0;

        try {
            target = Integer.parseInt(num_str[0]); //Target
        } catch (NumberFormatException e) {
            System.out.println("Please use specified input format.");
            System.exit(0);
        }

        //Populate array of ints
        for (int i = 1; i<num_str.length; i++){
            nums.add(num_str[i].trim());
        }

        ArrayList<String> ops = new ArrayList<String>();
        ops.add("+");
        ops.add("-");
        ops.add("*");
        ops.add("/");

        //ArrayLists containing all permutation of ints and ops
        ArrayList<String> num_permutes = new ArrayList<String>();
        ArrayList<String> ops_permutes = new ArrayList<String>();

        //Fill num_permutes and ops_permutes arrays
        fillPermutes(num_permutes, nums, "");
        fillPermutes(ops_permutes, ops, "");

        //Interweave and evaluate
        return findCombinations(num_permutes, ops_permutes, target);
    }

    //Takes in an ArrayList all_permutes and fills it with all permutations of the source
    //ArrayList. String sofar used for recursive calls
    void fillPermutes(ArrayList<String> all_permutes, ArrayList<String> source, String sofar){
        if (source.isEmpty()){
            all_permutes.add(sofar);
        } else {
            for(String i : source){
                ArrayList<String> source2 = new ArrayList<String>();
                source2.addAll(source);
                source2.remove(i);
                fillPermutes (all_permutes,source2, sofar + " " + i);
            }
        }
    }

    //Given a string of numbers and a string of operators, function evaluates
    //the value of the expression starting from left to right. If at any point
    //the value matches the target, the expression is returned. If not, an
    //empty string is returned.
    String evaluateExpression(String numstring, String opstring, int target){
        String[] numelem = numstring.split("\\s+");
        String[] opselem = opstring.split("\\s+");

        double value = 0;

        try {
            value = (double)Integer.parseInt(numelem[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please use specified input format.");
            System.exit(0);
        }

        int opcounter = 0;

        String ntemp = numelem[0] + " ";
        String otemp = "";

        for(int i = 1; i<numelem.length; i++){
            value = performOp(opselem[opcounter].trim(), value, (double)Integer.parseInt(numelem[i]));
            ntemp += numelem[i] + " ";
            otemp += opselem[opcounter] + " ";

            if (value == (double)target){
                return(buildExpression(ntemp.trim(), otemp.trim()));
            }
            opcounter++;
        }

        return "";
    }

    //Performs integer arithmetic given two values and a String operator
    double performOp(String op, double val1, double val2){

        if (op.equals("*")){
            return val1*val2;
        } else if (op.equals("/")){
            return val1/val2;
        } else if (op.equals("-")){
            return val1-val2;
        } else if (op.equals("+")) {
            return val1+val2;
        } else {
            System.out.println("Invalid operator.");
            return -999999;
        }
    }

    // Builds the expression string and final answer given the
    // solution number string and operator string
    String buildExpression(String numstring, String opstring){
        String[] numelem = numstring.split("\\s+");
        String[] opselem = opstring.split("\\s+");

        String value = numelem[0];
        int opcounter = 0;

        for(int i = 1; i<numelem.length; i++){
            value += opselem[opcounter];
            value += numelem[i];
            value = "(" + value + ")";
            opcounter++;
        }

        return value;
    }

    // Checks every number permutation with every operator permutation.
    // Returns the solution or "No solution found."
    String findCombinations(ArrayList<String> num_permutes, ArrayList<String> ops_permutes, int target){
        for (String numstr : num_permutes){
            for (String opstr : ops_permutes){
                String sln = evaluateExpression(numstr.trim(), opstr.trim(), target);
                if (!(sln.equals(""))){
                    return sln;
                }
            }
        }
        return "No solution found.";
    }

    void test(){

        System.out.println("TESTS:");

        //2, 3, 4, 5 int inputs
        System.out.println(evaluate("34 30 4"));
        System.out.println(evaluate("69 140 2 1"));
        System.out.println(evaluate("9000 1000 4 5 1"));

        // all unique and all same inputs
        System.out.println(evaluate("32 16 2 3 4 8"));
        System.out.println(evaluate("1 1 1 1 1 1"));

        //no solution
        System.out.println(evaluate("12 1 1 1 1 1"));
        System.out.println(evaluate("423 423 123 903 746 908"));

        //negative values
        System.out.println(evaluate("30 -6 -5"));

        //negative target
        System.out.println(evaluate("-30 0 30"));

    }
}