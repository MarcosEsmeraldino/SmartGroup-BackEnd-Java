package com.smartgroup.backend.bean;

import java.util.Date;
import java.util.LinkedHashMap;

public class Resultado {
    private long enquete;
    private Date dataRegistro;
    private LinkedHashMap<Long, Integer> respostas; // id-opção, votos

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LinkedHashMap<Long, Integer> getRespostas() {
        return respostas;
    }

    public void setRespostas(LinkedHashMap<Long, Integer> respostas) {
        this.respostas = respostas;
    }

    public long getEnquete() {
        return enquete;
    }

    public void setEnquete(long enquete) {
        this.enquete = enquete;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.enquete ^ (this.enquete >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Resultado other = (Resultado) obj;
        if (this.enquete != other.enquete) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Resultado{" + "enquete=" + enquete + ", dataRegistro=" 
                + dataRegistro + ", respostas=" + respostas + '}';
    }
    
}
