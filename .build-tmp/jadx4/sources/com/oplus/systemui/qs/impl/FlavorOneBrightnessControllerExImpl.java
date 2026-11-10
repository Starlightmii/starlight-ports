package com.oplus.systemui.qs.impl;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.OplusPowerManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.oplus.systemui.qs.observer.IncreaseBrightnessObserver;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;
import java.util.Locale;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FlavorOneBrightnessControllerExImpl extends OplusBrightnessControllerExImpl {
    public static final Companion Companion = new Companion();
    private static final String TAG = "FlavorBrightnessController";
    public int brightnessSecMax;
    public boolean hasInited;
    private final Context mContext;
    private IBrightnessSliderController mController;
    private IncreaseBrightnessObserver mIncreaseBrightnessObserver;
    private final FlavorOneBrightnessControllerExImpl$mObserverListener$1 mObserverListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public FlavorOneBrightnessControllerExImpl(Context context) {
        super(context);
        this.mContext = context;
        this.brightnessSecMax = 1;
        FlavorOneBrightnessControllerExImpl$mObserverListener$1 flavorOneBrightnessControllerExImpl$mObserverListener$1 = new FlavorOneBrightnessControllerExImpl$mObserverListener$1();
        flavorOneBrightnessControllerExImpl$mObserverListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mObserverListener = flavorOneBrightnessControllerExImpl$mObserverListener$1;
    }

    public int getBrightnessSecMax() {
        return this.brightnessSecMax;
    }

    public int getMaxBrightness() {
        if (!FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported()) {
            return getBrightnessMax();
        }
        IncreaseBrightnessObserver increaseBrightnessObserver = this.mIncreaseBrightnessObserver;
        return (increaseBrightnessObserver == null || !increaseBrightnessObserver.isIncreaseBrightnessEnabled) ? getBrightnessSecMax() : getBrightnessMax();
    }

    @Override // com.oplus.systemui.qs.impl.OplusBrightnessControllerExImpl
    public void init(DisplayManager displayManager) {
        Object obj;
        super.init(displayManager);
        if (this.hasInited) {
            return;
        }
        if (FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported()) {
            this.mIncreaseBrightnessObserver = IncreaseBrightnessObserver.Companion.getInstance();
            OplusPowerManager oplusPowerManager = new OplusPowerManager();
            try {
                Method methodWithParams = ReflectionUtils.getMethodWithParams(oplusPowerManager.getClass(), Float.TYPE);
                methodWithParams.setAccessible(true);
                setBrightnessSecMax((int) ((float[]) methodWithParams.invoke(oplusPowerManager, Float.valueOf(600.0f)))[0]);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("initBrightnessSecMax, onFailure", th2.getMessage(), "FlavorBrightnessController-->", "SystemUi--QuickSettings");
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(getBrightnessSecMax(), "brightnessSecMax=", "FlavorBrightnessController-->", "SystemUi--QuickSettings");
            }
            IncreaseBrightnessObserver increaseBrightnessObserver = this.mIncreaseBrightnessObserver;
            if (increaseBrightnessObserver != null) {
                increaseBrightnessObserver.registerForUserSwitch(this.mContext, -1, false);
            }
        }
        this.hasInited = true;
    }

    public void initControl(IBrightnessSliderController iBrightnessSliderController) {
        if (FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported()) {
            this.mController = iBrightnessSliderController;
        }
    }

    public final boolean isHighBrightness(int i) {
        IncreaseBrightnessObserver increaseBrightnessObserver;
        return FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported() && (increaseBrightnessObserver = this.mIncreaseBrightnessObserver) != null && increaseBrightnessObserver.isIncreaseBrightnessEnabled && getBrightnessMin() + i > getBrightnessSecMax();
    }

    public final boolean isLongLanguage() {
        String languageTag = Locale.getDefault().toLanguageTag();
        return TextUtils.equals(languageTag, "my-MM") || TextUtils.equals(languageTag, "my-ZG") || TextUtils.equals(languageTag, "ru-RU");
    }

    public final void registerIncreaseBrightnessObserver() {
        IncreaseBrightnessObserver increaseBrightnessObserver;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "FlavorBrightnessController-->registerIncreaseBrightnessObserver() called");
        }
        if (!FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported() || (increaseBrightnessObserver = this.mIncreaseBrightnessObserver) == null) {
            return;
        }
        increaseBrightnessObserver.addListener(this.mObserverListener);
    }

    public void setBrightnessSecMax(int i) {
        this.brightnessSecMax = i;
    }

    public final void unregisterIncreaseBrightnessObserver() {
        IncreaseBrightnessObserver increaseBrightnessObserver;
        if (!FlavorOneFeatureOption.INSTANCE.isBrightnessSecMaxSupported() || (increaseBrightnessObserver = this.mIncreaseBrightnessObserver) == null) {
            return;
        }
        increaseBrightnessObserver.removeListener(this.mObserverListener);
    }
}
