package fr.umlv.graph.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.umlv.graph.Edge;
import fr.umlv.graph.Graph;


public class XMLToGraph{
	
	/*
	 * Le handler XML
	 */
	private static class GraphHandler extends DefaultHandler {
		private StringBuilder sb;
		private Graph graph;
				
		@Override
		public void characters(char[] argv,int start, int length) throws SAXException{
			String read = new String(argv,start,length);
			if(sb != null) sb.append(read);       
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
			if (qName.equals("graph")){
				if (graph!=null){
					throw new SAXException("Graph already init, you can only load one graph by file");
				}
				return;
			}
			if (qName.equals("size")){
				sb = new StringBuilder();
				return;
			}
			if (qName.equals("edge")){
				if (graph==null) throw new SAXException("Size undefined: define the size first");
				int start, end;
				try {
					start = Integer.parseInt(attributes.getValue("start"));
					end = Integer.parseInt(attributes.getValue("end"));
				} catch (Exception e){
					throw new SAXException("Error while parsing Edge: start/end");
				}
				graph.addEdge(new Edge(start, end));
				return;
			}
			throw new SAXException("Unknown XML attribute: " + qName);
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException{
			if (qName.equals("size")){
				int size;
				try{
					size = Integer.parseInt(sb.toString());
				} catch (Exception e){
					throw new SAXException(qName+" tag should contain an integer value");
				}
				graph = new Graph(size);
				return;
			}
		}
		
		@Override
		public void endDocument() throws SAXException {
			sb = null;
		}

		public Graph getGraph() {
			return graph;
		}		
	}
	
	
	
	public static Graph getGraphFromXml(File f){
		GraphHandler gh = new GraphHandler();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			SAXParser parser = factory.newSAXParser();
			parser.parse(f, gh);
		} catch (ParserConfigurationException pce) {
			System.err.println("An error occured during parsing");
			System.exit(1);
		} catch (SAXException se){
			System.err.println("Parsing error : "+se.getMessage());
			System.exit(1);
		}  catch (IOException ioe) {
			System.err.println("Read/Write error");
			System.exit(1);
		}
		return gh.getGraph();
	}

	public static void main(String[] args) {
		File f = new File("src/fr/umlv/graph/util/graph1.xml");
		Graph g = getGraphFromXml(f);
		System.out.println(g.toString());
	}

}