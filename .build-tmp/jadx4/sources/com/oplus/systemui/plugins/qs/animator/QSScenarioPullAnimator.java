package com.oplus.systemui.plugins.qs.animator;

import androidx.core.view.insets.Protection$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.scenario.QSScenarioAnimListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSScenarioPullAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSScenarioPullAnimator";
    public boolean isEnterScenario;
    public boolean isNotifyAnimatorEnd;
    public boolean isTracking;
    private final Lazy pullToScenarioAnimation$delegate;
    private final QSScenarioAnimListener qsScenarioAnimListener;
    public float scenarioFraction;
    private final OplusSeparateQSPlugin.IStub stub;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final float getEXPAND_TRANSLATION_THRESHOLD() {
            return OliveLinkConnector.INSTANCE.safeScalar(OliveLinkConnector.SEP_QS_SERVER_NAME, "expand_scenario_threshold", "scenario", Float.valueOf(260.0f), "开始进入场景建议页面阈值").get().floatValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float getEXPAND_TRANSLATION_THRESHOLD_FIRST() {
            return OliveLinkConnector.INSTANCE.safeScalar(OliveLinkConnector.SEP_QS_SERVER_NAME, "expand_scenario_threshold_first", "scenario", Float.valueOf(750.0f), "开始进入场景建议页面阈值第一次").get().floatValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float getEXPAND_TRANSLATION_THRESHOLD_LAND() {
            return OliveLinkConnector.INSTANCE.safeScalar(OliveLinkConnector.SEP_QS_SERVER_NAME, "expand_scenario_threshold_land", "scenario", Float.valueOf(140.0f), "开始进入场景建议页面阈值横屏").get().floatValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float getEXPAND_TRANSLATION_THRESHOLD_LAND_FIRST() {
            return OliveLinkConnector.INSTANCE.safeScalar(OliveLinkConnector.SEP_QS_SERVER_NAME, "expand_scenario_threshold_land_first", "scenario", Float.valueOf(600.0f), "开始进入场景建议页面阈值横屏第一次").get().floatValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float getFLING_SPRING_RESPONSE() {
            return OliveLinkConnector.INSTANCE.safeScalar(OliveLinkConnector.SEP_QS_SERVER_NAME, "expand_scenario_response", "scenario", Float.valueOf(0.35f), "进入场景建议 动画response").get().floatValue();
        }
    }

    public QSScenarioPullAnimator(QSScenarioAnimListener qSScenarioAnimListener, OplusSeparateQSPlugin.IStub iStub) {
        this.qsScenarioAnimListener = qSScenarioAnimListener;
        this.stub = iStub;
        QSScenarioPullAnimator$$ExternalSyntheticLambda0 qSScenarioPullAnimator$$ExternalSyntheticLambda0 = new QSScenarioPullAnimator$$ExternalSyntheticLambda0();
        qSScenarioPullAnimator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.pullToScenarioAnimation$delegate = LazyKt__LazyJVMKt.lazy(qSScenarioPullAnimator$$ExternalSyntheticLambda0);
        this.isNotifyAnimatorEnd = true;
    }

    private final COUISpringAnimation getPullToScenarioAnimation() {
        return (COUISpringAnimation) this.pullToScenarioAnimation$delegate.getValue();
    }

    public final void flingScenario(Boolean bool) {
        getPullToScenarioAnimation().getSpring().setResponse(Companion.getFLING_SPRING_RESPONSE());
        boolean zBooleanValue = bool != null ? bool.booleanValue() : this.isEnterScenario;
        this.isEnterScenario = zBooleanValue;
        float f = zBooleanValue ? 1.0f : 0.0f;
        if (f != this.scenarioFraction || ((COUIDynamicAnimation) getPullToScenarioAnimation()).mRunning) {
            getPullToScenarioAnimation().animateToFinalPosition(f);
        } else {
            if (this.isTracking || f != this.scenarioFraction || ((COUIDynamicAnimation) getPullToScenarioAnimation()).mRunning) {
                return;
            }
            notifyScenarioAnimEnd(f);
        }
    }

    public final QSScenarioAnimListener getQsScenarioAnimListener() {
        return this.qsScenarioAnimListener;
    }

    public final OplusSeparateQSPlugin.IStub getStub() {
        return this.stub;
    }

    public final void moveDistance(float f, boolean z) {
        float expand_translation_threshold_first;
        if (((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue()) {
            expand_translation_threshold_first = z ? Companion.getEXPAND_TRANSLATION_THRESHOLD_LAND_FIRST() : Companion.getEXPAND_TRANSLATION_THRESHOLD_LAND();
        } else {
            expand_translation_threshold_first = z ? Companion.getEXPAND_TRANSLATION_THRESHOLD_FIRST() : Companion.getEXPAND_TRANSLATION_THRESHOLD();
        }
        boolean z2 = f >= expand_translation_threshold_first;
        if (this.isEnterScenario != z2) {
            if (z2) {
                SeparateQSStatisicUtil.updateScenarioEntryMode(SeparateQSStatisicUtil.SCENARIO_ENTRY_MODE_TEMP_ENTER);
            }
            this.stub.performHapticFeedback(true ^ this.isEnterScenario);
            if (z2 && this.isNotifyAnimatorEnd) {
                this.isNotifyAnimatorEnd = false;
                float f2 = this.scenarioFraction;
                LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(f2, "pullToScenarioAnimation notifyScenarioAnimStart cur:"), null, 8);
                this.qsScenarioAnimListener.onAnimatorStart(f2);
            } else {
                flingScenario(Boolean.valueOf(z2));
            }
            this.isEnterScenario = z2;
        }
    }

    public final void notifyScenarioAnimEnd(float f) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(f, "pullToScenarioAnimation notifyScenarioAnimEnd cur:"), null, 8);
        this.isNotifyAnimatorEnd = true;
        this.qsScenarioAnimListener.onAnimatorEnd(f);
    }

    public final void resetScenarioAnimator() {
        setTracking(false);
        this.isEnterScenario = false;
        getPullToScenarioAnimation().animateToFinalPosition(0.0f);
        if (getPullToScenarioAnimation().canSkipToEnd()) {
            getPullToScenarioAnimation().skipToEnd();
        }
        this.scenarioFraction = 0.0f;
    }

    public final void setEnterScenario(boolean z) {
        this.isEnterScenario = z;
    }

    public final void setTracking(boolean z) {
        boolean z2 = this.isTracking;
        if (z != z2) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("setTracking tracking:", " isTracking=", z, z2), null, 8);
            this.isTracking = z;
        }
    }
}
