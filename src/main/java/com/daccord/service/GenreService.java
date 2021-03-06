package com.daccord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daccord.entities.Genre;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class GenreService {
	
	public static final String COLLECTION_NAME = "genre";
	
	public List<Genre> getAllGenre() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("name");
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
		List<Genre> genreList = new ArrayList<>();
		genreList.addAll(doc.toObjects(Genre.class));

		return genreList;
		
		}else {
			return null;
		}
	}
	
	public Page<Genre> getPageGenre(Pageable pageable) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
	
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("name");
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			List<Genre> genreList = new ArrayList<>();
			genreList.addAll(doc.toObjects(Genre.class));
		
			final int start = (int)pageable.getOffset();
			final int end = Math.min((start + pageable.getPageSize()), genreList.size());
			final Page<Genre> genrePage = new PageImpl<>(genreList.subList(start, end), pageable, genreList.size());		

			return genrePage;
		
		}else {
			return null;
		}
	}
	
	public Genre getGenreById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Genre genre= doc.toObjects(Genre.class).get(0);
			return genre;
		} else {
			return null;
		}
	}
	
	public String addGenre(Genre genre) throws InterruptedException, ExecutionException {
		if(genre.getId3() == null || genre.getName() == null || genre.getName().equals("")) {
			return null;
		}
				
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = 
				dbFirestore.collection(COLLECTION_NAME).document(genre.get_id()).set(genre);				
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	@SuppressWarnings({ "unused" })
	public String deleteGenreById(String id) throws InterruptedException, ExecutionException {
		Genre genre = getGenreById(id);
		
		if(genre == null) {
			return null;
		}
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
		
		return "Genre ID: " + id + " deleted";
	}
	
	public String updateGenre(Genre genre) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(genre.get_id()).set(genre);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	public String getIdGenreByName(String name) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("name", name);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Genre genre = doc.toObjects(Genre.class).get(0);
			return genre.get_id();
		} else {
			return "0";
		}
	}
	
	public String getNameGenretById3(Integer id3) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("id3", id3);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Genre genre = doc.toObjects(Genre.class).get(0);
			return genre.getName();
		} else {
			return "0";
		}
	}
}
