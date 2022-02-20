/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program description - To sort the LinkList as per sorting technique */

package com.thealgorithms.sorts;
import java.util.*;
public class LinkList_Sort {
    public static boolean isSorted(int p[] , int option) {
        Node start;
        LinkList_Sort lls=new LinkList_Sort();
    
        int a[] = p;
        // Array is taken as input from test class
        int b[] = Arrays.copyOf(p,p.length);
        // array similar to a
        int ch = option;
        // Create linked list from array
        start = createLinkedList(a);

        // Choice is choosed as any number from 1 to 3 (So the linked list will be sorted by Merge sort technique/Insertion sort technique/Heap sort technique)
        // Switch case is used to call the classes as per the user requirement
        switch (ch) {
            case 1:
                Task nm = new Task();
                // Sort using merge sort
                start = nm.sort_by_mergesort(start);
                // Sort array b, the function should thus return true when checked with sorted list
                Arrays.sort(b);
                // array b is sorted and it will return true when checked with sorted list
                break;
                
            case 2:
                Task1 kk = new Task1();
                // Sort using insertion sort
                start = kk.sort_by_insertionsort(start);

                // array b is not sorted and it will return false when checked with sorted list
                break;

            case 3:
                Task2 mm = new Task2();
                // Sort using heap sort
                start = mm.sort_by_heapsort(start);
                // Sort array b, the function should thus return true when checked with sorted list
                Arrays.sort(b);
                break;

            default:
                // default is used incase user puts a unauthorized value
                System.out.println("Wrong choice");
                return false;
        }
        
        // Stores the sorted/unsorted linked list into the array
        a = convertSortedLinkedListToArray(a, start);
        // The given array and the expected array is checked if both are same then true is displayed else false is displayed
        if(lls.compare(a,b)) {
            return true;
        }
        return false;
    }

    boolean compare(int a[] , int b[])
    {
        for(int i=0;i<a.length;i++)
        {
            if(a[i]!=b[i])
            return false;
        }
        return true;
        // Both the arrays are checked for equalness. If both are equal then true is returned else false is returned
    }

    /**
     * Creates a linked list given an array
     * @param list the array to convert to a linked list
     * @return the start node in the linked list
     */
    public static Node createLinkedList(int[] list) {
        Node start = null, prev = null, fresh;
        for (int i = 0; i < list.length; i++) {
            // New nodes are created and values are added
            fresh = new Node(); // Node class is called
            fresh.val = list[i]; // Node val is stored
            if (start == null)
                start = fresh;
            else
                prev.next = fresh;
            prev = fresh;
        }
        return start;
    }

    /**
     * Inserts the values of a linked list to an array
     * @param list array to insert the linked list into 
     * @param start the starting node of the linked list 
     * @return the resulting list array
     */
    public static int[] convertSortedLinkedListToArray(int[] list, Node start) {
        Node ptr;
        int i=0;
        for (ptr = start;ptr != null; ptr = ptr.next) {
            list[i++]=ptr.val;
        }
        return list;
    }

    /**
     * OUTPUT :
     * Input - {89,56,98,123,26,75,12,40,39,68,91} is same for all the 3 classes
     * Output: [12 26 39 40 56 68 75 89 91 98 123] is same for all the 3 classes
     * 1st approach Time Complexity : O(n logn)
     * Auxiliary Space Complexity : O(n)
     * 2nd approach Time Complexity : O(n^2)
     * Auxiliary Space Complexity : O(n)
     * 3rd approach Time Complexity : O(n logn)
     * Auxiliary Space Complexity : O(n)
     */

}

class Node {
    int val;
    Node next;
    // Node class for creation of linklist nodes
}

class Task {
    static int a[];

