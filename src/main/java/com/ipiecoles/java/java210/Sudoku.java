package com.ipiecoles.java.java210;

import java.net.PasswordAuthentication;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.LeafElement;

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
			System.out.println(
					"Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces");
			return false;
		} else if (ligneSaisie.length() != 3) {
			System.out.println("Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères");
			return false;
		} else if (!(ligneSaisie.substring(0, 1).matches("[0-8]") && ligneSaisie.substring(1, 2).matches("[0-8]")
				&& ligneSaisie.substring(2).matches("[1-9]"))) {
			System.out.println("L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour
	 * initialiser un sudoku à résoudre. Les coordonnées prennent la forme XYZ avec
	 * X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
	 * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée
	 * après chaque saisie. Lorsqu'il a terminé sa saisie, il entre la chaîne FIN.
	 * La fonction remplit au fur et à mesure un tableau de String comportant les
	 * coordonnées des chiffres saisis.
	 * 
	 * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant
	 * la méthode ligneSaisieEstCoherente En cas de mauvaise saisie, la saisie ne
	 * doit pas être prise en compte et l'utilisateur doit pouvoir saisie une
	 * nouvelle ligne La fonction doit également gérer le cas où l'utilisateur ne
	 * rentre rien mais appuye sur Entrée
	 *
	 * @return Un tableau comportant les coordonnées des chiffres présents dans le
	 *         sudoku à résoudre
	 */
	public static String[] demandeCoordonneesSudoku() {

		String[] tableau;
		tableau = new String[81];

		int index = 0;
		String ligneSaisie;

		System.out.println("Saisissez les coordonées sous la forme XYZ");
		Scanner scanner = new Scanner(System.in);
		ligneSaisie = scanner.nextLine();

		while (!ligneSaisie.equalsIgnoreCase(FIN_SAISIE) && tableau[80] == null) {

			if (Sudoku.ligneSaisieEstCoherente(ligneSaisie)) {
				
				for (int i = 0; i < tableau.length; i++) {
					
					if (tableau[i] != null) {
						if (Integer.parseInt(ligneSaisie.substring(0, 2)) == Integer.parseInt(tableau[i].substring(0, 2))) {
							System.out.println("Cette case est deja remplie !");
							continue;
						}
					}
				}
				
				tableau[index] = ligneSaisie;

				index++;

			}

			System.out.println("Saisissez les coordonées sous la forme XYZ");
			ligneSaisie = scanner.nextLine();

		}

		scanner.close();

		return tableau;
	}

	/**
	 * La méthode prend un tableau de coordonnées de chiffre soud la forme XYZ avec
	 * X correspondant à l'abscisse, Y l'ordonnée et Z la valeur et remplit le
	 * tableau sudokuAResoudre avec les bonnes valeurs au bon endroit. Ex 012,
	 * première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle
	 * est rencontrée dans le tableau, on arrête le traitement
	 * 
	 * Pour passer d'une String à un short, on pourra utiliser la méthode
	 * stringToInt(string)
	 * 
	 * @param tableauCoordonnees
	 */
	public void remplitSudokuATrous(String[] tableauCoordonnees) {

		for (int i = 0; i < tableauCoordonnees.length; i++) {

			int X = stringToInt(tableauCoordonnees[i].substring(0, 1));
			int Y = stringToInt(tableauCoordonnees[i].substring(1, 2));
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
				} else {
					affichageLigne += "  ";
				}

				if ((j + 1) % 3 == 0) {
					if (j == 8) {
						affichageLigne += "|\n";
					} else {
						affichageLigne += "| ";
					}
				}
			}

			if ((i + 1) % 3 == 0) {
				affichageLigne += " -----------------------" + "\n";
			}
		}
		System.out.println(affichageLigne);
	}

	public static boolean validationSudoku(short[][] sudoku) {
		
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku.length; j++) {
				if (sudoku[i][j] != 0) {
					if (!Sudoku.estAutoriseSudoku(i, j, sudoku[i][j], sudoku)) {
						System.out.println("le sodoku est invalide");
						return false;
					}
				}
			}
		}
		System.out.println("le sudoku est valide");
		return true;
	}
	/**
	 * Cette méthode vérifie si un chiffre est autorisé à la position d'abscisse et
	 * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes :
	 * 
	 * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé 2 : Si
	 * le valeur est déjà dans la colone, le chiffre n'est pas autorisé 3 : Si la
	 * valeur est est déjà dans la boite, le chiffre n'est pas autorisé
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

			if (sudoku[i][ordonnee] == chiffre) {
				return false;
			}

			if (sudoku[abscisse][i] == chiffre) {
				return false;
			}
		}

		if (abscisse <= 2) {
			abscisseBlock = 0;
		} else if (abscisse <= 5) {
			abscisseBlock = 3;
		} else {
			abscisseBlock = 6;
		}

		if (ordonnee <= 2) {
			ordonneeBlock = 0;
		} else if (ordonnee <= 5) {
			ordonneeBlock = 3;
		} else {
			ordonneeBlock = 6;
		}

		indexOrdonneeBlock = ordonneeBlock;

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				if (sudoku[abscisseBlock][indexOrdonneeBlock] == chiffre) {
					return false;
				}
				indexOrdonneeBlock++;
			}
			indexOrdonneeBlock = ordonneeBlock; // reset de la position des ordonnéé
			abscisseBlock++;
		}
		return true;
	}

	public static boolean estAutoriseSudoku(int abscisse, int ordonnee, short chiffre, short[][] sudoku) {

		int abscisseBlock;
		int ordonneeBlock;
		int indexOrdonneeBlock;
		
		int compteurLigne = 0;
		int compteurColone = 0;
		int compteurBlock = 0;

		for (int i = 0; i < sudoku.length; i++) {
			
			if (sudoku[i][ordonnee] == chiffre) {
				compteurLigne++;
			}

			if (sudoku[abscisse][i] == chiffre) {
				compteurColone++;
			}
			
			if (compteurColone >= 2 || compteurLigne >= 2) {
				return false;
			}
		}

		if (abscisse <= 2) {
			abscisseBlock = 0;
		} else if (abscisse <= 5) {
			abscisseBlock = 3;
		} else {
			abscisseBlock = 6;
		}

		if (ordonnee <= 2) {
			ordonneeBlock = 0;
		} else if (ordonnee <= 5) {
			ordonneeBlock = 3;
		} else {
			ordonneeBlock = 6;
		}

		indexOrdonneeBlock = ordonneeBlock;

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {
					
				if (sudoku[abscisseBlock][indexOrdonneeBlock] == chiffre) {
					compteurBlock++;
				}
				indexOrdonneeBlock++;
				
			}
			indexOrdonneeBlock = ordonneeBlock; // reset de la position des ordonnéé
			abscisseBlock++;
		}
		
		if (compteurBlock >= 2) {
			return false;
		}
		return true;
	}

	public boolean resoudre(int abscisse, int ordonnee, short[][] sudoku) {
		short[][] caseTrie;
		caseTrie = Sudoku.trieDesCases(sudoku);

		short[][] modification = new short[caseTrie.length][3];

		short compteur = 0;

		for (short i = 0; i < caseTrie.length; i++) {

			short absci = caseTrie[i][0];
			short ordo = caseTrie[i][1];
			short nombrePosibilite = caseTrie[i][11];

			for (short j = 2; j < (nombrePosibilite + 2); j++) {

				compteur++;

				if (Sudoku.estAutorise(absci, ordo, caseTrie[i][j], sudoku)) {

					sudoku[absci][ordo] = caseTrie[i][j];

					modification[i][0] = absci;
					modification[i][1] = ordo;
					modification[i][2] = j;

					compteur = 0;
					break;

				}

				if (compteur >= nombrePosibilite) {
					
					boolean trouve = false;
					int copieIndexModification = i;

					while ((!trouve) && copieIndexModification != 0) {

						copieIndexModification--;

						short valeurModif = modification[copieIndexModification][2];
						short abscisseModif = modification[copieIndexModification][0];
						short ordonneeModif = modification[copieIndexModification][1];
						short nombrePossible = caseTrie[copieIndexModification][11];
						short[] tableauPossible = caseTrie[copieIndexModification];


						for (short k = valeurModif; k < (nombrePossible + 2); k++) {

							if (Sudoku.estAutorise(abscisseModif, ordonneeModif, tableauPossible[k], sudoku)) {

								sudoku[abscisseModif][ordonneeModif] = tableauPossible[k];
								modification[copieIndexModification][2] = k;

								trouve = true;
								compteur = 0;
								i = (short) copieIndexModification;

								break;

							} else if (k > tableauPossible[11]) {
								
								sudoku[abscisseModif][ordonneeModif] = 0;
								modification[copieIndexModification][2] = 2;
								
								if (copieIndexModification == 0) {
									compteur = 0;
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

	public static short[][] trieDesCases(short[][] sudoku) {
		short[] chiffre = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		short caseVide = Sudoku.compteCaseVide(sudoku);
		short[][] caseTrie = new short[caseVide][13]; // [ordre] [absice, ordonnee, valeurs possible (max 9), nuero de
														// trie, variable d'echeque];
		short ordre = 0;

		for (short i = 0; i < sudoku.length; i++) {

			for (short j = 0; j < sudoku.length; j++) {
				if (sudoku[i][j] == 0) {

					int indexValeurs = 2;
					caseTrie[ordre][0] = i;
					caseTrie[ordre][1] = j;
					caseTrie[ordre][12] = 0;

					for (short j2 = 0; j2 < chiffre.length; j2++) {
						if (Sudoku.estAutorise(i, j, chiffre[j2], sudoku)) {

							caseTrie[ordre][indexValeurs] = chiffre[j2];

							indexValeurs++;

						}
					}
					ordre++;
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
			caseTrie[i][11] = (short) (11 - count);
		}

		return caseTrie;
	}

	public static short[][] trieTableau(short caseTrieNuméroté[][]) {
		
		short[] suivant = new short[12];
		int count = 0;

		while (count < caseTrieNuméroté.length) {
			for (int i = 0; i < (caseTrieNuméroté.length - 1); i++) {

				if (caseTrieNuméroté[i][11] > caseTrieNuméroté[i + 1][11]) {

					suivant = caseTrieNuméroté[i + 1];

					caseTrieNuméroté[i + 1] = caseTrieNuméroté[i];
					caseTrieNuméroté[i] = suivant;

					count = 0;
				} else {
					count++;
				}
			}
		}
		return caseTrieNuméroté;
	}
	
}
