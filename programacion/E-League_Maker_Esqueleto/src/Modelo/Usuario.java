package Modelo;

public class Usuario {
    private int cod;
    private String nickname;
    private String password;
    private boolean admin;

    // Constructor
    public Usuario(int cod, String nickname, String password, boolean admin) {
        this.cod = cod;
        this.nickname = nickname;
        this.password = password;
        this.admin = admin;
    }

    public Usuario() {}
    // Getters & Setters
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public int isAdminInt()
    {
        int abierto = 0;
        if(isAdmin())
            abierto = 1;
        return abierto;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    // To String
    @Override
    public String toString() {
        return "USUARIO:" +
                "Nickname:'" + nickname + '\'' +
                ", Contraseña:'" + password + '\'';
    }
}
