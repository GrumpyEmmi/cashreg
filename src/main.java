import java.util.Scanner;
import java.io.*;
import java.util.*;



public class main {

    public static void main (String[] args){
        try{
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
        catch(IOException e){
            System.out.print("File not found!");
        }
    }

}
