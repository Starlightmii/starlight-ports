package com.oplusos.systemui.common.settingsvalue;

import android.content.Context;
import androidx.collection.ArraySet;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SettingsValueProxyManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SettingsValueProxyManager";
    private static volatile SettingsValueProxyManager sInstance;
    private final IMultiUserListener mListener;
    private final ArraySet mProxies = new ArraySet(0);
    private WeakReference<Context> mWeakCtx;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SettingsValueProxyManager getInstance() {
            if (SettingsValueProxyManager.sInstance == null) {
                synchronized (SettingsValueProxyManager.class) {
                    if (SettingsValueProxyManager.sInstance == null) {
                        SettingsValueProxyManager.sInstance = new SettingsValueProxyManager();
                    }
                }
            }
            SettingsValueProxyManager settingsValueProxyManager = SettingsValueProxyManager.sInstance;
            settingsValueProxyManager.getClass();
            return settingsValueProxyManager;
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.settingsvalue.SettingsValueProxyManager$checkValidity$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Consumer {
        public /* synthetic */ Context $context;

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            ((AbsSettingsValueProxy) obj).checkValidity(this.$context);
        }
    }

    public SettingsValueProxyManager() {
        SettingsValueProxyManager$mListener$1 settingsValueProxyManager$mListener$1 = new SettingsValueProxyManager$mListener$1();
        settingsValueProxyManager$mListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mListener = settingsValueProxyManager$mListener$1;
    }

    public final void checkValidity(Context context) {
        ArraySet arraySet = this.mProxies;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$context = context;
        VarHandle.storeStoreFence();
        arraySet.forEach(anonymousClass1);
    }

    public final void init(ArraySet arraySet) {
        this.mProxies.addAll(arraySet);
    }

    public final void registerMultiUserListener(Context context) {
        this.mWeakCtx = new WeakReference<>(context);
        MultiUserManager.Companion.getInstance().addListener(this.mListener);
    }
}
