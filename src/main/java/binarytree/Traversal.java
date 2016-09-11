package binarytree;

import java.util.Stack;

/**
 * Created by yunyue on 16/9/9.
 */

public class Traversal {

    /**
     * 二叉树的遍历，插入和删除；
     */

    public class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 先序遍历，递归式
     * @param head
     */
    public void preOrderRecur(Node head) {

        if (head == null) {
            return;
        }

        System.out.println("pre-order: ");

        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 中序遍历，递归式
     * @param head
     */
    public void inOrderRecur(Node head) {

        if (head == null) {
            return;
        }

        System.out.println("in-order: ");

        inOrderRecur(head.left);
        System.out.println(head.value + " ");
        inOrderRecur(head.right);
    }

    /**
     * 后序遍历，递归式
     * @param head
     */
    public void posOrderRecur(Node head) {

        if (head == null) {
            return;
        }

        System.out.println("pos-order: ");

        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.println(head.value + " ");
    }

    /**
     * 先序遍历，非递归式
     * 循环：弹出栈顶节点cur，将cur的右子节点(不为空)压入栈中，再将cur的左子节点(不为空)压入栈中
     * @param head
     */
    public void preOrderUnRecur(Node head) {

        System.out.println("pre-order: ");
        /*
        递归方法无非就是利用函数栈来保存信息，如果利用自己申请的数据结构来代替函数栈，就可以实现非递归遍历；
         */
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()) {

                head = stack.pop();
                System.out.println(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }

            }
        }

        System.out.println(" ");

    }

    /**
     * 中序遍历，非递归式
     * 循环：从头节点cur开始，不断重复将左子节点压入栈中cur = cur.left，直到左子节点为空，从栈中弹出节点node，并令cur = node.right
     * @param head
     */
    public void inOrderUnRecur(Node head) {

        System.out.println("in-order: ");

        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);

            while (!stack.isEmpty()){

                while ((head != null) && (head.left != null)) {
                    stack.push(head.left);
                    head = head.left;
                }

                head = stack.pop();
                System.out.println(head.value + " ");
                head = head.right;

            }

        }

        System.out.println(" ");

    }

    /**
     * 后序遍历，非递归式
     * 方法1：使用两个栈
     * @param head
     */
    public void posOrderUnRecur1(Node head) {

        System.out.println("pos-order: ");

        if (head != null) {

            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);

            while (!s1.isEmpty()) {
                //从s1中弹出节点cur；
                head = s1.pop();
                //立刻将cur推入s2中；
                s2.push(head);
                //向s1中依次推入cur的左子节点和右子节点；
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }

            //从s2中依次弹出节点并打印;
            while (!s2.isEmpty()) {
                System.out.println(s2.pop().value + " ");
            }
        }

        System.out.println(" ");

    }

    /**
     * 后序遍历，非递归式
     * 方法2：使用1个栈
     * @param head
     */
    public void posOrderUnRecur2(Node head) {

        System.out.println("pos-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            //top is the top of stack, initialized with null;
            Node top;
            while (!stack.isEmpty()) {
                //head的意义是最近一次弹出并打印的节点
                //top的意义是栈顶节点
                top = stack.peek();
                if (top.left != null && head != top.left && head != top.right) {
                    //top的左子树尚未被打印出来；
                    stack.push(top.left);
                } else if (top.right != null && head != top.right) {
                    //top的右子树尚未被打印出来，不检查head != top.left是因为先push进top.left再push进top.right；
                    stack.push(top.right);
                } else {
                    System.out.println(stack.pop().value + " ");
                    head = top;
                }
            }
        }

        System.out.println(" ");
    }

}
