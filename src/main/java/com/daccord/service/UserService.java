package com.daccord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.User;
import com.daccord.utils.Utils;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService {

	private static final String COLLECTION_NAME = "user";
	
	Utils util = new Utils();
	
	public List<User> getAllUser() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("name");
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
		List<User> userList = new ArrayList<>();
		userList.addAll(doc.toObjects(User.class));

		return userList;
		
		}else {
			return null;
		}
	}
	
	public User getUserById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReferencef = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		ApiFuture<QuerySnapshot> future = docReferencef.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			User user = doc.toObjects(User.class).get(0);
			return user;
		} else {
			return null;
		}
	}
	
	public String addUser(User user) throws InterruptedException, ExecutionException {
		if(user.getName() == null || user.getEmail() == null ||
				user.getName().equals("") || user.getEmail().equals("")) {
			return null;
		}
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = 
				dbFirestore.collection(COLLECTION_NAME).document(user.get_id()).set(user);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	@SuppressWarnings("unused")
	public String deleteUserById(String id) throws InterruptedException, ExecutionException {
		User usuario = getUserById(id);
		
		if(usuario == null) {
			return null;
		}
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = 
				dbFirestore.collection(COLLECTION_NAME).document(id).delete();
		
		return "User ID: " + id + " deleted";
	}
	
	public String updateUser(User user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.get_id()).set(user);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
}
