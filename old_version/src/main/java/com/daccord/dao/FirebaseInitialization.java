package com.daccord.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitialization {

	@PostConstruct
	public void initialization() throws IOException {

		FileInputStream serviceAccount = null;
		
		try {
			serviceAccount =  new FileInputStream("./serviceAccountKey.json");
		 
		
			FirebaseOptions options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .build();
			
			FirebaseApp.initializeApp(options);
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}