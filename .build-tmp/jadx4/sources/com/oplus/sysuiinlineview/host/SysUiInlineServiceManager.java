package com.oplus.sysuiinlineview.host;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.ArraySet;
import android.util.Log;
import com.oplus.sysuiinlineview.ISysUiInlineCallback;
import com.oplus.sysuiinlineview.ISysUiInlineService;
import com.oplus.sysuiinlineview.host.widget.ApiCaller;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiInlineServiceManager implements ServiceConnection, IBinder.DeathRecipient {
    public static final Companion Companion = new Companion();
    private static final String DESC_ON_HOST_VIEW_COMMAND = "onHostViewCommand";
    private static final String DESC_ON_HOST_VIEW_CONFIG_CHANGE = "onHostViewConfigurationChanged";
    private static final String DESC_ON_HOST_VIEW_CREATE = "onHostViewCreate";
    private static final String DESC_ON_HOST_VIEW_DESTROY = "onHostViewDestroy";
    private static final String DESC_ON_HOST_VIEW_LAYOUT = "onHostViewLayout";
    private static final String DESC_ON_HOST_VIEW_PAUSE = "onHostViewPause";
    private static final String DESC_ON_HOST_VIEW_RESUME = "onHostViewResume";
    private static final String TAG = "SysUiInlineServiceManager";
    private final Handler clientHandler;
    private final Intent intent;
    public final Context mContext;
    private final UserHandle userHandle;
    private final Queue<ApiCaller> mPendingMessages = new LinkedList();
    private final AtomicBoolean mIsBound = new AtomicBoolean(false);
    private final AtomicReference<SysUiInlineServiceWrapper> serviceWrapper = new AtomicReference<>(null);
    private final ArraySet<Listener> mListeners = new ArraySet<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Listener extends IBinder.DeathRecipient {
    }

    /* JADX INFO: renamed from: $r8$lambda$XYmfsNQ6sqLAwJKPh-RfTGiLiMU, reason: not valid java name */
    public static void m491$r8$lambda$XYmfsNQ6sqLAwJKPhRfTGiLiMU(SysUiInlineServiceManager sysUiInlineServiceManager) {
        boolean zIsListening = sysUiInlineServiceManager.isListening();
        boolean z = sysUiInlineServiceManager.serviceWrapper.get() != null;
        Log.d(TAG, "checkBindServiceStatus: listening=" + zIsListening + ", connected=" + z);
        if (!zIsListening) {
            sysUiInlineServiceManager.onStopListening();
        } else {
            if (z) {
                return;
            }
            sysUiInlineServiceManager.onStartListening();
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$cmmZ9y4tVN4dCpLw5dEp-eArDjY, reason: not valid java name */
    public static void m492$r8$lambda$cmmZ9y4tVN4dCpLw5dEpeArDjY(SysUiInlineServiceManager sysUiInlineServiceManager) {
        Log.d(TAG, "disconnectInternal: ");
        sysUiInlineServiceManager.mPendingMessages.clear();
        sysUiInlineServiceManager.freeWrapper();
        if (sysUiInlineServiceManager.mIsBound.compareAndSet(true, false)) {
            sysUiInlineServiceManager.mContext.unbindService(sysUiInlineServiceManager);
        }
    }

    public static void $r8$lambda$pGh31tWWaoNqqvs1B5Y9Stw2qHc(SysUiInlineServiceManager sysUiInlineServiceManager, ApiCaller apiCaller) {
        SysUiInlineServiceWrapper sysUiInlineServiceWrapper = sysUiInlineServiceManager.serviceWrapper.get();
        if (sysUiInlineServiceWrapper != null) {
            sysUiInlineServiceManager.handleClientMessageInternal(apiCaller, sysUiInlineServiceWrapper);
            return;
        }
        Log.d(TAG, "handleClientMessage " + ((Object) apiCaller.getDescription()) + ": service unconnected, pending");
        sysUiInlineServiceManager.queueMessage(apiCaller);
        if (Intrinsics.areEqual(apiCaller.getDescription(), DESC_ON_HOST_VIEW_CREATE)) {
            sysUiInlineServiceManager.onStartListening();
        }
    }

    public static void $r8$lambda$tsSFN9fXDOvpiDGg0uCxVoqcxzM(SysUiInlineServiceManager sysUiInlineServiceManager) {
        Object obj;
        try {
            obj = Result.constructor-impl(Boolean.valueOf(sysUiInlineServiceManager.mContext.bindServiceAsUser(sysUiInlineServiceManager.intent, sysUiInlineServiceManager, 705691841, sysUiInlineServiceManager.userHandle)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        boolean z = obj instanceof Result.Failure;
        if (!z) {
            Boolean bool = (Boolean) obj;
            bool.getClass();
            Log.d(TAG, Intrinsics.stringPlus(bool, "connectInternal: isBound = "));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            Log.e(TAG, "connectInternal: Failed to bind to service", th2);
        }
        Boolean bool2 = Boolean.FALSE;
        if (z) {
            obj = bool2;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        sysUiInlineServiceManager.mIsBound.set(zBooleanValue);
        if (!zBooleanValue) {
            sysUiInlineServiceManager.mContext.unbindService(sysUiInlineServiceManager);
            return;
        }
        sysUiInlineServiceManager.clientHandler.removeMessages(1002);
        Handler handler = sysUiInlineServiceManager.clientHandler;
        SysUiInlineServiceManager$$ExternalSyntheticLambda6 sysUiInlineServiceManager$$ExternalSyntheticLambda6 = new SysUiInlineServiceManager$$ExternalSyntheticLambda6(0);
        sysUiInlineServiceManager$$ExternalSyntheticLambda6.f$0 = sysUiInlineServiceManager;
        VarHandle.storeStoreFence();
        handler.postDelayed(sysUiInlineServiceManager$$ExternalSyntheticLambda6, 1002, 10000L);
    }

    public SysUiInlineServiceManager(Context context, Intent intent, UserHandle userHandle, Handler handler) {
        this.intent = intent;
        this.userHandle = userHandle;
        this.clientHandler = handler;
        this.mContext = context.getApplicationContext();
    }

    public final void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        ComponentName component = this.intent.getComponent();
        Log.d(TAG, Intrinsics.stringPlus(component == null ? null : component.flattenToShortString(), "binderDied: "));
        handleDeath();
        Iterator<T> it = this.mListeners.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).binderDied();
        }
    }

    public final void freeWrapper() {
        Object obj;
        SysUiInlineServiceWrapper andSet = this.serviceWrapper.getAndSet(null);
        if (andSet == null) {
            return;
        }
        try {
            obj = Result.constructor-impl(Boolean.valueOf(andSet.asBinder().unlinkToDeath(this, 0)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            ComponentName component = this.intent.getComponent();
            Log.w(TAG, Intrinsics.stringPlus(component == null ? null : component.flattenToShortString(), "Trying to unlink not linked recipient for component"));
        }
        if (!(obj instanceof Result.Failure)) {
            ((Boolean) obj).getClass();
            ComponentName component2 = this.intent.getComponent();
            Log.w(TAG, Intrinsics.stringPlus(component2 != null ? component2.flattenToShortString() : null, "freeWrapper recipient for component"));
        }
    }

    public final void handleClientMessageInternal(ApiCaller apiCaller, SysUiInlineServiceWrapper sysUiInlineServiceWrapper) {
        Object obj;
        try {
            apiCaller.performApiCall(sysUiInlineServiceWrapper);
            resetHandleMessageTimeout();
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            Log.w(TAG, Intrinsics.stringPlus(apiCaller.getDescription(), "handleClientMessageInternal fail: "), th2);
            apiCaller.receiveApiException(th2);
            queueMessage(apiCaller);
            handleDeath();
        }
        if (obj instanceof Result.Failure) {
            return;
        }
        Log.d(TAG, Intrinsics.stringPlus(apiCaller.getDescription(), "handleClientMessageInternal success: "));
    }

    public final void handleDeath() {
        if (this.serviceWrapper.get() == null) {
            return;
        }
        Log.d(TAG, "handleDeath: ");
        freeWrapper();
        this.mIsBound.set(false);
        if (isListening()) {
            onStartListening();
        }
    }

    public final boolean isListening() {
        ArraySet<Listener> arraySet = this.mListeners;
        if (arraySet != null && arraySet.isEmpty()) {
            return false;
        }
        Iterator<Listener> it = arraySet.iterator();
        while (it.hasNext()) {
            if (((SysUiInlineViewHost) it.next()).isListening()) {
                return true;
            }
        }
        return false;
    }

    public final void notifyHostViewCommand(Object obj, String str, Bundle bundle) {
        SysUiInlineServiceManager$$ExternalSyntheticLambda0 sysUiInlineServiceManager$$ExternalSyntheticLambda0 = new SysUiInlineServiceManager$$ExternalSyntheticLambda0(2);
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$0 = str;
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$1 = bundle;
        VarHandle.storeStoreFence();
        sendClientMessage(new ApiCaller.Builder(obj, DESC_ON_HOST_VIEW_COMMAND, null, sysUiInlineServiceManager$$ExternalSyntheticLambda0).build());
    }

    public final void notifyHostViewConfigurationChanged(Object obj, String str, Bundle bundle) {
        SysUiInlineServiceManager$$ExternalSyntheticLambda0 sysUiInlineServiceManager$$ExternalSyntheticLambda0 = new SysUiInlineServiceManager$$ExternalSyntheticLambda0(5);
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$0 = str;
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$1 = bundle;
        VarHandle.storeStoreFence();
        sendClientMessage(new ApiCaller.Builder(obj, DESC_ON_HOST_VIEW_CONFIG_CHANGE, null, sysUiInlineServiceManager$$ExternalSyntheticLambda0).build());
    }

    public final void notifyHostViewCreated(Object obj, String str, ISysUiInlineCallback iSysUiInlineCallback, Bundle bundle) {
        SysUiInlineServiceManager$$ExternalSyntheticLambda1 sysUiInlineServiceManager$$ExternalSyntheticLambda1 = new SysUiInlineServiceManager$$ExternalSyntheticLambda1();
        sysUiInlineServiceManager$$ExternalSyntheticLambda1.f$0 = str;
        sysUiInlineServiceManager$$ExternalSyntheticLambda1.f$1 = iSysUiInlineCallback;
        sysUiInlineServiceManager$$ExternalSyntheticLambda1.f$2 = bundle;
        VarHandle.storeStoreFence();
        sendClientMessage(new ApiCaller.Builder(obj, DESC_ON_HOST_VIEW_CREATE, null, sysUiInlineServiceManager$$ExternalSyntheticLambda1).build());
    }

    public final void notifyHostViewDestroyed(Object obj, String str, Bundle bundle) {
        SysUiInlineServiceManager$$ExternalSyntheticLambda0 sysUiInlineServiceManager$$ExternalSyntheticLambda0 = new SysUiInlineServiceManager$$ExternalSyntheticLambda0(0);
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$0 = str;
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$1 = bundle;
        VarHandle.storeStoreFence();
        sendClientMessage(new ApiCaller.Builder(obj, DESC_ON_HOST_VIEW_DESTROY, null, sysUiInlineServiceManager$$ExternalSyntheticLambda0).build());
    }

    public final void notifyHostViewLaidOut(Object obj, String str, Bundle bundle) {
        SysUiInlineServiceManager$$ExternalSyntheticLambda0 sysUiInlineServiceManager$$ExternalSyntheticLambda0 = new SysUiInlineServiceManager$$ExternalSyntheticLambda0(1);
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$0 = str;
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$1 = bundle;
        VarHandle.storeStoreFence();
        sendClientMessage(new ApiCaller.Builder(obj, DESC_ON_HOST_VIEW_LAYOUT, null, sysUiInlineServiceManager$$ExternalSyntheticLambda0).build());
    }

    public final void notifyHostViewPaused(Object obj, String str, Bundle bundle) {
        SysUiInlineServiceManager$$ExternalSyntheticLambda0 sysUiInlineServiceManager$$ExternalSyntheticLambda0 = new SysUiInlineServiceManager$$ExternalSyntheticLambda0(3);
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$0 = str;
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$1 = bundle;
        VarHandle.storeStoreFence();
        sendClientMessage(new ApiCaller.Builder(obj, DESC_ON_HOST_VIEW_PAUSE, null, sysUiInlineServiceManager$$ExternalSyntheticLambda0).build());
    }

    public final void notifyHostViewResumed(Object obj, String str, Bundle bundle) {
        SysUiInlineServiceManager$$ExternalSyntheticLambda0 sysUiInlineServiceManager$$ExternalSyntheticLambda0 = new SysUiInlineServiceManager$$ExternalSyntheticLambda0(4);
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$0 = str;
        sysUiInlineServiceManager$$ExternalSyntheticLambda0.f$1 = bundle;
        VarHandle.storeStoreFence();
        sendClientMessage(new ApiCaller.Builder(obj, DESC_ON_HOST_VIEW_RESUME, null, sysUiInlineServiceManager$$ExternalSyntheticLambda0).build());
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName componentName) {
        super.onBindingDied(componentName);
        Log.d(TAG, Intrinsics.stringPlus(componentName, "onBindingDied: "));
        handleDeath();
        Iterator<T> it = this.mListeners.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).binderDied();
        }
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        super.onNullBinding(componentName);
        Log.d(TAG, Intrinsics.stringPlus(componentName, "onNullBinding: "));
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.d(TAG, Intrinsics.stringPlus(componentName, "onServiceConnected: "));
        this.mIsBound.set(true);
        SysUiInlineServiceWrapper sysUiInlineServiceWrapper = new SysUiInlineServiceWrapper(ISysUiInlineService.Stub.asInterface(iBinder));
        int i = 0;
        if (iBinder != null) {
            try {
                iBinder.linkToDeath(this, 0);
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
        this.serviceWrapper.getAndSet(sysUiInlineServiceWrapper);
        synchronized (this.mPendingMessages) {
            Log.d(TAG, "handlePendingMessages: ");
            LinkedList linkedList = new LinkedList(this.mPendingMessages);
            this.mPendingMessages.clear();
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                handleClientMessageInternal((ApiCaller) it.next(), sysUiInlineServiceWrapper);
            }
        }
        this.clientHandler.removeMessages(1002);
        Handler handler = this.clientHandler;
        SysUiInlineServiceManager$$ExternalSyntheticLambda6 sysUiInlineServiceManager$$ExternalSyntheticLambda6 = new SysUiInlineServiceManager$$ExternalSyntheticLambda6(i);
        sysUiInlineServiceManager$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        handler.postDelayed(sysUiInlineServiceManager$$ExternalSyntheticLambda6, 1002, 10000L);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(TAG, Intrinsics.stringPlus(componentName, "onServiceDisconnected: "));
        handleDeath();
    }

    public final void onStartListening() {
        Log.d(TAG, "onStartListening: ");
        Handler handler = this.clientHandler;
        SysUiInlineServiceManager$$ExternalSyntheticLambda6 sysUiInlineServiceManager$$ExternalSyntheticLambda6 = new SysUiInlineServiceManager$$ExternalSyntheticLambda6(2);
        sysUiInlineServiceManager$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        handler.post(sysUiInlineServiceManager$$ExternalSyntheticLambda6);
    }

    public final void onStopListening() {
        Log.d(TAG, "onStopListening: ");
        Handler handler = this.clientHandler;
        SysUiInlineServiceManager$$ExternalSyntheticLambda6 sysUiInlineServiceManager$$ExternalSyntheticLambda6 = new SysUiInlineServiceManager$$ExternalSyntheticLambda6(1);
        sysUiInlineServiceManager$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        handler.post(sysUiInlineServiceManager$$ExternalSyntheticLambda6);
    }

    public final void queueMessage(ApiCaller apiCaller) {
        synchronized (this.mPendingMessages) {
            try {
                if (this.mPendingMessages.remove(apiCaller)) {
                    Log.i(TAG, "queueMessage: remove duplicate message{" + ((Object) apiCaller.getDescription()) + '-' + apiCaller.getIdentifier() + '}');
                }
                this.mPendingMessages.add(apiCaller);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void removeListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    public final void resetHandleMessageTimeout() {
        this.clientHandler.removeMessages(1003);
        Handler handler = this.clientHandler;
        SysUiInlineServiceManager$$ExternalSyntheticLambda6 sysUiInlineServiceManager$$ExternalSyntheticLambda6 = new SysUiInlineServiceManager$$ExternalSyntheticLambda6(3);
        sysUiInlineServiceManager$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        handler.postDelayed(sysUiInlineServiceManager$$ExternalSyntheticLambda6, 1003, 30000L);
    }

    public final void sendClientMessage(ApiCaller apiCaller) {
        Handler handler = this.clientHandler;
        SysUiInlineServiceManager$$ExternalSyntheticLambda10 sysUiInlineServiceManager$$ExternalSyntheticLambda10 = new SysUiInlineServiceManager$$ExternalSyntheticLambda10();
        sysUiInlineServiceManager$$ExternalSyntheticLambda10.f$0 = this;
        sysUiInlineServiceManager$$ExternalSyntheticLambda10.f$1 = apiCaller;
        VarHandle.storeStoreFence();
        handler.post(sysUiInlineServiceManager$$ExternalSyntheticLambda10);
    }
}
