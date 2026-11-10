package com.oplus.systemui.qs.fake.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.collection.FloatList$;
import com.android.systemui.statusbar.policy.Clock;
import com.oplus.systemui.qs.helper.OplusQSFontHelper;
import com.oplus.systemui.qs.widget.OplusQSClock;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsClock extends OplusQSClock {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QsClock";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ QsClock(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final float getFontScale() {
        return (OplusQSFontHelper.Companion.getInstance(((TextView) this).mContext).getAdapterTextSizeSp(2) / 13.0f) - 1.0f;
    }

    public final int computeSecondsFixedLayoutWidth() {
        float f = Resources.getSystem().getConfiguration().fontScale;
        float fontScale = (getFontScale() + f) - 1.0f;
        float fComputeSecondsTextMaxWidth = computeSecondsTextMaxWidth();
        int i = this.mPaddingEnd;
        int i2 = ((int) fComputeSecondsTextMaxWidth) + ((int) (fontScale * fComputeSecondsTextMaxWidth)) + i;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("seconds scaledWidth:", i2, i, ", mPaddingEnd ", ", systemFontScale ");
        sbM.append(f);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sbM.toString(), null, 8);
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.widget.OplusQSClock
    public final void updateMinWidth() {
        if (((Clock) this).mShowSeconds) {
            return;
        }
        float fCalculateMinutesTextWidth = calculateMinutesTextWidth();
        int fontScale = (int) (fCalculateMinutesTextWidth + ((int) (((getFontScale() + Resources.getSystem().getConfiguration().fontScale) - 1.0f) * fCalculateMinutesTextWidth)) + this.mPaddingEnd);
        if (getMinimumWidth() != fontScale) {
            setMinimumWidth(fontScale);
        }
    }

    public QsClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public QsClock(Context context) {
        this(context, null, 0, 6, null);
    }

    public QsClock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
