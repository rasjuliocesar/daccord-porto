package com.daccord.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Log;
import com.daccord.entities.Song;
import com.daccord.utils.Utils;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class SongService {

	private static final String COLLECTION_NAME = "song";
	private static final String COLLECTION_NAME_LOG = "log";
	
	Utils util = new Utils();
	
	/**
	 * Buscar todos os Song.
	 * @return songList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public List<Song> getAllSong() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> docReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = docReference.iterator();
		
		List<Song> songList = new ArrayList<>();
		Song song = null;
		
		while(iterator.hasNext()) {
			DocumentReference docReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = docReference1.get();
			DocumentSnapshot doc = future.get();
			
			song = doc.toObject(Song.class);
			songList.add(song);
		}
		
		return songList;
	}
	
	/**
	 * Buscar Song por Id.
	 * @param id
	 * @return song
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Carlos.Pereira
	 */
	public Song getSongById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Song song = doc.toObjects(Song.class).get(0);
			return song;
		} else {
			return null;
		}
	}
	
	/**
	 * Salvar Song
	 * @param song
	 * @return String song
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	@SuppressWarnings("unused")
	public String addSong(Song song) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(song.get_id()).set(song);
		
		Log log = util.geradorLog(COLLECTION_NAME, 1);
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFutureLog = dbFirestore.collection(COLLECTION_NAME_LOG).document(log.get_id()).set(log);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	/**
	 * Deletar Song por Id.
	 * @param id
	 * @return mensagem
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	@SuppressWarnings("unused")
	public String deleteSongById(String id) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
		
		Log log = util.geradorLog(COLLECTION_NAME, 4);
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFutureLog = dbFirestore.collection(COLLECTION_NAME_LOG).document(log.get_id()).set(log);
		
		return "Song ID: " + id + " deleted";
	}
	
	/**
	 * @param Song
	 * @return String song
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	@SuppressWarnings("unused")
	public String updateSong(Song song) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(song.get_id()).set(song);
		
		Log log = util.geradorLog(COLLECTION_NAME, 3);
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFutureLog = dbFirestore.collection(COLLECTION_NAME_LOG).document(log.get_id()).set(log);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
}
