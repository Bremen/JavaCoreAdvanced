package Lesson_6.Server;


import java.sql.*;
import java.util.ArrayList;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("SELECT nickname, password FROM main\n" +
                "WHERE login = '%s'", login);

        int myhash = pass.hashCode();

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);

                if (myhash == dbHash) {
                    return nick;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addUser(String login, String pass, String nick) {
        String sql = String.format("INSERT INTO main (login, password, nickname)" +
                "VALUES ('%s', '%s', '%s')", login, pass.hashCode(), nick);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция возвращает черный список для заданного никнейма из БД
     * @param nick - ник для которого нужно получить черный список из БД
     * @return - черный список
     */
    public static ArrayList<String> getBlackListFromDbForNick(String nick){
        ArrayList<String> blackList = new ArrayList<>();

        String sql = String.format("SELECT blackList FROM testTable\n" +
                "WHERE id_name = '%s'", nick);

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String blockedNick = rs.getString(1);
                blackList.add(blockedNick);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blackList;
    }

    /**
     * Функция добавления пользователя в черный список
     * @param nick - владелец черного списка
     * @param blockedNick - ник, добавляемый в черный список
     */
    public static void addNickToBlackList(String nick, String blockedNick) {
        String sql = String.format("INSERT INTO testTable (id_name, blackList)" +
                "VALUES ('%s', '%s')", nick, blockedNick);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
