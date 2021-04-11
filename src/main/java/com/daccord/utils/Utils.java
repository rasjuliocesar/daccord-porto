package com.daccord.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.math.random.RandomDataImpl;

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
}
