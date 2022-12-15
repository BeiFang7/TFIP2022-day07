package day07;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IntListStream{

  public static void main(String[] args){
    //Randomly generate a list of numbers
    Integer max =200;
    Integer range = 100;
    Random rnd = new SecureRandom();

    List<Integer> numList = new LinkedList<>();

    for (Integer i = 0; i < max; i++)
      numList.add(rnd.nextInt(range));
    
    System.out.println(">>> numList: "+numList);

    filter(numList);
    map(numList);
    
  }

  public static void filter(List<Integer> numList){
    System.out.println("======= Filter =======");
    
    //=======Filter=======

    //Create a result list after filter
    List<Integer> resultList = new LinkedList<>();
    //for-each loop: every number in the numList
    for (Integer n:numList){
      if(0==(n%3))
        resultList.add(n);
    }
    System.out.println(">>> resultList: "+resultList);

    //in stream, dont do the loop, it is done internally
    resultList = numList.stream()
      //Predicate: boolean test(Integer i) 
      /* .filter((n) -> {
        return 0 != ( n % 3 ); // return true or false */

      //if lambda is only 1 line can shorten to this
      .filter((n) -> 0 ==(n%3)) //any value that is not true it will not pass through
      .distinct() // get distinct values --> remove duplicates
      .sorted() // sort from small to big
      .limit(5) // get first 5 values
      .toList(); //terminating operation, generally return list or a number
      System.out.println(">>> stream resultList: " + resultList);
    
  }

  public static void map(List<Integer> numList){
    System.out.println("======= Map =======");
    
    //=======Map=======

    //Create a result list after filter
    List<String> resultList = new LinkedList<>();
    //for-each loop: every number in the numList
    for (Integer n:numList){
      resultList.add("%d%d".formatted(n,n));
    }
    System.out.println(">>> iteration resultList: "+resultList);

    //in stream, dont do the loop, it is done internally
    List<Integer> intList = numList.stream()
      //Intermediate operation transform the string
      // map: String apply(Integer n)
      .map (n -> "%d%d".formatted(n,n)) //n is integer, returns string
      //map: Integer apply(String n)
      .map (n -> Integer.parseInt(n)) //n is string, retuns integer /*.map(Integer::parseInt) //Method Reference */
      //Terminating operation to collect the results
      .toList();
      System.out.println(">>> stream intList: " + intList);
    
  }



}