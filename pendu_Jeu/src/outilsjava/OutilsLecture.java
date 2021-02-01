/**
 * Auteure : Soti
 * Fichier : OutilsLecture.java
 * Package : outilsjava
 * Date    : Hiver 2016
 * Cours   : Programmation avec Java
 */

// La classe OutilsLecture fait partie du package outilsjava.

package outilsjava;

// Package du système.
import java.io.*;

/**
 * Classe qui contient certaines méthodes utilitaires de lecture.
 */

public class OutilsLecture {
	// Constantes pour les types de lecture.
	public static final char LECTURE_CLAVIER = 'C';
	public static final char LECTURE_FICHIER = 'F';

	// Nom logique du fichier. Par défaut, lecture du clavier.
	public static BufferedReader fic = 
			new BufferedReader( new InputStreamReader( System.in ) );
	
	// Type de Lecture. Par défaut, lecture du clavier.
	public static char type = LECTURE_CLAVIER;

	/**
	 * On définit le constructeur private pour empêcher la création d'instances
	 * de la classe OutilsLecture.
	 */
	
	private OutilsLecture() {
	}
	
	/**
	 * La méthode publique lireEntree() permet d'afficher une question et
	 * de lire seulement la touche Entrée du clavier.
	 * 
	 * @param question
	 *            La question à afficher.
	 */

	public static void lireEntree( String question ) {
		String chaine;
		boolean valide;
		int lgChaine;

		do {
			valide = true;
			System.out.print( question );

			try {
				// Peut lire la touche Entree du fichier texte des jeux d'essai
				// ou du clavier.
				chaine = fic.readLine();

				if ( type == LECTURE_FICHIER ) {
					// Si lecture du fichier texte, on l'affiche sur la console.
					System.out.println(chaine);
				}

				lgChaine = chaine.length();

				// Erreur, si la chaîne lue n'est pas vide.

				if ( lgChaine > 0 ) {
					System.out.println( "\nErreur, appuyez sur la touche "
							+ "Entrée seulement." );
					valide = false;
				}
			}

			catch ( IOException errIO ) {
				System.out.println( "\nUne erreur d'entrée-sortie" +
						" est survenue." );
				valide = false;
			}

			catch ( Exception err ) {
				System.out.println( "\nUne erreur de lecture est survenue." );
				valide = false;
			}
		} while ( !valide );
	}

	/**
	 * La méthode publique lireChaine() permet d'afficher une question et de
	 * lire une chaîne de caractères quelconque.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @return La chaîne de caractères lue.
	 */

	public static String lireChaine( String question ) {
		String chaine = "";
		boolean valide;
		int lgChaine;

		do {
			valide = true;
			System.out.print( question );

			try {
				// Peut lire une chaîne du fichier texte des jeux d'essai
				// ou du clavier.
				chaine = fic.readLine();
				
				if ( type == LECTURE_FICHIER ) {
					// Si lecture du fichier texte, on l'affiche sur la console.
					System.out.println(chaine);
				}

				lgChaine = chaine.length();

				// Erreur, si la chaîne lue est vide.

				if ( lgChaine == 0 ) {
					System.out.println( "\nErreur, l'entrée ne doit pas" +
							" être vide." );
					valide = false;
				}
			}

			catch ( IOException errIO ) {
				System.out.println( "\nUne erreur d'entrée-sortie" +
						" est survenue." );
				valide = false;
			}

			catch ( Exception err ) {
				System.out.println( "\nUne erreur de lecture est survenue." );
				valide = false;
			}
		} while ( !valide );

		return chaine;
	}

	/**
	 * La méthode publique lireChaineValide() permet d'afficher une question et
	 * de lire une chaîne de caractères dont la longueur fait partie d'un
	 * intervalle.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @param nbMinCar
	 *            Le nombre minimum de caractères à lire.
	 * @param nbMaxCar
	 *            Le nombre maximum de caractères à lire.
	 * @return La chaîne de caractères lue.
	 */

