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
								/*INICI INSCRIPCIO CICLISTES*/

								/*Variables del ciclista*/
								String equip = "";
								String dni = "";
								String dataNaixement = "";
								String nom = "";

								/*Comprovació equips*/
									do{
										gui.imprimir("Introdueix el codi equip del Ciclista: ");
										equip = gui.readString();
									}
									while(acces.equipExistent(equip));

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


	String[] menu = {"Gestió de inscripció de ciclistes","Gestió de la competició","Sortir"};
	String[] menuGestioCiclistes = {"Inscriure","Llistar","Tornar"};
	String[] menuCompeticio = {"Enregistrar temps","Temps etapes", "Informe de guanyadors", "Llistat de remuneracions","Tornar"};
}
