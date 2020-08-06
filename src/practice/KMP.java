package practice;

import java.util.Arrays;

public class KMP {
	public static void main(String[] args) {
		String target = "ABABCABAA";
		KMP kmp = new KMP();
		int[] next = kmp.getNext(target);
		System.out.println("next=" + Arrays.toString(next));
	}
	public int[] getNext(String str) {
		int[] next = new int[str.length()];
		next[0] = 0;
		for(int i = 1, j = 0; i < str.length(); i++) {
			
			//找到最近匹配数
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = next[j - 1];
			}
			
			//如果字符相等则匹配
			if(str.charAt(i) == str.charAt(j)) {
				j++;
			}
			next[i] = j;//当前匹配数为j
		}
		return next;
	}
}
