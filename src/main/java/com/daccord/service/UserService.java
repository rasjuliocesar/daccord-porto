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
	
	/**
	 * Buscar todos os Users.
	 * @return userList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public List<User> getAllUser() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> docReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = docReference.iterator();
		
		List<User> userList = new ArrayList<>();
		User user = null;
		
		while(iterator.hasNext()) {
			DocumentReference docReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = docReference1.get();
			DocumentSnapshot doc = future.get();
			
			user = doc.toObject(User.class);
			userList.add(user);
		}
		return userList;
	}
	
	/**
	 * Buscar User por Id.
	 * @param id
	 * @return user
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Carlos.Pereira
	 */
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
	
	/**
	 * Salvar User
	 * @param user
	 * @return String user
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public String addUser(User user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.get_id()).set(user);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	/**
	 * Deletar User por Id.
	 * @param id
	 * @return mensagem
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	@SuppressWarnings("unused")
	public String deleteUserById(String id) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
		
		return "User ID: " + id + " deleted";
	}
	
	/**
	 * @param User
	 * @return String user
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public String updateUser(User user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.get_id()).set(user);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
}
