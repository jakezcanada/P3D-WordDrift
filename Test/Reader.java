import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<String> read(int stringLength){
        ArrayList<String> arr = new ArrayList<String>();
        try{
            String url = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english-no-swears.txt";
            URL wordsURL = new URL(url);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(wordsURL.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                if(inputLine.length() == stringLength)
                    arr.add(inputLine);
            in.close();
        }catch(Exception e){
            System.out.print("");
        }
        return arr;
    }
}