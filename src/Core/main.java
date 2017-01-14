package Core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		 ArrayList<String> adr = new ArrayList<>();
		 adr.add("d://square.json");
		 adr.add("d://triangle.json");
		 adr.add("d://arrow.json");
		Process process = new Process(adr);

	}

}
