package com.javaweb.util.help.tree;

/**
 * 1.不是黑色就是红色
 * 2.根节点和叶节点都是黑色的
 * 3.每个红色节点的父节点都是黑色的
 * 4.任一节点到叶子节点都有相同数目的黑色节点
 */
public class RedBlackTree extends BinaryTree<Number> {

	private static final long serialVersionUID = 1517872371388867427L;
	
	private RedBlackEnum redBlackEnum;

	public RedBlackEnum getRedBlackEnum() {
		return redBlackEnum;
	}

	public void setRedBlackEnum(RedBlackEnum redBlackEnum) {
		this.redBlackEnum = redBlackEnum;
	}

}
