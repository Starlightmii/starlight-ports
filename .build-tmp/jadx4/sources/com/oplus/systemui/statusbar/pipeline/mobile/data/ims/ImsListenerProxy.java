package com.oplus.systemui.statusbar.pipeline.mobile.data.ims;

import android.content.Context;
import android.os.Handler;
import android.telephony.ims.ImsException;
import android.telephony.ims.ImsMmTelManager;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.RegistrationManager;
import android.telephony.ims.feature.MmTelFeature;
import android.util.Log;
import com.android.ims.FeatureConnector;
import com.android.ims.ImsManager;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenStatisticUtil;
import com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1$imsListenerCb$1;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImsListenerProxy implements Dumpable {
    public static final Companion Companion = new Companion();
    public static final String TAG = "ImsListenerProxy";
    private final Context context;
    private final DumpManager dumpManager;
    private final Executor executor;
    private final MobileInputLogger logger;
    private final Handler networkHandler;
    private final Object mLock = new Object();
    private final ArrayList<ArrayList<IImsListenerCb>> imsListenerCallBacksArray = CollectionsKt__CollectionsKt.arrayListOf(new ArrayList[]{new ArrayList(), new ArrayList()});
    private ImsManager[] imsManagerArray = new ImsManager[2];
    private FeatureConnector<ImsManager>[] featureConnectorArray = new FeatureConnector[2];
    private Runnable[] connectRunnableArray = new Runnable[2];
    private int[] imsRegStateArray = {0, 0};
    private MmTelFeature.MmTelCapabilities[] capabilitiesArray = new MmTelFeature.MmTelCapabilities[2];
    private RegistrationManager.RegistrationCallback[] registrationCallbackArray = new RegistrationManager.RegistrationCallback[2];
    private ImsMmTelManager.CapabilityCallback[] capabilityCallbackListenerArray = new ImsMmTelManager.CapabilityCallback[2];

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String getResStatusString(int i) {
            if (i == 0) {
                return "unRegistered";
            }
            if (i != 1) {
                return i != 2 ? LockScreenStatisticUtil.REPORT_ERROR : "registered";
            }
            return "registering";
        }
    }

    public ImsListenerProxy(Context context, Handler handler, Executor executor, DumpManager dumpManager, MobileInputLogger mobileInputLogger) {
        this.context = context;
        this.networkHandler = handler;
        this.executor = executor;
        this.dumpManager = dumpManager;
        this.logger = mobileInputLogger;
    }

    public static final void access$imsConnectorReadyCallback(final ImsListenerProxy imsListenerProxy, ImsManager imsManager, final int i, int i2) {
        imsListenerProxy.imsManagerArray[i] = imsManager;
        if (i2 != -1) {
            RegistrationManager.RegistrationCallback registrationCallback = new RegistrationManager.RegistrationCallback() { // from class: com.oplus.systemui.statusbar.pipeline.mobile.data.ims.ImsListenerProxy$registerImsListener$regCallback$1
                @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
                public final void onRegistered(int i3) {
                    this.this$0.onImsRegistrationStatusChanged(i, 2);
                }

                @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
                public final void onRegistering(int i3) {
                    this.this$0.onImsRegistrationStatusChanged(i, 1);
                }

                @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
                public final void onUnregistered(ImsReasonInfo imsReasonInfo) {
                    this.this$0.onImsRegistrationStatusChanged(i, 0);
                }
            };
            ImsMmTelManager.CapabilityCallback capabilityCallback = new ImsMmTelManager.CapabilityCallback() { // from class: com.oplus.systemui.statusbar.pipeline.mobile.data.ims.ImsListenerProxy$registerImsListener$capCallback$1
                @Override // android.telephony.ims.ImsMmTelManager.CapabilityCallback
                public final void onCapabilitiesStatusChanged(MmTelFeature.MmTelCapabilities mmTelCapabilities) {
                    int i3 = i;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(i3, "slotId:", " CapabilityCallback onCapabilitiesStatusChanged", "ImsListenerProxy-->", "SystemUi--Statusbar");
                    }
                    this.this$0.logger.logOnCapabilitiesStatusChanged(i);
                    Object obj = this.this$0.mLock;
                    ImsListenerProxy imsListenerProxy2 = this.this$0;
                    int i4 = i;
                    synchronized (obj) {
                        imsListenerProxy2.capabilitiesArray[i4] = mmTelCapabilities;
                        Iterator it = ((Iterable) imsListenerProxy2.imsListenerCallBacksArray.get(i4)).iterator();
                        while (it.hasNext()) {
                            ((OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1$imsListenerCb$1) ((IImsListenerCb) it.next())).onImsCapabilitiesStatusChanged(i4, mmTelCapabilities, imsListenerProxy2.imsManagerArray[i4]);
                        }
                    }
                }
            };
            try {
                imsListenerProxy.unRegisterImsListener(i);
                ImsManager imsManager2 = imsListenerProxy.imsManagerArray[i];
                if (imsManager2 != null) {
                    imsManager2.addRegistrationCallback(registrationCallback, imsListenerProxy.executor);
                }
                ImsManager imsManager3 = imsListenerProxy.imsManagerArray[i];
                if (imsManager3 != null) {
                    imsManager3.addCapabilitiesCallback(capabilityCallback, imsListenerProxy.executor);
                }
                imsListenerProxy.registrationCallbackArray[i] = registrationCallback;
                imsListenerProxy.capabilityCallbackListenerArray[i] = capabilityCallback;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "ImsListenerProxy-->".concat("slotId " + i + " registerImsListener success"));
                }
            } catch (ImsException e) {
                LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "slotId " + i + " registerImsListener ImsException: " + e);
            } catch (IllegalArgumentException e2) {
                LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "slotId " + i + " registerImsListener IllegalArgumentException: " + e2);
            }
        }
        imsListenerProxy.logger.logImsConnectorReadyCallback(i, i2);
    }

    public static final void access$imsConnectorUnavailableCallback(ImsListenerProxy imsListenerProxy, int i, int i2) {
        Runnable runnable;
        if (i == 3 && (runnable = imsListenerProxy.connectRunnableArray[i2]) != null) {
            imsListenerProxy.networkHandler.removeCallbacks(runnable);
            imsListenerProxy.networkHandler.postDelayed(runnable, 5000L);
        }
        imsListenerProxy.unRegisterImsListener(i2);
        imsListenerProxy.logger.logImsConnectorUnavailableCallback(i, i2);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(TAG);
        synchronized (this.mLock) {
            try {
                ArrayList<ArrayList<IImsListenerCb>> arrayList = this.imsListenerCallBacksArray;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    ArrayList<IImsListenerCb> arrayList2 = arrayList.get(i);
                    i++;
                    printWriter.println("imsListenerCallBacksArray size:" + arrayList2.size());
                }
                for (ImsManager imsManager : this.imsManagerArray) {
                    printWriter.println("imsManager:" + imsManager);
                }
                for (FeatureConnector<ImsManager> featureConnector : this.featureConnectorArray) {
                    printWriter.println("featureConnector:" + featureConnector);
                }
                for (int i2 : this.imsRegStateArray) {
                    printWriter.println("imsRegState:" + Companion.getResStatusString(i2));
                }
                MmTelFeature.MmTelCapabilities[] mmTelCapabilitiesArr = this.capabilitiesArray;
                int length = mmTelCapabilitiesArr.length;
                for (int i3 = 0; i3 < length; i3++) {
                    MmTelFeature.MmTelCapabilities mmTelCapabilities = mmTelCapabilitiesArr[i3];
                    printWriter.println(" capabilities voice:" + (mmTelCapabilities != null ? Boolean.valueOf(mmTelCapabilities.isCapable(1)) : null) + " video:" + (mmTelCapabilities != null ? Boolean.valueOf(mmTelCapabilities.isCapable(2)) : null));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void init() {
        this.dumpManager.registerDumpable(this);
        synchronized (this.mLock) {
            for (int i = 0; i < 2; i++) {
                ImsListenerProxy$init$1$listener$1 imsListenerProxy$init$1$listener$1 = new ImsListenerProxy$init$1$listener$1();
                imsListenerProxy$init$1$listener$1.this$0 = this;
                VarHandle.storeStoreFence();
                ImsListenerProxy$init$1$listener$2 imsListenerProxy$init$1$listener$2 = new ImsListenerProxy$init$1$listener$2();
                imsListenerProxy$init$1$listener$2.this$0 = this;
                VarHandle.storeStoreFence();
                this.featureConnectorArray[i] = ImsManager.getConnector(this.context, i, TAG, new ImsManagerFeatureConnectorListenerAdapt(i, imsListenerProxy$init$1$listener$1, imsListenerProxy$init$1$listener$2), this.executor);
                Runnable[] runnableArr = this.connectRunnableArray;
                ImsListenerProxy$init$1$1 imsListenerProxy$init$1$1 = new ImsListenerProxy$init$1$1();
                imsListenerProxy$init$1$1.this$0 = this;
                imsListenerProxy$init$1$1.$i = i;
                VarHandle.storeStoreFence();
                runnableArr[i] = imsListenerProxy$init$1$1;
                this.networkHandler.post(imsListenerProxy$init$1$1);
            }
        }
    }

    public final void onImsRegistrationStatusChanged(int i, int i2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "ImsListenerProxy-->".concat("slotId:" + i + " regState:" + Companion.getResStatusString(i2)));
        }
        this.logger.logOnImsRegistrationStatusChanged(i, Companion.getResStatusString(i2));
        synchronized (this.mLock) {
            this.imsRegStateArray[i] = i2;
            Iterator<T> it = this.imsListenerCallBacksArray.get(i).iterator();
            while (it.hasNext()) {
                ((OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1$imsListenerCb$1) ((IImsListenerCb) it.next())).onImsRegistrationStatusChanged(i, i2, this.imsManagerArray[i]);
            }
        }
    }

    public final void startListenImsStatusChanged(int i, IImsListenerCb iImsListenerCb) {
        if (i < 0 || i >= 2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(i, "slotId ", " startListenImsStatusChanged slotId error!", "ImsListenerProxy-->", "SystemUi--Statusbar");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "ImsListenerProxy-->".concat("slotId " + i + " startListenImsStatusChanged cb:" + iImsListenerCb));
        }
        synchronized (this.mLock) {
            try {
                if (!this.imsListenerCallBacksArray.get(i).contains(iImsListenerCb)) {
                    this.imsListenerCallBacksArray.get(i).add(iImsListenerCb);
                }
                ((OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1$imsListenerCb$1) iImsListenerCb).onImsStatusChanged(i, this.imsRegStateArray[i], this.capabilitiesArray[i], this.imsManagerArray[i]);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void stopListenImsStatusChanged(int i, IImsListenerCb iImsListenerCb) {
        if (i < 0 || i >= 2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(i, "slotId ", " stopListenImsStatusChanged slotId error!", "ImsListenerProxy-->", "SystemUi--Statusbar");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "ImsListenerProxy-->".concat("slotId " + i + " stopListenImsStatusChanged cb:" + iImsListenerCb));
        }
        synchronized (this.mLock) {
            this.imsListenerCallBacksArray.get(i).remove(iImsListenerCb);
        }
    }

    public final void unRegisterImsListener(int i) {
        ImsManager imsManager;
        ImsManager imsManager2;
        RegistrationManager.RegistrationCallback registrationCallback = this.registrationCallbackArray[i];
        if (registrationCallback != null && (imsManager2 = this.imsManagerArray[i]) != null) {
            imsManager2.removeRegistrationListener(registrationCallback);
        }
        ImsMmTelManager.CapabilityCallback capabilityCallback = this.capabilityCallbackListenerArray[i];
        if (capabilityCallback != null && (imsManager = this.imsManagerArray[i]) != null) {
            imsManager.removeCapabilitiesCallback(capabilityCallback);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(i, "slotId ", " unRegisterImsListener", "ImsListenerProxy-->", "SystemUi--Statusbar");
        }
    }
}
