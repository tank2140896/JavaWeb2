package com.javaweb.util.core;


public class DynamicProgrammingUtil {
	
	/**
	钢条切割-自顶向下递归实现
	1    2    3    4    5    6    7    8    9    10
	1    5    8    9    10   17   17   20   24   30
	<<算法导论>>的伪代码如下:
	CUT-ROD(p,n)
	if n == 0
	   return 0
	q=负无穷(-∞)
	for i=1 to n
	   q = max(q,p[i]+CUT-ROD(p,n-i))
	return q
	*/
	public static int cutRod(int array[],int len){
		if(len==0){//切成0段就是无价值
			return 0;
		}
		int q = 0;
		for(int i=1;i<=len;i++){//切成几段
			q = MathUtil.getTheTwoNumOfMax(q,array[i-1]+cutRod(array,len-i));
		}
		return q;
	}
	
	/**
	钢条切割-带备忘的自顶向下法
	1    2    3    4    5    6    7    8    9    10
	1    5    8    9    10   17   17   20   24   30
	<<算法导论>>的伪代码如下:
	MEMOIZED-CUT-ROD(p,n)
	let r[0..n] be a new array
	for i=0 to n
	    r[i] = 负无穷(-∞)
	return MEMOIZED-CUT-ROD-AUX(p,n,r)
	*/
	public static int memoizedCutRod(int array[],int len){
		int newArray[] = new int[len];
		for(int i=0;i<newArray.length;i++){
			newArray[i] = 0;
		}
		return memoizedCutRodAux(array,len,newArray);
	}
	
	/**
	<<算法导论>>的伪代码如下:
	MEMOIZED-CUT-ROD-AUX(p,n,r)
	if r[n]>=0
	    return r[n]
	if n==0
	    q=0
	else q=负无穷(-∞)
		for i=1 to n
	    	q = max(q,p[i]+MEMOIZED-CUT-ROD-AUX(p,n-i,r))
	r[n]=q
	return q
	*/
	public static int memoizedCutRodAux(int array[],int len,int newArray[]){
		if(len==0){
			return 0;
		}else{
			int q = 0;
			if(len!=0){
				for(int i=1;i<=len;i++){
					q = MathUtil.getTheTwoNumOfMax(q,array[i-1]+memoizedCutRodAux(array,len-i,newArray));
				}
			}
			newArray[len-1] = q;
			return q;
		}
	}
	
	/**
	钢条切割-自底向上
	1    2    3    4    5    6    7    8    9    10
	1    5    8    9    10   17   17   20   24   30
	<<算法导论>>的伪代码如下:
	BOTTOM-UP-CUT-ROD(p,n)
	let r[0..n] be a new array
	r[0]=0
	for j=1 to n
		q = 负无穷(-∞)
		for i=1 to j
			q=max(q,p[i]+r[j-i])
		r[j]=q
	return r[n]
	*/
	public static int bottomUpCutRod(int array[],int len){
		int newArray[] = new int[len+1];
		newArray[0] = 0;
		for(int j=1;j<=len;j++){
			int q = 0;
			for(int i=1;i<=j;i++){
				q = MathUtil.getTheTwoNumOfMax(q,array[i-1]+newArray[j-i]);
			}
			newArray[j]=q;
		}
		return newArray[len];
	}

}
