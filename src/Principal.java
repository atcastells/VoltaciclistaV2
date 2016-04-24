import dades.Acces;
import dades.Informacio;

import javax.sound.midi.MidiDevice;
import java.util.Random;

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
        /*Iniciem la array de temps amb valors per defecte*/
		acces.initTempsEtapa(dades);
		/*Cridem al menú*/
		int controlMenu = 0;
		while (controlMenu < menu.length){
			gui.ln();
			gui.funcioTaula(inscrits,informacioInscrits(acces,dades));
			gui.ln();
			int controlMenuCiclistes = 0;
			int controlMenuCompeticio = 0;
			int controlMenuUtilitats = 0;
			gui.imprimir("##########\tMENÚ PRINCIPAL, SELECCIONA UNA OPCIÓ.\t##########");
			gui.ln();gui.ln();
			gui.imprimir(gui.funcioMenu(menu));
			controlMenu = gui.readInt("Introdueix una opció del menú: ");
			gui.ln();gui.ln();
			switch (controlMenu){
				case 1:
					while (controlMenuCiclistes < menuGestioCiclistes.length){
						gui.imprimir("##########\tGESTIÓ DE CICLISTES.\t##########");
						gui.ln();gui.ln();
						gui.imprimir(gui.funcioMenu(menuGestioCiclistes));
						controlMenuCiclistes = 0;
						controlMenuCiclistes = gui.readInt("Introdueix una opció del menú:  ");
						switch (controlMenuCiclistes){
							case 1:
								//Declaració de variables
								String equip = "";
								String dni = "";
								String dataNaixement = "";
								String nom = "";
								String dorsal = "";
								boolean inscripcioTancada = acces.numCiclistes(dades) == acces.getEquipsLength(dades) * dades.MAX_X_EQUIP;

								/*Si es pot inscriure mes ciclistes*/

								if(!inscripcioTancada){
									//Equip Existeix
									equip = comprovacioEquip(acces,gui,dades);

								/*Si el equip no està ple*/
									if(acces.getMembresEquip(dades,acces.numEquip(dades,equip)) < dades.MAX_X_EQUIP){
										//DNI Existeix
										dni = comprovacioDNI(acces,gui,dades);
										//Data Naixement
										dataNaixement = gui.funcioData();
										//Nom
										gui.imprimir("Escriu el nom del ciclista: ");
										nom = gui.readString();
										gui.ln();
										//Dorsal
										dorsal = gui.funcioDorsal(nom,acces.membresEquip(dades,acces.numEquip(dades,equip)),equip);
										//Enviem les dades per inserir
										acces.inscripcioCiclista(dades,nom,dni,dataNaixement,equip,dorsal);
									}
									else {
										gui.imprimir("El equip seleccionat està ple.\n");
									}
								}
								else {
									gui.imprimir("La inscripció a nous ciclistes està tancada.\n");
								}
								break;

							case 2:
								if(acces.numCiclistes(dades) > 0) {
									gui.funcioTaula(columnesCiclistes,acces.ciclistes_toString(dades));
								}
								else{
									gui.imprimir("No hi han ciclistes inscrits\n");
								}
								break;
						}
						gui.enterContinue();
					}
					break;
				case 2:
					while (controlMenuCompeticio < menuCompeticio.length){
						gui.imprimir("##########\tGESTIÓ DE LA COMPETICIÓ.\t##########");
						gui.ln();gui.ln();
						gui.imprimir(gui.funcioMenu(menuCompeticio));
						controlMenuCompeticio = 0;
						controlMenuCompeticio = gui.readInt("Introdueix una opció del menú:  ");
						switch (controlMenuCompeticio){
							case 1:
								/*Declaració de variables*/
								int etapa = 0;
								if(acces.numCiclistes(dades) > 0) {	//Si hi han ciclistes
                                     /*Seleccionem la etapa*/
									do{
										gui.funcioTaula(columnesEtapes,acces.getEtapes(dades));
										gui.imprimir("Selecciona una etapa(el nº de etapa): ");
										etapa = gui.readInt("")-1;
									}
									while (!(etapa < acces.getEtapes(dades).length && !(etapa < 0)));
                                    /*Passem a afegir el temps a cada jugador en la etapa seleccionada*/
									afegirTemps(acces,dades,gui,etapa);
								}
								else{
									gui.imprimir("No hi han ciclistes inscrits\n");
								}

								break;
							case 2:
								if(acces.numCiclistes(dades) > 0) {
									gui.funcioTaula(columnesMostrarEtapes,acces.mostrarTempsEtapes(dades));
								}
								else{
									gui.imprimir("No hi han ciclistes inscrits\n");
								}
								break;
							case 3:
								guanyadors();
								break;
							case 4:
								remuneracions();
								break;
						}
						gui.enterContinue();
					}
					break;
				case 3:
					while (controlMenuUtilitats < menuUtilitats.length){
						gui.imprimir("##########\tUTILITATS.\t##########");
						gui.ln();gui.ln();
						gui.imprimir(gui.funcioMenu(menuUtilitats));
						controlMenuUtilitats = 0;
						controlMenuUtilitats = gui.readInt("Introdueix una opció del menú:  ");
						switch (controlMenuUtilitats){
							case 1:
									gui.imprimir("Aquesta funció eliminara les dades existents i inicialitzarà el programa amb dades aleatories, continuar?(Escriu Si per continuar): ");
									String continuar = "Si";
									String opcio = gui.readString();
									if (opcio.equalsIgnoreCase(continuar)){
										buidaLlista(acces,dades);
										inserirCiclistes(dades,acces,gui);
										emplenarTemps(dades,acces,gui);
									}
								break;
							case 2:
								gui.imprimir("Aquesta funció eliminara les dades existents, continuar?(Escriu Si per continuar): ");
								continuar = "Si";
								opcio = gui.readString();
								if (opcio.equalsIgnoreCase(continuar)){
									buidaLlista(acces,dades);
									acces.initTempsEtapa(dades);
								}
								break;
						}
						gui.enterContinue();
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
				gui.ln();
				gui.funcioTaula(columnesEquip,acces.getEquips(dades));
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
		while (!(acces.dniExistent(dades,dni) == false));
		return dni;
	}

	/****************FUNCIONS AUXILIARS COMPETICIÓ***********************/

	/*Funcio per afegir el temps a les etapes*/
	void afegirTemps(Acces acces,Informacio dades,Biblioteca gui,int x){
		//Declaració de variables
		int etapa = x;
		int temps = 0;
		int posicioCiclista;
		String[][] ciclistes = acces.ciclistes_toString(dades);

		//Inserim el temps dels ciclistes
		for (int i = 0; i < ciclistes.length; i++){
			posicioCiclista = Integer.parseInt(ciclistes[i][0]);
			gui.imprimir("Insereix el temps (En segons) per al ciclista "+ciclistes[i][2]+" amb dorsal "+ciclistes[i][3]+" : ");
			temps = gui.readInt("");
			acces.setTempsEtapes(dades,temps,posicioCiclista,etapa);
			gui.ln();
		}
		return;
	}

	void guanyadors(){

	}
	void remuneracions(){

	}

	/*******************FUNCIONS AUXILIARS********/
	void buidaLlista(Acces acces,Informacio dades){
		for(int i = 0; i < acces.getEquipsLength(dades);i++){
			acces.setMembresEquip(dades,i,0);
		}
	}

	String[][] informacioInscrits(Acces acces, Informacio dades){
		String[][] informacio = new String[1][inscrits.length];
		informacio[0][0] = acces.numCiclistes(dades)+"";
		informacio[0][1] = acces.getEquipsLength(dades)+"";
		informacio[0][2] = 5+"";
		return informacio;
	}
	/***************ARRAYS MENUS*****************/
	String[] menu = {"Gestió de inscripció de ciclistes","Gestió de la competició","Utilitats","Sortir"};
	String[] menuGestioCiclistes = {"Inscriure","Llistar","Tornar"};
	String[] menuCompeticio = {"Enregistrar temps","Temps etapes", "Informe de guanyadors", "Llistat de remuneracions","Tornar"};
	String[] menuUtilitats = {"Carregar dades","Buidar llista","Tornar"};

	/*****************ARRAYS COLUMNES**************************/
	String[] inscrits = {"Num. de Ciclistes inscrits","Num. de equips inscrits","Max. ciclistes per equip"};
	String[] columnesEquip = {"Codi equip", "Nom"};
	String[] columnesEtapes = {"Num. Etapa", "Població Inici","Població Final", "Tipus terreny"};
	String[] columnesCiclistes = {"Nº ciclista","DNI","Nom","Dorsal"};
	String[] columnesMostrarEtapes ={"Ciclistes", "Etapa 1", "Etapa 2", "Etapa 3", "Etapa 4", "Etapa 5", "Etapa 6", "Etapa 7"};

	/*****************FUNCIONS DE PROVA**************************/
	void inserirCiclistes(Informacio dades, Acces acces,Biblioteca gui){
		String[][]dadesCiclistes = new String[acces.getCiclistesLength(dades)][5];
		int numEquip = 0;
		int numCiclistes = 0;
		for(int j = 0; j < acces.getEquipsLength(dades);j++){
			for(int k = 0; k < 5;k++){
				dadesCiclistes[(numEquip*5)+numCiclistes][0] = "Ciclista"+acces.getEquips(dades,numEquip,0)+k;
				dadesCiclistes[(numEquip*5)+numCiclistes][1] = (j*k+k)+j+k+"5434C";
				dadesCiclistes[(numEquip*5)+numCiclistes][2] = "01/01/1989";
				dadesCiclistes[(numEquip*5)+numCiclistes][3] = acces.getEquips(dades,j,0);
				numCiclistes++;
			}
			numEquip++;
			numCiclistes = 0;
		}
		for(int i = 0; i < dadesCiclistes.length;i++){
			String nom = dadesCiclistes[i][0];
			String dni = dadesCiclistes[i][1];
			String dataNaixement = dadesCiclistes[i][2];
			String equip = dadesCiclistes[i][3];
			String dorsal = gui.funcioDorsal(nom,acces.getMembresEquip(dades,acces.numEquip(dades,equip)),equip);
			acces.inscripcioCiclista(dades,nom,dni,dataNaixement,equip,dorsal);
		}

	}

	void emplenarTemps(Informacio dades, Acces acces,Biblioteca gui){
		Random rd = new Random();
		int min = 3000;
		int max = 5000;
		for(int i = 0;i < acces.getCiclistesLength(dades);i++){
			for(int j = 0; j < acces.getEtapes(dades).length;j++){
				acces.setTempsEtapes(dades,rd.nextInt((max - min) + 1)+min,i,j);
			}
		}
	}
}
