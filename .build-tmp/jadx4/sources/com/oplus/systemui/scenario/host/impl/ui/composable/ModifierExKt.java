package com.oplus.systemui.scenario.host.impl.ui.composable;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import java.lang.invoke.VarHandle;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ModifierExKt {
    public static final <T> Modifier interceptInputDuringAnimation(Modifier modifier, Transition transition, String str, Composer composer, int i, int i2) {
        Object obj;
        if ((i2 & 2) != 0) {
            str = "Modifier";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-553009965, i, -1, "com.oplus.systemui.scenario.host.impl.ui.composable.interceptInputDuringAnimation (ModifierEx.kt:33)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(transition, composer, (i >> 3) & 14);
        Unit unit = Unit.INSTANCE;
        boolean zChanged = ((((i & 896) ^ 384) > 256 && composer.changed(str)) || (i & 384) == 256) | composer.changed(stateRememberUpdatedState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            obj = objRememberedValue;
            interceptInputDuringAnimation.1.1 r0 = new interceptInputDuringAnimation.1.1();
            r0.$label = str;
            r0.$currentTransition$delegate = stateRememberUpdatedState;
            VarHandle.storeStoreFence();
            composer.updateRememberedValue(r0);
            obj = r0;
        }
        obj = objRememberedValue;
        Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifier, unit, (PointerInputEventHandler) obj);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return modifierPointerInput;
    }
}
