import java.util.*;
import java.lang.*;

public class AVLTest {

    public static void main(String[] args){

        AVL<String> strTree = new AVL<>();

        System.out.println("\n-----Testing Insertion and Removal-----");
        strTree.insert("a");
        strTree.insert("b");
        strTree.insert("c");
        strTree.insert("d");
        strTree.insert("e");
        strTree.insert("f");
        strTree.insert("g");
        strTree.insert("h");
        strTree.insert("i");
        strTree.insert("j");
        strTree.insert("k");
        strTree.insert("l");
        strTree.insert("m");


        //strTree.inorderTraversal();
        strTree.remove("a");
        strTree.remove("l");
        strTree.remove("m");
        strTree.remove("i");
        strTree.inorderTraversal();


        System.out.println("\n-----Testing Search Depth-----");
        AVL<Integer> intTree = new AVL<>();
        for(int i = 1; i <= 10000; i++){
            intTree.insert(i);
        }
        System.out.println("Number of steps to find 1: " + intTree.search(1));
        System.out.println("Number of steps to find 10000: " + intTree.search(10000));


    }

}
