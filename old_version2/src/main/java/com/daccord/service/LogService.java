package com.daccord.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daccord.entities.Log;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class LogService {

	private final static String COLLECTION_NAME = "log";
	
	/**
	 * Buscar todos os Log.
	 * @return logList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public List<Log> getAllLog() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> docReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = docReference.iterator();
		
		List<Log> logList = new ArrayList<>();
		Log log = null;
		
		while(iterator.hasNext()) {
			DocumentReference docReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = docReference1.get();
			DocumentSnapshot doc = future.get();
			
			log = doc.toObject(Log.class);
			logList.add(log);
		}
		return logList;
	}
	
	/**
	 * Buscar Log por Id.
	 * @param id
	 * @return log
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Carlos.Pereira
	 */
	public Log getLogById(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		Query docReference = dbFirestore.collection(COLLECTION_NAME).whereEqualTo("_id", id);
		ApiFuture<QuerySnapshot> future = docReference.get();
		
		QuerySnapshot doc = future.get();
		
		if(!doc.isEmpty()) {
			Log log = doc.toObjects(Log.class).get(0);
			return log;
		} else {
			return null;
		}
	}
	
	/**
	 * Salvar Log
	 * @param log
	 * @return String log
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public String addLog(Log log) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(log.get_id()).set(log);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	/**
	 * Deletar Log por Id.
	 * @param id
	 * @return mensagem
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	@SuppressWarnings("unused")
	public String deleteLogById(String id) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
		
		return "Log ID: " + id + " deleted";
	}
	
	/**
	 * @param Log
	 * @return String log
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Julio.Cesar
	 */
	public String updateLog(Log log) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(log.get_id()).set(log);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	} 
}
