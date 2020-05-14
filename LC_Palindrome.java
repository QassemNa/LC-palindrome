/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorthim2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author ME
 */
public class LC_Palindrome {
    private char A[];
    private char B[];
    private int S[][];
    private char list1[];
    private char list2[];
    private static long CTRrec=0;
    private static long CTRdp=0;
    private int M=0;
    private int N=0;
    private int memo[][];
    
    public int LCSrec(int i, int j,int memo[][]){// will solve the algorthim as mimoazation need to add thing into code to use this function
        CTRrec++;
        if(i==this.M || j==this.N)
            return 0;
        
        if(memo[i][j]!=0)
            return memo[i][j];
        
        if(this.A[i]==this.B[j]){
            memo[i][j]=1+ LCSrec(i+1,j+1, memo);
            return memo[i][j];
        }
        
        else{
            memo[i][j]= Integer.max(LCSrec(i+1,j,memo), LCSrec(i,j+1,memo));
            return memo[i][j];
        }
    }
    public int LCSrec(int i, int j){// will solve as recursion also change code to implement
        CTRrec++;
        if(i==this.M || j==this.N)
        return 0;

        if(this.A[i]==this.B[j]){
        return 1+LCSrec(i+1,j+1);}

        else
        return Integer.max(LCSrec(i+1,j), LCSrec(i,j+1));
}

    
    public void LCSdp(){
        
        for(int i=1;i<this.M+1;i++){
            for(int j=1;j<this.N+1;j++){
               CTRdp++;
               if(this.A[i-1]==B[j-1]){
                   this.S[i][j]=this.S[i-1][j-1]+1;
                   
               }
               else
                   this.S[i][j]=Integer.max(this.S[i-1][j], this.S[i][j-1]); 
            }
        }
        printLCSdp();
    } 
    public void printLCSdp(){
        //printing dynamic LCS 
        int index = S[this.M][this.N]; 
        int temp = index; 
    
        char[] lcs = new char[index+1]; 
        lcs[index] = ' '; // Set the terminating character 
        
        int i = this.M, j = this.N; 
        while (i > 0 && j > 0) 
        { 
            if (this.A[i-1] == this.B[j-1]) 
            { 
                lcs[index-1] = this.A[i-1];   
                i--;  
                j--;  
                index--;      
            }
            else if (S[i-1][j] > S[i][j-1]) 
                i--; 
            else
                j--; 
        } 
   
        // Print the lcs 
        
        System.out.print("LCS's length is: "+temp+" which is "); 
        for(int k=0;k<=temp;k++) 
            System.out.print(lcs[k]);
        System.out.println("");
        
        
    }
    
    public void initi(String a, String b){
       this.M=a.length();
       this.N=b.length();
       
       this.A = new char[this.M];
       this.B = new char[this.N];
       this.A=a.toCharArray();
       this.B=b.toCharArray();
       
       this.S = new int[this.M+1][this.N+1];
        for(int i=0;i<this.M+1;i++){
            this.S[i][0]=0;
        }
        for(int i=0;i<this.N+1;i++){
            this.S[0][i]=0;
        }
        
      /*
        This is for memoization if needed
      this.memo= new int[this.M][this.N];
      for(int i=0; i<this.M-1;i++){
          for(int j=0; j<this.N-1;j++)
              this.memo[i][j]=0;
      }
      */
    }
    
    public static void main(String[] args) throws FileNotFoundException {
       LC_Palindrome algo = new LC_Palindrome();
       Scanner input = new Scanner(new File("strings.txt"));
       String a=input.next();
       String b; //will hold the reverse of a
       char[] k;// hold the reverse of "a" as char
       
       k=new char[a.length()];
       char tmp;
       int z=0;
       for(int i=a.length()-1;i>=0;i--){
           k[z]=a.charAt(i);
           z++;
       }
       for(int i=0;i<=a.length()-1;i++){
           if(k[i]=='A')
               k[i] ='T';
           else if(k[i]=='T')
               k[i] ='A';
           else if(k[i]=='G')
               k[i]= 'C';
           else if(k[i]=='C')
               k[i]='G';
       }
       b=String.valueOf(k);
       
       System.out.println("Value of a is: "+a);
       System.out.println("Value of b is: "+b);
       algo.initi(a, b);//this will initilize the dynamic array and other transform the input text to array of char
       algo.LCSdp();//this will apply the LSC and print the solution
       
    }
    
}
