/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Page;

/**
 *
 * @author Lucky
 */
public class coretan {
    
    public static double[] returnArray( )  
    {  
        double[] arr = new double [3];    // Creating an array of 3 elements  
        arr[0]=6.9;  
        arr [1]=2.5;  
        arr [2]=11.5;  
        return arr;
    }  
    public static void main(String[] args)  
    {  
        double[] a;         //variable to store returned array  
        a = returnArray();      //called method  
        for (int i = 0; i < a.length; i++) //for loop to print the array  
        System.out.println( a[i]+ " ");     
    }  
}
