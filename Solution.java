import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double size = (double)n;
        int arr[] = new int[n];
        for(int arr_i=0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        double a = 0;
        double b = 0;
        double c = 0;
        for (int i = 0; i<n; i++){
            if (arr[i] > 0){
                a=a+1;
            } else if (arr[i] < 0){
                b=b+1;
            } else {
                c=c+1;
            }
        }
        a = a/size;
        b = b/size;
        c = c/size;
        
        System.out.printf("%f", a);
        System.out.print("\n");
        System.out.printf("%f", b);
        System.out.print("\n");
        System.out.printf("%f", c);
    }
}

