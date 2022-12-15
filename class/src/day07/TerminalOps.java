package day07;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class TerminalOps{

  public static void main(String[] args){
    //Randomly generate a list of numbers
    Integer max =200;
    Integer range = 100;
    Random rnd = new SecureRandom();

    List<Integer> numList = new LinkedList<>();

    for (Integer i = 0; i < max; i++)
      numList.add(rnd.nextInt(range));
    
    System.out.println(">>> numList: "+numList);

    joining(numList);
    reducing(numList);
    joiningAsReducing(numList);
    reducing2(numList);

    
  }

  public static void joining(List<Integer> numList){
    System.out.println("======= JOINING =======");

    String listOfNums = numList.stream()
      //map: String apply (Integer i)
      .map(n -> "%d%d".formatted(n,n))
      .collect(Collectors.joining("\n")); 
      //joining() : append or concat nnnnn together
      //joining (","): separate by ,
      //joining("\n"): separate per line
      System.out.println(">>> csv: " + listOfNums);
    
  }

  public static void reducing(List<Integer> numList){
    System.out.println("======= REDUCING =======");

    Optional<Integer> opt = numList.stream() //give a box, but may or may not contain the answer
      //map: String apply (Integer i)
      .map(n -> "%d%d".formatted(n,n))
      //change back to integer
      .map(Integer::parseInt)
      .collect(
        //Integer apply(Integer total, Integer i)
        Collectors.reducing((accumulator,i) -> { //pass 2 numbers, see what you want to do with it
          System.out.printf("total: %d, i: %d\n", accumulator, i);
          return accumulator+i;
        })
      );
      
    //Check if we have any answer
    if(opt.isPresent())
        //Get the answer
      System.out.println(">>> total: " + opt.get());
    else  
      System.out.println("Reducing produces no result");

  }

  public static void joiningAsReducing (List<Integer> numList){
    System.out.println("======= JOINING AS REDUCING ======="); //joining is a specialised version of reducing

    Optional<String> opt = numList.stream() //give a box, but may or may not contain the answer
      //map: String apply (Integer i)
      .map(n -> "%d%d".formatted(n,n))
      .collect(
        //Integer apply(Integer total, Integer i)
          Collectors.reducing((accumulator,i) -> { //pass 2 numbers, see what you want to do with it
          System.out.printf("total: %s, i: %s\n", accumulator, i);
          return "%s,%s".formatted(accumulator,i);
        })
      );
      
    //Check if we have any answer
    if(opt.isPresent())
        //Get the answer
      System.out.println(">>> total: " + opt.get());
    else  
      System.out.println("Reducing produces no result");

  }

  public static void reducing2 (List<Integer> numList){//another kind of reucing
    System.out.println("======= REDUCING TO ======="); //joining is a specialised version of reducing

    Integer result = numList.stream() //give a box, but may or may not contain the answer
      //map: String apply (Integer i)
      .map(n -> "%d%d".formatted(n,n))
      .map(Integer::parseInt)
      .collect(
        //Integer apply(Integer total, Integer i)
          Collectors.reducing(
            0 //total is 0
            ,(total,i) -> { //pass 2 numbers, see what you want to do with it
          System.out.printf("total: %d, i: %d\n", total,i);
          return total+i;
        }
        )
        
      );
    // Check if we have any answer
    System.out.println(">>> total : " + result);

  }

}