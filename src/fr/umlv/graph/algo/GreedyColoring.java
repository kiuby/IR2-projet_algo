package fr.umlv.graph.algo;

import fr.umlv.graph.Edge;
import fr.umlv.graph.Graph;

/**
 * This class does the color of a graph, by using a greedy algorithm.
 * Moreover, the color respect an array of order concerning vertex, to know the order of the process. 
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

public class GreedyColoring {

	/**
	 * Do the coloring of a graph, by process the vertex as the order given as parameter. Be care, the initial graph is not modified, and a new one is returned.
	 * @param graph - the graph to color
	 * @param orderVertex - the order concerning the vertex 
	 * @return a new graph, which is color using greedy algorithm, and the order of vertex given.
	 */
	public static Graph greedyColoring (Graph graph, int[] orderVertex){
		Graph graphColor = new Graph(graph.getGraph());
		for(int i=0;i<orderVertex.length;i++)
			checkAndSetValidColor(graphColor, orderVertex[i]);
		return graphColor;
	}
	
	/**
	 * Check what minimal color is needed to color the vertex at position indexVertex, and color it.
	 * @param graphColor - the graph to know all vertex
	 * @param indexVertex - the index of the vertex to check the minimal color.
	 */
	
	private static void checkAndSetValidColor(Graph graphColor, int indexVertex){
		for(int j=0;;j++){
			if(isValidColor(graphColor, indexVertex, j)){
				graphColor.getVertex(indexVertex).setColor(j);
				break;
			}
		}
	}

	/**
	 * Check if a color of the vertex can be possible, or is in conflict with his brothers.
	 * @param graphColor - the graph to know all vertex
	 * @param indexVertex - the index of the vertex to check color.
	 * @param color - the color to check
	 * @return true if the color can be possible, else false
	 */
	private static boolean isValidColor (Graph graphColor, int indexVertex, int color) {
		for(Edge edge : graphColor.getVertex(indexVertex).getBrothers())
			if(graphColor.getVertex(edge.getDst()).getColor() == color)
				return false;
		return true;
	}
}
