package com.oplusos.systemui.common.inline;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import com.oplus.sysuiinlineview.host.view.SysUiInlineHostView;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsInlineHostView extends SysUiInlineHostView {
    public int expectedHeight;
    private OnAnalyzeSysInlineRequestListener onIntentParsedListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OnAnalyzeSysInlineRequestListener {
        void onAnalyzeSysInlineRequest(Bundle bundle);
    }

    public /* synthetic */ OplusQsInlineHostView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    @Override // com.oplus.sysuiinlineview.host.view.SysUiInlineHostView
    public void onInlineViewRequest(Bundle bundle) {
        OnAnalyzeSysInlineRequestListener onAnalyzeSysInlineRequestListener = this.onIntentParsedListener;
        if (onAnalyzeSysInlineRequestListener != null) {
            onAnalyzeSysInlineRequestListener.onAnalyzeSysInlineRequest(bundle);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        int size = View.MeasureSpec.getSize(i2);
        if (size > 0 && (i3 = this.expectedHeight) > 0) {
            if (i3 > size) {
                i3 = size;
            }
            i2 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        String tag = getTAG();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            StringBuilder sbM2 = ListPopupWindow$.ExternalSyntheticOutline0.m(size, "onMeasure: atMostHeight = ", ", measureHeight = ");
            sbM2.append(getMeasuredHeight());
            sbM2.append(", expectedHeight = ");
            sbM2.append(this.expectedHeight);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
    }

    public final void setExpectedHeight(int i) {
        this.expectedHeight = i;
    }

    public final void setSysInlineRequestListener(OnAnalyzeSysInlineRequestListener onAnalyzeSysInlineRequestListener) {
        this.onIntentParsedListener = onAnalyzeSysInlineRequestListener;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OplusQsInlineHostView(Context context) {
        AttributeSet attributeSet = null;
        this(context, attributeSet, 2, attributeSet);
    }

    public OplusQsInlineHostView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.expectedHeight = -1;
    }
}
