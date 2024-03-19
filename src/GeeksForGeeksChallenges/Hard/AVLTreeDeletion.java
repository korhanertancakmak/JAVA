package GeeksForGeeksChallenges.Hard;
import java.io.*;
import java.util.*;

/*
        Given an AVL tree and N values to be deleted from the tree. Write a function to delete a given value from the tree.
    All the N values which need to be deleted are passed one by one as input data by driver code itself, you are asked
    to return the root of modified tree after deleting the value.

    Example 1:
    Tree =
            4
          /   \
         2     6
        / \   / \
       1   3 5   7

    N = 4
    Values to be deleted = {4,1,3,6}

    Input: Value to be deleted = 4
    Output:
            5
          /   \
         2     6
        / \     \
       1   3     7

    Input: Value to be deleted = 1
    Output:
            5
          /   \
         2     6
          \     \
           3     7

    Input: Value to be deleted = 3
    Output:
            5
          /   \
         2     6
                \
                 7

    Input: Value to be deleted = 6
    Output:
            5
          /   \
         2     7

    Your Task: You don't need to read input or print anything. Complete the function deleteNode() which takes the root
    of the tree and the value of the node to be deleted as input parameters and returns the root of the modified tree.

    Note: The tree will be checked after each deletion.
    If it violates the properties of balanced BST, an error message will be printed followed by the in order traversal of
    the tree at that moment.
    If instead, all deletions are successful, in order traversal of the tree will be printed.
    If every single node is deleted from the tree, 'null' will be printed.

    Expected Time Complexity: O(height of the tree)
    Expected Auxiliary Space: O(height of the tree)

    Constraints:
    1 ≤ N ≤ 500
*/

class pair {
    int first;
    boolean second;
    pair(int first, boolean second)
    {
        this.first = first;
        this.second = second;
    }
}

class Node {
    int data, height;
    Node left, right;

    Node(int x)
    {
        data=x;
        left=right=null;
        height=1;
    }
}

class GfG {

    public static int setHeights(Node n)
    {
        if(n==null) return 0;
        n.height = 1 + Math.max( setHeights(n.left) , setHeights(n.right) );
        return n.height;
    }

    static Node buildTree(String str)
    {

        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while(queue.size()>0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if(!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if(i >= ip.length)
                break;

            currVal = ip[i];

            // If the right child is not null
            if(!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        setHeights(root);
        return root;
    }

    public static boolean isBST(Node n, int lower, int upper)
    {
        if(n==null) return true;
        if( n.data <= lower || n.data >= upper ) return false;
        return isBST(n.left, lower, n.data) && isBST(n.right, n.data, upper) ;
    }

    public static pair isBalanced(Node n)
    {
        if(n==null)
        {
            return new pair(0,true);
        }

        pair l = isBalanced(n.left);
        pair r = isBalanced(n.right);

        if( Math.abs(l.first - r.first) > 1 ) return new pair (0,false);

        return new pair( 1 + Math.max(l.first , r.first) , l.second && r.second );
    }

    public static boolean isBalancedBST(Node root)
    {
        if( isBST(root, Integer.MIN_VALUE , Integer.MAX_VALUE) == false )
            System.out.print("BST voilated, inorder traversal : ");

        else if ( isBalanced(root).second == false)
            System.out.print("Unbalanced BST, inorder traversal : ");

        else return true;
        return false;
    }

    public static void printInorder(Node n)
    {
        if(n==null) return;
        printInorder(n.left);
        System.out.print(n.data + " ");
        printInorder(n.right);
    }


    public static void main(String args[]) throws IOException
    {
        System.out.println("Enter the number of elements in the tree:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-->0)
        {
            System.out.println("Enter the elements in the tree in the format 'a1 a2 a3 ...':");
            String s = br.readLine();
            Node root = buildTree(s);

            System.out.println("Enter the number of elements to be deleted:");
            int n = Integer.parseInt(br.readLine());
            int ip[] = new int[n];

            System.out.println("Enter the elements to be deleted in the format 'b1 b2 b3 ...':");
            String[] in = br.readLine().trim().split("\\s+");

            for(int i = 0; i < n; i++)
                ip[i] = Integer.parseInt(in[i]);

            AVLTreeDeletion obj = new AVLTreeDeletion();

            for(int i=0; i<n; i++)
            {
                root = obj.deleteNode(root, ip[i]);

                if(isBalancedBST(root)==false)
                    break;
            }

            if(root==null)
                System.out.print("null");
            else
                printInorder(root);
            System.out.println();

        }
    }
}
public class AVLTreeDeletion {

    static int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    static int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static Node minValueNode(Node node)
    {
        Node current = node;

        // Loop down to find the leftmost leaf
        while (current != null && current.left != null)
            current = current.left;

        return current;
    }

    public static Node deleteNode(Node root, int key)
    {
        // base Case
        if (root == null)
            return root;

        // If the key to be deleted is
        // smaller than the root's key,
        // then it lies in left subtree
        if (key < root.data) {
            root.left = deleteNode(root.left, key);
        }

        // If the key to be deleted is
        // greater than the root's key,
        // then it lies in right subtree
        else if (key > root.data) {

            root.right = deleteNode(root.right, key);
        }

        // If key is same as root's key,
        // then this is the node
        // to be deleted
        else {

            // Node with only one child
            // or no child
            if (root.left == null) {
                Node temp = root.right;
                return temp;
            }
            else if (root.right == null) {
                Node temp = root.left;
                return temp;
            }

            // Node with two children:
            // Get the inorder successor(smallest
            // in the right subtree)
            Node temp = minValueNode(root.right);

            // Copy the inorder successor's
            // content to this node
            root.data = temp.data;

            // Delete the inorder successor
            root.right = deleteNode(root.right, temp.data);
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }
}
