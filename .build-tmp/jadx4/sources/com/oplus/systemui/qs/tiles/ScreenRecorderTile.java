package com.oplus.systemui.qs.tiles;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.car.app.serialization.Bundler$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.Expandable;
import com.android.systemui.keyguard.data.repository.KeyguardRepository;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.UserSettingObserver;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.settings.SystemSettings;
import com.oplus.base.ocrscanner.oseesdk.utils.ApplicationUtil$;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplus.systemui.qs.base.dialog.OplusQSDetailItems;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.cache.BinderCacheManager;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.qs.base.BaseBinderCacheManager;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScreenRecorderTile extends QSTileImpl {
    private static final String ACTION_SETTING = "oplus.intent.action.SCREENRECORDER_SETTINGS";
    private static final String AREA_RECORD_META_DATA = "useSystemUiRecordTile";
    private static final String ENTRANCE_ACTIVITY_CLASS_NAME = "com.oplus.screenrecorder.MainActivity";
    private static final String ENTRANCE_SERVICE_CLASS_NAME = "com.oplus.screenrecorder.RecorderService";
    private static final String FORBCAP_SCREEN_ENABLE_PROPERTY_KEY = "persist.sys.oplus.customize.forbcap";
    private static final String PROVIDER_CALL_METHOD_GET_CDD_NOT_SHOW = "getCddNotShow";
    private static final String PROVIDER_CALL_METHOD_IS_RUNNING = "isRunning";
    private static final String PROVIDER_CALL_METHOD_STOP = "stopScreenRecord";
    private static final String RECORD_TILE_SERVICE_STATE_KEY = "record_tile_service_state";
    private static final String SCREENRECORDER_PACKAGE_NAME = "com.oplus.screenrecorder";
    private static final String SCREENRECORD_ENTRANCE_KEY = "recorder_entrance";
    private static final String SCREENRECORD_ENTRANCE_VALUE = "SystemUIControl";
    private static final String SCREENRECORD_USE_AREA_RECORD_KEY = "use_area_record";
    private static final String SUPPORT_META_DATA = "isSupportAreaRecorder";
    public static final String TILE_SPEC = "screenrecord";
    private static Boolean useSysRecordTile;
    private final CoroutineDispatcher backgroundDispatcher;
    private final BinderCacheManager binderCacheManager;
    private final OplusScreenRecordDetailAdapter detailAdapter;
    private final OplusQSDetailInteractor detailInteractor;
    private final Lazy isSupportAreaRecord$delegate;
    private final KeyguardRepository keyguardRepository;
    private final PanelInteractor panelInteractor;
    private final SystemSettings systemSettings;
    private final ScreenRecorderTile$userSettingObserver$1 userSettingObserver;
    public static final Companion Companion = new Companion();
    private static final int[] itemRes = {2131955849, 2131955847};
    private static final int[] itemIconRes = {2131237212, 2131237209};
    private static final int[] itemIconResNight = {2131237213, 2131237210};
    private static final String PROVIDER_SCREEN_RECORDER_RUNNING_STATE = "content://com.oplus.screenrecorder.CommandRecorderProvider/call";
    private static final Uri commandRecordUri = Uri.parse(PROVIDER_SCREEN_RECORDER_RUNNING_STATE);
    private static final Lazy stopRecordBundle$delegate = LazyKt__LazyJVMKt.lazy(new ScreenRecorderTile$$ExternalSyntheticLambda1());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final Bundle getStopRecordBundle() {
            return (Bundle) ScreenRecorderTile.stopRecordBundle$delegate.getValue();
        }

        public static boolean useSysRecordTile() {
            Bundle bundle;
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isSupportAreaRecord) {
                if (ScreenRecorderTile.useSysRecordTile == null) {
                    BaseBinderCacheManager baseBinderCacheManager = (BaseBinderCacheManager) DependencyEx.sDependency.getDependency(BaseBinderCacheManager.class);
                    ApplicationInfo applicationInfoCache = baseBinderCacheManager != null ? baseBinderCacheManager.getApplicationInfoCache(ScreenRecorderTile.SCREENRECORDER_PACKAGE_NAME, false) : null;
                    ScreenRecorderTile.useSysRecordTile = (applicationInfoCache == null || (bundle = applicationInfoCache.metaData) == null) ? Boolean.FALSE : Boolean.valueOf(bundle.getBoolean(ScreenRecorderTile.AREA_RECORD_META_DATA, false));
                }
                Boolean bool = ScreenRecorderTile.useSysRecordTile;
                if (bool != null) {
                    return bool.booleanValue();
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusScreenRecordDetailAdapter implements DetailAdapter {
        public /* synthetic */ ScreenRecorderTile this$0;

        public View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            ScreenRecorderTile screenRecorderTile = this.this$0;
            OplusQSDetailItems oplusQSDetailItemsConvertOrInflate = OplusQSDetailItems.convertOrInflate(context, viewGroup, z);
            if (oplusQSDetailItemsConvertOrInflate == null) {
                return null;
            }
            oplusQSDetailItemsConvertOrInflate.setTagSuffix(ScreenRecorderTile.TILE_SPEC);
            oplusQSDetailItemsConvertOrInflate.setTag(ScreenRecorderTile.TILE_SPEC);
            final boolean zIsDarkTheme = screenRecorderTile.detailInteractor.isDarkTheme();
            int length = ScreenRecorderTile.itemRes.length;
            ArrayList arrayList = new ArrayList();
            for (final int i = 0; i < length; i++) {
                OplusQSDetailItems.Item item = new OplusQSDetailItems.Item();
                item.icon = new QSTile.Icon() { // from class: com.oplus.systemui.qs.tiles.ScreenRecorderTile$OplusScreenRecordDetailAdapter$updateItem$1$1
                    public final Drawable getDrawable(Context context2) {
                        if (context2 == null) {
                            return null;
                        }
                        boolean z2 = zIsDarkTheme;
                        int i2 = i;
                        return AppCompatResources.getDrawable(context2, z2 ? ScreenRecorderTile.itemIconResNight[i2] : ScreenRecorderTile.itemIconRes[i2]);
                    }
                };
                item.line1 = context.getString(ScreenRecorderTile.itemRes[i]);
                item.viewType = 10;
                arrayList.add(item);
            }
            oplusQSDetailItemsConvertOrInflate.setListItems(0, arrayList, false);
            updateItem.2 r7 = new updateItem.2();
            r7.$list = arrayList;
            r7.this$0 = screenRecorderTile;
            VarHandle.storeStoreFence();
            oplusQSDetailItemsConvertOrInflate.setCallback(r7);
            return oplusQSDetailItemsConvertOrInflate;
        }

        public Intent getSettingsIntent() {
            Intent intent = new Intent();
            intent.setAction(ScreenRecorderTile.ACTION_SETTING);
            intent.setPackage(ScreenRecorderTile.SCREENRECORDER_PACKAGE_NAME);
            return intent;
        }

        public String getTag() {
            return ScreenRecorderTile.TILE_SPEC;
        }

        public CharSequence getTitle() {
            ScreenRecorderTile screenRecorderTile = this.this$0;
            Companion companion = ScreenRecorderTile.Companion;
            return ((QSTileImpl) screenRecorderTile).mContext.getString(2131955849);
        }

        public final boolean hasFooterDivider() {
            ScreenRecorderTile screenRecorderTile = this.this$0;
            Companion companion = ScreenRecorderTile.Companion;
            return DeviceUtil.isLandscape(((QSTileImpl) screenRecorderTile).mContext);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.ScreenRecorderTile$handleInitialize$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.ScreenRecorderTile$handleInitialize$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ ScreenRecorderTile this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(ScreenRecorderTile screenRecorderTile, Continuation continuation) {
                super(2, continuation);
                this.this$0 = screenRecorderTile;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                Flow[] flowArr = {this.this$0.keyguardRepository.isKeyguardShowing()};
                ScreenRecorderTile screenRecorderTile = this.this$0;
                ScreenRecorderTile$handleInitialize$2$1$invokeSuspend$$inlined$combine$1 screenRecorderTile$handleInitialize$2$1$invokeSuspend$$inlined$combine$1 = new ScreenRecorderTile$handleInitialize$2$1$invokeSuspend$$inlined$combine$1();
                screenRecorderTile$handleInitialize$2$1$invokeSuspend$$inlined$combine$1.$flows$inlined = flowArr;
                screenRecorderTile$handleInitialize$2$1$invokeSuspend$$inlined$combine$1.this$0 = screenRecorderTile;
                VarHandle.storeStoreFence();
                FlowKt.launchIn(screenRecorderTile$handleInitialize$2$1$invokeSuspend$$inlined$combine$1, coroutineScope);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return ScreenRecorderTile.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle lifecycle = ScreenRecorderTile.this.getLifecycle();
                Lifecycle.State state = Lifecycle.State.CREATED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(ScreenRecorderTile.this, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycle, state, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static boolean $r8$lambda$H_AkCXjCRjQqBAzRgKyXool5u8U(ScreenRecorderTile screenRecorderTile) {
        ApplicationInfo applicationInfoCache;
        Bundle bundle;
        QSFeatureOption.INSTANCE.getClass();
        if (!QSFeatureOption.isSupportAreaRecord || (applicationInfoCache = screenRecorderTile.binderCacheManager.getApplicationInfoCache(SCREENRECORDER_PACKAGE_NAME, false)) == null || (bundle = applicationInfoCache.metaData) == null) {
            return false;
        }
        return bundle.getBoolean(SUPPORT_META_DATA, false);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.oplus.systemui.qs.tiles.ScreenRecorderTile$userSettingObserver$1] */
    public ScreenRecorderTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, KeyguardRepository keyguardRepository, PanelInteractor panelInteractor, OplusQSDetailInteractor.Factory factory, BinderCacheManager binderCacheManager, final SystemSettings systemSettings, CoroutineDispatcher coroutineDispatcher) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.keyguardRepository = keyguardRepository;
        this.panelInteractor = panelInteractor;
        this.binderCacheManager = binderCacheManager;
        this.systemSettings = systemSettings;
        this.backgroundDispatcher = coroutineDispatcher;
        ScreenRecorderTile$$ExternalSyntheticLambda0 screenRecorderTile$$ExternalSyntheticLambda0 = new ScreenRecorderTile$$ExternalSyntheticLambda0();
        screenRecorderTile$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.isSupportAreaRecord$delegate = LazyKt__LazyJVMKt.lazy(screenRecorderTile$$ExternalSyntheticLambda0);
        OplusScreenRecordDetailAdapter oplusScreenRecordDetailAdapter = new OplusScreenRecordDetailAdapter();
        oplusScreenRecordDetailAdapter.this$0 = this;
        VarHandle.storeStoreFence();
        this.detailAdapter = oplusScreenRecordDetailAdapter;
        this.detailInteractor = factory.create(oplusScreenRecordDetailAdapter);
        final QSTileImpl.H h = ((QSTileImpl) this).mHandler;
        final int userId = qSHost.getUserId();
        this.userSettingObserver = new UserSettingObserver(systemSettings, h, userId) { // from class: com.oplus.systemui.qs.tiles.ScreenRecorderTile$userSettingObserver$1
            public final void handleValueChanged(int i, boolean z) {
                ScreenRecorderTile screenRecorderTile = this.this$0;
                ScreenRecorderTile.Companion companion = ScreenRecorderTile.Companion;
                String str = ((QSTileImpl) screenRecorderTile).TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, "handleValueChanged value:", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
                }
                this.this$0.refreshState((Object) null);
            }
        };
    }

    private final boolean getCddNotShow() {
        Object obj;
        try {
            Bundle bundleCall = getHost().getUserContext().getContentResolver().call(commandRecordUri, PROVIDER_CALL_METHOD_GET_CDD_NOT_SHOW, (String) null, new Bundle());
            obj = Result.constructor-impl(Boolean.valueOf(bundleCall != null ? bundleCall.getBoolean(PROVIDER_CALL_METHOD_GET_CDD_NOT_SHOW, false) : false));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            String str = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("getCddNotShow err:" + th2.getMessage());
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
        }
        boolean z = obj instanceof Result.Failure;
        if (!z) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            String str2 = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                AodClockLayout$3$.ExternalSyntheticOutline0.m("getCddNotShow:", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR), zBooleanValue);
            }
        }
        Boolean bool = Boolean.FALSE;
        if (z) {
            obj = bool;
        }
        return ((Boolean) obj).booleanValue();
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237212);
    }

    public Intent getLongClickIntent() {
        Intent intent = new Intent();
        intent.setAction(ACTION_SETTING);
        intent.setPackage(SCREENRECORDER_PACKAGE_NAME);
        return intent;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955849);
    }

    public void handleClick(Expandable expandable) {
        Object obj;
        Object obj2;
        if (screenRecordDisabled()) {
            LogUtil.w$default(8, LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleClick locked");
            return;
        }
        try {
            Bundle bundleCall = getHost().getUserContext().getContentResolver().call(commandRecordUri, PROVIDER_CALL_METHOD_IS_RUNNING, (String) null, new Bundle());
            obj = Result.constructor-impl(Boolean.valueOf(bundleCall != null ? bundleCall.getBoolean(PROVIDER_CALL_METHOD_IS_RUNNING, false) : false));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            String str = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("screenRecorderIsRunning err:" + th2.getMessage());
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
        }
        boolean z = obj instanceof Result.Failure;
        if (!z) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            String str2 = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                AodClockLayout$3$.ExternalSyntheticOutline0.m("screenRecorderIsRunning isRunning:", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR), zBooleanValue);
            }
        }
        Boolean bool = Boolean.FALSE;
        if (z) {
            obj = bool;
        }
        if (!((Boolean) obj).booleanValue()) {
            startRecording(false);
            return;
        }
        this.panelInteractor.forceCollapsePanels();
        this.detailInteractor.dismiss(true);
        try {
            obj2 = Result.constructor-impl(getHost().getUserContext().getContentResolver().call(commandRecordUri, PROVIDER_CALL_METHOD_STOP, (String) null, Companion.getStopRecordBundle()));
        } catch (Throwable th3) {
            obj2 = Result.constructor-impl(ResultKt.createFailure(th3));
        }
        Throwable th4 = Result.exceptionOrNull-impl(obj2);
        if (th4 != null) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "stopScreenRecorder err:", th4.getMessage(), LogUtil.TAG_QS, ((QSTileImpl) this).TAG);
        }
        if (obj2 instanceof Result.Failure) {
            return;
        }
        String str3 = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str3, "-->stopScreenRecorder success", "SystemUi--QuickSettings");
        }
    }

    public final void handleDestroy() {
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->handleDestroy", "SystemUi--QuickSettings");
        }
        super.handleDestroy();
        setListening(false);
    }

    public final void handleInitialize() {
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->handleInitialize", "SystemUi--QuickSettings");
        }
        setListening(true);
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(getLifecycle()), this.backgroundDispatcher, (CoroutineStart) null, new AnonymousClass2(null), 2);
    }

    public void handleLongClick(Expandable expandable) {
        if (screenRecordDisabled()) {
            return;
        }
        super.handleLongClick(expandable);
    }

    public void handleSecondaryClick(Expandable expandable) {
        if (screenRecordDisabled()) {
            LogUtil.w$default(8, LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleSecondaryClick locked");
        } else {
            this.detailInteractor.show(expandable != null ? expandable.getView() : null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0138  */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean z;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955849);
        ((QSTile.AdapterState) booleanState).value = true;
        ((QSTile.State) booleanState).dualTarget = ((Boolean) this.isSupportAreaRecord$delegate.getValue()).booleanValue();
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131955849);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
        boolean z2 = false;
        if (screenRecordDisabled()) {
            ((QSTile.State) booleanState).state = 0;
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237211);
        } else {
            boolean z3 = getValue() == 1;
            if (z3) {
                try {
                    long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                    ActivityManager activityManager = (ActivityManager) ((QSTileImpl) this).mContext.getSystemService(ActivityManager.class);
                    if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
                        List<ActivityManager.RunningAppProcessInfo> list = runningAppProcesses;
                        if (!(list instanceof Collection) || !list.isEmpty()) {
                            Iterator<T> it = list.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (Intrinsics.areEqual(((ActivityManager.RunningAppProcessInfo) it.next()).processName, SCREENRECORDER_PACKAGE_NAME)) {
                                        z = true;
                                        break;
                                    }
                                }
                            }
                        }
                        z = false;
                        break;
                    }
                    z = false;
                    break;
                    long jElapsedRealtimeNanos2 = (SystemClock.elapsedRealtimeNanos() - jElapsedRealtimeNanos) / 1000000;
                    String str = ((QSTileImpl) this).TAG;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        String str2 = LogUtil.TAG + LogUtil.TAG_QS;
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(LogUtil.SEPARATOR);
                        sb.append("isRecorderProcessAlive alive:" + z + " cost:" + jElapsedRealtimeNanos2 + "ms");
                        Log.d(str2, sb.toString());
                    }
                } catch (Throwable th) {
                    Throwable thM = ApplicationUtil$.ExternalSyntheticOutline0.m(th);
                    if (thM != null) {
                        String str3 = ((QSTileImpl) this).TAG;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str3, LogUtil.SEPARATOR);
                            sbM.append("isRecorderProcessAlive error:" + thM);
                            Log.d("SystemUi--QuickSettings", sbM.toString());
                        }
                    }
                    z = false;
                }
                if (z) {
                    z2 = z3;
                } else {
                    LogUtil.w$default(8, LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUpdateState state mismatch, reset record_tile_service_state");
                    setValue(0);
                }
            } else {
                z2 = z3;
            }
            ((QSTile.State) booleanState).state = z2 ? 2 : 1;
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237212);
        }
        ((QSTile.State) booleanState).labelDesc = (((QSTile.State) booleanState).state == 2 && QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS()) ? ((QSTileImpl) this).mContext.getString(2131955246) : "";
    }

    public final void handleUserSwitch(int i) {
        super.handleUserSwitch(i);
        setUserId(i);
    }

    public final boolean isAvailable() {
        Companion.getClass();
        return Companion.useSysRecordTile();
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m345newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_RED_COLOR;
        return booleanState;
    }

    public void preloadForSecondaryClick(Expandable expandable) {
        this.detailInteractor.prepareDetailView();
    }

    public void releasePreloadedForSecondaryClick(Expandable expandable) {
        this.detailInteractor.releaseDetailView();
    }

    public final boolean screenRecordDisabled() {
        Object obj;
        try {
            obj = Result.constructor-impl(Boolean.valueOf(Boolean.parseBoolean(OplusDevicepolicyManager.getInstance().getData(FORBCAP_SCREEN_ENABLE_PROPERTY_KEY, 1))));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "isForbid err:", th2.getMessage(), LogUtil.TAG_QS, ((QSTileImpl) this).TAG);
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        if (((Boolean) obj).booleanValue()) {
            String str = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->screenRecordDisabled disableScreenRecorder", "SystemUi--QuickSettings");
            }
        } else {
            if (!this.keyguardRepository.isKeyguardShowing()) {
                return false;
            }
            String str2 = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str2, "-->screenRecordDisabled locked", "SystemUi--QuickSettings");
            }
        }
        return true;
    }

    public final void startRecording(boolean z) {
        Object obj;
        ComponentName componentNameStartForegroundService;
        try {
            boolean cddNotShow = getCddNotShow();
            Intent intent = new Intent();
            if (cddNotShow) {
                intent.setClassName(SCREENRECORDER_PACKAGE_NAME, ENTRANCE_SERVICE_CLASS_NAME);
            } else {
                intent.setClassName(SCREENRECORDER_PACKAGE_NAME, ENTRANCE_ACTIVITY_CLASS_NAME);
                intent.setFlags(268435456);
            }
            intent.putExtra(SCREENRECORD_ENTRANCE_KEY, SCREENRECORD_ENTRANCE_VALUE);
            intent.putExtra(SCREENRECORD_USE_AREA_RECORD_KEY, z);
            if (cddNotShow) {
                this.panelInteractor.forceCollapsePanels();
                this.detailInteractor.dismiss(true);
                componentNameStartForegroundService = getHost().getUserContext().startForegroundService(intent);
            } else {
                ((QSTileImpl) this).mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
                componentNameStartForegroundService = Unit.INSTANCE;
            }
            obj = Result.constructor-impl(componentNameStartForegroundService);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "startRecording err:", th2.getMessage(), LogUtil.TAG_QS, ((QSTileImpl) this).TAG);
        }
    }
}
