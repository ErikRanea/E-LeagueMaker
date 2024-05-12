package Modelo;

public class Usuario {
    private int codUsuario;
    private String nickname;
    private String password;
    private boolean admin;

    // Constructor
    public Usuario(int codUsuario, String nickname, String password, boolean admin) {
        this.codUsuario = codUsuario;
        this.nickname = nickname;
        this.password = password;
        this.admin = admin;
    }

    // Getters & Setters
    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
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

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
