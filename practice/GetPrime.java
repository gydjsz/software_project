/*
 *
 * 写一个命令行程序， 输入1~20000内的所有素数，按每行5个打印出来，并分析程序中最费时的函数是什么， 如何改进？
 *
 */

public class GetPrime{

	public boolean isPrime(int num){
		for(int i = 2; i <= Math.sqrt(num); ++i){
			if(num % i == 0)
				return false;
		}
		return true;
	}

	public void solve(){
		int flag = 0;
		for(int i = 2; i <= 20000; ++i){
			if(isPrime(i)){
				flag++;
				System.out.print(i + " ");
			}
			if(flag == 5){
				System.out.println();
				flag = 0;
			}
		}

	}

	public static void main(String[] args){
		GetPrime getPrime = new GetPrime();
		getPrime.solve();
	}

}
