package dynamicplan;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

import java.util.Arrays;

/**
 * @Description 给你一个 n x n 整数矩阵 arr ，请你返回 非零偏移下降路径 数字和的最小值。
 *
 * 非零偏移下降路径 定义为：从 arr 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
 *
 * @Author kandoka
 * @Date 2023/6/8 11:35
 */
@Slf4j
public class DPFallingPathMinSum {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        int[][] arr = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(minFallingPathSum(arr));
    }

    public static int minFallingPathSum(int[][] grid) {
        if (grid.length == 1) {
            return grid[0][0];
        }
        int[] preChoice = Arrays.copyOf(grid[0], grid[0].length);

        for (int i = 1; i < grid.length; i++) {
            int[] curChoice = new int[grid[i].length];
            log.info("row {} start", i);
            for (int j = 0; j < grid[i].length; j++) {
                int curValue = Integer.MAX_VALUE;
                for (int k = 0; k < preChoice.length; k++) {
                    if (curValue > preChoice[k] && j != k) {
                        curChoice[j] = preChoice[k] + grid[i][j];
                        curValue = preChoice[k];
                    }
                }
            }
            log.info("curChoice:{}", Arrays.toString(curChoice));
            preChoice = curChoice;
            log.info("row {} end", i);
        }

        int rtn = Integer.MAX_VALUE;
        for (int i = 0; i < preChoice.length; i++) {
            rtn = Math.min(rtn, preChoice[i]);
        }
        return rtn;
    }
}
