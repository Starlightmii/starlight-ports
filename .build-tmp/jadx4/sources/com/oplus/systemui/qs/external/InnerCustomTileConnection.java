package com.oplus.systemui.qs.external;

import android.app.PendingIntent;
import android.app.compat.CompatChanges;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.UserHandle;
import android.service.quicksettings.IQSService;
import android.service.quicksettings.IQSTileService;
import android.service.quicksettings.Tile;
import android.support.v4.content.FileProvider$;
import android.util.ArraySet;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.room.util.CursorUtil$;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.common.domain.interactor.PackageChangeInteractor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.external.PackageManagerAdapter;
import com.android.systemui.qs.external.QSTileServiceWrapper;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.TileHandlerThread;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class InnerCustomTileConnection implements ServiceConnection {
    public static final Companion Companion = new Companion();
    private final ActivityStarter activityStarter;
    private final Handler bgHandler = new Handler(TileHandlerThread.Companion.getInstance().getLooper());
    private final CoroutineScope bgScope;
    public int bindTryCount;
    private AtomicBoolean bound;
    private final Binder clickToken;
    private final Context context;
    private final IBinder.DeathRecipient deathRecipient;
    private final Intent intent;
    private AtomicBoolean isBound;
    private final KeyguardStateController keyguardStateController;
    private final String logTag;
    private final PackageManagerAdapter packageManagerAdapter;
    private final PanelInteractor panelInteractor;
    private final ArraySet<Integer> pendingMessages;
    private AtomicReference<Job> rebindServiceJob;
    private final Flow rebindServiceListener;
    private final AtomicReference<QSTileServiceWrapper> serviceWrapper;
    private final String spec;
    private AtomicBoolean tileListening;
    private final InnerCustomTileConnection$tileServiceCallback$1 tileServiceCallback;
    private final ComponentName tileServiceComponent;
    private final Binder tileToken;
    private final Runnable unbindServiceRunnable;
    private final UserHandle userHandle;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        InnerCustomTileConnection create(String str, ComponentName componentName, UserHandle userHandle);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.external.InnerCustomTileConnection$handleClick$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ InnerCustomTileConnection this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.handleClick();
                    break;
                case 1:
                    this.this$0.handleDeath();
                    break;
                case 2:
                    if (this.this$0.bound.get()) {
                        this.this$0.setBindService(true);
                    }
                    break;
                case 3:
                    this.this$0.handlePendingMessages();
                    break;
                case 4:
                    this.this$0.handleStopListening();
                    break;
                case 5:
                    this.this$0.handleUnlockComplete();
                    break;
                case 6:
                    if (this.this$0.bound.get()) {
                        String str = this.this$0.logTag;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->Trying to rebind", "SystemUi--QuickSettings");
                        }
                        this.this$0.setBindService(true);
                    }
                    break;
                case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                    this.this$0.handleUnlockComplete();
                    break;
                default:
                    this.this$0.setBindService(false);
                    break;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v5, types: [android.service.quicksettings.IQSService$Stub, com.oplus.systemui.qs.external.InnerCustomTileConnection$tileServiceCallback$1] */
    public InnerCustomTileConnection(String str, ComponentName componentName, UserHandle userHandle, Context context, CoroutineScope coroutineScope, PackageManagerAdapter packageManagerAdapter, PanelInteractor panelInteractor, ActivityStarter activityStarter, KeyguardStateController keyguardStateController, BroadcastDispatcher broadcastDispatcher, PackageChangeInteractor packageChangeInteractor) {
        this.spec = str;
        this.tileServiceComponent = componentName;
        this.userHandle = userHandle;
        this.context = context;
        this.bgScope = coroutineScope;
        this.packageManagerAdapter = packageManagerAdapter;
        this.panelInteractor = panelInteractor;
        this.activityStarter = activityStarter;
        this.keyguardStateController = keyguardStateController;
        this.logTag = FileProvider$.ExternalSyntheticOutline0.m("InnerCustomTileConnection_", str);
        Binder binder = new Binder();
        this.tileToken = binder;
        this.clickToken = new Binder();
        this.serviceWrapper = new AtomicReference<>(null);
        this.bound = new AtomicBoolean(false);
        this.isBound = new AtomicBoolean(false);
        this.tileListening = new AtomicBoolean(false);
        this.pendingMessages = new ArraySet<>();
        this.rebindServiceJob = new AtomicReference<>();
        Flow flowPackageChanged = packageChangeInteractor.packageChanged(userHandle, componentName.getPackageName());
        InnerCustomTileConnection$special$$inlined$filter$1 innerCustomTileConnection$special$$inlined$filter$1 = new InnerCustomTileConnection$special$$inlined$filter$1();
        innerCustomTileConnection$special$$inlined$filter$1.$this_unsafeTransform$inlined = flowPackageChanged;
        VarHandle.storeStoreFence();
        Flow flowMerge = FlowKt.merge(new Flow[]{innerCustomTileConnection$special$$inlined$filter$1, BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter("android.intent.action.USER_UNLOCKED"), userHandle, 0, (String) null, 12)});
        InnerCustomTileConnection$special$$inlined$map$1 innerCustomTileConnection$special$$inlined$map$1 = new InnerCustomTileConnection$special$$inlined$map$1();
        innerCustomTileConnection$special$$inlined$map$1.$this_unsafeTransform$inlined = flowMerge;
        innerCustomTileConnection$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.rebindServiceListener = innerCustomTileConnection$special$$inlined$map$1;
        InnerCustomTileConnection$deathRecipient$1 innerCustomTileConnection$deathRecipient$1 = new InnerCustomTileConnection$deathRecipient$1();
        innerCustomTileConnection$deathRecipient$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.deathRecipient = innerCustomTileConnection$deathRecipient$1;
        ?? r3 = new IQSService.Stub() { // from class: com.oplus.systemui.qs.external.InnerCustomTileConnection$tileServiceCallback$1
            public Tile getTile(IBinder iBinder) {
                return new Tile();
            }

            public boolean isLocked() {
                return this.this$0.keyguardStateController.mShowing;
            }

            public boolean isSecure() {
                return this.this$0.keyguardStateController.mSecure && this.this$0.keyguardStateController.mShowing;
            }

            public void onDialogHidden(IBinder iBinder) {
                LogUtil.e$default(8, LogUtil.TAG_QS, this.this$0.logTag, "onDialogHidden: invalid func, ignore");
            }

            public void onShowDialog(IBinder iBinder) {
                LogUtil.i$default(LogUtil.TAG_QS, this.this$0.logTag, "onShowDialog: ", null, 8);
                this.this$0.panelInteractor.forceCollapsePanels();
            }

            public void onStartActivity(IBinder iBinder) {
                LogUtil.i$default(LogUtil.TAG_QS, this.this$0.logTag, "onStartActivity: ", null, 8);
                this.this$0.panelInteractor.forceCollapsePanels();
            }

            public void onStartSuccessful(IBinder iBinder) {
                LogUtil.e$default(8, LogUtil.TAG_QS, this.this$0.logTag, "onDialogHidden: invalid func, ignore");
            }

            public void startActivity(IBinder iBinder, PendingIntent pendingIntent) {
                boolean zIsActivity = pendingIntent.isActivity();
                InnerCustomTileConnection innerCustomTileConnection = this.this$0;
                if (!zIsActivity) {
                    LogUtil.i$default(LogUtil.TAG_QS, innerCustomTileConnection.logTag, "startActivity: Intent not for activity.", null, 8);
                } else {
                    LogUtil.i$default(LogUtil.TAG_QS, innerCustomTileConnection.logTag, "startActivity: The activity is starting", null, 8);
                    this.this$0.activityStarter.startPendingIntentMaybeDismissingKeyguard(pendingIntent, (Runnable) null, (ActivityTransitionAnimator.Controller) null);
                }
            }

            public void startUnlockAndRun(IBinder iBinder) {
                LogUtil.i$default(LogUtil.TAG_QS, this.this$0.logTag, "startUnlockAndRun: ", null, 8);
                ActivityStarter activityStarter2 = this.this$0.activityStarter;
                InnerCustomTileConnection innerCustomTileConnection = this.this$0;
                InnerCustomTileConnection.AnonymousClass1 anonymousClass1 = new InnerCustomTileConnection.AnonymousClass1(7);
                anonymousClass1.this$0 = innerCustomTileConnection;
                VarHandle.storeStoreFence();
                activityStarter2.postQSRunnableDismissingKeyguard(anonymousClass1);
            }

            public void updateQsTile(Tile tile, IBinder iBinder) {
                LogUtil.e$default(8, LogUtil.TAG_QS, this.this$0.logTag, "updateQsTile: invalid func, ignore");
            }

            public void updateStatusIcon(IBinder iBinder, Icon icon, String str2) {
                LogUtil.e$default(8, LogUtil.TAG_QS, this.this$0.logTag, "updateStatusIcon: invalid func, ignore");
            }
        };
        this.tileServiceCallback = r3;
        Intent intent = new Intent("android.service.quicksettings.action.QS_TILE");
        intent.setComponent(componentName);
        intent.putExtra("service", r3.asBinder());
        intent.putExtra("token", binder);
        this.intent = intent;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(8);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.unbindServiceRunnable = anonymousClass1;
    }

    public final boolean bindServices() {
        boolean zIsChangeEnabled = CompatChanges.isChangeEnabled(241766793L, this.tileServiceComponent.getPackageName(), this.userHandle);
        String str = this.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("bindServices: when isChangeEnabled(" + zIsChangeEnabled + ")");
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        return this.context.bindServiceAsUser(this.intent, this, zIsChangeEnabled ? 34603041 : 33554465, this.userHandle);
    }

    public final boolean checkComponentState() {
        Object obj;
        Object obj2;
        String packageName = this.tileServiceComponent.getPackageName();
        try {
            this.packageManagerAdapter.mPackageManager.getPackageInfoAsUser(packageName, 0, this.userHandle.getIdentifier());
            obj = Result.constructor-impl(Boolean.TRUE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, this.logTag, CursorUtil$.ExternalSyntheticOutline0.m("Package not available: ", packageName, " when exception ", th2.getMessage()));
            obj = Boolean.FALSE;
        }
        if (!((Boolean) obj).booleanValue()) {
            return false;
        }
        try {
            ServiceInfo serviceInfo = this.packageManagerAdapter.getServiceInfo(this.tileServiceComponent, 0, this.userHandle.getIdentifier());
            obj2 = Result.constructor-impl(serviceInfo != null ? Boolean.valueOf(serviceInfo.isEnabled()) : null);
        } catch (Throwable th3) {
            obj2 = Result.constructor-impl(ResultKt.createFailure(th3));
        }
        boolean z = obj2 instanceof Result.Failure;
        if (!z) {
            Boolean bool = (Boolean) obj2;
            if (bool == null) {
                LogUtil.w$default(8, LogUtil.TAG_QS, this.logTag, "Can't find component");
            } else if (bool.equals(Boolean.FALSE)) {
                LogUtil.w$default(8, LogUtil.TAG_QS, this.logTag, "component is disabled");
            }
        }
        Throwable th4 = Result.exceptionOrNull-impl(obj2);
        if (th4 != null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "isComponentAvailable exception ", th4.getMessage(), LogUtil.TAG_QS, this.logTag);
        }
        Boolean bool2 = Boolean.FALSE;
        if (z) {
            obj2 = bool2;
        }
        Boolean bool3 = (Boolean) obj2;
        return bool3 != null ? bool3.booleanValue() : false;
    }

    public final void executeOnBgHandler(Runnable runnable) {
        this.bgHandler.post(runnable);
    }

    public final void freeWrapper() {
        Object obj;
        QSTileServiceWrapper andSet = this.serviceWrapper.getAndSet(null);
        if (andSet != null) {
            try {
                obj = Result.constructor-impl(Boolean.valueOf(andSet.mService.asBinder().unlinkToDeath(this.deathRecipient, 0)));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (Result.exceptionOrNull-impl(obj) != null) {
                LogUtil.w$default(8, LogUtil.TAG_QS, this.logTag, "Trying to unlink not linked recipient");
            }
        }
    }

    public final void handleClick() {
        int i = 0;
        if (!isOnBgHandler()) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            executeOnBgHandler(anonymousClass1);
            return;
        }
        QSTileServiceWrapper qSTileServiceWrapper = this.serviceWrapper.get();
        if (qSTileServiceWrapper != null) {
            try {
                qSTileServiceWrapper.mService.onClick(this.clickToken);
                i = 1;
            } catch (Exception e) {
                Log.d("IQSTileServiceWrapper", "Caught exception from TileService", e);
            }
        }
        String str = this.logTag;
        if (i != 0) {
            LogUtil.i$default(LogUtil.TAG_QS, str, "handleClick: call remote onClick done", null, 8);
            this.bgHandler.removeCallbacks(this.unbindServiceRunnable);
            this.bgHandler.postDelayed(this.unbindServiceRunnable, 30000L);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, str, "handleClick: pending when wrapper=" + qSTileServiceWrapper, null, 8);
        synchronized (this.pendingMessages) {
            this.pendingMessages.add(2);
        }
        handleDeath();
        setBindService(true);
    }

    public final void handleDeath() {
        if (this.isBound.get()) {
            if (!isOnBgHandler()) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
                anonymousClass1.this$0 = this;
                VarHandle.storeStoreFence();
                executeOnBgHandler(anonymousClass1);
                return;
            }
            String str = this.logTag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->handleDeath", "SystemUi--QuickSettings");
            }
            unbindService();
            if (this.bound.get()) {
                if (!checkComponentState()) {
                    startPackageListening();
                    return;
                }
                Handler handler = this.bgHandler;
                AnonymousClass1 anonymousClass2 = new AnonymousClass1(2);
                anonymousClass2.this$0 = this;
                VarHandle.storeStoreFence();
                handler.postDelayed(anonymousClass2, 1000L);
            }
        }
    }

    public final void handlePendingMessages() {
        ArraySet arraySet;
        int i = 3;
        if (!isOnBgHandler()) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            executeOnBgHandler(anonymousClass1);
            return;
        }
        synchronized (this.pendingMessages) {
            arraySet = new ArraySet((ArraySet) this.pendingMessages);
            this.pendingMessages.clear();
        }
        if (arraySet.contains(2)) {
            String str = this.logTag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->Handling pending onClick", "SystemUi--QuickSettings");
            }
            handleClick();
        }
        if (arraySet.contains(3)) {
            String str2 = this.logTag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str2, "-->Handling pending onUnlockComplete", "SystemUi--QuickSettings");
            }
            handleUnlockComplete();
        }
        if (this.tileListening.get()) {
            return;
        }
        String str3 = this.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str3, "-->Handling stopListening", "SystemUi--QuickSettings");
        }
        handleStopListening();
    }

    public final void handleStopListening() {
        boolean zContains;
        if (!isOnBgHandler()) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(4);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            executeOnBgHandler(anonymousClass1);
            return;
        }
        synchronized (this.pendingMessages) {
            zContains = this.pendingMessages.contains(2);
        }
        if (zContains) {
            return;
        }
        setBindService(false);
    }

    public final void handleUnlockComplete() {
        if (!isOnBgHandler()) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(5);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            executeOnBgHandler(anonymousClass1);
            return;
        }
        QSTileServiceWrapper qSTileServiceWrapper = this.serviceWrapper.get();
        boolean z = false;
        if (qSTileServiceWrapper != null) {
            try {
                qSTileServiceWrapper.mService.onUnlockComplete();
                z = true;
            } catch (Exception e) {
                Log.d("IQSTileServiceWrapper", "Caught exception from TileService", e);
            }
        }
        String str = this.logTag;
        if (z) {
            LogUtil.i$default(LogUtil.TAG_QS, str, "onUnlockComplete: call remote onUnlockComplete done", null, 8);
            this.bgHandler.removeCallbacks(this.unbindServiceRunnable);
            this.bgHandler.postDelayed(this.unbindServiceRunnable, 30000L);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, str, "onUnlockComplete: pending when wrapper=" + qSTileServiceWrapper, null, 8);
        synchronized (this.pendingMessages) {
            this.pendingMessages.add(3);
        }
        handleDeath();
    }

    public final boolean isOnBgHandler() {
        return Intrinsics.areEqual(Looper.myLooper(), this.bgHandler.getLooper());
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName componentName) {
        super.onBindingDied(componentName);
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "onBindingDied", null, 8);
        handleDeath();
    }

    public final void onClick() {
        OplusCustomTileStub.INSTANCE.handleAppFromControlCenter(this.context, this.tileServiceComponent.getPackageName());
        handleClick();
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        super.onNullBinding(componentName);
        String str = this.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->onNullBinding", "SystemUi--QuickSettings");
        }
        setBindService(false);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Unit unit;
        Object obj;
        this.bindTryCount = 0;
        QSTileServiceWrapper qSTileServiceWrapper = new QSTileServiceWrapper(IQSTileService.Stub.asInterface(iBinder));
        if (iBinder != null) {
            try {
                iBinder.linkToDeath(this.deathRecipient, 0);
                unit = Unit.INSTANCE;
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
        } else {
            unit = null;
        }
        obj = Result.constructor-impl(unit);
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "linkToDeath fail: ", th2.getMessage(), LogUtil.TAG_QS, this.logTag);
        }
        QSTileServiceWrapper andSet = this.serviceWrapper.getAndSet(qSTileServiceWrapper);
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "onServiceConnected: update serviceWrapper from " + andSet + " to " + qSTileServiceWrapper, null, 8);
        handlePendingMessages();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "onServiceDisconnected", null, 8);
        freeWrapper();
    }

    public final void setBindService(boolean z) {
        Object obj;
        if (!isOnBgHandler()) {
            setBindService.1 r0 = new setBindService.1();
            r0.this$0 = this;
            r0.$bind = z;
            VarHandle.storeStoreFence();
            executeOnBgHandler(r0);
            return;
        }
        this.bound.getAndSet(z);
        if (!z) {
            stopPackageListening();
            unbindService();
            return;
        }
        if (this.bindTryCount == 5) {
            startPackageListening();
            String str = this.logTag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->setBindService: Max bind retries, return.", "SystemUi--QuickSettings");
                return;
            }
            return;
        }
        if (!checkComponentState()) {
            startPackageListening();
            String str2 = this.logTag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str2, "-->setBindService: Component not available, return.", "SystemUi--QuickSettings");
                return;
            }
            return;
        }
        String str3 = this.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str3, LogUtil.SEPARATOR);
            sbM.append("Binding service " + this.intent + " " + this.userHandle);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        this.bindTryCount++;
        try {
            if (!this.isBound.get() && bindServices()) {
                this.isBound.set(true);
            }
            if (!this.isBound.get()) {
                this.context.unbindService(this);
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e(LogUtil.TAG_QS, this.logTag, "Failed to bind to service", th2);
            this.isBound.set(false);
        }
    }

    public final void setListening(boolean z) {
        this.tileListening.getAndSet(z);
        if (z) {
            return;
        }
        handleStopListening();
    }

    public final void startPackageListening() {
        String str = this.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->startPackageListening", "SystemUi--QuickSettings");
        }
        Job andSet = this.rebindServiceJob.getAndSet(FlowKt.launchIn(this.rebindServiceListener, this.bgScope));
        if (andSet != null) {
            Job.Key key = Job.Key;
            andSet.cancel((CancellationException) null);
        }
    }

    public final void stopPackageListening() {
        String str = this.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->stopPackageListening", "SystemUi--QuickSettings");
        }
        Job andSet = this.rebindServiceJob.getAndSet(null);
        if (andSet != null) {
            Job.Key key = Job.Key;
            andSet.cancel((CancellationException) null);
        }
    }

    public final void unbindService() {
        Object obj;
        String str = this.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("Unbinding service " + this.intent + " " + this.userHandle);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        this.bindTryCount = 0;
        freeWrapper();
        this.bgHandler.removeCallbacks(this.unbindServiceRunnable);
        if (this.isBound.compareAndSet(true, false)) {
            try {
                this.context.unbindService(this);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                LogUtil.e(LogUtil.TAG_QS, this.logTag, "Failed to unbind service ", th2);
            }
            this.isBound.set(false);
        }
    }
}
