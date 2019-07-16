package demo

import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DefaultUndirectedGraph
import org.protelis.lang.ProtelisLoader

class HelloProtelis {

    private val protelisModuleName = "hello"
    private val n = 3
    private val devices = ArrayList<Device>()

    fun hello() {
        val network = initializeNetwork()
        // Set the leader
        setLeader(0)
        devices.forEach {
            it.network = network
        }
        syncRunNTimes(3)
    }

    private fun initializeNetwork() : DefaultUndirectedGraph<Device, DefaultEdge> {
        // Initialize a graph
        val g = DefaultUndirectedGraph<Device, DefaultEdge>(DefaultEdge::class.java)
        // Initialize n nodes
        repeat(n) {
            val program = ProtelisLoader.parse(protelisModuleName)
            val d = Device(program, it)
            devices.add(d)
            g.addVertex(d)
        }
        // Link the nodes as a ring network
        repeat(n) {
            g.addEdge(
                    devices[it],
                    devices[(it + 1) % n])
        }
        return g
    }

    private fun setLeader(id: Int) =
        devices[id].deviceCapabilities.executionEnvironment.put("leader", true)


    private fun syncRunNTimes(n: Int) = repeat(n) {
        devices.forEach { it.runCycle() }
        devices.forEach { it.sendMessages() }
    }
}

fun main() {
    HelloProtelis().hello()
}

