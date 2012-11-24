import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 11/24/12
 * Time: 11:52 AM
 */
public class Main {
    public static void main(String[] args){
        System.out.print("Please enter a file name: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String fileName = null;

        try{
            fileName = br.readLine();
        }catch(IOException e){
            System.out.println("That is not an existing file.");
        }

        convertFile(fileName);
    }

    public static void convertFile(String fileName){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<City> STATES = new ArrayList<City>();
        City city;

        String state_name = "";
        String city_name = "";
        float latitude;
        float longitude;

        while(scanner.hasNext()){
            String string = scanner.next();
            //Checks to see if it is a new STATE and then gets the cities in it
            while(isState(string)){
                state_name = string;
                //String cit = scanner.next();
                //city_name = cit;
                string = scanner.next();
                if(isState(string)){
                    state_name += " " + string;
                    string = scanner.next();
                }
            }

            //The next part could be the second word in a city
            //String derp = scanner.next();
            String derp = string;
            while(!isNumeric(derp)){
                city_name+= " " + derp;
                derp = scanner.next();
            }

            int latDegree = Integer.parseInt(derp);
            String minute = scanner.next();
            int latMinutes = Integer.parseInt(minute);
            latitude = convertLatToDecimal(latDegree, latMinutes);

            derp = scanner.next();
            int lonDegree = Integer.parseInt(derp);
            minute = scanner.next();
            int lonMinutes = Integer.parseInt(minute);
            longitude = convertLonToDecimal(lonDegree, lonMinutes);

            city = new City(state_name, city_name, latitude, longitude);
            STATES.add(city);
            city_name = "";
        }
        addStateToFile(STATES);
        //close the scanner like a boss
        scanner.close();
    }

    public static float convertLatToDecimal(int degrees, int minutes){
        float decimalLat = 0;
        decimalLat = degrees + (minutes / 60);
        return decimalLat;
    }

    public static float convertLonToDecimal(int degrees, int minutes){
        float decimalLon = 0;
        decimalLon = degrees + (minutes / 60);
        return decimalLon;
    }

    public static boolean isState(String input){
        if(input.toUpperCase() == input){
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String str){
        if(str == null){
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }

    //create a new file so I can actually use this shit!
    public static void addStateToFile(ArrayList<City> awesomeCityList){
        PrintWriter writer = null;
        try {
            FileWriter fw = new FileWriter("cities-2.dat");
            writer = new PrintWriter(fw);
            for(int i=0; i<awesomeCityList.size(); i++){
                String c = awesomeCityList.get(i).getCityName();
                String s = awesomeCityList.get(i).getStateName();
                String lat = String.valueOf(awesomeCityList.get(i).getLat());
                String lon = String.valueOf(awesomeCityList.get(i).getLon());
                writer.println(c + ", " + s);
                writer.println(lat + " -" + lon);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
