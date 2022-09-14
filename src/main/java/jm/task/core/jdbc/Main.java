package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Max", "Komissarov", (byte) 23);
        userService.saveUser("Ramil", "Salimov", (byte) 23);
        userService.saveUser("Andrey", "Da", (byte) 18);
        userService.saveUser("Danil", "Net", (byte) 26);
        System.out.println(userService.getAllUsers());
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}