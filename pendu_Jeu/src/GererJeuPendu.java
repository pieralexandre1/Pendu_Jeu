
// Auteurs : Pier-Alexandre Yale et Soti
// Fichier : GererJeuPendu.java
// Date    : 8 Mars 2016
// Cours   : 420-146-MO (TP1, Jeu du Pendu)

import com.sun.org.apache.xerces.internal.impl.io.ASCIIReader;

import com.sun.xml.internal.ws.util.ASCIIUtility;

// Package du programmeur.
import outilsjava.*;

/**
 * La classe GererJeuPendu permet de jouer au petit jeu du pendu.
 */

public class GererJeuPendu {

	// Constantes de la classe.
	private static final int MAX_ERREURS = 7;
	private static final int ASCII_A = 65; // Code ascii de la lettre A.
	private static final char TIRET = '_';

	/**
	 * Constructeur qui permet de jouer au petit jeu du pendu.
	 */

	public GererJeuPendu() {
		// Constantes du constructeur.
		final String _BIENVENUE = "\nBienvenue au programme qui " + "permet de jouer au petit jeu du pendu.";
		final String _INFO1 = "\nIl s'agit de trouver les lettres " + "qui doivent remplacer les tirets.";
		final String _INFO2 = "Vous avez droit de vous tromper " + MAX_ERREURS + " fois, pas plus.\n";
		final String QUEST_AUTRE_JEU = "\nVoulez-vous jouer encore (O ou N) : ";
		final String _FIN_MOTS = "\nIl n'y a plus de mots � deviner.";
		final String _FIN = "\nMerci d'avoir jou� au petit jeu du pendu." + "\n\nFin du programme.";

		// Tableau des mots � trouver. Les mots doivent �tre en majuscules.
		final String[] TAB_MOTS = { "PAIX", "TERRE", "LAVAL", "POSSIBLE" };
		int indicemot = 0;
		char jouer = 'O';
		System.out.println(_BIENVENUE);
		do {
			System.out.println(_INFO1);
			System.out.println(_INFO2);
			devinerMot(TAB_MOTS[indicemot]);
			indicemot++;
			if (indicemot != 4) {
				jouer = OutilsLecture.lireOuiNon(QUEST_AUTRE_JEU);
			} else {
				System.out.println(_FIN_MOTS);
				jouer = 'N';
			}
		} while (jouer == 'O');
		System.out.println(_FIN);

	}

	/**
	 * M�thode qui permet de faire deviner un mot par l'utilisateur. La m�thode
	 * re�oit le mot � deviner.
	 */

	private void devinerMot(String mot) {
		// Constantes de la m�thode.
		final int MAX_LETTRES = 26;
		final String TITRE_MOT = "\nMot � deviner :";
		final String TITRE_LETTRES = "\nListe des lettres possibles :";
		final String QUEST_LETTRE = "\nEntrez une lettre : ";
		final String _MAX_ERREURS = "\nC'est termin�. Vous avez atteint" + " le nombre maximum d'erreurs possibles.";
		final String _ENCOURAGEMENT = "\nBonne chance pour la " + "prochaine fois !";

		char tabCarMot[] = mot.toCharArray();
		char tabTirets[] = new char[mot.length()];
		int nberreur = 0;
		int nbessais = 0;
		boolean terminer = false;
		boolean lettreChoisie[] = new boolean[MAX_LETTRES];
		initialiserTab(tabTirets, TIRET);
		do {
			afficherPendu(nberreur);
			nbessais++;
			System.out.println("�ssai # " + nbessais);
			afficherTab(TITRE_MOT, tabTirets);
			System.out.println("Il vous reste " + (MAX_ERREURS - nberreur) + " erreur(s) possible(s)");
			String lettresPossibles = fabriquerLettresPossibles(lettreChoisie);
			char tabCarLettresPossibles[] = lettresPossibles.toCharArray();
			afficherTab(TITRE_LETTRES, tabCarLettresPossibles);
			char lettre = OutilsLecture.lireCaractereDisparate(QUEST_LETTRE, lettresPossibles);
			lettreChoisie[(int) (lettre - ASCII_A)] = true;
			boolean lettrePresente = chercherLettre(tabCarMot, tabTirets, lettre);
			
			if (!lettrePresente) {
				System.out.println("D�sol�, la lettre " + lettre + " ne fait pas partie du mot.");
				nberreur++;
				if (nberreur == MAX_ERREURS) {
					afficherPendu(MAX_ERREURS);
					System.out.println(_MAX_ERREURS);
					System.out.println("Le mot � deviner �tait : " + mot);
					System.out.println(_ENCOURAGEMENT);
					terminer = true;
				}
			} else {
				int nbLettresTrouvees = compterLettresTrouvees(tabTirets);

				if (nbLettresTrouvees == mot.length()) {
					System.out.println("Bravo ! vous avez devin� le mot " + mot + " en " + nbessais + " essais");
					terminer = true;
				}
			}
		} while (!terminer);

	}

