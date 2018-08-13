package com.javaweb.util.help.tree;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 1.不是黑色就是红色
 * 2.根节点是黑色
 * 3.每个叶节点是黑色
 * 4.如果一个节点是红色的那么它的两个子节点都是黑色
 * 5.任一节点到叶子节点任一路径都有相同数目的黑色节点
 */
//红黑树
public class RedBlackTree<T> implements Serializable {

	private static final long serialVersionUID = 1517872371388867427L;
	
	private String uniqueIndex;//唯一索引
	
	private T value;//节点值
	
	private RedBlackTree<T> leftNode;//左节点

	private RedBlackTree<T> rightNode;//右节点
	
	@JsonIgnore
	private transient RedBlackTree<T> parentNode;//父节点
	
	private RedBlackEnum redBlackEnum;//红黑性
	
	private Long size;//扩展属性size

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

	public RedBlackTree<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(RedBlackTree<T> leftNode) {
		this.leftNode = leftNode;
	}

	public RedBlackTree<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(RedBlackTree<T> rightNode) {
		this.rightNode = rightNode;
	}

	public RedBlackTree<T> getParentNode() {
		return parentNode;
	}

	public void setParentNode(RedBlackTree<T> parentNode) {
		this.parentNode = parentNode;
	}

	public RedBlackEnum getRedBlackEnum() {
		return redBlackEnum;
	}

	public void setRedBlackEnum(RedBlackEnum redBlackEnum) {
		this.redBlackEnum = redBlackEnum;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
