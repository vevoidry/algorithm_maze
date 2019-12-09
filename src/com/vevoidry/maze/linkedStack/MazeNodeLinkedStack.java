package com.vevoidry.maze.linkedStack;

import java.util.Arrays;
import java.util.Collections;

public class MazeNodeLinkedStack {
	MazeNode[] stack;
	// 链表总长度
	private int length;
	// 当前所拥有的数量
	private int size = 0;

	public MazeNodeLinkedStack() {
		length = 3;
		stack = new MazeNode[length];
	}

	public synchronized MazeNode push(MazeNode item) {
		if (size < length) {
			stack[size] = item;
		} else {
			length = length * 2;
			MazeNode[] stack2 = new MazeNode[length];
			Collections.copy(Arrays.asList(stack2), Arrays.asList(stack));
			stack = stack2;
			stack[size] = item;
		}
		size++;
		return item;
	}

	public synchronized MazeNode pop() {
		if (size > 0) {
			MazeNode item = stack[size - 1];
			size--;
			return item;
		} else {
			throw new RuntimeException("size为0");
		}
	}

	public synchronized MazeNode lastElement() {
		if (size > 0) {
			return stack[size - 1];
		} else {
			throw new RuntimeException("size为0");
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public synchronized MazeNode[] getArray() {
		MazeNode[] stack2 = new MazeNode[size];
		for (int i = 0; i < size; i++) {
			stack2[i] = stack[i];
		}
		return stack2;
	}

}
