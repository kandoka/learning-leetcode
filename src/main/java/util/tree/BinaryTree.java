package util.tree;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2022/11/3 14:37
 */
@Slf4j
public class BinaryTree {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        BinaryTreeNode leftRight = new BinaryTreeNode().setVal(3);
        BinaryTreeNode leftLeft = new BinaryTreeNode().setVal(4);
        BinaryTreeNode left = new BinaryTreeNode().setVal(2).setRight(leftRight).setLeft(leftLeft);
        BinaryTreeNode root = new BinaryTreeNode().setVal(1).setLeft(left);
        log.info(BinaryTree.prettyPrint(root));
    }

    public static int getDepth(BinaryTreeNode root) {
        return root == null ? 0 : (1 + Math.max(getDepth(root.getLeft()), getDepth(root.getLeft())));
    }

    public static String prettyPrint(BinaryTreeNode root) {
        if(root == null) {
            return "树为空";
        }

        int depth = getDepth(root);

        //最后一行的宽度为2^(n-1) * 3 + 1
        int arrHeight = depth * 2 + 1;
        int arrWidth = (2 << (depth - 2)) * 3 + 1;
        //初始化数组
        String[][] target = new String[arrHeight][arrWidth];
        for(int i = 0; i < arrHeight; i++) {
            for(int j = 0; j < arrWidth; j++) {
                target[i][j] = " ";
            }
        }

        //从根节点开始，递归整个树，写入到数组里
        toArray(root, 0, arrWidth / 2, target, depth);
        //打印数组
        StringBuilder sb = new StringBuilder("\n");
        for(String[] line: target) {
            for(int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if(line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            sb.append("\n");
        }
        String print = sb.toString();
        log.info(print);
        return print;
    }

    private static void toArray(BinaryTreeNode curNode, int rowIndex, int colIndex, String[][] target, int depth) {
        if(curNode == null) {
            return;
        }
        //保存当前节点
        target[rowIndex][colIndex] = String.valueOf(curNode.getVal());
        //计算当前位于树的第几层
        int curLevel = (rowIndex + 1) / 2;
//        log.info("curLevel: {}, depth: {}, rowIndex: {}, colIndex: {}, line: {}", curLevel, depth, rowIndex, colIndex, target[rowIndex]);
        if(curLevel == depth) {
            //如果是最后一层，则返回
            return;
        }
        //计算当前行到下一行，每个元素之间的间隔
        int gap = depth - curLevel - 1;

        //递归左子树
        if(curNode.getLeft() != null) {
//            log.info("to left node: {}", curNode.getLeft());
            target[rowIndex + 1][colIndex - gap] = "/";
            toArray(curNode.getLeft(), rowIndex + 2, colIndex - gap * 2, target, depth);
        }

        //递归右子树
        if(curNode.getRight() != null) {
//            log.info("to right node: {}", curNode.getRight());
            target[rowIndex + 1][colIndex + gap] = "\\";
            toArray(curNode.getRight(), rowIndex + 2, colIndex + gap * 2, target, depth);
        }
    }
}
