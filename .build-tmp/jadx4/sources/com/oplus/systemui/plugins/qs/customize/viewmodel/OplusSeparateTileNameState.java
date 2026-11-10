package com.oplus.systemui.plugins.qs.customize.viewmodel;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateTileNameState {
    public static final Companion Companion = new Companion();
    private static final String KEY = "qs_show_tile_name";
    private static final String TAG = "OplusSeparateTileNameState";
    private static volatile OplusSeparateTileNameState instance;
    private final MutableStateFlow _enableShowTileName;
    private final StateFlow enableShowTileName;
    private final OplusSeparateTileNameState$observer$1 observer;
    private OplusSeparateQSPlugin.IStub proxy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusSeparateTileNameState getInstance() {
            OplusSeparateTileNameState oplusSeparateTileNameState;
            OplusSeparateTileNameState oplusSeparateTileNameState2 = OplusSeparateTileNameState.instance;
            if (oplusSeparateTileNameState2 != null) {
                return oplusSeparateTileNameState2;
            }
            synchronized (this) {
                oplusSeparateTileNameState = OplusSeparateTileNameState.instance;
                if (oplusSeparateTileNameState == null) {
                    oplusSeparateTileNameState = new OplusSeparateTileNameState();
                    OplusSeparateTileNameState.instance = oplusSeparateTileNameState;
                }
            }
            return oplusSeparateTileNameState;
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.oplus.systemui.plugins.qs.customize.viewmodel.OplusSeparateTileNameState$observer$1] */
    public OplusSeparateTileNameState() {
        MutableStateFlow mutableStateFlowTraceAs = FlowExtKt.traceAs(StateFlowKt.MutableStateFlow(Boolean.FALSE), "OplusSeparateTileNameState-flow-enableShowTileName");
        this._enableShowTileName = mutableStateFlowTraceAs;
        this.enableShowTileName = FlowKt.asStateFlow(mutableStateFlowTraceAs);
        final Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        this.observer = new ContentObserver(workHandler) { // from class: com.oplus.systemui.plugins.qs.customize.viewmodel.OplusSeparateTileNameState$observer$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                this.this$0._enableShowTileName.setValue(Boolean.valueOf(this.this$0.getTileNameStateBySettings()));
                OplusSeparateTileNameState oplusSeparateTileNameState = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("onChange enableShowTileName: ", oplusSeparateTileNameState._enableShowTileName.getValue(), "OplusSeparateTileNameState-->", "SystemUi--QuickSettings");
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getTileNameStateBySettings() {
        Context userContext = OplusQSSpecialModeProvider.Companion.getInstance().getUserContext();
        return userContext != null && Settings.Secure.getIntForUser(userContext.getContentResolver(), KEY, 1, userContext.getUserId()) == 1;
    }

    public final void bindStub(OplusSeparateQSPlugin.IStub iStub) {
        Unit unit;
        this.proxy = iStub;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusSeparateTileNameState-->registerTileNameStateObserver");
        }
        Context userContext = OplusQSSpecialModeProvider.Companion.getInstance().getUserContext();
        if (userContext != null && userContext.getContentResolver() != null) {
            try {
                OplusSeparateQSPlugin.IStub iStub2 = this.proxy;
                if (iStub2 != null) {
                    iStub2.registerForUserSwitch(userContext, Settings.Secure.getUriFor(KEY), false, this.observer);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                Result.constructor-impl(unit);
            } catch (Throwable th) {
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
        OplusSeparateTileNameState$registerTileNameStateObserver$3 oplusSeparateTileNameState$registerTileNameStateObserver$3 = new OplusSeparateTileNameState$registerTileNameStateObserver$3();
        oplusSeparateTileNameState$registerTileNameStateObserver$3.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(oplusSeparateTileNameState$registerTileNameStateObserver$3);
    }

    public final StateFlow getEnableShowTileName() {
        return this.enableShowTileName;
    }

    public final void onDestroy() {
        OplusSeparateQSPlugin.IStub iStub;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusSeparateTileNameState-->unregisterContentObserver");
        }
        Context userContext = OplusQSSpecialModeProvider.Companion.getInstance().getUserContext();
        if (userContext == null || (iStub = this.proxy) == null) {
            return;
        }
        iStub.unregister(userContext, this.observer);
    }

    public final void setTileNameStateBySettings(int i) {
        Context userContext = OplusQSSpecialModeProvider.Companion.getInstance().getUserContext();
        if (userContext != null) {
            Settings.Secure.putIntForUser(userContext.getContentResolver(), KEY, i, userContext.getUserId());
        }
    }
}
