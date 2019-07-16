package demo;

import org.protelis.lang.datatype.DeviceUID;

public class MyDeviceUID implements DeviceUID, Comparable<MyDeviceUID>{

    private final int uid;

    public MyDeviceUID(final int uid) {
        this.uid = uid;
    }

    public int getUID() {
        return uid;
    }

    @Override
    public int compareTo(MyDeviceUID o) {
        if (this.getClass() == o.getClass()) {
            return this.getUID() - o.getUID();
        } else {
            return 1;
        }
    }
}
