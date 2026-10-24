import java.util.ArrayList;
import java.util.List;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3){
          int[][] t = { parse(traversal1), parse(traversal2), parse(traversal3) };
          if (t[0].length != t[1].length || t[1].length != t[2].length) {
               return "Invalid traversals";
          }

          for (int in = 0; in < 3; in++) {
               for (int pre = 0; pre < 3; pre++) {
                    if (pre == in) {
                         continue;
                    }
                    int post = 3 - in - pre;
                    if (isSameTree(t[in], t[pre], t[post])) {
                         String[] names = new String[3];
                         names[in] = "Inorder";
                         names[pre] = "Preorder";
                         names[post] = "Postorder";
                         return "Traversal 1 - " + names[0] + ", Traversal 2 - " + names[1]+ ", Traversal 3 - " + names[2];
                    }
               }
          }
          return "Invalid traversals";
     }

     private static class Node {
          int value;
          Node left, right;

          Node(int value) {
               this.value = value;
          }
     }

     private static int[] parse(String s) {
          String[] parts = s.split("-");
          int[] values = new int[parts.length];
          for (int i = 0; i < parts.length; i++) {
               values[i] = Integer.parseInt(parts[i].trim());
          }
          return values;
     }

     private static boolean isSameTree(int[] in, int[] pre, int[] post) {
          Node root = null;
          for (int v : pre) {
               root = insert(root, v);
          }

          List<Integer> inList = new ArrayList<>();
          List<Integer> preList = new ArrayList<>();
          List<Integer> postList = new ArrayList<>();
          inorder(root, inList);
          preorder(root, preList);
          postorder(root, postList);

          return matches(in, inList) && matches(pre, preList) && matches(post, postList);
     }

     private static Node insert(Node node, int value) {
          if (node == null) {
               return new Node(value);
          }
          if (value < node.value) {
               node.left = insert(node.left, value);
          } else {
               node.right = insert(node.right, value);
          }
          return node;
     }

     private static void inorder(Node node, List<Integer> out) {
          if (node == null) {
               return;
          }
          inorder(node.left, out);
          out.add(node.value);
          inorder(node.right, out);
     }

     private static void preorder(Node node, List<Integer> out) {
          if (node == null) {
               return;
          }
          out.add(node.value);
          preorder(node.left, out);
          preorder(node.right, out);
     }

     private static void postorder(Node node, List<Integer> out) {
          if (node == null) {
               return;
          }
          postorder(node.left, out);
          postorder(node.right, out);
          out.add(node.value);
     }

     private static boolean matches(int[] given, List<Integer> actual) {
          if (given.length != actual.size()) {
               return false;
          }
          for (int i = 0; i < given.length; i++) {
               if (given[i] != actual.get(i)) {
                    return false;
               }
          }
          return true;
     }
}