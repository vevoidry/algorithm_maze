package com.vevoidry.maze.stack;

import java.util.Iterator;
import java.util.Stack;

/*
 * �ܴ�(1,1)�����±߻����ұߵ�����һ����㼴Ϊ����
 */
public class Test {
	public static void main(String[] args) {
		// ����߶ȺͿ�Ȳ�����һ������Թ�����ӡ����
		int row = 5;
		int col = 5;
		int[][] maze = MazeUtil.getMaze(row, col);
		MazeUtil.printMaze(maze);
		/* �����Ҫ�Զ����Թ���ֻ���ֶ�����һ��int[][]���󲢵���getPath()���ɵõ��Թ�·�� */
		System.out.println("ʹ��stack���������������");
		// ��������Թ���·��
		Stack<MazeNode> end = MazeUtil.getPath(maze);
		Iterator<MazeNode> iterator = end.iterator();
		while (iterator.hasNext()) {
			MazeNode next = iterator.next();
			// ��ӡ�Թ�·��
			System.out.println("(" + next.getRow() + "," + next.getCol() + "," + next.getDirection() + ")");
		}
	}

}
