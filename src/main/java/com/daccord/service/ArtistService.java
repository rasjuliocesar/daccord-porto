package com.daccord.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Artist;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ArtistService {

	private static final String COLLECTION_NAME = "artist";
	
	/**
	 * Buscar todos os Artist.
	 * @return artistList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public List<Artist> getAllArtist() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> docReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = docReference.iterator();
		
		List<Artist> artistList = new ArrayList<>();
		Artist artist = null;
		
		while(iterator.hasNext()) {
			DocumentReference docReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = docReference1.get();
			DocumentSnapshot doc = future.get();
			
			artist = doc.toObject(Artist.class);
			artistList.add(artist);
		}
		return artistList;
	}
	
	/**
	 * Buscar Artist por Id.
	 * @param id
	 * @return artist
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Carlos.Pereira
	 */
	public Artist getArtistById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Artist artist = doc.toObjects(Artist.class).get(0);
			return artist;
		} else {
			return null;
		}
	}
}
