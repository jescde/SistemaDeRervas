//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Scanner sc = new Scanner(System.in);
    int accion = 0; // Registramos la respuesta del usuario

    System.out.println("¡Bienvenido a nuestro sistema de Reserva de Citas!");


    while (accion != 4) {

        Menu();

        System.out.println("\n¿Qué te gustaría hacer?");

        accion = sc.nextInt();

        opciones(accion);
    }

    sc.close();
}

public static void Menu() {
    System.out.println("\t1. Realizar reserva");
    System.out.println("\t2. Consultar reserva");
    System.out.println("\t3. Cancelar reserva");
    System.out.println("\t4. Salir");

}

public static void opciones(int x) {
    switch (x) {
        case 1:
            System.out.println("\nPágina de reservas en desarrollo...");
            System.out.println("Disculpa las molestias.");
            System.out.println();
            break;

        case 2:
            System.out.println("\nPágina de consultas en desarrollo...");
            System.out.println("Disculpa las molestias.");
            System.out.println();
            break;

        case 3:
            System.out.println("\nPágina de cancelación en desarrollo...");
            System.out.println("Disculpa las molestias.");
            System.out.println();
            break;

        case 4:
            System.out.println();
            System.out.println("\nEsperamos tenerte pronto con nosotros.");
            System.out.println("¡Hasta pronto!");
            System.out.println();
            break;

        default:
            System.out.println("Opción no válida.");
            System.out.println("Por favor, elige una opción del menú.");
            System.out.println();
    }
}


