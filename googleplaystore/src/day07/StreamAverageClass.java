package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAverageClass{
  public static void main(String[] args) throws Exception{

    Reader r = new FileReader("googleplaystore.csv"); //can also be args[0]
    BufferedReader br = new BufferedReader(r);

  
    List<App> apps = br.lines() //return a string
      //to skip the first row which is the header
      .skip(1) 
      // (input) String --> (output) String[]
      .map(l -> l.split(","))
      /* .map(cols -> { //if total column is less than 14, format to concatenate first column with second column
        if (cols.length<=14)
          return cols;
        cols[1] = "%s%s".formatted(cols[0], cols[1]);
        String[] dest = new String[cols.length -1];
          System.arraycopy(cols,1,dest,0,dest.length);
          return dest;
      })*/
      .filter(cols -> cols.length <= 14) // ignore if column more than 14
      .filter(cols -> !cols[2].trim().toLowerCase().equals("nan")) //take the 2nd column rating, remove space, make into lowercase and compare if is not "nan"
      //(input) String[] -> (output) App
      .map(cols -> { //convert to Java object to hold it
        App app = new App();
        app.setName(cols[0]);
        app.setCategory(cols[1]);
        app.setRating(Float.parseFloat(cols[2]));
        return app;
      })
      .toList(); //return app list with name, catergory, rating

    br.close();
    r.close();
    
    //System.out.println(apps);

    Map<String, List<App>> groupByCategory = apps.stream()
      .collect(
        Collectors.groupingBy(app -> app.getCategory())
      ); //Key is whatever we ask to groupby, in this case category

      for (String c :groupByCategory.keySet()){
        List<App> listOfApps = groupByCategory.get(c);
        System.out.printf("Categories: %s - %d\n", c, listOfApps.size());
      }
      
      Float sum = 0f;
      Float avg = 0f;

      for(App a: groupByCategory.get("EVENTS")){//App call the entire App function, including toString method in class App 
        System.out.println(a);
        
        sum = sum + a.getRating();
        
        avg = sum / (groupByCategory.get("EVENTS").size());
        
      }
      System.out.printf("The sum of all ratings is %.2f\n", sum);
      System.out.printf("The average of all ratings is %.2f\n", avg);

        

      

      
      

      
  }
}