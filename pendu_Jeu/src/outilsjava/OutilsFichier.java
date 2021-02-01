/**
 * Auteure : Soti
 * Fichier : OutilsFichier.java
 * Package : outilsjava
 * Date    : Hiver 2016
 * Cours   : Programmation avec Java
 */

// La classe OutilsFichier fait partie du package outilsjava.

package outilsjava;

// Packages du système.
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

/**
 * Classe qui contient certaines méthodes utilitaires pour les fichiers. Cette
 * classe fait partie du package outilsjava.
 */

public class OutilsFichier {

	private static final int MAX_CAR_FICHIER = 250;

	/**
	 * On définit le constructeur private pour empêcher la création d'instances
	 * de la classe OutilsFichier.
	 */
	
	private OutilsFichier() {
	}

	/**
	 * La méthode publique lireNomFichier() permet d'afficher une question et de
	 * lire une chaîne de caractères représentant un nom physique de fichier.
	 * 
	 * @param question
	 *            La question à afficher.
	 * @return La chaîne de caractères représentant un nom physique de fichier.
	 */

	public static String lireNomFichier( String question ) {
		String nomFic;

		nomFic = OutilsLecture.lireChaineValide( question, 1, MAX_CAR_FICHIER );

		return nomFic;
	}

	/**
	 * La méthode publique ouvrirFicTexteLecture() permet d'ouvrir un fichier
	 * texte en mode lecture bufferisée.
	 * 
	 * @param nomFichier
	 *            Le nom physique du fichier.
	 * @return le nom logique du fichier si l'ouverture est un succès ou
	 *         null dans le cas contraire.
	 */

	public static BufferedReader ouvrirFicTexteLecture( String nomFichier ) {

		boolean valide = true;
		Path chemin = null;
		String cheminAbsolu = "";
		BufferedReader ficLecture = null;

		// Création du chemin.
		try {
			chemin = Paths.get( nomFichier );

		} 
		catch ( InvalidPathException errNomFichier ) {
			System.out.println( "\nErreur, le fichier " + nomFichier 
					+ " contient des caractères illégaux." );
			valide = false;
		}

		// Si la création du chemin est valide, on peut poursuivre.

		if ( valide ) {
			cheminAbsolu = chemin.toAbsolutePath().toString();

			// Vérifier l'existence du fichier.

			if ( Files.notExists( chemin ) ) {
				// Le fichier n'existe pas.
				System.out.println( "\nErreur, le fichier " + cheminAbsolu
						+ " n'existe pas." );
				valide = false;

			} else if ( Files.exists( chemin ) ) {
				// Le fichier existe.
				// Est-ce un fichier ordinaire ?

				if ( !Files.isRegularFile( chemin ) ) {

					System.out.println( "\nErreur, le fichier " + cheminAbsolu
							+ " n'est pas un fichier ordinaire." );
					valide = false;
				} else {
					// C'est un fichier ordinaire.
					// Est-ce un fichier permis en lecture ?

					if ( !Files.isReadable( chemin ) ) {

						System.out.println( "\nErreur, le fichier "
								+ cheminAbsolu
								+ " n'est pas permis en lecture." );
						valide = false;
					} else {
						// Le fichier existe, est ordinaire 
						// et permis en lecture.

						// Ouverture du fichier texte en mode lecture.

						try {
							ficLecture = Files.newBufferedReader( chemin,
											        Charset.defaultCharset() );
						}
						catch ( IOException errIO ) {
							System.out
									.println( "\nErreur, impossible d'ouvrir "
											+ "le fichier " + cheminAbsolu
											+ " en mode lecture texte." );
							valide = false;
						}
					}
				}
			} else {
				System.out.println( "\nErreur, impossible de vérifier "
						+ "l'existence du fichier " + cheminAbsolu + "." );
				valide = false;
			}
		}

		return ficLecture;
	}

	/**
	 * La méthode publique ouvrirFicTexteEcriture() permet d'ouvrir un fichier
	 * texte en mode écriture bufferisée.
	 * 
	 * @param nomFichier
	 *            Le nom physique du fichier.
	 * @return le nom logique du fichier si l'ouverture est un succès ou
	 *         null dans le cas contraire.
	 */

