import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<String> read(int stringLength){
        ArrayList<String> arr = new ArrayList<String>();
        if(stringLength == 1){
            arr.add("i");
            arr.add("a");
            return arr;
        }
        try{
            String url = "http://www.mieliestronk.com/corncob_caps.txt";
            URL wordsURL = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(wordsURL.openStream()));
            String inputLine;
            // If the word has the correct length and isn't already contained in the array, add it
            while ((inputLine = in.readLine()) != null)
                if(inputLine.length() == stringLength && !arr.contains(inputLine.toUpperCase()))
                    arr.add(inputLine.toUpperCase());
            in.close();
        }catch(Exception e){
            System.out.print("");
        }
        return arr;
    }
}