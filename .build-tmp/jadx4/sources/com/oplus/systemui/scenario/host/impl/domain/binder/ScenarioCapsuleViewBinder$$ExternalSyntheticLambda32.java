package com.oplus.systemui.scenario.host.impl.domain.binder;

import android.content.res.ColorStateList;
import android.widget.TextSwitcher;
import androidx.compose.runtime.State;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.view.TextSwitcherExtKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda32 implements Function1 {
    public /* synthetic */ String f$0;
    public /* synthetic */ boolean f$1;
    public /* synthetic */ ColorStateList f$2;
    public /* synthetic */ State f$3;

    public final Object invoke(Object obj) {
        String str = this.f$0;
        boolean z = this.f$1;
        ColorStateList colorStateList = this.f$2;
        State state = this.f$3;
        TextSwitcher textSwitcher = (TextSwitcher) obj;
        boolean zBooleanValue = ((Boolean) state.getValue()).booleanValue();
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("CapsuleText: text=", str, " enableAnima=", z, " marqueeEnabled=");
        sbM.append(zBooleanValue);
        LogUtil.i$default(LogUtil.TAG_QS, "ScenarioCapsuleViewBinder", sbM.toString(), null, 8);
        textSwitcher.setSelected(((Boolean) state.getValue()).booleanValue());
        TextSwitcherExtKt.setTextColor(textSwitcher, colorStateList);
        if (z) {
            TextSwitcherExtKt.setContent(textSwitcher, str);
        } else {
            textSwitcher.setCurrentText(str);
        }
        return Unit.INSTANCE;
    }
}
