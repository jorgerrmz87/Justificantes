package vistas;
import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NuevoJustificanteAlumno extends JFrame {
    Controlador controlador;
    PrincipalAlumno principal;
    JTable tabla;
    JButton btnNuevo, btnCerrar, btnGuardar, btnCancelar;
    JLabel lblLogo, lblAlumno, lblNumControl, lblGrupo, lblFechas, lblTipoJustificante, lblMotivos, lblDocumento;
    JTextField txtAlumno, txtNumControl, txtGrupo, txtFechas;
    JComboBox<String> cmbTipoJustificante;
    JTextArea txtMotivos;
    JButton btnAdjuntar;
    
    public NuevoJustificanteAlumno(Controlador controlador, PrincipalAlumno principal) {
        this.controlador = controlador;
        this.principal = principal;
        
        setTitle("Sistema gestor de justificantes");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Barra superior
        JPanel barraSuperior = new JPanel();
        barraSuperior.setLayout(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Nuevo Justificante", SwingConstants.CENTER);
        barraSuperior.add(lblTitulo, BorderLayout.CENTER);
        
        JPanel barraDerecha = new JPanel();
        barraDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btnMinimizar = new JButton("-");
        JButton btnCerrarVentana = new JButton("X");
        btnCerrarVentana.addActionListener(e -> dispose());
        barraDerecha.add(btnMinimizar);
        barraDerecha.add(btnCerrarVentana);
        
        barraSuperior.add(barraDerecha, BorderLayout.EAST);
        
        add(barraSuperior, BorderLayout.NORTH);
        
        // Panel central
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(9, 2, 10, 10)); // 9 filas, 2 columnas
        
        // Alumno
        lblAlumno = new JLabel("Alumno:");
        txtAlumno = new JTextField("Luis Ramirez");
        txtAlumno.setEnabled(false);
        
        // Número de control
        lblNumControl = new JLabel("Número de control:");
        txtNumControl = new JTextField("20212211");
        txtNumControl.setEnabled(false);
        
        // Grupo
        lblGrupo = new JLabel("Grupo:");
        txtGrupo = new JTextField("GTI0135");
        txtGrupo.setEnabled(false);
        
        // Fechas
        lblFechas = new JLabel("Fecha(s) a justificar:");
        txtFechas = new JTextField();
        
        // Tipo de justificante
        lblTipoJustificante = new JLabel("Tipo de Justificante:");
        cmbTipoJustificante = new JComboBox<>(new String[]{"Enfermedad", "Otro"});
        
        // Motivos
        lblMotivos = new JLabel("Motivos:");
        txtMotivos = new JTextArea();
        JScrollPane scrollMotivos = new JScrollPane(txtMotivos);
        
        // Documento
        lblDocumento = new JLabel("Documento:");
        btnAdjuntar = new JButton("Adjuntar archivo");
        
        // Añadir todo al panel
        panelFormulario.add(lblAlumno);
        panelFormulario.add(txtAlumno);
        panelFormulario.add(lblNumControl);
        panelFormulario.add(txtNumControl);
        panelFormulario.add(lblGrupo);
        panelFormulario.add(txtGrupo);
        panelFormulario.add(lblFechas);
        panelFormulario.add(txtFechas);
        panelFormulario.add(lblTipoJustificante);
        panelFormulario.add(cmbTipoJustificante);
        panelFormulario.add(lblMotivos);
        panelFormulario.add(scrollMotivos);
        panelFormulario.add(lblDocumento);
        panelFormulario.add(btnAdjuntar);
        
        add(panelFormulario, BorderLayout.CENTER);
        
        // Panel botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarJustificante());
        panelBotones.add(btnGuardar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());  // Cierra solo la ventana de justificante
        panelBotones.add(btnCancelar);
        
        add(panelBotones, BorderLayout.SOUTH);
        
        // Logo
        lblLogo = new JLabel(new ImageIcon(getClass().getResource("/recursos/logosUTNG-UTP.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblLogo, BorderLayout.NORTH);
        
        setVisible(true);
    }
    
    private void guardarJustificante() {
        // Lógica para guardar el justificante
        // Aquí puedes añadir el código para guardar los datos ingresados por el alumno
        JOptionPane.showMessageDialog(this, "Justificante guardado correctamente.");
    }
    
    public void actualizarLista() {
        // Lógica para actualizar la lista de justificantes
    }
}