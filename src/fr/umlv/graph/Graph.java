package fr.umlv.graph;


/**
 * This class represents a Graph, which is composed by a tab of Vertex.
 * This class contains all mains methods used to work with a graph : Add or remove edge, get the number of colors of the graph, get the Dot format, ...
 * Graph are no oriented, related, and have no weight in their edge.
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

public class Graph {

	/**
	 * An array of Vertex, which represents our graph.
	 */
	private Vertex[] graph;
	
	/**
	 * Default constructor. Initialize a graph, with a size equals to verticesCount.
	 * @param verticesCount - size of the new graph
	 */
	public Graph(int verticesCount) {
		if(verticesCount <= 0 )
			throw new IllegalArgumentException("VerticesCount cannot be negative");
		graph = new Vertex[verticesCount];
		
		for(int i=0;i<verticesCount;i++)
			graph[i] = new Vertex(i);
	}
	
	/**
	 * Init a graph, by copying all vertex and edge of graph, into a new graph.
	 * @param graph - the tab of vertex to convert into a graph
	 */
	public Graph(Vertex[] graph){
		this.graph = new Vertex[graph.length];
		for(int i=0; i<graph.length; i++){
			this.graph [i] = new Vertex(i);
			for(Edge edge : graph[i].getBrothers())
				this.graph[i].addEdge(edge);
		}
	}
	
	/**
	 * Return the list of vertex which compose this graph.
	 * @return the list of vertex which compose this graph
	 */
	public Vertex[] getGraph() {
		return graph;
	}
	
	/**
	 * Return the number of Vertex of this graph.
	 * @return the number of Vertex of this graph
	 */
	public int getVertexCount() {
		return graph.length;
	}
	
	/**
	 * Return the Vertex which is in the position indexVertex in the graph.
	 * @param indexVertex - the index of the vertex to return
	 * @return the vertex which is in the position indexVertex
	 */
	public Vertex getVertex(int indexVertex) {
		if(indexVertex <0 || indexVertex > getVertexCount())
			throw new IllegalArgumentException();
		return graph[indexVertex];
	}
	
	/**
	 * Return the number of color different in the graph, which are store in every vertex. Be care, if the graph have not been colored by algorithm, return Integer.MAX_VALUE.
	 * @return the number of color different in the graph but if the graph have not been colored by algorithm, return Integer.MAX_VALUE
	 */
	public int getColors(){
		int colorMax=getVertex(0).getColor();
		for(Vertex vertex : graph)
			if(vertex.getColor() > colorMax)
				colorMax = vertex.getColor();
		return colorMax+1;
	}
	
	/**
	 * Check if a graph has and edge in this tab of vertex.
	 * @param edge - the edge to know if it is in the graph
	 * @return true if edge is in the graph, else false
	 */
	public boolean hasEdge(Edge edge) {
		if(edge.getSrc()> graph.length)
			throw new IllegalStateException();
		return graph[edge.getSrc()].contains(edge);
	}

	/**
	 * Add an edge into a graph. Be care, because we have no oriented graph, the add do the same job with revert value of edge.
	 * @param edge - the edge to add into our graph.
	 * @see Edge
	 * @return true if the edge wasn't in the graph before, else false
	 */
	public boolean addEdge(Edge edge) {
		if(!hasEdge(edge)){//Because it's no oriented
			graph[edge.getSrc()].addEdge(edge);
			graph[edge.getDst()].addEdge(edge.revert());
			return true;
		}
		return false;
	}
	
	/**
	 * Remove an edge into a graph. Be care, does the same job with revert value of edge, because we keep an no oriented graph
	 * @param edge - the edge to remore into our graph.
	 * @return true if the edge was in the graph before, else false
	 */
	public boolean removeEdge(Edge edge) {
		if(hasEdge(edge)){//Because it's no oriented
			graph[edge.getSrc()].removeEdge(edge);
			graph[edge.getDst()].removeEdge(edge.revert());
			return true;
		}
		return false;
	}
	
	/**
	 * Convert the current tab of vertex into a tab of order, using vertex ID. (Use if the graph is sort).
	 * @return the tab of order made by this graph
	 */
	public int[] convertVertexAsOrder(){
		int [] orderTab = new int [getVertexCount()];//We have sort our Graph, now we have to instantiate our Order tab
		
		for(int i=0;i<getVertexCount();i++)
			orderTab[i]=getVertex(i).getIdVertex();
		return orderTab;
	}
	
	/**
	 * Return the dot format of a graph. Be care, the color have to been initialized with an algorithm before this call, or the color will be equals to Integer.MAX_VALUE.
	 * @return a string which represents the dot format of this graph.
	 */
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder().append("graph G {\n");
		
		for(int i=0;i<getVertexCount();i++)//print all vertex
			res.append("\t"+i+" [label=\""+graph[i].getIdVertex()+"["+graph[i].getColor()+"]"+"\"]\n");
		res.append("\n");
		
		for(Vertex vertex : graph)//print all edges
			for(Edge edge : vertex.getBrothers())
				if(edge.getSrc() <= edge.getDst())
					res.append(edge+"\n");
		
		return res.append("}").toString();
	}

}