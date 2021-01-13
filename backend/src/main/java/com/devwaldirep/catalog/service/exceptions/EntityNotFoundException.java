package com.devwaldirep.catalog.service.exceptions;

/**
 * Quando se herda de Exception, obrigatoriamente ela precisa ser tratada, o compilador não permite que vc não trate.
 * RuntimeException -> excessão mais flexivel, pode ser ou não tratada
 * @author wepbi
 *
 */
public class EntityNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	
	
	
	public EntityNotFoundException(String msg) {
		super(msg);
	}

}
