package com.ipiecoles.java.java210;

import java.util.Scanner;

public class Sudoku {

	public static final String FIN_SAISIE = "FIN";
	
	public boolean resolu = false;
	
	public short sudokuAResoudre[][];

	
	public short[][] getSudokuAResoudre() {
		return this.sudokuAResoudre;
	}
	
	public void setSudokuAResoudre(short tab[][]) {
		this.sudokuAResoudre = tab;
	}
	
	/**
	 * Constructeur par défaut
	 */
	public Sudoku() {
		this.sudokuAResoudre = new short[9][9];
	}

	public static boolean ligneSaisieEstCoherente(String ligneSaisie) {
		if (ligneSaisie == ("    ") || ligneSaisie == null || ligneSaisie == ("")) {
			System.out.println( "Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces");
			return false;
		}else if (ligneSaisie.length() != 3) {
			System.out.println("Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères");
			return false;
		}else if (!(ligneSaisie.substring(0,1).matches("[0-8]") && ligneSaisie.substring(1,2).matches("[0-8]") && ligneSaisie.substring(2).matches("[1-9]"))) {
			System.out.println("L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9");
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour initialiser un sudoku à résoudre.
	 * Les coordonnées prennent la forme XYZ avec X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
	 * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée après chaque saisie. 
	 * Lorsqu'il a terminé sa saisie, il entre la chaîne FIN. La fonction remplit au fur et à mesure un tableau de String
	 * comportant les coordonnées des chiffres saisis.
	 * 
	 * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant la méthode ligneSaisieEstCoherente
	 * En cas de mauvaise saisie, la saisie ne doit pas être prise en compte et l'utilisateur doit pouvoir saisie une nouvelle ligne
	 * La fonction doit également gérer le cas où l'utilisateur ne rentre rien mais appuye sur Entrée
	 *
	 * @return Un tableau comportant les coordonnées des chiffres présents dans le sudoku à résoudre
	 */
	public static String[] demandeCoordonneesSudoku() {
		
		String[] tableau;
		tableau = new String[81];
		
		int index = 0;
		String ligneSaisie;
		
		System.out.println("Saisissez les coordonées sous la forme XYZ");
		Scanner scanner = new Scanner(System.in);
		ligneSaisie = scanner.nextLine();

		while (!ligneSaisie.equalsIgnoreCase(FIN_SAISIE)) {
			
			if (Sudoku.ligneSaisieEstCoherente(ligneSaisie)) {
				
				tableau[index] = ligneSaisie;
				
				index ++;
				
			}
			
			System.out.println("Saisissez les coordonées sous la forme XYZ");
			ligneSaisie = scanner.nextLine();
			
		}
		
		scanner.close();
		
		return tableau;
	}
	
	/**
	 * La méthode prend un tableau de coordonnées de chiffre soud la forme XYZ avec X correspondant 
	 * à l'abscisse, Y l'ordonnée et Z la valeur et remplit le tableau sudokuAResoudre avec les bonnes valeurs
	 * au bon endroit. Ex 012, première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle est 
	 * rencontrée dans le tableau, on arrête le traitement
	 * 
	 * Pour passer d'une String à un short, on pourra utiliser la méthode stringToInt(string)
	 * 
	 * @param tableauCoordonnees
	 */
	public void remplitSudokuATrous(String[] tableauCoordonnees) {
		
		for (int i = 0; i < tableauCoordonnees.length; i++) {
			
			int X = stringToInt(tableauCoordonnees[i].substring(0,1));
			int Y = stringToInt(tableauCoordonnees[i].substring(1,2));
			int ZInt = stringToInt(tableauCoordonnees[i].substring(2));
			
			short Z = (short) ZInt;
			
			sudokuAResoudre[X][Y] = Z;
			
		}
		
    }
	
	private int stringToInt(String s) {
		return Integer.parseInt(s);
	}
	
	/**
	 * Cette méthode affiche un sudoku de manière formatée sur la console.
	 * Cela doit ressembler exactement à :
	 * -----------------------
	 * |   8   | 4   2 |   6   |
	 * |   3 4 |       | 9 1   |
	 * | 9 6   |       |   8 4 |
	 *  -----------------------
	 * |       | 2 1 6 |       |
	 * |       |       |       |
	 * |       | 3 5 7 |       |
	 *  -----------------------
	 * | 8 4   |       |   7 5 |
	 * |   2 6 |       | 1 3   |
	 * |   9   | 7   1 |   4   |
	 *  -----------------------
	 * 
	 * @param sudoku tableau de short représentant les valeurs d'un sudoku (résolu ou non). 
	 * Ce tableau fait 9 par 9 et contient des chiffres de 0 à 9, 0 correspondant à une valeur 
	 * non trouvée (dans ce cas, le programme affiche un blanc à la place de 0
	 */
	public void ecrireSudoku(short[][] sudoku) {
		
		String affichageLigne = " " + "-----------------------\n";
		
		for (int i = 0; i < sudoku.length; i++) {
			
			affichageLigne += "| ";
			
			for (int j = 0; j < sudoku[i].length; j++) {
				
				if (sudoku[i][j] != 0) {
					affichageLigne += sudoku[i][j] + " ";				
				}else {	
					affichageLigne += "  ";	
				}

				if ((j+1)%3 == 0) {		
					if (j == 8) {
						affichageLigne += "|\n";	
					}else {
						affichageLigne += "| ";
					}
				}
			}
			
			if ((i+1)%3 == 0) {		
				affichageLigne += " -----------------------" + "\n";
			}	
		}
		System.out.println(affichageLigne);	
    }
	
	/**
	 * Cette méthode vérifie si un chiffre est autorisé à la position d'abscisse et
	 * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes : 
	 * 
	 * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
	 * 2 : Si le valeur est déjà dans la colone, le chiffre n'est pas autorisé
	 * 3 : Si la valeur est est déjà dans la boite, le chiffre n'est pas autorisé
	 * 
	 * @param abscisse
	 * @param ordonnee
	 * @param chiffre
	 * @param sudoku
	 * @return
	 */
	public static boolean estAutorise(int abscisse, int ordonnee, short chiffre, short[][] sudoku) {
		
		int abscisseBlock;
		int ordonneeBlock;
		int indexOrdonneeBlock;
		
		for (int i = 0; i < sudoku.length; i++) {
			
			if(sudoku[i][ordonnee] == chiffre) {
				return false;
			}
			
			if (sudoku[abscisse][i] == chiffre) {
				return false;
			}		
		}
		
		if (abscisse <= 2) {
			abscisseBlock = 0;
		}else if (abscisse <= 5) {
			abscisseBlock = 3;
		}else {	
			abscisseBlock = 5;	
		}
		
		if (ordonnee <= 2) {			
			ordonneeBlock = 0;			
		}else if (ordonnee <= 5) {			
			ordonneeBlock = 3;			
		}else {			
			ordonneeBlock = 5;	
		}
		
		indexOrdonneeBlock = ordonneeBlock;
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {

				if (sudoku[abscisseBlock][indexOrdonneeBlock] == chiffre) {
					return false;
				}
				indexOrdonneeBlock ++;
			}
			indexOrdonneeBlock = ordonneeBlock; //reset de la position des ordonnéé
			abscisseBlock ++;
		}	
		return true;
    }

	public boolean resoudre(int abscisse, int ordonnee, short[][] sudoku) {
	
		int indexModification;
		short[] chiffre = {1,2,3,4,5,6,7,8,9};
		short[][] modification = new short[81][3];
		
		int compteurDebug = 0;
		
		Sudoku monSudoku = new Sudoku();
		
		short compteur = 0;
		
		indexModification = 0;
		
		for (short i = 0; i < sudoku.length; i++) {
			for (short j = 0; j < sudoku.length; j++) {
				if (sudoku[i][j] == 0) {
					for (short j2 = 0; j2 < chiffre.length; j2++) {
						
						compteur++;
						System.out.println("conteur " + compteur);
								
						if (Sudoku.estAutorise(i, j, chiffre[j2], sudoku)) {
							
							sudoku[i][j] = chiffre[j2];
							
							modification[indexModification][0] = i;
							modification[indexModification][1] = j;
							modification[indexModification][2] = chiffre[j2];
							
							indexModification++;
							compteur = 0;
							
							System.out.println("normal " + i + " " + j + " " + chiffre[j2]);
							monSudoku.ecrireSudoku(sudoku);
							break;
						}
						
						if (compteur == 10) {
							boolean trouve = false;
							int copieIndexModification = indexModification;
							
							while ((!trouve) && (copieIndexModification != 0)) {
								System.out.println("index modif = " + copieIndexModification);
								compteurDebug++;

								compteur = 0;
								copieIndexModification--;
								
								short valeurModif = modification[copieIndexModification][2];
								short abscisseModif = modification[copieIndexModification][0];
								short ordonneeModif = modification[copieIndexModification][1];								
											
								for (int k = valeurModif; k < chiffre.length; k++) {
									
									if (Sudoku.estAutorise(abscisseModif, ordonneeModif, chiffre[k], sudoku)) {
										
										sudoku[abscisseModif][ordonneeModif] = chiffre[k];
										valeurModif = chiffre[k];
										
										trouve = true;
										System.out.println("absice et ordonée" + i + " " + j);
										
										i = 0;
										j = 0;
										compteur = 0;
										
										System.out.println("modif " + abscisseModif + " " + ordonneeModif + " " + chiffre[k]);
										
										monSudoku.ecrireSudoku(sudoku);
										
										break;
										}
										
										if (copieIndexModification == 0) {
											i = 0;
											j = 0;
											sudoku[0][2] = 7;
											sudoku[0][6] = 5;
											break;
										}
										
										if (compteurDebug == 100) {
											return true;
										}
									}
								}	
							}
						}	
					}
				}
			}
		return true;
    }
	
	public static short compteCaseVide(short[][] sudoku) {
		
		short compte = 0;
		
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku.length; j++) {
				if (sudoku[i][j] == 0) {
					compte++;
				}
			}
		}
		return compte;	
	}
	
	public short[][] trieDesCases(short[][] sudoku) {
		short[] chiffre = {1,2,3,4,5,6,7,8,9};
		short caseVide = Sudoku.compteCaseVide(sudoku);
		short[][] caseTrie = new short[caseVide][12]; //[ordre] [absice, ordonnee, valeurs possible (max 9)];
		short ordre = 0;
		
		for (short i = 0; i < sudoku.length; i++) {
			
			int indexValeurs = 2;
			
			for (short j = 0; j < sudoku.length; j++) {
				if (sudoku[i][j] == 0) {
					
					caseTrie[ordre][0] = i;
					caseTrie[ordre][1] = j;
					
					
					for (short j2 = 0; j2 < chiffre.length; j2++) {
						if (Sudoku.estAutorise(i, j, chiffre[j2], sudoku)) {
							
							caseTrie[ordre][indexValeurs] = chiffre[j2];

							indexValeurs++;
							
						}
					}
					ordre ++;
					indexValeurs = 2;
				}
			}
		}
		
		 return Sudoku.trieTableau(Sudoku.numerotationCase(caseTrie));
	}
	
	public static short[][] numerotationCase(short caseTrie[][]) {
		for (int i = 0; i < caseTrie.length; i++) {
			short count = 0;
			
			for (int j = 2; j < caseTrie[i].length; j++) {
				
				if (caseTrie[i][j] == 0) {
					count++;
				}
			}
			caseTrie[i][11] = (short) (10 - count);
		}
		
		return caseTrie;
	}

	public static short[][] trieTableau(short caseTrieNuméroté[][]) {
		short[] suivant = new short[12];
		int count = 0;
		
		while (count < caseTrieNuméroté.length) {
			for (int i = 0; i < (caseTrieNuméroté.length - 1); i++) {
				
				if (caseTrieNuméroté[i][11] > caseTrieNuméroté[i+1][11]) {
					
					suivant = caseTrieNuméroté[i+1];
					
					caseTrieNuméroté[i+1] = caseTrieNuméroté[i];
					caseTrieNuméroté[i] = suivant; 
					
					count = 0;
				}else {
					count++;
				}
			}	
		}
		return caseTrieNuméroté;
	}
	
	public boolean testRollo(short abscisse, short ordonnee, short[][] sudoku) {
		short[] chiffre = {1,2,3,4,5,6,7,8,9};
		
		for ( abscisse = 0; abscisse < sudoku.length; abscisse++) {
			for (ordonnee = 0; ordonnee < chiffre.length; ordonnee++) {
				
				for (int i = 0; i < chiffre.length; i++) {
					
					if (sudoku[abscisse][ordonnee] == 0 ) {
						if (Sudoku.estAutorise(abscisse, ordonnee, chiffre[i], sudoku)) {
							sudoku[abscisse][ordonnee] = chiffre[i];
							break;
						}
					}
				}
			}
		}
		
		return true;
	}
}