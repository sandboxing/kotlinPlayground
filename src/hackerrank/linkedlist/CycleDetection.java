package hackerrank.linkedlist;

import random.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/detect-whether-a-linked-list-contains-a-cycle
 */

public class CycleDetection {
  boolean hasCycle(Node head) {
    if (head == null) return false;

    Node fast = head.next;
    while (fast != null && fast.next != null) {
      if (fast == head) return true;
      head = head.next;
      fast = fast.next.next;
    }
    return false;
  }
}
