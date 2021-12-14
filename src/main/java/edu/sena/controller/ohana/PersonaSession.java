/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.ohana;

import edu.sena.entity.ohana.Personas;
import edu.sena.entity.ohana.Roles;
import edu.sena.facade.ohana.PersonasFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@Named(value = "personaSession")
@SessionScoped
public class PersonaSession implements Serializable {

    @EJB
    PersonasFacadeLocal personasFacadeLocal;

    private Personas perLogin = new Personas();
    private String correoIn = "";
    private String contraseniaIn = "";

    public PersonaSession() {
    }

    public void validarUsuario() {
        try {
            perLogin = personasFacadeLocal.inicioSesion(correoIn, contraseniaIn);
            if (perLogin != null) {
                if (perLogin.getRolesCollection().size() == 1) {
                    ArrayList<Roles> Rol =  new ArrayList<>(perLogin.getRolesCollection());
                    if (Rol.get(0).getNombreRol().equalsIgnoreCase("Administrador")) {
                        FacesContext fc = FacesContext.getCurrentInstance();
                        fc.getExternalContext().redirect("../administrador/interfaztrabajadores.xhtml");
                    } else if(perLogin.getRolesCollection().isEmpty()) {
                        FacesContext fc = FacesContext.getCurrentInstance();
                        fc.getExternalContext().redirect("index.xhtml");
                    }else {
                        FacesContext fc = FacesContext.getCurrentInstance();
                        fc.getExternalContext().redirect("../Cliente/ClientePaginaprincipal.xhtml");
                    }
                } else {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().redirect("ingreso.xhtml");
                }

            } else {
                PrimeFaces.current().executeScript("Swal.fire("
                        + " 'Usuario',"
                        + " 'No existe en la nase de datos', "
                        + " 'error'"
                        + ")");
            }
        } catch (Exception e) {
        }
    }

    public void cerrarSesion() throws IOException {
        perLogin = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().invalidateSession();
        fc.getExternalContext().redirect("ohana.xhtml");
    }

    public Personas getPerLogin() {
        return perLogin;
    }

    public void setPerLogin(Personas perLogin) {
        this.perLogin = perLogin;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public String getContraseniaIn() {
        return contraseniaIn;
    }

    public void setContraseniaIn(String contraseniaIn) {
        this.contraseniaIn = contraseniaIn;
    }

}
