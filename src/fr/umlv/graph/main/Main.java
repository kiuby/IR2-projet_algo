package fr.umlv.graph.main;

import java.io.File;

import fr.umlv.graph.Graph;
import fr.umlv.graph.algo.GreedyColoring;
import fr.umlv.graph.algo.TriangulatedAlgo;
import fr.umlv.graph.algo.WelshPowellAlgo;
import fr.umlv.graph.util.XMLToGraph;

public class Main {

	static class Param{
		boolean decreaseDegreeOrder = false;// Option -d  : Use the Welsh Powell Algo
		boolean triangulatedOrder = false;//Option -t : Use the Triangulated Algo
		boolean displayList = false;//Option -l : Display the List
		String filename = null;
	}

	public static void printUsage(String[] args){
		System.out.println("Usage : "+args[0]+" filename.xml\n");
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

	private static void initParam(String[] args, Param param) {
		for(String opt : args){

			if(opt.endsWith(".xml") && param.filename == null)//File case
				param.filename = new String(opt);
			else if (opt.endsWith(".xml")){
				System.out.println("Can't defines the files twice.");
				printUsage(args);
				return;
			}

			switch(opt){

			case "-d" : case "--decrease" ://-d case
				if(param.decreaseDegreeOrder == false && param.triangulatedOrder == false)
					param.decreaseDegreeOrder = true;
				else if(param.decreaseDegreeOrder == false){
					System.out.println("You can't use -t and -d at the same time.");
					printUsage(args);
					return;
				}
				else{
					System.out.println("You can't use -d twice.");
					printUsage(args);
					return;
				}
				break;


			case "-t" : case "--triangulated" ://-t case
				if(param.decreaseDegreeOrder == false && param.triangulatedOrder == false)
					param.triangulatedOrder = true;
				else if(param.triangulatedOrder == false){
					System.out.println("You can't use -t and -d at the same time.");
					printUsage(args);
					return;
				}
				else{
					System.out.println("You can't use -t twice.");
					printUsage(args);
					return;
				}
				break;


			case "-l" : case "--liste" ://-l case
				if(param.displayList == false)
					param.displayList = true;
				else{
					System.out.println("You can't use -l twice.");
					printUsage(args);
					return;
				}
				break;
			}
		}
	}

	public static void doTheJob(String[] args){
		Param param = new Param();
		initParam(args, param);

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


	public static void main(String[] args) {
		doTheJob(args);
	}
}
