package dades;

/**
 * Classe on s'accedeix desde el programa principal per interactuar amb la informació.
 */
public class Acces {

	public String getEquips(Informacio dades, int x, int y){
		return dades.equips[x][y];
	}
	public void setEquips(Informacio dades,String equips, int n, int m){
		dades.equips[n][m] = equips;
	}
	public String getEtapes(Informacio dades,int x, int y){
		return dades.etapes[x][y];
	}
	public void setEtapes(Informacio dades,String etapes, int x, int y){
		dades.etapes[x][y] = etapes;
	}
	public double getDistancia(Informacio dades,int x){
		return dades.distanciaEtapa[x];
	}
	public void setDistancia(Informacio dades,Double distancia, int x) {
		dades.distanciaEtapa[x] = distancia;
	}
	public String getTipusEtapa(Informacio dades,int x) {
		return dades.tipusGeneralEtapa[x];
	}
	public void setTipusEtapa(Informacio dades,String TipusEtapa, int x) {
		dades.tipusGeneralEtapa[x] = TipusEtapa;
	}
	public String getdadesPremis(Informacio dades,int x, int y) {
		return dades.dadesPremi[x][y];
	}
	public void setdadesPremis(Informacio dades,String dadesPremi,int x, int y) {
		dades.dadesPremi[x][y] = dadesPremi;
	}
	public double getImportPremi(Informacio dades,int x){
		return dades.importPremi[x];
	}
	public String getCiclistes(Informacio dades,int x, int y){
		return dades.Ciclistes[x][y];
	}
	public void setCiclistes(Informacio dades,String Ciclistes, int x, int y){
		dades.Ciclistes[x][y] = Ciclistes;
	}

	public int getTempsEtapes(Informacio dades, int x, int y){
		return dades.tempsEtapes[x][y];
	}
	public void setTempsEtapes(Informacio dades, int TempsEtapes,int x, int y){
		dades.tempsEtapes[x][y] = TempsEtapes;
	}
	public int getMembresEquip(Informacio dades, int x){
		return dades.membres_equip[x];
	}
	public void setMembresEquip(Informacio dades, int x, int y) {
		dades.membres_equip[x] = y;
	}
	public int getEquipsLength(Informacio dades){
		return dades.equips.length;
	}
	public int getCiclistesLength(Informacio dades){
		return dades.Ciclistes.length;
	}

	public int getMembresLength (Informacio dades){
		return dades.membres_equip.length;
	}

	public String[][] getEquips(Informacio dades){
		return dades.equips;
	}
	public String[][] getEtapes(Informacio dades){
		return dades.etapes;
	}
	public String[][] getCiclistes(Informacio dades){
		return dades.Ciclistes;
	}
	public int numEquip(Informacio dades, String text){
		for(int i = 0; i < getEquipsLength(dades);i++){
			if(getEquips(dades,i,0).equalsIgnoreCase(text)){
				return i;
			}
		}
		return -1;
	}
	public int membresEquip(Informacio dades,int x){
		if(getMembresEquip(dades,x) < dades.MAX_X_EQUIP){
			return getMembresEquip(dades,x);
		}
		return -1;
	}
	public boolean dniExistent (Informacio dades, String text){
		for(int i = 0; i < getCiclistesLength(dades); i++){
			if(getCiclistes(dades,i,0) != null){
				if(getCiclistes(dades,i,0).equalsIgnoreCase(text)){
					return true;
				}
			}
		}
		return false;
	}

	public void inscripcioCiclista(Informacio dades, String nom, String dni, String dataNaixement, String equip, String dorsal){
		int posicioArray = (numEquip(dades,equip) * dades.MAX_X_EQUIP) + (Integer.parseInt(dorsal.charAt(3)+"")-1);	//Posició del ciclista a la array
		/***************INSERIM DADES A LA ARRAY*****************/
		setCiclistes(dades,dni,posicioArray,0);
		setCiclistes(dades,nom,posicioArray,1);
		setCiclistes(dades,dataNaixement,posicioArray,2);
		setCiclistes(dades,equip,posicioArray,3);
		setCiclistes(dades,dorsal,posicioArray,4);

		/****************AUMENTEM EL Nº DE CICLISTES***********************/
		setMembresEquip(dades,numEquip(dades,equip),getMembresEquip(dades,numEquip(dades,equip))+1);
	}

	public int numCiclistes(Informacio dades){
		int contador = 0;
		for(int i = 0; i < getMembresLength(dades); i++){
			contador = getMembresEquip(dades,i) + contador;
		}
		if(contador == 0){
			return contador;
		}
		return contador;
	}

	/** Funció que retorna el numero del ciclista, el seu nom, el seu DNI i el seu dorsal */
	public String[][] ciclistes_toString(Informacio dades) {
		int contador = 0; //Contador on inscriure els ciclistes a la array local

		String ciclistes[][] = new String[numCiclistes(dades)][4];

		for (int i = 0; i < getCiclistesLength(dades) && contador <= numCiclistes(dades); i++) {
			if (getCiclistes(dades, i, 0) != null) {
				ciclistes[contador][0] = i + "";
				ciclistes[contador][1] = getCiclistes(dades, i, 0);
				ciclistes[contador][2] = getCiclistes(dades, i, 1);
				ciclistes[contador][3] = getCiclistes(dades, i, 4);
				contador++;
			}
		}
		return ciclistes;
	}

	/***************INIT**************************/
	public void initTempsEtapa(Informacio dades){
		for(int i = 0; i < getCiclistesLength(dades);i++){
			for(int j = 0; j < getEtapes(dades).length;j++){
				setTempsEtapes(dades,0,i,j);
			}
		}
	}
	public int[][] mostrarTempsEtapes (Informacio dades){
		int etapes[][] = new int[numCiclistes(dades)][getEtapes(dades).length];
		for(int i = 0; i<numCiclistes(dades);i++){
			if (getCiclistes(dades, i, 0) != null) {
				for (int y = 0; y < getEtapes(dades).length; y++) {
					etapes[i][y] = getTempsEtapes(dades,i,y);
				}
			}
		}
		return etapes;
	}
}