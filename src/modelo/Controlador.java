package modelo;

import java.util.ArrayList;

public class Controlador {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Justificante> justificantes = new ArrayList<>();

    public Controlador() {
    usuarios.add(new Usuario("admin", "admin", "admin"));
    usuarios.add(new Usuario("asistente", "asistente", "asistente"));
    usuarios.add(new Usuario("alumno", "alumno", "alumno"));

    justificantes = new ArrayList<>();

    // Justificantes prehechos de ejemplo
    justificantes.add(new Justificante("Luis Ramirez", "20212211", "10/12/2024", "Familiar", "GTO0135"));
    justificantes.add(new Justificante("Ana Torres", "20212212", "11/12/2024", "Enfermedad", "GTO0136"));
    justificantes.add(new Justificante("Carlos López", "20212213", "12/12/2024", "Personal", "GTO0137"));

    justificantes.get(0).aprobar();   // Aprobado
    justificantes.get(1).rechazar();  // Rechazado
    justificantes.get(2).aprobar();  // está línea hace un llamado a una lista de justificantes con una numeración y con este simple comando le damos un estado al justificante 

}

    public Usuario validarUsuario(String username, String password) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public void agregarJustificante(String alumno, String matricula, String fecha, String tipo, String grupo) {
    justificantes.add(new Justificante(alumno, matricula, fecha, tipo, grupo));
}


    public ArrayList<Justificante> getJustificantes() {
        return justificantes;
    }

    public void aprobarTodos() {
        for (Justificante j : justificantes) {
            j.aprobar();
        }
    }

    public void rechazarTodos() {
        for (Justificante j : justificantes) {
            j.rechazar();
        }
    }
    
}
