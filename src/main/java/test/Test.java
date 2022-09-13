package test;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        DBOperations dbOperations = new DBOperations();
        dbOperations.listarServicio();
        dbOperations.actualizarServicio(2, "Arreglo de neveras","Mantenimiento y limpieza de nevera");
    }
}