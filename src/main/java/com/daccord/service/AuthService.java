package com.daccord.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class AuthService {

	private static final String COLLECTION_NAME = "user";
	
	public User getAuthentication(String email, String password) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReferencef = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("email", email).whereEqualTo("password", password);
		ApiFuture<QuerySnapshot> future = docReferencef.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			User user = doc.toObjects(User.class).get(0);
			return user;
		} else {
			return null;
		}
	}
	
}
