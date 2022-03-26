package com.anahuac.calidad.crudMOCKTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import com.anahuac.calidad.crudMOCK.DAOmysqlEstudiante;
import com.anahuac.calidad.crudMOCK.Estudiante;

public class DAOmysqlEstudianteMOCKTest {
	
private DAOmysqlEstudiante dao ;
private Estudiante e;
private ArrayList<Estudiante> DB = new ArrayList<Estudiante>();

	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(DAOmysqlEstudiante.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AddEstudiantetest() {
	Estudiante e = new Estudiante(0,"nombre", "apellido", "correo","carrera");
	
	Answer<Integer>agregar = invocation->{
	
	Estudiante newestudiante = (Estudiante) invocation.getArguments()[0];
	
	int oldsize = DB.size();
		e.setId(oldsize);
		DB.add(newestudiante);
	return DB.size();
	};
		when(dao.createEstudiante(e)).thenAnswer(agregar);
	int oldsize = DB.size();
	assertThat(oldsize+1,is(dao.createEstudiante(e))); 

	}
	@Test
	public void DelateEstudiantetest() {
	Estudiante e = new Estudiante(1,"nombre", "apellido", "correo","carrera");	
	DB.add(e);	
	e.setId(DB.size()-1);
	Answer<Boolean>eliminar = invocation->{	
	Integer aux = (Integer) invocation.getArguments()[0];
	DB.remove(aux.intValue());
	
	return true;
	};
	when(dao.deleteEstudiante(e.getId())).thenAnswer(eliminar);
	int oldsize = DB.size();
	dao.deleteEstudiante(e.getId());
	int newsize = DB.size();
	assertThat(oldsize-1,is(newsize)); 

	}
}
