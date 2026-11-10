package com.oplus.systemui.plugins.qs.guide;

import android.content.Context;
import android.util.Log;
import com.android.systemui.plugins.qs.QSGuide;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideStateProvider {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    private static final String TAG = "OplusQsGuideStateProvider";
    private final OplusQsGuideStateProvider$cachedSpInt$1 animationEndTimes$delegate;
    private final Context context;
    private final OplusQsGuideStateProvider$cachedSpBoolean$1 isNotificationGuideCompleted$delegate;
    private final OplusQsGuideStateProvider$cachedSpBoolean$1 isQsGuideCompleted$delegate;
    private QSGuide qsGuide;
    private final OplusQsGuideStateProvider$cachedTestGuideMode$1 testGuideMode$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(OplusQsGuideStateProvider.class, "isNotificationGuideCompleted", "isNotificationGuideCompleted()Z", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, new MutablePropertyReference1Impl(OplusQsGuideStateProvider.class, "isQsGuideCompleted", "isQsGuideCompleted()Z", 0), new MutablePropertyReference1Impl(OplusQsGuideStateProvider.class, "animationEndTimes", "getAnimationEndTimes()I", 0), new PropertyReference1Impl(OplusQsGuideStateProvider.class, "testGuideMode", "getTestGuideMode()I", 0)};
        Companion = new Companion();
    }

    public OplusQsGuideStateProvider(Context context) {
        this.context = context;
        OplusQsGuideStateProvider$cachedSpBoolean$1 oplusQsGuideStateProvider$cachedSpBoolean$1 = new OplusQsGuideStateProvider$cachedSpBoolean$1();
        oplusQsGuideStateProvider$cachedSpBoolean$1.this$0 = this;
        oplusQsGuideStateProvider$cachedSpBoolean$1.$key = OplusQsGuideSpUtils.NOTIFICATION_SHOW_GUIDE;
        VarHandle.storeStoreFence();
        this.isNotificationGuideCompleted$delegate = oplusQsGuideStateProvider$cachedSpBoolean$1;
        OplusQsGuideStateProvider$cachedSpBoolean$1 oplusQsGuideStateProvider$cachedSpBoolean$2 = new OplusQsGuideStateProvider$cachedSpBoolean$1();
        oplusQsGuideStateProvider$cachedSpBoolean$2.this$0 = this;
        oplusQsGuideStateProvider$cachedSpBoolean$2.$key = OplusQsGuideSpUtils.QUICK_SETTINGS_SHOW_GUIDE;
        VarHandle.storeStoreFence();
        this.isQsGuideCompleted$delegate = oplusQsGuideStateProvider$cachedSpBoolean$2;
        OplusQsGuideStateProvider$cachedSpInt$1 oplusQsGuideStateProvider$cachedSpInt$1 = new OplusQsGuideStateProvider$cachedSpInt$1();
        oplusQsGuideStateProvider$cachedSpInt$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationEndTimes$delegate = oplusQsGuideStateProvider$cachedSpInt$1;
        OplusQsGuideStateProvider$cachedTestGuideMode$1 oplusQsGuideStateProvider$cachedTestGuideMode$1 = new OplusQsGuideStateProvider$cachedTestGuideMode$1();
        oplusQsGuideStateProvider$cachedTestGuideMode$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.testGuideMode$delegate = oplusQsGuideStateProvider$cachedTestGuideMode$1;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideStateProvider--> init");
        }
    }

    public static boolean isPhoneLandscape() {
        FeatureOption.INSTANCE.getClass();
        return (!FeatureOption.isFoldDevice() && !FeatureOption.isTablet()) && ((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue();
    }

    public final void bindQsGuide(QSGuide qSGuide) {
        this.qsGuide = qSGuide;
    }

    public final int getAnimationEndTimes() {
        OplusQsGuideStateProvider$cachedSpInt$1 oplusQsGuideStateProvider$cachedSpInt$1 = this.animationEndTimes$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        Integer num = oplusQsGuideStateProvider$cachedSpInt$1.cached;
        if (num != null) {
            return num.intValue();
        }
        int guideSpIntValue = OplusQsGuideSpUtils.getGuideSpIntValue(oplusQsGuideStateProvider$cachedSpInt$1.this$0.context, OplusQsGuideSpUtils.GUIDE_ANIMATION_END_TIMES, 0);
        oplusQsGuideStateProvider$cachedSpInt$1.cached = Integer.valueOf(guideSpIntValue);
        return guideSpIntValue;
    }

    public final int getTestGuideMode() {
        OplusQsGuideStateProvider$cachedTestGuideMode$1 oplusQsGuideStateProvider$cachedTestGuideMode$1 = this.testGuideMode$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        Integer num = oplusQsGuideStateProvider$cachedTestGuideMode$1.cached;
        if (num != null) {
            return num.intValue();
        }
        int testGuideMode = OplusQsGuideSpUtils.getTestGuideMode(oplusQsGuideStateProvider$cachedTestGuideMode$1.this$0.context);
        oplusQsGuideStateProvider$cachedTestGuideMode$1.cached = Integer.valueOf(testGuideMode);
        return testGuideMode;
    }

    public final boolean isAllGuideComplete() {
        return isNotificationGuideCompleted() && isQsGuideCompleted() && ScenarioAssistantGuideProvider.INSTANCE.isQsScenarioAssistantGuideCompleted(this.context);
    }

    public final boolean isHeadsUpNotificationShow() {
        QSGuide qSGuide = this.qsGuide;
        if (qSGuide != null) {
            return qSGuide.isHeadsUpNotificationShow();
        }
        return false;
    }

    public final boolean isKeyguardShow() {
        QSGuide qSGuide = this.qsGuide;
        if (qSGuide != null) {
            return qSGuide.isKeyguardShowing();
        }
        return false;
    }

    public final boolean isLaunchAppTop() {
        QSGuide qSGuide = this.qsGuide;
        if (qSGuide != null) {
            return qSGuide.isLaunchAppAtTop();
        }
        return true;
    }

    public final boolean isMainUser() {
        QSGuide qSGuide = this.qsGuide;
        if (qSGuide != null) {
            return qSGuide.isMainUser();
        }
        return true;
    }

    public final boolean isNotificationGuideCompleted() {
        OplusQsGuideStateProvider$cachedSpBoolean$1 oplusQsGuideStateProvider$cachedSpBoolean$1 = this.isNotificationGuideCompleted$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        Boolean bool = oplusQsGuideStateProvider$cachedSpBoolean$1.cached;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean guideSpBooleanValue = OplusQsGuideSpUtils.getGuideSpBooleanValue(oplusQsGuideStateProvider$cachedSpBoolean$1.this$0.context, oplusQsGuideStateProvider$cachedSpBoolean$1.$key, false);
        oplusQsGuideStateProvider$cachedSpBoolean$1.cached = Boolean.valueOf(guideSpBooleanValue);
        return guideSpBooleanValue;
    }

    public final boolean isProvisionComplete() {
        QSGuide qSGuide = this.qsGuide;
        if (qSGuide != null) {
            return qSGuide.isProvisionComplete();
        }
        return true;
    }

    public final boolean isQsGuideCompleted() {
        OplusQsGuideStateProvider$cachedSpBoolean$1 oplusQsGuideStateProvider$cachedSpBoolean$1 = this.isQsGuideCompleted$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        Boolean bool = oplusQsGuideStateProvider$cachedSpBoolean$1.cached;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean guideSpBooleanValue = OplusQsGuideSpUtils.getGuideSpBooleanValue(oplusQsGuideStateProvider$cachedSpBoolean$1.this$0.context, oplusQsGuideStateProvider$cachedSpBoolean$1.$key, false);
        oplusQsGuideStateProvider$cachedSpBoolean$1.cached = Boolean.valueOf(guideSpBooleanValue);
        return guideSpBooleanValue;
    }

    public final boolean isSelectDialogNeedShow() {
        QSGuide qSGuide = this.qsGuide;
        if (qSGuide != null) {
            return qSGuide.isSelectDialogNeedShow();
        }
        return false;
    }

    public final void setAnimationEndTimes(int i) {
        OplusQsGuideStateProvider$cachedSpInt$1 oplusQsGuideStateProvider$cachedSpInt$1 = this.animationEndTimes$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        oplusQsGuideStateProvider$cachedSpInt$1.cached = Integer.valueOf(i);
        OplusQsGuideSpUtils.setGuideSpIntValue(oplusQsGuideStateProvider$cachedSpInt$1.this$0.context, OplusQsGuideSpUtils.GUIDE_ANIMATION_END_TIMES, i);
    }

    public final void setNotificationGuideCompleted(boolean z) {
        OplusQsGuideStateProvider$cachedSpBoolean$1 oplusQsGuideStateProvider$cachedSpBoolean$1 = this.isNotificationGuideCompleted$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        oplusQsGuideStateProvider$cachedSpBoolean$1.cached = Boolean.valueOf(z);
        OplusQsGuideSpUtils.setGuideSpBooleanValue(oplusQsGuideStateProvider$cachedSpBoolean$1.this$0.context, oplusQsGuideStateProvider$cachedSpBoolean$1.$key, z);
    }

    public final void setQsGuideCompleted(boolean z) {
        OplusQsGuideStateProvider$cachedSpBoolean$1 oplusQsGuideStateProvider$cachedSpBoolean$1 = this.isQsGuideCompleted$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        oplusQsGuideStateProvider$cachedSpBoolean$1.cached = Boolean.valueOf(z);
        OplusQsGuideSpUtils.setGuideSpBooleanValue(oplusQsGuideStateProvider$cachedSpBoolean$1.this$0.context, oplusQsGuideStateProvider$cachedSpBoolean$1.$key, z);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("OplusQsGuideStateProvider@" + hashCode() + "(");
        stringBuffer.append("qsGuide=" + this.qsGuide + ", ");
        stringBuffer.append("isNotificationGuideFinished=" + isNotificationGuideCompleted() + ", ");
        stringBuffer.append("isQsGuideFinished=" + isQsGuideCompleted() + ", ");
        stringBuffer.append("isHeadsUpNotificationShow=" + isHeadsUpNotificationShow() + ", ");
        stringBuffer.append("isKeyguardShow=" + isKeyguardShow() + ", ");
        stringBuffer.append("isPhoneLandscape=" + isPhoneLandscape() + ", ");
        stringBuffer.append("isSuperPowerSavingMode=" + ((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().getSuperPowerSaveMode().getValue()).booleanValue() + ", ");
        stringBuffer.append("isSelectDialogNeedShow=" + isSelectDialogNeedShow() + ", ");
        stringBuffer.append("isMainUser=" + isMainUser() + ", ");
        stringBuffer.append("isGuideComplete=" + isAllGuideComplete() + ", ");
        stringBuffer.append("isLaunchAppTop=" + isLaunchAppTop() + ", ");
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public final void updateStatusBarVisible(int i, boolean z) {
        QSGuide qSGuide = this.qsGuide;
        if (qSGuide != null) {
            qSGuide.updateStatusBarVisible(z, i, true);
        }
    }
}
