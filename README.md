# Descripció:
      Programa per gestionar una volta ciclista, amb etapes, equips, premis i remuneracions predefinides.
      Podem inscriure ciclistes, afegir els temps de cada cilista i generar informes de premis.

##  Menu Principal

1. [Gestió de ciclistes](#gestioCiclistes)
    1. [Inscriure](#inscriure)
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
4. Afegir control a funció dorsal per a noms curts (Si el nom es curt afegeix * al nom NOMES al dorsal).
5. ~~Crear funció per crear una taula amb les dades ordenades que se li envien.~~
6. Funció Ciclistes toString
    * Index ciclistes de la array original
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
##### Principal (Menu)
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
##### Principal (Funcions Auxiliars)
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