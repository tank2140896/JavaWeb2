package com.javaweb.util.core;

import java.util.stream.LongStream;

public class MathUtil {
	
	//获取两数的较大者
	public static int getTheTwoNumOfMax(int x,int y){
		return x>y?x:y;
	}
	
	//获取两数的较小者
	public static int getTheTwoNumOfMin(int x,int y){
		return x>y?y:x;
	}
	
	//获取随机数(范围为[0,seed))(L:Left;C:close;R:right;O:open)
	public static int getRandomNumForLCRO(int seed){
		int num = (int)(Math.random()*seed);
		return num;
	}
	
	//获取随机数(范围为[min,max))(L:Left;C:close;R:right;O:open)
	public static int getRandomNumForLCRO(int min,int max){
		int num = getRandomNumForLCRO(max-min)+min;
		return num;
	}
	
	//获取随机数(范围为(0,seed])(L:Left;C:close;R:right;O:open)
	public static int getRandomNumForLORC(int seed){
		int num = (int)(Math.random()*seed+1);
		return num;
	}
	
	//获取随机数(范围为(min,max])(L:Left;C:close;R:right;O:open)
	public static int getRandomNumForLORC(int min,int max){
		int num = getRandomNumForLORC(max-min)+min;
		return num;
	}
	
	//获取随机数(范围为[0,seed])(L:Left;C:close;R:right;O:open)
	public static int getRandomNumForLCRC(int seed){
		int num = (int)(Math.random()*(seed+1));
		return num;
	}
	
	//获取随机数(范围为[min,max])(L:Left;C:close;R:right;O:open)
	public static int getRandomNumForLCRC(int min,int max){
		int num = getRandomNumForLCRC(max-min)+min;
		return num;
	}
	
	//获取随机数(范围为(0,seed))(L:Left;C:close;R:right;O:open)
	public static int getRandomNumForLORO(int seed){
		int num = (int)(Math.random()*(seed-1))+1;
		return num;
	}
	
	//获取随机数(范围为(min,max))(L:Left;C:close;R:right;O:open)
	public static int getRandomNumForLORO(int min,int max){
		int num = getRandomNumForLORO(max-min)+min;
		return num;
	}

	//判断是否是偶数(true:是;false:不是)
	public static boolean odevity(Number number) {
		return (number.longValue()&1)==0?true:false;
	}

	//判断是否是素数(true:是;false:不是)
    public static boolean isPrime(long number) {
        return !LongStream.rangeClosed(2,Math.round(Math.sqrt(number))).anyMatch(n->number%n==0);
    }

    //判断某个无符号整数是不是2的幂或0(true:是;false:不是)
	public static boolean isPowerTwoOrZero(long number) {
		return (number&(number-1))==0?true:false;
	}

	//判断某个无符号整数是不是2的幂-1或0或所有位元都是1(true:是;false:不是)
	public static boolean isPowerTwoSubOneOrZeroOrAllOne(long number) {
		return (number&(number+1))==0?true:false;
	}

	//将该值的二进制表示数中值为1且最靠右的位元关闭(变为0),如果是2的幂或0则返回0
	public static long turnOffTheRight(long number) {
		return number&(number-1);
	}

	//将该值的二进制表示数中值为0且最靠右的位元打开(变为1)
	public static long turnOnTheRight(long number) {
		return number|(number+1);
	}

	//将该值的二进制表示数中的尾部的1都变成0(1010->1010;1011->1000)
	public static long turnOffTheTail(long number) {
		return number&(number+1);
	}

	//将该值的二进制表示数中的尾部的0都变成1(1010->1011;1011->1011)
	public static long turnOnTheTail(long number) {
		return number|(number-1);
	}

	//将该值的二进制表示数中的最靠右且值为0的位元变为1,且将其余位元都变为0(10010101->00000010)
	public static long turnOnTheRightAndOtherZero(long number) {
		return (~number)&(number+1);
	}

	//将该值的二进制表示数中的最靠右且值为1的位元变为0,且将其余位元都变为1(101010->111101)
	public static long turnOffTheRightAndOtherOne(long number) {
		return (~number)|(number-1);
	}

	//将该值的二进制表示数中的尾部所有值为0的位元变为1,且将其余位元都变为0(1010100->0000011)
	public static long turnOnTheTailAndOtherZero(long number) {
		return (~number)&(number-1);//~(number|-number)//(number&-number)-1
	}

	//将该值的二进制表示数中的尾部所有值为1的位元变为0,且将其余位元都变为1(1010100->1111111;10011->11100)
	public static long turnOffTheTailAndOtherOne(long number) {
		return (~number)|(number+1);
	}

	//将该值的二进制表示数中的最靠右的1保留,且将其余位元都变为0(1010100->0000100)
	public static long keepTheRightOfOneAndOtherZero(long number) {
		return number&(-number);
	}

	//将该值的二进制表示数中的最靠右的1的后方所有值为0的都变为1,前方所有值都变为0(1010100->0000111)
	public static long theRightOfOneForRightOneAndLeftZero(long number) {
		return number^(number-1);
	}

	//将该值的二进制表示数中的最靠右的0的后方所有值为1的都变为1,前方所有值都变为0(1010100->0000001)
	public static long theRightOfZeroForRightOneAndLeftZero(long number) {
		return number^(number+1);
	}

	//将该值的二进制表示数中右侧连续出现且值为1的位元置0(1010100->1010000)
	public static long theRightOneZero(long number) {
		return ((number|(number-1))+1)&number;//(number&(-number)+number)&number
	}

	//将该值的二进制表示数中求出下一个值比该值大且1的个数与该值相同的数
	public static long greaterThanAndNumOfOneSame (long number) {
		long x = number&(-number);
		long y = number + x;
		long z = number^y;
		z = (z>>number)/x;
		return y|z;
	}

}
