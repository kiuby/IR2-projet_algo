package fr.umlv.graph.main;

import java.io.File;

import fr.umlv.graph.Graph;
import fr.umlv.graph.algo.GreedyColoring;
import fr.umlv.graph.algo.TriangulatedAlgo;
import fr.umlv.graph.algo.WelshPowellAlgo;
import fr.umlv.graph.util.XMLToGraph;

/**
 * This class does the program, by launching the good algorithm, depending on parameters.
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

public class Main {

	/**
	 * The inner Class Param represents all local variable used in order to manage all arguments of the main program. 
	 *
	 */
	static class Param{
		boolean decreaseDegreeOrder = false;// Option -d  : Use the Welsh Powell Algo
		boolean triangulatedOrder = false;//Option -t : Use the Triangulated Algo
		boolean displayList = false;//Option -l : Display the List
		String filename = null;
	}

	/**
	 * Display the current Usage of the program.
	 * @param args
	 */
	public static void printUsage(String[] args){
		System.out.println("Usage : projetAlgo.jar filename.xml\n");
		System.out.println("\nBy default, Use Greedy Algorithm with natural Order, and display the result on dot format.\nArguments : \n\r-d --decrease : Use Greedy Algorithm with vertex order descendently\n\r-t --triangulated : Use Greedy Algorithm with Optimized Order for Triangulated graph" +
				"\n\r-l --list : Display only the order Tab");
	}

	/**
	 * Display a tab of order
	 * @param order - the tab to display
	 */
	public static void displayOrder(int [] order){
		System.out.print("Order : { ");
		for(int i=0;i<order.length;i++){
			System.out.print(" "+order[i]);
			if(i+1<order.length)
				System.out.print(",");
		}
		System.out.println(" }");
	}

	/**
	 * Initialize all parameters : refuses the same arguments twice, -d and -t at the same time, and any other arguments which are not recognized
	 * @param args - the Program argument
	 * @param param - the structure to initialize
	 */
	private static boolean initParam(String[] args, Param param) {
		for(String opt : args){

			if(opt.endsWith(".xml") && param.filename == null)//File case
				param.filename = new String(opt);
			else if (opt.endsWith(".xml")){
				System.out.println("Can't defines the files twice.");
				printUsage(args);
				return false;
			}
			else
				switch(opt){

				case "-d" : case "--decrease" ://-d case
					if(param.decreaseDegreeOrder == false && param.triangulatedOrder == false){
						param.decreaseDegreeOrder = true;
						break;
					}

					if(param.decreaseDegreeOrder == false)
						System.out.println("You can't use -t and -d at the same time.");
					else
						System.out.println("You can't use -d twice.");
					printUsage(args);
					return false;

				case "-t" : case "--triangulated" ://-t case
					if(param.decreaseDegreeOrder == false && param.triangulatedOrder == false){
						param.triangulatedOrder = true;
						break;
					}
					if(param.triangulatedOrder == false)
						System.out.println("You can't use -t and -d at the same time.");
					else
						System.out.println("You can't use -t twice.");
					printUsage(args);
					return false;


				case "-l" : case "--list" ://-l case
					if(param.displayList == false)
						param.displayList = true;
					else{
						System.out.println("You can't use -l twice.");
						printUsage(args);
						return false;
					}
					break;
					
				default :
					System.out.println("Can't recognize the arguments.");
					printUsage(args);
					return false;
				}
		}
		return true;
	}

	/**
	 * Do the Work of the project : Initialize all parameters, and do the corresponding algorithm, depending on parameters.
	 * @param args - the Program argument
	 */
	public static void doTheJob(String[] args){
		Param param = new Param();
		if(initParam(args, param) == false)
			return;

		if(param.filename == null){
			System.out.println("You have to specified an xml file.");
			printUsage(args);
			return;
		}

		Graph graph = XMLToGraph.getGraphFromXml(new File(param.filename));

		if(param.displayList){//Display the listOrder
			if(param.decreaseDegreeOrder == false && param.triangulatedOrder == false)
				displayOrder(GreedyColoring.getOrder(graph));
			else if (param.triangulatedOrder == false)
				displayOrder(WelshPowellAlgo.getOrder(graph));
			else if (param.decreaseDegreeOrder == false)
				displayOrder(TriangulatedAlgo.getOrder(graph));
		}

		else{//Display the graph, where toString represents the dot format
			if(param.decreaseDegreeOrder == false && param.triangulatedOrder == false)
				System.out.println(GreedyColoring.greedyColoring(graph, GreedyColoring.getOrder(graph)));
			else if (param.triangulatedOrder == false)
				System.out.println(WelshPowellAlgo.welshPowellAlgo(graph));
			else if (param.decreaseDegreeOrder == false)
				System.out.println(TriangulatedAlgo.triangulatedAlgo(graph));
		}
	}

	/**
	 * The main method, which call doTheJob.
	 * @param args - the Program argument
	 */
	public static void main(String[] args) {
		doTheJob(args);
	}
}
