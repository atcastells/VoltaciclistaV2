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
}
