package com.daccord.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Artists;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ArtistsService {

	private static final String COLLECTION_NAME = "artists";
	
	public List<Artists> getAllArtist() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> docReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = docReference.iterator();
		
		List<Artists> artistsList = new ArrayList<>();
		Artists artist = null;
		
		while(iterator.hasNext()) {
			DocumentReference docReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = docReference1.get();
			DocumentSnapshot doc = future.get();
			
			artist = doc.toObject(Artists.class);
			artistsList.add(artist);
		}
		return artistsList;
	}
	
	public Artists getArtistByName(String name) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("artist_name", name);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Artists artist = doc.toObjects(Artists.class).get(0);
			return artist;
		} else {
			return null;
		}
	}
		
	public String addArtist(Artists artist) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).add(artist);
		
		return collectionApiFuture.get().getId().toString();
	}
	
	@SuppressWarnings("unused")
	public String deleteArtistByName(String name) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(name).delete();
		
		return "Artist Name: " + name + " deleted";
	}
	
	public String updateArtist(Artists artist) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(artist.getArtist_name()).set(artist);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
}
