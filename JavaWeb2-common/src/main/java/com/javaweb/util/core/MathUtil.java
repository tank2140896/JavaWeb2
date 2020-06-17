package com.javaweb.util.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.LongStream;

import org.mvel2.MVEL;

import com.javaweb.exception.MatrixException;

public class MathUtil {
    
    //根据公式计算结果(默认四舍五入)
    public static BigDecimal defaultFormulaCalculate(String formula,Map<String,Object> parameterMap) {
        return ((BigDecimal)MVEL.eval(formula,parameterMap)).setScale(2,RoundingMode.HALF_UP);
    }
    
    //BigDecimal大数值比较
    public static boolean bigDecimalCompare(String oneValue,String anotherValue){
    	int result = new BigDecimal(oneValue).compareTo(new BigDecimal(anotherValue));
    	if(result==-1){//oneValue<anotherValue
    		return false;
    	}else{//oneValue>=anotherValue
    		return true;
    	}
    }
    
    //计算逆序数
    public static int getInversionNumber(int[] array) {
        int count = 0;
        int index = 1;
        while(index!=array.length) {
            int temp = array[index-1];
            for(int i=index;i<array.length;i++) {
                if(temp>array[i]) {
                    count++;
                }
            } 
            index++;
        }
        return count;
    }
    
    //二阶行列式计算(克拉默法则)
    public static BigDecimal[] cramersRule(int[][] array) {
        int d = array[0][0]*array[1][1]-array[0][1]*array[1][0];//系数
        int d1 = array[0][2]*array[1][1]-array[0][1]*array[1][2];
        int d2 = array[0][0]*array[1][2]-array[0][2]*array[1][0];
        return new BigDecimal[]{new BigDecimal(d1).divide(new BigDecimal(d),1,RoundingMode.HALF_UP),
                                new BigDecimal(d2).divide(new BigDecimal(d),1,RoundingMode.HALF_UP)};
    }
    
    //三阶行列式计算(沙路法则)
    public static BigDecimal[] sandRoadRule(int[][] array) {
        int d = array[0][0]*array[1][1]*array[2][2]+array[1][0]*array[2][1]*array[0][2]+array[0][1]*array[1][2]*array[2][0]
                -(array[0][2]*array[1][1]*array[2][0]+array[0][1]*array[1][0]*array[2][2]+array[0][0]*array[1][2]*array[2][1]);//系数
        int d1 = array[0][3]*array[1][1]*array[2][2]+array[0][1]*array[1][2]*array[2][3]+array[1][3]*array[2][1]*array[0][2]
                 -(array[0][2]*array[1][1]*array[2][3]+array[0][1]*array[1][3]*array[2][2]+array[0][3]*array[1][2]*array[2][1]);
        int d2 = array[0][0]*array[1][3]*array[2][2]+array[0][3]*array[1][2]*array[2][0]+array[1][0]*array[2][3]*array[0][2]
                 -(array[0][2]*array[1][3]*array[2][0]+array[0][3]*array[1][0]*array[2][2]+array[0][0]*array[1][2]*array[2][3]);
        int d3 = array[0][0]*array[1][1]*array[2][3]+array[0][1]*array[1][3]*array[2][0]+array[0][3]*array[1][0]*array[2][1]
                 -(array[0][3]*array[1][1]*array[2][0]+array[0][1]*array[1][0]*array[2][3]+array[0][0]*array[1][3]*array[2][1]);
        return new BigDecimal[]{new BigDecimal(d1).divide(new BigDecimal(d),1,RoundingMode.HALF_UP),
                                new BigDecimal(d2).divide(new BigDecimal(d),1,RoundingMode.HALF_UP),
                                new BigDecimal(d3).divide(new BigDecimal(d),1,RoundingMode.HALF_UP)};
    }
    
    /**
    <<算法导论>>的伪代码如下:
    n=A.rows
    let C be a new n*n matrix
    for i=1 to n
        for j=1 to n
            c[i,j] = 0
            for k=1 to n
                c[i,j]=c[i,j]+a[i,k]+b[k,j]
    return C
    */
    //矩阵相乘(行乘以列)  
    public static int[][] matrixMultiplication(int a[][],int b[][]) throws MatrixException {
        final int aLength = a.length;
        final int bLength = b[0].length;
        //a的列数要与b的行数相同  
        if(aLength!=bLength){  
            throw new MatrixException("第一个矩阵的列数与第二个矩阵的行数不同"); 
        } 
        int newArray[][] = new int[aLength][aLength];
        for(int i=0;i<aLength;i++){
            for(int j=0;j<aLength;j++){
                newArray[i][j] = 0;
                for(int k=0;k<b.length;k++){
                    newArray[i][j] += a[i][k]*b[k][j];
                }
            }
        }
        return newArray;
    }
    
    //矩阵置换(行列置换)  
    public static int[][] maxtrixPermutation(int beforeArray[][]){  
        int newArray[][] = new int[beforeArray[0].length][beforeArray.length];  
        for (int i = 0; i < newArray.length; i++) {  
            for (int j = 0; j < newArray[i].length; j++) {  
                newArray[i][j] = beforeArray[j][i];  
            }  
        }  
        return newArray;  
    }
	
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
    
    //获取最大公约数（欧几里得算法）（延伸扩展：Stein算法）
    public static long getGreatestCommonDivisor(long m,long n){
    	if(n==0){//处理一个为0和两个都为0的情况
    		return m;
    	}
    	long temp = m%n;//核心就是辗转相余法
    	return getGreatestCommonDivisor(n,temp);//m%n，相当于先处理的是m，因此下一个先处理的就是n
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
