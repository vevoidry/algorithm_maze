package com.vevoidry.maze.linkedList;

import java.util.Arrays;
import java.util.LinkedList;

/*
 * 用于生成随机迷宫
 */
public class MazeUtil {
	// 传入行和列，获取一个迷宫
	public static int[][] getMaze(int row, int col) {
		int[][] maze = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				maze[i][j] = (int) getRandom();
			}
		}
		maze[0][0] = 0;
		return maze;
	}

	// 获取0或1的随机数，0是路，1是墙
	public static long getRandom() {
		// param可以用来控制生成0或1的概率，以便控制随机迷宫的可走性概率
		double param = 0.3;
		return Math.round(Math.random() - param);
	}

	// 打印迷宫
	public static void printMaze(int[][] maze) {
		int row = maze.length;
		for (int i = 0; i < row; i++) {
			System.out.println(Arrays.toString(maze[i]));
		}
	}

	public static LinkedList<MazeNode> getPath(int[][] maze) {
		// 将迷宫转化为结点的二维数组模式
		MazeNode[][] mazeNodeArr = transMaze(maze);
		// 为所有结点配置基本信息
		setInformation(mazeNodeArr);
		// 开始回溯法
		LinkedList<MazeNode> end = calculate(mazeNodeArr);
		return end;
	}

	// 将迷宫从基本数据类型二维数组转换为结点类型二维数组
	public static MazeNode[][] transMaze(int[][] maze) {
		int row = maze.length;
		int col = maze[0].length;
		MazeNode[][] mazeNodeArr = new MazeNode[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				MazeNode mazeNode = new MazeNode();
				mazeNode.setRow(i);
				mazeNode.setCol(j);
				mazeNode.setValue(maze[i][j]);
				mazeNodeArr[i][j] = mazeNode;
			}
		}
		return mazeNodeArr;
	}

	// 计算单个MazeNode的上下左右以及出口信息
	public static void setInformation(MazeNode[][] mazeNodeArr) {
		int arrRow = mazeNodeArr.length;
		int arrCol = mazeNodeArr[0].length;
		for (int i = 0; i < arrRow; i++) {
			for (int j = 0; j < arrCol; j++) {
				MazeNode mazeNode = mazeNodeArr[i][j];
				if (mazeNode.getValue() == 0) {
					Integer row = mazeNode.getRow();
					Integer col = mazeNode.getCol();
					// 配置上方是否可通行
					if (row == 0) {
						mazeNode.setUp(false);
					} else if (mazeNodeArr[row - 1][col].getValue() != 1) {
						mazeNode.setUp(true);
					}
					// 配置右方是否可通行，且是否终点
					if (col == arrCol - 1) {
						mazeNode.setRight(false);
						mazeNode.setEnd(true);
					} else if (mazeNodeArr[row][col + 1].getValue() != 1) {
						mazeNode.setRight(true);
					}
					// 配置下方是否可通行，且是否终点
					if (row == arrRow - 1) {
						mazeNode.setDown(false);
						mazeNode.setEnd(true);
					} else if (mazeNodeArr[row + 1][col].getValue() != 1) {
						mazeNode.setDown(true);
					}
					// 配置左方是否可通行
					if (col == 0) {
						mazeNode.setLeft(false);
					} else if (mazeNodeArr[row][col - 1].getValue() != 1) {
						mazeNode.setLeft(true);
					}
				} else {
					mazeNode.setUp(false);
					mazeNode.setRight(false);
					mazeNode.setDown(false);
					mazeNode.setLeft(false);
					mazeNode.setEnd(false);
				}
			}
		}
	}

	public static LinkedList<MazeNode> calculate(MazeNode[][] mazeNodeArr) {
		System.out.println("开始计算迷宫路径");
		int arrRow = mazeNodeArr.length;
		int arrCol = mazeNodeArr[0].length;
		LinkedList<MazeNode> stack = new LinkedList<MazeNode>();
		if (mazeNodeArr[0][0].getValue() == 1) {
			throw new RuntimeException("入口为1，无法进入迷宫");
		} else {
			stack.add(mazeNodeArr[0][0]);
			while (!stack.isEmpty()) {
				// MazeNode last = stack.getLast();
				MazeNode last = stack.getLast();
				Integer row = last.getRow();
				Integer col = last.getCol();
				if (last.getEnd() == true) {// row == arrRow - 1 || col == arrCol - 1
					System.out.println("成功找到路径");
					last.setDirection("终点");
					return stack;
				} else {
					if (last.getUp()) {
						last.setUp(false);
						last.setDirection("向上");
						MazeNode brother = mazeNodeArr[row - 1][col];
						brother.setDown(false);
						stack.add(brother);
						continue;
					}
					if (last.getRight()) {
						last.setRight(false);
						last.setDirection("向右");
						MazeNode brother = mazeNodeArr[row][col + 1];
						brother.setLeft(false);
						stack.add(brother);
						continue;
					}
					if (last.getDown()) {
						last.setDown(false);
						last.setDirection("向下");
						MazeNode brother = mazeNodeArr[row + 1][col];
						brother.setUp(false);
						stack.add(brother);
						continue;
					}
					if (last.getLeft()) {
						last.setLeft(false);
						last.setDirection("向左");
						MazeNode brother = mazeNodeArr[row][col - 1];
						brother.setRight(false);
						stack.add(brother);
						continue;
					}
					stack.removeLast();
				}
			}
			throw new RuntimeException("该迷宫没有出口");
		}
	}
}
