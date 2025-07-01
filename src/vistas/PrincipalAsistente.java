package vistas;

import modelo.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class PrincipalAsistente extends JFrame {
    Controlador controlador;
    JTable tabla;
    JTextField txtAlumno, txtGrupo;
    JComboBox<String> comboTipo, comboEstado, comboPeriodo;
    JButton btnGenerar, btnCerrar, btnFiltrar;
    JLabel lblLogo;

    public PrincipalAsistente(Controlador controlador) {
        this.controlador = controlador;

        setTitle("Principal Asistente - Generar Justificante");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel contenedor vertical para logo + filtros
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));

        // Logo
        lblLogo = new JLabel(new ImageIcon(getClass().getResource("/recursos/logosUTNG-UTP.png")));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(lblLogo);

        // Panel de filtros (dos filas)
        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new GridLayout(2, 1));

        // Primera fila: nombre/matrícula y tipo
        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        fila1.add(new JLabel("Nombre o Matrícula:"));
        txtAlumno = new JTextField(10);
        fila1.add(txtAlumno);

        fila1.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Todos", "Enfermedad", "Familiar", "Personal"});
        fila1.add(comboTipo);

        panelFiltros.add(fila1);

        // Segunda fila: estado, grupo, periodo, aplicar filtros
        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        fila2.add(new JLabel("Estado:"));
        comboEstado = new JComboBox<>(new String[]{"Todos", "Aprobado", "Pendiente", "Rechazado"});
        fila2.add(comboEstado);

        fila2.add(new JLabel("Grupo:"));
        txtGrupo = new JTextField(6);
        fila2.add(txtGrupo);

        fila2.add(new JLabel("Periodo:"));
        comboPeriodo = new JComboBox<>(new String[]{"Enero-Abril", "Mayo-Agosto", "Septiembre-Diciembre"});
        fila2.add(comboPeriodo);

        btnFiltrar = new JButton("Aplicar filtros");
        btnFiltrar.addActionListener(e -> mostrarJustificantes());
        fila2.add(btnFiltrar);

        panelFiltros.add(fila2);

        // Agrega filtros debajo del logo
        panelSuperior.add(panelFiltros);

        // Añade el panel superior completo al NORTH
        add(panelSuperior, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"Alumno", "Matrícula", "Fecha", "Tipo", "Estado", "Grupo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Panel botones
        JPanel panelBotones = new JPanel();
        btnGenerar = new JButton("Generar Justificante (PDF)");
        btnGenerar.addActionListener(e -> generar());
        panelBotones.add(btnGenerar);

        btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.addActionListener(e -> cerrar());
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        mostrarJustificantes();
        setVisible(true);
    }

    private void mostrarJustificantes() {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (Justificante j : controlador.getJustificantes()) {
            if (filtra(j)) {
                modelo.addRow(new Object[]{
                    j.getAlumno(),
                    j.getMatricula(),
                    j.getFecha(),
                    j.getTipo(),
                    j.getEstado(),
                    j.getGrupo()
                });
            }
        }
    }

    private boolean filtra(Justificante j) {
        boolean estadoOk = comboEstado.getSelectedItem().equals("Todos") || j.getEstado().equals(comboEstado.getSelectedItem());
        boolean periodoOk = comboPeriodo.getSelectedItem().equals("Enero-Abril"); // demo fijo, adapta si implementas periodos reales

        return (txtAlumno.getText().isEmpty() || j.getAlumno().contains(txtAlumno.getText()) || j.getMatricula().contains(txtAlumno.getText())) &&
               (comboTipo.getSelectedItem().equals("Todos") || j.getTipo().equals(comboTipo.getSelectedItem())) &&
               estadoOk &&
               (txtGrupo.getText().isEmpty() || j.getGrupo().contains(txtGrupo.getText())) &&
               periodoOk;
    }

    private void generar() {
        int row = tabla.getSelectedRow();
        if (row != -1) {
            String alumno = (String) tabla.getValueAt(row, 0);
            controlador.getJustificantes().get(row).aprobar(); // marca como aprobado
            mostrarJustificantes();
            JOptionPane.showMessageDialog(this, "Justificante generado para " + alumno + " (prototipo PDF)");
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un justificante para generar");
        }
    }

    private void cerrar() {
        new IniciodeSesion(controlador);
        dispose();
    }
}