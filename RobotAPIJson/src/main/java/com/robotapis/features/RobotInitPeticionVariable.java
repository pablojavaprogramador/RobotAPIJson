package com.robotapis.features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;
import java.nio.file.Files;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.yaml.snakeyaml.Yaml;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RobotInitPeticionVariable {

	public static void main(String[] args) throws Exception {

		Reader fr = new FileReader(
				System.getProperty("user.dir").toString() + File.separator + "clientes_post_201.json");
		generacionFeature(fr);
	}

	public static void generacionFeature(Reader fr) throws IOException, ParseException {
		Map<String, String> keyValueStore = new HashMap<>();
		Stack<String> keyPath = new Stack();
		// JSONObject jsonObject = (JSONObject) parser.parse(fr);
		// JSONObject json = new JSONObject(jsonObject);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(fr);

		String ultimoPunto = "";
		keyValueStore = getObtenerXpathYValueDelJsonObject(json, keyValueStore, keyPath);
		
		

		Reader lineabase = new FileReader(
				System.getProperty("user.dir").toString() + File.separator + "clientes_post_201.json");

		JsonObject jsonprincipal = new Gson().fromJson(lineabase, JsonObject.class);
		
		System.out.println(jsonprincipal.toString());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		for (Map.Entry<String, String> map : keyValueStore.entrySet()) {

			String cadenafinal = map.getKey();
			String valor = map.getValue();
			int posicionPunto = cadenafinal.lastIndexOf('.');
			// System.out.println(posicionPunto);
			// String sHastaPrimerPunto = cadenafinal.substring(0,posicionPunto);
			ultimoPunto = cadenafinal.substring(posicionPunto + 1, cadenafinal.length());

			// System.out.println(ultimoPunto);

			Cambio(ultimoPunto, valor);
		}

	}

	public static void Cambio(String cadenafinal, Object valor) throws FileNotFoundException {
		JSONParser parser = new JSONParser();
		Reader fr = new FileReader(
				System.getProperty("user.dir").toString() + File.separator + "clientes_post_201.json");

		JsonObject jsonprincipal = new Gson().fromJson(fr, JsonObject.class);
		// jsonprincipal.add(cadenafinal, null);

		String numero_null = "\"" + cadenafinal + "\"" + ":" + null;
		String numero_boolean = "\"" + cadenafinal + "\"" + ":" + true;
		String numero_string_vacio = "\"" + cadenafinal + "\"" + ":" + "\"" + "\"";
		String numero_string = "\"" + cadenafinal + "\"" + ":" + "\"" + "soy un string" + "\"";
		String numero_float = "\"" + cadenafinal + "\"" + ":" + 1.9;
		String numero_12354 = "\"" + cadenafinal + "\"" + ":" + 1234;

		try {
			valor = Integer.parseInt((String) valor);

		} catch (NumberFormatException ex) {
			//System.err.println("No se puede convertir a Int");
			
		}

		try {
			valor = Float.parseFloat((String) valor);

		} catch (Exception ex) {
			//System.err.println("No se puede convertir a Float");
			
		}
		
		try {
			valor = Short.parseShort((String) valor);
			
		} catch (Exception ex) {
		//	System.err.println("No se puede convertir a String" +ex.getMessage());
		
			
		}
		
//		try {
//			valor =  Boolean.parseBoolean((String) valor);
//			
//		} catch (Exception ex) {
//			System.err.println("No se puede convertir a boolean");
//			
//		}
	//	System.out.println(valor);
		String cadenainicio = "\"" + cadenafinal + "\"" + ":" + valor;


		if (valor instanceof JSONObject) {
			System.out.println("No es de tipo JSONObject");
		} else if (valor instanceof JSONArray) {
			System.out.println("No es de tipo JSONArray");
		} else if (valor instanceof String) {
		//	System.out.println("Es de tipo String" +"\"" + cadenafinal + "\"" + ":" + "\"" + valor + "\"" );
			 cadenainicio = "\"" + cadenafinal + "\"" + ":" + "\"" + valor + "\"";
			 
			System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_null)+"|" );
			System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_boolean)+"|");
			System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_string_vacio)+"|");
			System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_float)+"|");
			System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_12354)+"|");
		} else if (Number.class.isInstance(valor)) {
			
			
				System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_null)+"|");
				System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_boolean)+"|");
				System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_string_vacio)+"|");
				System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_string)+"|");
			
			
			try {
				valor = Short.parseShort((String) valor);			
			} catch (Exception ex) {
			
				
				 cadenainicio = "\"" + cadenafinal + "\"" + ":" + "\"" + valor + "\"";
				System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_null)+"|");
				System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_boolean)+"|");
				System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_string_vacio)+"|");
				System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_float)+"|");
				System.out.println("|" +jsonprincipal.toString().replace(cadenainicio, numero_12354)+"|");
				
			
			}
		}
		
