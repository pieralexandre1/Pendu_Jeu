
// Auteure : Pier-Alexandre Yale et Soti
// Fichier : MainPendu.java
// Date    : 8 Mars 2016
// Cours   : 420-146-MO (TP1, Jeu du Pendu)

import java.io.*;

import outilsjava.*;

/*
 * La classe MainPendu contient la méthode principale du TP1 qui permet
 * de jouer au petit jeu du pendu.
 */

public class MainPendu {

	public static void main(String[] args) {
		boolean peutContinuer = true;
		String nomFicTest;
		// On suppose une lecture des données du clavier.
		BufferedReader fic = new BufferedReader(new InputStreamReader(System.in));

		char type = OutilsLecture.lireCaractereDisparate(
				"\nEntrez le type " + "de test du programme " + "(C avec clavier, F avec Fichier) : ", "CF");
		if (type == OutilsLecture.LECTURE_FICHIER) {
			nomFicTest = OutilsFichier
					.lireNomFichier("\nEntrez le nom " + "du fichier qui contient les jeux d'essai : ");
			fic = OutilsFichier.ouvrirFicTexteLecture(nomFicTest);
			if (fic == null) {
				peutContinuer = false;
			}
		}
		if (peutContinuer) {
			// Lire du clavier ou d'un fichier.
			OutilsLecture.fic = fic;
			OutilsLecture.type = type;
			/*
			 * Une instance de la classe GererJeuPendu permettra de jouer au
			 * petit jeu du pendu.
			 */
			new GererJeuPendu();
		} else {
			System.out.println("\nImpossible de tester le programme.");
		}
	}
}