/*
 * 练习数值计算。找出一个整数数组中子数组之和的最大值，例如：数组[1, -2, 3, 5, -1]，返回8（因为符合要求的子数组是 [3, 5]）；数组[1, -2, 3, -8, 5, 1]，返回6（因为符合要求的子数组是 [5, 1]）; 数组[1, -2, 3,-2, 5, 1]，返回7（因为符合要求的子数组是 [3, -2, 5, 1]）
 *
 */

import java.util.Scanner;

public class FindMaxInterval{
	private int[] array;
	private int n;

	public FindMaxInterval(){
		System.out.print("请输入数组的大小：");
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		array = new int[n + 1];
		System.out.println("请输入该数组：");
		for(int i = 1; i <= n; ++i){
			array[i] = in.nextInt();
		}
	}

	public void solve(){
		int start = 0, end = 0;
		for(int i = 1; i <= n; ++i){
			//end = integer.max(0, end);
			//end += array[i];
			//start = integer.max(start, end);
			end = Integer.max(end + array[i], array[i]);
			start = Integer.max(end, start);
		}
		System.out.println(start);
	}

	public static void main(String[] args){
		FindMaxInterval f = new FindMaxInterval();
		f.solve();
	}
}
