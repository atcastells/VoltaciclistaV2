# Descripció:
      Programa per gestionar una volta ciclista, amb etapes, equips, premis i remuneracions predefinides.
      Podem inscriure ciclistes, afegir els temps de cada cilista i generar informes de premis.

##  Menu Principal

1. [Gestió de ciclistes](#gestioCiclistes)
    1. [Inscriure](#inscriure)
        * [Principal(Menú)](#inscriureMenu)
        * [Principal(Auxiliars)](#inscriureAuxiliars)
        * [Biblioteca](#inscriureBiblioteca)
        * [Accés](#inscriureAcces)
    2.  Llistar
2. Gestió de la competició.
    1. Enregistrar temps.
    2. Informe per Ciclista.
    3. Informe per Etapa.
    4. Informe de guanyadors.
    5. Llista de remuneracións a abonar a cada ciclista.

## TODO
1. Error equip ple.
    * Testejar
3. Funcio comprobar dni a BOOLEAN.
4. ~~Afegir control a funció dorsal per a noms curts (Si el nom es curt afegeix * al nom NOMES al dorsal).~~
5. ~~Crear funció per crear una taula amb les dades ordenades que se li envien.~~
6. Funció Ciclistes toString
    * ~~Index ciclistes de la array original~~
7. Crear funció per afegir temps
    * Seleccionar Etapa
        * Seleccionar Ciclista
            * Afegir Temps (en segons)
8. Crear funció per mostrar el temps de les etapes
    * Seleccionar Etapa
        * Mostrar taula de temps (HH:MM:SS) amb els Ciclistes
9. Crear funció per inserir dades de mostra
    1. ~~Test Inserir~~
        * Completar funció per inserir els 45 ciclistes
    2. Test Afegir Temps
10. Crear funció enterPerContinuar a la biblioteca

    
## CHANGELOG (20/04/16)
* Funció creació de taules
* Finalització Inserció

## CHANGELOG (21/04/16)
* Creació array de Ciclistes per a taula
* Fixes
    * Funció taula
    * Aumentar contador ciclistes
* Arreglat numCiclistes
    * Retornava 1 si habien 0 ciclistes
    
    
    
## Codi Font

### <a name="gestioCiclistes">Gestió Ciclistes
#### <a name="Inscriure">Inscriure
##### <a name="inscriureMenu">Principal (Menu)
```java
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
```
##### <a name="inscriureAuxiliars">Principal (Funcions Auxiliars)
```java
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
```
##### <a name="inscriureBiblioteca">Biblioteca
```java
	String funcioData(){
		String data = "";
		int dies;
		int mesos;
		int anys;
		/*Anys*/
		do{
			anys = readInt("Any: ");
			ln();
		}
		while (2016 - anys > 99 && 2016 - anys < 16);
		/*Mesos*/
		do{
			mesos = readInt("Mes: ");
			ln();
		}
		while (mesos <= 0 && mesos > 12);
		/*Dies*/
		do{
			dies = readInt("Dia: ");
			ln();
		}
		while (dies > 31);

		data +=dies+"/"+mesos+"/"+anys;
		return data;
	}
	public String funcioDorsal(String nom, int num_inscripcio, String equip){
		String nom_return = "";
		if(nom.length() == 1){
			for(int i=0;i<1;i++){
				nom_return += nom.charAt(i);
			}
			nom_return +="**";
			nom_return += (num_inscripcio+1);
			nom_return += equip;
			return nom_return.toUpperCase();
		}
		if(nom.length() ==2){
			for(int i=0;i<2;i++){
				nom_return += nom.charAt(i);
			}
			nom_return +="*";
			nom_return += (num_inscripcio+1);
			nom_return += equip;
			return nom_return.toUpperCase();
		}
		else{
			for(int i=0;i<3;i++){
				nom_return += nom.charAt(i);
			}
			nom_return += (num_inscripcio+1);
			nom_return += equip;
			return nom_return.toUpperCase();
		}

	}
```
##### <a name="inscriureAcces">Acces
```java
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
    	public int membresEquip(Informacio dades,int x){
        		if(getMembresEquip(dades,x) < dades.MAX_X_EQUIP){
        			return getMembresEquip(dades,x);
        		}
        		return -1;
        	}
        	public int numEquip(Informacio dades, String text){
            		for(int i = 0; i < getEquipsLength(dades);i++){
            			if(getEquips(dades,i,0).equalsIgnoreCase(text)){
            				return i;
            			}
            		}
            		return -1;
            	}
```