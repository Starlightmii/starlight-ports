package com.oplus.systemui.qs.detail.binder;

import android.view.View;
import android.view.ViewGroup;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailContainerBinder$collectLayoutParamsState$2 implements FlowCollector {
    public /* synthetic */ View $container;

    public final Object emit(Object obj, Continuation continuation) {
        boolean z;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) obj;
        ViewGroup.LayoutParams layoutParams = this.$container.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams2 != null) {
            View view = this.$container;
            StringBuilder sb = new StringBuilder("collectLayoutParamsState: ");
            boolean z2 = true;
            if (marginLayoutParams2.getMarginStart() != marginLayoutParams.getMarginStart()) {
                marginLayoutParams2.setMarginStart(marginLayoutParams.getMarginStart());
                z = true;
            } else {
                z = false;
            }
            sb.append("marginStart=" + marginLayoutParams2.getMarginStart() + " ");
            if (marginLayoutParams2.getMarginEnd() != marginLayoutParams.getMarginEnd()) {
                marginLayoutParams2.setMarginEnd(marginLayoutParams.getMarginEnd());
                z = true;
            }
            sb.append("marginEnd=" + marginLayoutParams2.getMarginEnd() + " ");
            int i = marginLayoutParams2.topMargin;
            int i2 = marginLayoutParams.topMargin;
            if (i != i2) {
                marginLayoutParams2.topMargin = i2;
                z = true;
            }
            sb.append("topMargin=" + marginLayoutParams2.topMargin + " ");
            int i3 = marginLayoutParams2.bottomMargin;
            int i4 = marginLayoutParams.bottomMargin;
            if (i3 != i4) {
                marginLayoutParams2.bottomMargin = i4;
                z = true;
            }
            sb.append("bottomMargin=" + marginLayoutParams2.bottomMargin + " ");
            int i5 = marginLayoutParams2.width;
            int i6 = marginLayoutParams.width;
            if (i5 != i6) {
                marginLayoutParams2.width = i6;
                z = true;
            }
            sb.append("width=" + marginLayoutParams2.width + " ");
            int i7 = marginLayoutParams2.height;
            int i8 = marginLayoutParams.height;
            if (i7 != i8) {
                marginLayoutParams2.height = i8;
            } else {
                z2 = z;
            }
            sb.append("height=" + marginLayoutParams2.height + " ");
            if (z2) {
                view.setLayoutParams(marginLayoutParams2);
            }
            sb.append("change=" + z2 + " ");
            LogUtil.i$default(LogUtil.TAG_QS, "QSDetailContainerBinder", sb.toString(), null, 8);
        } else {
            LogUtil.i$default(LogUtil.TAG_QS, "QSDetailContainerBinder", "collectLayoutParamsState: layoutParams not MarginLayoutParams", null, 8);
        }
        return Unit.INSTANCE;
    }
}
