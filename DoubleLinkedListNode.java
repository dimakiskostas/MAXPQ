
import java.io.PrintStream;


public class DoubleLinkedListNode<T> {

    public T data;
    public DoubleLinkedListNode nextNode, previousNode;

    public DoubleLinkedListNode(T data) {
        this.data = data;
    }

    public DoubleLinkedListNode(T data, DoubleLinkedListNode nextNode, DoubleLinkedListNode previousNode) {
        this.data = data;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }
    
    void printNode(PrintStream stream) {
        stream.print(data);
    }
}
