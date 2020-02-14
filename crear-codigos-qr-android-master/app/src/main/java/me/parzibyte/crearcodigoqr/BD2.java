package me.parzibyte.crearcodigoqr;

/**
 * Created by jj on 05/11/19.
 */

public class BD2 {
    private int id;
    private String nombre;
    private String apellidom;
    private String pellidop;
    private String grupo;
    private String carrera;
    private String semestre;
    private String numero_de_cuenta;

    public BD2(int id, String nombre, String apellidom, String pellidop, String grupo, String carrera, String semestre, String numero_de_cuenta) {
        this.id = id;
        this.nombre = nombre;
        this.apellidom = apellidom;
        this.pellidop = pellidop;
        this.grupo = grupo;
        this.carrera = carrera;
        this.semestre = semestre;
        this.numero_de_cuenta = numero_de_cuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getPellidop() {
        return pellidop;
    }

    public void setPellidop(String pellidop) {
        this.pellidop = pellidop;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getNumero_de_cuenta() {
        return numero_de_cuenta;
    }

    public void setNumero_de_cuenta(String numero_de_cuenta) {
        this.numero_de_cuenta = numero_de_cuenta;
    }
}
