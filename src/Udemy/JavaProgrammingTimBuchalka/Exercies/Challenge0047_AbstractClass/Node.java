package Udemy.JavaProgrammingTimBuchalka.Exercies.Challenge0047_AbstractClass;
/*
    Node (concrete class) :
            - It extends ListItem.
            - It has a constructor that takes an Object, then calls its parent constructor with the parameter that was
              passed in.
            - And five methods which are package-private:
            - next(), takes no parameters and returns the ListItem to its right.
            - setNext(), takes a ListItem and sets it as its rightLink, then it returns rightLink.
            - previous(), takes no parameters and returns the ListItem to its left.
            - setPrevious(), takes a ListItem and sets it as its leftLink, then it returns leftLink.
            - compareTo(), takes a ListItem and compares it to the ListItem that called this method. Use value from
              ListItem for comparison. If this value is greater than the value that was passed in, then it should return
              a number greater than zero. If vice versa, then it should return a number less than zero, and zero if equal.
*/
public class Node extends ListItem{
    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem next() {
        return this.rightLink;
    }

    @Override
    ListItem setNext(ListItem item) {
        this.rightLink = item;
        return this.rightLink;
    }

    @Override
    ListItem previous() {
        return this.leftLink;
    }

    @Override
    ListItem setPrevious(ListItem item) {
        this.leftLink = item;
        return this.leftLink;
    }

// compareTo(), takes a ListItem and compares it to the ListItem that called this method. Use value from ListItem
// for comparison. If this value is greater than the value that was passed in, then it should return a number
// greater than zero. If vice versa, then it should return a number less than zero, and zero if equal.
    @Override
    int compareTo(ListItem item) {
        if (item != null) {
            return ((String) super.getValue()).compareTo((String) item.getValue());
        } else {
            return -1;
        }
    }
}
