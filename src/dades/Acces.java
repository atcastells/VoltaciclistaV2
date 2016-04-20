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
	public int dniExistent (Informacio dades, String text){
		for(int i = 0; i < getCiclistesLength(dades); i++){
			if(getCiclistes(dades,i,0) != null){
				if(getCiclistes(dades,i,0).equalsIgnoreCase(text)){
					return i;
				}
			}
		}
		return -1;
	}

	public void inscripcioCiclista(Informacio dades,String[] arrayDades){
		int numEquip = Integer.parseInt(arrayDades[4]);
		int numCiclista = Integer.parseInt(arrayDades[6]);
		int posicio = ((numEquip) * dades.MAX_X_EQUIP) + (numCiclista);	//Determinem la posició del Ciclista a la Array
		/*Afegim dni*/
		setCiclistes(dades,arrayDades[0],posicio,0);
		/*Afegim nom*/
		setCiclistes(dades,arrayDades[1],posicio,1);
		/*Afegim Data Naixement*/
		setCiclistes(dades,arrayDades[2],posicio,2);
		/*Afegim Dorsal*/
		setCiclistes(dades,arrayDades[3],posicio,3);
		/*Afegim Equip*/
		setCiclistes(dades,arrayDades[5],posicio,4);
		/*Sumem +1 als membres del equip*/
		setMembresEquip(dades,numEquip,(getMembresEquip(dades,numEquip)+1));
	}

	public int numCiclistes(Informacio dades){
		int contador = 0;
		for(int i = 0; i < getMembresLength(dades); i++){
			contador = getMembresEquip(dades,i) + contador;
		}
		if(contador == 0){
			return 1;
		}
		return contador;
	}

	/* Funció que retorna el numero del ciclista, el seu nom, el seu DNI i el seu dorsal */
	public String ciclistes_toString(Informacio dades){
		String ciclistes = "";
		int n_ciclista = 1;
		for(int i = 0; i < getCiclistesLength(dades); i++) {
			if (getCiclistes(dades, i, 0) != null) {
				ciclistes +=  n_ciclista;
				ciclistes +="\t";
				ciclistes += getCiclistes(dades,i, 0);
				ciclistes +="\t";
				ciclistes += getCiclistes(dades,i, 1);
				ciclistes +="\t";
				ciclistes += getCiclistes(dades,i, 3);
				ciclistes += "\n";
				n_ciclista++;
			}
		}
		return ciclistes;
	}


}