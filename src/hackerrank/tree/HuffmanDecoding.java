package hackerrank.tree;

/**
 * https://www.hackerrank.com/challenges/tree-huffman-decoding
 */

public class HuffmanDecoding {
  private static class Node {
    final public char data;
    public Node left, right;

    private Node(char data) {
      this.data = data;
    }
  }

  void decode(String s, Node root) {
    Node current = root;
    while (s.length() > 0) {
      if (current.data != '\0') {
        System.out.print(current.data);
        current = root;
      }
      char c = s.charAt(0);
      switch (c) {
        case '0':
          current = current.left;
          break;
        case '1':
          current = current.right;
          break;
      }
      s = s.substring(1);
    }
    System.out.print(current.data);
  }

  public static void main(String[] args) {
    Node a = new Node('A');
    Node b = new Node('B');
    Node bc = new Node('ϕ');
    Node c = new Node('C');
    Node top = new Node('ϕ');
    top.right = a;
    top.left = bc;
    bc.left = b;
    bc.right = c;

    new HuffmanDecoding().decode("1001011", top);
  }
}
