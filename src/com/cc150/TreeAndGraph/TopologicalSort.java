package com.cc150.TreeAndGraph;

import com.leetcode.core.DirectedGraph;
import com.leetcode.core.Vertex;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.*;

/**
 * Created by Xiaomeng on 11/25/2014.
 */
public class TopologicalSort {
    public static class TimeRecorder {
        private int time = 0;

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    public static Vertex[] topologicalSort(DirectedGraph graph) {
        Set<Vertex> vertexSet = graph.getVertexSet();
        if (vertexSet.size() < 2) {
            return vertexSet.toArray(new Vertex[0]);
        }

        LinkedList<Vertex> sortedList = new LinkedList<Vertex>();
        TimeRecorder recorder = new TimeRecorder();

        for (Vertex vertex : vertexSet) {
            if (vertex.color == Vertex.Color.WHITE) {
                visitVertex(graph, vertex, recorder, sortedList);
            }
        }

        return sortedList.toArray(new Vertex[0]);
    }

    /**
     * 深度优先搜索(Depth First Search)
     */
    public static void visitVertex(DirectedGraph graph, Vertex vertex, TimeRecorder recorder, LinkedList<Vertex> sortedList) {
        recorder.time += 1;
        vertex.color = Vertex.Color.GRAY;
        vertex.discover = recorder.time;

        Map<Vertex, List<Vertex>> edgeMap = graph.getAdjacencys();
        List<Vertex> adjacencys = edgeMap.get(vertex);
        if (adjacencys != null && adjacencys.size() > 0) {
            for (Vertex adjacency : adjacencys) {
                if (adjacency.color == Vertex.Color.WHITE) {
                    adjacency.parent = vertex;
                    visitVertex(graph, adjacency, recorder, sortedList);
                }
            }
        }

        recorder.time += 1;
        vertex.color = Vertex.Color.BLACK;
        vertex.finish = recorder.time;
        sortedList.addLast(vertex);
    }

    public static void printVertex(Vertex[] Vertexs) {
        for (Vertex vertex : Vertexs) {
            System.out.println(vertex.getName() + "  discover time:"
                    + vertex.getDiscover() + "  finish time:"
                    + vertex.getFinish());
        }
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();
        Set<Vertex> vertexSet = graph.getVertexSet();
        Map<Vertex, List<Vertex>> edgeMap = graph.getAdjacencys();

        Vertex aVertex = new Vertex('a');
        Vertex bVertex = new Vertex('b');
        Vertex cVertex = new Vertex('c');
        Vertex dVertex = new Vertex('d');

        vertexSet.add(aVertex);
        vertexSet.add(bVertex);
        vertexSet.add(cVertex);
        vertexSet.add(dVertex);

        edgeMap.put(aVertex, new ArrayList<Vertex>());
        edgeMap.put(bVertex, new ArrayList<Vertex>());
        edgeMap.put(dVertex, new ArrayList<Vertex>());

        edgeMap.get(bVertex).add(dVertex);
        edgeMap.get(bVertex).add(aVertex);
        edgeMap.get(dVertex).add(aVertex);
        edgeMap.get(aVertex).add(cVertex);

        Vertex[] sortedVertexs = topologicalSort(graph);
        printVertex(sortedVertexs);
    }
}
