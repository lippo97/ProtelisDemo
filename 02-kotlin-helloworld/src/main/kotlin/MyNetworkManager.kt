package demo

import org.protelis.lang.datatype.DeviceUID
import org.protelis.vm.CodePath
import org.protelis.vm.NetworkManager

interface MyNetworkManager : NetworkManager {
    fun sendMessages()
}