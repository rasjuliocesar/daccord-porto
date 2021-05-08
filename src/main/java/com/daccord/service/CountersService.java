package com.daccord.service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Counters;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CountersService {

	private static final String COLLECTION_NAME = "counters";
	
	/*@SuppressWarnings("unchecked")
	public HashMap<Integer, Integer> getAllGenre() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		@SuppressWarnings({ })
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("genre");

		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot document = future.get();
		
		return (HashMap<Integer, Integer>) document.getDocuments();
	}*/
	
	public List<QueryDocumentSnapshot> getAllGenre() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("genre");

		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot document = future.get();
		
		List<QueryDocumentSnapshot> genre;
		genre = document.getDocuments();
		
		return genre;
	}
}