	public static BufferedWriter ouvrirFicTexteEcriture( String nomFichier ) {

		boolean valide = true;
		Path chemin = null;
		String cheminAbsolu = "";
		BufferedWriter ficEcriture = null;

		// Création du chemin.
		try {
			chemin = Paths.get( nomFichier );

		}
		catch ( InvalidPathException errNomFichier ) {
			System.out.println( "\nErreur, le fichier " + nomFichier
					+ " contient des caractères illégaux." );
			valide = false;
		}

		// Si la création du chemin est valide, on peut poursuivre.

		if ( valide ) {
			cheminAbsolu = chemin.toAbsolutePath().toString();

			// Vérifier l'existence du fichier.

			if ( Files.notExists( chemin ) ) {
				// Le fichier n'existe pas. On peut l'ouvrir en écriture.
				valide = true;

			} else if ( Files.exists( chemin ) ) {
				// Le fichier existe.
				// Est-ce un fichier ordinaire ?

				if ( !Files.isRegularFile( chemin ) ) {

					System.out.println( "\nErreur, le fichier " + cheminAbsolu
							+ " n'est pas un fichier ordinaire." );
					valide = false;
				} else {
					// C'est un fichier ordinaire.
					// Est-ce un fichier permis en écriture ?

					if ( !Files.isWritable( chemin ) ) {

						System.out.println( "\nErreur, le fichier "
								+ cheminAbsolu
								+ " n'est pas permis en écriture." );
						valide = false;
					} else {
						// Le fichier existe, est ordinaire 
						// et permis en écriture.
						// On demande une confirmation avant de l'écraser.

						System.out.println( "\nLe fichier " + cheminAbsolu
								+ " existe." );

						char conf =	OutilsLecture.lireOuiNon( "Voulez-vous "
										+ "écraser ce fichier ( O ou N ) ? " );

						valide = ( conf == 'O' ); // valide = true ou false.
					}
				}
			} else {
				System.out.println( "\nErreur, impossible de vérifier "
						+ "l'existence du fichier " + cheminAbsolu + "." );
				valide = false;
			}
		}

		if ( valide ) {
			// Le fichier n'existe pas.
			// Ou le fichier existe, est ordinaire, est permis écriture et
			// on veut l'écraser.
			
			// Ouverture du fichier texte en mode écriture.

			try {
				ficEcriture = Files.newBufferedWriter( chemin,
												Charset.defaultCharset() );
			}
			catch ( IOException errIO ) {
				System.out.println( "\nErreur, impossible d'ouvrir "
						+ "le fichier " + cheminAbsolu
						+ " en mode écriture texte." );
				valide = false;
			}
		}

		return ficEcriture;
	}

	/**
	 * La méthode publique ouvrirFicTexteEcritureAjout() permet d'ouvrir un
	 * fichier texte en mode ajout de texte à la fin du fichier. Le fichier est
	 * créé, s'il n'existe pas.
	 * 
	 * @param nomFichier
	 *            Le nom physique du fichier.
	 * @return le nom logique du fichier si l'ouverture est un succès ou null
	 *         dans le cas contraire.
	 */

	public static BufferedWriter ouvrirFicTexteEcritureAjout( 
														String nomFichier ) {

		boolean valide = true;
		Path chemin = null;
		String cheminAbsolu = "";
		BufferedWriter ficEcriture = null;

		// Création du chemin.
		try {
			chemin = Paths.get( nomFichier );

		}
		catch ( InvalidPathException errNomFichier ) {
			System.out.println( "\nErreur, le fichier " + nomFichier
					+ " contient des caractères illégaux." );
			valide = false;
		}

		// Si la création du chemin est valide, on peut poursuivre.

		if ( valide ) {
			cheminAbsolu = chemin.toAbsolutePath().toString();

			// Vérifier l'existence du fichier.

			if ( Files.notExists( chemin ) ) {
				// Le fichier n'existe pas. On peut l'ouvrir en écriture.
				valide = true;

			} else if ( Files.exists( chemin ) ) {
				// Le fichier existe.
				// Est-ce un fichier ordinaire ?

				if ( !Files.isRegularFile( chemin ) ) {

					System.out.println( "\nErreur, le fichier " + cheminAbsolu
							+ " n'est pas un fichier ordinaire." );
					valide = false;
				} else {
					// C'est un fichier ordinaire.
					// Est-ce un fichier permis en écriture ?

					if ( !Files.isWritable( chemin ) ) {

						System.out.println( "\nErreur, le fichier "
								+ cheminAbsolu
								+ " n'est pas permis en écriture." );
						valide = false;
					} else {
						// Le fichier existe, est ordinaire et
						// est permis en écriture.
						// On peut l'ouvrir en écriture pour ajouter à la fin.
						valide = true;
					}
				}
			} else {
				System.out.println( "\nErreur, impossible de vérifier "
						+ "l'existence du fichier " + cheminAbsolu + "." );
				valide = false;
			}
		}

		if ( valide ) {
			// Le fichier n'existe pas.
			// Ou le fichier existe, est ordinaire et est permis écriture.
			// Ouverture du fichier texte en mode écriture (ajouter à la fin).

			try {
				ficEcriture =
						Files.newBufferedWriter( chemin,
								Charset.defaultCharset(),
								StandardOpenOption.CREATE,
								StandardOpenOption.APPEND );
			}
			catch ( IOException errIO ) {
				System.out.println( "\nErreur, impossible d'ouvrir "
						+ "le fichier " + cheminAbsolu
						+ " en mode écriture texte (ajouter à la fin)." );
				valide = false;
			}
		}

		return ficEcriture;
	}

