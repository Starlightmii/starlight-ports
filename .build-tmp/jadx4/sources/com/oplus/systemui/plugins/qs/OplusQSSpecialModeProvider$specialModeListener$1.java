package com.oplus.systemui.plugins.qs;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSpecialModeProvider$specialModeListener$1 implements QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener {
    public /* synthetic */ OplusQSSpecialModeProvider this$0;

    public final void onConfigChanged(Configuration configuration) {
        OplusSeparateQSPlugin.IStub iStub;
        int rotation = configuration.windowConfiguration.getRotation();
        OplusQSSpecialModeProvider oplusQSSpecialModeProvider = this.this$0;
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", "onConfigChanged rotation" + rotation + " last rotation " + oplusQSSpecialModeProvider._rotation.getValue(), null, 8);
        Context context = oplusQSSpecialModeProvider.context;
        if (context != null) {
            Rect bounds = context.getResources().getConfiguration().windowConfiguration.getBounds();
            oplusQSSpecialModeProvider._screenWidth.setValue(Integer.valueOf(bounds.right - bounds.left));
            oplusQSSpecialModeProvider._screenHeight.setValue(Integer.valueOf(bounds.bottom - bounds.top));
            oplusQSSpecialModeProvider._rotation.setValue(Integer.valueOf(configuration.windowConfiguration.getRotation()));
            oplusQSSpecialModeProvider._isLandSplitMode.setValue(Boolean.valueOf(context.getResources().getBoolean(2131034210)));
            oplusQSSpecialModeProvider._isLargeScreen.setValue(Boolean.valueOf(context.getResources().getBoolean(2131034209)));
            oplusQSSpecialModeProvider._smallestScreenWidth.setValue(Integer.valueOf(configuration.smallestScreenWidthDp));
            oplusQSSpecialModeProvider._languageCountry.setValue(configuration.locale.getCountry());
        }
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor = oplusQSSpecialModeProvider.specialModeUpdateMonitor;
        if (qSSpecialModeUpdateMonitor != null && ((qSSpecialModeUpdateMonitor.isApplyDynamicFontVariationConfig() || qSSpecialModeUpdateMonitor.isApplyFlipFontConfig()) && (iStub = oplusQSSpecialModeProvider.proxy) != null)) {
            iStub.onReloadQSRootView();
        }
        int i = configuration.uiMode & 48;
        MutableStateFlow mutableStateFlow = oplusQSSpecialModeProvider._isNightMode;
        boolean z = 32 == i;
        Boolean boolValueOf = Boolean.valueOf(z);
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", KeyguardLogger$.ExternalSyntheticOutline0.m("uiMode of newConfig: ", z), null, 8);
        oplusQSSpecialModeProvider.updateColorfulConfigEnable();
        mutableStateFlow.setValue(boolValueOf);
        oplusQSSpecialModeProvider.updateLargeFoldDeviceUnFoldingCache();
    }

    public final void onDensityOrFontScaleChanged() {
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", "onDensityOrFontScaleChanged", null, 8);
        OplusSeparateQSPlugin.IStub iStub = this.this$0.proxy;
        if (iStub != null) {
            iStub.onReloadQSRootView();
        }
    }

    public final void onDisplayRotationChanged(int i) {
        OplusQSSpecialModeProvider oplusQSSpecialModeProvider = this.this$0;
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", "onDisplayRotationChanged rotation: " + i + " last rotation: " + oplusQSSpecialModeProvider._rotation.getValue(), null, 8);
        oplusQSSpecialModeProvider._rotation.setValue(Integer.valueOf(i));
    }

    public final void onFontChanged(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", KeyguardLogger$.ExternalSyntheticOutline0.m("onFontChanged isDefaultFont: ", z), null, 8);
        this.this$0._isDefaultFont.setValue(Boolean.valueOf(z));
    }

    public final void onGestureModeChange() {
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", "onGestureModeChange", null, 8);
        OplusQSSpecialModeProvider oplusQSSpecialModeProvider = this.this$0;
        MutableStateFlow mutableStateFlow = oplusQSSpecialModeProvider._isGestureNavigation;
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor = oplusQSSpecialModeProvider.specialModeUpdateMonitor;
        mutableStateFlow.setValue(Boolean.valueOf(qSSpecialModeUpdateMonitor != null ? qSSpecialModeUpdateMonitor.isGestureNavigation() : true));
    }

    public final void onKeyguardShowingChanged() {
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", "onKeyguardShowingChanged", null, 8);
        OplusQSSpecialModeProvider oplusQSSpecialModeProvider = this.this$0;
        MutableStateFlow mutableStateFlow = oplusQSSpecialModeProvider._keyguardShowing;
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor = oplusQSSpecialModeProvider.specialModeUpdateMonitor;
        boolean z = false;
        if (qSSpecialModeUpdateMonitor != null && qSSpecialModeUpdateMonitor.isKeyguardShowing()) {
            z = true;
        }
        mutableStateFlow.setValue(Boolean.valueOf(z));
    }

    public final void onLayoutDirectionChanged(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", KeyguardLogger$.ExternalSyntheticOutline0.m("onLayoutDirectionChanged: ", z), null, 8);
        OplusSeparateQSPlugin.IStub iStub = this.this$0.proxy;
        if (iStub != null) {
            iStub.onReloadQSRootView();
        }
    }

    public final void onLocaleListChanged() {
        OplusQSSpecialModeProvider oplusQSSpecialModeProvider = this.this$0;
        OplusSeparateQSPlugin.IStub iStub = oplusQSSpecialModeProvider.proxy;
        boolean zIsDeviceProvisioned = iStub != null ? iStub.isDeviceProvisioned() : true;
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", KeyguardLogger$.ExternalSyntheticOutline0.m("onLocaleListChanged, isDeviceProvisioned: ", zIsDeviceProvisioned), null, 8);
        if (zIsDeviceProvisioned) {
            OplusSeparateQSPlugin.IStub iStub2 = oplusQSSpecialModeProvider.proxy;
            if (iStub2 != null) {
                iStub2.onReloadQSRootView();
                return;
            }
            return;
        }
        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
        OplusQSSpecialModeProvider$specialModeListener$1$onLocaleListChanged$1 oplusQSSpecialModeProvider$specialModeListener$1$onLocaleListChanged$1 = new OplusQSSpecialModeProvider$specialModeListener$1$onLocaleListChanged$1();
        oplusQSSpecialModeProvider$specialModeListener$1$onLocaleListChanged$1.this$0 = oplusQSSpecialModeProvider;
        VarHandle.storeStoreFence();
        mainHandler.post(oplusQSSpecialModeProvider$specialModeListener$1$onLocaleListChanged$1);
    }

    public final void onOverlayChanged() {
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", "onOverlayChanged", null, 8);
        this.this$0._overlayChangedEvent.tryEmit(Unit.INSTANCE);
    }

    public final void onQSColorStateChanged() {
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", "onQSColorStateChanged", null, 8);
        this.this$0._colorState.setValue(Integer.valueOf(QsColorUtil.getQsColorState()));
    }

    public final void onSuperPowerSaveModeChanged() {
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", "onSuperPowerSaveModeChanged", null, 8);
        OplusQSSpecialModeProvider oplusQSSpecialModeProvider = this.this$0;
        MutableStateFlow mutableStateFlow = oplusQSSpecialModeProvider._superPowerSaveMode;
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor = oplusQSSpecialModeProvider.specialModeUpdateMonitor;
        mutableStateFlow.setValue(Boolean.valueOf(qSSpecialModeUpdateMonitor != null ? qSSpecialModeUpdateMonitor.isSuperPowerSaveMode() : false));
    }

    public final void onUserChanged(int i, Context context) {
        LogUtil.i$default(LogUtil.TAG_QS, "SeparateQSSpecialModeProvider", "onUserChanged newUser:" + i + " userContext:" + context, null, 8);
        OplusQSSpecialModeProvider oplusQSSpecialModeProvider = this.this$0;
        oplusQSSpecialModeProvider._userId.setValue(Integer.valueOf(i));
        OplusSeparateQSPlugin.IStub iStub = oplusQSSpecialModeProvider.proxy;
        if (iStub != null) {
            iStub.onReloadQSRootView();
        }
    }
}
