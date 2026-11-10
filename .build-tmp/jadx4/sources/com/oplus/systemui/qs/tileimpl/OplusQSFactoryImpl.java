package com.oplus.systemui.qs.tileimpl;

import android.util.Log;
import com.android.systemui.plugins.qs.QSFactory;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.systemui.qs.tiles.AliPayTile;
import com.oplus.systemui.qs.tiles.AliScanTile;
import com.oplus.systemui.qs.tiles.AutoScreenTemperatureTile;
import com.oplus.systemui.qs.tiles.BreathingLightTile;
import com.oplus.systemui.qs.tiles.CalculatorTile;
import com.oplus.systemui.qs.tiles.DarkModeTile;
import com.oplus.systemui.qs.tiles.EnvelopeTile;
import com.oplus.systemui.qs.tiles.FlavorOneOtgTile;
import com.oplus.systemui.qs.tiles.GoogleNowTile;
import com.oplus.systemui.qs.tiles.InputMethodTile;
import com.oplus.systemui.qs.tiles.Iris5Tile;
import com.oplus.systemui.qs.tiles.LockTile;
import com.oplus.systemui.qs.tiles.MultiSystemTile;
import com.oplus.systemui.qs.tiles.NoDisturbTile;
import com.oplus.systemui.qs.tiles.OplusInnerFakeTile;
import com.oplus.systemui.qs.tiles.OplusShutDownTile;
import com.oplus.systemui.qs.tiles.OsieTile;
import com.oplus.systemui.qs.tiles.PhoneAntiTheftTile;
import com.oplus.systemui.qs.tiles.RingerModeTile;
import com.oplus.systemui.qs.tiles.ScreenRecorderTile;
import com.oplus.systemui.qs.tiles.ScreenshotTile;
import com.oplus.systemui.qs.tiles.SuperPowerSaveModeTile;
import com.oplus.systemui.qs.tiles.VibrationModeTile;
import com.oplus.systemui.qs.tiles.WeChatScanTile;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import javax.inject.Provider;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFactoryImpl implements QSFactory {
    private static final String CTS_TILE_PKG = "android.app.stubs";
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSFactory";
    private final Provider mAirplaneModeTileProvider;
    private final Provider mAliPayTileProvider;
    private final Provider mAliScanTileProvider;
    private final Provider mAutoBrightnessProvider;
    private final Provider mBatterySaverTileProvider;
    private final Provider mBluetoothTileProvider;
    private final Provider mBreathingLightTileProvider;
    private final Provider mCalculatorTileProvider;
    private final Provider mCameraToggleTileProvider;
    private final Provider mCellularTileProvider;
    private final Provider mCleanTileProvider;
    private final Provider mCustomTileBuilderProvider;
    private final Provider mDarkModeTileProvider;
    private final Provider mDataSaverTileProvider;
    private final Provider mDeviceControlsTileProvider;
    private final Provider mDndTileProvider;
    private final Provider mDolbyTileProvider;
    private final Provider mEnvelopeTileProvider;
    private final Provider mFlashlightTileProvider;
    private final Provider mFlavorOneOtgTileProvider;
    private final Provider mFlavorTwoVPNTileProvider;
    private final Provider mGoogleNowTileProvider;
    private final Provider mHotspotTileProvider;
    private final Provider mInputMethodTileProvider;
    private final Provider mIris5TileProvider;
    private final Provider mLocationTileProvider;
    private final Provider mLockTileProvider;
    private final Provider mMicrophoneToggleTileProvider;
    private final Provider mMultiSystemTileProvider;
    private final Provider mNfcTileProvider;
    private final Provider mNodisturbTileProvider;
    private final Provider mOplusShutDownTile;
    private final Provider mOsieTileProvider;
    private final Provider mPhoneAntiTheftTileProvider;
    private final Provider mQRCodeScannerTileProvider;
    private final Lazy mQsHostLazy;
    private final Provider mQuickAccessWalletTileProvider;
    private final Provider mRotationLockTileProvider;
    private final Provider mScreensRecorderProvider;
    private final Provider mScreenshotTileProvider;
    private final Provider mSmallScreenTileProvider;
    private final Provider mSuperPowerSaveModeTileProvider;
    private final Provider mTemperatureTileProvider;
    private final Provider mThreeStageRingerModeTileProvider;
    private final Provider mVibrationModeTileProvider;
    private final Provider mWechatPayTileProvider;
    private final Provider mWechatScanTileProvider;
    private final Provider mWifiTileProvider;
    private final Provider mWorkModeTileProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSFactoryImpl(Lazy lazy, Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, Provider provider14, Provider provider15, Provider provider16, Provider provider17, Provider provider18, Provider provider19, Provider provider20, Provider provider21, Provider provider22, Provider provider23, Provider provider24, Provider provider25, Provider provider26, Provider provider27, Provider provider28, Provider provider29, Provider provider30, Provider provider31, Provider provider32, Provider provider33, Provider provider34, Provider provider35, Provider provider36, Provider provider37, Provider provider38, Provider provider39, Provider provider40, Provider provider41, Provider provider42, Provider provider43, Provider provider44, Provider provider45, Provider provider46, Provider provider47, Provider provider48) {
        this.mQsHostLazy = lazy;
        this.mCustomTileBuilderProvider = provider;
        this.mDataSaverTileProvider = provider2;
        this.mQuickAccessWalletTileProvider = provider3;
        this.mBluetoothTileProvider = provider4;
        this.mDndTileProvider = provider5;
        this.mAirplaneModeTileProvider = provider6;
        this.mWorkModeTileProvider = provider7;
        this.mRotationLockTileProvider = provider8;
        this.mFlashlightTileProvider = provider9;
        this.mLocationTileProvider = provider10;
        this.mHotspotTileProvider = provider11;
        this.mBatterySaverTileProvider = provider12;
        this.mNfcTileProvider = provider13;
        this.mDeviceControlsTileProvider = provider14;
        this.mQRCodeScannerTileProvider = provider15;
        this.mCameraToggleTileProvider = provider16;
        this.mMicrophoneToggleTileProvider = provider17;
        this.mWifiTileProvider = provider18;
        this.mCellularTileProvider = provider19;
        this.mLockTileProvider = provider20;
        this.mAliPayTileProvider = provider21;
        this.mAliScanTileProvider = provider22;
        this.mWechatPayTileProvider = provider23;
        this.mWechatScanTileProvider = provider24;
        this.mCleanTileProvider = provider25;
        this.mSmallScreenTileProvider = provider26;
        this.mCalculatorTileProvider = provider27;
        this.mThreeStageRingerModeTileProvider = provider28;
        this.mGoogleNowTileProvider = provider29;
        this.mNodisturbTileProvider = provider30;
        this.mAutoBrightnessProvider = provider31;
        this.mSuperPowerSaveModeTileProvider = provider32;
        this.mMultiSystemTileProvider = provider33;
        this.mScreenshotTileProvider = provider34;
        this.mEnvelopeTileProvider = provider35;
        this.mScreensRecorderProvider = provider36;
        this.mVibrationModeTileProvider = provider37;
        this.mIris5TileProvider = provider38;
        this.mOsieTileProvider = provider39;
        this.mDarkModeTileProvider = provider40;
        this.mTemperatureTileProvider = provider41;
        this.mDolbyTileProvider = provider42;
        this.mInputMethodTileProvider = provider43;
        this.mOplusShutDownTile = provider44;
        this.mBreathingLightTileProvider = provider45;
        this.mPhoneAntiTheftTileProvider = provider46;
        this.mFlavorOneOtgTileProvider = provider47;
        this.mFlavorTwoVPNTileProvider = provider48;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:18:0x003e  */
    /* JADX WARN: Code duplicated, block: B:211:0x042f  */
    /* JADX WARN: Code duplicated, block: B:213:0x0438  */
    /* JADX WARN: Code duplicated, block: B:215:0x0440  */
    /* JADX WARN: Code duplicated, block: B:221:0x046e  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public QSTile createTile(String str) {
        OplusInnerFakeTile oplusInnerFakeTile;
        int iHashCode = str.hashCode();
        QSTileImpl qSTileImplCreate = null;
        if (iHashCode != -1999972780) {
            if (iHashCode != -1480077968) {
                if (iHashCode == 1105097290 && str.equals(OplusInnerFakeTile.SPEC_DEVICE)) {
                    FeatureOption.INSTANCE.getClass();
                    oplusInnerFakeTile = new OplusInnerFakeTile(str, FeatureOption.isSupportMyDevice());
                }
            } else if (str.equals(OplusInnerFakeTile.SPEC_MEDIA)) {
                oplusInnerFakeTile = new OplusInnerFakeTile(str, true);
            }
            oplusInnerFakeTile = null;
        } else if (str.equals(OplusInnerFakeTile.SPEC_BRIGHTNESS_VOLUME)) {
            oplusInnerFakeTile = new OplusInnerFakeTile(str, true);
        } else {
            oplusInnerFakeTile = null;
        }
        if (oplusInnerFakeTile != null) {
            return oplusInnerFakeTile;
        }
        switch (str.hashCode()) {
            case -2048782384:
                if (str.equals(EnvelopeTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mEnvelopeTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -1885795609:
                if (str.equals(SuperPowerSaveModeTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mSuperPowerSaveModeTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -1414960566:
                if (str.equals(AliPayTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mAliPayTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -1183073498:
                if (str.equals("flashlight")) {
                    qSTileImplCreate = (QSTileImpl) this.mFlashlightTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -1138529534:
                if (str.equals(CalculatorTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mCalculatorTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -919209152:
                if (str.equals("autobrightness")) {
                    qSTileImplCreate = (QSTileImpl) this.mAutoBrightnessProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -914013925:
                if (str.equals(AliScanTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mAliScanTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -805491779:
                if (str.equals(ScreenRecorderTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mScreensRecorderProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -795192327:
                if (str.equals("wallet")) {
                    qSTileImplCreate = (QSTileImpl) this.mQuickAccessWalletTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -677011630:
                if (str.equals("airplane")) {
                    qSTileImplCreate = (QSTileImpl) this.mAirplaneModeTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -566933834:
                if (str.equals("controls")) {
                    qSTileImplCreate = (QSTileImpl) this.mDeviceControlsTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -416447130:
                if (str.equals(ScreenshotTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mScreenshotTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -364217041:
                if (str.equals(PhoneAntiTheftTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mPhoneAntiTheftTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -331239923:
                if (str.equals("battery")) {
                    qSTileImplCreate = (QSTileImpl) this.mBatterySaverTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -169343402:
                if (str.equals(OplusShutDownTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mOplusShutDownTile.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -81857902:
                if (str.equals(VibrationModeTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mVibrationModeTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -40300674:
                if (str.equals("rotation")) {
                    qSTileImplCreate = (QSTileImpl) this.mRotationLockTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case -37334949:
                if (str.equals(OplusQSHighlightTileViewLabelTextHandler.MIC_ACCESS)) {
                    qSTileImplCreate = (QSTileImpl) this.mMicrophoneToggleTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 3154:
                if (str.equals(OplusQSHighlightTileViewLabelTextHandler.BLUETOOTH)) {
                    qSTileImplCreate = (QSTileImpl) this.mBluetoothTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 99610:
                if (str.equals(OplusQSHighlightTileViewLabelTextHandler.DND)) {
                    qSTileImplCreate = (QSTileImpl) this.mDndTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 108971:
                if (str.equals("nfc")) {
                    qSTileImplCreate = (QSTileImpl) this.mNfcTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 110370:
                if (str.equals(FlavorOneOtgTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mFlavorOneOtgTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 116980:
                if (str.equals("vpn")) {
                    qSTileImplCreate = (QSTileImpl) this.mFlavorTwoVPNTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 3049826:
                if (str.equals(OplusQSHighlightTileViewLabelTextHandler.CELL)) {
                    qSTileImplCreate = (QSTileImpl) this.mCellularTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 3330352:
                if (str.equals(Iris5Tile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mIris5TileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 3420672:
                if (str.equals(OsieTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mOsieTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 3649301:
                if (str.equals(OplusQSHighlightTileViewLabelTextHandler.WIFI)) {
                    qSTileImplCreate = (QSTileImpl) this.mWifiTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 3655441:
                if (str.equals("work")) {
                    qSTileImplCreate = (QSTileImpl) this.mWorkModeTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 6344377:
                if (str.equals(OplusQSHighlightTileViewLabelTextHandler.CAMERA_ACCESS)) {
                    qSTileImplCreate = (QSTileImpl) this.mCameraToggleTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 32846899:
                if (str.equals("smallscreen")) {
                    qSTileImplCreate = (QSTileImpl) this.mSmallScreenTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 94746189:
                if (str.equals("clear")) {
                    qSTileImplCreate = (QSTileImpl) this.mCleanTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 95756548:
                if (str.equals("dobly")) {
                    qSTileImplCreate = (QSTileImpl) this.mDolbyTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 109211285:
                if (str.equals("saver")) {
                    qSTileImplCreate = (QSTileImpl) this.mDataSaverTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 424585515:
                if (str.equals(InputMethodTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mInputMethodTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 440509140:
                if (str.equals(BreathingLightTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mBreathingLightTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 578953145:
                if (str.equals(AutoScreenTemperatureTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mTemperatureTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 598816030:
                if (str.equals(NoDisturbTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mNodisturbTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 660781224:
                if (str.equals(MultiSystemTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mMultiSystemTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 769345503:
                if (str.equals(WeChatScanTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mWechatScanTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 876619530:
                if (str.equals("qr_code_scanner")) {
                    qSTileImplCreate = (QSTileImpl) this.mQRCodeScannerTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 922496512:
                if (str.equals(RingerModeTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mThreeStageRingerModeTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 1099603663:
                if (str.equals(OplusQSHighlightTileViewLabelTextHandler.HOTSPOT)) {
                    qSTileImplCreate = (QSTileImpl) this.mHotspotTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 1474524669:
                if (str.equals(GoogleNowTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mGoogleNowTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 1741782553:
                if (str.equals(DarkModeTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mDarkModeTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 1792850263:
                if (str.equals(LockTile.TILE_SPEC)) {
                    qSTileImplCreate = (QSTileImpl) this.mLockTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 1825929990:
                if (str.equals("weixinpay")) {
                    qSTileImplCreate = (QSTileImpl) this.mWechatPayTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            case 1901043637:
                if (str.equals("location")) {
                    qSTileImplCreate = (QSTileImpl) this.mLocationTileProvider.get();
                } else if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
            default:
                if (StringsKt__StringsJVMKt.startsWith(str, "custom(", false)) {
                    Log.w(TAG, "No stock tile spec: ".concat(str));
                } else {
                    if (StringsKt__StringsKt.contains(str, CTS_TILE_PKG, false)) {
                        KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTileInternal special tileSpec: ", str, "QSFactory-->", "SystemUi--QuickSettings");
                    }
                    qSTileImplCreate = CustomTile.create((CustomTile.Factory) this.mCustomTileBuilderProvider.get(), str, ((QSHost) this.mQsHostLazy.get()).getUserContext());
                }
                break;
        }
        if (qSTileImplCreate != null) {
            QSTileImpl.H h = qSTileImplCreate.mHandler;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                KeyguardSettingProvider$.ExternalSyntheticOutline0.m("createTile: ", str, "QSFactory-->", "SystemUi--Common");
            }
            qSTileImplCreate.setTileSpec(str);
            h.sendEmptyMessage(12);
            h.sendEmptyMessage(11);
        }
        return qSTileImplCreate;
    }
}
