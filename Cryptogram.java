/**
 * Created by Samuel Decanio on 10/29/2014.
 * Last Edited: March 30, 2015
 */

import java.util.Random;
import java.text.DecimalFormat;

public class Cryptogram {

    final private char [] ALPHABET = {'a','b','c','d','e','f','g','h',
            'i','j','k','l','m','n','o','p','q','r','s','t','u','v',
            'w','x','y','z', 'A','B','C','D','E','F','G','H',
            'I','J','K','L','M','N','O','P','Q','R','S','T','U','V',
            'W','X','Y','Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private char [] cryptCode;
    private int [] letterCounters;
    final int NOT_FOUND = -1;

    public Cryptogram()
    {
        this.cryptCode = new char[ALPHABET.length];
        setCryptCode();
        this.letterCounters = new int[this.ALPHABET.length];
    }

    public void setCryptCode()
    {
        Random random_num = new Random();
        char temp;
        int random_index;
        for (int i=0; i<this.cryptCode.length; i++)
        {
            this.cryptCode[i] = this.ALPHABET[i];
        }
        for (int i=0; i<this.cryptCode.length; i++)
        {
            random_index = random_num.nextInt(cryptCode.length);
            temp = this.cryptCode[i];
            this.cryptCode[i] = this.cryptCode[random_index];
            this.cryptCode[random_index] = temp;

        }
    }

    public int findLetterInAlphabet(char letter)
    {
        int index = this.NOT_FOUND;
        for (int i=0; i < this.ALPHABET.length && index == this.NOT_FOUND; i++)
        {
            if (this.ALPHABET[i] == letter)
            {
                index = i;
            }
        }
        return index;
    }

    public String encrypt(String toEncrypt)
    {
        String final_encrypt = "";
        for (int i=0; i<toEncrypt.length(); i++)
        {
            char search = toEncrypt.charAt(i);
            int found = findLetterInAlphabet(search);
            if (found != NOT_FOUND)
            {
               final_encrypt += this.cryptCode[found];
               this.letterCounters[found]++;
            }
            else
            {
                final_encrypt += search;
            }
        }
        return final_encrypt;
    }

    public int findLetterInCryptCode(char letter)
    {
        int index = this.NOT_FOUND;
        for (int i=0; i < this.cryptCode.length && index == this.NOT_FOUND; i++)
        {
            if (this.cryptCode[i] == letter)
            {
                index = i;
            }
        }
        return index;
    }

    public String decrypt(String toDecrypt)
    {
        String final_decrypt = "";
        for (int i=0; i<toDecrypt.length(); i++)
        {
            char search = toDecrypt.charAt(i);
            int found = findLetterInCryptCode(search);
            if (found != -1)
            {
                final_decrypt += this.ALPHABET[found];
            }
            else
            {
                final_decrypt += search;
            }
        }
        return final_decrypt;
    }

    public void displayStatistics()
    {
        DecimalFormat percentage_format = new DecimalFormat("#.#%");
        double percentage;
        int sum = 0;
        for (int i=0; i<this.letterCounters.length; i++)
        {
            sum += this.letterCounters[i];
        }
        for (int i=0; i<this.letterCounters.length; i++)
        {
            percentage = (double) this.letterCounters[i] / sum ;
            System.out.println(this.ALPHABET[i] + " -> " + this.letterCounters[i]
             + " -> " + percentage_format.format(percentage));
        }
    }

    public String toString()
    {
        String return_string = "Alphabet:  ";
        for (int i=0; i<this.ALPHABET.length; i++)
        {
          return_string += this.ALPHABET[i] + " ";
        }
        System.out.println();
        return_string +="\nCryptCode: ";
        for (int i=0; i<this.cryptCode.length; i++)
        {
            return_string += this.cryptCode[i] + " ";
        }
        return  return_string;
    }

}
