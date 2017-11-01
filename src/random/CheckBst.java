package random;

/**
 * https://www.hackerrank.com/challenges/ctci-is-binary-search-tree
 */
public class CheckBst {
  private static class Nody {
    int data;
    Nody left;
    Nody right;
  }

  boolean checkBST(Nody root) {
    if (root == null) return true;

    return checkBST(root, -1, 10001);
  }

  boolean checkBST(Nody root, int min, int max) {
    if (root == null) return true;

    if (root.data <= min || root.data >= max) return false;
    return checkBST(root.left, min, root.data) && checkBST(root.right, root.data, max);
  }
}
