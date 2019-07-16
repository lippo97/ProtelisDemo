package demo;

import org.protelis.lang.datatype.DeviceUID;
import org.protelis.vm.NetworkManager;
import org.protelis.vm.impl.AbstractExecutionContext;
import org.protelis.vm.impl.SimpleExecutionEnvironment;

public class DeviceCapabilities extends AbstractExecutionContext implements Speaker {

    private final MyDeviceUID uid;

    public DeviceCapabilities(final int uid, final NetworkManager ntmgr) {
        super(new SimpleExecutionEnvironment(), ntmgr);
        this.uid = new MyDeviceUID(uid);
    }

    @Override
    protected AbstractExecutionContext instance() {
        return new DeviceCapabilities(this.uid.getUID(), getNetworkManager());
    }

    @Override
    public DeviceUID getDeviceUID() {
        return uid;
    }

    @Override
    public Number getCurrentTime() {
        return System.currentTimeMillis();
    }

    @Override
    public double nextRandomDouble() {
        return Math.random();
    }
}
