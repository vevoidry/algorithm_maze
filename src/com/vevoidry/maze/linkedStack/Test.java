package com.vevoidry.maze.linkedStack;

/*
 * �ܴ�(1,1)�����±߻����ұߵ�����һ����㼴Ϊ����
 */
public class Test {
	public static void main(String[] args) {
		// ����߶ȺͿ�Ȳ�����һ������Թ�����ӡ����
		int row = 5;
		int col = 7;
		int[][] maze = MazeUtil.getMaze(row, col);
		MazeUtil.printMaze(maze);
		/* �����Ҫ�Զ����Թ���ֻ���ֶ�����һ��int[][]���󲢵���getPath()���ɵõ��Թ�·�� */
		System.out.println("ʹ���Զ������ջ���������������");
		// ��������Թ���·��
		MazeNodeLinkedStack end = MazeUtil.getPath(maze);
		MazeNode[] array = end.getArray();
		System.out.print("[");
		for (MazeNode mazeNode : array) {
			System.out.print("(" + mazeNode.getRow() + "," + mazeNode.getCol() + "," + mazeNode.getDirection() + ")");
			if (!mazeNode.getDirection().equals("�յ�")) {
				System.out.print(",");
			}
		}
		System.out.print("]");
	}

}
