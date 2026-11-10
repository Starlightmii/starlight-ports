import android.os.IBinder;
import android.os.Parcel;

public class EngProbe {
    public static void main(String[] a) throws Exception {
        String name = "vendor.oplus.hardware.engineer.IEngineer/default";
        Class<?> sm = Class.forName("android.os.ServiceManager");
        IBinder b = (IBinder) sm.getMethod("getService", String.class).invoke(null, name);
        System.out.println("binder=" + b);
        if (b == null) {
            return;
        }
        Parcel d = Parcel.obtain();
        Parcel r = Parcel.obtain();
        d.writeInterfaceToken("vendor.oplus.hardware.engineer.IEngineer");
        d.writeInt(100005);
        long t = System.nanoTime();
        boolean ok = b.transact(1, d, r, 0);
        long ms = (System.nanoTime() - t) / 1000000L;
        System.out.println("transact ok=" + ok + " ms=" + ms + " replySize=" + r.dataSize());
        r.recycle();
        d.recycle();
    }
}
