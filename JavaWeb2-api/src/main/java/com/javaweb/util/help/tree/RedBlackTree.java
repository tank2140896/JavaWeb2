package com.javaweb.util.help.tree;

/**
 * 1.不是黑色就是红色
 * 2.根节点是黑色的
 * 3.每个叶节点是黑色的
 * 4.如果一个节点是红色的那么它的两个子节点都是黑色的
 * 5.任一节点到叶子节点任一路径都有相同数目的黑色节点
 */
//红黑树
public class RedBlackTree extends BinaryTree<Number> {

	private static final long serialVersionUID = 1517872371388867427L;
	
	private RedBlackEnum redBlackEnum;//红黑性

	public RedBlackEnum getRedBlackEnum() {
		return redBlackEnum;
	}

	public void setRedBlackEnum(RedBlackEnum redBlackEnum) {
		this.redBlackEnum = redBlackEnum;
	}

}
