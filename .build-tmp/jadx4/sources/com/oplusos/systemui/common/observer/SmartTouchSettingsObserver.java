package com.oplusos.systemui.common.observer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmartTouchSettingsObserver extends AbstractObserver {
    private static final String TAG = "SmartTouchSettingsObserver";
    public boolean mSmartTouchOpened;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new SmartTouchSettingsObserver$.ExternalSyntheticLambda0());

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return CommonSettingsValueProxy.SETTINGS_SMART_TOUCH_SWITCH;
    }

    public final boolean getMSmartTouchOpened() {
        return this.mSmartTouchOpened;
    }

    public boolean getSmartTouchModeState() {
        return this.mSmartTouchOpened;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context != null) {
            String systemStringValueForCurUser = AbsSettingsValueProxy.Companion.getSystemStringValueForCurUser(context, CommonSettingsValueProxy.SETTINGS_SMART_TOUCH_SWITCH);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onChange smartTouchModeValue: ", systemStringValueForCurUser, "SmartTouchSettingsObserver-->", "SystemUi--Common");
            }
            if (systemStringValueForCurUser != null) {
                setSmartTouchModeState(systemStringValueForCurUser);
            }
        }
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        String systemStringValueForCurUser = AbsSettingsValueProxy.Companion.getSystemStringValueForCurUser(context, CommonSettingsValueProxy.SETTINGS_SMART_TOUCH_SWITCH);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onRegister smartTouchModeValue: ", systemStringValueForCurUser, "SmartTouchSettingsObserver-->", "SystemUi--Common");
        }
        if (systemStringValueForCurUser != null) {
            setSmartTouchModeState(systemStringValueForCurUser);
        }
    }

    public void setSmartTouchModeState(String str) {
        boolean zEquals = false;
        if (!TextUtils.isEmpty(str) && str.length() == 2) {
            zEquals = str.substring(0, 1).equals("1");
        }
        this.mSmartTouchOpened = zEquals;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setSmartTouchModeState mSmartTouchOpened: ", "SmartTouchSettingsObserver-->", "SystemUi--Common", this.mSmartTouchOpened);
        }
    }
}
