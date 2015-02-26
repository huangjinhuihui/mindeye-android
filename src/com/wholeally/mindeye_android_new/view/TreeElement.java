/**
 * 
 */
package com.wholeally.mindeye_android_new.view;

import java.util.ArrayList;

/**
 * @author chenshaorong
 * 
 */
public class TreeElement {
	
	private String upperLevelName;
	/**true有子节点，可以展开*/
	private boolean mhasChild;
	/**控制不同级别节点在界面显示时的缩进量*/
	private int level;
	private ArrayList<TreeElement> childList = new ArrayList<TreeElement>();
	private NodeInfo nodeInfo;
	// private OutlineElement outlineElement;
	/**true节点处于展开状态*/
	private boolean expanded;
	/**通道节点在父级别的childList中的位置*/
	private int positionInChildList;

	public TreeElement() {
	}

	public TreeElement(String upperLevelName, boolean mhasChild, int level, boolean expanded) {
		super();
		this.upperLevelName = upperLevelName;
		this.mhasChild = mhasChild;
		this.level = level;
		this.expanded = expanded;
	}

	public void addChild(TreeElement c) {
		this.childList.add(c);
		this.mhasChild = true;
		c.level = this.level + 1;
	}

	/**true有子节点，可以展开*/
	public boolean isMhasChild() {
		return mhasChild;
	}
	/**true有子节点，可以展开*/
	public void setMhasChild(boolean mhasChild) {
		this.mhasChild = mhasChild;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	/**true节点处于展开状态*/
	public boolean isExpanded() {
		return expanded;
	}
	/**true节点处于展开状态*/
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public ArrayList<TreeElement> getChildList() {
		return childList;
	}

	/**
	 * @return the nodeInfo
	 */
	public NodeInfo getNodeInfo() {
		return nodeInfo;
	}

	/**
	 * @param nodeInfo the nodeInfo to set
	 */
	public void setNodeInfo(NodeInfo nodeInfo) {
		this.nodeInfo = nodeInfo;
	}

	/**
	 * @return the positionInChildList
	 */
	public int getPositionInChildList()
	{
		return positionInChildList;
	}

	/**
	 * @param positionInChildList the positionInChildList to set
	 */
	public void setPositionInChildList(int positionInChildList)
	{
		this.positionInChildList = positionInChildList;
	}

	public String getUpperLevelName()
	{
		return upperLevelName;
	}

	public void setUpperLevelName(String upperLevelName)
	{
		this.upperLevelName = upperLevelName;
	}

	public void setChildList(ArrayList<TreeElement> childList)
	{
		this.childList = childList;
	}

}
