import java.util.Scanner;

import client.Client;

public class App {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("Escolha qual tipo de encadeamento a aplicação vai utilizar: ");       
        System.out.println("1 -> Endereçamento exterior");
        System.out.println("2 -> Endereçamento aberto");   
        System.out.print("Opção: ");
        int option = scan.nextInt();    
        System.out.println("-------------------------------------");
        System.out.println();
        var client = new Client(option);
        client.start();
    }
}
