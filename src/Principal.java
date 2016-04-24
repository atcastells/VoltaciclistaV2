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
            int controlMenuInformes = 0;
			gui.imprimir("##########\tMENÚ PRINCIPAL, SELECCIONA UNA OPCIÓ.\t##########");
			gui.ln();gui.ln();
			gui.imprimir(gui.funcioMenu(menu));
			gui.imprimir("Introdueix una opció del menú:  ");
			controlMenu = gui.readInt();
			gui.ln();gui.ln();
			switch (controlMenu){
				case 1:
					while (controlMenuCiclistes < menuGestioCiclistes.length){
						gui.imprimir("##########\tGESTIÓ DE CICLISTES.\t##########");
						gui.ln();gui.ln();
						gui.imprimir(gui.funcioMenu(menuGestioCiclistes));
						controlMenuCiclistes = 0;
                        gui.imprimir("Introdueix una opció del menú:  ");
						controlMenuCiclistes = gui.readInt();
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
										do{
                                            nom = gui.readString();
                                        }
                                        while (nom.length() < 1);
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
						gui.imprimir("Introdueix una opció del menú:  ");
						controlMenuCompeticio = gui.readInt();
						switch (controlMenuCompeticio){
                            case 1: //Equips
                                gui.funcioTaula(columnesEquip,acces.getEquips(dades));
                                break;
                            case 2: //Premis
                                gui.funcioTaula(columnesPremis,acces.getPremis(dades));
                                break;
							case 3: //Enregistrar Temps
								int etapa = 0;
								if(acces.numCiclistes(dades) > 0) {	//Si hi han ciclistes
                                     /*Seleccionem la etapa*/
									do{
										gui.funcioTaula(columnesEtapes,acces.getEtapes(dades));
										gui.imprimir("Selecciona una etapa(el nº de etapa): ");
										etapa = gui.readInt()-1;
									}
									while (!(etapa < acces.getEtapes(dades).length && !(etapa < 0)));
                                    /*Passem a afegir el temps a cada jugador en la etapa seleccionada*/
									afegirTemps(acces,dades,gui,etapa);
								}
								else{
									gui.imprimir("No hi han ciclistes inscrits\n");
								}

								break;
							case 4:
								if(acces.numCiclistes(dades) > 0) {
									gui.funcioTaula(columnesMostrarEtapes,acces.mostrarTempsEtapes(dades));
								}
								else{
									gui.imprimir("No hi han ciclistes inscrits\n");
								}
								break;
							case 5:
                                if(acces.numCiclistes(dades) > 0){
                                    while (controlMenuInformes < menuInformes.length){
                                        gui.imprimir("##########\tINFORMES.\t##########");
                                        gui.ln();gui.ln();
                                        gui.imprimir(gui.funcioMenu(menuInformes));
                                        controlMenuInformes = 0;
                                        gui.imprimir("Introdueix una opció del menu: ");
                                        controlMenuInformes = gui.readInt();
                                        switch (controlMenuInformes){
                                            case 1: //Informe Ciclista
                                                int ciclista = 0;
                                                int temps = 0;
                                                gui.funcioTaula(columnesCiclistes,acces.ciclistes_toString(dades));
                                                do{
                                                    gui.imprimir("Selecciona un Ciclista: ");
                                                    ciclista = gui.readInt();
                                                    for(int i = 0; i < acces.ciclistes_toString(dades).length;i++){
                                                        if(Integer.parseInt(acces.ciclistes_toString(dades)[i][0]) == ciclista){
                                                            ciclista = i;
                                                            gui.imprimir(i+"\n");
                                                        }
                                                    }
                                                }
                                                while (ciclista >= acces.ciclistes_toString(dades).length || ciclista < 0);
                                                gui.ln();
                                                gui.funcioTaula(columnesMostrarEtapes,acces.tempsCiclista(dades,ciclista,acces.mostrarTempsEtapes(dades)));
                                                gui.ln();
                                                gui.imprimir("Temps total: ");
                                                temps = acces.tempsTotal(dades,ciclista);
                                                gui.imprimir(gui.tempsToString(temps)+"\n");
                                                gui.imprimir("Mitja de temps per etapa: ");
                                                temps = acces.mitjaTemps(dades,ciclista);
                                                gui.imprimir(gui.tempsToString(temps)+"\n");
                                                gui.ln();
                                                break;
                                            case 2: //Informe Etapa
                                                int etapaInforme = 0;
                                                gui.funcioTaula(columnesEtapes,acces.getEtapes(dades));
                                                do{
                                                    gui.imprimir("Selecciona una etapa: ");
                                                    etapa = gui.readInt();
                                                }
                                                while (etapa >= acces.getEtapesLength(dades) || etapa < 0);
                                                gui.ln();
                                                gui.imprimir("Creant informe de temps de la etapa nº "+etapa+". ("+acces.getEtapes(dades,etapa,1)+" - "+acces.getEtapes(dades,etapa,2)+").\n");
                                                gui.enterContinue();
                                                gui.funcioTaula(columnesInformeEtapa,acces.informeEtapa(etapa,dades));
                                                break;
                                            case 3: //Guanyadors
                                                guanyadors(acces,gui,dades);
                                                break;
                                        }
                                        gui.enterContinue();
                                    }

                                }
                                else {
                                    gui.imprimir("No hi han ciclistes inscrits\n");
                                }
								break;
							case 6:
                                if(acces.numCiclistes(dades) > 0){
                                    /*Remuneracions premis*/
                                   gui.funcioTaula(columnesPremisCiclistes,remuneracions(acces,gui,dades));
                                }
                                else {
                                    gui.imprimir("No hi han ciclistes inscrits\n");
                                }
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
						gui.imprimir("Introdueix una opció del menú:  ");
						controlMenuUtilitats = gui.readInt();
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
			gui.imprimir("Insereix el temps (En segons) per al ciclista "+ciclistes[i][2]+" amb dorsal "+ciclistes[i][3]+" (Per sortir, escriu 'Sortir'): ");
			String sortida = gui.readString();
			if(sortida.equalsIgnoreCase("Sortir")){
				break;
			}
			else {
				temps = gui.checkInt(sortida);
			}
			acces.setTempsEtapes(dades,temps,posicioCiclista,etapa);
			gui.ln();
		}
		return;
	}

	void guanyadors(Acces acces,Biblioteca gui, Informacio dades){
        int total = 0;
        int ciclistaGuanyador = 0;
        for(int i = 0; i < acces.ciclistes_toString(dades).length;i++){
            if (acces.tempsTotal(dades,i) > total){
                total = acces.tempsTotal(dades,i);
                ciclistaGuanyador = i;
            }
        }
        gui.imprimir("El guanyador del premi 'Maillot Groc' es: "+acces.ciclistes_toString(dades)[ciclistaGuanyador][2]+" amb un temps total de "+gui.tempsToString(total)+".\n");
        total = 0;
        //Maillot Verd
        for(int i = 0; i < acces.ciclistes_toString(dades).length;i++){
            for(int j = 0; j < acces.getEtapesLength(dades);j++){
                if (acces.getEtapes(dades,j,3).equalsIgnoreCase("Alta Muntanya")){
                    if (acces.tempsTotal(dades,i,j) > total){
                        total = acces.tempsTotal(dades,i,j);
                        ciclistaGuanyador = i;
                    }
                }
            }
        }
        gui.imprimir("El guanyador del premi 'Maillot Verd' es: "+acces.ciclistes_toString(dades)[ciclistaGuanyador][2]+" amb un temps total de "+gui.tempsToString(total)+".\n");
        total = 0;
        //Maillot Blanc
        for(int i = 0; i < acces.ciclistes_toString(dades).length;i++){
            for(int j = 0; j < acces.getEtapesLength(dades);j++){
                if (acces.getEtapes(dades,j,3).equalsIgnoreCase("Muntanya")){
                    if (acces.tempsTotal(dades,i,j) > total){
                        total = acces.tempsTotal(dades,i,j);
                        ciclistaGuanyador = i;
                    }
                }
            }
        }
        gui.imprimir("El guanyador del premi 'Maillot Blanc' es: "+acces.ciclistes_toString(dades)[ciclistaGuanyador][2]+" amb un temps total de "+gui.tempsToString(total)+".\n");
        //Maillot Blau
        for(int i = 0; i < acces.ciclistes_toString(dades).length;i++){
            for(int j = 0; j < acces.getEtapesLength(dades);j++){
                if (acces.getEtapes(dades,j,3).equalsIgnoreCase("Plana")){
                    if (acces.tempsTotal(dades,i,j) > total){
                        total = acces.tempsTotal(dades,i,j);
                        ciclistaGuanyador = i;
                    }
                }
            }
        }
        gui.imprimir("El guanyador del premi 'Maillot Blau' es: "+acces.ciclistes_toString(dades)[ciclistaGuanyador][2]+" amb un temps total de "+gui.tempsToString(total)+".\n");
	}
	String[][] remuneracions(Acces acces,Biblioteca gui,Informacio dades){

        /*Etapes*/
        for(int h = 0; h < acces.ciclistes_toString(dades).length;h++){
            for(int i = 0; i < acces.getEtapesLength(dades);i++){
                if (!(acces.getTempsEtapes(dades,h,i) < 1)){
                    acces.setPremis(dades,h,acces.getPremis(dades,h)+500);
                }
            }
        }
        /*Premis*/
        /*Maillot Groc*/
        int total = 0;
        int ciclistaGuanyador = 0;
        for(int i = 0; i < acces.ciclistes_toString(dades).length;i++){
            if (acces.tempsTotal(dades,i) > total){
                total = acces.tempsTotal(dades,i);
                ciclistaGuanyador = i;
            }
        }
        acces.setPremis(dades,Integer.parseInt(acces.ciclistes_toString(dades)[ciclistaGuanyador][0]),acces.getPremis(dades,Integer.parseInt(acces.ciclistes_toString(dades)[ciclistaGuanyador][0]))+(int)acces.getImportPremi(dades,0));
        ciclistaGuanyador = 0;
        total = 0;
        //Maillot Verd
        for(int i = 0; i < acces.ciclistes_toString(dades).length;i++){
            for(int j = 0; j < acces.getEtapesLength(dades);j++){
                if (acces.getEtapes(dades,j,3).equalsIgnoreCase("Alta Muntanya")){
                    if (acces.tempsTotal(dades,i,j) > total){
                        total = acces.tempsTotal(dades,i,j);
                        ciclistaGuanyador = i;
                    }
                }
            }
        }
        acces.setPremis(dades,Integer.parseInt(acces.ciclistes_toString(dades)[ciclistaGuanyador][0]),acces.getPremis(dades,Integer.parseInt(acces.ciclistes_toString(dades)[ciclistaGuanyador][0]))+(int)acces.getImportPremi(dades,1));
        ciclistaGuanyador = 0;
        total = 0;
        //Maillot Blanc
        for(int i = 0; i < acces.ciclistes_toString(dades).length;i++){
            for(int j = 0; j < acces.getEtapesLength(dades);j++){
                if (acces.getEtapes(dades,j,3).equalsIgnoreCase("Muntanya")){
                    if (acces.tempsTotal(dades,i,j) > total){
                        total = acces.tempsTotal(dades,i,j);
                        ciclistaGuanyador = i;
                    }
                }
            }
        }
        acces.setPremis(dades,Integer.parseInt(acces.ciclistes_toString(dades)[ciclistaGuanyador][0]),acces.getPremis(dades,Integer.parseInt(acces.ciclistes_toString(dades)[ciclistaGuanyador][0]))+(int)acces.getImportPremi(dades,2));
        ciclistaGuanyador = 0;
        total = 0;
        for(int i = 0; i < acces.ciclistes_toString(dades).length;i++){
            for(int j = 0; j < acces.getEtapesLength(dades);j++){
                if (acces.getEtapes(dades,j,3).equalsIgnoreCase("Plana")){
                    if (acces.tempsTotal(dades,i,j) > total){
                        total = acces.tempsTotal(dades,i,j);
                        ciclistaGuanyador = i;
                    }
                }
            }
        }
        acces.setPremis(dades,Integer.parseInt(acces.ciclistes_toString(dades)[ciclistaGuanyador][0]),acces.getPremis(dades,Integer.parseInt(acces.ciclistes_toString(dades)[ciclistaGuanyador][0]))+(int)acces.getImportPremi(dades,3));
        ciclistaGuanyador = 0;
        total = 0;
        /*Creem la array on guardar les dades*/
        String[][] remuneracionsCiclistes = new String[acces.ciclistes_toString(dades).length][3];
        /*Emplenem la array*/
        for(int i = 0; i < remuneracionsCiclistes.length;i++){
            remuneracionsCiclistes[i][0] = acces.ciclistes_toString(dades)[i][2];
            remuneracionsCiclistes[i][1] = acces.ciclistes_toString(dades)[i][3];
            remuneracionsCiclistes[i][2] = acces.getPremis(dades,Integer.parseInt(acces.ciclistes_toString(dades)[i][0]))+"";
        }

        /*Ordenem la array*/
        String[] temp = new String[3];

        for(int i = 0;i < remuneracionsCiclistes.length;i++){
            for(int j = 0; j < remuneracionsCiclistes.length;j++){
                if(Integer.parseInt(remuneracionsCiclistes[i][2]) > Integer.parseInt(remuneracionsCiclistes[j][2])){
                    temp[0] = remuneracionsCiclistes[i][0];
                    temp[1] = remuneracionsCiclistes[i][1];
                    temp[2] = remuneracionsCiclistes[i][2];
                    remuneracionsCiclistes[i][0] = remuneracionsCiclistes[j][0];
                    remuneracionsCiclistes[i][1] = remuneracionsCiclistes[j][1];
                    remuneracionsCiclistes[i][2] = remuneracionsCiclistes[j][2];
                    remuneracionsCiclistes[j][0] = temp[0];
                    remuneracionsCiclistes[j][1] = temp[1];
                    remuneracionsCiclistes[j][2] = temp[2];
                    j--;
                }
            }
        }
        return remuneracionsCiclistes;
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
	String[] menuCompeticio = {"Llistar equips","Llistar premis","Enregistrar temps","Taula general de Temps", "Informes", "Llistat de remuneracions","Tornar"};
	String[] menuUtilitats = {"Carregar dades","Buidar llista","Tornar"};
    String[] menuInformes = {"Informe ciclista", "Informe etapa","Guanyadors","Sortir"};

	/*****************ARRAYS COLUMNES**************************/
	String[] inscrits = {"Num. de Ciclistes inscrits","Num. de equips inscrits","Max. ciclistes per equip"};
	String[] columnesEquip = {"Codi equip", "Nom"};
	String[] columnesEtapes = {"Num. Etapa", "Població Inici","Població Final", "Tipus terreny"};
	String[] columnesCiclistes = {"Nº ciclista","DNI","Nom","Dorsal"};
	String[] columnesMostrarEtapes ={"Ciclistes", "Etapa 1", "Etapa 2", "Etapa 3", "Etapa 4", "Etapa 5", "Etapa 6", "Etapa 7"};
    String[] columnesPremis = {"Premi","Patrocinador","Tipus premi"};
    String[] columnesInformeEtapa = {"Posició","Nom","Temps"};
    String[] columnesPremisCiclistes = {"Nom Ciclista", "Dorsal", "Import"};

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
