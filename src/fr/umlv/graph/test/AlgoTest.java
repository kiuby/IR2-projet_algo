package fr.umlv.graph.test;

import junit.framework.Assert;

import org.junit.Test;

import fr.umlv.graph.Edge;
import fr.umlv.graph.Graph;
import fr.umlv.graph.algo.GreedyColoring;
import fr.umlv.graph.algo.TriangulatedAlgo;
import fr.umlv.graph.algo.WelshPowellAlgo;

/**
 * This class does the test for each algorithm, with a small graph, in order to add a check to our implementation.
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

public class AlgoTest {
	Graph graph = new Graph(4);
	
	public void initGraphTest(){
		graph.addEdge(new Edge(0, 1));
		graph.addEdge(new Edge(0, 2));
		graph.addEdge(new Edge(1, 3));
	}
	
	@Test
	public void greedyColoringTest(){
		initGraphTest();
		Graph colorList = GreedyColoring.greedyColoring(graph, GreedyColoring.getOrder(graph));
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