	public static String lireChaineValide( String question,
										   int nbMinCar, int nbMaxCar ) {
		String chaine = "";
		boolean valide;
		int lgChaine;

		do {
			valide = true;

			chaine = OutilsLecture.lireChaine( question );
			lgChaine = chaine.length();

			// Erreur, si le nombre de caractères de la chaîne lue
			// n'est pas entre nbMinCar et nbMaxCar.

			if ( lgChaine < nbMinCar || lgChaine > nbMaxCar ) {
				System.out.println( "\nErreur, entrez entre " + nbMinCar +
						" et " + nbMaxCar + " caractères." );
				valide = false;
			}
		} while ( !valide );

		return chaine;
	}

	/**
	 * La méthode publique lireChaineExacte() permet d'afficher une question et
	 * de lire une chaîne de caractères d'une certaine longueur.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @param nbCar
	 *            Le nombre exact de caractères à lire.
	 * @return La chaîne de caractères lue.
	 */

	public static String lireChaineExacte( String question, int nbCar ) {
		String chaine = "";
		boolean valide;
		int lgChaine;

		do {
			valide = true;

			chaine = OutilsLecture.lireChaine( question );
			lgChaine = chaine.length();

			// Erreur, si le nombre de caractères de la chaîne lue
			// n'est pas exactement nbCar.

			if ( lgChaine != nbCar ) {
				System.out.println( "\nErreur, entrez exactement " + nbCar
						+ " caractères." );
				valide = false;
			}
		} while ( !valide );

		return chaine;
	}

	/**
	 * La méthode publique lireCaractere() permet d'afficher une question et de
	 * lire un caractère quelconque.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @return Le caractère lu.
	 */

	public static char lireCaractere( String question ) {
		String chaine = "";
		boolean valide;
		int lgChaine;

		do {
			valide = true;
			chaine = OutilsLecture.lireChaine( question );
			lgChaine = chaine.length();

			// Erreur, si le nombre de caractères de la chaîne lue
			// n'est pas exactement 1.
			
			if ( lgChaine != 1 ) {
				System.out.println( "\nErreur, entrez un seul caractère." );
				valide = false;
			}
		} while ( !valide );

		// Retourne le premier caractère de la chaîne lue.
		return chaine.charAt( 0 );
	}

	/**
	 * La méthode publique lireOuiNon() permet d'afficher une question et de
	 * lire une réponse O ou N de l'utilisateur.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @return Le caractère O ou N en majuscule.
	 */

	public static char lireOuiNon( String question ) {
		char rep = '\0';  // Caractère nul (vide).
		boolean valide;

		do {
			valide = true;

			// Convertir le caractère lu en majuscule.
			rep = Character.toUpperCase(
					OutilsLecture.lireCaractere( question ) );

			// Erreur, si le caractère lu n'est pas O ou N.
			
			if ( rep != 'O' && rep != 'N' ) {
				System.out.println(
						"\nErreur, répondez par O ou N seulement." );
				valide = false;
			}
		} while ( !valide );

		return rep;
	}

	/**
	 * La méthode publique lireCaractereValide() permet d'afficher une question
	 * et de lire un caractère qui fait partie d'un intervalle.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @param carMin
	 *            Le caractère minimum.
	 * @param carMax
	 *            Le caractère maximum.
	 * @return Le caractère lu en majuscule.
	 */

	public static char lireCaractereValide( String question,
										    char carMin, char carMax ) {
		char rep = '\0';  // Caractère nul (vide).
		boolean valide;

		// Convertir carMin et carMax en majuscules.
		carMin = Character.toUpperCase( carMin );
		carMax = Character.toUpperCase( carMax );

		do {
			valide = true;

			// Convertir le caractère lu en majuscule.
			rep = Character.toUpperCase(
					OutilsLecture.lireCaractere( question ) );

			// Erreur, si le caractère lu n'est pas entre carMin et carMax.

			if ( rep < carMin || rep > carMax ) {
				System.out.println( "\nErreur, entrez un caractère entre "
						+ carMin + " et " + carMax + "." );
				valide = false;
			}
		} while ( !valide );

		return rep;
	}

