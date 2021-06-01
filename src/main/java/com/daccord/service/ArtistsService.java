package com.daccord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daccord.entities.Artists;
import com.daccord.utils.Utils;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ArtistsService {

	private static final String COLLECTION_NAME = "artists";
	
	Utils util = new Utils();
	
	public List<Artists> getAllArtist() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
	
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("artist_name");
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
		List<Artists> artistList = new ArrayList<>();
		artistList.addAll(doc.toObjects(Artists.class));

		return artistList;
		
		}else {
			return null;
		}
	}
	
	public Page<Artists> getPageArtist(Pageable pageable) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
	
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("artist_name");
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			List<Artists> artistList = new ArrayList<>();
			artistList.addAll(doc.toObjects(Artists.class));
		
			final int start = (int)pageable.getOffset();
			final int end = Math.min((start + pageable.getPageSize()), artistList.size());
			final Page<Artists> artistPage = new PageImpl<>(artistList.subList(start, end), pageable, artistList.size());		

			return artistPage;
		
		}else {
			return null;
		}
	}
	
	public Artists getArtistById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
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
		if(artist.getArtist_genre() == null || artist.getArtist_name() == null ||
				artist.getArtist_genre().equals("") || artist.getArtist_name().equals("")) {
			return null;
		}
				
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = 
				dbFirestore.collection(COLLECTION_NAME).document(artist.get_id()).set(artist);				
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	@SuppressWarnings({ "unused" })
	public String deleteArtistById(String id) throws InterruptedException, ExecutionException {
		Artists artista = getArtistById(id);
		
		if(artista == null) {
			return null;
		}
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
		
		return "Artist ID: " + id + " deleted";
	}
	
	public String updateArtist(Artists artist) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(artist.get_id()).set(artist);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	public String getNameArtistById(String name) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("artist_name", name);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Artists artist = doc.toObjects(Artists.class).get(0);
			return artist.get_id();
		} else {
			return "0";
		}
	}
}
