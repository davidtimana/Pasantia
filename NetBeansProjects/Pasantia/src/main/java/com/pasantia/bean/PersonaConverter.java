package com.pasantia.bean;

import com.pasantia.dao.PersonaDAO;
import com.pasantia.entidades.Persona;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class PersonaConverter extends ConverterGenerico<Persona> implements Serializable {

    @Inject
    private PersonaDAO personaDAO;

    @Override
    List<Persona> getLista() {
        return personaDAO.buscar();
    }

    @Override
    Integer getId(Persona t) {
        return t.getIdTblpersona();
    }

}
