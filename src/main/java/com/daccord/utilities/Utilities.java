package com.daccord.utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.math.random.RandomDataImpl;

public class Utilities {

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
	
	/*
	 * Criptografia de String.
	 * @return word
	 */
	public String criptografar(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algoritmo = MessageDigest.getInstance("MD5");
		byte[] wordByte = algoritmo.digest(password.getBytes());
		
		String word = new String(wordByte);
		
		return word;
	}
	
}
