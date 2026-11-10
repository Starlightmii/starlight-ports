package com.oplus.tingle.ipc.serviceproxy;

import android.content.Context;
import com.oplus.tingle.ipc.SlaveBinder;
import com.oplus.tingle.ipc.utils.Logger;
import java.lang.reflect.Proxy;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SystemServiceProxy {
    public static String TAG;
    public final ThreadLocal mLocalKey = new ThreadLocal();
    public final ReentrantLock mLock = new ReentrantLock(true);
    public Object mOriginalService;
    public Object mProxy;
    public String mServiceName;
    public boolean mShouldUseLock;
    public SlaveBinder mSlaveBinder;

    public SystemServiceProxy() {
        TAG = getClass().getName();
        this.mShouldUseLock = true;
    }

    private void setServiceToProxyInternal(Context context) {
        synchronized (this) {
            try {
                if (this.mSlaveBinder == null) {
                    if (context.getApplicationContext() != null) {
                        init(context.getApplicationContext());
                    } else {
                        init(context);
                    }
                }
                this.mLocalKey.set(Thread.currentThread().getName());
                resetServiceCache(context, this.mProxy);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void cleanSystemService(Context context) {
        setServiceToProxyInternal(context);
    }

    public abstract void init(Context context);

    public abstract void resetCacheInternal(Context context, Object obj);

    public final void resetServiceCache(Context context, Object obj) {
        boolean zIsHeldByCurrentThread;
        if (!this.mShouldUseLock) {
            resetCacheInternal(context, obj);
            return;
        }
        if (!(obj instanceof Proxy)) {
            try {
                resetCacheInternal(context, obj);
                if (zIsHeldByCurrentThread) {
                    return;
                } else {
                    return;
                }
            } finally {
                if (this.mLock.isHeldByCurrentThread()) {
                    this.mLock.unlock();
                }
            }
        }
        this.mLock.lock();
        try {
            resetCacheInternal(context, obj);
        } catch (Exception e) {
            Logger.e(TAG, "LOCK Exception : " + e, new Object[0]);
            this.mLock.unlock();
        }
    }
}
