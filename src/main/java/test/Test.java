package test;

import controller.UserController;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {

        DBOperations dbOperations = new DBOperations();
        dbOperations.listarServicio();
        dbOperations.actualizarServicio( "Mantenimiento y limpieza de lavadora","Electrodomesticos",1);
    }
}