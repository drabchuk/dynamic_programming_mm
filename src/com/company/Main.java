package com.company;

import static com.company.Task2.*;

public class Main {

    public static void main(String[] args) {
        ForwardPropGraph graph = new ForwardPropGraph(W);
        double minRoute = graph.maxRoute();
        System.out.println(minRoute);
    }
}
