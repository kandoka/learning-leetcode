package dynamicplan;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 * @Author kandoka
 * @Date 2023/6/7 20:05
 */
@Slf4j
public class DPTriangle {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new DPTriangle().minimumTotal(new ArrayList<>(Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        )));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        List<Integer> preLine = Arrays.asList(triangle.get(0).get(0));
        List<Integer> curLine = new ArrayList<>();
        for(int i = 1; i < triangle.size(); i++) {
            //向下
            curLine = new ArrayList<>(Collections.nCopies(triangle.get(i).size(), Integer.MAX_VALUE));
            for (int j = 0; j < triangle.get(i).size(); j++) {
                //向右
                if (j > 0 && j < triangle.get(i).size() - 1) {
                    int min = Math.min(preLine.get(j), preLine.get(j-1)) + triangle.get(i).get(j);
                    curLine.set(j, min);
                } else if (j == 0) {
                    curLine.set(j, preLine.get(j) + triangle.get(i).get(j));
                } else if (j == triangle.get(i).size() - 1) {
                    curLine.set(j, preLine.get(j-1) + triangle.get(i).get(j));
                }
            }
            log.info("pre line: {}", preLine.toString());
            log.info("cur line: {}", curLine.toString());
            preLine = curLine;
        }

        int rtn = Integer.MAX_VALUE;
        for (int i = 0; i < curLine.size(); i++) {
            rtn = Math.min(rtn, curLine.get(i));
        }
        return rtn;
    }
}
