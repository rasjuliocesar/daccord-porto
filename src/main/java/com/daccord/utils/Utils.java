package com.daccord.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.math.random.RandomDataImpl;

import com.daccord.entities.Log;

public class Utils {

	/**
	 * Gerador de id automatico de 20 caracteres.
	 * @return id
	 * @author Carlos.Pereira
	 */
	public String geradorId() {
		Date data = new Date();
		SimpleDateFormat dataFormatada = new SimpleDateFormat("ddMMyyyy");
		String random = new RandomDataImpl().nextSecureHexString(12);
		String id = dataFormatada.format(data) + random;
		
		return  id;
	}
	
	/**
	 * Gerar objeto Log para inserção no Banco
	 * @param collection
	 * @param type
	 * @return log
	 */
	public Log geradorLog(String collection, Integer type) {
		String id = geradorId();
		String description = "";
		
		switch(type) {
			case 1:
				description = "create";
				break;
			case 2:
				description = "read";
				break;
			case 3:
				description = "update";
				break;
			case 4:
				description = "delete";
				break;
		}
		
		Log log = new Log(id, description, "Padrão", collection);
	
		return log;
	}
}
