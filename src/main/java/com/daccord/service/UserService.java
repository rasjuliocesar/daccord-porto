package com.daccord.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService {

	private static final String COLLECTION_NAME = "user";
	
	public String saveUser(User user) throws InterruptedException, ExecutionException {		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.get_id()).set(user);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	public String updateUser(User user) throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.get_id()).set(user);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	@SuppressWarnings("unused")
	public String deleteUserById(String id) throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
		
		return "Document with Product ID: " + id + " has been deleted successfully";
	}
	
	public List<User> getAllUserDetails() 
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = documentReference.iterator();
		
		List<User> userList = new ArrayList<>();
		User user = null;
		
		while(iterator.hasNext()) {
			DocumentReference documentReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = documentReference1.get();
			DocumentSnapshot document = future.get();
			
			user = document.toObject(User.class);
			userList.add(user);
		}
		
		return userList;
	}
	
	public User getUserDetailsById(String id) 
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query documentReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		
		ApiFuture<QuerySnapshot> future = documentReference.get();
		
		QuerySnapshot document = future.get();
		
		if(!document.isEmpty()) {
			User user = document.toObjects(User.class).get(0);
			return user;
		} else {
			return null;
		}
	}
}
