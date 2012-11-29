package fr.umlv.graph.test;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import fr.umlv.graph.Graph;
import fr.umlv.graph.algo.GreedyColoring;
import fr.umlv.graph.algo.TriangulatedAlgo;
import fr.umlv.graph.algo.WelshPowellAlgo;
import fr.umlv.graph.util.XMLToGraph;

/**
 * This class does the test of XMLParser, and check if each algorithm works correctly, with the two graph given with the Projet subject, in order to do some tests.
 * 
 *  * @author Quentin Bernard et Ludovic Feltz
 */

/* <This program is a program which colored graph, by using some algorithms, made by IR students.>
 *  Copyright (C) <2012>  <BERNARD Quentin & FELTZ Ludovic>

 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

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