	/**
	 * M�thode qui permet d'initialiser chaque case d'un tableau de caract�res
	 * par un caract�re de d�part. La m�thode re�oit en param�tres un tableau de
	 * caract�res et un caract�re de d�part.
	 */

	private void initialiserTab(char[] tab, char carDepart) {
		for (int i = 0; i < tab.length; i++) {
			tab[i] = carDepart;
		}
	}

	/**
	 * M�thode qui permet d'afficher un titre et les caract�res d'un tableau
	 * avec un espace entre chaque caract�re. La m�thode re�oit en param�tres,
	 * un titre et un tableau de caract�res.
	 */

	private void afficherTab(String titre, char[] tab) {
		System.out.println(titre);
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i] + " ");
		}
		System.out.println("\n");
	}

	/**
	 * M�thode qui permet de fabriquer la cha�ne de caract�res contenant les
	 * lettres possibles que l'utilisateur pourra choisir. La m�thode re�oit en
	 * param�tre le tableau des lettres choisies et retourne la cha�ne de
	 * caract�res fabriqu�e.
	 */

	private String fabriquerLettresPossibles(boolean[] lettreChoisie) {
		String lettresPossibles = "";
		int charactere = 0;
		char lettre = '\0';
		for (int i = 0; i < lettreChoisie.length; i++) {
			if (!lettreChoisie[i]) {
				charactere = i + ASCII_A;
				lettre = (char) charactere;
				lettresPossibles += lettre;
			}
		}
		return lettresPossibles;
	}

	/**
	 * M�thode qui cherche une lettre dans le tableau du mot � deviner. � chaque
	 * fois que la lettre est trouv�e, le tiret �quivalent du tableau des tirets
	 * est remplac� par la lettre. La m�thode re�oit en param�tres le tableau du
	 * mot, le tableau des tirets et la lettre. La m�thode retourne true si la
	 * lettre est pr�sente et false dans le cas contraire.
	 */

	private boolean chercherLettre(char[] tabCarMot, char[] tabTirets, char lettre) {
		boolean trouve = false; // On suppose pas trouv�.
		for (int i = 0; i < tabCarMot.length; i++) {
			if (tabCarMot[i] == lettre) {
				tabTirets[i] = lettre;
				trouve = true;
			}
		}
		return trouve;
	}

	/**
	 * M�thode qui permet de compter le nombre de lettres trouv�es. La m�thode
	 * re�oit en param�tre le tableau des tirets et retourne le nombre de
	 * lettres trouv�es.
	 */

	private int compterLettresTrouvees(char[] tabTirets) {
		int nbLettres = 0;
		for (int i = 0; i < tabTirets.length; i++) {
			if (tabTirets[i] != TIRET) {
				nbLettres++;
			}
		}
		return nbLettres;
	}

	/**
	 * M�thode qui affiche le bonhomme du pendu selon le nombre d'erreurs. La
	 * m�thode re�oit en param�tre le nombre d'erreurs.
	 */

	private void afficherPendu(int nbErreurs) {
		switch (nbErreurs) {
		case 0:
			System.out.println("    ______");
			System.out.println("   |      |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|_____________");
			break;

		case 1:
			System.out.println("    ______");
			System.out.println("   |      |");
			System.out.println("   |      O");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|_____________");
			break;

		case 2:
			System.out.println("    ______");
			System.out.println("   |      |");
			System.out.println("   |      O");
			System.out.println("   |      |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|_____________");
			break;

		case 3:
			System.out.println("    ______");
			System.out.println("   |      |");
			System.out.println("   |      O");
			System.out.println("   |     /|");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|_____________");
			break;

		case 4:
			System.out.println("    ______");
			System.out.println("   |      |");
			System.out.println("   |      O");
			System.out.println("   |     /|\\");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|_____________");
			break;

		case 5:
			System.out.println("    ______");
			System.out.println("   |      |");
			System.out.println("   |      O");
			System.out.println("   |     /|\\");
			System.out.println("   |      |");
			System.out.println("   |");
			System.out.println("___|_____________");
			break;

		case 6:
			System.out.println("    ______");
			System.out.println("   |      |");
			System.out.println("   |      O");
			System.out.println("   |     /|\\");
			System.out.println("   |      |");
			System.out.println("   |     /");
			System.out.println("___|_____________");
			break;

		case MAX_ERREURS:
			System.out.println("    ______");
			System.out.println("   |      |");
			System.out.println("   |      O");
			System.out.println("   |     /|\\");
			System.out.println("   |      |");
			System.out.println("   |     / \\");
			System.out.println("___|_____________");
			break;
		}
	}
}