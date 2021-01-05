package Project;

import Project.Node;

public class NodeTest {

    public static void main(String[] args) {
        //创建节点
        Node node = new Node("0");
        //填充节点数据
        for (int i = 0; i < 10; i++) {
            node.addChildNode(new Node(String.valueOf(i)));
        }
        //打印节点内容
        Node.printNodeTree(node);
    }

}
