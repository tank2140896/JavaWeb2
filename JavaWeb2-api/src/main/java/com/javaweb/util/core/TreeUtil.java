package com.javaweb.util.core;

import com.javaweb.util.help.tree.BinaryTree;

public class TreeUtil {
	
	/**
	<<算法导论>>的伪代码如下:
	PARENT(i)
	1 return [i/2]
	*/
	//根据某一节点下标计算其父节点下标(注意:是按照下标0开始计算的)
	public static Long getParentIndexByCurrentIndex(Long currentIndex){
		return (currentIndex-1)>>1;//(currentIndex-1)/2
	}
	
	/**
	<<算法导论>>的伪代码如下:
	LEFT(i)
	1 return 2i
	*/
	//根据某一节点下标计算其左节点下标(注意:是按照下标0开始计算的)
	public static Long getLeftIndexByCurrentIndex(Long currentIndex){
		return (currentIndex<<1)+1;//currentIndex*2+1
	}
	
	/**
	<<算法导论>>的伪代码如下:
	RIGHT(i)
	1 return 2i+1
	*/
	//根据某一节点下标计算其右节点下标(注意:是按照下标0开始计算的)
	public static Long getRightIndexByCurrentIndex(Long currentIndex){
		return (currentIndex<<1)+2;//currentIndex*2+2;
	}
	
	//计算堆的高度(不算根节点所在的那一层)
	public static Long getHeapHight(Long heapLength){
		double tmp = Math.log(heapLength)/Math.log(2);//采用换底公式
		return Long.parseLong(String.valueOf(tmp).split("\\.")[0]);
	}
	
	//随机构建二叉树
	public static BinaryTree<Integer> buildRandomBinaryTree(Integer array[]){
		BinaryTree<Integer> tree = new BinaryTree<>();
		for(int i=0;i<array.length;i++){
			int num = MathUtil.getRandomNumForLCRO(i,array.length);
			if(num!=i){
				array[num] = array[num] ^ array[i];
				array[i] = array[num] ^ array[i];
				array[num] = array[num] ^ array[i];
			}
			buildBinaryTree(tree,array[i]);
		}
		return tree;
	}
	
	//直接构建二叉树
	public static BinaryTree<Integer> buildDirectBinaryTree(Integer array[]){
		BinaryTree<Integer> tree = new BinaryTree<>();
		for(int i=0;i<array.length;i++){
			buildBinaryTree(tree,array[i]);
		}
		return tree;
	}
	
	//构建二叉树(新增节点值也是调用该方法)
	public static void buildBinaryTree(BinaryTree<Integer> tree,Integer value){
		if(tree.getUniqueIndex()==null){//表示根节点
			tree.setIndex(0L);
			tree.setUniqueIndex(SecretUtil.getRandomUUID());
			tree.setValue(value);
		}else{
			if(tree.getValue()>value){//相当于父节点的值大于输入值
				if(tree.getLeftNode()==null){
					BinaryTree<Integer> leftNode = new BinaryTree<>();
					leftNode.setIndex(getLeftIndexByCurrentIndex(tree.getIndex()));
					leftNode.setUniqueIndex(SecretUtil.getRandomUUID());
					leftNode.setValue(value);
					tree.setLeftNode(leftNode);
				}else{
					buildBinaryTree(tree.getLeftNode(),value);
				}
			}else{//相当于父节点的值小于等于输入值
				if(tree.getRightNode()==null){
					BinaryTree<Integer> rightNode = new BinaryTree<>();
					rightNode.setIndex(getRightIndexByCurrentIndex(tree.getIndex()));
					rightNode.setUniqueIndex(SecretUtil.getRandomUUID());
					rightNode.setValue(value);
					tree.setRightNode(rightNode);
				}else{
					buildBinaryTree(tree.getRightNode(),value);
				}
			}
		}
	}
	
	/**
	<<算法导论>>的伪代码如下:
	INORDER-TREE-WALK(x)
	if x != NIL
		INORDER-TREE-WALK(x.left)
		print x.key
		INORDER-TREE-WALK(x.right)
	*/
	//中序遍历二叉树
	public static void middleOrderBinaryTree(BinaryTree<Integer> binaryTree){
		if(binaryTree!=null){
			middleOrderBinaryTree(binaryTree.getLeftNode());
			System.out.println(binaryTree.getValue());
			middleOrderBinaryTree(binaryTree.getRightNode());
		}
	}
	
	/**
	<<算法导论>>的伪代码如下:
	TREE-SEARCH(x,k)
	if x==NIL or k==x.key
		return x
	if k<x.key
		return TREE-SEARCH(x.left,k)
	else return TREE-SEARCH(x.right,k)
	*/
	//二叉树搜索
	public static BinaryTree<Integer> binaryTreeSearch(BinaryTree<Integer> binaryTree,Integer value){
		if(binaryTree==null||binaryTree.getValue()==value){
			return binaryTree;
		}else{
			if(binaryTree.getValue()>value){
				return binaryTreeSearch(binaryTree.getLeftNode(),value);
			}else{
				return binaryTreeSearch(binaryTree.getRightNode(),value);
			}
		}
	}
	
	//删除节点值(分为三种情况:1.无左右节点;2.只有某一节点;3.两个节点都有[较复杂])
	public void BinaryTreeDelete(BinaryTree<Integer> binaryTree,Integer value){
		//TODO
	}
	
	//TODO 红黑树

}
