package com.oplus.systemui.qs.impl;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.OplusPowerManager;
import android.provider.Settings;
import android.util.Log;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.qs.OplusBrightnessControllerEx;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.brightness.BrightnessNotification;
import com.oplus.systemui.qs.brightness.BrightnessNotification$updateBrightnessHandler$1;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusBrightnessControllerExImpl extends OplusBrightnessControllerEx {
    public static final Companion Companion = new Companion();
    public static final String SCREEN_BRIGHTNESS_RANGE = "screen_brightness_range";
    private static final String TAG = "OplusBrightnessControllerExImpl";
    public static int lightControllerBrightness;
    public boolean automatic;
    public int brightnessMax;
    public int brightnessMin;
    private DisplayManager displayManager;
    public boolean hasInited;
    public boolean isMultiBits;
    private BrightnessNotification mBrightnessNotification;
    private final Context mContext;
    public boolean needUpdateWhenFirstListen;
    private final OplusPowerManager powerManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getLightControllerBrightness() {
            return OplusBrightnessControllerExImpl.lightControllerBrightness;
        }

        public final void setLightControllerBrightness(int i) {
            OplusBrightnessControllerExImpl.lightControllerBrightness = i;
        }
    }

    public OplusBrightnessControllerExImpl(Context context) {
        super(context);
        this.mContext = context;
        this.mBrightnessNotification = new BrightnessNotification(context);
        this.powerManager = new OplusPowerManager();
        this.brightnessMax = 1;
    }

    public boolean getAutomatic() {
        return this.automatic;
    }

    public int getBrightness() {
        int brightnessMax = (getBrightnessMax() + getBrightnessMin()) / 2;
        String brightness = QsSettingsValueProxy.Companion.getBrightness(this.mContext);
        if (brightness != null) {
            try {
                brightnessMax = Integer.parseInt(brightness);
            } catch (NumberFormatException unused) {
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "getBrightness: valueStr=".concat(brightness));
            }
        }
        return Math.max(getBrightnessMin(), Math.min(getBrightnessMax(), brightnessMax));
    }

    public int getBrightnessMax() {
        return this.brightnessMax;
    }

    public int getBrightnessMin() {
        return this.brightnessMin;
    }

    public boolean getNeedUpdateWhenFirstListen() {
        return this.needUpdateWhenFirstListen;
    }

    public void init(DisplayManager displayManager) {
        if (this.hasInited) {
            return;
        }
        this.displayManager = displayManager;
        setMultiBits(OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.multibits_dimming_support"));
        setBrightnessMin(this.powerManager.getMinimumScreenBrightnessSetting());
        setBrightnessMax(this.powerManager.getMaximumScreenBrightnessSetting());
        boolean z = this.isMultiBits;
        int brightnessMin = getBrightnessMin();
        int brightnessMax = getBrightnessMax();
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("init: isMultiBits=", brightnessMin, " ,brightnessMin=", "  ,brightnessMax=", z);
        sbM.append(brightnessMax);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        this.hasInited = true;
        setNeedUpdateWhenFirstListen(true);
    }

    public final boolean isMultiBits() {
        return this.isMultiBits;
    }

    public void setAutomatic(boolean z) {
        this.automatic = z;
    }

    public void setBrightness(int i) {
        BrightnessNotification brightnessNotification = this.mBrightnessNotification;
        int brightness = getBrightness();
        boolean automatic = getAutomatic();
        brightnessNotification.getClass();
        Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        BrightnessNotification$updateBrightnessHandler$1 brightnessNotification$updateBrightnessHandler$1 = new BrightnessNotification$updateBrightnessHandler$1();
        brightnessNotification$updateBrightnessHandler$1.this$0 = brightnessNotification;
        brightnessNotification$updateBrightnessHandler$1.$brightness = brightness;
        brightnessNotification$updateBrightnessHandler$1.$automatic = automatic;
        VarHandle.storeStoreFence();
        workHandler.post(brightnessNotification$updateBrightnessHandler$1);
        QsSettingsValueProxy.Companion.setBrightness(this.mContext, String.valueOf(i));
        try {
            Settings.System.putFloatForUser(this.mContext.getContentResolver(), "screen_auto_brightness_adj", i, -2);
        } catch (Exception e) {
            Log.e(TAG, "set screen_auto_brightness_adj error", e);
        }
    }

    public void setBrightnessMax(int i) {
        this.brightnessMax = i;
    }

    public void setBrightnessMin(int i) {
        this.brightnessMin = i;
    }

    public void setMultiBits(boolean z) {
        this.isMultiBits = z;
    }

    public void setNeedUpdateWhenFirstListen(boolean z) {
        this.needUpdateWhenFirstListen = z;
    }

    public final void setTemporaryBrightness(int i, int i2, boolean z) {
        if (z) {
            int iMax = Math.max(getBrightnessMin(), Math.min(getBrightnessMax(), i2));
            DisplayManager displayManager = this.displayManager;
            if (displayManager != null) {
                displayManager.setTemporaryBrightness(i, iMax);
            }
            DisplayManager displayManager2 = this.displayManager;
            if (displayManager2 != null) {
                displayManager2.setTemporaryAutoBrightnessAdjustment(iMax);
            }
            lightControllerBrightness = iMax;
        }
    }

    public final void updateMinMaxBrightness() {
        Object obj;
        Pair pair;
        try {
            String stringForUser = Settings.System.getStringForUser(this.mContext.getContentResolver(), SCREEN_BRIGHTNESS_RANGE, -2);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateMinMaxBrightness: value=" + stringForUser, null, 8);
            if (stringForUser != null) {
                List listSplit$default = StringsKt__StringsKt.split$default(stringForUser, new String[]{OplusPreventModeData.SEPARATOR}, 0, 6);
                pair = new Pair(Integer.valueOf(Integer.parseInt((String) listSplit$default.get(0))), Integer.valueOf(Integer.parseInt((String) listSplit$default.get(1))));
            } else {
                pair = new Pair(0, 0);
            }
            obj = Result.constructor-impl(pair);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        Pair pair2 = (Pair) obj;
        if (pair2 == null || ((Number) pair2.getSecond()).intValue() <= ((Number) pair2.getFirst()).intValue()) {
            setBrightnessMin(this.powerManager.getMinimumScreenBrightnessSetting());
            setBrightnessMax(this.powerManager.getMaximumScreenBrightnessSetting());
        } else {
            setBrightnessMin(((Number) pair2.getFirst()).intValue());
            setBrightnessMax(((Number) pair2.getSecond()).intValue());
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(getBrightnessMin(), getBrightnessMax(), "updateMinMaxBrightness: brightnessMin=", ", brightnessMax="), null, 8);
    }
}
