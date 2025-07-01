package modelo;

public class Justificante {
    private String alumno;
    private String matricula;
    private String fecha;
    private String tipo;
    private String estado;
    private String grupo;

    public Justificante(String alumno, String matricula, String fecha, String tipo, String grupo) {
        this.alumno = alumno;
        this.matricula = matricula;
        this.fecha = fecha;
        this.tipo = tipo;
        this.estado = "Pendiente";
        this.grupo = grupo;
    }

    public String getAlumno() { return alumno; }
    public String getMatricula() { return matricula; }
    public String getFecha() { return fecha; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }
    public String getGrupo() { return grupo; }

    public void aprobar() { estado = "Aprobado"; }
    public void rechazar() { estado = "Rechazado"; }
}