    public Node sort_by_mergesort(Node head) {
        if (head == null || head.next == null)
            return head;
        int c = count(head);
        a = new int[c];
        // Array of size c is created
        int i = 0;
        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            a[i++] = ptr.val;
        }
        // values are stored in the array
        i = 0;
        task(a, 0, c - 1);
        // task method will be executed
        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            ptr.val = a[i++];
            // Value is stored in the linklist after being sorted
        }
        return head;
    }

    int count(Node head) {
        int c = 0;
        Node ptr;
        for (ptr = head; ptr != null; ptr = ptr.next) {
            c++;
        }
        return c;
        // This Method is used to count number of elements/nodes present in the linklist
        // It will return a integer type value denoting the number of nodes present
    }

    void task(int n[], int i, int j) {
        if (i < j) {
            int m = (i + j) / 2;
            task(n, i, m);
            task(n, m + 1, j);
            task1(n, i, m, j);
            // Array is halved and sent for sorting
        }
    }

    void task1(int n[], int s, int m, int e) {
        int i = s, k = 0, j = m + 1;
        int b[] = new int[e - s + 1];
        while (i <= m && j <= e) {
            if (n[j] >= n[i])
                b[k++] = n[i++];
            else
                b[k++] = n[j++];
        }
        // Smallest number is stored after checking from both the arrays
        while (i <= m) {
            b[k++] = n[i++];
        }
        while (j <= e) {
            b[k++] = n[j++];
        }
        for (int p = s; p <= e; p++) {
            a[p] = b[p - s];
        }
    }
    // The method task and task1 is used to sort the linklist using merge sort
}
class Task1 {
    public Node sort_by_insertionsort(Node head) {
        if (head == null || head.next == null)
            return head;
        int c = count(head);
        int a[] = new int[c];
        // Array of size c is created
        a[0] = head.val;
        int i;
        Node ptr;
        for (ptr = head.next, i = 1; ptr != null; ptr = ptr.next, i++) {
            int j = i - 1;
            while (j >= 0 && a[j] > ptr.val) {
                // values are stored in the array
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = ptr.val;
        }
        i = 0;
        for (ptr = head; ptr != null; ptr = ptr.next) {
            ptr.val = a[i++];
            // Value is stored in the linklist after being sorted
        }
        return head;
    }

    static int count(Node head) {
        Node ptr;
        int c = 0;
        for (ptr = head; ptr != null; ptr = ptr.next) {
            c++;
        }
        return c;
        // This Method is used to count number of elements/nodes present in the linklist
        // It will return a integer type value denoting the number of nodes present
    }
    // The method task and task1 is used to sort the linklist using insertion sort
}

class Task2 {
    static int a[];

    public Node sort_by_heapsort(Node head) {
        if (head == null || head.next == null)
            return head;
        int c = count(head);
        a = new int[c];
        // Array of size c is created
        int i = 0;
        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            a[i++] = ptr.val;
            // values are stored in the array
        }
        i = 0;
        task(a);
        for (Node ptr = head; ptr != null; ptr = ptr.next) {
            ptr.val = a[i++];
            // Value is stored in the linklist after being sorted
        }
        return head;
    }

    int count(Node head) {
        int c = 0;
        Node ptr;
        for (ptr = head; ptr != null; ptr = ptr.next) {
            c++;
        }
        return c;
        // This Method is used to count number of elements/nodes present in the linklist
        // It will return a integer type value denoting the number of nodes present
    }

    void task(int n[]) {
        int k = n.length;
        for (int i = k / 2 - 1; i >= 0; i--) {
            task1(n, k, i);
        }
        for (int i = k - 1; i > 0; i--) {
            int d = n[0];
            n[0] = n[i];
            n[i] = d;
            task1(n, i, 0);
            // recursive calling of task1 method
        }
    }

    void task1(int n[], int k, int i) {
        int p = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < k && n[l] > n[p])
            p = l;
        if (r < k && n[r] > n[p])
            p = r;
        if (p != i) {
            int d = n[p];
            n[p] = n[i];
            n[i] = d;
            task1(n, k, p);
        }
    }
    // The method task and task1 is used to sort the linklist using heap sort
}