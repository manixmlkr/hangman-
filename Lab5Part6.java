/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author manix
 */
public class Lab5Part6 {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) 
    {
        int numLetters = 30;  // 1.c) Change to match words/phrases in MakeWordArray
        int numberOfWordsToGuess = 30;// 1.d) Change to match words in MakeWordArray
        String wordToGuess;
        char[] guesses = new char[numLetters];
        char[] correctGuess = new char[numLetters];
        char[][] wordArray = new char [30][30];
        // 1.a)  Declare a 2D array of at least 30 rows and 30 columns to 
        // hold the words that can be guessed by the user.  Just
        // declare the array here and initialize its size.
        String printGuess;
        int count = 0;
        int wrongCount = 0;
        int inWord = 0;
        int indexWord = 0;
        int noBlank = 0;
        boolean done = false;
        Scanner input = new Scanner(System.in);
        /*
            This is a hangman style game 
        */
        // 1.e) call MakeWordArray method to put words into 2D array
        numberOfWordsToGuess = MakeWordArray(wordArray);
        //System.out.println("number of words is "+ numberOfWordsToGuess);
        
        
        boolean playAgain=true;
          while(playAgain=true){
        /*   Choose a word   */
        int r = (int)(Math.random() * numberOfWordsToGuess);
        
        wordToGuess = selectWord(r ,wordArray     );// 2.b) pass in array as second parameter
        int wordLength= wordToGuess.length();
              System.out.println("there are   "+ wordLength + "  letters in your word");
        
        //System.out.println("DEBUG: Random numb er was "+r+" giving word: **"+wordToGuess+"**");
        
       
      
        
        
        System.out.println("Welcome to the game!  ");
        System.out.println("Enter letters to guess the word.  Get done before the doll is hung!\n");
        for (int i= 0; i < numLetters; i++)
        {
            guesses[i] = ' ';
            correctGuess[i] = ' ';
        }
        
        while (!done)
        {
            // 6)  Give the user an indication of the number of letters in the
            // selected word.  You can print blanks or you can just tell how
            // many letters are in the word or do something else to give this info.
            
            System.out.print("\nPlease enter a lowercase letter for your guess: ");
            guesses[count] = input.next().charAt(0);  // What is done here?
            /*
                Is the letter in the selected word?
            */
            boolean newChar=true;
            do{
                newChar=true;
                for(int i=0; i<count; i++){
                    if(guesses[count]== guesses[i]){
                            newChar=false;
                            
                            System.out.println("The character you have entered has already been guessed :)");
                            guesses[count] = input.next().charAt(0);
                    }
                    
                }
                
                
            }while(newChar==false);
            
            if (wordToGuess.indexOf(guesses[count]) == -1)
            {   // The letter is not in the word
                
                if ((wrongCount = hangDoll(wrongCount)) > 7)  // 3.a) Why does this check > 7?
                {
                    done = true;
                    System.out.println("\nSo sorry. You lose.");
                }

            }
            else
            {   // 3.b)  Does this section correctly record the guesses?  Why or why not?
                // 3.c)  If needed, correct this section to record all guesses.
                // 3.d)  What happens if the user guesses a letter they have already used?
                // 3.e)  Indicate to the user if they guess a letter they already used.
                inWord=0;
                while(wordToGuess.substring(inWord,wordToGuess.length()).indexOf(guesses[count])!=-1)
                {
                indexWord = wordToGuess.indexOf(guesses[count], inWord);
                correctGuess[indexWord] = guesses[count];
                inWord=indexWord +1;
                }
                
                printGuess = String.valueOf(correctGuess);
                noBlank = printGuess.indexOf(' ');
                printGuess = printGuess.substring(0, wordToGuess.length());
                System.out.println("**"+printGuess+"**");
                if (wordToGuess.equalsIgnoreCase(printGuess))
                {
                    done = true;
                    System.out.println("\nGreat! You win!");
                    System.out.println("You are a "+ wordToGuess +" student!");
                }                
            }
            
            count++;
        }  
        System.out.println("Do you want to play again? (Y/N)");
        String yn= input.next();
        yn = yn.toUpperCase();
        char test= yn.charAt(0);
        if(test=='Y'){
            playAgain=true;
        }else {
            playAgain=true;
            done=false;
        }
        
        
        
        
    }//while(playAgain) ends here
        // 4.a) Modify the game so that the user can play multiple times if desired.
        System.out.println("\nThank you for playing!");
    }
    
    public static int MakeWordArray(char[][] words){
        File mFile = new File("words.txt");
        Scanner inFile;
        try{
        
            //Scanner inFile = new Scanner(inputFileVar);
            inFile = new Scanner(mFile);
        }
        catch (FileNotFoundException fnf)
        {
            System.out.println("File not found");
            inFile = new Scanner (System.in);
            
        }
        int wordlength;
        String Wordline;
        int i = 0;
        while (inFile.hasNext())
        {
         Wordline = inFile.nextLine();
            //System.out.println("wordline    "+Wordline);
         wordlength = Wordline.length();
         for (int j = 0; j<wordlength;j++)
         {
             words[i][j] = Wordline.charAt(j);
            // System.out.printf("%c",words[i][j]);
         }
         i++;
            System.out.println("");
         
        }
        return ++i;
        
    }
    /** 1.b)  MakeWordArray is a method that takes in a 2 dimensional array and 
     * fills it with words that the player will guess. 
     * Use the multi-dimensional array that is passed in which 
     * must have at least 30 rows and 30 columns.  Assign the 
     * following words to the array and add words or phrases 
     * so that there are at least 20 positive words to choose 
     * from that could go in the sentence shown below. Words/phrases 
     * should be of length 6 or more  

        "achieving"
        "successful"
        "happy"
        "excited"
        "beneficial"
        "powerful"
        "diligent"
        "exceptional"
        "terrific"
        "outstanding"

        Your additional words/phrases must positively complete the 
        following sentence:

        "You are a _______ student!"
    
    */
    
    
    /**  Select a word from the possible words
     * in the program.  A random number is sent into 
     * the method and a string is returned.
     * @param roll - a random number 
     * @return  
     */
    public static String selectWord(int roll ,char[][]words   )  // 2.a)add the word array as parameter    
    {
        // 2.c) Select the row in the word array
        // indicated by 'roll' to be the chosen 
        // word for the game. Turn the array into 
        // a string and return the string of the word.
         String word = "" ;
        for( int i=0;i<=roll;i++)
        
            if(i==roll)
                for (int j= 0;j< words[i].length;j++)
                {
                    
                  
                    word+= words[i][j];
                }
           // System.out.println("this is word+"+      word);
           word= word.trim();
       return word; 
    }
        
        
        
        
    
    
    /**  This method draws the hangman based on how 
     * many incorrect guesses the user has made.
     *   ---+
     *      o
     *     /@\
     *     / \
     * @param guess - a counter 
    */
    public static int hangDoll(int guess)
    {
        System.out.println("");
        System.out.printf("%10s%2s%4s\n"," "," |",(guess>=1?"---+":" "));
        System.out.printf("%10s%2s%4s\n"," "," |",(guess>=2?"   o":" "));
        System.out.printf("%10s%2s%3s%1s%1s\n"," "," |",
                (guess>=3?"  /":" "),(guess>=4?"@":" "),(guess>=5?"\\":" "));
        System.out.printf("%10s%2s%3s%2s\n"," "," |",
                (guess>=6?"  /":" "),(guess>=7?" \\":" "));
        System.out.printf("%10s%2s\n"," "," |        ");
        System.out.printf("%10s%2s\n"," "," |        ");                

        System.out.printf("%10s%13s\n"," ","+----------+");
        System.out.printf("%10s%13s\n"," ","|          |");
        System.out.printf("%10s%13s\n"," ","+----------+");
        return ++guess;  // 5. What happens if ++guess is changed to guess++?
                         // Describe what happens and explain why.
    }

}