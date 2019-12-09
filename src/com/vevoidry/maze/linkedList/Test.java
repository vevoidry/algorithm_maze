package com.vevoidry.maze.linkedList;

import java.util.Iterator;
import java.util.LinkedList;

/*
 * 能从(1,1)到最下边或最右边的任意一个结点即为出口
 */
public class Test {
	public static void main(String[] args) {
		// 传入高度和宽度并生成一个随机迷宫并打印出来
		int row = 5;
		int col = 5;
		int[][] maze = MazeUtil.getMaze(row, col);
		MazeUtil.printMaze(maze);
		/* 如果想要自定义迷宫，只需手动生成一个int[][]对象并调用getPath()即可得到迷宫路径 */
		System.out.println("使用LinkedList进行深度优先搜索");
		// 返回随机迷宫的路径
		LinkedList<MazeNode> end = MazeUtil.getPath(maze);
		Iterator<MazeNode> iterator = end.iterator();
		while (iterator.hasNext()) {
			MazeNode next = iterator.next();
			// 打印迷宫路径
			System.out.println("(" + next.getRow() + "," + next.getCol() + "," + next.getDirection() + ")");
		}
	}

}
