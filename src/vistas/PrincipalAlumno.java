package vistas;
import modelo.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class PrincipalAlumno extends JFrame {
    Controlador controlador;
    JTable tabla;
    JButton btnNuevo, btnCerrar;
    JLabel lblLogo, lblTitulo;
    JTextField txtNombre, txtMatricula, txtGrupo;
    JComboBox<String> cmbPeriodo;

    public PrincipalAlumno(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Principal Alumno - Justificantes");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Barra superior con título
        JPanel barraSuperior = new JPanel();
        barraSuperior.setLayout(new BorderLayout());

        lblTitulo = new JLabel("Sistema gestor de justificantes", SwingConstants.LEFT);
        barraSuperior.add(lblTitulo, BorderLayout.WEST);

        add(barraSuperior, BorderLayout.NORTH);

        // Logo UTNG y UTP
        lblLogo = new JLabel(new ImageIcon(getClass().getResource("/recursos/logosUTNG-UTP.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblLogo, BorderLayout.CENTER);

        // Formulario de información del alumno
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(4, 2, 10, 10));

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField("Luis Ramirez");
        txtNombre.setEnabled(false);

        // Matrícula
        JLabel lblMatricula = new JLabel("Matrícula:");
        txtMatricula = new JTextField("20212211");
        txtMatricula.setEnabled(false);

        // Grupo
        JLabel lblGrupo = new JLabel("Grupo:");
        txtGrupo = new JTextField("GTI0135");

        // Periodo
        JLabel lblPeriodo = new JLabel("Periodo:");
        cmbPeriodo = new JComboBox<>(new String[]{"Enero-Abril", "Mayo-Agosto", "Septiembre-Diciembre"});

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblMatricula);
        panelFormulario.add(txtMatricula);
        panelFormulario.add(lblGrupo);
        panelFormulario.add(txtGrupo);
        panelFormulario.add(lblPeriodo);
        panelFormulario.add(cmbPeriodo);

        // Contenedor de formulario y tabla en un panel con BoxLayout
        JPanel contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new BoxLayout(contenedorPrincipal, BoxLayout.Y_AXIS));
        contenedorPrincipal.add(panelFormulario);

        // Tabla de justificantes
        String[] columnas = {"Fecha", "Tipo", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        contenedorPrincipal.add(scroll); // Añadimos la tabla en el contenedor

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnNuevo = new JButton("Nuevo Justificante");
        btnNuevo.addActionListener(e -> abrirNuevoJustificante());
        panelBotones.add(btnNuevo);

        btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.addActionListener(e -> cerrar());
        panelBotones.add(btnCerrar);

        contenedorPrincipal.add(panelBotones);

        add(contenedorPrincipal, BorderLayout.CENTER);

        mostrarJustificantes();
        setVisible(true);
    }

    private void mostrarJustificantes() {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);  // Limpiar la tabla antes de agregar nuevas filas

        // Iterar a través de los justificantes y añadir filas a la tabla
        for (Justificante j : controlador.getJustificantes()) {
            modelo.addRow(new Object[]{j.getFecha(), j.getTipo(), j.getEstado()});
        }
    }

    private void abrirNuevoJustificante() {
        new NuevoJustificanteAlumno(controlador, this);
    }

    private void cerrar() {
        new IniciodeSesion(controlador);
        dispose();
    }

    public void actualizarLista() {
        mostrarJustificantes();
    }
}