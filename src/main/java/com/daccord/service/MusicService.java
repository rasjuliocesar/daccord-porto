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
import com.google.firebase.cloud.FirestoreClient;

@Service
public class MusicService {

	private static final String COLLECTION_NAME = "music";
	
	public List<Music> getAllMusicDetails() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
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
}
