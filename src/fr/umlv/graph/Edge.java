package fr.umlv.graph;

/**
 * This class represents an Edge, which is only define by a source and destination vertex Id.
 * Edge are not valued in this project.
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

public class Edge {
	
	/**
	 * The source of the edge.
	 */
	private final int src;
	
	/**
	 * The destination of the edge.
	 */
	private final int dst;
	
	/**
	 * Default constructor, which init an edge with the corresponding source and destination, which represents a Vertex Id.
	 * @param src - The ID vertex, where begin the edge
	 * @param dst - The ID vertex, where end the edge
	 * @throws IllegalArgumentException - If the src is equals to destination, because no loop for vertex, or if the src and dst values are negative
	 */
	public Edge(int src, int dst){
		if(src == dst)
			throw new IllegalArgumentException("An edge can't have the same src than dst");
		if(src < 0 || dst < 0)
			throw new IllegalArgumentException("the src or dst can't have a negative value");
		this.src=src;
		this.dst=dst;
	}

	/**
	 * Return the source of the edge.
	 * @return the source of the edge
	 */
	public int getSrc() {
		return src;
	}

	/**
	 * Return the destination of the edge.
	 * @return the destination of the edge
	 */
	public int getDst() {
		return dst;
	}
	
	/**
	 * Return a new Edge, which is revert : His source and his destination have been switched.
	 * @return a new Edge, which is revert
	 */
	public Edge revert (){
		return new Edge(dst, src);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dst;
		result = prime * result + src;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (dst != other.dst)
			return false;
		if (src != other.src)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\t"+src+" -- "+dst;
	}
}
