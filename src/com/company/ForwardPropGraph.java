package com.company;

import java.util.ArrayList;

/**
 * Created by Denis on 17.11.2016.
 */
public class ForwardPropGraph {

    double[][][] weights;

    public ForwardPropGraph(double[][][] weights) {
        this.weights = weights;
    }

    public int stepsCount() {
        return weights.length;
    }

    public double minRoute() {
        int steps = stepsCount();
        ArrayList<Double>[] minRoutesFromVertex = new ArrayList[steps + 1];
        for (int i = 0; i < steps; i++) {
            int layerSize = layerSize(i);
            minRoutesFromVertex[i] = new ArrayList<>();
            for (int j = 0; j < layerSize; j++) {
                minRoutesFromVertex[i].add(Double.POSITIVE_INFINITY);
            }
            minRoutesFromVertex[steps] = new ArrayList<>();
            minRoutesFromVertex[steps].add(0.0);
        }
        for (int i = steps - 1; i >= 0; i--) {
            int secondLayerSize = layerSize(i + 1);
            for (int j = 0; j < secondLayerSize; j++) {
                int firstLayerSize = layerSize(i);
                double minRouteCost = minRoutesFromVertex[i + 1].get(j);
                for (int k = 0; k < firstLayerSize; k++) {
                    double weight = weights[i][k][j];
                    if (weight < 0) continue;
                    double entireRouteCost = minRouteCost + weight;
                    if (minRoutesFromVertex[i].get(k) > entireRouteCost) {
                        minRoutesFromVertex[i].set(k, entireRouteCost);
                        System.out.println("step: " + i);
                        System.out.println("vert: " + k);
                        System.out.println("route: " + entireRouteCost);
                    }
                }
            }
        }
        return minRoutesFromVertex[0].get(0);
    }

    public double maxRoute() {
        int steps = stepsCount();
        ArrayList<Double>[] maxRoutesFromVertex = new ArrayList[steps + 1];
        for (int i = 0; i < steps; i++) {
            int layerSize = layerSize(i);
            maxRoutesFromVertex[i] = new ArrayList<>();
            for (int j = 0; j < layerSize; j++) {
                maxRoutesFromVertex[i].add(Double.NEGATIVE_INFINITY);
            }
            maxRoutesFromVertex[steps] = new ArrayList<>();
            maxRoutesFromVertex[steps].add(0.0);
        }
        for (int i = steps - 1; i >= 0; i--) {
            int secondLayerSize = layerSize(i + 1);
            for (int j = 0; j < secondLayerSize; j++) {
                int firstLayerSize = layerSize(i);
                double minRouteCost = maxRoutesFromVertex[i + 1].get(j);
                for (int k = 0; k < firstLayerSize; k++) {
                    double weight = weights[i][k][j];
                    if (weight < 0) continue;
                    double entireRouteCost = minRouteCost + weight;
                    if (maxRoutesFromVertex[i].get(k) < entireRouteCost) {
                        maxRoutesFromVertex[i].set(k, entireRouteCost);
                        System.out.println("step: " + i);
                        System.out.println("vert: " + k);
                        System.out.println("route: " + entireRouteCost);
                    }
                }
            }
        }
        return maxRoutesFromVertex[0].get(0);
    }

    private int layerSize(int num) {
        if (num == stepsCount()) {
            return 1;
        } else {
            return weights[num].length;
        }
    }

}
