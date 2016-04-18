import dades.Acces;
import dades.Informacio;

/**
 * Created by acastells on 04/04/16.
 */
public class Principal {
	public static void main (String[] args){
		new Principal().inici();
	}

	void inici(){
		Biblioteca gui = new Biblioteca();
		Acces acces = new Acces();
		Informacio informacio = new Informacio();
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
								inscripcioCiclista(gui,acces,informacio);
								break;
							case 2:
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

	String[] inscripcioCiclista(Biblioteca gui,Acces acces, Informacio info){
		/*Variables del ciclista*/
		String equip = "";
		String dni = "";
		String dataNaixement = "";
		String nom = "";
		String[] dadesCiclista = new String[6];
		String dorsal = "";
		int indexEquip;
		int numDorsal;

		/*Comprobem si el equip existeix*/
			do{
				gui.imprimir("Introdueix el codi equip del Ciclista: ");
				equip = gui.readString();
			}
			while(acces.validarCodi(info,equip) >= 0);
			indexEquip = acces.validarCodi(info,equip);
		/*Comprobem si el equip esta ple*/
			if(acces.equipPle(info,indexEquip) == -1){
				gui.imprimir("El equip està ple.");
				return null;
			}
		else {
				numDorsal = acces.equipPle(info,indexEquip);
				/*DNI*/
				do{
					gui.imprimir("Introdueix el DNI del Ciclista: ");
					dni = gui.readString();
				}
				while (acces.dniExistent(info,dni) != -1);
				/*Nom*/
				gui.imprimir("Introdueix el nom del Ciclista: ");
				nom = gui.readString();
				/*DataNaixement*/
				dataNaixement = gui.funcioData();
			}

		dadesCiclista[0] = dni;
		dadesCiclista[1] = nom;
		dadesCiclista[2] = dataNaixement;
		dadesCiclista[3] = dorsal;
		dadesCiclista[4] = Integer.toString(indexEquip);
		dadesCiclista[5] = Integer.toString(numDorsal);

		return dadesCiclista;


	}
	String[] menu = {"Gestió de inscripció de ciclistes","Gestió de la competició","Sortir"};
	String[] menuGestioCiclistes = {"Inscriure","Llistar","Tornar"};
	String[] menuCompeticio = {"Enregistrar temps","Temps etapes", "Informe de guanyadors", "Llistat de remuneracions","Tornar"};
}
