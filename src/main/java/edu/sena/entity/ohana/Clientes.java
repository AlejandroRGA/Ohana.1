/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.ohana;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 57301
 */
@Entity
@Table(name = "clientes")
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idClientes")
    private Integer idClientes;
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private Collection<Cotizaciones> cotizacionesCollection;
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private Collection<Ventas> ventasCollection;
    @JoinColumns({
        @JoinColumn(name = "numeroCedula", referencedColumnName = "numeroCedula"),
        @JoinColumn(name = "numeroCedula", referencedColumnName = "numeroCedula")})
    @ManyToOne(fetch = FetchType.LAZY)
    private Personas personas;

    public Clientes() {
    }

    public Clientes(Integer idClientes) {
        this.idClientes = idClientes;
    }

    public Integer getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Integer idClientes) {
        this.idClientes = idClientes;
    }

    public Collection<Cotizaciones> getCotizacionesCollection() {
        return cotizacionesCollection;
    }

    public void setCotizacionesCollection(Collection<Cotizaciones> cotizacionesCollection) {
        this.cotizacionesCollection = cotizacionesCollection;
    }

    public Collection<Ventas> getVentasCollection() {
        return ventasCollection;
    }

    public void setVentasCollection(Collection<Ventas> ventasCollection) {
        this.ventasCollection = ventasCollection;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClientes != null ? idClientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.idClientes == null && other.idClientes != null) || (this.idClientes != null && !this.idClientes.equals(other.idClientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entity.ohana.Clientes[ idClientes=" + idClientes + " ]";
    }
    
}
