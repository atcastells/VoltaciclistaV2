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
			imprimir("\n");
		}
		while (2016 - anys > 99 && 2016 - anys < 16);
		/*Mesos*/
		do{
			mesos = readInt("Mes: ");
			imprimir("\n");
		}
		while (mesos <= 0 && mesos > 12);
		/*Dies*/
		do{
			dies = readInt("Dia: ");
			imprimir("\n");
		}
		while (dies > 31);

		data +=dies+"/"+mesos+"/"+anys;
		return data;
	}
	public String funcioDorsal(String nom, int num_inscripcio, String equip){
		String nom_return = "";

		for(int i=0;i<3;i++){
			nom_return += nom.charAt(i);
		}
		nom_return += (num_inscripcio+1);
		nom_return += equip;
		return nom_return.toUpperCase();
	}

	void ln(){
	imprimir("\n");
	};
}
