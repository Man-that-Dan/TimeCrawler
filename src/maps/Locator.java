package maps;




import java.io.*;
import java.net.*;
//import org.json.*;

public class Locator {



    private double latitude;
    private double longitude;
    private String country;
    private String state;
    private String city;
    private int zipcode;

    //URL endpoint to get api with api key
    public static String urlToRead = "http://api.ipapi.com/check?access_key=4f6dab6fc5b0733df3168f3b05ee3284";

    //get request method returns json string
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    //gets and initializes values
    public void init()
    {

        //default values
        country = "Offline";
        state = "Offline";
        city = "Offline";
        zipcode = 0;
        latitude = 0;
        longitude = 0;

        //get request
//        String location = getHTML(urlToRead);
//        JSONObject locObj = new JSONObject(location);
//        country = locObj.getString("country_name");
//        state = locObj.getString("region_name");
//        city = locObj.getString("city");
//        zipcode = locObj.getInt("zip");
//        latitude = locObj.getDouble("latitude");
//        longitude = locObj.getDouble("longitude");

    }


    //gets latitude as double
    public double getLat(){
        return latitude;
    }

    //gets longitude as double
    public double getLong(){
        return longitude;
    }

    //gets country as string
    public String getCountry(){
        return country;
    }

    //gets state as string
    public String getState(){
        return state;
    }

    //gets city as string
    public String getCity(){
        return city;
    }

    //gets zipcode as int
    public int getZip(){
        return zipcode;
    }


}