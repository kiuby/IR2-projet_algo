package fr.umlv.graph.test;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import fr.umlv.graph.Graph;
import fr.umlv.graph.algo.GreedyColoring;
import fr.umlv.graph.algo.TriangulatedAlgo;
import fr.umlv.graph.algo.WelshPowellAlgo;
import fr.umlv.graph.util.XMLToGraph;

public class XmlTest {

	
	public Graph init(String filename){
		File f = new File(filename);
		return XMLToGraph.getGraphFromXml(f);
	}
	
	public int[] initClassicOrder(Graph graph){
		int order[] = new int[graph.getVertexCount()];
		for(int i=0;i<graph.getVertexCount();i++)
			order[i]=i;
		return order;
	}
	
	@Test
	public void testGraphColorationXml1(){
		Graph graph = init("src/fr/umlv/graph/util/graph1.xml");
		Assert.assertEquals(5, GreedyColoring.greedyColoring(graph, initClassicOrder(graph)).getColors());
		Assert.assertEquals(4, WelshPowellAlgo.welshPowellAlgo(graph).getColors());
		Assert.assertEquals(3, TriangulatedAlgo.triangulatedAlgo(graph).getColors());
	}
	
	@Test
	public void testGraphColorationXml2(){
		Graph graph = init("src/fr/umlv/graph/util/graph2.xml");
		Assert.assertEquals(5, GreedyColoring.greedyColoring(graph, initClassicOrder(graph)).getColors());
		Assert.assertEquals(5, WelshPowellAlgo.welshPowellAlgo(graph).getColors());
		Assert.assertEquals(3, TriangulatedAlgo.triangulatedAlgo(graph).getColors());
	}
	
}
