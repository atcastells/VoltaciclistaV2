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

	public String getTempsEtapes(Informacio dades, int x, int y, int z){
		return dades.tempsEtapes[x][y][z];
	}
	public void setTempsEtapes(Informacio dades, String TempsEtapes,int x, int y, int z){
		dades.tempsEtapes[x][y][z] = TempsEtapes;
	}
	public int getMembresEquip(Informacio dades, int x){
		return dades.membres_equip[x];
	}
	public void setMembresEquip(Informacio dades, int x, int y) {
		dades.membres_equip[x] = y;
	}
	public int getEquipsLenght(Informacio dades){
		return dades.equips.length;
	}

	public int validarCodi(Informacio dades, String text){
		for(int i = 0; i < getEquipsLenght(dades);i++){
			if(getEquips(dades,i,0).equalsIgnoreCase(text)){
				return i;
			}
		}
		return -1;
	}

	public int equipPle(Informacio dades,int x){
		if(getMembresEquip(dades,x) < dades.MAX_X_EQUIP){
			return getMembresEquip(dades,x);
		}
		return -1;
	}


}