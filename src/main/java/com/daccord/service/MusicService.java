package com.daccord.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Music;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class MusicService {

	private static final String COLLECTION_NAME = "music";
	
	/**
	 * Salvar musica
	 * @param music
	 * @return music
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public String saveMusic(Music music) 
			throws InterruptedException, ExecutionException {	
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore
				.collection(COLLECTION_NAME)
				.document(music.get_id())
				.set(music);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	/**
	 * Update em musica com o id igual ao que ja se encontra no banco
	 * @param music
	 * @return music
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public String updateMusic(Music music) throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore
				.collection(COLLECTION_NAME)
				.document(music.get_id())
				.set(music);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	/**
	 * Deletar musica por Id.
	 * @param id
	 * @return mensagem
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	@SuppressWarnings("unused")
	public String deleteMusicById(String id) throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore
				.collection(COLLECTION_NAME)
				.document(id)
				.delete();
		
		return "Document with Product ID: " + id + " has been deleted successfully";
	}
	
	/**
	 * Buscar todas as musicas.
	 * @return musicList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public List<Music> getAllMusicDetails() 
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> documentReference = dbFirestore
				.collection(COLLECTION_NAME)
				.listDocuments();
		Iterator<DocumentReference> iterator = documentReference.iterator();
		
		List<Music> musicList = new ArrayList<>();
		Music music = null;
		
		while(iterator.hasNext()) {
			DocumentReference documentReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = documentReference1.get();
			DocumentSnapshot document = future.get();
			
			music = document.toObject(Music.class);
			musicList.add(music);
		}
		
		return musicList;
	}
	
	/**
	 * Buscar musicas por Id.
	 * @param id
	 * @return music
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Carlos.Pereira
	 */
	public Music getMusicDetailsById(String id) 
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query documentReference = dbFirestore
				.collection(COLLECTION_NAME)
				.whereEqualTo("_id", id);
		
		ApiFuture<QuerySnapshot> future = documentReference.get();
		
		QuerySnapshot document = future.get();
		
		if(!document.isEmpty()) {
			Music music = document.toObjects(Music.class).get(0);
			return music;
		} else {
			return null;
		}
	}
	
	/**
	 * Buscar musicas por Nivel.
	 * @param nivel
	 * @return musicList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Carlos.Pereira
	 */
	public List<Music> getMusicDetailsByNivel(String nivel) 
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query documentReference = dbFirestore
				.collection(COLLECTION_NAME)
				.whereEqualTo("nivel", nivel);
		
		ApiFuture<QuerySnapshot> future = documentReference.get();
		
		QuerySnapshot document = future.get();
		
		if(!document.isEmpty()) {
			List<Music> musicList = document.toObjects(Music.class);
			return musicList;
		} else {
			return null;
		}
	}
	
	/**
	 * Buscar musicas por Genero.
	 * @param genero
	 * @return musicList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Carlos.Pereira
	 */
	public List<Music> getMusicDetailsByGenero(String genero) 
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query documentReference = dbFirestore
				.collection(COLLECTION_NAME)
				.whereEqualTo("genero", genero);
		
		ApiFuture<QuerySnapshot> future = documentReference.get();
		
		QuerySnapshot document = future.get();
		
		if(!document.isEmpty()) {
			List<Music> musicList = document.toObjects(Music.class);
			return musicList;
		} else {
			return null;
		}
	}

	/**
	 * Buscar musicas por Acordes.
	 * @param acordes
	 * @return musicList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Carlos.Pereira
	 */
	public List<Music> getMusicDetailsByAcordes(Integer acordes) 
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query documentReference = dbFirestore
				.collection(COLLECTION_NAME)
				.whereEqualTo("acordes", acordes);
		
		ApiFuture<QuerySnapshot> future = documentReference.get();
		
		QuerySnapshot document = future.get();
		
		if(!document.isEmpty()) {
			List<Music> musicList = document.toObjects(Music.class);
			return musicList;
		} else {
			return null;
		}
	}
	
}
