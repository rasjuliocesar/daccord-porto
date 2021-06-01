package com.daccord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daccord.entities.Genre;
import com.daccord.entities.Song;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class SongService {

	private static final String COLLECTION_NAME = "song";
	private static final String SUBCOLLECTION_NAME = "cifra";

	public List<Song> getAllSong() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("title");
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();

		if (!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));

			return songList;

		} else {
			return null;
		}
	}
	
	public Page<Song> getPageSong(Pageable pageable) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
	
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("title");
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));
		
			final int start = (int)pageable.getOffset();
			final int end = Math.min((start + pageable.getPageSize()), songList.size());
			final Page<Song> songPage = new PageImpl<>(songList.subList(start, end), pageable, songList.size());		

			return songPage;
		
		}else {
			return null;
		}
	}
	
	public List<Song> getAllSongByGenre(Integer value) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		CollectionReference colRef = dbFirestore.collection(COLLECTION_NAME);
		Query docReference = colRef.whereEqualTo("genre", value);
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();

		if (!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));

			return songList;

		} else {
			return null;
		}
	}
	
	public List<Song> getAllSongByArtist(String value) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		CollectionReference colRef = dbFirestore.collection(COLLECTION_NAME);
		Query docReference = colRef.whereEqualTo("artist", value);
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();

		if (!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));

			return songList;

		} else {
			return null;
		}
	}
	
	public List<Song> getAllSongByName(String value) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		CollectionReference colRef = dbFirestore.collection(COLLECTION_NAME);
		Query docReference = colRef.whereEqualTo("title", value);
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();

		if (!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));

			return songList;

		} else {
			return null;
		}
	}

	public Song getSongById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		ApiFuture<QuerySnapshot> future = docReference.get();

		QuerySnapshot doc = future.get();

		if (!doc.isEmpty()) {
			Song song = doc.toObjects(Song.class).get(0);

			return song;
		}

		return null;
	}

	public String addSong(Song song) throws InterruptedException, ExecutionException {
		if (song.getArtist() == null || song.getTitle() == null || song.getArtist().equals("")
				|| song.getTitle().equals("")) {
			return null;
		}

		Firestore dbFirestore = FirestoreClient.getFirestore();

		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture =
				dbFirestore.collection(COLLECTION_NAME).document(song.get_id()).set(song);
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture2 =
				dbFirestore.collection(COLLECTION_NAME).document(song.get_id()).collection(SUBCOLLECTION_NAME).document().set(song.getCifra());

		return collectionApiFuture.get().getUpdateTime().toString() + collectionApiFuture2.get().getUpdateTime().toString();
	}

	@SuppressWarnings("unused")
	public String deleteSongById(String id) throws InterruptedException, ExecutionException {
		Song song = getSongById(id);

		if (song == null) {
			return null;
		}

		Firestore dbFirestore = FirestoreClient.getFirestore();

		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)
				.document(id).delete();

		return "Song ID: " + id + " deleted";
	}

	public String updateSong(Song song) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)
				.document(song.get_id()).set(song);

		return collectionApiFuture.get().getUpdateTime().toString();
	}
}