//		else if (((Boolean) valor).booleanValue() == true) {
//			System.out.println(" es de tipo Boolean");
//			cadenainicio = "\"" + cadenafinal + "\"" + ":" + valor ;
//			System.out.println(jsonprincipal.toString().replace(cadenainicio, numero_null));
//			System.out.println(jsonprincipal.toString().replace(cadenainicio, numero_string_vacio));
//			System.out.println(jsonprincipal.toString().replace(cadenainicio, numero_string));
//			System.out.println(jsonprincipal.toString().replace(cadenainicio, numero_float));
//			System.out.println(jsonprincipal.toString().replace(cadenainicio, numero_12354));
//			cadenainicio = null ;
//		}

	}

	public static Map<String, String> getObtenerXpathYValueDelJsonObject(JSONObject json,
			Map<String, String> keyValueStore, Stack<String> keyPath) {
		Set<String> jsonKeys = json.keySet();
		for (Object keyO : jsonKeys) {
			String key = (String) keyO;
			keyPath.push(key);
			Object object = json.get(key);
//			System.out.println("getObtenerXpathYValueDelJsonObject Traza: " + json);
			if (object instanceof JSONObject) {
				getObtenerXpathYValueDelJsonObject((JSONObject) object, keyValueStore, keyPath);
			}

			if (object instanceof JSONArray) {
				doJsonArray((JSONArray) object, keyPath, keyValueStore, json, key);
			}

			if (object instanceof String || object instanceof Boolean) {
				String keyStr = "";
				for (String keySub : keyPath) {
					keyStr += keySub + ".";
				}
				keyStr = keyStr.substring(0, keyStr.length() - 1);
				keyPath.pop();
				keyValueStore.put(keyStr, json.get(key).toString());
			}
			// System.out.println("objecto name: "+object.toString());
			// if (Integer.class.isInstance(object) || object.equals(null)) {

			// System.out.println("tipo de dato: "+Number.class.isInstance(object));
			if (Number.class.isInstance(object) || object.equals(null)) {

				if (object.equals(null)) {
					System.out.println("Es Nulo ");
				} else {
					String keyStr = "";
					for (String keySub : keyPath) {
						keyStr += keySub + ".";
					}
					keyStr = keyStr.substring(0, keyStr.length() - 1);
					keyPath.pop();
					keyValueStore.put(keyStr, json.get(key).toString());
				}
			}
		}
		if (keyPath.size() > 0) {
			keyPath.pop();
		}
		return keyValueStore;
	}

	public static void doJsonArray(JSONArray object, Stack<String> keyPath, Map<String, String> keyValueStore,
			JSONObject json, String key) {
		JSONArray arr = (JSONArray) object;
		for (int i = 0; i < arr.size(); i++) {
			keyPath.push(Integer.toString(i));
			Object obj = arr.get(i);
			if (obj instanceof JSONObject) {
				getObtenerXpathYValueDelJsonObject((JSONObject) obj, keyValueStore, keyPath);
			}
			if (obj instanceof JSONArray) {
				doJsonArray((JSONArray) obj, keyPath, keyValueStore, json, key);
			}
			if (obj instanceof String || obj instanceof Boolean || obj.equals("")) {
				String keyStr = "";
				for (String keySub : keyPath) {
					keyStr += keySub + ".";
				}
				keyStr = keyStr.substring(0, keyStr.length() - 1);
				keyPath.pop();
				keyValueStore.put(keyStr, json.get(key).toString());
			}

			if (Number.class.isInstance(obj) || obj.equals(null)) {

				if (obj.equals(null)) {
					System.out.println("Es Nulo ");
				} else {
					String keyStr = "";
					for (String keySub : keyPath) {
						keyStr += keySub + ".";
					}
					keyStr = keyStr.substring(0, keyStr.length() - 1);
					keyPath.pop();
					keyValueStore.put(keyStr, json.get(key).toString());
				}
			}
		}
		if (keyPath.size() > 0) {
			keyPath.pop();
		}
	}

}
