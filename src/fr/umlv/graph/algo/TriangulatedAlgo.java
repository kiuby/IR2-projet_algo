package fr.umlv.graph.algo;

import java.util.LinkedList;
import java.util.List;

import fr.umlv.graph.Graph;
import fr.umlv.graph.Vertex;

/**
 * This class does the color of a graph, by using a triangulated optimization.
 * This class use GreedyColoring, but optimize the order if the graph is triangulated.
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

public class TriangulatedAlgo {
	
	/**
	 * Inner Class, that represent a set : a set is compose by a list of vertex, whereas graph is composed by a tab of Vertex.
	 *
	 */
	public static class Ensemble{
		
		/**
		 * The list of vertex, that represent the set
		 */
		List<Vertex> graphList;
		
		/**
		 * Default constructor. Init our current list of vertex.
		 */
		public Ensemble(){
			graphList = new LinkedList<>();
		}
	}

	/**
	 * Convert a graph, which is composed by a tab of vertex, into a Ensemble, which is composed by a list of vertex.
	 * @param graph - the graph to convert
	 * @return an Ensemble, which have the same vertex than the graph, but store as a list
	 */
	public static Ensemble convertGraphIntoElement(Graph graph){
		Ensemble ens = new Ensemble();
		for(int i=0;i<graph.getVertexCount();i++)
			ens.graphList.add(graph.getVertex(i));
		return ens;
	}
	
	/**
	 * Convert the list of Vertex as an order, similar to the same method in class graph.
	 * @param ensemble - the list of vertex to convert into an order
	 * @return a tab init with the value containing the order of the vertex
	 */
	private static int[] convertElemAsOrder(List<Vertex> ensemble) {
		int [] orderTab = new int [ensemble.size()];//We have sort our Graph, now we have to instantiate our Order tab
		
		for(int i=0;i<ensemble.size();i++)
			orderTab[i]=ensemble.get(i).getIdVertex();
		return orderTab;
	}
	
	/**
	 * Does the job : Split P by Ensemble which are more smaller, and add the vertex when it's process is finished.
	 * @param ens_P - the list of Ensemble, that contains the graph
	 * @param ens_L - the list of vertex, which are the list of order
	 */
	private static void findGoodOrder(List<Ensemble> ens_P, List<Vertex> ens_L) {
		List<Ensemble> ens_PTmp = new LinkedList<>();//Uses only to add during an iteration of ens_P
		Vertex elem_v = ens_P.get(0).graphList.remove(0);
		ens_L.add(elem_v);
		if(ens_P.get(0).graphList.size() == 0)
			ens_P.remove(0);
		
		for(Ensemble E : ens_P){
			splitSubset(ens_PTmp, elem_v, E);
		}
		ens_P.clear();//Copy our temporaly list into our current list P
		ens_P.addAll(ens_PTmp);
		ens_PTmp.clear();
	}

	/**
	 * Split the Ensemble E in two Ensemble, where one is represents the brother of elem_v, and the other one everything else.
	 * Add it to the List of Ensemble ens_PTmp.
	 * @param ens_PTmp - the temporary ensemble to add the split of E
	 * @param elem_v - the element which brothers are use to split E in two
	 * @param E - the Ensemble to split in two parts
	 */
	private static void splitSubset(List<Ensemble> ens_PTmp, Vertex elem_v, Ensemble E) {
		Ensemble V = new Ensemble(), W = new Ensemble();
		List<Vertex> ssEns = E.graphList;
		
		for(Vertex vertex : ssEns){//Split of the subset E
			if(elem_v.hasBrother(vertex.getIdVertex()))
				V.graphList.add(vertex);
			else
				W.graphList.add(vertex);
		}
		
		if(!V.graphList.isEmpty() && !W.graphList.isEmpty()){//Use of a temporaly list, to add during an iteration of P
			ens_PTmp.add(V);
			ens_PTmp.add(W);
		}
		else
			ens_PTmp.add(E);
	}
	
	/**
	 * Do the coloring of the graph, by return a new one, using the optimization of triangulation.
	 * @param graph - the graph to color
	 * @return a new color which is color, using the optimization of triangulation if the graph is triangulated, else no warranty of the minimal color of the graph
	 */
	public static Graph triangulatedAlgo (Graph graph){
		
		Graph graphTmp = new Graph(graph.getGraph());
		
		List<Ensemble> ens_P = new LinkedList<>();
		List<Vertex> ens_L = new LinkedList<>();
		
		ens_P.add(convertGraphIntoElement(graphTmp));//init of P
		while(!ens_P.isEmpty())//the algo
			findGoodOrder(ens_P, ens_L);
		
		return GreedyColoring.greedyColoring(graph, convertElemAsOrder(ens_L));
	}
}
