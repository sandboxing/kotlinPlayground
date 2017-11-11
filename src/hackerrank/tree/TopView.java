package hackerrank.tree;

public class TopView {
  private static class Node {
    final int data;
    Node left;
    Node right;

    private Node(int data) {
      this.data = data;
    }
  }

  void topView(Node root) {
    topView(root, 0);
  }

  private void topView(Node root, int index) {
    if (root == null) return;

    if (index <= 0) {
      topView(root.left, index - 1);
    }
    System.out.print(root.data + " ");
    if (index >= 0) {
      topView(root.right, index + 1);
    }
  }

  public static void main(String[] args) {
    Node one = new Node(1);
    Node two = new Node(2);
    Node three = new Node(3);
    Node four = new Node(4);
    Node five = new Node(5);
    Node six = new Node(6);

    six.left = five;
    five.left = four;
    four.left = three;
    three.left = two;
    two.left = one;

    new TopView().topView(six);
  }
}
