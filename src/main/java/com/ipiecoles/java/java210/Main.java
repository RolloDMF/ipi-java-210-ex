package com.ipiecoles.java.java210;

import java.awt.print.Printable;

public class Main {
	
	static short[][] tab = {
		    {0, 8, 0, 4, 0, 2, 0, 6, 0},
		    {0, 3, 4, 0, 0, 0, 9, 1, 0},
		    {9, 6, 0, 0, 0, 0, 0, 8, 4},
		    {0, 0, 0, 2, 1, 6, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 3, 5, 7, 0, 0, 0},
		    {8, 4, 0, 0, 0, 0, 0, 7, 5},
		    {0, 2, 6, 0, 0, 0, 1, 3, 0},
		    {0, 9, 0, 7, 0, 1, 0, 4, 0},
		};
	
	 public static void main(String[] args) {
		 	Sudoku sudoku = new Sudoku();
		 	short[][] caseTrie;
		 	caseTrie = sudoku.trieDesCases(tab);
		 	
		 	for (int i = 0; i < caseTrie.length; i++) {
				System.out.println("ordre " + caseTrie[i]);
				for (int j = 0; j < caseTrie[i].length; j++) {
					System.out.println("rest " + caseTrie[i][j]);
				}
			}
		 }
	 
}
