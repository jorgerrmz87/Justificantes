package vistas;

import modelo.*;
import javax.swing.*;
import java.awt.*;

public class RegistroAlumno extends JFrame {
    Controlador controlador;
    JTextField txtNombre, txtApellidos, txtNumControl, txtUsuario;
    JPasswordField txtContrasena;
    JButton btnRegistrar, btnCancelar;

    public RegistroAlumno(Controlador controlador) {
        this.controlador = controlador;

        setTitle("Registro Alumno");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔷 Encabezado con logo único (logosUTNG-UTP.png)
        JPanel panelEncabezado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelEncabezado.setBackground(Color.WHITE);

        JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/recursos/logosUTNG-UTP.png")));
        panelEncabezado.add(logo);

        add(panelEncabezado, BorderLayout.NORTH);

        // 🔷 Panel central con separador, formulario y botones
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);

        // Separador
        JSeparator separador = new JSeparator();
        panelCentral.add(separador);

        // Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panelFormulario.setBackground(Color.WHITE);

        panelFormulario.add(new JLabel("Nombre(s):"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panelFormulario.add(txtApellidos);

        panelFormulario.add(new JLabel("Número de control:"));
        txtNumControl = new JTextField();
        panelFormulario.add(txtNumControl);

        panelFormulario.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panelFormulario.add(txtUsuario);

        panelFormulario.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panelFormulario.add(txtContrasena);

        panelCentral.add(panelFormulario);

        // Botones registrar y cancelar
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrar());
        panelBotones.add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panelBotones.add(btnCancelar);

        panelCentral.add(panelBotones);

        add(panelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    private void registrar() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String numControl = txtNumControl.getText();
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (nombre.isEmpty() || apellidos.isEmpty() || numControl.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
        } else {
            JOptionPane.showMessageDialog(this, "Alumno registrado exitosamente (prototipo)");
            dispose();
        }
    }
}