// enigma Charlotte SAUTIERE & Thomas DUJARDIN S1-A

class Enigma_DUJARDIN_SAUTIERE extends Program {

	// CONSTANTES DE LA MACHINE
	String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String ROTOR1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
	String ROTOR2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
	String ROTOR3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
	String ROTOR4 = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
	String ROTOR5 = "VZBRGITYUPSDNHLXAWMJQOFECK";
	String REFLECTEURA = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
	String REFLECTEURB = "RDOBJNTKVEHMLFCWZAXGYIPSUQ";

	// ----------------------------------------------------------------------------------------
	// Fonction qui retourne un entier correspondant à la position d'une lettre
	// donnée dans l'alphabet (A=0, B=1, C=2, ...)
	// Exemples :
	// entrée : 'A' -> sortie 0
	// entrée : 'B' -> sortie 1
	// ...
	// entrée : 'Z' -> sortie 25
	void testLettreEnNombre() {
		assertEquals(0, lettreEnNombre('A'));
		assertEquals(1, lettreEnNombre('B'));
		assertEquals(25, lettreEnNombre('Z'));
	}

	int lettreEnNombre(char lettre) {
		return ((int) lettre) - 'A';
	}

	// ----------------------------------------------------------------------------------------
	// Fonction qui retourne la lettre associée à une position (un entier) dans
	// l'alphabet (0=A, 1=B, 2=C, ...)
	// Exemples :
	// entrée : 0 -> sortie : 'A'
	// entrée : 1 -> sortie : 'B'
	// ...
	// entrée : 25 -> sortie : 'Z'
	void testNombreEnLettre() {
		assertEquals('A', nombreEnLettre(0));
		assertEquals('B', nombreEnLettre(1));
	}

	char nombreEnLettre(int nombre) {
		int nbAscii = nombre + ((int) 'A');
		return (char) nbAscii;
	}
	// ----------------------------------------------------------------------------------------
	// Fonction qui permet de sélectionner un rotor
	// A partir d'un entier (entre 1 et 5) passé en paramètre, cette fonction
	// retourne le rotor correspondant (la chaîne de caractère correspondante)
	// Exemples :
	// entrée : 1 -> sortie : "EKMFLGDQVZNTOWYHXUSPAIBRCJ" (ROTOR1)
	// entrée : 4 -> sortie : "ESOVPZJAYQUIRHXLNFTGKDCMWB" (ROTOR4)
	// ...

	void testChoixRotor() {
		assertEquals("EKMFLGDQVZNTOWYHXUSPAIBRCJ", choixRotor(1));
		assertEquals("ESOVPZJAYQUIRHXLNFTGKDCMWB", choixRotor(4));
	}

	String choixRotor(int numeroRotor) {
		String rotor = ROTOR5;
		if (numeroRotor == 1) {
			rotor = ROTOR1;
		} else if (numeroRotor == 2) {
			rotor = ROTOR2;
		} else if (numeroRotor == 3) {
			rotor = ROTOR3;
		} else if (numeroRotor == 4) {
			rotor = ROTOR4;
		}
		return rotor;
	}
	// ----------------------------------------------------------------------------------------
	// Fonction qui permet à l'utilisateur de sélectionner le réflecteur
	// A partir d'une lettre ('A' ou 'B') passée en paramètre, cette fonction
	// retourne le réflecteur correspondant (la chaîne de caractère)
	// Exemples :
	// entrée : 'A' -> sortie : "YRUHQSLDPXNGOKMIEBFZCWVJAT" (REFLECTEURA)
	// entrée : 'B' -> sortie : "RDOBJNTKVEHMLFCWZAXGYIPSUQ" (REFLECTEURB)

	void testChoixReflecteur() {
		assertEquals("YRUHQSLDPXNGOKMIEBFZCWVJAT", choixReflecteur('A'));
		assertEquals("RDOBJNTKVEHMLFCWZAXGYIPSUQ", choixReflecteur('B'));
	}

	String choixReflecteur(char lettreReflecteur) {
		String reflecteur = REFLECTEURA;
		if (lettreReflecteur == 'B') {
			reflecteur = REFLECTEURB;
		}
		return reflecteur;
	}
	// ----------------------------------------------------------------------------------------
	// Fonction qui permet à l'utilisateur de la machine de brancher les câbles
	// reliant les paires (6) de lettres
	// Cette fonction doit retourner une chaîne de caractères de 6 lettres
	// majuscules saisies au clavier par l'utilisateur (on supposera que ces 6
	// lettres sont distinctes)
	// Exemple :
	// Si l'utilisateur saisit les 6 paires suivantes : AV puis DE puis HO puis JK
	// puis LS puis XQ, la fonction doit retourner "AVDEHOJKLSXQ"

