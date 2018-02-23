package com.javaweb.util.help.sort;

//插入排序(一句话攻略:如果说冒泡排序是正向处理的,那么插入排序就是反向处理的)
public class InsertSort implements BaseSort<Integer> {

	/**
		<<算法导论>>的伪代码如下:
		for j=2 to A.length
	  		key = A[j]
	  		i = j-1
	  		while i>0 and A[i]>key
	  			A[i+1] = A[i]
	  			i = i-1
	  		A[i+1] = key
	*/
	public Integer[] sort(Integer[] array) {
		final int sortLength = array.length;
		for (int i = 1; i < sortLength; i++) {
			int key = array[i];//从第二个数开始作为key
			int j = i - 1;//从第i个前面一个开始,即[从后往前推],为什么这么做,是有道理的
			while(j>-1&&array[j]>key){//array[j]>key定义了从小到大的顺序
				/**
				 * 解析思想为:
				 * key=0
				 * 1->1 5 9 0(此时指针指向9)
				 * 2->1 5 9 9(此时指针指向5)
				 * 3->1 5 5 9(此时指针指向1)
				 * 4->1 1 5 9(此时指针指向0)
				 * 5->0 1 5 9(结束,将key=0赋值过去)
				 */
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = key;//如果进入while就是把key赋给前一个数,否则就是自己对自己赋值
		}
		return array;
	}
	
	/**
	  个人认为这么写虽然代码不是很好,但是思路是最容易理解的 ,这里可以理解为一个隐式简介置换:
	 0 1 2 3
	 -------
	 1 6 8 5
	 key=5
	 8和5比较,8>5,将8赋给5,此时为[1,6,8,8],下标是2
	 6和5比较,6>5,将6赋给8,此时为[1,6,6,8],下标是1
	 1和5比较,1<5,不作处理,此时为[1,6,6,8],下标是1
	   最后将key=5给下标1,此时为[1,5,6,8]
	 */
	public Integer[] sort2(Integer[] array) {
		final int sortLength = array.length;
		for (int i = 1; i < sortLength; i++) {
			int key = array[i];//从第二个数开始作为key
			boolean change = false;
			int changeIndex = 0;
			for (int j = i-1; j > -1; j--) {
				if(array[j]>key){
					//将每轮的前者赋给后者
					array[j+1] = array[j];
					change = true;
					changeIndex = j;
				}
			}
			if(change){
				array[changeIndex] = key;
			}
		}
		return array;
	}
	
	public Integer[] sort3(Integer[] array) {
		/**
	 	   为什么i不从0开始,因为要称为比较至少要两个数,如果从i=0(即第一个数开始)
		   那么第一个数和谁比较呢?(也不是不能比较,但一般我们都是按常理出牌的)
		   因此可以从i=1(即第二个数开始),这样第二个数就能和第一个比较了
		*/
		for (int i = 1; i < array.length; i++) {
			int key = array[i];//待排序的值
			int count = i;
			//为什么要用while,因为我不确定会循环几次
			while(count>0&&array[count-1]>key){
				//进行两数交换,作用于下面两行
				array[count] = array[count-1];
				array[count-1] = key;
				/**
				   如不进行count--的话,每次就只会比较一次
				   如5,4,3排序,不进行count--的话
				   第一次:count = 2;此时为5,3,4
				   进行count--的话
				   第一次:count = 2;此时为5,3,4
				   第二次:count = 1;此时为3,5,4
				   第三次:count = 0;不符合条件结束
				 count--的作用就是始终将最小数推至最前面
				*/
				count--;
			}
			/**
			   该方法是<<算法导论>>的伪代码的一种实现
			   可以看到,不同点就在于进行了过程交换和最后赋值,其原理为:
			   如进行3,2,5,1排序,假设key=1为此时要进行比较的数
			   第一次,因为5>1,结果为:3,2,5,5
			   第二次,因为2>1,结果为:3,2,2,5
			   第三次,因为3>1,结果为:3(*),3,2,5
			   第四次(最后),将key=1赋给(*)处,结果为:1,3,2,5
			   个人比较喜欢上面一种,理解起来感觉容易点
			 while(count>0&&array[count-1]>key){
			 	array[count] = array[count-1];
				count--;
			 }
			 array[count] = key;
			*/
		}
		return array;
	}
	
}