	/**
	 * La méthode publique fermerFicTexteLecture() permet de fermer un fichier
	 * texte en mode lecture bufferisée.
	 * 
	 * @param nomLogique
	 *            Le nom logique du fichier.
	 * @param nomFic
	 *            Le nom physique du fichier.
	 * @return true si la fermeture du fichier est un succès ou false dans le
	 *         cas contraire.
	 */

	public static boolean fermerFicTexteLecture( BufferedReader nomLogique,
			String nomFic ) {

		boolean fermerFic = true;

		try {
			nomLogique.close();
		}
		catch ( IOException errIO ) {
			System.out.println( "Erreur, impossible de fermer le fichier "
					+ nomFic + "." );
			fermerFic = false;
		}

		return fermerFic;
	}

	/**
	 * La méthode publique fermerFicTexteEcriture() permet de fermer un fichier
	 * texte en mode écriture bufferisée.
	 * 
	 * @param nomLogique
	 *            Le nom logique du fichier.
	 * @param nomFic
	 *            Le nom physique du fichier.
	 * @return true si la fermeture du fichier est un succès ou false dans le
	 *         cas contraire.
	 */

	public static boolean fermerFicTexteEcriture( BufferedWriter nomLogique,
			String nomFic ) {

		boolean fermerFic = true;

		try {
			nomLogique.close();
		}
		catch ( IOException errIO ) {
			System.out.println( "Erreur, impossible de fermer le fichier "
					+ nomFic + "." );
			fermerFic = false;
		}

		return fermerFic;
	}

	/**
	 * La méthode publique ouvrirFicBinLecture() permet d'ouvrir un fichier
	 * sérialisé (binaire) en mode lecture.
	 * 
	 * @param nomFichier
	 *            Le nom physique du fichier.
	 * @return le nom logique du fichier si l'ouverture est un succès ou null
	 *         dans le cas contraire.
	 */

	public static ObjectInputStream ouvrirFicBinLecture( String nomFichier ) {

		boolean valide = true;
		Path chemin = null;
		String cheminAbsolu = "";
		ObjectInputStream ficLecture = null;

		// Création du chemin.
		try {
			chemin = Paths.get( nomFichier );
		}
		catch ( InvalidPathException errNomFichier ) {
			System.out.println( "\nErreur, le fichier " + nomFichier
					+ " contient des caractères illégaux." );
			valide = false;
		}

		// Si la création du chemin est valide, on peut poursuivre.

		if ( valide ) {
			cheminAbsolu = chemin.toAbsolutePath().toString();

			// Vérifier l'existence du fichier.

			if ( Files.notExists( chemin ) ) {
				// Le fichier n'existe pas.
				System.out.println( "\nErreur, le fichier " + cheminAbsolu
						+ " n'existe pas." );
				valide = false;

			} else if ( Files.exists( chemin ) ) {
				// Le fichier existe.
				// Est-ce un fichier ordinaire ?

				if ( !Files.isRegularFile( chemin ) ) {

					System.out.println( "\nErreur, le fichier " + cheminAbsolu
							+ " n'est pas un fichier ordinaire." );
					valide = false;
				} else {
					// C'est un fichier ordinaire.
					// Est-ce un fichier permis en lecture ?

					if ( !Files.isReadable( chemin ) ) {

						System.out.println( "\nErreur, le fichier "
								+ cheminAbsolu
								+ " n'est pas permis en lecture." );
						valide = false;
					} else {
						// Le fichier existe, est ordinaire et 
						// est permis en lecture.
						
						// Ouverture du fichier texte en mode lecture.

						try {
							ficLecture = new ObjectInputStream( 
											new FileInputStream( nomFichier ) );
						}
						catch ( IOException errIO ) {
							System.out
									.println( "\nErreur, impossible d'ouvrir "
											+ "le fichier " + cheminAbsolu
											+ " en mode lecture binaire." );
							valide = false;
						}
					}
				}
			} else {
				System.out.println( "\nErreur, impossible de vérifier "
						+ "l'existence du fichier " + cheminAbsolu + "." );
				valide = false;
			}
		}

		return ficLecture;
	}


