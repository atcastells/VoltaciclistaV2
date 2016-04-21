import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by acastells on 04/04/16.
 */
public class Biblioteca {
	void imprimir(String text){
		System.out.print(text);
	}
	void imprimir (int x){
		System.out.print(x);
	}
	String funcioMenu(String[] array){
	String menu = "";
		for (int i = 0; i < array.length; i++){
			menu += ("#"+(i+1)+"    "+array[i]+"\n");
		}
		return menu;
	}
	int readInt(String missatge) {
		Scanner sc = new Scanner(System.in);
		try {
			imprimir(missatge);
			return sc.nextInt();
		} catch (InputMismatchException e) {
			imprimir("Error d'entrada, introdueix una xifra!\n");
			return (readInt(missatge));
		}
	}
	String readString() {
		Scanner sc = new Scanner(System.in);
		String newString = sc.nextLine();
		return newString;
	}

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

	void ln(){
	imprimir("\n");
	};

	void funcioTaula(String[] rows, String[][] dades){
		int length = 0;
		int numRows = rows.length;
		int[] rowsLength = new int[numRows];
		String taula = "";
		String separacio = "    ";

		/**Calculem la mida màxima de la columna*/
		for(int i = 0; i < numRows;i++){
			for (int j = 0;j < dades.length;j++){
				if(dades[j][i].length() > length){
					length += dades[j][i].length();
				}
			}
			if (rows[i].length() > length){
				length += rows[i].length();
			}
			rowsLength[i] = length;
			length = 0;
		}

		/**Imprimim les columnes*/
		for (int i = 0;i < numRows; i++){
			taula += rows[i];
			for (int j = 0; j < (rowsLength[i] - rows[i].length());j++){
				taula+=" ";
			}
			taula+=separacio;
		}
		taula+="\n";
		/**Imprimim separació columnes/dades*/
		for(int i = 0; i < numRows;i++){
			for(int j = 0; j < rowsLength[i];j++){
				taula+="-";
			}
			taula+=separacio;
		}
		taula+="\n";
		/**Afegim les dades a la taula*/
		for(int i = 0; i < dades.length;i++){
			for(int j = 0; j < numRows;j++){
				taula+=dades[i][j];
				for(int k = 0;k < (rowsLength[j] - dades[i][j].length());k++){
					taula+=" ";
				}
				taula+=separacio;
			}
			taula+="\n";
		}
		/**Imprimim la taula*/
		imprimir(taula);
	}
}
