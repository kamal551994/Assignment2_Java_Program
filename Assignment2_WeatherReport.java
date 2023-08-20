package First;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import io.restassured.path.json.JsonPath;

public class Assignment2_WeatherReport {
	   private static final String API_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
	    private static final Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        int choice;

	        do {
	            System.out.println("1. Get Temperature");
	            System.out.println("2. Get Wind Speed");
	            System.out.println("3. Get Pressure");
	            System.out.println("0. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    getWeatherData("temperature");
	                    break;
	                case 2:
	                    getWeatherData("wind.speed");
	                    break;
	                case 3:
	                    getWeatherData("pressure");
	                    break;
	                case 0:
	                    System.out.println("Exiting the program.");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 0);
	    }

	    private static void getWeatherData(String parameter) {
	        System.out.print("Enter date and time (YYYY-MM-DD HH:mm): ");
	        scanner.nextLine(); // Consume newline left by previous nextInt
	        String dateTime = scanner.nextLine();

	        try {
	            URL url = new URL(API_URL);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");

	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();

	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            reader.close();

	            // You need to parse the JSON response to extract the relevant data based on the parameter
	            // For simplicity, let's assume you have a function 'parseJSON' that does this
	            double value = parseJSON(response.toString(), parameter);

	            System.out.println("Weather data for " + dateTime + ": " + value);
	        } catch (IOException e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        }
	    }

	    private static double parseJSON(String json, String parameter) {
	        // You need to implement JSON parsing logic here
	        // This function should extract the relevant value based on the parameter
	        // For example, you can use a JSON library like Gson to achieve this
	    	
	    	JsonPath js = new JsonPath(json);
	    	Double val = Double.parseDouble(js.getString(parameter));
	    	
	        return val; // Placeholder value
	    }
	    
	    }
	


