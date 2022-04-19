import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class Taller0 {
    public static void main(String[] args) throws Exception {
        // Jugador
        int[] ListaEstado = new int[1000];
        String[] Users = new String[1000];
        String[] Passwords = new String[1000];
        int[] ListaHP = new int[1000];
        int[] ListaAD = new int[1000];
        int[] ListaDF = new int[1000];
        int[] ListaSP = new int[1000];
        int[] ListaCantHechizos = new int[1000];
        int[] ListaXP = new int[1000];

        // TOP 10
        String[] UserTop10 = new String[100];
        int[] ListaXPTop10 = new int[100];

        // Hechizo
        String[] Hechizos = new String[1000];
        int[] adHechizo = new int[1000];

        // Enemigo
        String[] Enemigos = new String[1000];
        int[] hpEnemigo = new int[1000];
        int[] adEnemigo = new int[1000];
        String[] Clase = new String[1000];
        int[] spEnemigo = new int[1000];

        int cantJ = cargarJugadores(Users, Passwords, ListaHP, ListaAD, ListaDF, ListaSP, ListaCantHechizos, ListaXP,
                UserTop10, ListaXPTop10);
        int cantH = cargarHechizos(Hechizos, adHechizo);
        int[][] matrizHJ = new int[cantJ][cantH];
        int cantE = cargarEnemigos(Enemigos, hpEnemigo, adEnemigo, Clase, spEnemigo);
        HechizosJugador(Hechizos, Users, matrizHJ, cantJ, cantH, ListaCantHechizos);
        LogIn(Users, Passwords, ListaEstado, ListaHP, ListaAD, ListaDF, ListaSP, ListaCantHechizos, ListaXP, matrizHJ,
                cantJ, Hechizos, adHechizo, cantH, Enemigos, hpEnemigo, adEnemigo, Clase, spEnemigo, UserTop10,
                ListaXPTop10);
        salir(Users, Passwords, ListaEstado, ListaHP, ListaAD, ListaDF, ListaSP, ListaCantHechizos, ListaXP, matrizHJ,
                cantJ, Hechizos, adHechizo, cantH, cantE, Enemigos, hpEnemigo, adEnemigo, Clase, spEnemigo);
    }

    /**
     * Function to charge players
     * 
     * @param Users             Is the list with the names of the player
     * @param Passwords         Is the list with the password of the player
     * @param ListaHP           Is the list with the health points of the player
     * @param ListaAD           Is the list with the attack of the player
     * @param ListaDF           Is the list with the defence of the player
     * @param ListaSP           Is the list with the speed of the player
     * @param ListaCantHechizos Is the list with the number of player spells
     * @param ListaXP           Is the list with the experience of the player
     * @param UserTop10         Is the list with the names of the player for to top
     *                          10
     * @param ListaXPTop10      Is the list with the experence of the player for to
     *                          top 10
     * @return Returns a numbers of the players
     * @throws IOException catch any error that has a data
     */
    public static int cargarJugadores(String[] Users, String[] Passwords, int[] ListaHP, int[] ListaAD, int[] ListaDF,
            int[] ListaSP, int[] ListaCantHechizos, int[] ListaXP, String[] UserTop10, int[] ListaXPTop10)
            throws IOException {
        int cantJ = 0;
        Scanner arch = new Scanner(new File("Jugadores.txt"));
        while (arch.hasNextLine()) {
            String line = arch.nextLine();
            String[] data = line.split(",");
            String nombre = data[0];
            String pass = data[1];
            int HP = Integer.parseInt(data[2]);
            int AD = Integer.parseInt(data[3]);
            int DF = Integer.parseInt(data[4]);
            int SP = Integer.parseInt(data[5]);
            int cantListaCantHechizos = Integer.parseInt(data[6]);
            int XP = Integer.parseInt(data[7]);
            int indexJ = index(Users, nombre, cantJ);

            if (indexJ == -1) {
                Users[cantJ] = nombre;
                Passwords[cantJ] = pass;
                ListaHP[cantJ] = HP;
                ListaAD[cantJ] = AD;
                ListaDF[cantJ] = DF;
                ListaSP[cantJ] = SP;
                ListaCantHechizos[cantJ] = cantListaCantHechizos;
                ListaXP[cantJ] = XP;

                UserTop10[cantJ] = nombre;
                ListaXPTop10[cantJ] = XP;
                cantJ++;
            }
        }
        arch.close();
        return cantJ;
    }

    /**
     * Function to charge spells
     * 
     * @param Hechizos  It is the list with the names of the spells
     * @param adHechizo It’s the list of spell damage
     * @return Returns a numbers of spells
     * @throws IOException catch any error that has a data
     */
    public static int cargarHechizos(String[] Hechizos, int[] adHechizo) throws IOException {
        int cant = 0;
        Scanner arch = new Scanner(new File("Hechizos.txt"));
        while (arch.hasNextLine()) {
            String line = arch.nextLine();
            String[] data = line.split(",");
            String nombre = data[0];
            int AD = Integer.parseInt(data[1]);
            int indexH = index(Hechizos, nombre, cant);
            if (indexH == -1) {
                Hechizos[cant] = nombre;
                adHechizo[cant] = AD;
                cant++;
            }
        }
        arch.close();
        return cant;
    }

    /**
     * Function to charge spells
     * 
     * @param Enemigos  It is the list with the names of the enemies
     * @param hpEnemigo It is the list with the health points of the enemies
     * @param adEnemigo It is the list with the attacks of the enemies
     * @param Clase     It is the list with the class of the enemies
     * @param spEnemigo It is the list with the speed of the enemies
     * @return Returns a numbers of enemies
     * @throws IOException catch any error that has a data
     */
    public static int cargarEnemigos(String[] Enemigos, int[] hpEnemigo, int[] adEnemigo, String[] Clase,
            int[] spEnemigo) throws IOException {
        int cant = 0;
        Scanner arch = new Scanner(new File("Enemigos.txt"));
        while (arch.hasNextLine()) {
            String line = arch.nextLine();
            String[] data = line.split(",");
            String nombre = data[0];
            int HP = Integer.parseInt(data[1]);
            int AD = Integer.parseInt(data[2]);
            String Class = data[3];
            int SP = Integer.parseInt(data[4]);
            int indexE = index(Enemigos, nombre, cant);
            if (indexE == -1) {
                Enemigos[cant] = nombre;
                hpEnemigo[cant] = HP;
                adEnemigo[cant] = AD;
                Clase[cant] = Class;
                spEnemigo[cant] = SP;
                cant++;
            }
        }
        arch.close();
        return cant;
    }

    /**
     * Function to charge enemies
     * 
     * @param Hechizos          Is the list with the names of the spells
     * @param Users             Is the list with the names of the player
     * @param matrizHJ          It is the matrix where the spells possessed by the
     *                          player are saved
     * @param cantJ             Is the number of players
     * @param cantH             Is the number of spells
     * @param ListaCantHechizos Is the list with the number of player spells
     * @throws IOException catch any error that has a data
     */
    public static void HechizosJugador(String[] Hechizos, String[] Users, int[][] matrizHJ, int cantJ, int cantH,
            int[] ListaCantHechizos) throws IOException {
        Scanner arch = new Scanner(new File("HechizosJugadores.txt"));
        while (arch.hasNextLine()) {
            String line = arch.nextLine();
            String[] data = line.split(",");
            String nombre = data[0];
            String hechizo = data[1];
            int indexJ = index(Users, nombre, cantJ);
            int indexH = index(Hechizos, hechizo, cantH);
            if (indexJ != -1 && indexH != -1) {
                matrizHJ[indexJ][indexH] = 1;

            }
        }
        arch.close();
    }

    /**
     * function to search for a position
     * 
     * @param lista    list to which we will search
     * @param nombre   name to search
     * @param cantidad total quantity
     * @return Returns the position of the object
     */
    public static int index(String[] lista, String nombre, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            if (lista[i].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Function to generate a menu
     * 
     * @param Users             Is the list with the names of the player
     * @param Passwords         Is the list with the passwords of the player
     * @param ListaEstado       Is the list with the names of the player
     * @param ListaHP           Is the list with the health points of the player
     * @param ListaAD           Is the list with the attack of the player
     * @param ListaDF           Is the list with the defence of the player
     * @param ListaSP           Is the list with the speed of the player
     * @param ListaCantHechizos Is the list with the number of spells of the player
     * @param ListaXP           Is the list with the experience of the player
     * @param matrizHJ          It is the matrix where the spells possessed by the
     *                          player are saved
     * @param cantJ             Is the number of players
     * @param Hechizos          It is the list with the names of the spells
     * @param adHechizo         It’s the list of spell damage
     * @param cantH             It's a number of spells
     * @param Enemigos          It is the list with the names of the enemies
     * @param hpEnemigo         It is the list with the health points of the enemies
     * @param adEnemigo         It is the list with the attacks of the enemies
     * @param Clase             It is the list with the class of the enemies
     * @param spEnemigo         It is the list with the speed of the enemies
     * @param UserTop10         Is the list with the names of the player for to top
     *                          10
     * @param ListaXPTop10      Is the list with the experence of the player for to
     *                          top 10
     */
    public static void LogIn(String[] Users, String[] Passwords, int[] ListaEstado, int[] ListaHP, int[] ListaAD,
            int[] ListaDF, int[] ListaSP, int[] ListaCantHechizos, int[] ListaXP, int[][] matrizHJ, int cantJ,
            String[] Hechizos, int[] adHechizo, int cantH, String[] Enemigos, int[] hpEnemigo, int[] adEnemigo,
            String[] Clase, int[] spEnemigo, String[] UserTop10, int[] ListaXPTop10) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese su nombre y contraseña para iniciar sesión.");
        System.out.println("Ingrese f para terminar el proceso.");
        System.out.println("Si se desea registrar, escriba 'r' ");
        System.out.println("Ingrese su nombre: ");
        String User = scan.nextLine();
        while (!User.equals("f")) {
            int indexLogin = index(Users, User, cantJ); // 2
            if (indexLogin != -1) {
                System.out.println("Contraseña: ");
                String pass = scan.nextLine();
                int indexPass = index(Passwords, pass, cantJ); // 2
                if (indexPass != -1) {
                    if (indexLogin == indexPass) { // 2==2
                        System.out.println("--------------MENU----------------");
                        System.out.println("[1] Pelear contra un enemigo");
                        System.out.println("[2] Aprender hechizo");
                        System.out.println("[3] Ver estadisticas");
                        System.out.println("[4] Ver estadisticas de hechizos ");
                        System.out.println("[5] Ver Top 10 ");
                        System.out.println("[6] Salir ");
                        System.out.println("----------------------------------");

                        String option = scan.nextLine();
                        while (!option.equals("6")) {
                            if (option.equals("1")) {
                                Pvp(Users, Passwords, ListaHP, ListaAD, ListaDF, ListaSP, ListaCantHechizos, ListaXP,
                                        User, cantJ, Enemigos, hpEnemigo, adEnemigo, Clase, spEnemigo);
                                break;
                            } else if (option.equals("2")) {
                                aprenderHechizo(ListaCantHechizos, Hechizos, ListaXP, matrizHJ,
                                        indexLogin, cantH, Users);
                                break;
                            } else if (option.equals("3")) {
                                System.out.println(obtenerEstadisticas(Users, ListaHP, ListaAD, ListaDF, ListaSP,
                                        ListaCantHechizos, ListaXP, indexLogin));
                                break;
                            } else if (option.equals("4")) {
                                System.out.println(
                                        obtenerHechizos(indexLogin, Hechizos, Users, ListaXPTop10, cantH, matrizHJ));
                                break;
                            } else if (option.equals("5")) {
                                top10(UserTop10, ListaXPTop10, cantJ);
                                for (int i = 0; i < 10; i++) {
                                    System.out.println(
                                            "Usuario" + (i + 1) + ":" + UserTop10[i] + "||" + "Xp" + ListaXPTop10[i]);
                                }
                                break;

                            }
                        }
                        break;
                    }
                } else {
                    System.out.println("Contraseña incorrecta, inicie sesion otra vez.");
                }
            } else {
                if (User.equals("r")) {
                    System.out.println("Registrese");
                }
                if (User.equals("ADMIN")) {
                    System.out.println("MENU ADMIN");
                } else {
                    System.out.println("Usuario no encontrado.");
                }
            }

            if (User.equals("r")) {
                System.out.println("Ingrese su usuario: ");
                String userNew = scan.nextLine();
                System.out.println("Ingrese su contraseña: ");
                String passNew = scan.nextLine();
                System.out.println("Ingrese sus puntos de vida: ");
                int hpNew = Integer.parseInt(scan.nextLine());
                System.out.println("Ingrese su ad(ataque): ");
                int adNew = Integer.parseInt(scan.nextLine());
                ;
                System.out.println("Ingrese su df(defensa): ");
                int defNew = Integer.parseInt(scan.nextLine());
                System.out.println("Ingrese su sp(velocidad)");
                int spNew = Integer.parseInt(scan.nextLine());
                System.out.println("Ingrese su cantidad de hechizos");
                int hechizosCantNew = Integer.parseInt(scan.nextLine());
                String a = " ";
                for (int i = 0; i < hechizosCantNew; i++) {
                    System.out.println("Ingrese el nombre del hechizo :");
                    String nombreHechizo = scan.nextLine();
                    if (i < hechizosCantNew - 1) {
                        a += nombreHechizo + ",";
                    } else {
                        a += nombreHechizo;
                    }
                }

                HechizoJugador("\n" + userNew, a);

                System.out.println("Ingrese su exp(experiencia)");
                int expNew = Integer.parseInt(scan.nextLine());
                writerData("\n" + userNew, passNew, hpNew, adNew, defNew, spNew, hechizosCantNew, expNew);
                cantJ++;
            }
            if (User.equals("ADMIN")) {
                System.out.println("Contraseña: ");
                String passAd = scan.nextLine();
                if (passAd.equals("Patata19")) {
                    System.out.println("---------------MENU ADMIN---------------");
                    System.out.println("[1] Eliminar un jugador");
                    System.out.println("[2] Agregar enemigos");
                    System.out.println("[3] Agregar Hechizos");
                    System.out.println("[4] Ver estadisticas de los jugadores ");
                    System.out.println("[5] Salir ");
                    System.out.println("----------------------------------------");
                    String option = scan.nextLine();

                    while (!option.equals("5")) {
                        if (option.equals("1")) {
                            System.out.println("Ingrese nombre a buscar: ");
                            String nombreBuscar = scan.nextLine();
                            int indexBuscar = index(Users, nombreBuscar, cantJ);
                            if (indexBuscar != -1) {
                                ListaEstado[indexBuscar] = 1;

                            } else {
                                System.out.println("El nombre ingresado no se encuentra");
                            }

                        } else if (option.equals("2")) {
                            System.out.println("Ingrese Nombre Enemigo: ");
                            String nombreNew = scan.nextLine();
                            System.out.println("Ingrese Hp de Enemigo: ");
                            int hpNew = Integer.parseInt(scan.nextLine());
                            System.out.println("Ingrese AD de Enemigo: ");
                            int adNew = Integer.parseInt(scan.nextLine());
                            System.out.println("Ingrese Clase Enemigo: ");
                            String ClassNew = scan.nextLine();
                            System.out.println("Ingrese Velocidad(SP) de Enemigo: ");
                            int spNew = Integer.parseInt(scan.nextLine());
                            agregarEnemigo("\n" + nombreNew, hpNew, adNew, ClassNew, spNew);
                            break;

                        } else if (option.equals("3")) {
                            System.out.println("Ingrese Nombre Hechizo: ");
                            String hechizoNew = scan.nextLine();
                            System.out.println("Ingrese Poder de Hechizo: ");
                            int poderNew = Integer.parseInt(scan.nextLine());
                            agregarHechizo("\n" + hechizoNew, poderNew);
                            cantH++;
                            break;

                        } else if (option.equals("4")) {
                            System.out.println(obtenerEstadisticasJugadores(Users, ListaHP, ListaAD, ListaDF, ListaSP,
                                    ListaCantHechizos, ListaXP, cantJ));
                            break;
                        }
                        break;
                    }
                    break;
                }
                break;
            }

            System.out.println("Ingrese su nombre: ");
            User = scan.nextLine();
        }
        scan.close();
    }

    /**
     * function to fight between user versus user or user versus enemy
     * 
     * @param Users             Is the list with the names of the player
     * @param Passwords         list with the password of the users
     * @param ListaHP           user life list
     * @param ListaAD           user attack list
     * @param ListaDF           user defense list
     * @param ListaSP           user speed list
     * @param ListaCantHechizos list number of user spells
     * @param ListaXP           list list containing user experience
     * @param User              username to search for pvp
     * @param cantJ             Users quantity
     * @param Enemigos          list with the number of enemies
     * @param hpEnemigo         enemy life list
     * @param adEnemigo         enemy attack list
     * @param Clase             enemy class list
     * @param spEnemigo         enemy speed list
     */
    public static void Pvp(String[] Users, String[] Passwords, int[] ListaHP, int[] ListaAD, int[] ListaDF,
            int[] ListaSP, int[] ListaCantHechizos, int[] ListaXP, String User, int cantJ,
            String[] Enemigos, int[] hpEnemigo, int[] adEnemigo, String[] Clase, int[] spEnemigo) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random(); // <--------
        int JugadorA = index(Users, User, cantJ);
        System.out.println("Ingrese el tipo de pelea: 'JcE' o 'JcJ' ");
        String option = scan.nextLine();
        if (option == "JcE") {

        } else if (option == "Jcj") {
            int JugadorB = random.nextInt(cantJ);
            if (Users[JugadorA] != Users[JugadorB]) {
                System.out.println((Users[JugadorA] + "V/S" + Users[JugadorB]));
                if (ListaSP[JugadorA] > ListaSP[JugadorB]) {
                    System.out.println("Empieza: " + Users[JugadorA]);

                } else {
                    System.out.println("Empieza: " + Users[JugadorB]);

                }
            }
        } else {
            System.out.println("La opcion ingresada no existe");

        }
        scan.close();
    }

    /**
     * function to learn a spell randomly
     * 
     * @param ListaCantHechizos Is the number of spells of the player
     * @param Hechizos          Is the list of spells
     * @param ListaXP           list containing the users experience
     * @param matrizHJ          It is the matrix where the spells possessed by the
     *                          player are saved
     * @param indexLogin        is a Player position
     * @param cantH             Is the number of spells
     * @param Users             Is the list with the names of the player
     */
    public static void aprenderHechizo(int[] ListaCantHechizos, String[] Hechizos, int[] ListaXP, int[][] matrizHJ,
            int indexLogin, int cantH, String[] Users) {
        String aux = "";
        Random random = new Random();
        int resta = ListaXP[indexLogin] - 1000;
        if (resta >= 0) {
            for (int i = 0; i < cantH; i++) {
                int HechizoRd = random.nextInt(cantH);
                if (Hechizos[HechizoRd] != Hechizos[i]) {
                    aux = Hechizos[HechizoRd];
                    break;
                }
            }
            System.out.println("Felicidades"
                    + Users[indexLogin] + "Aprendiste el hechizo " + aux);
            ListaCantHechizos[indexLogin] += 1;
        } else {
            System.out.println("No puedes aprender un hechizo, tienes " + resta + " de experiencia.");
            System.out.println("Puedes obtener experiencia peleando contra enemigos.");
        }
    }

    /**
     * function that writes in the txt to new users
     * 
     * @param nombre                Is the name of the player to register
     * @param pass                  Is the password of the player to register
     * @param HP                    Is the health points of the player to register
     * @param AD                    Is the attack of the player to register
     * @param DF                    Is the defence of the player to register
     * @param SP                    Is the speed of the player to register
     * @param cantListaCantHechizos Is the number of spells of the player to
     *                              register
     * @param XP                    Is the experience of the player to register
     */
    public static void writerData(String nombre, String pass, int HP, int AD, int DF, int SP, int cantListaCantHechizos,
            int XP) {
        try {
            BufferedWriter writes = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("Jugadores.txt", true)));
            writes.write(nombre + "," + pass + "," + HP + "," + AD + "," + DF + "," + SP + "," + cantListaCantHechizos
                    + "," + XP);
            System.out.println("Ya se encuentra registrado.");
            writes.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    /**
     * function that adds spell
     * 
     * @param nombre Is the name of the spell to add
     * @param AD     Is the spell damage to add
     */
    public static void agregarHechizo(String nombre, int AD) {
        try {
            BufferedWriter writes = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("Hechizos.txt", true)));
            writes.write(nombre + "," + AD);
            System.out.println("Hechizo agregado");
            writes.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * function that adds spells to the users list
     * 
     * @param nombre        list to contains usernames
     * @param nombreHechizo list to contains names spells
     */
    public static void HechizoJugador(String nombre, String nombreHechizo) {
        try {
            BufferedWriter writes = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("HechizosJugadores.txt")));
            writes.write(nombre + "," + nombreHechizo);
            writes.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * function that adds enemies to the enemies list
     * 
     * @param nombre Is the name of the enemy
     * @param HP     Is the health points of the enemy
     * @param AD     Is the attack of the enemy
     * @param Class  Is the class of the enemy
     * @param SP     Is the speed of the enemy
     */
    public static void agregarEnemigo(String nombre, int HP, int AD, String Class, int SP) {
        try {
            BufferedWriter writes = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("Enemigos.txt", true)));
            writes.write(nombre + "," + HP + "," + AD + "," + Class + "," + SP);
            System.out.println("Enemigo agregado");
            writes.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * function that shows the spells
     * 
     * @param indexLogin        is a Player position
     * @param Hechizos          It’s the system’s spell list
     * @param Users             It’s the system’s Users list
     * @param ListaCantHechizos It’s the player’s spell count list
     * @param cantH             is the number of spells in the system
     * @param matrizHJ          It is the matrix where the spells possessed by the
     *                          player are saved
     * @return Returns a string
     */
    public static String obtenerHechizos(int indexLogin, String[] Hechizos, String[] Users, int[] ListaCantHechizos,
            int cantH, int[][] matrizHJ) {
        String text = "";
        text += "Usuario: " + Users[indexLogin] + " tienes: ";
        for (int i = 0; i < cantH; i++) {
            System.out.println("i :" + i);
            if (matrizHJ[indexLogin][i] == 1) {
                if (i < cantH - 1) {
                    text += Hechizos[i] + ",";
                } else {
                    text += Hechizos[i];
                }
            }
        }
        return text;
    }

    /**
     * function that shows the statistics
     * 
     * @param Users             It’s the system’s Users list
     * @param ListaHP           It’s the system’s Healths points list
     * @param ListaAD           It’s the system’s Attacks list
     * @param ListaDF           It’s the system’s Defence list
     * @param ListaSP           It’s the system’s Speed list
     * @param ListaCantHechizos It’s the system’s number of spells list
     * @param ListaXP           It’s the system’s Experience list
     * @param indexLogin        is a Player position
     * @return Returns a string
     */
    public static String obtenerEstadisticas(String[] Users, int[] ListaHP, int[] ListaAD, int[] ListaDF,
            int[] ListaSP, int[] ListaCantHechizos, int[] ListaXP, int indexLogin) {
        String text = "";
        text += "Usuario: " + Users[indexLogin] + " HP:" + ListaHP[indexLogin] + " Ataque:" + ListaAD[indexLogin]
                + " Defensa:" + ListaDF[indexLogin]
                + " Velocidad:" + ListaSP[indexLogin] + " N° Hechizos:" + ListaCantHechizos[indexLogin] + " XP:"
                + ListaXP[indexLogin];
        return text;
    }

    /**
     * function that shows the statistics users
     * 
     * @param Users             list containing users
     * @param ListaHP           list containing life(hp)
     * @param ListaAD           list containing attack
     * @param ListaDF           list containing defense
     * @param ListaSP           list containing speed
     * @param ListaCantHechizos list containing number of spells
     * @param ListaXP           list with the amount of experience
     * @param cantJ             list with the number of users
     * @return returns a string
     */
    public static String obtenerEstadisticasJugadores(String[] Users, int[] ListaHP, int[] ListaAD, int[] ListaDF,
            int[] ListaSP, int[] ListaCantHechizos, int[] ListaXP, int cantJ) {
        String text = "";
        for (int i = 0; i < cantJ; i++) {
            text += "Usuario: " + Users[i] + " HP: " + ListaHP[i] + " Ataque: " + ListaAD[i] + " Defensa: " + ListaDF[i]
                    + " Velocidad: " + ListaSP[i] + " N° Hechizos: " + ListaCantHechizos[i] + " XP: " + ListaXP[i]
                    + "\n";
        }
        return text;

    }

    /**
     * function that will show the top 10 user experience
     * 
     * @param UserTop10    It is the list with the names of the top 10
     * @param ListaXPTop10 It is the list with the experience of the top 10
     * @param cantJ        Is the number of players
     */
    public static void top10(String[] UserTop10, int[] ListaXPTop10, int cantJ) {
        for (int i = 0; i < cantJ; i++) {
            for (int j = i; j < cantJ; j++) {
                if (ListaXPTop10[i] < ListaXPTop10[j]) {

                    int aux = ListaXPTop10[i];
                    ListaXPTop10[i] = ListaXPTop10[j];
                    ListaXPTop10[j] = aux;

                    String aux2 = UserTop10[i];
                    UserTop10[i] = UserTop10[j];
                    UserTop10[j] = aux2;
                }
            }
        }
    }

    /**
     * function to write over the txt
     * 
     * @param Users             list containing users
     * @param Passwords         list containing passwords
     * @param ListaEstado       list containing states
     * @param ListaHP           list containing life hp
     * @param ListaAD           list containing attack
     * @param ListaDF           list containing defense
     * @param ListaSP           list containing speed
     * @param ListaCantHechizos list that has the number of spells
     * @param ListaXP           list you have the amount of experience
     * @param matrizHJ          matrix that has the spells 1 if you have them 0 if
     *                          you don't
     * @param cantJ             number of total users
     * @param Hechizos          list containing users' spells
     * @param adHechizo         list containing the amount of ad or damage of the
     *                          spell
     * @param cantH             number of total spells
     * @param cantE             number of total enemys
     * @param Enemigos          list containing enemys
     * @param hpEnemigo         list with the amount of life of the enemy
     * @param adEnemigo         list with the amount of enemy damage
     * @param Clase             list with the class of the enemy
     * @param spEnemigo         list with enemy speed
     * @throws IOException catch any error that has a data
     */
    public static void salir(String[] Users, String[] Passwords, int[] ListaEstado, int[] ListaHP, int[] ListaAD,
            int[] ListaDF, int[] ListaSP, int[] ListaCantHechizos, int[] ListaXP, int[][] matrizHJ, int cantJ,
            String[] Hechizos, int[] adHechizo, int cantH, int cantE, String[] Enemigos, int[] hpEnemigo,
            int[] adEnemigo, String[] Clase, int[] spEnemigo) throws IOException {
        String arch = "Jugadores.txt";
        FileWriter file = new FileWriter(arch);
        PrintWriter escritura = new PrintWriter(file);
        for (int i = 0; i < cantJ; i++) {
            if (ListaEstado[i] == 0) {
                escritura.println(Users[i] + "," + Passwords[i] + "," + ListaHP[i] + "," + ListaAD[i] + "," + ListaDF[i]
                        + "," + ListaSP[i] + "," + ListaCantHechizos[i] + "," + ListaXP[i]);
            }
        }
        String arch2 = "Hechizos.txt";//
        FileWriter file2 = new FileWriter(arch2);
        PrintWriter escritura2 = new PrintWriter(file2);
        for (int j = 0; j < cantH; j++) {
            escritura2.println(Hechizos[j] + "," + adHechizo[j]);
        }

        String arch3 = "HechizosJugadores.txt";
        FileWriter file3 = new FileWriter(arch3);
        PrintWriter escritura3 = new PrintWriter(file3);
        String text = "";
        for (int x = 0; x < cantJ; x++) {
            text += Users[x];
            String a = " ";
            for (int z = 0; z < ListaCantHechizos[x]; z++) {
                if (z < ListaCantHechizos[x] - 1) {
                    a += Hechizos[x] + ",";
                } else {
                    a += Hechizos[x];
                }
            }
            text += a;
            escritura3.println(text);
        }

        String arch4 = "Enemigos.txt";
        FileWriter file4 = new FileWriter(arch4);
        PrintWriter escritura4 = new PrintWriter(file4);
        for (int k = 0; k < cantE; k++) {
            escritura4.println(
                    Enemigos[k] + "," + hpEnemigo[k] + "," + adEnemigo[k] + "," + Clase[k] + "," + spEnemigo[k]);
        }

        escritura.close();
        escritura2.close();
        escritura3.close();
        escritura4.close();
    }

}