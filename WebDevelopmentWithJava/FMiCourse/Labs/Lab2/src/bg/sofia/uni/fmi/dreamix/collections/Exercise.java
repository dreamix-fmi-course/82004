package bg.sofia.uni.fmi.dreamix.collections;

import bg.sofia.uni.fmi.dreamix.collections.entity.Dog;

import java.util.*;

public class Exercise {

    // T0) Write a method to insert an element into the array list at the first position
    public static void insertElementAtFirstPositionInArray(ArrayList<Integer> inputArray, int newElement) {
        inputArray.add(0, newElement);
    }

    // T1) Write a method to retrieve an element (at a specified index) from a given list.
    public static Integer retrieveElementFromList(List<Integer> inputList, int position) {
        return inputList.get(position);
    }

    // T2) Write a method to remove the third element from an array list.
    public static void removeThirdElement(ArrayList<Integer> inputList) {
        inputList.remove(3);
    }

    // T3) Write a method to search an element in a list.
    public static boolean findElementInList(List<Integer> inputList, Integer element) {
        return inputList.stream().anyMatch(e -> e.equals(element));
    }

    // T4) Write a method to sort a given array list. (list of String/Integer/Dog).
    // Implement Dog class with attribute breed and weight, sort the array by weight property.
    // Tip: implement the task with Comparator and Comparable
    public void sortDogs(List<Dog> dogs) {
        dogs.sort((f, s) -> (int) (f.getWeight() - s.getWeight()));
    }

    // T5) Write a method to replace the second element of a ArrayList with the specified element.
    public static void replaceSecondElementInList(List<Integer> inputList, Integer newElement) {
        inputList.set(1, newElement);
    }

    // T6)?? Write a Java program to count the number of key-value (size) mappings in a map.

    // T7) Write the following structure against aircraft tail number associate
    // list of leg information (fromAirport, toAirport, date).
    // Fill test information
    // Extract legs inside list/set that have from/to airport for a specific airport
    // (Example: All legs for airport LBSF)

}