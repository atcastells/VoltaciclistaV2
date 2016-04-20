import dades.Acces;
import dades.Informacio;

/**
 * Versio 0.1
 *
 * Autors:  Aaron Castells, Jose Febrer, Ruben Serret, Jasmina Cubedo
 *
 * Descripció:
 *
 * Programa per gestionar la base de dades d'una volta ciclista, afegir jugadors, establir els temps i crear informes de
 * premis.
 *
 */
public class Principal {
	public static void main (String[] args){
		new Principal().inici();
	}

	void inici(){
		Biblioteca gui = new Biblioteca();
		Acces acces = new Acces();
		Informacio dades = new Informacio();
		/*Cridem al menú*/
		int controlMenu = 0;
		int controlMenuCiclistes = 0;
		int controlMenuCompeticio = 0;
		while (controlMenu < menu.length){
			gui.imprimir(gui.funcioMenu(menu));
			controlMenu = gui.readInt("Introdueix una opció del menú: ");
			switch (controlMenu){
				case 1:
					while (controlMenuCiclistes < menuGestioCiclistes.length){
						gui.imprimir(gui.funcioMenu(menuGestioCiclistes));
						controlMenuCiclistes = gui.readInt("Introdueix una opció del menú:  ");
						switch (controlMenuCiclistes){
							case 1:
								//Declaració de variables
								String equip = "";
								String dni = "";
								String dataNaixement = "";
								String nom = "";
								String dorsal = "";
								
								//Equip Existeix
								equip = comprovacioEquip(acces,gui,dades);
								//DNI Existeix
								dni = comprovacioDNI(acces,gui,dades);
								//Data Naixement
								dataNaixement = gui.funcioData();
								//Funcio NOM
								break;
							case 2:
								if(acces.numCiclistes(dades) > 0) {
									gui.imprimir(acces.ciclistes_toString(dades));
								}
								else{
									gui.imprimir("No hi han ciclistes inscrits\n");
								}
								break;
						}
					}
					break;
				case 2:
					while (controlMenuCompeticio < menuCompeticio.length){
						gui.imprimir(gui.funcioMenu(menuCompeticio));
						controlMenuCompeticio = gui.readInt("Introdueix una opció del menú:  ");
						switch (controlMenuCompeticio){
							case 1:
								//Si hi han Ciclistes
								afegirTemps();
								break;
							case 2:
								break;
							case 3:
								break;
							case 4:
								break;
						}
					}
					break;
			}
		}


	}
	/****************FUNCIONS AUXILIARS INSCRIPCIO***********************/
	String comprovacioEquip(Acces acces, Biblioteca gui,Informacio dades){
		String equip = "";
		boolean ajuda = false;
		do{
			if(ajuda){
				gui.imprimir("Aquests son els equips disponibles.\n");
				for (int i = 0;i < acces.getEquipsLength(dades);i++){
					gui.imprimir(acces.getEquips(dades,i,0)+"    "+acces.getEquips(dades,i,0)+"\n");
				}
			}
			gui.imprimir("Escriu el codi del equip: ");
			equip = gui.readString();
			ajuda = true;
			gui.ln();
		}
		while ((acces.numEquip(dades,equip) <0));
		return equip;
	}
	
	String comprovacioDNI(Acces acces,Biblioteca gui,Informacio dades){
		String dni = "";
		do{
			gui.imprimir("Escriu el DNI del ciclista: ");
			dni = gui.readString();
			gui.ln();
		}
		while (acces.dniExistent(dades,dni) == -1);
		return dni;
	}

	String[] inscripcioCiclista(Biblioteca gui,Acces acces, Informacio info){
		/*Variables del ciclista*/
		String equip = "";
		String dni = "";
		String dataNaixement = "";
		String nom = "";
		String[] dadesCiclista = new String[7];
		String dorsal = "";
		int indexEquip;
		int numDorsal;

		/*Comprobem si el equip existeix*/
			do{
				gui.imprimir("Introdueix el codi equip del Ciclista: ");
				equip = gui.readString();
			}
			while(acces.numEquip(info,equip) < 0);
			indexEquip = acces.numEquip(info,equip);
		/*Comprobem si el equip esta ple*/
			if(acces.membresEquip(info,indexEquip) == -1){
				gui.imprimir("El equip està ple.");
				return null;
			}
		else {
				numDorsal = acces.membresEquip(info,indexEquip);
				/*DNI*/
				if (acces.numCiclistes(info) > 0){
					do{
						gui.imprimir("Introdueix el DNI del Ciclista: ");
						dni = gui.readString();
					}
					while (acces.dniExistent(info,dni) >= 0);
				}
				else {
					gui.imprimir("Introdueix el DNI del Ciclista: ");
					dni = gui.readString();
				}
				/*Nom*/
				gui.imprimir("Introdueix el nom del Ciclista:");
				nom = gui.readString();
				/*DataNaixement*/
				dataNaixement = gui.funcioData();
				/*Dorsal*/
				dorsal = gui.funcioDorsal(nom,numDorsal,equip);
			}

		dadesCiclista[0] = dni;
		dadesCiclista[1] = nom;
		dadesCiclista[2] = dataNaixement;
		dadesCiclista[3] = dorsal;
		dadesCiclista[4] = Integer.toString(indexEquip);
		dadesCiclista[5] = equip;
		dadesCiclista[6] = Integer.toString(numDorsal);

		return dadesCiclista;


	}

	/*Funcio per afegir el temps a les etapes*/
	void afegirTemps(){
		//Declaració de variables
		int ciclista = 0;
		int etapa = 0;
		int temps = 0;
		//Imprimim el llistat de jugadors


		return;
	}

	String[] menu = {"Gestió de inscripció de ciclistes","Gestió de la competició","Sortir"};
	String[] menuGestioCiclistes = {"Inscriure","Llistar","Tornar"};
	String[] menuCompeticio = {"Enregistrar temps","Temps etapes", "Informe de guanyadors", "Llistat de remuneracions","Tornar"};
}
