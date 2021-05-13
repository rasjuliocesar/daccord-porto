package com.daccord.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Counters;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CountersService {

	private static final String COLLECTION_NAME = "counters";
	
	public Counters getCountersById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Counters count= doc.toObjects(Counters.class).get(0);
			return count;
		} else {
			return null;
		}
	}
	
	public String updateCounters(String field, Integer value) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		//ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document("Bp1IsLTDVtUfa4fAfC7C").update(field, value, 0);
		
		
		// Update an existing document
		DocumentReference docRef = dbFirestore.collection(COLLECTION_NAME).document("Bp1IsLTDVtUfa4fAfC7C");

		// (async) Update one field
		ApiFuture<WriteResult> future = docRef.update(field, value);
		
		
		
		
		return future.get().getUpdateTime().toString();
	}
	
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

	/*public List<Counters> getAllGenre() throws InterruptedException, ExecutionException {
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
	}*/

}
