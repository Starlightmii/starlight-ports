package com.oplus.systemui.oplusstatusbar.display;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Window;
import androidx.appcompat.app.AlertDialog;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.display.OplusConnectingDisplayEx;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor;
import com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.coui.appcompat.darkmode.COUIDarkModeUtil;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.coui.appcompat.dialog.adapter.ChoiceListAdapter;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.zoom.common.ZoomDCSManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusConnectingDisplayExImpl extends OplusConnectingDisplayEx implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String DEMO_MODE_CONNECTING_DISPLAY_DIALOG = "connecting_display_dialog";
    private static final String TAG = "OplusConnectingDisplayExImpl";
    private final CoroutineDispatcher bgDispatcher;
    public volatile boolean clearData;
    private ConnectingDisplayViewModel.ConnectingDisplayExActionCallback connectingExCallback;
    private final Context context;
    private volatile ConnectedDisplayInteractor.PendingDisplay currentPendingDisplay;
    private final Flow demoCommandStream;
    private Dialog dialog;
    public volatile boolean isDesktopModeSupported;
    public volatile boolean isInKioskMode;
    private final Flow isShowConnectingDisplayDialog;
    public volatile boolean linkedEventReported;
    private final CoroutineScope scope;

    /* JADX INFO: renamed from: com.oplus.systemui.oplusstatusbar.display.OplusConnectingDisplayExImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.oplusstatusbar.display.OplusConnectingDisplayExImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00381 implements FlowCollector {
            public /* synthetic */ OplusConnectingDisplayExImpl this$0;

            public final Object emit(Object obj, Continuation continuation) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusConnectingDisplayExImpl.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isShowConnectingDisplayDialog: ", zBooleanValue), null, 8);
                OplusConnectingDisplayExImpl oplusConnectingDisplayExImpl = this.this$0;
                if (zBooleanValue) {
                    oplusConnectingDisplayExImpl.showDialog(oplusConnectingDisplayExImpl.currentPendingDisplay, this.this$0.isDesktopModeSupported, this.this$0.isInKioskMode, this.this$0.connectingExCallback);
                } else {
                    oplusConnectingDisplayExImpl.hideDialog();
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusConnectingDisplayExImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = OplusConnectingDisplayExImpl.this.isShowConnectingDisplayDialog;
                OplusConnectingDisplayExImpl oplusConnectingDisplayExImpl = OplusConnectingDisplayExImpl.this;
                C00381 c00381 = new C00381();
                c00381.this$0 = oplusConnectingDisplayExImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (flow.collect(c00381, this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusConnectingDisplayExImpl(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, ConfigurationController configurationController, DemoModeController demoModeController) {
        this.context = context;
        this.scope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        Flow flowDemoFlowForCommand = demoModeController.demoFlowForCommand(ZoomDCSManager.STATUS);
        this.demoCommandStream = flowDemoFlowForCommand;
        OplusConnectingDisplayExImpl$special$$inlined$map$1 oplusConnectingDisplayExImpl$special$$inlined$map$1 = new OplusConnectingDisplayExImpl$special$$inlined$map$1();
        oplusConnectingDisplayExImpl$special$$inlined$map$1.$this_unsafeTransform$inlined = flowDemoFlowForCommand;
        oplusConnectingDisplayExImpl$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.isShowConnectingDisplayDialog = FlowKt.filterNotNull(oplusConnectingDisplayExImpl$special$$inlined$map$1);
        this.clearData = true;
        configurationController.addCallback(this);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }

    public final boolean hideDialog() {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "hideDialog", null, 8);
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.dialog = null;
        if (!this.clearData) {
            return true;
        }
        this.connectingExCallback = null;
        this.currentPendingDisplay = null;
        return true;
    }

    public void onConfigChanged(Configuration configuration) {
        Dialog dialog = this.dialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "onConfigChanged , reshow dialog", null, 8);
        Executor mainExecutor = this.context.getMainExecutor();
        showDialog.3 r1 = new showDialog.3(1);
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        mainExecutor.execute(r1);
    }

    public final void onUiModeChanged() {
        Dialog dialog = this.dialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "ui mode changed , reshow dialog", null, 8);
        Executor mainExecutor = this.context.getMainExecutor();
        showDialog.3 r2 = new showDialog.3(1);
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        mainExecutor.execute(r2);
    }

    public final void reportLinked(String str) {
        if (this.linkedEventReported) {
            return;
        }
        this.linkedEventReported = true;
        ConnectedDisplayStatisticHelper.onLinked(this.context, str);
    }

    public boolean showDialog(ConnectedDisplayInteractor.PendingDisplay pendingDisplay, boolean z, boolean z2, ConnectingDisplayViewModel.ConnectingDisplayExActionCallback connectingDisplayExActionCallback) {
        COUIAlertDialogBuilder cOUIAlertDialogBuilder;
        this.currentPendingDisplay = pendingDisplay;
        this.isDesktopModeSupported = z;
        this.isInKioskMode = z2;
        this.connectingExCallback = connectingDisplayExActionCallback;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFactoryMode()) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "not show dp dialog in factory mode", null, 8);
            BuildersKt.launch$default(this.scope, this.bgDispatcher, (CoroutineStart) null, new showDialog.1(this, (Continuation) null), 2);
            return true;
        }
        Dialog dialog = this.dialog;
        if (dialog != null && dialog.isShowing()) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "already show dialog", null, 8);
            return true;
        }
        if (pendingDisplay == null || connectingDisplayExActionCallback == null) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "pendingDisplay or connectingExCallback is null", null, 8);
            return true;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("showDialog isDesktopModeSupported: ", ", isInKioskMode: ", "OplusConnectingDisplayExImpl-->", z, z2));
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(new ContextThemeWrapper(this.context, COUIDarkModeUtil.isNightMode(this.context) ? 2132019096 : 2132019095), 2132017609);
        COUIThemeOverlay.getInstance().applyThemeOverlays(contextThemeWrapper);
        if (z) {
            String[] strArr = {this.context.getString(2131955108), this.context.getString(2131955109)};
            String[] strArr2 = {this.context.getString(2131955106), this.context.getString(2131955107)};
            boolean[] zArr = {false, this.isInKioskMode};
            int[] iArr = new int[1];
            createDesktopDialogBuilder.singleChoiceListItemClickListener.1 r15 = new createDesktopDialogBuilder.singleChoiceListItemClickListener.1();
            r15.$radioIndex = iArr;
            VarHandle.storeStoreFence();
            ChoiceListAdapter choiceListAdapter = new ChoiceListAdapter(contextThemeWrapper, 2131558720, strArr, strArr2, new boolean[]{true, false}, zArr, false);
            cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(contextThemeWrapper, 2132017609);
            cOUIAlertDialogBuilder.setTitle(2131955112);
            cOUIAlertDialogBuilder.setSingleChoiceItems(choiceListAdapter, 0, r15);
            createMirrorDialogBuilder.builder.1.1 r2 = new createMirrorDialogBuilder.builder.1.1(1);
            r2.$pendingDisplay = pendingDisplay;
            r2.this$0 = this;
            VarHandle.storeStoreFence();
            cOUIAlertDialogBuilder.setNegativeButton$1(2131955104, r2);
            createDesktopDialogBuilder.builder.1.2 r3 = new createDesktopDialogBuilder.builder.1.2();
            r3.$radioIndex = iArr;
            r3.this$0 = this;
            r3.$pendingDisplay = pendingDisplay;
            VarHandle.storeStoreFence();
            cOUIAlertDialogBuilder.setPositiveButton$1(2131955105, r3);
            cOUIAlertDialogBuilder.setWindowType(2003);
            cOUIAlertDialogBuilder.setBlurBackgroundDrawable(true);
        } else {
            COUIAlertDialogBuilder cOUIAlertDialogBuilder2 = new COUIAlertDialogBuilder(contextThemeWrapper, 2132017609);
            cOUIAlertDialogBuilder2.setTitle(2131955112);
            cOUIAlertDialogBuilder2.setMessage(2131955111);
            createMirrorDialogBuilder.builder.1.1 r4 = new createMirrorDialogBuilder.builder.1.1(0);
            r4.$pendingDisplay = pendingDisplay;
            r4.this$0 = this;
            VarHandle.storeStoreFence();
            cOUIAlertDialogBuilder2.setNegativeButton$1(2131955104, r4);
            createMirrorDialogBuilder.builder.1.1 r5 = new createMirrorDialogBuilder.builder.1.1(2);
            r5.this$0 = this;
            r5.$pendingDisplay = pendingDisplay;
            VarHandle.storeStoreFence();
            cOUIAlertDialogBuilder2.setPositiveButton$1(2131955105, r5);
            cOUIAlertDialogBuilder2.setWindowType(2003);
            cOUIAlertDialogBuilder2.setBlurBackgroundDrawable(true);
            cOUIAlertDialogBuilder = cOUIAlertDialogBuilder2;
        }
        AlertDialog alertDialogCreate = cOUIAlertDialogBuilder.create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        this.linkedEventReported = false;
        showDialog.3 r6 = new showDialog.3(0);
        r6.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIDialog.registerDismissListener(alertDialogCreate, r6);
        alertDialogCreate.show();
        cOUIAlertDialogBuilder.updateViewAfterShown();
        Window window = alertDialogCreate.getWindow();
        if (window != null) {
            window.addPrivateFlags(16);
        }
        this.dialog = alertDialogCreate;
        return true;
    }
}
