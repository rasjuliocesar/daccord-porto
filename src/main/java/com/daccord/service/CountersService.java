package com.daccord.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Counters;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CountersService {

	private static final String COLLECTION_NAME = "counters";
	private static final String ID = "Bp1IsLTDVtUfa4fAfC7C";
	
	public Counters getCountersById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Counters count = doc.toObjects(Counters.class).get(0);
			return count;
		} else {
			return null;
		}
	}
	
	public String incrementCountersArtists() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", ID);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		Counters count = doc.toObjects(Counters.class).get(0);
		count.setArtists(count.getArtists() + 1);
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(ID).set(count);

		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	public String decrementCountersArtists() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", ID);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		Counters count = doc.toObjects(Counters.class).get(0);
		count.setArtists(count.getArtists() - 1);
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(ID).set(count);

		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	public String incrementCountersNivel(String key) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", ID);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		Counters count = doc.toObjects(Counters.class).get(0);
		
		count.getNivel().put(key, count.getNivel().get(key).intValue() + 1);
				
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(ID).set(count);

		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	public String incrementCountersChords(String value) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", ID);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		Counters count = doc.toObjects(Counters.class).get(0);
		
		String key = "";
		if(value.equals("0") || value.equals("1") || value.equals("2") || value.equals("3")) {
			key = "0-3";
		} else if(value.equals("4") || value.equals("5")) {
			key = "4-5";
		} else if(value.equals("6") || value.equals("7") || value.equals("8")) {
			key = "6-8";
		} else if(value.equals("9") || value.equals("10") || value.equals("11") || value.equals("12")) {
			key = "9-12";
		} else {
			key = "more";
		}
		
		count.getChords().put(key, count.getChords().get(key).intValue() + 1);	
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(ID).set(count);

		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
}
