package com.daccord.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Artists;
import com.daccord.entities.Counters;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;

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
	
	/*public List<QueryDocumentSnapshot> getAllGenre() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("genre");

		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot document = future.get();
		
		List<QueryDocumentSnapshot> genre;
		genre = document.getDocuments();
		
		return genre;
	}*/
	
	/*public List<Counters> getAllGenre() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> docReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = docReference.iterator();
		
		List<Counters> counters = new ArrayList<>();
		Counters count = null;
		
		while(iterator.hasNext()) {
			DocumentReference docReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = docReference1.get();
			DocumentSnapshot doc = future.get();
			
			count = doc.toObject(Counters.class);
			counters.add(count);
		}
		
		return counters;
		
		//Retorno do Insomnia
		//[
		//  {
		//    "values": null,
		//    "value": null,
		//    "key": null
		//  }
		//]
	}*/

	/*@SuppressWarnings({ "null", "unchecked" })
	public List<Counters> getAllGenre() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> docReference = (Iterable<DocumentReference>) dbFirestore.collection(COLLECTION_NAME).orderBy("genre");
		Iterator<DocumentReference> iterator = docReference.iterator();
		
		HashMap<Integer, Integer> count = null;
		Counters counters = null;
		
		while(iterator.hasNext()) {
			DocumentReference docReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = docReference1.get();
			DocumentSnapshot doc = future.get();
			
			counters = doc.toObject(Counters.class);
			((List<Counters>) count).add(counters);
		}
		return (List<Counters>) count;
	}*/

	public List<Counters> getAllGenre() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		@SuppressWarnings("unchecked")
		Iterator<DataSnapshot> items = (Iterator<DataSnapshot>) dbFirestore.collection(COLLECTION_NAME).orderBy("genre");
		
		//Iterable<DocumentReference> docReference = (Iterable<DocumentReference>) dbFirestore.collection(COLLECTION_NAME).orderBy("genre");
		//Iterator<DocumentReference> iterator = docReference.iterator();
		
		//HashMap<Integer, Integer> count = null;
		List<Counters> count = new ArrayList<>();
		
		Counters counters = null;
		
		while(items.hasNext()) {
			//DocumentReference docReference1 = iterator.next();
			//ApiFuture<DocumentSnapshot> future = docReference1.get();
			DataSnapshot doc = items.next();
			
			counters = (Counters) doc.getValue();
			count.add(counters);
		}
		return count;
	}

}
