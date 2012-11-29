package fr.umlv.graph.algo;

import java.util.Arrays;
import java.util.Comparator;

import fr.umlv.graph.Graph;
import fr.umlv.graph.Vertex;
import fr.umlv.graph.algo.GreedyColoring;


/**
 * This class does the color of a graph, by using a greedy algorithm, with a descendant order concerning the degree of each vertex.
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

public class WelshPowellAlgo {

	/**
	 * Does the algorithm of Welsh and Powell, which colored a graph in order to minimize the number of color.
	 * This algorithm uses the greed algorithm, where the order of vertex is the degree, sort by descendant way;
	 * @param graph - the graph to color, using the Welsh and Powell algo
	 * @return
	 */
	public static Graph welshPowellAlgo (Graph graph){
		return GreedyColoring.greedyColoring(graph, getOrder(graph));
	}
	
	/**
	 * Return the Order need by the WelshPowellAlgo, which is represented by the degree of vertex, sort by descendant way.
	 * @param graph - the graph to get the Welsh Powell Order
	 * @return the Welsh Powell Order associated with the graph
	 */
	public static int[] getOrder(Graph graph){
		Graph graphTmp = new Graph(graph.getGraph());//Init a temporary graph, to do the sort with
		Arrays.sort(graphTmp.getGraph(), new Comparator<Vertex>() {
			@Override
			public int compare(Vertex vex1, Vertex vex2) {
				if(vex2.degree() < vex1.degree())
					return 1;
				else if (vex2.degree() > vex1.degree())
					return -1;
				return 0;
			}
		});
		return graphTmp.convertVertexAsOrder();
	}
}
