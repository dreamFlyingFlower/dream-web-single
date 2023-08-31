package com.dream.framework.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wy.lang.AssertHelper;

import dream.framework.web.entity.node.AbstractNode;

/**
 * 树形结构工具类
 *
 * @author 飞花梦影
 * @date 2023-08-08 17:54:23
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class TreeHelper {

	/**
	 * 根据pid,构建树节点
	 */
	public static <T extends AbstractNode<T, Long>> List<T> build(List<T> treeNodes, Long pid) {
		AssertHelper.isNull(pid, "pid");
		List<T> treeList = new ArrayList<>();
		for (T treeNode : treeNodes) {
			if (pid.equals(treeNode.getPid())) {
				treeList.add(findChildren(treeNodes, treeNode));
			}
		}
		return treeList;
	}

	/**
	 * 查找子节点
	 */
	private static <T extends AbstractNode<T, Long>> T findChildren(List<T> treeNodes, T rootNode) {
		for (T treeNode : treeNodes) {
			if (rootNode.getId().equals(treeNode.getPid())) {
				rootNode.getChildren().add(findChildren(treeNodes, treeNode));
			}
		}
		return rootNode;
	}

	/**
	 * 构建树节点
	 */
	public static <T extends AbstractNode<T, Long>> List<T> build(List<T> treeNodes) {
		List<T> result = new ArrayList<>();

		// list转map
		Map<Long, T> nodeMap = new LinkedHashMap<>(treeNodes.size());
		for (T treeNode : treeNodes) {
			nodeMap.put(treeNode.getId(), treeNode);
		}

		for (T node : nodeMap.values()) {
			T parent = nodeMap.get(node.getPid());
			if (parent != null && !(node.getId().equals(parent.getId()))) {
				parent.getChildren().add(node);
				continue;
			}
			result.add(node);
		}
		return result;
	}
}