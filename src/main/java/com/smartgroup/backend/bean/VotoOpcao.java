package com.smartgroup.backend.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartgroup.backend.enumerate.votoopcao.Valor;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class VotoOpcao implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne(optional = false)
    private Opcao opcao;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 4, nullable = false)
    private Valor valor;
    
    @ManyToOne(optional = false)
    @JsonIgnore
    private Voto voto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Opcao getOpcao() {
        return opcao;
    }

    public void setOpcao(Opcao opcao) {
        this.opcao = opcao;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public Voto getVoto() {
        return voto;
    }

    public void setVoto(Voto voto) {
        this.voto = voto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final VotoOpcao other = (VotoOpcao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VotoOpcao{" + "id=" + id + ", opcao=" + opcao.getId() 
                + ", valor=" + valor + ", voto=" + voto.getId() + '}';
    }

}
