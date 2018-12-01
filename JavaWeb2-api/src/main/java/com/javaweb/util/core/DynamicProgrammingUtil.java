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
	//{1,5,8,9,10,17,17,20,24,30}
	public static int cutRod(int array[],int len){
		if(len==0){
			return 0;
		}
		int q = 0;
		for(int i=1;i<=len;i++){//for(int i=len;i>=1;i--)
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
	//{1,5,8,9,10,17,17,20,24,30}
	public static int memoizedCutRod(int array[],int len){
		int newArray[] = new int[len];
		for(int i=0;i<newArray.length;i++){
			newArray[i] = 0;
		}
		int out = memoizedCutRodAux(array,len,newArray);
		//System.out.println(Arrays.toString(newArray));
		return out;
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
	//{1,5,8,9,10,17,17,20,24,30}
	public static int memoizedCutRodAux(int array[],int len,int newArray[]){
		if(len!=0&&newArray[len-1]>0){
			return newArray[len-1];
		}
		int q = 0;
		if(len==0){
			newArray[len] = array[0];
		}else{
			for(int i=1;i<=len;i++){
				q = MathUtil.getTheTwoNumOfMax(q,array[i-1]+memoizedCutRodAux(array,len-i,newArray));
			}
			newArray[len-1] = q;
		}
		return q;
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
	//{1,5,8,9,10,17,17,20,24,30}
	public static int bottomUpCutRod(int array[],int len){
		int newArray[] = new int[len+1];
		newArray[0] = 0;//长度为0的钢条没有收益
		for(int j=1;j<=len;j++){
			int q = 0;
			for(int i=1;i<=j;i++){
				q = MathUtil.getTheTwoNumOfMax(q,array[i-1]+newArray[j-i]);
			}
			newArray[j]=q;
		}
		//System.out.println(Arrays.toString(Arrays.copyOfRange(newArray,1,newArray.length)));
		return newArray[len];
	}
	
	/**
	<<算法导论>>的伪代码如下:
	EXTENDED-BOTTOM-UP-CUT-ROD(p,n)
	let r[0..n] and s[0..n] be new arrays
	r[0]=0
	for j=1 to n
		q = 负无穷(-∞)
		for i=1 to j
			if q<p[i]+r[j-i]
				q = p[i]+r[j-i]
				s[j] = i
		r[j]=q
	return r and s
	*/
	//{0,1,5,8,9,10,17,17,20,24,30}
	public static Object[] extendedBottomUpCutRod(int array[],int len){
		int r[] = new int[len+1];
		int s[] = new int[len+1];
		r[0]=0;
		s[0]=0;
		for(int j=1;j<=len;j++){
			int q = 0;
			for(int i=1;i<=j;i++){
				if(q<array[i-1]+r[j-i]){
					q = array[i-1]+r[j-i];
					s[j] = i;
				}
			}
			r[j]=q;
		}
		return new Object[]{r,s};
	}
	
	/**
	<<算法导论>>的伪代码如下:
	PRINT-CUT-ROD-SOLUTION(p,n)
	(r,s) = EXTENDED-BOTTOM-UP-CUT-ROD(p,n)
	while n>0
		print s[n]
		n = n-s[n]
	*/
	//{0,1,5,8,9,10,17,17,20,24,30}
	public static void printCutRodSolution(int array[],int len){
		Object obj[] = extendedBottomUpCutRod(array,len);
		//int r[] = (int[])obj[0];
		int s[] = (int[])obj[1];
		while(len>0){
			System.out.print(s[len]+" ");
			len = len-s[len];
		}
		System.out.println();
	}

}
