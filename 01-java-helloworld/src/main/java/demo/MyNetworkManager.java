package demo;

import org.protelis.lang.datatype.DeviceUID;
import org.protelis.vm.CodePath;
import org.protelis.vm.NetworkManager;

import java.util.HashMap;
import java.util.Map;

public class MyNetworkManager implements NetworkManager {

    private Map<CodePath, Object> toSend;
    private final Map<DeviceUID, Map<CodePath, Object>> states = new HashMap<>();

    public MyNetworkManager() { }

    public Map<CodePath, Object> getToSend() {
        return toSend;
    }

    public void setNeighborState(DeviceUID uid, Map<CodePath, Object> msg) {
        states.put(uid, msg);
    }

    @Override
    public Map<DeviceUID, Map<CodePath, Object>> getNeighborState() {
        return states;
    }

    @Override
    public void shareState(Map<CodePath, Object> toSend) {
        this.toSend = toSend;
    }
}
