
/**
 * Cryptogram Client
 * Created by Sam Decanio on 10/29/2014
 * Last Edited: March 30, 2015
 */

/*
    TODO: Would like to add functionality to read in the file containing
            the key and then take a given text file and decode/encode using
            that given key
 */
import java.util.*;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

public class CryptogramClient
{
    public static void main(String[] args)  throws IOException
    {
        Scanner scan = new Scanner(System.in);
        Cryptogram cg = new Cryptogram();
        String code = "";
        System.out.println(cg); // print alphabet and substitution code

        System.out.println("\nPlease enter the name of the file to encrypt (including file type extension.) > " );
        String file_in = scan.nextLine();

        Scanner fromFile = new Scanner(new File(file_in));
        while (fromFile.hasNext())
        {
            String phrase = fromFile.nextLine();
            System.out.println("\nThe original line is \t \"" + phrase + "\"");

            String codeLine = cg.encrypt(phrase);

            System.out.println("The encrypted phrase is \t \"" + codeLine + "\"");

            String plainText = cg.decrypt(codeLine);

            System.out.println("The decrypted phrase is \t \"" + plainText + "\"");

            code += codeLine + "            ";
        }
        System.out.println();
        //displays stats of letter/num usage
        //cg.displayStatistics();

        //outputting cypher to a text file
        System.out.println("");
        String key_File_Name = file_in.substring(0, file_in.length() - 4) + "_Key"
                + file_in.substring(file_in.length() - 4, file_in.length());
        // testing output file naming system
        WriteFile data = new WriteFile( key_File_Name , false );
        data.writeToFile(cg.toString());
        System.out.println("Saved: " + key_File_Name + " ,containing your cypher's key.");

        System.out.println("Please enter a file name containing the decryption cipher. > ");
        String coded_File_Name = file_in.substring(0, file_in.length() - 4) + "_Coded"
            + file_in.substring(file_in.length() - 4, file_in.length());
        WriteFile coded_Data = new WriteFile(coded_File_Name, false);
        coded_Data.writeToFile(code);
        System.out.println("Saved: " + coded_File_Name  + " , containing your coded message.");


    }
}