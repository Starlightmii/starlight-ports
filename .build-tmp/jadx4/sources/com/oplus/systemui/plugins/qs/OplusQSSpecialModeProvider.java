package com.oplus.systemui.plugins.qs;

import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.plugins.PluginDependency;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSpecialModeProvider {
    public static final Companion Companion = new Companion();
    private static final String DEFAULT_COUNTRY = "CN";
    private static final String KEY = "qs_tile_colorful_config";
    private static final String TAG = "SeparateQSSpecialModeProvider";
    private static volatile OplusQSSpecialModeProvider instance;
    private final MutableStateFlow _colorState;
    private final MutableStateFlow _isDefaultFont;
    private final MutableStateFlow _isGestureNavigation;
    private final MutableStateFlow _isLandSplitMode;
    private final MutableStateFlow _isLargeScreen;
    private final MutableStateFlow _isNightMode;
    private final MutableStateFlow _isTileColorfulEnable;
    private final MutableStateFlow _keyguardShowing;
    private final MutableStateFlow _languageCountry;
    private final MutableSharedFlow _overlayChangedEvent;
    private final MutableStateFlow _rotation;
    private final MutableStateFlow _screenHeight;
    private final MutableStateFlow _screenWidth;
    private final MutableStateFlow _smallestScreenWidth;
    private final MutableStateFlow _superPowerSaveMode;
    private MutableStateFlow _userId;
    private final StateFlow colorState;
    public boolean colorfulSettingEnable;
    private Context context;
    private final StateFlow isDefaultFont;
    private final StateFlow isGestureNavigation;
    private final StateFlow isLandSplitMode;
    public boolean isLargeFoldDeviceUnFoldingCache;
    private final StateFlow isLargeScreen;
    private final StateFlow isNightMode;
    private final StateFlow isTileColorfulEnable;
    private final StateFlow keyguardShowing;
    private final StateFlow languageCountry;
    private final OplusQSSpecialModeProvider$observer$1 observer;
    private final SharedFlow overlayChangedEvent;
    private OplusSeparateQSPlugin.IStub proxy;
    private final StateFlow rotation;
    private final StateFlow screenHeight;
    private final StateFlow screenWidth;
    private final StateFlow smallestScreenWidth;
    private final OplusQSSpecialModeProvider$specialModeListener$1 specialModeListener;
    private QSSpecialModeUpdateMonitor specialModeUpdateMonitor;
    private final StateFlow superPowerSaveMode;
    private Context userContext;
    private final StateFlow userId;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusQSSpecialModeProvider getInstance() {
            OplusQSSpecialModeProvider oplusQSSpecialModeProvider;
            OplusQSSpecialModeProvider oplusQSSpecialModeProvider2 = OplusQSSpecialModeProvider.instance;
            if (oplusQSSpecialModeProvider2 != null) {
                return oplusQSSpecialModeProvider2;
            }
            synchronized (this) {
                oplusQSSpecialModeProvider = OplusQSSpecialModeProvider.instance;
                if (oplusQSSpecialModeProvider == null) {
                    oplusQSSpecialModeProvider = new OplusQSSpecialModeProvider();
                    OplusQSSpecialModeProvider.instance = oplusQSSpecialModeProvider;
                }
            }
            return oplusQSSpecialModeProvider;
        }
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider$observer$1] */
    public OplusQSSpecialModeProvider() {
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isLandSplitMode = MutableStateFlow;
        this.isLandSplitMode = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._isLargeScreen = MutableStateFlow2;
        this.isLargeScreen = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._superPowerSaveMode = MutableStateFlow3;
        this.superPowerSaveMode = FlowKt.asStateFlow(MutableStateFlow3);
        MutableStateFlow MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this._keyguardShowing = MutableStateFlow4;
        this.keyguardShowing = FlowKt.asStateFlow(MutableStateFlow4);
        MutableStateFlow MutableStateFlow5 = StateFlowKt.MutableStateFlow(0);
        this._smallestScreenWidth = MutableStateFlow5;
        this.smallestScreenWidth = FlowKt.asStateFlow(MutableStateFlow5);
        MutableStateFlow MutableStateFlow6 = StateFlowKt.MutableStateFlow(0);
        this._screenWidth = MutableStateFlow6;
        this.screenWidth = FlowKt.asStateFlow(MutableStateFlow6);
        MutableStateFlow MutableStateFlow7 = StateFlowKt.MutableStateFlow(0);
        this._screenHeight = MutableStateFlow7;
        this.screenHeight = FlowKt.asStateFlow(MutableStateFlow7);
        MutableStateFlow MutableStateFlow8 = StateFlowKt.MutableStateFlow(bool);
        this._isNightMode = MutableStateFlow8;
        this.isNightMode = FlowKt.asStateFlow(MutableStateFlow8);
        MutableStateFlow MutableStateFlow9 = StateFlowKt.MutableStateFlow(Integer.valueOf(QsColorUtil.getQsColorState()));
        this._colorState = MutableStateFlow9;
        this.colorState = FlowKt.asStateFlow(MutableStateFlow9);
        MutableStateFlow MutableStateFlow10 = StateFlowKt.MutableStateFlow(bool);
        this._isGestureNavigation = MutableStateFlow10;
        this.isGestureNavigation = FlowKt.asStateFlow(MutableStateFlow10);
        MutableStateFlow MutableStateFlow11 = StateFlowKt.MutableStateFlow(-1);
        this._rotation = MutableStateFlow11;
        this.rotation = FlowKt.asStateFlow(MutableStateFlow11);
        MutableStateFlow MutableStateFlow12 = StateFlowKt.MutableStateFlow(bool);
        this._isTileColorfulEnable = MutableStateFlow12;
        this.isTileColorfulEnable = FlowKt.asStateFlow(MutableStateFlow12);
        MutableStateFlow MutableStateFlow13 = StateFlowKt.MutableStateFlow(DEFAULT_COUNTRY);
        this._languageCountry = MutableStateFlow13;
        this.languageCountry = FlowKt.asStateFlow(MutableStateFlow13);
        MutableStateFlow MutableStateFlow14 = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._isDefaultFont = MutableStateFlow14;
        this.isDefaultFont = FlowKt.asStateFlow(MutableStateFlow14);
        MutableSharedFlow MutableSharedFlow = SharedFlowKt.MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST);
        this._overlayChangedEvent = MutableSharedFlow;
        this.overlayChangedEvent = FlowKt.asSharedFlow(MutableSharedFlow);
        MutableStateFlow MutableStateFlow15 = StateFlowKt.MutableStateFlow(0);
        this._userId = MutableStateFlow15;
        this.userId = FlowKt.asStateFlow(MutableStateFlow15);
        OplusQSSpecialModeProvider$specialModeListener$1 oplusQSSpecialModeProvider$specialModeListener$1 = new OplusQSSpecialModeProvider$specialModeListener$1();
        oplusQSSpecialModeProvider$specialModeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.specialModeListener = oplusQSSpecialModeProvider$specialModeListener$1;
        final Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        this.observer = new ContentObserver(workHandler) { // from class: com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider$observer$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                Context userContext = this.this$0.getUserContext();
                if (userContext != null) {
                    OplusQSSpecialModeProvider oplusQSSpecialModeProvider = this.this$0;
                    oplusQSSpecialModeProvider.colorfulSettingEnable = Settings.Secure.getIntForUser(userContext.getContentResolver(), "qs_tile_colorful_config", 0, userContext.getUserId()) == 0;
                    oplusQSSpecialModeProvider.updateColorfulConfigEnable();
                }
            }
        };
    }

    public static final OplusQSSpecialModeProvider getInstance() {
        return Companion.getInstance();
    }

    public final void bindStub(OplusSeparateQSPlugin.IStub iStub) {
        Unit unit;
        this.proxy = iStub;
        Context userContext = getUserContext();
        if (userContext != null) {
            if (userContext.getContentResolver() != null) {
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
            OplusQSSpecialModeProvider$bindStub$1$2 oplusQSSpecialModeProvider$bindStub$1$2 = new OplusQSSpecialModeProvider$bindStub$1$2();
            oplusQSSpecialModeProvider$bindStub$1$2.this$0 = this;
            oplusQSSpecialModeProvider$bindStub$1$2.$this_apply = userContext;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(oplusQSSpecialModeProvider$bindStub$1$2);
        }
    }

    public final StateFlow getColorState() {
        return this.colorState;
    }

    public final boolean getColorfulSettingEnable() {
        return this.colorfulSettingEnable;
    }

    public final StateFlow getKeyguardShowing() {
        return this.keyguardShowing;
    }

    public final StateFlow getLanguageCountry() {
        return this.languageCountry;
    }

    public final SharedFlow getOverlayChangedEvent() {
        return this.overlayChangedEvent;
    }

    public final StateFlow getRotation() {
        return this.rotation;
    }

    public final StateFlow getScreenHeight() {
        return this.screenHeight;
    }

    public final StateFlow getScreenWidth() {
        return this.screenWidth;
    }

    public final StateFlow getSmallestScreenWidth() {
        return this.smallestScreenWidth;
    }

    public final StateFlow getSuperPowerSaveMode() {
        return this.superPowerSaveMode;
    }

    public final Context getUserContext() {
        Context userContext;
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor = this.specialModeUpdateMonitor;
        return (qSSpecialModeUpdateMonitor == null || (userContext = qSSpecialModeUpdateMonitor.getUserContext()) == null) ? this.context : userContext;
    }

    public final StateFlow getUserId() {
        return this.userId;
    }

    public final StateFlow isDefaultFont() {
        return this.isDefaultFont;
    }

    public final boolean isGestureBarHide() {
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor = this.specialModeUpdateMonitor;
        if (qSSpecialModeUpdateMonitor != null) {
            return qSSpecialModeUpdateMonitor.isSideGestureBarHide();
        }
        return false;
    }

    public final StateFlow isGestureNavigation() {
        return this.isGestureNavigation;
    }

    public final StateFlow isLandSplitMode() {
        return this.isLandSplitMode;
    }

    public final StateFlow isLargeScreen() {
        return this.isLargeScreen;
    }

    public final StateFlow isNightMode() {
        return this.isNightMode;
    }

    public final boolean isPrivacyIconInQuickEntrance() {
        Context context = this.context;
        if (context != null) {
            return DeviceUtil.shouldChangePrivacyPlace(context.getResources());
        }
        return false;
    }

    public final boolean isSplitAdd() {
        Resources resources;
        Context context = this.context;
        if (context == null || (resources = context.getResources()) == null) {
            return false;
        }
        return resources.getBoolean(2131034211);
    }

    public final StateFlow isTileColorfulEnable() {
        return this.isTileColorfulEnable;
    }

    public final void onCreate(OplusSeparateQSPlugin oplusSeparateQSPlugin, Context context) {
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor = (QSSpecialModeUpdateMonitor) PluginDependency.INSTANCE.get(oplusSeparateQSPlugin, QSSpecialModeUpdateMonitor.class);
        this.specialModeUpdateMonitor = qSSpecialModeUpdateMonitor;
        this.context = qSSpecialModeUpdateMonitor != null ? qSSpecialModeUpdateMonitor.getWindowContext() : null;
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor2 = this.specialModeUpdateMonitor;
        if (qSSpecialModeUpdateMonitor2 != null) {
            qSSpecialModeUpdateMonitor2.addListener(this.specialModeListener);
        }
        MutableStateFlow mutableStateFlow = this._superPowerSaveMode;
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor3 = this.specialModeUpdateMonitor;
        mutableStateFlow.setValue(Boolean.valueOf(qSSpecialModeUpdateMonitor3 != null ? qSSpecialModeUpdateMonitor3.isSuperPowerSaveMode() : false));
        Context context2 = this.context;
        if (context2 != null) {
            this._isLandSplitMode.setValue(Boolean.valueOf(context2.getResources().getBoolean(2131034210)));
            this._isLargeScreen.setValue(Boolean.valueOf(context2.getResources().getBoolean(2131034209)));
            this._smallestScreenWidth.setValue(Integer.valueOf(context2.getResources().getConfiguration().smallestScreenWidthDp));
            Rect bounds = context2.getResources().getConfiguration().windowConfiguration.getBounds();
            this._screenWidth.setValue(Integer.valueOf(bounds.right - bounds.left));
            this._screenHeight.setValue(Integer.valueOf(bounds.bottom - bounds.top));
            this._isNightMode.setValue(Boolean.valueOf(OplusDarkModeUtil.isNightMode(context2)));
            this._rotation.setValue(Integer.valueOf(context2.getDisplay().getRotation()));
        }
        this._colorState.setValue(Integer.valueOf(QsColorUtil.getQsColorState()));
        MutableStateFlow mutableStateFlow2 = this._isGestureNavigation;
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor4 = this.specialModeUpdateMonitor;
        mutableStateFlow2.setValue(Boolean.valueOf(qSSpecialModeUpdateMonitor4 != null ? qSSpecialModeUpdateMonitor4.isGestureNavigation() : true));
        updateColorfulConfigEnable();
        updateLargeFoldDeviceUnFoldingCache();
    }

    public final void onDestroy() {
        OplusSeparateQSPlugin.IStub iStub;
        QSSpecialModeUpdateMonitor qSSpecialModeUpdateMonitor = this.specialModeUpdateMonitor;
        if (qSSpecialModeUpdateMonitor != null) {
            qSSpecialModeUpdateMonitor.destroy();
        }
        this.specialModeUpdateMonitor = null;
        Context userContext = getUserContext();
        if (userContext == null || (iStub = this.proxy) == null) {
            return;
        }
        iStub.unregister(userContext, this.observer);
    }

    public final void updateColorfulConfigEnable() {
        Context userContext = getUserContext();
        if (userContext != null) {
            boolean z = false;
            if (ScrimUtil.isWallpaperBlurDisable()) {
                QsColorfulConfigUtil.INSTANCE.setEnableColorfulConfig(false);
                this._isTileColorfulEnable.setValue(Boolean.FALSE);
                return;
            }
            if (QsColorUtil.isGlobalThemeApplied(userContext)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "SeparateQSSpecialModeProvider-->updateTileColorfulEnable isGlobalThemeApplied ");
                }
            } else if (this.colorfulSettingEnable) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "SeparateQSSpecialModeProvider-->updateTileColorfulEnable true");
                }
                z = true;
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "SeparateQSSpecialModeProvider-->updateTileColorfulEnable getTileColorfulEnableBySettings return");
            }
            QsColorfulConfigUtil.INSTANCE.setEnableColorfulConfig(z);
            this._isTileColorfulEnable.setValue(Boolean.valueOf(z));
        }
    }

    public final void updateLargeFoldDeviceUnFoldingCache() {
        Context context = this.context;
        boolean z = false;
        if (context != null && context.getResources().getDimensionPixelSize(2131172043) != -1) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet()) {
                z = true;
            }
        }
        this.isLargeFoldDeviceUnFoldingCache = z;
    }
}
