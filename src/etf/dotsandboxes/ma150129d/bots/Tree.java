package etf.dotsandboxes.ma150129d.bots;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import etf.dotsandboxes.ma150129d.structs.State;

public class Tree {
    public class Node
    {
        private int depth;
        private State state;

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }

        private List<Node> children = new ArrayList<>();
        private int value;

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public etf.dotsandboxes.ma150129d.structs.State getState() {
            return state;
        }

        public void setState(etf.dotsandboxes.ma150129d.structs.State state) {
            this.state = state;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node(State state)
        {
            this.setState(state);
        }

        public void addChild(Node n)
        {
            children.add(n);
        }
    }

    private Node root;
    private int initDepth;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Tree(State state, int depth)
    {
        root = new Node(state);
        initDepth = depth;
        root.depth = depth;
    }

    public Tree(State state)
    {
        root = new Node(state);
        initDepth = 0;
        root.depth = 0;
    }

    public void formTree(int depth)
    {
        int i = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int q = 1;
        while (i < depth)
        {

            int p = q;
            q = 0;
            while (p-- > 0)
            {
                Node tmp = queue.remove();
                int a = 5;
                for (int j = 0; j < tmp.getState().getNum(); j++)
                {
                    for (int k = 1; k <= tmp.getState().getNum(); k++)
                        if (tmp.getState().getNum() == 1)
                        {
                            Node newOne = new Node(tmp.getState());
                            newOne.setDepth(tmp.getDepth() + 1);
                            tmp.addChild(newOne);
                            queue.add(newOne);
                            q++;
                        }
                }
            }
            i++;
        }
    }
}
