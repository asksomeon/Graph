package com.company.graph2;

import java.util.HashMap;

public interface GraphTasks2 {

    HashMap<Integer, Integer> dijkstraSearch(int [][] adjacencyMatrix, int startIndex);
    Integer primaAlgorithm(int [][] adjacencyMatrix);
    Integer kraskalAlgorithm(int [][] adjacencyMatrix);
}
