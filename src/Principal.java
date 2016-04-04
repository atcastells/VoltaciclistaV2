import Dades.Acces;

/**
 * Created by acastells on 04/04/16.
 */
public class Principal {
	public static void main (String[] args){
		Biblioteca gui = new Biblioteca();
		Acces acces = new Acces();
		new Principal().inici();
	}

	void inici(){
		/*Cridem al menú*/
		int controlMenu = 0;
		int controlMenuCiclistes = 0;
		int controlMenuCompeticio = 0;
		while (controlMenu < menu.length){
			switch (controlMenu){
				case 1:
					while (controlMenuCiclistes < menuGestioCiclistes.length){
						switch (controlMenuCiclistes){
							case 1:
								break;
							case 2:
								break;
						}
					}
					break;
				case 2:
					while (controlMenuCompeticio < menuCompeticio.length){
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
