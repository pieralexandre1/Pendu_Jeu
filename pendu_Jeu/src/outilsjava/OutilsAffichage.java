/**
 * Auteure : Soti
 * Fichier : OutilsAffichage.java
 * Package : outilsjava
 * Date    : Hiver 2016
 * Cours   : Programmation avec Java
 */

// La classe OutilsAffichage fait partie du package outilsjava.

package outilsjava;

// Package du système.
import java.text.*;

/**
 * Classe qui contient certaines méthodes utilitaires d'affichage.
 */

public class OutilsAffichage {

	/**
	 * On définit le constructeur private pour empêcher la création d'instances
	 * de la classe OutilsAffichage.
	 */
	
	private OutilsAffichage() {
	}

	/**
	 * La méthode publique afficherChoisirMenu() permet d'afficher un menu et
	 * de lire le choix de l'utilisateur.
	 * 
	 * @param menu
	 *            La chaîne de caractères contenant le menu à afficher.
	 * @param choixMin
	 *            Le caractère contenant le premier choix du menu.
	 * @param choixMax
	 *            Le caractère contenant le dernier choix du menu.
	 * @return Le caractère contenant le choix de l'utilisateur en majuscule.
	 */

	public static char afficherChoisirMenu( String menu,
										    char choixMin, char choixMax ) {
		char choixLu;

		System.out.println( menu );

		choixLu = OutilsLecture.lireCaractereValide( "\nEntrez votre choix : ",
													 choixMin, choixMax );
		return choixLu;
	}

	/**
	 * La méthode publique formaterNb() permet de formater un nombre dans
	 * le format 9 999,99.
	 * 
	 * @param nb
	 *            Le nombre à formater.
	 * @param dec
	 *            Le nombre de décimales à mettre après la virgule.
	 * @return La chaîne de caractères contenant le nombre formaté.
	 */

	public static String formaterNb( double nb, int dec ) {
		NumberFormat formatNb = NumberFormat.getNumberInstance();

		formatNb.setMinimumIntegerDigits(1);
		formatNb.setMinimumFractionDigits(dec);
		formatNb.setMaximumFractionDigits(dec);

		return formatNb.format( nb );
	}

	/**
	 * La méthode publique formaterMonetaire() permet de formater un
	 * nombre dans le format 9 999,99 $.
	 * 
	 * @param nb
	 *            Le nombre à formater.
	 * @param dec
	 *            Le nombre de décimales à mettre après la virgule.
	 * @return La chaîne de caractères contenant le nombre formaté.
	 */

	public static String formaterMonetaire( double nb, int dec ) {
		NumberFormat formatNb = NumberFormat.getCurrencyInstance();

		formatNb.setMinimumIntegerDigits(1);
		formatNb.setMinimumFractionDigits(dec);
		formatNb.setMaximumFractionDigits(dec);

		return formatNb.format( nb );
	}

	/**
	 * La méthode publique formaterPourcentage() permet de formater un
	 * nombre dans le format 9 999,99 %.
	 * 
	 * @param nb
	 *            Le nombre à formater.
	 * @param dec
	 *            Le nombre de décimales à mettre après la virgule.
	 * @return La chaîne de caractères contenant le nombre formaté.
	 */

	public static String formaterPourcentage( double nb, int dec ) {
		NumberFormat formatNb = NumberFormat.getPercentInstance();

		formatNb.setMinimumIntegerDigits(1);
		formatNb.setMinimumFractionDigits(dec);
		formatNb.setMaximumFractionDigits(dec);

		return formatNb.format( nb );
	}
}