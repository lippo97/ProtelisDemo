package demo

import com.google.common.hash.Hashing
import org.protelis.vm.ProtelisProgram
import org.protelis.vm.ProtelisVM
import org.protelis.vm.impl.HashingCodePathFactory

class Device(program: ProtelisProgram, uid: Int, port: Int) {
    //val netmgr = EmulatedNetworkManager(IntDeviceUID(uid))
    private val netmgr = SocketNetworkManager(IntDeviceUID(uid), port)
    val deviceCapabilities = DeviceCapabilities(uid, netmgr, HashingCodePathFactory(Hashing.sha256()))
    private val vm = ProtelisVM(program, deviceCapabilities)
    var neighbors: Set<IPv4Host> = emptySet()

    fun runNetwork()  { this.netmgr.run() }
    fun stopNetwork() { this.netmgr.stop() }
    fun runCycle()    { this.vm.runCycle() }
    fun sendMessage() { netmgr.sendMessage(neighbors) }
}
