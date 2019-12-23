package com.javaweb.util.help.tree;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

//二叉树
public class BinaryTree<T> implements Serializable {

	private static final long serialVersionUID = 510645529723567194L;

	private String uniqueIndex;//唯一索引
	
	private T value;//节点值
	
	private BinaryTree<T> leftNode;//左节点

	private BinaryTree<T> rightNode;//右节点
	
	@JsonIgnore
	private transient BinaryTree<T> parentNode;//父节点

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

	public BinaryTree<T> getParentNode() {
		return parentNode;
	}

	public void setParentNode(BinaryTree<T> parentNode) {
		this.parentNode = parentNode;
	}
	
}