	/**
	 * La méthode publique ouvrirFicBinEcriture() permet d'ouvrir un fichier
	 * sérialisé (binaire) en mode écriture.
	 * 
	 * @param nomFichier
	 *            Le nom physique du fichier.
	 * @return le nom logique du fichier si l'ouverture est un succès ou null
	 *         dans le cas contraire.
	 */

	public static ObjectOutputStream ouvrirFicBinEcriture( String nomFichier ) {

		boolean valide = true;
		Path chemin = null;
		String cheminAbsolu = "";
		ObjectOutputStream ficEcriture = null;

		// Création du chemin.
		try {
			chemin = Paths.get( nomFichier );
		}
		catch ( InvalidPathException errNomFichier ) {
			System.out.println( "\nErreur, le fichier " + nomFichier
					+ " contient des caractères illégaux." );
			valide = false;
		}

		// Si la création du chemin est valide, on peut poursuivre.

		if ( valide ) {
			cheminAbsolu = chemin.toAbsolutePath().toString();

			// Vérifier l'existence du fichier.

			if ( Files.notExists( chemin ) ) {
				// Le fichier n'existe pas. On peut l'ouvrir en écriture.
				valide = true;

			} else if ( Files.exists( chemin ) ) {
				// Le fichier existe.
				// Est-ce un fichier ordinaire ?

				if ( !Files.isRegularFile( chemin ) ) {

					System.out.println( "\nErreur, le fichier " + cheminAbsolu
							+ " n'est pas un fichier ordinaire." );
					valide = false;
				} else {
					// C'est un fichier ordinaire.
					// Est-ce un fichier permis en écriture ?

					if ( !Files.isWritable( chemin ) ) {

						System.out.println( "\nErreur, le fichier "
								+ cheminAbsolu
								+ " n'est pas permis en écriture." );
						valide = false;
					} else {
						// Le fichier existe, est ordinaire et 
						// est permis en écriture.
						// On demande une confirmation avant de l'écraser.

						System.out.println( "\nLe fichier " + cheminAbsolu
								+ " existe." );

						char conf =	OutilsLecture.lireOuiNon( "Voulez-vous "
										+ "écraser ce fichier ( O ou N ) ? " );

						valide = ( conf == 'O' ); // valide = true ou false.
					}
				}
			} else {
				System.out.println( "\nErreur, impossible de vérifier "
						+ "l'existence du fichier " + cheminAbsolu + "." );
				valide = false;
			}
		}

		if ( valide ) {
			// Le fichier n'existe pas.
			// Ou le fichier existe, est ordinaire, est permis écriture et
			// on veut l'écraser.
			
			// Ouverture du fichier texte en mode écriture.

			try {
				ficEcriture = new ObjectOutputStream( 
										new FileOutputStream( nomFichier ) );
			}
			catch ( IOException errIO ) {
				System.out.println( "\nErreur, impossible d'ouvrir "
						+ "le fichier " + cheminAbsolu
						+ " en mode écriture binaire." );
				valide = false;
			}
		}

		return ficEcriture;
	}

	/**
	 * La méthode publique fermerFicBinLecture() permet de fermer un fichier
	 * sérialisé (binaire) en mode lecture.
	 * 
	 * @param nomLogique
	 *            Le nom logique du fichier.
	 * @param nomFic
	 *            Le nom physique du fichier.
	 * @return true si la fermeture du fichier est un succès ou false dans le
	 *         cas contraire.
	 */

	public static boolean fermerFicBinLecture( ObjectInputStream nomLogique,
			String nomFic ) {

		boolean fermerFic = true;

		try {
			nomLogique.close();
		}
		catch ( IOException errIO ) {
			System.out.println( "Erreur, impossible de fermer le fichier "
					+ nomFic + "." );
			fermerFic = false;
		}

		return fermerFic;
	}

	/**
	 * La méthode publique fermerFicBinEcriture() permet de fermer un fichier
	 * sérialisé (binaire) en mode écriture.
	 * 
	 * @param nomLogique
	 *            Le nom logique du fichier.
	 * @param nomFic
	 *            Le nom physique du fichier.
	 * @return true si la fermeture du fichier est un succès ou false dans le
	 *         cas contraire.
	 */

	public static boolean fermerFicBinEcriture( ObjectOutputStream nomLogique,
			String nomFic ) {

		boolean fermerFic = true;

		try {
			nomLogique.close();
		}
		catch ( IOException errIO ) {
			System.out.println( "Erreur, impossible de fermer le fichier "
					+ nomFic + "." );
			fermerFic = false;
		}

		return fermerFic;
	}
	
