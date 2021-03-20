package com.company.graphtasks2;

import java.util.*;


public class GraphTasks2Solution implements GraphTasks2 {


    @Override
    public HashMap<Integer, Integer> dijkstraSearch(int[][] adjacencyMatrix, int startIndex) {

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        HashMap<Integer, Integer> ourMap = new HashMap<>();


        ourMap.put(startIndex, 0);
        int start = startIndex;
        deque.addLast(startIndex);

        while (!deque.isEmpty()) {

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[startIndex][i] != 0) {
                    if (!ourMap.containsKey(i)) {
                        deque.addLast(i);
                        ourMap.put(i, adjacencyMatrix[startIndex][i] + ourMap.getOrDefault(startIndex, 0));
                    } else {
                        if ((ourMap.get(startIndex) != null) & (ourMap.get(i) != null)) {
                            if ((ourMap.get(startIndex) + adjacencyMatrix[startIndex][i]) < ourMap.get(i)) {
                                ourMap.put(i, ourMap.get(startIndex) + adjacencyMatrix[startIndex][i]);
                            }
                        }
                    }
                }
            }

            deque.pollFirst();
            if (!deque.isEmpty()) {
                startIndex = deque.peekFirst();
            }
        }
        ourMap.remove(start);
        return ourMap;
    }

    @Override
    public Integer primaAlgorithm(int[][] adjacencyMatrix) {
        HashSet<Integer> firstSet = new HashSet<>();
        HashSet<Integer> secondSet = new HashSet<>();

        int weight = 0;

        firstSet.add(0);
        for (int i = 1; i < adjacencyMatrix.length; i++) {
            secondSet.add(i);
        }

        int temp = 0;
        while (firstSet.size() != adjacencyMatrix.length) {

            int min = Integer.MAX_VALUE;

            for (int two : secondSet) {
                for (int one : firstSet) {
                    if ((adjacencyMatrix[two][one] <= min) & (adjacencyMatrix[two][one] != 0)) {
                        min = adjacencyMatrix[two][one];
                        temp = two;
                    }
                }
            }

            secondSet.remove(temp);
            firstSet.add(temp);
            weight += min;
        }
        return weight;

    }

    @Override
    public Integer kraskalAlgorithm(int[][] adjacencyMatrix) {

        return ostovCrascalGo(adjacencyMatrix);

    }


    public int ostovCrascalGo(int[][] matrix) {

        ArrayList<Edge> edges = new ArrayList<>();

        int eCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if ((i < j) & (matrix[i][j] != 0)) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                    eCount++;
                }
            }
        }

        SetNumbers setNumbers = new SetNumbers(eCount);

        Collections.sort(edges);

        int result = 0;
        for (Edge edge : edges) {
            if (setNumbers.unification(edge.u, edge.v)) {
                result += edge.weight;
            }
        }
        return result;
    }


    public class SetNumbers {
        int[] setNum;

        SetNumbers(int size) {
            setNum = new int[size];
            for (int i = 0; i < size; i++) {
                setNum[i] = i;
            }
        }

        int getSet(int x) {
            if (x == setNum[x]) {
                return x;
            } else {
                setNum[x] = getSet(setNum[x]);
                return setNum[x];
            }
        }

        boolean unification(int u, int v) {
            u = getSet(u);
            v = getSet(v);
            if (u == v) return false;
            for (int i = 0; i < setNum.length; i++) {
                if (setNum[i] == v) {
                    setNum[i] = u;
                }
            }
            return true;
        }
    }

    public class Edge implements Comparable<Edge> {

        int u;
        int v;
        int weight;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.weight = w;
        }

        @Override
        public int compareTo(Edge edge) {
            if (weight != edge.weight) {
                return weight < edge.weight ? -1 : 1;
            }
            return 0;
        }
    }
}
