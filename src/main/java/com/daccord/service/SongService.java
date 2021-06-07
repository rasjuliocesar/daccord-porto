package com.daccord.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daccord.entities.Song;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

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
	/******************************************************************************* PAGINACAO ****************/
	public Page<Song> getPageSong(Pageable pageable) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();	
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).orderBy("title");
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();	
		
		if(!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));
			
			for (int i = 0; i < songList.size(); i++) {	
				System.out.println(songList.get(i).getDifficulty());		
			}
	
			final int start = (int)pageable.getOffset();
			final int end = Math.min((start + pageable.getPageSize()), songList.size());
			final Page<Song> songPage = new PageImpl<>(songList.subList(start, end), pageable, songList.size());
			
			return songPage;		
		}else {
			return null;
		}
	}	
	/******************************************************************************* CONTADOR DIFICULDADE *****/
	public Map<Object, Long> getDifficulty() throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();

		Query docReference = dbFirestore.collection(COLLECTION_NAME);
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();
		
		if (!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));
			Map<Object, Long> collect = songList.stream().collect(Collectors.groupingBy(item -> item.getDifficulty(), Collectors.counting()));
			return collect;
		} else {
			return null;
		}

	}
	/******************************************************************************* CONTADOR MUSICA/ACORDES **/
	public List<JSONObject> getChords() throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();

		Query docReference = dbFirestore.collection(COLLECTION_NAME);
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();
		
		List<JSONObject> genreList = new ArrayList<>(); 
		ArrayList<Integer> counter = new ArrayList<Integer>();
		
		counter.add(0);
		counter.add(0);
		counter.add(0);
		counter.add(0);
		counter.add(0);
		
		if (!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));
			
			for (int i = 0; i < songList.size(); i++) {				
				counter.set(0, songList.get(i).getChords().size() >= 0 && songList.get(i).getChords().size() <= 3 ? counter.get(0) + 1 : counter.get(0));
				counter.set(1, songList.get(i).getChords().size() >= 4 && songList.get(i).getChords().size() <= 5 ? counter.get(1) + 1 : counter.get(1));
				counter.set(2, songList.get(i).getChords().size() >= 6 && songList.get(i).getChords().size() <= 8 ? counter.get(2) + 1 : counter.get(2));
				counter.set(3, songList.get(i).getChords().size() >= 9 && songList.get(i).getChords().size() <= 12 ? counter.get(3) + 1 : counter.get(3));
				counter.set(4, songList.get(i).getChords().size() > 12 ? counter.get(4) + 1 : counter.get(4));				
			}
		}
		
		JSONObject d1 = new JSONObject();
		d1.put("0-3", counter.get(0));
		genreList.add(d1);
		JSONObject d2 = new JSONObject();
		d2.put("4-5", counter.get(1));
		genreList.add(d2);
		JSONObject d3 = new JSONObject();
		d3.put("6-8", counter.get(2));
		genreList.add(d3);
		JSONObject d4 = new JSONObject();
		d4.put("9-12", counter.get(3));
		genreList.add(d4);
		JSONObject d5 = new JSONObject();
		d5.put("mais", counter.get(4));
		genreList.add(d5);

		return genreList;

	}
	/******************************************************************************* CONTADOR MUSICA/GENERO ***/
	public Map<Object, Long> getArtist() throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME);
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();	
		
		if (!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));
			Map<Object, Long> collect = songList.stream().collect(Collectors.groupingBy(item -> item.getArtist(), Collectors.counting()));
			return collect;
		} else {
			return null;
		}

	}
	/******************************************************************************* CONTADOR MUSICA/ARTISTA ***/
	public Map<Object, Long> getGenre() throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME);
		ApiFuture<QuerySnapshot> future = docReference.get();
		QuerySnapshot doc = future.get();	
		
		if (!doc.isEmpty()) {
			List<Song> songList = new ArrayList<>();
			songList.addAll(doc.toObjects(Song.class));
			Map<Object, Long> collect = songList.stream().collect(Collectors.groupingBy(item -> item.getGenre(), Collectors.counting()));
			return collect;
		} else {
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
