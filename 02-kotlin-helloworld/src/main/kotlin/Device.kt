package demo

import org.jgrapht.Graphs
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DefaultUndirectedGraph
import org.protelis.vm.ProtelisProgram
import org.protelis.vm.ProtelisVM

class Device(program: ProtelisProgram, uid: Int) {
    val netmgr = EmulatedNetworkManager(IntDeviceUID(uid))
    val deviceCapabilities = DeviceCapabilities(uid, netmgr)
    private val vm = ProtelisVM(program, deviceCapabilities)
    var network = DefaultUndirectedGraph<Device, DefaultEdge>(DefaultEdge::class.java)

    init {
        network.addVertex(this)
    }

    fun runCycle() = this.vm.runCycle()
    fun sendMessages() = this.netmgr.sendMessages(Graphs.neighborSetOf(network, this))
}
