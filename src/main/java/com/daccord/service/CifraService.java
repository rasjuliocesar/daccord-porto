package com.daccord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Cifra;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CifraService {

	private static final String COLLECTION_NAME = "cifra";
	
	public List<Cifra> getAllCifra() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
	
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("artist");
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
		List<Cifra> cifraList = new ArrayList<>();
		cifraList.addAll(doc.toObjects(Cifra.class));

		return cifraList;
		
		}else {
			return null;
		}
	}
	
	public Cifra getCifraById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Cifra cifra = doc.toObjects(Cifra.class).get(0);
			return cifra;
		} else {
			return null;
		}
	}
	
	public String addCifra(Cifra cifra) throws InterruptedException, ExecutionException {
		if(cifra.getArtist() == null || cifra.getSong_id() == null ||
				cifra.getArtist().equals("") || cifra.getSong_id().equals("")) {
			return null;
		}
				
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = 
				dbFirestore.collection(COLLECTION_NAME).document(cifra.get_id()).set(cifra);				
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
}
