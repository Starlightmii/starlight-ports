package com.oplus.systemui.plugins.tileservice.plugin;

import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import androidx.collection.FloatList$;
import androidx.exifinterface.media.ExifInterface$;
import androidx.fragment.app.Fragment$5$;
import com.oplus.systemui.plugins.tileservice.util.TileThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileStateController {
    public static final Companion Companion = new Companion();
    private static final String SCHEME_CONTENT = "content";
    private static final String SETTINGS = "settings";
    private static final String SETTINGS_GLOBAL = "global";
    private static final String SETTINGS_SECURE = "secure";
    private static final String SETTINGS_SYSTEM = "system";
    private static final String TAG = "TileStateController";
    private Context context;
    private final ConcurrentHashMap<ComponentName, OplusCustomTileServiceInfo> infoMap = new ConcurrentHashMap<>();
    private final InnerKeyguardLockedStateListener keyguardLockedStateListener;
    private KeyguardManager keyguardManager;
    private TileStateChangeListener listener;
    private final TileStateController$onPackageDataClearReceiver$1 onPackageDataClearReceiver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CustomTileContentProviderInfo {
        private String activeValue;
        private String columnValue;
        private String inActiveValue;
        private final Lazy observer$delegate;
        private String unavailableValue;
        private Uri uri;
        private final AtomicBoolean isListening = new AtomicBoolean(false);
        private final AtomicReference<Context> contextReference = new AtomicReference<>();
        private final AtomicInteger observedStatus = new AtomicInteger(0);
        private final AtomicReference<Function1> callBackReference = new AtomicReference<>();

        public CustomTileContentProviderInfo(Uri uri, String str, String str2, String str3, String str4) {
            this.uri = uri;
            this.columnValue = str;
            this.activeValue = str2;
            this.inActiveValue = str3;
            this.unavailableValue = str4;
            TileStateController$$ExternalSyntheticLambda0 tileStateController$$ExternalSyntheticLambda0 = new TileStateController$$ExternalSyntheticLambda0(1);
            tileStateController$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.observer$delegate = LazyKt__LazyJVMKt.lazy(tileStateController$$ExternalSyntheticLambda0);
        }

        private final TileStateController$CustomTileContentProviderInfo$observer$2$1 getObserver() {
            return (TileStateController$CustomTileContentProviderInfo$observer$2$1) this.observer$delegate.getValue();
        }

        public final int getStatus(Context context) {
            return this.isListening.get() ? this.observedStatus.get() : getStatusFromProvider(context);
        }

        public final int getStatusFromProvider(Context context) {
            int i = 1;
            String string = null;
            if (Intrinsics.areEqual(this.uri.getScheme(), TileStateController.SCHEME_CONTENT) && Intrinsics.areEqual(this.uri.getAuthority(), TileStateController.SETTINGS)) {
                List<String> pathSegments = this.uri.getPathSegments();
                if (pathSegments != null && pathSegments.size() >= 2) {
                    String str = pathSegments.get(0);
                    String str2 = pathSegments.get(1);
                    if (str != null && str.length() != 0 && str2 != null && str2.length() != 0) {
                        int iHashCode = str.hashCode();
                        if (iHashCode != -1243020381) {
                            if (iHashCode != -906273929) {
                                if (iHashCode == -887328209 && str.equals(TileStateController.SETTINGS_SYSTEM)) {
                                    string = Settings.System.getStringForUser(context.getContentResolver(), str2, context.getUserId());
                                }
                            } else if (str.equals(TileStateController.SETTINGS_SECURE)) {
                                string = Settings.Secure.getStringForUser(context.getContentResolver(), str2, context.getUserId());
                            }
                        } else if (str.equals(TileStateController.SETTINGS_GLOBAL)) {
                            string = Settings.Global.getStringForUser(context.getContentResolver(), str2, context.getUserId());
                        }
                    }
                }
            } else {
                try {
                    Cursor cursorQuery = context.getContentResolver().query(this.uri, null, null, null, null);
                    if (cursorQuery == null) {
                        cursorQuery = null;
                    } else if (cursorQuery.getCount() > 0) {
                        while (true) {
                            if (cursorQuery.moveToNext()) {
                                int columnIndex = cursorQuery.getColumnIndex(this.columnValue);
                                if (columnIndex >= 0) {
                                    string = cursorQuery.getString(columnIndex);
                                }
                            }
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (RemoteException e) {
                    Log.e(TileStateController.TAG, "queryContentProviderValue remote error", e);
                } catch (IllegalArgumentException e2) {
                    Log.e(TileStateController.TAG, "queryContentProviderValue param invalid", e2);
                } catch (SecurityException e3) {
                    Log.e(TileStateController.TAG, "queryContentProviderValue permission error", e3);
                }
            }
            if (Intrinsics.areEqual(string, this.activeValue)) {
                i = 2;
            } else if (Intrinsics.areEqual(string, this.unavailableValue)) {
                i = 0;
            }
            Fragment$5$.ExternalSyntheticOutline0.m(context.getUserId(), TileStateController.TAG, FloatList$.ExternalSyntheticOutline0.m("status form provider: ", i, this.observedStatus.get(), " , observed status: ", " ,userId: "));
            return i;
        }

        public final void registerObserver(Context context, Function1 function1) {
            Object obj;
            this.callBackReference.set(function1);
            if (this.isListening.compareAndSet(false, true)) {
                this.observedStatus.set(getStatusFromProvider(context));
                try {
                    context.getContentResolver().registerContentObserver(this.uri, false, getObserver(), context.getUserId());
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Throwable th2 = Result.exceptionOrNull-impl(obj);
                if (th2 != null) {
                    Log.e(TileStateController.TAG, "registerContentObserver error", th2);
                    this.isListening.set(false);
                }
                if (obj instanceof Result.Failure) {
                    return;
                }
                this.contextReference.set(context);
            }
        }

        public final void restoreStatus() {
            ExifInterface$.ExternalSyntheticOutline1.m(this.observedStatus.get(), "restoreStatus observedStatus: ", TileStateController.TAG);
            this.observedStatus.set(1);
            Function1 function1 = this.callBackReference.get();
            if (function1 != null) {
                function1.invoke(1);
            }
        }

        public final void unRegisterObserver() {
            if (this.isListening.compareAndSet(true, false)) {
                Unit unit = null;
                this.callBackReference.set(null);
                Context andSet = this.contextReference.getAndSet(null);
                if (andSet != null) {
                    try {
                        ContentResolver contentResolver = andSet.getContentResolver();
                        if (contentResolver != null) {
                            contentResolver.unregisterContentObserver(getObserver());
                            unit = Unit.INSTANCE;
                        }
                    } catch (Throwable th) {
                        Result.constructor-impl(ResultKt.createFailure(th));
                        return;
                    }
                }
                Result.constructor-impl(unit);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class InnerKeyguardLockedStateListener implements KeyguardManager.KeyguardLockedStateListener, Executor {
        private final Function0 keyguardStateChangeCallback;
        private final AtomicBoolean isListening = new AtomicBoolean(false);
        private final AtomicBoolean isKeyguardLocked = new AtomicBoolean(false);

        public InnerKeyguardLockedStateListener(Function0 function0) {
            this.keyguardStateChangeCallback = function0;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (runnable != null) {
                runnable.run();
            }
        }

        public final boolean isKeyguardLocked(KeyguardManager keyguardManager) {
            if (this.isListening.get()) {
                return this.isKeyguardLocked.get();
            }
            if (keyguardManager != null) {
                return keyguardManager.isKeyguardLocked();
            }
            return false;
        }

        @Override // android.app.KeyguardManager.KeyguardLockedStateListener
        public final void onKeyguardLockedStateChanged(boolean z) {
            Log.i(TileStateController.TAG, "onKeyguardLockedStateChanged: isKeyguardLocked=" + z);
            this.isKeyguardLocked.set(z);
            this.keyguardStateChangeCallback.invoke();
        }

        public final void startKeyguardLockedStateListener(KeyguardManager keyguardManager) {
            Object obj;
            if (keyguardManager != null && this.isListening.compareAndSet(false, true)) {
                onKeyguardLockedStateChanged(keyguardManager.isKeyguardLocked());
                try {
                    keyguardManager.addKeyguardLockedStateListener(this, this);
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Throwable th2 = Result.exceptionOrNull-impl(obj);
                if (th2 != null) {
                    Fragment$5$.ExternalSyntheticOutline0.m("startKeyguardLockedStateListener: occur exception-", th2.getMessage(), TileStateController.TAG);
                    this.isListening.set(false);
                }
            }
        }

        public final void stopKeyguardLockedStateListener(KeyguardManager keyguardManager) {
            Object obj;
            if (keyguardManager != null && this.isListening.compareAndSet(true, false)) {
                try {
                    keyguardManager.removeKeyguardLockedStateListener(this);
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Throwable th2 = Result.exceptionOrNull-impl(obj);
                if (th2 != null) {
                    Fragment$5$.ExternalSyntheticOutline0.m("stopKeyguardLockedStateListener: occur exception-", th2.getMessage(), TileStateController.TAG);
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusCustomTileServiceInfo {
        public boolean bindAppWhenListening;
        private IBinder binder;
        private CustomTileContentProviderInfo customTileContentProviderInfo;
        private final IBinder.DeathRecipient deathRecipient;
        public boolean availableWhenLock = true;
        public long lastBindAppIsTrueTime = -1;

        public OplusCustomTileServiceInfo() {
            TileStateController$OplusCustomTileServiceInfo$deathRecipient$1 tileStateController$OplusCustomTileServiceInfo$deathRecipient$1 = new TileStateController$OplusCustomTileServiceInfo$deathRecipient$1();
            tileStateController$OplusCustomTileServiceInfo$deathRecipient$1.this$0 = this;
            VarHandle.storeStoreFence();
            this.deathRecipient = tileStateController$OplusCustomTileServiceInfo$deathRecipient$1;
        }

        public final boolean getAvailableWhenLock() {
            return this.availableWhenLock;
        }

        public final boolean getBindAppWhenListening() {
            return this.bindAppWhenListening;
        }

        public final CustomTileContentProviderInfo getCustomTileContentProviderInfo() {
            return this.customTileContentProviderInfo;
        }

        public final void setAvailableWhenLock(boolean z) {
            this.availableWhenLock = z;
        }

        public final void setBindAppWhenListening(boolean z) {
            if (z) {
                this.lastBindAppIsTrueTime = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - this.lastBindAppIsTrueTime < 500) {
                Log.d(TileStateController.TAG, "The time from the last abnormal data is too close, it may be dirty data");
                return;
            }
            this.bindAppWhenListening = z;
        }

        public final void setCustomTileContentProviderInfo(CustomTileContentProviderInfo customTileContentProviderInfo) {
            this.customTileContentProviderInfo = customTileContentProviderInfo;
        }

        public final void setDeathRecipientToken(IBinder iBinder) {
            Object obj;
            try {
                if (iBinder == null) {
                    IBinder iBinder2 = this.binder;
                    if (iBinder2 != null) {
                        iBinder2.unlinkToDeath(this.deathRecipient, 0);
                    }
                } else {
                    iBinder.linkToDeath(this.deathRecipient, 0);
                }
                this.binder = iBinder;
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                Log.e(TileStateController.TAG, "linkToDeath error", th2);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface TileStateChangeListener {
        void onSettingsStateChange(ComponentName componentName, int i);

        void refreshState(ComponentName componentName);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.tileservice.plugin.TileStateController$setListening$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ TileStateController$$ExternalSyntheticLambda1 $callBack;
        public /* synthetic */ Context $nonnullContext;
        public /* synthetic */ CustomTileContentProviderInfo $providerInfo;

        @Override // java.lang.Runnable
        public final void run() {
            this.$callBack.invoke(Integer.valueOf(this.$providerInfo.getStatus(this.$nonnullContext)));
        }
    }

    public static Unit $r8$lambda$CXMuRbGleGDimmUuOXcKhdZnZVU(TileStateController tileStateController, ComponentName componentName, int i) {
        TileStateChangeListener tileStateChangeListener = tileStateController.listener;
        if (tileStateChangeListener != null) {
            ((OplusTileControllerPlugin$tileStateChangeListener$1) tileStateChangeListener).onSettingsStateChange(componentName, i);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$JV_ywhmOBuhnJNJ5rXFDqKjtc0g(TileStateController tileStateController) {
        ConcurrentHashMap<ComponentName, OplusCustomTileServiceInfo> concurrentHashMap = tileStateController.infoMap;
        TileStateController$onKeyguardStateChange$1 tileStateController$onKeyguardStateChange$1 = new TileStateController$onKeyguardStateChange$1();
        tileStateController$onKeyguardStateChange$1.this$0 = tileStateController;
        VarHandle.storeStoreFence();
        concurrentHashMap.forEach(tileStateController$onKeyguardStateChange$1);
        return Unit.INSTANCE;
    }

    public TileStateController(Context context) {
        this.context = context;
        TileStateController$$ExternalSyntheticLambda0 tileStateController$$ExternalSyntheticLambda0 = new TileStateController$$ExternalSyntheticLambda0(0);
        tileStateController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardLockedStateListener = new InnerKeyguardLockedStateListener(tileStateController$$ExternalSyntheticLambda0);
        this.onPackageDataClearReceiver = new TileStateController$onPackageDataClearReceiver$1(this);
    }

    private final KeyguardManager getKeyguardManager() {
        KeyguardManager keyguardManager = this.keyguardManager;
        if (keyguardManager == null) {
            keyguardManager = (KeyguardManager) this.context.getSystemService(KeyguardManager.class);
        }
        this.keyguardManager = keyguardManager;
        return keyguardManager;
    }

    public final void addInfo(ComponentName componentName, OplusCustomTileServiceInfo oplusCustomTileServiceInfo) {
        Log.d(TAG, "addInfo componentName: " + componentName + " infoMapSize: " + this.infoMap.size());
        this.infoMap.put(componentName, oplusCustomTileServiceInfo);
        this.keyguardLockedStateListener.startKeyguardLockedStateListener(getKeyguardManager());
        TileStateController$onPackageDataClearReceiver$1 tileStateController$onPackageDataClearReceiver$1 = this.onPackageDataClearReceiver;
        if (tileStateController$onPackageDataClearReceiver$1.isRegister.compareAndSet(false, true)) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_DATA_CLEARED");
            intentFilter.addDataScheme("package");
            tileStateController$onPackageDataClearReceiver$1.this$0.context.registerReceiver(tileStateController$onPackageDataClearReceiver$1, intentFilter);
        }
    }

    public final boolean bindAppWhenListening(ComponentName componentName) {
        OplusCustomTileServiceInfo oplusCustomTileServiceInfo = this.infoMap.get(componentName);
        if (oplusCustomTileServiceInfo == null) {
            return false;
        }
        Log.d(TAG, "bindAppWhenListening: " + oplusCustomTileServiceInfo.getBindAppWhenListening() + " " + componentName);
        return oplusCustomTileServiceInfo.getBindAppWhenListening();
    }

    public final void destroy() {
        Iterator<Map.Entry<ComponentName, OplusCustomTileServiceInfo>> it = this.infoMap.entrySet().iterator();
        while (it.hasNext()) {
            CustomTileContentProviderInfo customTileContentProviderInfo = it.next().getValue().getCustomTileContentProviderInfo();
            if (customTileContentProviderInfo != null) {
                customTileContentProviderInfo.unRegisterObserver();
            }
        }
        this.infoMap.clear();
    }

    public final void removeInfo(ComponentName componentName) {
        OplusCustomTileServiceInfo oplusCustomTileServiceInfoRemove = this.infoMap.remove(componentName);
        if (oplusCustomTileServiceInfoRemove != null) {
            Log.d(TAG, "removeInfo componentName: " + componentName + " infoMapSize: " + this.infoMap.size());
            CustomTileContentProviderInfo customTileContentProviderInfo = oplusCustomTileServiceInfoRemove.getCustomTileContentProviderInfo();
            if (customTileContentProviderInfo != null) {
                customTileContentProviderInfo.unRegisterObserver();
            }
        }
        if (this.infoMap.isEmpty()) {
            this.keyguardLockedStateListener.stopKeyguardLockedStateListener(getKeyguardManager());
            TileStateController$onPackageDataClearReceiver$1 tileStateController$onPackageDataClearReceiver$1 = this.onPackageDataClearReceiver;
            if (tileStateController$onPackageDataClearReceiver$1.isRegister.compareAndSet(true, false)) {
                try {
                    tileStateController$onPackageDataClearReceiver$1.this$0.context.unregisterReceiver(tileStateController$onPackageDataClearReceiver$1);
                } catch (IllegalArgumentException unused) {
                    Log.d(TAG, "unregisterReceiver onPackageDataClearReceiver error");
                }
            }
        }
    }

    public final void setDeathRecipientToken(IBinder iBinder, ComponentName componentName) {
        OplusCustomTileServiceInfo oplusCustomTileServiceInfo = this.infoMap.get(componentName);
        if (oplusCustomTileServiceInfo != null) {
            oplusCustomTileServiceInfo.setDeathRecipientToken(iBinder);
        }
    }

    public final void setListening(boolean z, ComponentName componentName, Context context) {
        CustomTileContentProviderInfo customTileContentProviderInfo;
        OplusCustomTileServiceInfo oplusCustomTileServiceInfo = this.infoMap.get(componentName);
        if (oplusCustomTileServiceInfo == null || (customTileContentProviderInfo = oplusCustomTileServiceInfo.getCustomTileContentProviderInfo()) == null) {
            return;
        }
        Log.d(TAG, "setListening listening: " + z + " component: " + componentName + " userContext: " + (context != null ? Integer.valueOf(context.getUserId()) : null));
        if (!z) {
            customTileContentProviderInfo.unRegisterObserver();
            return;
        }
        if (context == null) {
            context = this.context;
        }
        TileStateController$$ExternalSyntheticLambda1 tileStateController$$ExternalSyntheticLambda1 = new TileStateController$$ExternalSyntheticLambda1();
        tileStateController$$ExternalSyntheticLambda1.f$0 = this;
        tileStateController$$ExternalSyntheticLambda1.f$1 = componentName;
        VarHandle.storeStoreFence();
        customTileContentProviderInfo.registerObserver(context, tileStateController$$ExternalSyntheticLambda1);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$callBack = tileStateController$$ExternalSyntheticLambda1;
        anonymousClass1.$providerInfo = customTileContentProviderInfo;
        anonymousClass1.$nonnullContext = context;
        VarHandle.storeStoreFence();
        TileThreadUtil.runOnWorkThread(anonymousClass1);
    }

    public final void setTileStateChangeListener(TileStateChangeListener tileStateChangeListener) {
        this.listener = tileStateChangeListener;
    }

    public final boolean shouldDisableTile(ComponentName componentName) {
        OplusCustomTileServiceInfo oplusCustomTileServiceInfo = this.infoMap.get(componentName);
        return (oplusCustomTileServiceInfo == null || oplusCustomTileServiceInfo.getAvailableWhenLock() || !this.keyguardLockedStateListener.isKeyguardLocked(getKeyguardManager())) ? false : true;
    }

    public final void updateCurrentStateIsNotDirty(ComponentName componentName) {
        OplusCustomTileServiceInfo oplusCustomTileServiceInfo = this.infoMap.get(componentName);
        if (oplusCustomTileServiceInfo != null) {
            Log.d(TAG, "updateCurrentStateIsNotDirty component:" + componentName);
            oplusCustomTileServiceInfo.setBindAppWhenListening(false);
        }
    }
}
