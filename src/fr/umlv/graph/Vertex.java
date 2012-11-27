package fr.umlv.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a Vertex, which is compose by a list of Edge, a color, and an ID.
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

public class Vertex {
	
	/**
	 * Define a constant to represent empty color, which is Integer.MAX_VALUE.
	 */
	public final int COLOR_EMPTY = Integer.MAX_VALUE;
	
	/**
	 * The color of the vertex.
	 */
	private int color;
	
	/**
	 * The id of the vertex.
	 */
	private final int idVertex;
	
	/**
	 * The Queue of edge, which represents all brothers link of a vertex.
	 */
	private final Queue<Edge> brothers;
	
	/**
	 * Default constructor, which init the color as empty, and set the id as the paramater.
	 * @param idVertex - the id of the new Vertex
	 */
	public Vertex(int idVertex) {
		brothers = new LinkedList<>();
		setColor(COLOR_EMPTY);
		this.idVertex = idVertex;
	}
	
	/**
	 * Return the Queue of edge, which represents all brothers of this vertex.
	 * @return the Queue of edge that represents all brother of this vertex
	 */
	public Queue<Edge> getBrothers() {
		return brothers;
	}
	
	/**
	 * Return the id of this vertex.
	 * @return the id of this vertex
	 */
	public int getIdVertex() {
		return idVertex;
	}
	
	/**
	 * Return the color of this vertex.
	 * @return the color of this vertex
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * Set the color with the one given in parameter.
	 * @param color
	 */
	public void setColor(int color) {
		this.color = color;
	}

	
	/**
	 * Check if the vertex has the vertex given in parameter as a brother, which is represents by an edge with this vertex in destination.
	 * @param vertex - the vertex to know if it is a friend
	 * @return true if the vertex is a brother, else false
	 */
	public boolean hasBrother(int vertex){
		if(vertex <0)
			throw new IllegalArgumentException();
		return contains(new Edge(idVertex, vertex));
	}
	
	/**
	 * Return the degree of the vertex, which is represented by the number of edge that he contains.
	 * @return the degree of this vertex
	 */
	public int degree() {
		return brothers.size();
	}
	
	/**
	 * Check if the vertex contains an edge or not.
	 * @param edge - the edge to check if he belong to the vertex
	 * @return true if the edge belong to the vertex, else false
	 */
	public boolean contains(Edge edge) {
		return brothers.contains(edge);
	}
	
	/**
	 * Add an edge into the vertex.
	 * @param edge - the edge to add
	 * @return - true if the edge has been added, else false
	 */
	public boolean addEdge(Edge edge) {
		if(!contains(edge))
			return brothers.add(edge);
		return false;
	}
	
	/**
	 * Remove an edge into the vertex.
	 * @param edge - the edge to remove
	 * @return - true if the edge has been removed, else false
	 */
	public boolean removeEdge(Edge edge) {
		return brothers.remove(edge);
	}
}
