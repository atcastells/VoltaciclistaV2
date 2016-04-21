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
									gui.funcioTaula(columnesEquip,acces.ciclistes_toString(dades));
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
								if(acces.numCiclistes(dades) > 0) {
									gui.funcioTaula(columnesEquip,acces.ciclistes_toString(dades));
									afegirTemps();
								}
								else{
									gui.imprimir("No hi han ciclistes inscrits\n");
								}

								break;
							case 2:
								if(acces.numCiclistes(dades) > 0) {
									gui.funcioTaula(columnesEquip,acces.ciclistes_toString(dades));
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
	void afegirTemps(){
		//Declaració de variables
		int ciclista = 0;
		int etapa = 0;
		int temps = 0;
		//Imprimim el llistat de jugadors


		return;
	}
	void imprimirTemps(){

	}

	void guanyadors(){

	}
	void remuneracions(){

	}


	/***************ARRAYS MENUS*****************/
	String[] menu = {"Gestió de inscripció de ciclistes","Gestió de la competició","Sortir"};
	String[] menuGestioCiclistes = {"Inscriure","Llistar","Tornar"};
	String[] menuCompeticio = {"Enregistrar temps","Temps etapes", "Informe de guanyadors", "Llistat de remuneracions","Tornar"};

	/*****************ARRAYS COLUMNES**************************/
	String[] columnesEquip = {"Codi equip", "Nom"};
	String[] columnesEtapes = {"Num. Etapa", "Població Inici","Població Final", "Tipus terreny"};
	String[] columnesCiclistes = {"Nº ciclista","DNI","Nom","Dorsal"};
}
