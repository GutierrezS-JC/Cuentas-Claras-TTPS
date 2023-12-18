package com.ttps.cuentasclaras.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Division {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Enumerated(EnumType.STRING) // Mapea el enumerador como una cadena de texto en la base de datos
    private DivisionEnum divisionEnum;

}
