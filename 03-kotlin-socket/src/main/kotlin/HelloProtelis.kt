package demo

import com.uchuhimo.konf.Config
import org.protelis.lang.ProtelisLoader

class Main {

    private var devices: List<Device> = emptyList()
    private val config = Config { addSpec(ProtelisConfigSpec) }
            .from.toml.resource("config.toml")
    private val protelisModuleName = config[ProtelisConfigSpec.protelisModuleName]
    private val iterations = config[ProtelisConfigSpec.iterations]
    private val nodes = config[ProtelisConfigSpec.nodes]

    fun hello() {
        nodes.forEach {
            val program = ProtelisLoader.parse(protelisModuleName)
            val node = Device(program, it.id, it.hostandport.port)
            if (it.leader) {
                node.deviceCapabilities.executionEnvironment.put("leader", true)
            }
            node.neighbors = it.neighbors
            node.runNetwork()
            devices += node
        }

        repeat(iterations) {
            devices.forEach { it.runCycle() }
            devices.forEach { it.sendMessage() }
        }

        devices.forEach { it.stopNetwork() }
    }
}

fun main() {
    Main().hello()
}

