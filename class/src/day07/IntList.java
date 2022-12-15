package day07;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IntList{

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
    
  }

  public static void filter(List<Integer> numList){
    System.out.println("======= Filter =======");
    
    //=======Filter=======

    //Create a result list after filter
    List<Integer> resultList = new LinkedList<>();
    //for-each loop: every number in the numList
    for (Integer n:numList){
      if(0!=(n%3))
        continue; //skip and continue with loop
      resultList.add(n);
    }
    System.out.println(">>> resultList: "+resultList);
  }
}