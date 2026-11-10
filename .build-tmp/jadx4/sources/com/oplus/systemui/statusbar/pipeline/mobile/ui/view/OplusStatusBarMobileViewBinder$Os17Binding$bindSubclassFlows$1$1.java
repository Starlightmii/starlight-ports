package com.oplus.systemui.statusbar.pipeline.mobile.ui.view;

import android.graphics.Typeface;
import android.util.Log;
import com.android.systemui.statusbar.pipeline.mobile.data.OplusNetworkTypeText;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.OplusNetworkTypeTextModel;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarMobileViewBinder$Os17Binding$bindSubclassFlows$1$1 implements FlowCollector {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusStatusBarMobileViewBinder.Os17Binding this$0;

    public final Object emit(Object obj, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.mobileRoamingText.setTypeface((Typeface) ((Pair) obj).component1());
                break;
            case 1:
                Triple triple = (Triple) obj;
                OplusStatusBarMobileViewBinder.Os17Binding os17Binding = this.this$0;
                OplusNetworkTypeTextModel oplusNetworkTypeTextModel = (OplusNetworkTypeTextModel) triple.component1();
                Typeface typeface = (Typeface) triple.component2();
                int iIntValue = ((Number) triple.component3()).intValue();
                OplusNetworkTypeText networkTypeText = oplusNetworkTypeTextModel != null ? oplusNetworkTypeTextModel.getNetworkTypeText() : null;
                String name = networkTypeText != null ? networkTypeText.getName() : null;
                if (!Intrinsics.areEqual(os17Binding.lastNetworkTypeTextName, name) || (name == null && os17Binding.getMobileTypeText().getVisibility() == 0)) {
                    os17Binding.lastNetworkTypeTextName = name;
                    os17Binding.bindNetworkTypeText(oplusNetworkTypeTextModel);
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusStatusBarMobileViewBinder-->".concat("bindSubclassFlows: typeText=" + (networkTypeText != null ? networkTypeText.getName() : null) + ", isLarge=" + (networkTypeText != null ? Boxing.boxBoolean(networkTypeText.isLarge()) : null) + ", typeface=" + typeface + ", weight=" + iIntValue));
                }
                if (networkTypeText != null) {
                    OplusStatusBarMobileViewBinder.Os17Binding.access$setMobileTypeTextTypeface(os17Binding, typeface, iIntValue, networkTypeText.isLarge());
                }
                break;
            default:
                this.this$0.bindRoamingText(((Boolean) obj).booleanValue());
                break;
        }
        return Unit.INSTANCE;
    }
}
