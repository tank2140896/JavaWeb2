package com.javaweb.util.help.tree;

import java.io.Serializable;

//二叉树
public class BinaryTree<T extends Number> implements Serializable {

	private static final long serialVersionUID = 510645529723567194L;

	private Long index;//用于计算根节点、叶子节点等的序号
	
	private String uniqueIndex;//唯一索引
	
	private T value;//节点值
	
	private PositionEnum postion;//位置(相对于父节点来说)
	
	private BinaryTree<T> leftNode;//左节点

	private BinaryTree<T> rightNode;//右节点
	
	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getUniqueIndex() {
		return uniqueIndex;
	}

	public void setUniqueIndex(String uniqueIndex) {
		this.uniqueIndex = uniqueIndex;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public PositionEnum getPostion() {
		return postion;
	}

	public void setPostion(PositionEnum postion) {
		this.postion = postion;
	}

	public BinaryTree<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(BinaryTree<T> leftNode) {
		this.leftNode = leftNode;
	}

	public BinaryTree<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(BinaryTree<T> rightNode) {
		this.rightNode = rightNode;
	}

}