	String cablageInitial() {
		String chaine = "";
		for (int idx = 1; idx <= 6; idx++) {
			String paire = readString();
			chaine = chaine + paire;
		}
		return chaine;
	}

	void affichageCablageInitial() {
		println(cablageInitial());
	}

	// ----------------------------------------------------------------------------------------
	// Fonction qui permet de décaler le rotor d'un rang vers la gauche
	// A partir d'une chaîne de caractères passée en paramètre, cette fonction
	// retourne la chaîne de caractères décalée d'un cran vers la gauche,
	// c'est-à-dire que la première lettre est déplacée à la fin de la chaîne.
	// Exemples :
	// entrée : "ABCDEFGHIJKLMNOPQRSTUVWXYZ" -> sortie :
	// "BCDEFGHIJKLMNOPQRSTUVWXYZA"
	// entrée : "IFHUQSMDVHNQOIVHZ" -> sortie : "FHUQSMDVHNQOIVHZI"

	void testDecalageUnRang() {
		assertEquals("BCDEFGHIJKLMNOPQRSTUVWXYZA", decalageUnRang("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		assertEquals("FHUQSMDVHNQOIVHZI", decalageUnRang("IFHUQSMDVHNQOIVHZ"));
	}

	String decalageUnRang(String rotor) {
		String decalage = "";
		char caractere;
		char char0 = charAt(rotor, 0);
		for (int idx = 1; idx < length(rotor); idx++) {
			caractere = charAt(rotor, idx);
			decalage = decalage + caractere;
		}
		decalage = decalage + char0;
		return decalage;
	}
	// ----------------------------------------------------------------------------------------
	// Fonction qui retourne le rotor après avoir défini sa position initiale,
	// c'est-à-dire après nb décalages.
	// A partir d'un rotor donné (une chaîne de caractères) et d'un entier nb donné,
	// cette fonction retourne le rotor décalé de nb crans vers la gauche
	// Exemples :
	// entrées : "ABCDEFGHIJKLMNOPQRSTUVWXYZ" et 3 -> sortie :
	// "DEFGHIJKLMNOPQRSTUVWXYZABC"
	// entrées : "IFHUQSMDVHNQOIVHZ" et 5 -> sortie : "SMDVHNQOIVHZIFHUQ"

	void testPositionInitialeRotor() {
		assertEquals("DEFGHIJKLMNOPQRSTUVWXYZABC", positionInitialeRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 3));
		assertEquals("SMDVHNQOIVHZIFHUQ", positionInitialeRotor("IFHUQSMDVHNQOIVHZ", 5));
	}

	String positionInitialeRotor(String rotor, int position) {
		for (int idx = 0; idx < position; idx++) {
			rotor = decalageUnRang(rotor);
		}
		return rotor;
	}
	// ----------------------------------------------------------------------------------------
	// Fonction qui recherche une lettre dans une chaîne de caractères
	// A partir d'une lettre donnée et d'une chaîne de caractère donnée, cette
	// fonction retourne l'indice (la position)
	// de la lettre dans la chaîne (-1 si absent)
	// Exemples :
	// entrées 'C' et "ABCDE" -> sortie 2
	// entrées 'A' et "ABCDE" -> sortie 0
	// entrées 'E' et "ABCDE" -> sortie 4
	// entrées 'F' et "ABCDE" -> sortie -1
	// entrées 'F' et ROTOR1 -> sortie 3

	void testIndiceLettre() {
		assertEquals(2, indiceLettre('C', "ABCDE"));
		assertEquals(1, indiceLettre('B', "ABCDE"));
		assertEquals((-1), indiceLettre('F', "ABCDE"));
		assertEquals(3, indiceLettre('F', ROTOR1));
	}

	int indiceLettre(char lettre, String cablage) {
		int idxlettre = (-1);
		for (int idx = 0; idx < length(cablage); idx = idx + 1) {
			if (lettre == charAt(cablage, idx)) {
				idxlettre = idx;
			}
		}
		return idxlettre;
	}

	// JUSQUE ICI POUR LE 26
	// ----------------------------------------------------------------------------------------
	// Fonction qui permet de vérifier si la lettre à décoder est reliée par un
	// câble à une autre lettre. Si oui, elle est transformée en cette lettre, sinon
	// elle reste identique.
	// A partir d'une lettre donnée et d'un cablâge donné (une chaîne de 12
	// caractères), cette fonction retourne la lettre transformée si elle fait
	// partie d'une paire de lettres d'un des 6 câbles, la même lettre sinon.
	// Exemples :
	// entrées : 'H' et "AVDEHOJKLSXQ" -> sortie 'O' (car 3ème paire HO)
	// entrées : 'A' et "ABCDEFGHIJKL" -> sortie 'B' (car 1ère paire AB)
	// entrées : 'B' et "ABCDEFGHIJKL" -> sortie 'A' (car 1ère paire AB)
	// entrées : 'K' et "ABCDEFGHIJKL" -> sortie 'L' (car 6ème paire KL)
	// entrées : 'D' et "ABCDEFGHIJKL" -> sortie 'C' (car 2ème paire CD)
	// entrées : 'M' et "ABCDEFGHIJKL" -> sortie 'M' (car M absent de la chaîne du
	// cablâge)
	void testValeurApresCablageDeDepart() {
		assertEquals('O', valeurApresCablageDeDepart('H', "AVDEHOJKLSXQ"));
		assertEquals('B', valeurApresCablageDeDepart('A', "ABCDEFGHIJKL"));
		assertEquals('K', valeurApresCablageDeDepart('L', "ABCDEFGHIJKL"));
		assertEquals('M', valeurApresCablageDeDepart('M', "ABCDEFGHIJKL"));
	}

	char valeurApresCablageDeDepart(char lettre, String cablage) {
		char res = lettre;
		for (int idx = 0; idx < length(cablage); idx++) {
			if (charAt(cablage, idx) == lettre) {
				if (idx == 0 || idx % 2 == 0) {
					res = charAt(cablage, idx + 1);
				} else {
					res = charAt(cablage, idx - 1);
				}
			}
		}
		return res;
	}

	// ----------------------------------------------------------------------------------------
	// Fonction qui retourne la nouvelle valeur après le passage dans un rotor
	// A partir d'une lettre donnée et d'un rotor donné (une chaîne de caractères),
	// cette fonction retourne la lettre correspondante à la lettre passée en
	// paramètre après passage dans le rotor donné.
	// Exemples :
	// entrées : 'A' et ROTOR1 -> sortie : 'E'
	// entrées : 'B' et ROTOR1 -> sortie : 'K'
	// entrées : 'Z' et ROTOR1 -> sortie : 'J'
	// entrées : 'E' et "AJDKSIRUXBLHWTMCQGZNPYFVOE" -> sortie : S
	void testPassageDansUnRotor() {
		assertEquals('E', passageDansUnRotor('A', ROTOR1));
		assertEquals('K', passageDansUnRotor('B', ROTOR1));
		assertEquals('J', passageDansUnRotor('Z', ROTOR1));
		assertEquals('S', passageDansUnRotor('E', "AJDKSIRUXBLHWTMCQGZNPYFVOE"));
	}

	char passageDansUnRotor(char lettre, String rotor) {
		char apresPassage = 0;
		int idx = 0;
		do {
			apresPassage = charAt(rotor, idx);
			idx++;
		} while (lettre != charAt(ALPHABET, idx - 1));

		return apresPassage;
	}

	// ----------------------------------------------------------------------------------------
	// Fonction qui retourne la nouvelle valeur après le passage dans le réflecteur
	// A partir d'une lettre donnée et d'un réflecteur donné (une chaîne de
	// caractères), cette fonction retourne la lettre correspondante à la lettre
	// passée en paramètre après passage dans le réflecteur.
	// Exemples :
	// entrées : 'A' et REFLECTEURA -> sortie : 'Y'
	// entrées : 'B' et REFLECTEURA -> sortie : 'R'
	// entrées : 'Z' et "YRUHQSLDPXNGOKMIEBFZCWVJAT" -> sortie : 'T'
	void testPassageDansLeReflecteur() {
		assertEquals('Y', passageDansLeReflecteur('A', REFLECTEURA));
		assertEquals('R', passageDansLeReflecteur('B', REFLECTEURA));
		assertEquals('T', passageDansLeReflecteur('Z', "YRUHQSLDPXNGOKMIEBFZCWVJAT"));
	}

	char passageDansLeReflecteur(char lettre, String reflecteur) {
		return passageDansUnRotor(lettre, reflecteur);
	}

	// ----------------------------------------------------------------------------------------
	// Fonction qui retourne la nouvelle valeur après le passage dans un rotor dans
	// le sens inverse (pour le retour)
	// A partir d'une lettre donnée et d'un rotor donné (une chaîne de caractères),
	// cette fonction retourne la lettre correspondante à la lettre passée en
	// paramètre après passage en sens inverse dans le rotor.
	// Exemples :
	// entrées : 'E' et ROTOR1 -> sortie : 'A'
	// entrées : 'K' et ROTOR1 -> sortie : 'B'
	// entrées : 'J' et ROTOR1 -> sortie : 'Z'
	// entrées : 'S' et "AJDKSIRUXBLHWTMCQGZNPYFVOE" -> sortie : E
	void testInverseRotor() {
		assertEquals('A', inverseRotor('E', ROTOR1));
		assertEquals('B', inverseRotor('K', ROTOR1));
		assertEquals('Z', inverseRotor('J', ROTOR1));
		assertEquals('E', inverseRotor('S', "AJDKSIRUXBLHWTMCQGZNPYFVOE"));
	}

	char inverseRotor(char lettre, String rotor) {
		char apresPassage = 0;
		int idx = 0;
		do {
			apresPassage = charAt(ALPHABET, idx);
			idx++;
		} while (lettre != charAt(rotor, idx - 1));

		return apresPassage;
	}

	// ----------------------------------------------------------------------------------------
	// Fonction qui transforme une chaîne de caractères en majuscule
	String enMajuscule(String message) {
		String majuscules = "";
		for (int idx = 0; idx < length(message); idx++) {
			majuscules = majuscules + (charAt(message, idx) - ('a' - 'A'));
		}
		return majuscules;
	}
	// ----------------------------------------------------------------------------------------
    
    // PROGRAMME PRINCIPAL
    void algorithm(){
	println(" ------------------------------------\n| Simulation d'une machine Enigma M3 |\n ------------------------------------");
	
	// Initialisation des éléments
	// ---------------------------
	println("Quel type de configuration souhaitez-vous ? \n 1 : Configuration par défaut (Rotor 1 : III en position W, Rotor 2 : I en position D, Rotor 3 : V en position E, Réflecteur : B, Cablâge : AV - DE - HO - JK - LS - XQ, Message à décoder par défaut)\n 2 : Configuration personnalisée");
	int choix = readInt();
	while (choix<1 || choix>2){
	    println("Erreur de saisie, choix : 1 ou 2");
	    choix = readInt();
	}
	
	int rotor1,rotor2,rotor3;
	String R1,R2,R3;
	char position1,position2,position3;
	int decalage1,decalage2,decalage3;
	char choixRef;
	String refl;
	String cables;
	String message="";
	
	if (choix == 1){// Configuration pré-établie
	    // // Rotors choisis
	    rotor1 = 3;
	    rotor2 = 1;
	    rotor3 = 5;
	    R1 = choixRotor(rotor1);
	    R2 = choixRotor(rotor2);
	    R3 = choixRotor(rotor3);
	    
	    // // Position initiale des rotors choisis
	    position1 = 'W';
	    position2 = 'D';
	    position3 = 'E';
	    decalage1 = indiceLettre(position1,R1);
	    R1 = positionInitialeRotor(R1,decalage1);
	    decalage2 = indiceLettre(position2,R2);
	    R2 = positionInitialeRotor(R2,decalage2);
	    decalage3 = indiceLettre(position3,R3);
	    R3 = positionInitialeRotor(R3,decalage3);
	    
	    // // Réflecteur choisi
	    choixRef = 'B';
	    refl = choixReflecteur(choixRef);
	    
	    // // Initialisation de la configuration du cablage de la machine par l'utilisateur
	    cables = "AVDEHOJKLSXQ";
	    
	    // // Message à tester 
	    message = "AKBAOKETGPVYHGWBSGSVUDTZEBNOXGFOBVYOJVTWFPIKC";
	}
	else{// Configuration choisie par l'utilisateur
	    // // Choix des 3 rotors (distincts) parmi les 5
	    // NB : On supposera que les numéros des 3 rotors sont bien compris entre 1 et 5 et qu'ils sont tous différents
	    println("Entrez le numéro du premier rotor choisi (1, 2, 3, 4, 5)");
	    rotor1 = readInt();
	    println("Entrez le numéro du deuxième rotor choisi");
	    rotor2 = readInt();
	    println("Entrez le numéro du troisième rotor choisi");
	    rotor3 = readInt();
	    
	    R1 = choixRotor(rotor1);
	    R2 = choixRotor(rotor2);
	    R3 = choixRotor(rotor3);
	    
	    // // Choix de la position initiale des rotors choisis
	    println("Entrez la position initiale du premier rotor choisi (A à Z)");
	    position1 = readChar();
	    println("Entrez la position initiale du deuxième rotor choisi (A à Z)");
	    position2 = readChar();
	    println("Entrez la position initiale du troisième rotor choisi (A à Z)");
	    position3 = readChar();
	    
	    decalage1 = indiceLettre(position1, R1);
	    R1 = positionInitialeRotor(R1, decalage1);
	    decalage2 = indiceLettre(position2, R2);
	    R2 = positionInitialeRotor(R2, decalage2);
	    decalage3 = indiceLettre(position3, R3);
	    R3 = positionInitialeRotor(R3, decalage3);
	    
	    // // Choix du réflecteur parmi les 2
	    println("Entrez la lettre du réflecteur choisi (A ou B)");
	    choixRef = readChar();
	    refl = choixReflecteur(choixRef);
	    
	    // // Initialisation de la configuration du cablage de la machine par l'utilisateur
	    cables = cablageInitial();
	    
	    // // Le message à coder
	    println("Entrez le message à coder :");
	    message = readString();
	    message = enMajuscule(message);
	}
	
	String messageDecode="";
	
	// Boucle principale du programme Enigma
	// -------------------------------------
	for (int tour = 0 ; tour < length(message) ; tour = tour+1){
	    // la boucle s'arrête quand on a codé chaque lettre du message
	    // 1. Récupération de la lettre courante dans le message à décoder
	    char lettreCourante = charAt(message, tour);
	    // 2. Passage par le câblage
	    lettreCourante = valeurApresCablageDeDepart(lettreCourante, cables); 
	    // 3. Passage par les 3 rotors (premier, deuxième, troisième)
	    lettreCourante = passageDansUnRotor(lettreCourante, R1);
	    lettreCourante = passageDansUnRotor(lettreCourante, R2);
	    lettreCourante = passageDansUnRotor(lettreCourante, R3);
	    // 4. Passage par le réflecteur
	    lettreCourante = passageDansLeReflecteur(lettreCourante, refl);
	    // 5. Passage par les 3 rotors (dans le sens inverse : troisième, deuxième, premier)
	    lettreCourante = inverseRotor(lettreCourante, R3);
	    lettreCourante = inverseRotor(lettreCourante, R2);
	    lettreCourante = inverseRotor(lettreCourante, R1);
	    // 6. Passage par le cablâge
	    lettreCourante = valeurApresCablageDeDepart(lettreCourante, cables);
	    
	    // 7. Ajout de la lettre décodée au message
	    messageDecode = messageDecode + lettreCourante;
	    
	    // 8. Préparation de l'itération suivante : décalage du premier rotor (à chaque fois); décalage du deuxième rotor si le premier a fait un tour complet (après 26 itérations) ; décalage du troisième rotor si le deuxième a fait un tour complet (après 26*26 itérations) 
	    // 8.1 Le rotor 1 tourne d'un rang vers la gauche après chaque lettre (donc à chaque tour)
	    R1 = decalageUnRang(R1);
	    // 8.2 Si le rotor 1 a effectué un tour (toutes les 26 itérations) alors le rotor 2 tourne d'un cran vers la gauche
	    if(tour % 26 == 0 && tour !=0){
		R2 = decalageUnRang(R2);
	    }
	    // 8.3 Si le rotor 2 a effectué un tour (toutes les 26*26 itérations) alors le rotor 3 tourne d'un cran vers la gauche 
	    if(tour % 676 == 0 && tour !=0){
		R3 = decalageUnRang(R3);
	    }
	}
	println("Le message décodé est : \n" + messageDecode);
    }	
}

