package demo;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.protelis.lang.ProtelisLoader;
import org.protelis.vm.ProtelisProgram;

import java.util.ArrayList;
import java.util.List;

public class HelloProtelis {

    private final static String protelisModuleName = "hello";
    private final static int N = 5;
    private final static int iterations = 3;
    private final static List<Device> devices = new ArrayList<>();

    public static void main(String[] args) {
        useJgrapht();
    }

    private static void useJgrapht() {
        // Initialize a graph
        Graph<Device, DefaultEdge> g = new DefaultUndirectedGraph<>(DefaultEdge.class);
        // Initialize some devices
        for (int i = 0; i < N; i++) {
            ProtelisProgram program = ProtelisLoader.parse(protelisModuleName);
            Device d = new Device(program, i);
            devices.add(d);
        }
        // Make the first one leader
        devices.get(0).getDeviceCapabilities().getExecutionEnvironment().put("leader", true);
        // Add the devices into the graph and link them as a ring network
        g.addVertex(devices.get(0));
        for (int i = 1; i < devices.size(); i++) {
            g.addVertex(devices.get(i));
            g.addEdge(devices.get(i - 1), devices.get(i));
        }
        g.addEdge(devices.get(devices.size()-1), devices.get(0));
        // Let the devices know the network topology
        devices.forEach(d -> d.setNetwork(g));
        // Let the devices execute 3 times
        for (int i = 0; i < iterations; i++) {
            devices.forEach(Device::runCycle);
            devices.forEach(Device::sendMessages);
        }
    }
}
