import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        System.out.println("Bienvenido al juego...!");
        System.out.println("Iniciando partida...");

        System.out.println("Escriba el nombre de su personaje");

        Scanner scanner = new Scanner(System.in);
        String nombreJugador = scanner.nextLine();
        char opcion;

        Personaje jugador1 = null;

        do{
        System.out.println("Selecciones el tipo de jugador que será " + nombreJugador);
        System.out.println("1 Arquero");
        System.out.println("2 Guerrero");
        System.out.println("3 Mago");

        opcion = scanner.next().charAt(0);

        } while (opcion != '1' && opcion != '2' && opcion != '3');

        switch (opcion) {
            case '1':
                jugador1 = new Arquero(nombreJugador);
                break;
        
            case '2':
                jugador1 = new Guerrero(nombreJugador);
                break;

            case '3':
                jugador1 = new Mago(nombreJugador);
                break;
            default:
                break;
        }

        System.out.println(jugador1.identidad());
        System.out.println("Estas son tus habilidades");
        System.out.println(jugador1.mostrarHabilidades());

        scanner.close();
    }
}
