import java.util.ArrayList;
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
        boolean repetirPartida = false;
        Personaje jugador1;

        do{
            System.out.println("Selecciones el tipo de jugador que será " + nombreJugador);
            System.out.println("Nivel: " + nivel);
            System.out.println("1 Arquero");
            System.out.println("2 Guerrero");
            System.out.println("3 Mago");

            opcion = scanner.next().charAt(0);

        } while (opcion != '1' && opcion != '2' && opcion != '3');

        jugador1 = crearJugador(opcion, nombreJugador);

        System.out.println(jugador1.identidad());
        System.out.println("Estas son tus habilidades");
        System.out.println(jugador1.mostrarHabilidades());

        Estadistica estadistica = new Estadistica();

        do {
            Personaje oponente = crearOponente();
            System.out.println("Te enfrentas a " + oponente.identidad());

            System.out.println("Empieza el juego, atacas primero");
            System.out.println("Nivel: " + nivel);

            do {
                System.out.println(jugador1.mostrarHabilidades());
                System.out.println("5: Rendirse");
                opcion = scanner.next().charAt(0);

                atacarOponente(opcion, jugador1, oponente, estadistica, scanner);

                if(opcion == '5'){
                    estadistica.registrarDerrota();
                    repetirPartida = false;
                    break;
                }

                System.out.println("Tu oponenten está atacando...");
                Thread.sleep(4000); //Se coloca un deley de 5 segundos para simular el ataque de loponente...
                oponenteAtaca(jugador1, oponente, estadistica, nivel);

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
   
            if (jugador1.getVidaActual() > 0) {
                if(opcion != '5'){ //Solo registra victoria, recompensa con oro y sube de nivel si el jugador no se rindió
                    estadistica.registrarVictoria();
                    nivel++;
                    jugador1.setOro(jugador1.getOro() + 50); // Se le da al jugador una cantidad de oro adicional por cada victoria
                }
                System.out.println("¿Desea jugar otra partida? (s/n)");
                char respuesta = scanner.next().charAt(0);
                if (respuesta == 's' || respuesta == 'S') {
                    repetirPartida = true;
                    jugador1.setVidaActual(jugador1.getVidaMaxima());
                    System.out.println("Tienes " + jugador1.getOro() + " Au de oro.");
                    System.out.println("¿Desea comprar algún item? (s/n)");
                    respuesta = scanner.next().charAt(0);
                    if (respuesta == 's' || respuesta == 'S')
                        agregarItem(jugador1, scanner);
                } else {
                    repetirPartida = false;
                }
            } else {
                estadistica.registrarDerrota();
                repetirPartida = false;
            }
        } while (repetirPartida);
        
        // Nueva linea
        System.out.println(estadistica);

        scanner.close();
    }

    //Método que permite agregar
    private static void agregarItem(Personaje jugador1, Scanner scanner){
        System.out.println("Le vendemos, le vendemos, qué le vendemos, baratico, lleve lleve... :-)");
        System.out.println("""
                1 Vitaminas. Precio: 100 Au
                2 Proteína. Precio 150 Au
                3 Bebida energética. Precio 200 Au
                4 Arroz blanco. Precio 400 Au
                5 Espinacas. Precio 700 Au
                """);
        
        ArrayList<Item> items = jugador1.getItem();
        char respuesta = scanner.next().charAt(0);
        switch (respuesta) {
            case '1':
                if (jugador1.getOro() >= 100){
                    items.add(new Item("Vitaminas", 100, 10));
                    jugador1.setOro(jugador1.getOro() - 100);
                }
                else
                    System.out.println("Lo siento, no te alcanza jaja, limpio.");
                break;
            case '2':
                if (jugador1.getOro() >= 150){
                    items.add(new Item("Proteína", 150, 15));
                    jugador1.setOro(jugador1.getOro() - 150);
                }
                else
                    System.out.println("Lo siento, no te alcanza jaja, limpio.");
                break;
            case '3':
                if (jugador1.getOro() >= 200){
                    items.add(new Item("Bebida energética", 200, 20));
                    jugador1.setOro(jugador1.getOro() - 200);
                }
                else
                    System.out.println("Lo siento, no te alcanza jaja, limpio.");
                break;
            case '4':
                if (jugador1.getOro() >= 400){
                    items.add(new Item("Arroz blanco", 400, 40));
                    jugador1.setOro(jugador1.getOro() - 400);
                }
                else
                    System.out.println("Lo siento, no te alcanza jaja, limpio.");
                break;
            case '5':
                if (jugador1.getOro() >= 700){
                    items.add(new Item("Espinacas", 700, 70));
                    jugador1.setOro(jugador1.getOro() - 700);
                }
                else
                    System.out.println("Lo siento, no te alcanza jaja, limpio.");
                break;
            default:
                break;
        }
        jugador1.setItem(items);
    }

    //Muestra los items que tiene el jugador disponibles
    private static int mostrarItems(Personaje jugador1, Scanner scanner){
        ArrayList<Item> items = jugador1.getItem();
        if (items.isEmpty()){
            System.out.println("No tienes items, compra algo en la tienda para mejorar tus habilidades.");
        }
        else{
            System.out.println("Puedes usar tus items:");
            int contador = 1;
            for (Item item : items) {
                System.out.println(contador +" - " + item.getNombre() + ": " + item.getPorcDannioCura() + "%");
                contador++;
            }
            System.out.println("N - No usar item");
        }
        char respuesta = scanner.next().charAt(0);
        if (respuesta == 'N' || respuesta == 'n')
            return 0;
        else if (respuesta > '0' && respuesta <= ('0' + items.size())){
            Item itemSeleccionado = items.get(respuesta - '1');
            items.remove(itemSeleccionado);
            jugador1.setItem(items);
            return itemSeleccionado.getPorcDannioCura();
        }
        else{
            System.out.println("Opción no válida, no se usará ningún item.");
            return 0;
        }
    }

    private static void atacarOponente(char opcion, Personaje jugador1, Personaje oponente, Estadistica estadistica, Scanner scanner) {
        int danioJugador;
        int danioExtra = 0;
        
        if(!jugador1.getItem().isEmpty())
            danioExtra =mostrarItems(jugador1, scanner);

        switch (opcion) {
            case '1':
                danioJugador = jugador1.atacar() + danioExtra;
                oponente.recibirAtaque(danioJugador);
                estadistica.agregarDanoInfligido(danioJugador);
                estadistica.registrarTurnoJugado();
                System.out.println(oponente.identidad());
                break;
            case '2':
                int vidaAntes = jugador1.getVidaActual();
                jugador1.recuperarVida(danioExtra);
                estadistica.registrarCuracion(jugador1.getVidaActual() - vidaAntes);
                estadistica.registrarTurnoJugado();
                System.out.println(jugador1.identidad());
                break;
            case '3':
                danioJugador = jugador1.atacar() + danioExtra;
                oponente.recibirAtaque(danioJugador);
                estadistica.agregarDanoInfligido(danioJugador);
                estadistica.registrarTurnoJugado();
                System.out.println(oponente.identidad());
                break;
            case '4':
                danioJugador = jugador1.atacar() + danioExtra;
                oponente.recibirAtaque(danioJugador);
                estadistica.agregarDanoInfligido(danioJugador);
                estadistica.registrarTurnoJugado();
                System.out.println(oponente.identidad());
                break;
            case '5':
                System.out.println("Que cobarde, erees una verguenza para el clan!");
                jugador1.getItem().clear(); // El jugador pierde todos sus items por rendirse;
                break;
            default:
                estadistica.registrarTurnoJugado();
                System.out.println("Jaja, perdió su turno, ahora prepárese para lo que viene...");
                break;                
        }
    }

    //El oponente ataca
    private static void oponenteAtaca(Personaje jugador1, Personaje oponente, Estadistica estadistica, int nivel) {
        int opcion;
        if(oponente.getVidaActual() < 3)
            opcion = '2';
        else
            opcion = (char)(((int) (Math.random() * 3) + 1) + '0');

        if(opcion == '1' || opcion == '3' || opcion == '4'){
            int danioOponente1;

            if(nivel <= 5) //Si el nivel es bajo el ataque del oponente es de acuerdo al nivel
                danioOponente1 = nivel;
            else // Ah pero si ya es un nivel mayor a 5 ahí si agarrese, porque viene ataque feroz.
                danioOponente1 = oponente.atacar(nivel);            
 
            jugador1.recibirAtaque(danioOponente1);
            estadistica.agregarDanoRecibido(danioOponente1);
            System.out.println(jugador1.identidad());
        }
        else if (opcion == '2'){
            int vidaAntesOponente = oponente.getVidaActual();
            oponente.recuperarVida(nivel);
            estadistica.registrarCuracion(oponente.getVidaActual() - vidaAntesOponente);
            System.out.println(oponente.identidad());
        }
    }

    //Crea un jugador dependiendo de la opción que elija el usuario
    private static Personaje crearJugador(char opcion, String nombreJugador) {
        Personaje jugador1 = null;
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
        jugador1.setOro(100); // Se le da al jugador una cantidad inicial de oro para comprar items
        jugador1.setItem(new ArrayList<Item>()); // Se inicializa la lista de items del jugador
        return jugador1;
    }

    //Crea un oponente de un tipo al azar
    private static Personaje crearOponente(){
        Personaje oponente = null;
        switch ((int) (Math.random() * 3) + 1) {
            case 1:
                oponente = new Arquero(generarNombreOponente(1));
                break;
        
            case 2:
                oponente = new Guerrero(generarNombreOponente(2));
                break;

            case 3:
                oponente = new Mago(generarNombreOponente(3));
                break;
            default:
                break;
        }
        return oponente;
    }

    public static String generarNombreOponente(int tipo) {
        String[] nombres;
        switch (tipo) {
            case 1:
                nombres = new String[] {
                    "Legolas",
                    "Robin Hood",
                    "Green Arrow",
                    "Katniss Everdeen",
                    "Hawkeye",
                    "Daryl Dixon",
                    "Merida",
                    "Artemis",
                    "Ashitaka",
                    "Flecha Verde",
                    "Flecha Roja",
                    "Flecha Negra",
                    "Flecha Blanca",
                    "Flecha Dorada",
                    "Flecha Plateada",
                    "Flecha de Fuego"
                };
                break;
        case 2:
                nombres = new String[] {
                    "Conan",
                    "Hércules",
                    "Aquiles",
                    "Beowulf",
                    "Thor",
                    "Ares",
                    "Kratos",
                    "Sansón",
                    "Hulk",
                    "Doomsday",
                    "Juggernaut",
                    "Berserker",
                    "Gladiador",
                    "Spartacus",
                    "Leonidas",
                    "Sirinfusio"
                };
                break;
                case 3:
                nombres = new String[] {
                    "Gandalf",
                    "Dumbledore",
                    "Merlin",
                    "Saruman",
                    "Sauron",
                    "Voldemort",
                    "Morgana",
                    "Elminster",
                    "Raistlin Majere",
                    "Harry",
                    "Doctor Strange",
                    "Scarlet",
                    "Rasputín",
                    "Circe",
                    "Mefisto",
                    "Hermione"
                };
            default:
                nombres = new String[] {
                    "Bruce",
                    "Hermione",
                    "Klark",
                    "Froddo",
                    "Merlin",
                    "Optimus",
                    "Xena",
                    "Harry",
                    "Merlina",
                    "Gandalf",
                    "Dumbledore",
                    "Sauron",
                    "Legolas",
                    "Aragorn",
                    "Gimli",
                    "Saruman"
                };
                break;
        };

        Random rand = new Random();
        // Elegimos un índice al azar entre 0 y la longitud del arreglo - 1
        int indice = rand.nextInt(nombres.length);
        
        return nombres[indice];
    }
}