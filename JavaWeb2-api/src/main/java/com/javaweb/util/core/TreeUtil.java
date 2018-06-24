package com.javaweb.util.core;

import java.util.List;

import com.javaweb.util.help.tree.BinaryTree;
import com.javaweb.util.help.tree.BinaryTreeOrderEnum;
import com.javaweb.util.help.tree.PositionEnum;

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
			tree.setUniqueIndex(SecretUtil.getRandomUUID());
			tree.setValue(value);
			tree.setPostion(PositionEnum.ROOT);
		}else{
			if(tree.getValue()>value){//相当于父节点的值大于输入值
				if(tree.getLeftNode()==null){
					BinaryTree<Integer> leftNode = new BinaryTree<>();
					leftNode.setUniqueIndex(SecretUtil.getRandomUUID());
					leftNode.setValue(value);
					leftNode.setPostion(PositionEnum.LEFT);
					leftNode.setParentNode(tree);
					tree.setLeftNode(leftNode);
				}else{
					buildBinaryTree(tree.getLeftNode(),value);
				}
			}else{//相当于父节点的值小于等于输入值
				if(tree.getRightNode()==null){
					BinaryTree<Integer> rightNode = new BinaryTree<>();
					rightNode.setUniqueIndex(SecretUtil.getRandomUUID());
					rightNode.setValue(value);
					rightNode.setPostion(PositionEnum.RIGHT);
					rightNode.setParentNode(tree);
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
	//遍历二叉树
	public static List<Integer> binaryTreeOrder(BinaryTree<Integer> binaryTree,List<Integer> list,BinaryTreeOrderEnum binaryTreeOrderEnum){
		if(binaryTreeOrderEnum==BinaryTreeOrderEnum.FIRST){//先序
			if(binaryTree!=null){
				list.add(binaryTree.getValue());
				binaryTreeOrder(binaryTree.getLeftNode(),list,BinaryTreeOrderEnum.FIRST);
				binaryTreeOrder(binaryTree.getRightNode(),list,BinaryTreeOrderEnum.FIRST);
			}
		}else if(binaryTreeOrderEnum==BinaryTreeOrderEnum.MIDDLE){//中序
			if(binaryTree!=null){
				binaryTreeOrder(binaryTree.getLeftNode(),list,BinaryTreeOrderEnum.MIDDLE);
				list.add(binaryTree.getValue());
				binaryTreeOrder(binaryTree.getRightNode(),list,BinaryTreeOrderEnum.MIDDLE);
			}
		}else{//后序
			if(binaryTree!=null){
				binaryTreeOrder(binaryTree.getLeftNode(),list,BinaryTreeOrderEnum.LAST);
				binaryTreeOrder(binaryTree.getRightNode(),list,BinaryTreeOrderEnum.LAST);
				list.add(binaryTree.getValue());
			}
		}
		return list;
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
	//二叉树搜索(递归方式)
	public static BinaryTree<Integer> binaryTreeSearchWithRecursion(BinaryTree<Integer> binaryTree,Integer value){
		if(binaryTree==null||binaryTree.getValue()==value){
			return binaryTree;
		}else{
			if(binaryTree.getValue()>value){
				return binaryTreeSearchWithRecursion(binaryTree.getLeftNode(),value);
			}else{
				return binaryTreeSearchWithRecursion(binaryTree.getRightNode(),value);
			}
		}
	}
	
	/**
	<<算法导论>>的伪代码如下:
	ITERATIVE-TREE-SEARCH(x,k)
	while x!=NIL and k!=x.key
		if k<x.key
			x=x.left
		else x=x.right
	return x
	*/
	//二叉树搜索(while方式)
	public static BinaryTree<Integer> binaryTreeSearchWithWhile(BinaryTree<Integer> binaryTree,Integer value){
		while(binaryTree!=null&&binaryTree.getValue()!=value){
			if(binaryTree.getValue()>value){
				binaryTree = binaryTree.getLeftNode();
			}else{
				binaryTree = binaryTree.getRightNode();
			}
		}
		return binaryTree;
	}
	
	/**
	<<算法导论>>的伪代码如下:
	TREE-MAXIMUM(x)
	while x.right!=NIL
		x=x.right
	return x
	*/
	//获得二叉树的最大节点
	public static BinaryTree<Integer> getMaxNode(BinaryTree<Integer> binaryTree){
		while(binaryTree.getRightNode()!=null){
			binaryTree = binaryTree.getRightNode();
		}
		return binaryTree;
	}
	
	/**
	<<算法导论>>的伪代码如下:
	TREE-MINIMUM(x)
	while x.left!=NIL
		x=x.left
	return x
	*/
	//获得二叉树的最小节点
	public static BinaryTree<Integer> getMinNode(BinaryTree<Integer> binaryTree){
		while(binaryTree.getLeftNode()!=null){
			binaryTree = binaryTree.getLeftNode();
		}
		return binaryTree;
	}
	
	/**
	<<算法导论>>的伪代码如下:
	TREE-SUCCESSOR(x)
	if x.right != NIL
		return TREE-MINIMUM(x.right)
	y = x.p
	while y!=NIL and x==y.right
		x = y
		y = y.p
	return y
	*/
	/**
	获得某个节点的后继:
	1:如果当前结点有右结点,则下一个遍历的是右子树的最左结点
	2:如果当前结点无右结点,若它是父节点的左儿子,则下一遍历的是父节点
	3:如果当前结点无右结点,且它是父节点的右儿子,则所在子树遍历完了.向上寻找一个作为左儿子的祖先结点,那么下一遍历的就是该祖先结点的父节点(一直找到根节点为止)
	如果上面三种情况都没找到,则该节点是树的最后一个结点,无后继结点
	*/
	//获得二叉树某个节点的后继
	public static BinaryTree<Integer> getBinaryTreeSuccessor(BinaryTree<Integer> currentNode){
		if(currentNode==null){
			return null;
		}
		if(currentNode.getRightNode()!=null){//如果当前结点有右结点,则下一个遍历的是右子树的最左结点
			return getMinNode(currentNode.getRightNode());
		}
		BinaryTree<Integer> parentNode = currentNode.getParentNode();//获得当前节点的父节点
		while(parentNode!=null&&currentNode.getPostion()==PositionEnum.RIGHT){//如果当前结点无右结点,且它是父节点的右儿子,则所在子树遍历完了.向上寻找一个作为左儿子的祖先结点,那么下一遍历的就是该祖先结点的父节点(一直找到根节点为止)
			currentNode = parentNode;
			parentNode = currentNode.getParentNode();
		}//如果当前结点无右结点,若它是父节点的左儿子,则下一遍历的是父节点
		return parentNode;
	}
	
	/**
	获得某个节点的前驱(前驱与后继是相反的):
	1:如果当前结点有左结点,则下一个遍历的是左子树的最右结点
	2:如果当前结点无左结点,若它是父节点的右儿子,则下一遍历的是父节点
	3:如果当前结点无左结点,且它是父节点的左儿子,则所在子树遍历完了.向上寻找一个作为右儿子的祖先结点,那么下一遍历的就是该祖先结点的父节点(一直找到根节点为止)
	如果上面三种情况都没找到,则该节点是树的最后一个结点,无后继结点
	*/
	//获得二叉树某个节点的前驱
	public static BinaryTree<Integer> getBinaryTreePrecursor(BinaryTree<Integer> currentNode){
		if(currentNode==null){
			return null;
		}                                   
		if(currentNode.getLeftNode()!=null){//如果当前结点有左结点,则下一个遍历的是左子树的最右结点
			return getMaxNode(currentNode.getLeftNode());
		}
		BinaryTree<Integer> parentNode = currentNode.getParentNode();
		while(parentNode!=null&&currentNode.getPostion()==PositionEnum.LEFT){//如果当前结点无左结点,且它是父节点的左儿子,则所在子树遍历完了.向上寻找一个作为右儿子的祖先结点,那么下一遍历的就是该祖先结点的父节点(一直找到根节点为止)
			currentNode = parentNode;
			parentNode = currentNode.getParentNode();
		}//如果当前结点无左结点,若它是父节点的右儿子,则下一遍历的是父节点
		return parentNode;
	}
	
	/**
	<<算法导论>>的伪代码如下:
	TREE-DELETE(T,z)
	if z.left == NIL
		TRANSPLANT(T,z,z.right)
	elseif z.right == NIL
		TRANSPLANT(T,z,z.left)
	else y = TREE-MINIMUM(z.right)
		if y.p != z
			TRANSPLANT(T,y,y.right)
			y.right = z.right
			y.right.p = y
		TRANSPLANT(T,z,y)
		y.left = z.left
		y.left.p = y
	本人的简单思路(代码实现的难点在节点的交换):
	1.若删除节点无左右节点则直接删除
	2.若删除节点只有左节点或右节点,则将其左或右节点及其下面的所有节点(如果有)替换删除的节点
	3.若删除节点既有左节点又有右节点,则查找该节点的后继节点并与要删除的节点交换,然后继续递归调用二叉树删除的步骤
	*/
	//二叉树节点删除
	public static BinaryTree<Integer> deleteBinaryTreeNode(BinaryTree<Integer> binaryTree,BinaryTree<Integer> currentNode){
		if(currentNode.getLeftNode()==null){
			BinaryTree<Integer> array[] = transplant(binaryTree,currentNode,currentNode.getRightNode());
			binaryTree = array[0];
			currentNode = array[1];
			currentNode.setRightNode(array[2]);
		}else if(currentNode.getRightNode()==null){
			BinaryTree<Integer> array[] = transplant(binaryTree,currentNode,currentNode.getLeftNode());
			binaryTree = array[0];
			currentNode = array[1];
			currentNode.setLeftNode(array[2]);
		}else{
			BinaryTree<Integer> node = getMinNode(currentNode.getRightNode());
			if(!node.getParentNode().getUniqueIndex().equals(currentNode.getUniqueIndex())){
				BinaryTree<Integer> array[] = transplant(binaryTree,node,node.getRightNode());
				binaryTree = array[0];
				node = array[1];
				node.setRightNode(array[2]);
				node.setRightNode(currentNode.getRightNode());
				node.getRightNode().setParentNode(node);
			}
			BinaryTree<Integer> array[] = transplant(binaryTree,currentNode,node);
			binaryTree = array[0];
			currentNode = array[1];
			node = array[2];
			node.setLeftNode(currentNode.getLeftNode());
			node.getLeftNode().setParentNode(node);
		}
		return binaryTree;
	}
	
	/**
	<<算法导论>>的伪代码如下:
	TRANSPLANT(T,u,v)
	if u.p == NIL
		T.root = v
	elseif u == u.p.left
		u.p.left = v
	else u.p.right = v
	if v != NIL
		v.p = u.p
	*/
	//删除树时移动子树的过程
	@SuppressWarnings("unchecked")
	private static BinaryTree<Integer>[] transplant(BinaryTree<Integer> binaryTree,BinaryTree<Integer> u,BinaryTree<Integer> v){
		if(u.getParentNode()==null){
			binaryTree = v;
		}else if(u.getUniqueIndex().equals(u.getParentNode().getLeftNode()==null?null:u.getParentNode().getLeftNode().getUniqueIndex())){
			u.getParentNode().setLeftNode(v);
		}else{
			u.getParentNode().setRightNode(v);
		}
		if(v!=null){
			v.setParentNode(u.getParentNode());
		}
		return new BinaryTree[]{binaryTree,u,v};
	}
	
	//TODO 红黑树

}
