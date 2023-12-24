public class LinkedList {
  class Node {
    int data;
    Node next;

    public Node(int data) {
      this.data = data;
      this.next = null;
    }
  }

  public static Node head;
  public static Node tail;
  public static int size;

  // add first in a linked List
  public void AddFirst(int data) {
    // create a new node
    Node newNode = new Node(data);
    size++;
    if (head == null) {
      head = tail = newNode;
      return;
    }
    // point this node to the head
    newNode.next = head;
    // declare this new Node as head
    head = newNode;
  }

  // add last in a linked List
  public void AddLast(int data) {

    // creating a new node
    Node newNode = new Node(data);
    size++;
    if (head == null) {
      head = tail = newNode;
      return;
    }
    // assign newNode next as tail
    tail.next = newNode;
    // because newNode next don't know what the tail is..so we know tail then we
    // give tail next as newNode
    // declaring tail as a newNode
    tail = newNode;
  }

  // adding in middle
  public void AddMiddle(int index, int data) {
    if (index == 0) {
      AddFirst(data);
      return;
    }
    // creating a newNode
    Node newNode = new Node(data);
    size++;
    Node temp = head;
    int i = 0;
    while (i < index - 1) {
      temp = temp.next;// updating the temp
      i++;// the index count

    }
    // point the next of current node same as prev node
    newNode.next = temp.next;
    // point the next of prev node to the current newnode
    temp.next = newNode;
  }

  // printing the linked list
  public void PrintLL() {

    Node temp = head;
    if (head == null) {
      System.out.println("The linkedList is empty");
      return;
    }

    while (temp != null) {
      System.out.println(temp.data + "->");
      temp = temp.next;

    }
    System.out.println();

  }

  // removing the first node in a linknedList
  public int RemoveFirst() {
    if (size == 0) {
      System.out.println("LinkedList is empty");
      return Integer.MAX_VALUE;
    } else if (size == 1) {
      int val = head.data;
      head = tail = null;
      return val;
    }
    int val = head.data;
    head = head.next;
    size--;
    return val;

  }

  // removing the last in a linkedList
  public int RemoveLast() {
    if (size == 0) {
      System.out.println("LinkedList is empty");
      return Integer.MIN_VALUE;
    } else if (size == 1) {
      int val = head.data;
      head = tail = null;
      return val;
    }
    // logic in 2 steps
    // 1)prev.next = null
    // 2)tail = prev

    // prev : i = size-2(previous node just before the tail)
    Node prev = head;
    for (int i = 0; i < size - 2; i++) {
      prev = prev.next;

    }
    int val = tail.data;// prev.next.data
    prev.next = null;
    tail = prev;
    size--;
    return val;

  }

  // Iterative search in a linkedList for a key;
  // return -1 if not found
  public int IterativeSearch(int key) {
    Node temp = head;
    int i = 0;

    while (temp != null) {
      if (temp.data == key) {
        return i;
      }
      temp = temp.next;
      i++;

    }
    return -1;

  }

  // search in LinkedList using recursion
  // gonna use a helping function for this
  public int helper(Node head, int key) {
    if (head == null) {
      return -1;

    }
    if (head.data == key) {
      return 0;// key at head which head is at index 0;

    }
    int index = helper(head.next, key);// can helper find the key at head.next
    if (index == -1) {
      return -1;

    }
    return index + 1;

  }

  public int recursiveSearch(int key) {
    return helper(head, key);

  }

  // **REVERSING THE LINKEDlIST(CLASSICAL INTERVIEW QUESTION)
  /*
   * FOUR STEPS PROCESS
   * 
   * 1) 3 VARIABLES:- CURRENT, PREV, NEXT
   * while current == null;
   * .)NEXT = CURRENT.NEXT
   * .) CURRENT.NEXT = PREV(BACKTRACK)
   * .)PREV = CURRENT
   * .)CURRENT = NEXT
   */
  public void ReverseLinkedList() {
    Node prev = null;// head ke pehele wali node
    Node current = tail = head;// after reversing, tail is the new head
    Node next;
    while (current != null) {
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    head = prev;// current is null and the node before current that is previous is now our head

  }

  // find and remvoe Nth Node from end(iterative approach);-delete from middle
  /*
   * nth node from the end is same as
   * (size-n+1) from the start
   * we need prev node of the target node to point the next of prev as
   * prev.next.next
   */
  public void deleteNthFromEnd(int n) {
    // calculate the size of linked list
    int sz = 0;
    Node temp = head;
    while (temp != null) {
      temp = temp.next;
      sz++;
    }
    // the case where we have to delete the head itself
    if (n == sz) {
      head = head.next;// remove first operation
      return;

    }
    // sz-n//the index just before the node to delete
    int i = 1;
    int iToFind = sz - n;
    Node prev = head;
    while (i < iToFind) {
      prev = prev.next;
      i++;

    }
    prev.next = prev.next.next;
    return;

  }

  // **cheking if the linkedList is palindrome or not
  // slow-fast approach
  // reversing the half linked list
  /*
   * 1)finding middle of linked list
   * 2)reverse the second half of the linkedlist
   * 3)check if first half == second half
   */
  public Node findMid(Node head) {
    // slow fast approach
    Node slow = head;
    Node fast = head;
    while (fast != null && fast.next == null) {
      slow = slow.next;
      fast = fast.next.next;

    }
    return slow;// slow is the midNode now
  }

  public boolean chekcpalindrome() {
    if (head == null || head.next == null) {
      return true;
    }
    // step 1- find mid
    Node midNode = findMid(head);

    // step 2) reverse 2ndhalf
    Node prev = null;
    Node current = midNode;
    Node next;
    while (current != null) {
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    Node right = prev;// right half head
    Node left = head;

    // step 3)- check left and right half
    while (right != null) {
      if (left.data != right.data) {
        return false;
      }
      left = left.next;
      right = right.next;

    }
    return true;
  }

  public static void main(String args[]) {
    LinkedList ll = new LinkedList();
    ll.AddFirst(3);
    ll.AddFirst(2);
    ll.AddFirst(1);
    ll.AddFirst(0);

    ll.AddLast(-1);
    ll.AddLast(-2);
    ll.AddLast(-3);
    ll.AddLast(-4);
    ll.AddMiddle(4, 10);
    ll.PrintLL();
    ll.deleteNthFromEnd(3);
    ll.PrintLL();
  }
}
