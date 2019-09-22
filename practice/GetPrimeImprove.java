/**
 * 每次都需要判断一个数是否为素数，每次判断素数都得判断它前面的数是否能整除该数
 *素数求法改进：

 定理：每个合数都能分解成几个质数相乘的形式
 
改进：将每次求得的质数存起来, 不断用最小质因数*一个合数来筛出素数,为了避免重复筛选，当合数能够被质因数整除时，则进行下一个合数的筛选
 *
 */

import java.util.Arrays;

public class GetPrimeImprove{
	private int[] prime;
	private boolean[] visit;
	private final int n = 20000;

	public GetPrimeImprove(){
		prime = new int[n + 10];
		visit = new boolean[n + 10];
		Arrays.fill(visit, true);
	}

	public int getPrime(){
		int res = 0;
		for(int i = 2; i <= n; ++i){
			if(visit[i])
				prime[res++] = i;
			for(int j = 0; j < res && prime[j] * i <= n; ++j){
				visit[prime[j] * i] = false;
				if(i % prime[j] == 0)
					break;
			}
		}
		return res;
	}

	public void printPrime(){
		int res = getPrime();
		for(int i = 0; i < res; ++i){
			System.out.print(prime[i] + " ");
			if(i % 5 == 4)
				System.out.println();
		}
	}

	public static void main(String[] args){
		GetPrimeImprove getPrime = new GetPrimeImprove();
		getPrime.printPrime();
	}

}