	/**
	 * La méthode publique lireCaractereDisparate() permet d'afficher une
	 * question et de lire un caractère qui fait partie d'une certaine chaîne
	 * de caractères.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @param carPossibles
	 *            La chaîne de caractères qui contient les différents
	 *            caractères que l'on peut lire.
	 * @return Le caractère lu en majuscule.
	 */

	public static char lireCaractereDisparate( String question,
											   String carPossibles ) {
		char rep = '\0';  // Caractère nul (vide).
		boolean valide;

		// Convertir les caractères possibles en majuscules.
		carPossibles = carPossibles.toUpperCase();

		do {
			valide = true;

			// Convertir le caractère lu en majuscule.
			rep = Character.toUpperCase(
					OutilsLecture.lireCaractere( question ) );

			// Erreur, si le caractère lu ne fait pas partie des car. possibles.
			
			if ( carPossibles.indexOf( rep ) == -1 ) {
				System.out.println("\nErreur, entrez un caractère parmi les "
						+ "suivants : " + carPossibles + ".");
				valide = false;
			}
		} while ( !valide );

		return rep;
	}

	/**
	 * La méthode publique lireEntier() permet d'afficher une question et de
	 * lire un nombre entier quelconque.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @return Le nombre entier lu.
	 */

	public static int lireEntier( String question ) {
		String chaine = "";
		boolean valide;
		int nombreLu = 0;
		
		do {
			valide = true;

			chaine = OutilsLecture.lireChaine( question );

			try {
				nombreLu = Integer.parseInt( chaine );
			}

			catch ( NumberFormatException errFormat ) {
				System.out.println( "\nErreur de formatage, " +
						"entrez un nombre entier." );
				valide = false;
			}
		} while ( !valide );

		return nombreLu;
	}

	/**
	 * La méthode publique lireEntierValide() permet d'afficher une question et
	 * de lire un nombre entier qui fait partie d'un intervalle.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @param min
	 *            Le nombre minimum.
	 * @param max
	 *            Le nombre maximum.
	 * @return Le nombre entier lu.
	 */

	public static int lireEntierValide( String question, int min, int max ) {
		int nombreLu = 0;
		boolean valide;

		do {
			valide = true;

			nombreLu = OutilsLecture.lireEntier( question );
			
			// Erreur, si le nombre lu n'est pas entre min et max.

			if ( nombreLu < min || nombreLu > max ) {
				System.out.println( "\nErreur, entrez un nombre entier entre "
						+ min + " et " + max + "." );
				valide = false;
			}
		} while ( !valide );

		return nombreLu;
	}

	/**
	 * La méthode publique lireReel() permet d'afficher une question et de lire
	 * un nombre réel quelconque.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @return Le nombre réel lu.
	 */

	public static double lireReel( String question ) {
		String chaine = "";
		boolean valide;
		double nombreLu = 0;
		
		do {
			valide = true;

			chaine = OutilsLecture.lireChaine( question );

			try {
				nombreLu = Double.parseDouble( chaine );
			}

			catch ( NumberFormatException errFormat ) {
				System.out.println( "\nErreur de formatage, " +
						"entrez un nombre réel." );
				valide = false;
			}
		} while ( !valide );

		return nombreLu;
	}

	/**
	 * La méthode publique lireReelValide() permet d'afficher une question et de
	 * lire un nombre réel qui fait partie d'un intervalle.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @param min
	 *            Le nombre minimum.
	 * @param max
	 *            Le nombre maximum.
	 * @return Le nombre réel lu.
	 */

	public static double lireReelValide( String question,
										 double min, double max ) {
		double nombreLu = 0;
		boolean valide;

		do {
			valide = true;

			nombreLu = OutilsLecture.lireReel( question );

			// Erreur, si le nombre lu n'est pas entre min et max.

			if ( nombreLu < min || nombreLu > max ) {
				System.out.println( "\nErreur, entrez un nombre réel entre "
						+ min + " et " + max + "." );
				valide = false;
			}
		} while ( !valide );

		return nombreLu;
	}
}