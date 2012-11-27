/**
 * @author Quentin Bernard(qbernard@etudiant.univ-mlv.fr)
 * Copyright Universite de Marne-la-Vallee
 */
package fr.umlv.graph.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.umlv.graph.Edge;
import fr.umlv.graph.Graph;

public class GraphTest {

	Graph graph = new Graph(4);
	
	public void initGraphTest(){
		graph.addEdge(new Edge(0, 3));
		graph.addEdge(new Edge(0, 1));
		graph.addEdge(new Edge(0, 2));
		graph.addEdge(new Edge(1, 2));
		graph.addEdge(new Edge(2, 3));
	}
	
	
	/* Constructor test*/
	@Test(expected=IllegalArgumentException.class)
	public void testMatrixGraphe() {
		new Graph(-5);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testMatrixGraphe2() {
		new Graph(0);
	}


	/* verticesCount test*/
	@Test
	public void testverticesCount() {
		for(int i=1;i<100;i++){
			Graph graphTest = new Graph(i);
			assertEquals(i, graphTest.getVertexCount());
		}
	}

	@Test
	public void testverticesCount2() {
		initGraphTest();
		assertEquals(4, graph.getVertexCount());
	}

	/* hasEdge test*/
	@Test(expected=IllegalArgumentException.class)
	public void testhasEdge() {
		Graph graphTest = new Graph(40);
		graphTest.hasEdge(new Edge(-2, -3));
	}	

	@Test
	public void testhasEdge2() {
		initGraphTest();
		assertEquals(true, graph.hasEdge(new Edge(0, 3)));
		assertEquals(true, graph.hasEdge(new Edge(0, 1)));
		assertEquals(true, graph.hasEdge(new Edge(0, 2)));
		assertEquals(true, graph.hasEdge(new Edge(1, 2)));
		assertEquals(true, graph.hasEdge(new Edge(2, 3)));
		assertEquals(true, graph.hasEdge(new Edge(3, 0)));
	}	

	/* addEdge test*/
	@Test(expected=IllegalArgumentException.class)
	public void testaddArc() {
		Graph graphTest = new Graph(40);
		graphTest.addEdge(new Edge(0, 0));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testaddArc2() {
		Graph matrix = new Graph(40);
		matrix.addEdge(new Edge(40, 40));
	}

	
	/* removeEdge test*/
	@Test(expected=IllegalArgumentException.class)
	public void testremoveArc() {
		Graph matrix = new Graph(40);
		matrix.removeEdge(new Edge(-2, -3));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testremoveArc2() {
		Graph matrix = new Graph(40);
		matrix.removeEdge(new Edge(40, 40));
	}

}
