package demo;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.protelis.vm.NetworkManager;
import org.protelis.vm.ProtelisProgram;
import org.protelis.vm.ProtelisVM;

public class Device {

    private final ProtelisVM vm;
    private final DeviceCapabilities deviceCapabilities;
    private final MyNetworkManager netmgr;

    public Device(ProtelisProgram program, int uid, MyNetworkManager netmgr) {
        this.netmgr = netmgr;
        this.deviceCapabilities = new DeviceCapabilities(uid, netmgr);
        this.vm = new ProtelisVM(program, deviceCapabilities);
    }

    public MyNetworkManager getNetworkManager() { return netmgr; }

    public DeviceCapabilities getDeviceCapabilities() {
        return deviceCapabilities;
    }

    public void runCycle() {
        this.vm.runCycle();
    }

    public void sendMessages() throws IllegalStateException {
        this.netmgr.sendMessages();
    }
}
