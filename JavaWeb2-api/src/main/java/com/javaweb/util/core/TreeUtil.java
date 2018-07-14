package com.javaweb.util.core;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.util.help.tree.BinaryTree;
import com.javaweb.util.help.tree.BinaryTreeOrderEnum;
import com.javaweb.util.help.tree.RedBlackEnum;
import com.javaweb.util.help.tree.RedBlackTree;

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
	
	//构建二叉树(新增节点值也是调用该方法[这是用的是while的方式,也可以采用递归调用buildBinaryTree的方式])
	/**
	<<算法导论>>的伪代码如下:
	TREE-INSERT(T,z)
	y=NIL
	x=T.root
	while x!=NIL
		y=x
		if z.key<x.key
			x=x.left
		else x=x.right
	z.p=y
	if y==NIL
		T.root=z
	elseif z.key<y.key
		y.left=z
	else y.right=z
	*/
	public static void buildBinaryTree(BinaryTree<Integer> tree,Integer value){
		if(tree.getUniqueIndex()==null){//表示根节点
			tree.setUniqueIndex(SecretUtil.getRandomUUID());
			tree.setValue(value);
		}else{
			BinaryTree<Integer> node = new BinaryTree<>();
			node.setUniqueIndex(SecretUtil.getRandomUUID());
			node.setValue(value);
			BinaryTree<Integer> tmp = tree;
			while(true){
				if(tmp.getValue()>value){//相当于父节点的值大于输入值
					if(tmp.getLeftNode()==null){
						tmp.setLeftNode(node);
						break;
					}
					tmp = tmp.getLeftNode();
				}else{//相当于父节点的值小于等于输入值
					if(tmp.getRightNode()==null){
						tmp.setRightNode(node);
						break;
					}
					tmp = tmp.getRightNode();
				}
			}
			node.setParentNode(tmp);
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
		while(parentNode!=null&&parentNode.getRightNode().getUniqueIndex().equals(currentNode.getUniqueIndex())){//如果当前结点无右结点,且它是父节点的右儿子,则所在子树遍历完了.向上寻找一个作为左儿子的祖先结点,那么下一遍历的就是该祖先结点的父节点(一直找到根节点为止)
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
		while(parentNode!=null&&parentNode.getLeftNode().getUniqueIndex().equals(currentNode.getUniqueIndex())){//如果当前结点无左结点,且它是父节点的左儿子,则所在子树遍历完了.向上寻找一个作为右儿子的祖先结点,那么下一遍历的就是该祖先结点的父节点(一直找到根节点为止)
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
			BinaryTree<Integer> node = getMinNode(currentNode.getRightNode());//可以理解为就是后继
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
	//删除树时移动子树的过程(只负责要删除的节点和其左右节点的链接关系)
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
	
	/**
	<<算法导论>>的伪代码如下:
	LEFT-ROTATE(T,x)
	y=x.right
	x.right=y.left
	if y.left!=T.nil
		y.left.p=x
	y.p=x.p
	if x.p==T.nil
		T.root=y
	elseif x==x.p.left
		x.p.left=y
	else x.p.right=y
	y.left=x
	x.p=y
	*/
	//红黑树左旋操作
	public static RedBlackTree<Integer> redBlackTreeLeftRotate(RedBlackTree<Integer> redBlackTree,RedBlackTree<Integer> rotateNode){
		RedBlackTree<Integer> rightNode = rotateNode.getRightNode();//获得左旋基准旋转节点的右节点
		if(rightNode!=null){
			rotateNode.setRightNode(rightNode.getLeftNode());
			if(rightNode.getLeftNode()!=null) {
				rightNode.getLeftNode().setParentNode(rotateNode);
			}
			rightNode.setParentNode(rotateNode.getParentNode());
			if(rotateNode.getParentNode()==null) {//左旋基准旋转节点就是根节点
				redBlackTree = rightNode;
				//下面两个else的意思是:左旋基准旋转节点对于父节点来说的位置判断,即子节点指向了父节点,父节点也需要引用子节点
			}else if(rotateNode.getUniqueIndex().equals(rotateNode.getParentNode().getLeftNode()==null?null:rotateNode.getParentNode().getLeftNode().getUniqueIndex())) {
				rotateNode.getParentNode().setLeftNode(rightNode);
			}else {
				rotateNode.getParentNode().setRightNode(rightNode);
			}
			rightNode.setLeftNode(rotateNode);
			rotateNode.setParentNode(rightNode);
		}
		return redBlackTree;
	}
	
	//红黑树右旋操作(和红黑树左旋操作左右相反)
	public static RedBlackTree<Integer> redBlackTreeRightRotate(RedBlackTree<Integer> redBlackTree,RedBlackTree<Integer> rotateNode){
		RedBlackTree<Integer> leftNode = rotateNode.getLeftNode();
		if(leftNode!=null){
			rotateNode.setLeftNode(leftNode.getRightNode());
			if(leftNode.getRightNode()!=null) {
				leftNode.getRightNode().setParentNode(rotateNode);
			}
			leftNode.setParentNode(rotateNode.getParentNode());
			if(rotateNode.getParentNode()==null) {
				redBlackTree = leftNode;
			}else if(rotateNode.getUniqueIndex().equals(rotateNode.getParentNode().getRightNode()==null?null:rotateNode.getParentNode().getRightNode().getUniqueIndex())) {
				rotateNode.getParentNode().setRightNode(leftNode);
			}else {
				rotateNode.getParentNode().setLeftNode(leftNode);
			}
			leftNode.setRightNode(rotateNode);
			rotateNode.setParentNode(leftNode);
		}
		return redBlackTree;
	}
	
	//构建红黑树(新增节点值也是调用该方法)-该方法与二叉树的新增只是略微不同
	public static RedBlackTree<Integer> buildRedBlackTree(RedBlackTree<Integer> redBlackTree,Integer value){
		if(redBlackTree.getUniqueIndex()==null){//表示根节点
			redBlackTree.setUniqueIndex(SecretUtil.getRandomUUID());
			redBlackTree.setValue(value);
			redBlackTree.setRedBlackEnum(RedBlackEnum.RED);//此处无论设置为红色还是黑色都没关系,因为根节点最终都会设置为黑色
		}else{
			RedBlackTree<Integer> node = new RedBlackTree<>();
			node.setUniqueIndex(SecretUtil.getRandomUUID());
			node.setValue(value);
			node.setRedBlackEnum(RedBlackEnum.RED);
			RedBlackTree<Integer> tmp = redBlackTree;
			while(true){
				if(tmp.getValue()>value){//相当于父节点的值大于输入值
					if(tmp.getLeftNode()==null){
						tmp.setLeftNode(node);
						node.setParentNode(tmp);
						redBlackTree = redBlackTreeFixup(redBlackTree,tmp.getLeftNode());//维持红黑性
						break;
					}
					tmp = tmp.getLeftNode();
				}else{//相当于父节点的值小于等于输入值
					if(tmp.getRightNode()==null){
						tmp.setRightNode(node);
						node.setParentNode(tmp);
						redBlackTree = redBlackTreeFixup(redBlackTree,tmp.getRightNode());//维持红黑性
						break;
					}
					tmp = tmp.getRightNode();
				}
			}
			//node.setParentNode(tmp);
		}
		return redBlackTree;
	}
	
	/**
	<<算法导论>>的伪代码如下:
	RB-INSERT-FIXUP(T,z)
	while z.p.color==RED
		if z.p==z.p.p.left
			y=z.p.p.right
			if y.color==RED
				z.p.color==BLACK
				y.color==BLACK
				z.p.p.color=RED
				z=z.p.p
			else if z==z.p.right
				z==z.p
				LEFT-ROTATE(T,z)
			z.p.color=BLACK
			z.p.p.color=RED
			RIGHT-ROTATE(T,z.p.p)
		else(same as then clause with "right" and "left" exchanged)
	T.root.color=BLACK
	*/
	//对节点重新着色并旋转以此来维持红黑性
	public static RedBlackTree<Integer> redBlackTreeFixup(RedBlackTree<Integer> redBlackTree,RedBlackTree<Integer> currentNode){
		while((currentNode.getParentNode()==null?null:currentNode.getParentNode().getRedBlackEnum())==RedBlackEnum.RED){//由于已经把所有节点设置为红色,因此当前节点的父节点不应该为红色,若为红色则进行调整
			RedBlackTree<Integer> cp = currentNode.getParentNode();//当前节点的父节点
			RedBlackTree<Integer> cpp = currentNode.getParentNode().getParentNode()==null?null:currentNode.getParentNode().getParentNode();//当前节点的父节点的父节点(有可能没有)
			if(cpp!=null){//前提是当前节点的父节点的父节点存在
				if(cp.getUniqueIndex().equals(cpp.getLeftNode()==null?null:cpp.getLeftNode().getUniqueIndex())){
					RedBlackTree<Integer> node = currentNode.getParentNode().getParentNode().getRightNode();
					if(node!=null&&node.getRedBlackEnum()==RedBlackEnum.RED){
						currentNode.getParentNode().setRedBlackEnum(RedBlackEnum.BLACK);
						node.setRedBlackEnum(RedBlackEnum.BLACK);
						currentNode.getParentNode().getParentNode().setRedBlackEnum(RedBlackEnum.RED);
						currentNode = currentNode.getParentNode().getParentNode();
					}else if(currentNode.getUniqueIndex().equals(currentNode.getParentNode().getRightNode()==null?null:currentNode.getParentNode().getRightNode().getUniqueIndex())){
						currentNode = currentNode.getParentNode();
						redBlackTree = redBlackTreeLeftRotate(redBlackTree,currentNode);
					}else{
						currentNode.getParentNode().setRedBlackEnum(RedBlackEnum.BLACK);
						currentNode.getParentNode().getParentNode().setRedBlackEnum(RedBlackEnum.RED);
						redBlackTree = redBlackTreeRightRotate(redBlackTree,currentNode.getParentNode().getParentNode());
					}
				}else if(cp.getUniqueIndex().equals(cpp.getRightNode()==null?null:cpp.getRightNode().getUniqueIndex())){
					RedBlackTree<Integer> node = currentNode.getParentNode().getParentNode().getLeftNode();
					if(node!=null&&node.getRedBlackEnum()==RedBlackEnum.RED){
						currentNode.getParentNode().setRedBlackEnum(RedBlackEnum.BLACK);
						node.setRedBlackEnum(RedBlackEnum.BLACK);
						currentNode.getParentNode().getParentNode().setRedBlackEnum(RedBlackEnum.RED);
						currentNode = currentNode.getParentNode().getParentNode();
					}else if(currentNode.getUniqueIndex().equals(currentNode.getParentNode().getLeftNode()==null?null:currentNode.getParentNode().getLeftNode().getUniqueIndex())){
						currentNode = currentNode.getParentNode();
						redBlackTree = redBlackTreeRightRotate(redBlackTree,currentNode);
					}else{
						currentNode.getParentNode().setRedBlackEnum(RedBlackEnum.BLACK);
						currentNode.getParentNode().getParentNode().setRedBlackEnum(RedBlackEnum.RED);
						redBlackTree = redBlackTreeLeftRotate(redBlackTree,currentNode.getParentNode().getParentNode());
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
		redBlackTree.setRedBlackEnum(RedBlackEnum.BLACK);
		return redBlackTree;
	}
	
	//TODO 红黑树的插入(需理解)和删除(未完成)
	public static void main(String[] args) throws Exception {
		RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
		Integer x[] = new Integer[]{5,2,1,8,7,3,6};
		for(int i=0;i<x.length;i++){
			redBlackTree = buildRedBlackTree(redBlackTree,x[i]);
		}
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(redBlackTree));
	}

}
