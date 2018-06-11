/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;


public class UserIOConsoleImpl implements UserIO {

    final private Scanner console = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    @Override
    public int readInt(String msgPrompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
             
                String stringValue = this.readString(msgPrompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); 
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                System.out.println("You can't do that Dave...!");
            }
        }
        return num;
    }

    @Override
    public int readInt(String msgPrompt, int min, int max) {
        int result;
        do {
            result = readInt(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public long readLong(String msgPrompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                System.out.println("You can't do that Dave...!");
            }
        }
    }

    @Override
    public long readLong(String msgPrompt, long min, long max) {
        long result;
        do {
            result = readLong(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public float readFloat(String msgPrompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                System.out.println("You can't do that Dave...!");
            }
        }
    }

    @Override
    public float readFloat(String msgPrompt, float min, float max) {
        float result;
        do {
            result = readFloat(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    public double readDouble(String msgPrompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                System.out.println("You can't do that Dave...!");
            }
        }
    }


    @Override
    public double readDouble(String msgPrompt, double min, double max) {
        double result;
        do {
            result = readDouble(msgPrompt);
        } while (result < min || result > max);
        return result;
    }
    
    @Override
   public BigDecimal readBigDecimal(String prompt) {
       System.out.println(prompt);
       String inputString = console.nextLine().trim();
       BigDecimal input = new BigDecimal(inputString).setScale(2, RoundingMode.HALF_UP);
       return input;
   }
 
   @Override
   public BigDecimal readBigDecimal(String prompt, BigDecimal min) {
       boolean validate = true;
       BigDecimal input;
       do {
           System.out.println(prompt);
           String inputString = console.nextLine().trim();
           input = new BigDecimal(inputString).setScale(2, RoundingMode.HALF_UP);
           if (input.compareTo(min) > 0) {
               validate = false;
           }
       } while (validate);
       return input;
   }

}
