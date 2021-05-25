package com.daccord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Song;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class SongService {

	private static final String COLLECTION_NAME = "song";

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

		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)
				.document(song.get_id()).set(song);

		return collectionApiFuture.get().getUpdateTime().toString();
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