	/**
	 * La méthode publique ouvrirFicRandom() permet d'ouvrir un fichier en mode
	 * accès aléatoire lecture ou lecture/écriture.
	 * 
	 * @param nomFichier
	 *            Le nom physique du fichier.
	 * @param mode
	 *            Le mode "r" ou "rw".
	 * @return Le nom logique du fichier si capable de l'ouvrir et null dans le
	 *         cas contraire.
	 */

	public static RandomAccessFile ouvrirFicRandom( String nomFichier,
													String mode ) {

		boolean valide = true;
		Path chemin = null;
		String cheminAbsolu = "";
		RandomAccessFile ficRandom = null;

		// Création du chemin.
		try {
			chemin = Paths.get( nomFichier );

		}
		catch ( InvalidPathException errNomFichier ) {
			System.out.println( "\nErreur, le fichier " + nomFichier
					+ " contient des caractères illégaux." );
			valide = false;
		}

		// Si la création du chemin est valide, peut poursuivre.

		if ( valide ) {
			cheminAbsolu = chemin.toAbsolutePath().toString();

			valide = false; // Assume suite pas ok.

			mode = mode.toLowerCase();

			if ( mode.equals( "r" ) ) {

				// Vérifier si peut ouvrir le fichier en mode lecture.

				if ( Files.notExists( chemin ) ) {
					// Le fichier n'existe pas.

					System.out.println( "\nLe fichier " + cheminAbsolu
							+ " n'existe pas." );

				} else if ( Files.exists( chemin ) ) {
					// Le fichier existe.

					// Fichier ordinaire ?

					if ( !Files.isRegularFile( chemin ) ) {

						System.out.println( "\nErreur, le fichier "
								+ cheminAbsolu
								+ " n'est pas un fichier ordinaire." );
					} else {
						// C'est un fichier ordinaire.

						// Permis en lecture ?

						if ( !Files.isReadable( chemin ) ) {

							System.out.println( "\nErreur, le fichier "
									+ cheminAbsolu
									+ " n'est pas permis en lecture." );
						} else {
							// Fichier existe,
							// est ordinaire et permis en lecture.
							valide = true;
						}
					}
				}

			} else if ( mode.equals( "rw" ) ) {
				// Vérifier si peut ouvrir le fichier en mode lecture/écriture.
				// Si le fichier n'existe pas, on veut le créer et travailler
				// avec en mode lecture/écriture.

				if ( Files.notExists( chemin ) ) {
					// Le fichier n'existe pas, on essaie de le créer vide.

					System.out.println( "\nLe fichier " + cheminAbsolu
							+ " n'existe pas.\nCréation de ce fichier vide." );
					try {
						Files.createFile( chemin );

						// Le fichier existe maintenant. On peut essayer de
						// l'ouvrir en mode lecture/écriture.

						valide = true;
					}

					catch ( IOException erEx ) {
						System.out.println( "\nErreur, impossible de créer le "
								+ "fichier " + cheminAbsolu + " vide." );
					}
					
				} else if ( Files.exists( chemin ) ) {
					// Le fichier existe. On peut essayer de l'ouvrir en
					// mode lecture/écriture.

					valide = true;
				}

			} else {
				// Ce n'est pas un mode "r" ou "rw".

				System.out.println( "\nErreur, impossible d'ouvrir le fichier "
						+ cheminAbsolu + " en mode " + mode + "." );
			}

		}

		if ( valide ) {
			// Essayer d'ouvrir le fichier en mode lecture ou lecture/écriture.

			try {
				ficRandom = new RandomAccessFile( nomFichier, mode );
			}

			catch ( IOException erEx ) {
				System.out.println( "\nErreur, impossible d'ouvrir le fichier "
						+ cheminAbsolu + " en mode " + mode + "." );
				ficRandom = null;
			}
		}

		return ficRandom;
	}

	/**
	 * La méthode publique fermerFicRandom() permet de fermer un fichier en
	 * accès aléatoire, en mode lecture ou en mode lecture/écriture.
	 * 
	 * @param nomLogique
	 *            Le nom logique du fichier.
	 * @param nomFic
	 *            Le nom physique du fichier.
	 * @return true si capable de fermer le fichier et false dans le cas
	 *         contraire.
	 */

	public static boolean fermerFicRandom( RandomAccessFile nomLogique,
										   String nomFic ) {
		
		boolean fermerFic = true;

		try {
			nomLogique.close();
		}
		
		catch ( IOException erEx ) {
			System.out.println( "Erreur, impossible de fermer le fichier "
					+ nomFic + "." );
			fermerFic = false;
		}

		return fermerFic;
	}

}