package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Assignment to get average rating for particular genre
public class StreamAverageDraft{
  public static void main(String[] args) throws Exception{

    //Read the csv
    Map<String, String> csv = readCSV("googleplaystore.csv");

    //Sort according to Genre


    //For a genre, sum the total and get average (total/no)
    if(csv.containsKey("Finance")==true){
      List<Integer> numList = new LinkedList<>();
      numList.add(Integer.parseInt(csv.get("Finance")));
      avg(numList);
      System.out.println(csv.get("Finance"));
      
      
    }
    
  }

  public static Map<String, String> readCSV(String fileName) throws Exception {
        Map<String, String> data = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;

        while((line = reader.readLine() )!= null){
          String[] parts = line.trim().split(",");
          String genre = parts[9];
          String rating = parts[3];

          data.put(genre,rating);
          System.out.printf("Genre: %s, Rating: %s\n", genre, rating);
          
        }
        reader.close();
        return data;
      
    }

    public static void avg(List<Integer> numList){

      Integer result = numList.stream()
        .collect(Collectors.reducing(0,(total,i) -> { 
          System.out.printf("The average is %d", ((total+i)/(numList.size())));
          return ((total+i)/(numList.size()));}));
      
        
      
    }
}