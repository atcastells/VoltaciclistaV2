import dades.Acces;
import dades.Informacio;

import javax.sound.midi.MidiDevice;

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
            gui.ln();
			gui.imprimir(gui.funcioMenu(menu));
			controlMenu = gui.readInt("Introdueix una opció del menú: ");
			switch (controlMenu){
				case 1:
					while (controlMenuCiclistes < menuGestioCiclistes.length){
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
								
								//Equip Existeix
								equip = comprovacioEquip(acces,gui,dades);
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
					}
					break;
				case 2:
					while (controlMenuCompeticio < menuCompeticio.length){
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
									imprimirTemps();
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
					}
					break;
				case 3:
					if(acces.numCiclistes(dades) == 0){
						inserirCiclistes(dades,acces,gui);
					}
					else {
						gui.imprimir("Aquesta funció eliminara les dades existents i inicialitzarà el programa amb dades aleatories, continuar?(Escriu Si per continuar): ");
                        String continuar = "Si";
                        String opcio = gui.readString();
                        if (opcio.equalsIgnoreCase(continuar)){
                            buidaLlista(acces,dades);
                            inserirCiclistes(dades,acces,gui);
                        }
                        else {
                            break;
                        }
						gui.ln();

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
		while (!(acces.dniExistent(dades,dni) == -1));
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
            gui.imprimir("Insereix el temps (En segons) per al ciclista "+ciclistes[i][2]+" amb dorsal "+ciclistes[i][3]+": ");
            temps = gui.readInt("");
            acces.setTempsEtapes(dades,temps,posicioCiclista,etapa);
            gui.ln();
        }
		return;
	}
	void imprimirTemps(){

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
	String[] menu = {"Gestió de inscripció de ciclistes","Gestió de la competició","Carregar dades","Sortir"};
	String[] menuGestioCiclistes = {"Inscriure","Llistar","Tornar"};
	String[] menuCompeticio = {"Enregistrar temps","Temps etapes", "Informe de guanyadors", "Llistat de remuneracions","Tornar"};

	/*****************ARRAYS COLUMNES**************************/
    String[] inscrits = {"Num. de Ciclistes inscrits","Num. de equips inscrits","Max. ciclistes per equip"};
	String[] columnesEquip = {"Codi equip", "Nom"};
	String[] columnesEtapes = {"Num. Etapa", "Població Inici","Població Final", "Tipus terreny"};
	String[] columnesCiclistes = {"Nº ciclista","DNI","Nom","Dorsal"};

	/*****************FUNCIONS DE PROVA**************************/
	void inserirCiclistes(Informacio dades, Acces acces,Biblioteca gui){
		String[][]dadesCiclistes = {
				{"Aaron","47822563C","20/02/1989","ONZ"},
				{"Jose","47822563Z","20/03/1989","BIC"},
				{"Ruben","47822563Y","20/04/1989","FON"},
				{"Josep","47822563Z","20/05/1989","TNK"},
				{"Oscar","47822563A","21/02/1989","MTX"},
				{"Jesus","45822563C","20/11/1989","MVX"}};
		for(int i = 0; i < dadesCiclistes.length;i++){
			String nom = dadesCiclistes[i][0];
			String dni = dadesCiclistes[i][1];
			String dataNaixement = dadesCiclistes[i][2];
			String equip = dadesCiclistes[i][3];
			String dorsal = gui.funcioDorsal(nom,acces.getMembresEquip(dades,acces.numEquip(dades,equip)),equip);
			acces.inscripcioCiclista(dades,nom,dni,dataNaixement,equip,dorsal);
		}

	}
}
