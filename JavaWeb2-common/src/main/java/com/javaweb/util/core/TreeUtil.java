package com.javaweb.util.core;

import java.util.List;

import com.javaweb.util.help.tree.BinaryTree;
import com.javaweb.util.help.tree.RedBlackEnum;
import com.javaweb.util.help.tree.RedBlackTree;
import com.javaweb.util.help.tree.TreeOrderEnum;

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
	
	//随机构建红黑树
	public static RedBlackTree<Integer> buildRandomRedBlackTree(Integer array[]){
		RedBlackTree<Integer> tree = new RedBlackTree<>();
		for(int i=0;i<array.length;i++){
			int num = MathUtil.getRandomNumForLCRO(i,array.length);
			if(num!=i){
				array[num] = array[num] ^ array[i];
				array[i] = array[num] ^ array[i];
				array[num] = array[num] ^ array[i];
			}
			buildRedBlackTree(tree,array[i]);
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
	
	//直接构建红黑树
	public static RedBlackTree<Integer> buildDirectRedBlackTree(Integer array[]){
		RedBlackTree<Integer> tree = new RedBlackTree<>();
		for(int i=0;i<array.length;i++){
			buildRedBlackTree(tree,array[i]);
		}
		return tree;
	}
	
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
	//构建二叉树(新增节点值也是调用该方法[这是用的是while的方式,也可以采用递归调用buildBinaryTree的方式])
	public static void buildBinaryTree(BinaryTree<Integer> tree,Integer value){
		if(tree.getUniqueIndex()==null){//表示根节点
			tree.setUniqueIndex(SecretUtil.getRandomUUID(true));
			tree.setValue(value);
		}else{
			BinaryTree<Integer> node = new BinaryTree<>();
			node.setUniqueIndex(SecretUtil.getRandomUUID(true));
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
	RB-INSERT(T,z)
	y=T.nil
	x=T.root
	while x!=T.nil
		y=x
		if z.key<x.key
			x=x.left
		else x=x.right
	z.p=y
	if y==T.nil
		T.root=z
	elseif z.key<y.key
		y.left=z
	else y.right=z
	z.left=T.nil
	z.right=T.nil
	z.color=RED
	RB-INSERT-FIXUP(T,z)
	*/
	//构建红黑树(新增节点值也是调用该方法)-该方法与二叉树的新增只是略微不同
	public static RedBlackTree<Integer> buildRedBlackTree(RedBlackTree<Integer> redBlackTree,Integer value){
		if(redBlackTree.getUniqueIndex()==null){//表示根节点
			redBlackTree.setUniqueIndex(SecretUtil.getRandomUUID(true));
			redBlackTree.setValue(value);
			redBlackTree.setRedBlackEnum(RedBlackEnum.RED);//此处无论设置为红色还是黑色都没关系,因为根节点最终都会设置为黑色
		}else{
			RedBlackTree<Integer> node = new RedBlackTree<>();
			node.setUniqueIndex(SecretUtil.getRandomUUID(true));
			node.setValue(value);
			node.setRedBlackEnum(RedBlackEnum.RED);
			RedBlackTree<Integer> tmp = redBlackTree;
			while(true){
				if(tmp.getValue()>value){//相当于父节点的值大于输入值
					if(tmp.getLeftNode()==null){
						tmp.setLeftNode(node);
						node.setParentNode(tmp);
						redBlackTree = redBlackTreeInsertFixup(redBlackTree,tmp.getLeftNode());//维持红黑性
						break;
					}
					tmp = tmp.getLeftNode();
				}else{//相当于父节点的值小于等于输入值
					if(tmp.getRightNode()==null){
						tmp.setRightNode(node);
						node.setParentNode(tmp);
						redBlackTree = redBlackTreeInsertFixup(redBlackTree,tmp.getRightNode());//维持红黑性
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
	public static RedBlackTree<Integer> redBlackTreeInsertFixup(RedBlackTree<Integer> redBlackTree,RedBlackTree<Integer> currentNode){
		//由于已经把所有节点设置为红色,因此当前节点的父节点不应该为红色(参考条件4:如果一个节点是红色的那么它的两个子节点都是黑色),所以若为红色则进行调整
		while((currentNode.getParentNode()==null?null:currentNode.getParentNode().getRedBlackEnum())==RedBlackEnum.RED){
			RedBlackTree<Integer> cp = currentNode.getParentNode();//当前节点的父节点
			RedBlackTree<Integer> cpp = currentNode.getParentNode().getParentNode()==null?null:currentNode.getParentNode().getParentNode();//当前节点的父节点的父节点(有可能没有)
			if(cpp!=null){//前提是当前节点的父节点的父节点存在
				if(cp.getUniqueIndex().equals(cpp.getLeftNode()==null?null:cpp.getLeftNode().getUniqueIndex())){//分支1:当前节点的父节点位于当前节点的父节点的父节点的左分支
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
				}else if(cp.getUniqueIndex().equals(cpp.getRightNode()==null?null:cpp.getRightNode().getUniqueIndex())){//分支2:当前节点的父节点位于当前节点的父节点的父节点的右分支
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
	
	/**
	<<算法导论>>的伪代码如下:
	INORDER-TREE-WALK(x)
	if x != NIL
		INORDER-TREE-WALK(x.left)
		print x.key
		INORDER-TREE-WALK(x.right)
	*/
	//遍历二叉树
	public static List<Integer> binaryTreeOrder(BinaryTree<Integer> binaryTree,List<Integer> list,TreeOrderEnum binaryTreeOrderEnum){
		if(binaryTreeOrderEnum==TreeOrderEnum.FIRST){//先序
			if(binaryTree!=null){
				list.add(binaryTree.getValue());
				binaryTreeOrder(binaryTree.getLeftNode(),list,TreeOrderEnum.FIRST);
				binaryTreeOrder(binaryTree.getRightNode(),list,TreeOrderEnum.FIRST);
			}
		}else if(binaryTreeOrderEnum==TreeOrderEnum.MIDDLE){//中序
			if(binaryTree!=null){
				binaryTreeOrder(binaryTree.getLeftNode(),list,TreeOrderEnum.MIDDLE);
				list.add(binaryTree.getValue());
				binaryTreeOrder(binaryTree.getRightNode(),list,TreeOrderEnum.MIDDLE);
			}
		}else{//后序
			if(binaryTree!=null){
				binaryTreeOrder(binaryTree.getLeftNode(),list,TreeOrderEnum.LAST);
				binaryTreeOrder(binaryTree.getRightNode(),list,TreeOrderEnum.LAST);
				list.add(binaryTree.getValue());
			}
		}
		return list;
	}
	
	//遍历红黑树
	public static List<Integer> redBlackTreeOrder(RedBlackTree<Integer> redBlackTree,List<Integer> list,TreeOrderEnum binaryTreeOrderEnum){
		if(binaryTreeOrderEnum==TreeOrderEnum.FIRST){//先序
			if(redBlackTree!=null){
				list.add(redBlackTree.getValue());
				redBlackTreeOrder(redBlackTree.getLeftNode(),list,TreeOrderEnum.FIRST);
				redBlackTreeOrder(redBlackTree.getRightNode(),list,TreeOrderEnum.FIRST);
			}
		}else if(binaryTreeOrderEnum==TreeOrderEnum.MIDDLE){//中序
			if(redBlackTree!=null){
				redBlackTreeOrder(redBlackTree.getLeftNode(),list,TreeOrderEnum.MIDDLE);
				list.add(redBlackTree.getValue());
				redBlackTreeOrder(redBlackTree.getRightNode(),list,TreeOrderEnum.MIDDLE);
			}
		}else{//后序
			if(redBlackTree!=null){
				redBlackTreeOrder(redBlackTree.getLeftNode(),list,TreeOrderEnum.LAST);
				redBlackTreeOrder(redBlackTree.getRightNode(),list,TreeOrderEnum.LAST);
				list.add(redBlackTree.getValue());
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
	
	//红黑树搜索(递归方式)
	public static RedBlackTree<Integer> redBlackTreeSearchWithRecursion(RedBlackTree<Integer> redBlackTree,Integer value){
		if(redBlackTree==null||redBlackTree.getValue()==value){
			return redBlackTree;
		}else{
			if(redBlackTree.getValue()>value){
				return redBlackTreeSearchWithRecursion(redBlackTree.getLeftNode(),value);
			}else{
				return redBlackTreeSearchWithRecursion(redBlackTree.getRightNode(),value);
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
	
	//红黑树搜索(while方式)
	public static RedBlackTree<Integer> redBlackTreeSearchWithWhile(RedBlackTree<Integer> redBlackTree,Integer value){
		while(redBlackTree!=null&&redBlackTree.getValue()!=value){
			if(redBlackTree.getValue()>value){
				redBlackTree = redBlackTree.getLeftNode();
			}else{
				redBlackTree = redBlackTree.getRightNode();
			}
		}
		return redBlackTree;
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
	
	//获得红黑树的最大节点
	public static RedBlackTree<Integer> getMaxNode(RedBlackTree<Integer> redBlackTree){
		while(redBlackTree.getRightNode()!=null){
			redBlackTree = redBlackTree.getRightNode();
		}
		return redBlackTree;
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
	
	//获得红黑树的最小节点
	public static RedBlackTree<Integer> getMinNode(RedBlackTree<Integer> redBlackTree){
		while(redBlackTree.getLeftNode()!=null){
			redBlackTree = redBlackTree.getLeftNode();
		}
		return redBlackTree;
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
	
	//获得红黑树某个节点的后继
	public static RedBlackTree<Integer> getRedBlackTreeSuccessor(RedBlackTree<Integer> currentNode){
		if(currentNode==null){
			return null;
		}
		if(currentNode.getRightNode()!=null){//如果当前结点有右结点,则下一个遍历的是右子树的最左结点
			return getMinNode(currentNode.getRightNode());
		}
		RedBlackTree<Integer> parentNode = currentNode.getParentNode();//获得当前节点的父节点
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
	
	//获得红黑树某个节点的前驱
	public static RedBlackTree<Integer> getRedBlackTreePrecursor(RedBlackTree<Integer> currentNode){
		if(currentNode==null){
			return null;
		}                                   
		if(currentNode.getLeftNode()!=null){//如果当前结点有左结点,则下一个遍历的是左子树的最右结点
			return getMaxNode(currentNode.getLeftNode());
		}
		RedBlackTree<Integer> parentNode = currentNode.getParentNode();
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
			BinaryTree<Integer> array[] = binaryTreeDeleteTransplant(binaryTree,currentNode,currentNode.getRightNode());
			binaryTree = array[0];
			currentNode = array[1];
			currentNode.setRightNode(array[2]);
		}else if(currentNode.getRightNode()==null){
			BinaryTree<Integer> array[] = binaryTreeDeleteTransplant(binaryTree,currentNode,currentNode.getLeftNode());
			binaryTree = array[0];
			currentNode = array[1];
			currentNode.setLeftNode(array[2]);
		}else{
			BinaryTree<Integer> node = getMinNode(currentNode.getRightNode());//可以理解为就是后继
			if(!node.getParentNode().getUniqueIndex().equals(currentNode.getUniqueIndex())){
				BinaryTree<Integer> array[] = binaryTreeDeleteTransplant(binaryTree,node,node.getRightNode());
				binaryTree = array[0];
				node = array[1];
				node.setRightNode(array[2]);
				node.setRightNode(currentNode.getRightNode());
				node.getRightNode().setParentNode(node);
			}
			BinaryTree<Integer> array[] = binaryTreeDeleteTransplant(binaryTree,currentNode,node);
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
	RB-DELETE(T,z)
	y=z
	y-original-color=y.color
	if z.left == T.nil
		x=z.right
		RB-TRANSPLANT(T,z,z.right)
	elseif z.right == T.nil
		RB-TRANSPLANT(T,z,z.left)
	else y = TREE-MINIMUM(z.right)
		y-original-color=y.color
		x=y.right
		if y.p == z
			x.p=y
		else RB-TRANSPLANT(T,y,y.right)
			 y.right = z.right
			 y.right.p = y
		RB-TRANSPLANT(T,z,y)
		y.left=z.left
		y.left.p=y
		y.color=z.color
	if y-original-color==BLACK
		RB-DELETE-FIXUP(T,x)
	*/
	//红黑树节点删除
	public static RedBlackTree<Integer> deleteRedBlackTreeNode(RedBlackTree<Integer> redBlackTree,RedBlackTree<Integer> currentNode){
		RedBlackTree<Integer> x = null;
		RedBlackTree<Integer> y = currentNode;
		RedBlackEnum rbe = y.getRedBlackEnum();
		if(currentNode.getLeftNode()==null){
			x = currentNode.getRightNode();
			RedBlackTree<Integer> array[] = redBlackTreeDeleteTransplant(redBlackTree,currentNode,currentNode.getRightNode());
			redBlackTree = array[0];
			currentNode = array[1];
			currentNode.setRightNode(array[2]);
		}else if(currentNode.getRightNode()==null){
			RedBlackTree<Integer> array[] = redBlackTreeDeleteTransplant(redBlackTree,currentNode,currentNode.getLeftNode());
			redBlackTree = array[0];
			currentNode = array[1];
			currentNode.setLeftNode(array[2]);
		}else{
			y = getMinNode(currentNode.getRightNode());//可以理解为就是后继
			rbe = y.getRedBlackEnum();
			x = y.getRightNode();
			if(x!=null&&y.getParentNode().getUniqueIndex().equals(currentNode.getUniqueIndex())){
				x.setParentNode(y);
			}else{
				RedBlackTree<Integer> array[] = redBlackTreeDeleteTransplant(redBlackTree,currentNode,currentNode.getRightNode());
				redBlackTree = array[0];
				currentNode = array[1];
				currentNode.setRightNode(array[2]);
				y.setLeftNode(currentNode.getLeftNode());
				y.getLeftNode().setParentNode(y);
				y.setRedBlackEnum(currentNode.getRedBlackEnum());
			}
			RedBlackTree<Integer> array[] = redBlackTreeDeleteTransplant(redBlackTree,currentNode,y);
			redBlackTree = array[0];
			currentNode = array[1];
			y = array[2];
			y.setLeftNode(currentNode.getLeftNode());
			y.getLeftNode().setParentNode(y);
			y.setRedBlackEnum(currentNode.getRedBlackEnum());
		}
		if(rbe==RedBlackEnum.BLACK){
			redBlackTree = redBlackTreeDeleteFixup(redBlackTree,x);
		}
		return redBlackTree;
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
	public static BinaryTree<Integer>[] binaryTreeDeleteTransplant(BinaryTree<Integer> binaryTree,BinaryTree<Integer> u,BinaryTree<Integer> v){
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
	RB-TRANSPLANT(T,u,v)
	if u.p == NIL
		T.root = v
	elseif u == u.p.left
		u.p.left = v
	else u.p.right = v
	v.p = u.p
	*/
	//删除树时移动子树的过程(只负责要删除的节点和其左右节点的链接关系)
	@SuppressWarnings("unchecked")
	public static RedBlackTree<Integer>[] redBlackTreeDeleteTransplant(RedBlackTree<Integer> redBlackTree,RedBlackTree<Integer> u,RedBlackTree<Integer> v){
		if(u.getParentNode()==null){
			redBlackTree = v;
		}else if(u.getUniqueIndex().equals(u.getParentNode().getLeftNode()==null?null:u.getParentNode().getLeftNode().getUniqueIndex())){
			u.getParentNode().setLeftNode(v);
		}else{
			u.getParentNode().setRightNode(v);
		}
		if(v!=null){
			v.setParentNode(u.getParentNode());
		}
		return new RedBlackTree[]{redBlackTree,u,v};
	}
	
	/**
	<<算法导论>>的伪代码如下:
	RB-DELETE-FIXUP(T,x)
	while x!=T.root and x.color==BLACK
		if x==x.p.left
			w=x.p.right
			if w.color==RED
				w.color=BLACK
				x.p.color=RED
				LEFT-ROTATE(T,x.p)
				w=x.p.right
			if w.left.color==BLACK and w.right.color==BLACK
				w.color=RED
				x=x.p
		    else if	w.right.color==BLACK
		    	w.left.color=BLACK
		    	w.color=RED
		    	RIGHT-ROTATE(T,w)
				w=x.p.right
			w.color=x.p.color
			x.p.color=BLACK
			w.right.color=BLACK
			LEFT-ROTATE(T,x.p)
			x=T.root
		else (same as then clause with "right" and "left" exchanged)
	x.color=BLACK
	*/
	//红黑树删除修正
	public static RedBlackTree<Integer> redBlackTreeDeleteFixup(RedBlackTree<Integer> redBlackTree,RedBlackTree<Integer> currentNode){
		while(currentNode!=null&&currentNode.getParentNode()!=null&&currentNode.getRedBlackEnum()==RedBlackEnum.BLACK){
			if(currentNode.getUniqueIndex().equals(currentNode.getParentNode().getLeftNode()==null?null:currentNode.getParentNode().getLeftNode().getUniqueIndex())){
				RedBlackTree<Integer> tmp = currentNode.getParentNode().getRightNode();
				if(tmp!=null&&tmp.getRedBlackEnum()==RedBlackEnum.RED){
					tmp.setRedBlackEnum(RedBlackEnum.BLACK);
					currentNode.getParentNode().setRedBlackEnum(RedBlackEnum.RED);
					redBlackTree = redBlackTreeLeftRotate(redBlackTree,currentNode.getParentNode());
					tmp = currentNode.getParentNode().getRightNode();
				}else if(tmp!=null&&tmp.getLeftNode().getRedBlackEnum()==RedBlackEnum.BLACK&&tmp.getRightNode().getRedBlackEnum()==RedBlackEnum.BLACK){
					tmp.setRedBlackEnum(RedBlackEnum.RED);
					currentNode = currentNode.getParentNode();
				}else if(tmp!=null&&tmp.getRightNode().getRedBlackEnum()==RedBlackEnum.BLACK){
					tmp.getLeftNode().setRedBlackEnum(RedBlackEnum.BLACK);
					tmp.setRedBlackEnum(RedBlackEnum.RED);
					redBlackTree = redBlackTreeRightRotate(redBlackTree,tmp);
					tmp = currentNode.getParentNode().getRightNode();
				}else if(tmp!=null){
					tmp.setRedBlackEnum(currentNode.getParentNode().getRedBlackEnum());
					currentNode.getParentNode().setRedBlackEnum(RedBlackEnum.BLACK);
					tmp.getRightNode().setRedBlackEnum(RedBlackEnum.BLACK);
					redBlackTree = redBlackTreeLeftRotate(redBlackTree,currentNode.getParentNode());
					currentNode = redBlackTree;
				}
			}else if(currentNode.getUniqueIndex().equals(currentNode.getParentNode().getRightNode()==null?null:currentNode.getParentNode().getRightNode().getUniqueIndex())){
				RedBlackTree<Integer> tmp = currentNode.getParentNode().getLeftNode();
				if(tmp!=null&&tmp.getRedBlackEnum()==RedBlackEnum.RED){
					tmp.setRedBlackEnum(RedBlackEnum.BLACK);
					currentNode.getParentNode().setRedBlackEnum(RedBlackEnum.RED);
					redBlackTree = redBlackTreeRightRotate(redBlackTree,currentNode.getParentNode());
					tmp = currentNode.getParentNode().getLeftNode();
				}else if(tmp!=null&&tmp.getRightNode().getRedBlackEnum()==RedBlackEnum.BLACK&&tmp.getLeftNode().getRedBlackEnum()==RedBlackEnum.BLACK){
					tmp.setRedBlackEnum(RedBlackEnum.RED);
					currentNode = currentNode.getParentNode();
				}else if(tmp!=null&&tmp.getLeftNode().getRedBlackEnum()==RedBlackEnum.BLACK){
					tmp.getRightNode().setRedBlackEnum(RedBlackEnum.BLACK);
					tmp.setRedBlackEnum(RedBlackEnum.RED);
					redBlackTree = redBlackTreeLeftRotate(redBlackTree,tmp);
					tmp = currentNode.getParentNode().getLeftNode();
				}else if(tmp!=null){
					tmp.setRedBlackEnum(currentNode.getParentNode().getRedBlackEnum());
					currentNode.getParentNode().setRedBlackEnum(RedBlackEnum.BLACK);
					tmp.getLeftNode().setRedBlackEnum(RedBlackEnum.BLACK);
					redBlackTree = redBlackTreeRightRotate(redBlackTree,currentNode.getParentNode());
					currentNode = redBlackTree;
				}
			}else{
				break;
			}
		}
		return redBlackTree;
	}
	
	/** 数据结构的扩张
	//红黑树获取第i小的节点
	public static RedBlackTree<Integer> osSelect(RedBlackTree<Integer> redBlackTree,Long i){
		Long r = 1L;
		try{
			r = redBlackTree.getLeftNode().getSize()+1L;
		}catch(Exception e){ }
		if(r==i){
			return redBlackTree;
		}else if(i<r){
			return osSelect(redBlackTree.getLeftNode(),i);
		}else{
			return osSelect(redBlackTree.getRightNode(),i-r);
		}
	}
	
	//红黑树获取当前节点的秩
	public static Long osRank(RedBlackTree<Integer> currentNode){
		RedBlackTree<Integer> temp = currentNode;
		Long r = 1L;
		try{
			r = currentNode.getLeftNode().getSize()+1L;
		}catch(Exception e){
		}
		while(temp!=null&&temp.getParentNode()!=null){
			try{
				if(temp.getUniqueIndex().equals(temp.getParentNode().getRightNode().getUniqueIndex())){
					r = r+temp.getParentNode().getLeftNode().getSize()+1;
				}
			}catch(Exception e){ }
			temp = temp.getParentNode();
		}
		return r;
	}
	*/

}