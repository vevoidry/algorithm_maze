package com.vevoidry.maze.linkedStack;

/*
 * 能从(1,1)到最下边或最右边的任意一个结点即为出口
 */
public class Test {
	public static void main(String[] args) {
		// 传入高度和宽度并生成一个随机迷宫并打印出来
		int row = 5;
		int col = 7;
		int[][] maze = MazeUtil.getMaze(row, col);
		MazeUtil.printMaze(maze);
		/* 如果想要自定义迷宫，只需手动生成一个int[][]对象并调用getPath()即可得到迷宫路径 */
		System.out.println("使用自定义的链栈进行深度优先搜索");
		// 返回随机迷宫的路径
		MazeNodeLinkedStack end = MazeUtil.getPath(maze);
		MazeNode[] array = end.getArray();
		System.out.print("[");
		for (MazeNode mazeNode : array) {
			System.out.print("(" + mazeNode.getRow() + "," + mazeNode.getCol() + "," + mazeNode.getDirection() + ")");
			if (!mazeNode.getDirection().equals("终点")) {
				System.out.print(",");
			}
		}
		System.out.print("]");
	}

}
