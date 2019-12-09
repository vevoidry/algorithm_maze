package com.vevoidry.maze.linkedList;

import java.util.Arrays;
import java.util.LinkedList;

/*
 * ������������Թ�
 */
public class MazeUtil {
	// �����к��У���ȡһ���Թ�
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

	// ��ȡ0��1���������0��·��1��ǽ
	public static long getRandom() {
		// param����������������0��1�ĸ��ʣ��Ա��������Թ��Ŀ����Ը���
		double param = 0.3;
		return Math.round(Math.random() - param);
	}

	// ��ӡ�Թ�
	public static void printMaze(int[][] maze) {
		int row = maze.length;
		for (int i = 0; i < row; i++) {
			System.out.println(Arrays.toString(maze[i]));
		}
	}

	public static LinkedList<MazeNode> getPath(int[][] maze) {
		// ���Թ�ת��Ϊ���Ķ�ά����ģʽ
		MazeNode[][] mazeNodeArr = transMaze(maze);
		// Ϊ���н�����û�����Ϣ
		setInformation(mazeNodeArr);
		// ��ʼ���ݷ�
		LinkedList<MazeNode> end = calculate(mazeNodeArr);
		return end;
	}

	// ���Թ��ӻ����������Ͷ�ά����ת��Ϊ������Ͷ�ά����
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

	// ���㵥��MazeNode�����������Լ�������Ϣ
	public static void setInformation(MazeNode[][] mazeNodeArr) {
		int arrRow = mazeNodeArr.length;
		int arrCol = mazeNodeArr[0].length;
		for (int i = 0; i < arrRow; i++) {
			for (int j = 0; j < arrCol; j++) {
				MazeNode mazeNode = mazeNodeArr[i][j];
				if (mazeNode.getValue() == 0) {
					Integer row = mazeNode.getRow();
					Integer col = mazeNode.getCol();
					// �����Ϸ��Ƿ��ͨ��
					if (row == 0) {
						mazeNode.setUp(false);
					} else if (mazeNodeArr[row - 1][col].getValue() != 1) {
						mazeNode.setUp(true);
					}
					// �����ҷ��Ƿ��ͨ�У����Ƿ��յ�
					if (col == arrCol - 1) {
						mazeNode.setRight(false);
						mazeNode.setEnd(true);
					} else if (mazeNodeArr[row][col + 1].getValue() != 1) {
						mazeNode.setRight(true);
					}
					// �����·��Ƿ��ͨ�У����Ƿ��յ�
					if (row == arrRow - 1) {
						mazeNode.setDown(false);
						mazeNode.setEnd(true);
					} else if (mazeNodeArr[row + 1][col].getValue() != 1) {
						mazeNode.setDown(true);
					}
					// �������Ƿ��ͨ��
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
		System.out.println("��ʼ�����Թ�·��");
		int arrRow = mazeNodeArr.length;
		int arrCol = mazeNodeArr[0].length;
		LinkedList<MazeNode> stack = new LinkedList<MazeNode>();
		if (mazeNodeArr[0][0].getValue() == 1) {
			throw new RuntimeException("���Ϊ1���޷������Թ�");
		} else {
			stack.add(mazeNodeArr[0][0]);
			while (!stack.isEmpty()) {
				// MazeNode last = stack.getLast();
				MazeNode last = stack.getLast();
				Integer row = last.getRow();
				Integer col = last.getCol();
				if (last.getEnd() == true) {// row == arrRow - 1 || col == arrCol - 1
					System.out.println("�ɹ��ҵ�·��");
					last.setDirection("�յ�");
					return stack;
				} else {
					if (last.getUp()) {
						last.setUp(false);
						last.setDirection("����");
						MazeNode brother = mazeNodeArr[row - 1][col];
						brother.setDown(false);
						stack.add(brother);
						continue;
					}
					if (last.getRight()) {
						last.setRight(false);
						last.setDirection("����");
						MazeNode brother = mazeNodeArr[row][col + 1];
						brother.setLeft(false);
						stack.add(brother);
						continue;
					}
					if (last.getDown()) {
						last.setDown(false);
						last.setDirection("����");
						MazeNode brother = mazeNodeArr[row + 1][col];
						brother.setUp(false);
						stack.add(brother);
						continue;
					}
					if (last.getLeft()) {
						last.setLeft(false);
						last.setDirection("����");
						MazeNode brother = mazeNodeArr[row][col - 1];
						brother.setRight(false);
						stack.add(brother);
						continue;
					}
					stack.removeLast();
				}
			}
			throw new RuntimeException("���Թ�û�г���");
		}
	}
}
