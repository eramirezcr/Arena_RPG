import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenido al juego...!");
        System.out.println("Iniciando partida...");

        System.out.println("Escriba el nombre de su personaje");

        Scanner scanner = new Scanner(System.in);
        String nombreJugador = scanner.nextLine();
        char opcion;
        int nivel = 1;

        Personaje jugador1 = null;

        do{
            System.out.println("Selecciones el tipo de jugador que será " + nombreJugador);
            System.out.println("Nivel: " + nivel);
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

        Personaje oponente = crearOponente();
        System.out.println("Te enfrentas a " + oponente.identidad());

        System.out.println("Empieza el juego, atacas primero");

        do {
            System.out.println(jugador1.mostrarHabilidades());
            opcion = scanner.next().charAt(0);

            //TODO: Falta la opción de rendirse y hacer el atque con algún item, este incrementa el daño al oponente
            switch (opcion) {
                case '1':
                    oponente.recibirAtaque(jugador1.atacar());
                    System.out.println(oponente.identidad());
                    break;
                case '2':
                    jugador1.recuperarVida();
                    System.out.println(jugador1.identidad());
                    break;
                case '3':
                    oponente.recibirAtaque(jugador1.atacar());
                    System.out.println(oponente.identidad());
                    break;
                case '4':
                    oponente.recibirAtaque(jugador1.atacar());
                    System.out.println(oponente.identidad());
                    break;
                case '5':
                    System.out.println("Vaya cobarde!");
                    break;
                default:
                    System.out.println("Jaja, perdió su turno, ahora prepárese para lo que viene...");
                    break;                
            }
            
            System.out.println("Tu oponenten está atacando...");
            Thread.sleep(5000);
            //El oponente ataca
            if(oponente.getVidaActual() < 3)
                opcion = '2';
            else
                opcion = (char)(((int) (Math.random() * 3) + 1) + '0');

            switch (opcion) {
                case '1':
                    jugador1.recibirAtaque(oponente.atacar());
                    System.out.println(jugador1.identidad());
                    break;
                case '2':
                    oponente.recuperarVida();
                    System.out.println(oponente.identidad());
                    break;
                case '3':
                    jugador1.recibirAtaque(oponente.atacar());
                    System.out.println(jugador1.identidad());
                    break;
                case '4':
                    jugador1.recibirAtaque(oponente.atacar());
                    System.out.println(jugador1.identidad());
                    break;
                default:
                    System.out.println("Jaja, perdió su turno, ahora prepárese para lo que viene...");
                    break;                
            }

        } while (jugador1.getVidaActual() > 0 && oponente.getVidaActual() > 0);
        
        System.out.println(jugador1.identidad());
        if (jugador1.getVidaActual() > 0)
            System.out.println(jugador1.getNombre() + " sobrevivió.");
        else
            System.out.println(jugador1.getNombre() + " murió. RIP :-(");

        System.out.println(oponente.identidad());
        if (oponente.getVidaActual() > 0)
            System.out.println(oponente.getNombre() + " sobrevivió.");
        else
            System.out.println(oponente.getNombre() + " murió. RIP :-(");

        scanner.close();
    }

    //Crea un oponente de un tipo al azar
    private static Personaje crearOponente(){
        Personaje oponente = null;
        switch ((int) (Math.random() * 3) + 1) {
            case 1:
                oponente = new Arquero(generarNombreOponente());
                break;
        
            case 2:
                oponente = new Guerrero(generarNombreOponente());
                break;

            case 3:
                oponente = new Mago(generarNombreOponente());
                break;
            default:
                break;
        }
        return oponente;
    }

    public static String generarNombreOponente() {
        String[] nombres = {
            "Bruce",
            "Hermione",
            "Klark",
            "Froddo",
            "Merlin",
            "Optimus",
            "Xena",
            "Harry",
            "Merlina"
        };

        Random rand = new Random();
        // Elegimos un índice al azar entre 0 y la longitud del arreglo - 1
        int indice = rand.nextInt(nombres.length);
        
        return nombres[indice];
    }

}
