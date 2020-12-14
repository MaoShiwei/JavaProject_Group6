import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Node<T> implements Serializable {

    private Node parentNode;
    private T nodeEntity;
    private List<Node> childNodes;

    public Node (T nodeEntity){
        this.nodeEntity=nodeEntity;
    }

    public Node (){}

    public void addChildNode(Node childNode){
        childNode.setParentNode(this);
        if ( this.childNodes==null){
            this.childNodes = new ArrayList<Node>();
        }
        this.childNodes.add(childNode);
    }

    public void removeChildNode(Node childNode){
        if (this.childNodes!=null){
            this.childNodes.remove(childNode);
        }
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public T getNodeEntity() {
        return nodeEntity;
    }

    public void setNodeEntity(T nodeEntity) {
        this.nodeEntity = nodeEntity;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public static void printNodeTree(Node<String> node){
        for (Node<String> childNode: node.getChildNodes()) {
            System.out.println(childNode.getNodeEntity().toString());
            if (childNode.getChildNodes()!=null){
                printNodeTree(childNode);
            }
        }
    }

    public static void main(String args[]) {
        File file = new File("D:\\Java\\managebase\\content.txt");
        Stack<Node> stack = new Stack();
        Node temp = new Node();
        Node root = new Node();
        String name = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                String[] arr = s.split("\\s+");
                if (arr[0].equals("blob")) {
                    temp = new Node(arr[1]);
                    stack.push(temp);
                } else if (arr[0].equals("tree")) {
                    temp = new Node(arr[1]);
                    while (!stack.empty()) {
                        Node child = stack.peek();
                        temp.addChildNode(child);
                        stack.pop();
                    }
                    stack.push(temp);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        root = stack.peek();
        System.out.println(root.getNodeEntity());
        printNodeTree(root);
    }
}
