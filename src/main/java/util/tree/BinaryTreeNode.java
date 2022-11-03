package util.tree;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2022/11/3 14:25
 */
@Data
@Accessors(chain = true)
public class BinaryTreeNode {
    private int val;
    private int index;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public String toString() {
        return "{" + index + ":" + val + "}";
    }
}
