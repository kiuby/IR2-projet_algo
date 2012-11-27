package fr.umlv.graph.test;

import junit.framework.Assert;

import org.junit.Test;

import fr.umlv.graph.Edge;
import fr.umlv.graph.Graph;
import fr.umlv.graph.algo.GreedyColoring;
import fr.umlv.graph.algo.TriangulatedAlgo;
import fr.umlv.graph.algo.WelshPowellAlgo;

public class AlgoTest {
	Graph graph = new Graph(4);
	int [] order = new int[4];
	
	public void initGraphTest(){
		graph.addEdge(new Edge(0, 1));
		graph.addEdge(new Edge(0, 2));
		graph.addEdge(new Edge(1, 3));
	}
	
	public void initOrder(){
		for(int i=0;i<4;i++)
			order[i]=i;
	}
	
	@Test
	public void greedyColoringTest(){
		initGraphTest();
		initOrder();
		Graph colorList = GreedyColoring.greedyColoring(graph, order);
		Assert.assertEquals(2, colorList.getColors());
	}
	
	@Test
	public void welshPowellAlgoTest(){
		initGraphTest();
		Graph colorList = WelshPowellAlgo.welshPowellAlgo(graph);
		Assert.assertEquals(3, colorList.getColors());
	}
	
	@Test
	public void triangulatedAlgoTest(){
		initGraphTest();
		Graph colorList = TriangulatedAlgo.triangulatedAlgo(graph);
		Assert.assertEquals(2, colorList.getColors());
	}
	
	
}
