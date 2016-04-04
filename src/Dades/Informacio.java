package Dades;

/**
 * Classe on es guarda la informació del programa
 */
public class Informacio {

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

	protected String[][] etapes = {
			{"Etapa 1","Barcelona", "Girona","Plana"}
			,{"Etapa 2","Girona", "Berga", "Muntanya"}
			,{"Etapa 3","Berga", "Sort", "Alta muntanya"}
			,{"Etapa 4","Sort", "Balaguer", "Alta muntanya"}
			,{"Etapa 5","Balaguer", "Amposta", "Plana"}
			,{"Etapa 6","Amposta", "Manresa", "Plana"}
			,{"Etapa 7","Manresa", "Mataró", "Muntanya"}
	};
	protected double[] distanciaEtapa ={185.2, 191.8, 156.6, 188.4, 195.4, 194.1, 126.6};

	protected String[] tipusGeneralEtapa={"Plana","Alta Muntanya","Muntanya"};

	protected String dadesPremi[][]={{"Maillot groc","Tisset","General"},
			{"Maillot blanc","Rolax","Muntanya"},
			{"Maillot blau","Supermarx","Plana"},
			{"Maillot verd","BMV","Alta muntanya"}};

	protected double[] importPremi={9000, 4500, 3000, 5000};

	protected String [] [] Ciclistes = new String [equips.length * 5] [4];

	protected int indexCiclistes = 0; //Variable per controlar el num. de ciclistes.

	protected String [] [] []tempsEtapes = new String[Ciclistes.length][etapes.length][5]; //Array on es guardarà les dades dels ciclistes respecte a les etapes que fan.

}