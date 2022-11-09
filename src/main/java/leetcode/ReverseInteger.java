package leetcode;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2022/11/9 9:39
 */
@Slf4j
public class ReverseInteger {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        int s = 123456;
        int r = reverse(s);
        log.info("输入：{}，结果：{}", s, r);
    }

    private static int reverse(int x) {
        if(x < 10 && x > -10) {
            return x;
        }

        int reversedX = 0;
        while(x != 0) {
            int digit = x % 10;
            //处理int位数溢出
            if(reversedX > 214748364 || (reversedX == 214748364 && digit > 7)) {
                return 0;
            }
            if(reversedX < -214748364 || (reversedX == -214748364 && digit < -8)) {
                return 0;
            }
            reversedX = reversedX * 10 + digit;
            x = x / 10;
            log.info("当前位数字：{}， 当前反转x：{}，当前x：{}", digit, reversedX, x);
        }
        return reversedX;
    }
}
