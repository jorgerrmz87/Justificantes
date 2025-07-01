package vistas;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IniciodeSesion extends JFrame {
    Controlador controlador;
    JTextField txtUsuario;
    JPasswordField txtContrasena;
    JCheckBox chkRecordar;
    JButton btnLogin, btnRegAlumno, btnRegAdmin;
    JLabel lblLogo;
    

    public IniciodeSesion(Controlador controlador) {
        this.controlador = controlador;

        setTitle("Inicio de Sesión");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Logo
        lblLogo = new JLabel();
        lblLogo.setBounds(30, 10, 400, 80);
        lblLogo.setIcon(new ImageIcon(getClass().getResource("/recursos/logosUTNG-UTP.png")));
        add(lblLogo);

        // Usuario
        JLabel lblU = new JLabel("Usuario:");
        lblU.setBounds(100, 100, 80, 25);
        add(lblU);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 100, 150, 25);
        add(txtUsuario);

        // Contraseña
        JLabel lblC = new JLabel("Contraseña:");
        lblC.setBounds(100, 140, 80, 25);
        add(lblC);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(180, 140, 150, 25);
        add(txtContrasena);

        // Recordarme
        chkRecordar = new JCheckBox("Recordarme");
        chkRecordar.setBounds(180, 170, 150, 25);
        add(chkRecordar);

        // Botón login
        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(160, 200, 120, 30);
        btnLogin.addActionListener(e -> login());
        add(btnLogin);

        // Botón registrar alumno
        btnRegAlumno = new JButton("Registrarse Alumno");
        btnRegAlumno.setBounds(80, 250, 140, 25);
        btnRegAlumno.addActionListener(e -> new RegistroAlumno(controlador));
        add(btnRegAlumno);

        // Botón registrar admin/docente
        btnRegAdmin = new JButton("Registrarse Docente/Admin");
        btnRegAdmin.setBounds(230, 250, 180, 25);
        btnRegAdmin.addActionListener(e -> new RegistroAdmin(controlador));
        add(btnRegAdmin);

        setVisible(true);
    }

    private void login() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        Usuario u = controlador.validarUsuario(usuario, contrasena);
        if (u != null) {
            if (u.getRol().equals("admin")) {
                new PrincipalAdmin(controlador);
            } else if (u.getRol().equals("asistente")) {
                new PrincipalAsistente(controlador);
            } else if (u.getRol().equals("alumno")) {
                new PrincipalAlumno(controlador);
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }
    public static void main(String[] args) {
    modelo.Controlador controlador = new modelo.Controlador();
    new IniciodeSesion(controlador);
}

}
