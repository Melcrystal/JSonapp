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

public class Process {
	ArrayList<Square> squares = new ArrayList<>();
	ArrayList<Triangle> triangles = new ArrayList<>();
	ArrayList<Arrow> arrows = new ArrayList<>();
	public Process(ArrayList<String> adr){
		ArrayList<JSONObject> person = new ArrayList<>();
		for(String s : adr){
		if(s.contains("square")){
			ArrayList<String> rect = null;
			try {
				rect = readJson(s);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 ArrayList<HashMap<String,Double>> pos = parsing(rect);
			 ArrayList<JSONObject> list = JsonWrite(pos, "Square");
			 for(int i = 0 ; i< list.size(); i++){
				 Square square = new Square();
				 JSONObject sq = list.get(i) ;
				 square.setSquare(sq) ;
				 person.add(sq);
				 squares.add(square);
			 }

		}
		if(s.contains("triangle")){
			ArrayList<String> rect = null;
			try {
				rect = readJson(s);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 ArrayList<HashMap<String,Double>> pos = parsing(rect);
			 ArrayList<JSONObject> list = JsonWrite(pos, "Triangle");
			 for(int i = 0 ; i< list.size(); i++){
				 Triangle square = new Triangle();
				 JSONObject sq = list.get(i) ;
				 square.setTriangle(sq) ;
				 person.add(sq);
				 triangles.add(square);
			 }

		}
		if(s.contains("arrow")){
			ArrayList<String> rect = null;
			try {
				rect = readJson(s);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 ArrayList<HashMap<String,Double>> pos = parsing(rect);
			 ArrayList<JSONObject> list = JsonWrite(pos, "Arrow");
			 for(int i = 0 ; i< list.size(); i++){
				 Arrow square = new Arrow();
				 JSONObject sq = list.get(i) ;
				 square.setArrow(sq) ;
				 person.add(sq);
				 arrows.add(square);
			 }

		}
		}
		try {
			FileWriter file = new FileWriter("d:\\test.json");
			for (int i = 0; i<person.size(); i++){
			file.write(person.get(i).toJSONString());
			file.write("\n");
	}
			file.flush();
			file.close();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<HashMap<String,Double>> parsing (ArrayList<String> rect){
		ArrayList<HashMap<String,Double>> pos = new ArrayList<HashMap<String,Double>>();
		for (String s: rect){
			HashMap<String,Double> posrects = new HashMap<String,Double>();
			String delims = "[:,}]";
			String[] tokens = s.split(delims);
			posrects.put("y1",Double.parseDouble(tokens[1]));
			posrects.put("x1",Double.parseDouble(tokens[3]));
			posrects.put("y2",Double.parseDouble(tokens[5]));
			posrects.put("x2",Double.parseDouble(tokens[7]));
			pos.add(posrects);
		}
		return pos;
		
	}
	
	public static ArrayList<JSONObject> JsonWrite(ArrayList<HashMap<String,Double>> pos, String s){
		ArrayList<JSONObject> person = new ArrayList<JSONObject>();
		JSONArray a = new JSONArray();
		int index = 0;
		for (HashMap<String,Double> temp : pos)
		  {
			JSONObject c = new JSONObject();
			temp = pos.get(index);
			JSONArray list = new JSONArray();
			list.add("y1 : "+temp.get("y1"));
			list.add("x1 : "+temp.get("x1"));
			list.add("y2 : "+temp.get("y2"));
			list.add("x2 : "+temp.get("x2"));
			
			c.put(s, list);
			person.add(c);
			index++;
		  }
	
		return person;

		
	}
	
	public static ArrayList<String> readJson(String Adr) throws FileNotFoundException, IOException, ParseException{
		 JSONParser parser = new JSONParser();
		 ArrayList<String> r = new ArrayList<>();
		 JSONArray a = (JSONArray) parser.parse(new FileReader(Adr));


		  for (Object o : a)
		  {
		    JSONObject person = (JSONObject) o;
		    JSONArray cars = (JSONArray) person.get("rects");

		    for (Object c : cars)
		    {
		      r.add(c.toString());
		    }
		  }  
		    return r;
	}
	
}
