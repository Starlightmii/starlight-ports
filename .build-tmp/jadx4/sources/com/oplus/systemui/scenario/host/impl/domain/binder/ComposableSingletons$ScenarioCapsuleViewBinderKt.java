package com.oplus.systemui.scenario.host.impl.domain.binder;

import android.support.v4.content.FileProvider$;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ComposableSingletons$ScenarioCapsuleViewBinderKt {

    /* JADX INFO: renamed from: lambda$-13134187, reason: not valid java name */
    private static Function2 f2lambda$13134187;
    private static Function2 lambda$411936030;
    private static Function2 lambda$77693743;
    public static final ComposableSingletons$ScenarioCapsuleViewBinderKt INSTANCE = new ComposableSingletons$ScenarioCapsuleViewBinderKt();

    /* JADX INFO: renamed from: lambda$-723417697, reason: not valid java name */
    private static Function3 f3lambda$723417697 = ComposableLambdaKt.composableLambdaInstance(-723417697, false, new ComposableSingletons$ScenarioCapsuleViewBinderKt$.ExternalSyntheticLambda3());

    public static Unit $r8$lambda$CY4mvwVKPFkaRCqBht6mw3iAvo0(Composer composer, int i, String str) {
        if ((i & 6) == 0) {
            i |= composer.changed(str) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-723417697, i, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$-723417697.<anonymous> (ScenarioCapsuleViewBinder.kt:265)");
            }
            LogUtil.i$default(LogUtil.TAG_QS, "ScenarioCapsuleViewBinder", FileProvider$.ExternalSyntheticOutline0.m("CapsuleContent: capsuleState=", str), null, 8);
            if (Intrinsics.areEqual(str, "SCENARIO_CAPSULE")) {
                composer.startReplaceGroup(580300968);
                ScenarioCapsuleViewBinder.INSTANCE.FirstLaunchProvider(lambda$411936030, composer, 70);
                composer.endReplaceGroup();
            } else {
                if (Intrinsics.areEqual(str, "PRIVACY_CAPSULE")) {
                    composer.startReplaceGroup(580303975);
                    ScenarioCapsuleViewBinder.INSTANCE.FirstLaunchProvider(f2lambda$13134187, composer, 70);
                } else {
                    composer.startReplaceGroup(797062531);
                }
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static {
        final int i = 0;
        lambda$77693743 = ComposableLambdaKt.composableLambdaInstance(77693743, false, new Function2() { // from class: com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt$$ExternalSyntheticLambda0
            public final Object invoke(Object obj, Object obj2) {
                Composer composer = (Composer) obj;
                Integer num = (Integer) obj2;
                switch (i) {
                    case 0:
                        int iIntValue = num.intValue();
                        if (composer.shouldExecute((iIntValue & 3) != 2, iIntValue & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(77693743, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$77693743.<anonymous> (ScenarioCapsuleViewBinder.kt:262)");
                            }
                            ScenarioCapsuleViewBinder.INSTANCE.CapsuleBackground(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), composer, 70);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composer.skipToGroupEnd();
                        }
                        break;
                    case 1:
                        int iIntValue2 = num.intValue();
                        if (composer.shouldExecute((iIntValue2 & 3) != 2, iIntValue2 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(411936030, iIntValue2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$411936030.<anonymous> (ScenarioCapsuleViewBinder.kt:267)");
                            }
                            ScenarioCapsuleViewBinder.INSTANCE.ScenarioSection(composer, 8);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composer.skipToGroupEnd();
                        }
                        break;
                    default:
                        int iIntValue3 = num.intValue();
                        if (composer.shouldExecute((iIntValue3 & 3) != 2, iIntValue3 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-13134187, iIntValue3, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$-13134187.<anonymous> (ScenarioCapsuleViewBinder.kt:268)");
                            }
                            ScenarioCapsuleViewBinder.INSTANCE.PrivacySection(composer, 8);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composer.skipToGroupEnd();
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
        final int i2 = 1;
        lambda$411936030 = ComposableLambdaKt.composableLambdaInstance(411936030, false, new Function2() { // from class: com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt$$ExternalSyntheticLambda0
            public final Object invoke(Object obj, Object obj2) {
                Composer composer = (Composer) obj;
                Integer num = (Integer) obj2;
                switch (i2) {
                    case 0:
                        int iIntValue = num.intValue();
                        if (composer.shouldExecute((iIntValue & 3) != 2, iIntValue & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(77693743, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$77693743.<anonymous> (ScenarioCapsuleViewBinder.kt:262)");
                            }
                            ScenarioCapsuleViewBinder.INSTANCE.CapsuleBackground(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), composer, 70);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composer.skipToGroupEnd();
                        }
                        break;
                    case 1:
                        int iIntValue2 = num.intValue();
                        if (composer.shouldExecute((iIntValue2 & 3) != 2, iIntValue2 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(411936030, iIntValue2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$411936030.<anonymous> (ScenarioCapsuleViewBinder.kt:267)");
                            }
                            ScenarioCapsuleViewBinder.INSTANCE.ScenarioSection(composer, 8);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composer.skipToGroupEnd();
                        }
                        break;
                    default:
                        int iIntValue3 = num.intValue();
                        if (composer.shouldExecute((iIntValue3 & 3) != 2, iIntValue3 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-13134187, iIntValue3, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$-13134187.<anonymous> (ScenarioCapsuleViewBinder.kt:268)");
                            }
                            ScenarioCapsuleViewBinder.INSTANCE.PrivacySection(composer, 8);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composer.skipToGroupEnd();
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
        final int i3 = 2;
        f2lambda$13134187 = ComposableLambdaKt.composableLambdaInstance(-13134187, false, new Function2() { // from class: com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt$$ExternalSyntheticLambda0
            public final Object invoke(Object obj, Object obj2) {
                Composer composer = (Composer) obj;
                Integer num = (Integer) obj2;
                switch (i3) {
                    case 0:
                        int iIntValue = num.intValue();
                        if (composer.shouldExecute((iIntValue & 3) != 2, iIntValue & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(77693743, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$77693743.<anonymous> (ScenarioCapsuleViewBinder.kt:262)");
                            }
                            ScenarioCapsuleViewBinder.INSTANCE.CapsuleBackground(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), composer, 70);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composer.skipToGroupEnd();
                        }
                        break;
                    case 1:
                        int iIntValue2 = num.intValue();
                        if (composer.shouldExecute((iIntValue2 & 3) != 2, iIntValue2 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(411936030, iIntValue2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$411936030.<anonymous> (ScenarioCapsuleViewBinder.kt:267)");
                            }
                            ScenarioCapsuleViewBinder.INSTANCE.ScenarioSection(composer, 8);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composer.skipToGroupEnd();
                        }
                        break;
                    default:
                        int iIntValue3 = num.intValue();
                        if (composer.shouldExecute((iIntValue3 & 3) != 2, iIntValue3 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-13134187, iIntValue3, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ComposableSingletons$ScenarioCapsuleViewBinderKt.lambda$-13134187.<anonymous> (ScenarioCapsuleViewBinder.kt:268)");
                            }
                            ScenarioCapsuleViewBinder.INSTANCE.PrivacySection(composer, 8);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        } else {
                            composer.skipToGroupEnd();
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX INFO: renamed from: getLambda$-13134187$vendor__oplus__packages__apps__systemui__oplus_pods__scenario__host__android_common__com_oplus_systemui_scenario_host, reason: not valid java name */
    public final Function2 m356x1bb319cb() {
        return f2lambda$13134187;
    }

    /* JADX INFO: renamed from: getLambda$-723417697$vendor__oplus__packages__apps__systemui__oplus_pods__scenario__host__android_common__com_oplus_systemui_scenario_host, reason: not valid java name */
    public final Function3 m357x26f9cef1() {
        return f3lambda$723417697;
    }

    public final Function2 getLambda$411936030$vendor__oplus__packages__apps__systemui__oplus_pods__scenario__host__android_common__com_oplus_systemui_scenario_host() {
        return lambda$411936030;
    }

    public final Function2 getLambda$77693743$vendor__oplus__packages__apps__systemui__oplus_pods__scenario__host__android_common__com_oplus_systemui_scenario_host() {
        return lambda$77693743;
    }
}
