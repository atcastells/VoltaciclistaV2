package dades;
public class Informacio {

	/* Constant que indica el numero maxim de membres per equips */
	protected final int MAX_X_EQUIP = 5;
	/* Array que guarda el codi i nom de cada equip */
	protected String[][] equips = {
			{"ONZ", "ONZE"}
			,{"TNK", "Tinkof"}
			,{"BCH", "Biancho"}
			,{"BRW", "Bartoloworld"}
			,{"MVX", "Movixtar"}
			,{"BIC", "Bich"}
			,{"CFD", "Cofidos"}
			,{"FON", "Fono"}
			,{"MTX", "Matrix"}
	};
	/* Array que guarda les dades de cada etapa */
	protected String[][] etapes = {
			{"Etapa 1","Barcelona", "Girona","Plana"}
			,{"Etapa 2","Girona", "Berga", "Muntanya"}
			,{"Etapa 3","Berga", "Sort", "Alta muntanya"}
			,{"Etapa 4","Sort", "Balaguer", "Alta muntanya"}
			,{"Etapa 5","Balaguer", "Amposta", "Plana"}
			,{"Etapa 6","Amposta", "Manresa", "Plana"}
			,{"Etapa 7","Manresa", "Mataró", "Muntanya"}
	};
	/* Array que guarda la distancia a recorrer en km. Distancia posicio 0 = etapa posicio 0 */
	protected double[] distanciaEtapa ={185.2, 191.8, 156.6, 188.4, 195.4, 194.1, 126.6};

	/* Array que guarda el tipus d'etapa */
	protected String[] tipusGeneralEtapa={"Plana","Mitja Muntanya","Muntanya"};

	/* Array per a guardar les dades del premi que s'otorga al final de la competicio */
	protected String dadesPremi[][]={{"Maillot groc","Tisset","General"},
			{"Maillot blanc","Rolax","Muntanya"},
			{"Maillotblau","Supermarx","Plana"},
			{"Maillot verd","BMV","Alta muntanya"}};

	/* Array que guarda el import assignat a cada premi */
	protected double[] importPremi={9000, 4500, 3000, 5000};

	/* Array que guarda l'informació de registre dels ciclistes. Es guardara: el nom del ciclista, el codi del equip, el DNI, el nom i la data de naixement .

	* Posició 0: 	DNI
	* Posició 1:	Nom
	* Posició 2:	Data Naixement
	* Posició 3:	Codi Equip
	* Posició 4:	Dorsal
	* */
	protected String [][] Ciclistes = new String [equips.length*MAX_X_EQUIP] [5];

	/* Array per a guardar el registre de les dades de la volta. Es guardara: el nom del ciclista, el nº de la etapa, el temps en segons */
	protected int [][] tempsEtapes = new int[Ciclistes.length][etapes.length];

	/* Array que guarda el nº de membres que te cada equip */
	protected int [] membres_equip = {0, 0, 0, 0, 0, 0, 0, 0, 0};


}

