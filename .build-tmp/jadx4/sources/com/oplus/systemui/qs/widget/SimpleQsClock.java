package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.policy.Clock;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt__RangesKt;
import kotlin.text.StringsKt__IndentKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SimpleQsClock extends OplusQSClock {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SimpleQsClock";
    public final float clockScaleFactor;
    public final float systemFontScale;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SimpleQsClock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.clockScaleFactor = context.getResources().getFloat(2131171153);
        this.systemFontScale = Resources.getSystem().getConfiguration().fontScale;
    }

    public static int getAbsoluteX(View view) {
        if (!(view.getParent() instanceof View)) {
            return view.getLeft();
        }
        return view.getLeft() + getAbsoluteX((View) view.getParent());
    }

    public final int computeSecondsFixedLayoutWidth() {
        float f = (this.clockScaleFactor + this.systemFontScale) - 1.0f;
        float fComputeSecondsTextMaxWidth = computeSecondsTextMaxWidth();
        int i = this.mPaddingEnd;
        int i2 = ((int) fComputeSecondsTextMaxWidth) + ((int) (f * fComputeSecondsTextMaxWidth)) + i;
        float f2 = this.systemFontScale;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("seconds scaledWidth:", i2, i, ", mPaddingEnd ", ", systemFontScale ");
        sbM.append(f2);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super/*android.widget.TextView*/.onSizeChanged(i, i2, i3, i4);
        setPivotX(DeviceUtil.isRtl() ? getWidth() : 0.0f);
        setPivotY(getBaseline());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", NotificationPanelViewController$.ExternalSyntheticOutline0.m(getWidth(), getHeight(), ",height: ", "SimpleQsClock-->", MonoSpline$.ExternalSyntheticOutline0.m("onSizeChanged updateClockPivot, pivotX: ", getPivotX(), ", pivotY: ", getPivotY(), ",width: ")));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Pair pair;
        if (!isClickable()) {
            return super/*android.widget.TextView*/.onTouchEvent(motionEvent);
        }
        int absoluteX = getAbsoluteX(this);
        if (getLayoutDirection() == 1) {
            pair = new Pair(Integer.valueOf(absoluteX), Integer.valueOf(getWidth() + absoluteX));
        } else {
            int paddingStart = getPaddingStart() + absoluteX;
            pair = new Pair(Integer.valueOf(paddingStart), Integer.valueOf(getWidth() + paddingStart));
        }
        int iIntValue = ((Number) pair.component1()).intValue();
        int iIntValue2 = ((Number) pair.component2()).intValue();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String str = getLayoutDirection() == 1 ? "RTL" : "LTR";
            int absoluteX2 = getAbsoluteX(this);
            int width = getWidth();
            int paddingStart2 = getPaddingStart();
            int paddingEnd = getPaddingEnd();
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(absoluteX2, "\n            Optimized touch event:\n            - Layout direction: ", str, "\n            - View location: ", "\n            - View width: ");
            RectList$.ExternalSyntheticOutline0.m(width, paddingStart2, "\n            - Padding: start=", ", end=", sbM);
            RectList$.ExternalSyntheticOutline0.m(paddingEnd, iIntValue, "\n            - Click bounds: [", ", ", sbM);
            IconProvider$.ExternalSyntheticOutline0.m(rawX, iIntValue2, "]\n            - Raw coordinates: (", ", ", sbM);
            sbM.append(rawY);
            sbM.append(")\n        ");
            String strTrimIndent = StringsKt__IndentKt.trimIndent(sbM.toString());
            if (strTrimIndent == null) {
                strTrimIndent = "";
            }
            Log.d("SystemUi--Notification", "SimpleQsClock-->".concat(strTrimIndent));
        }
        return RangesKt__RangesKt.rangeTo((float) iIntValue, (float) iIntValue2).contains(Float.valueOf(motionEvent.getRawX())) && super/*android.widget.TextView*/.onTouchEvent(motionEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.widget.OplusQSClock
    public final void updateMinWidth() {
        if (((Clock) this).mShowSeconds) {
            return;
        }
        float fCalculateMinutesTextWidth = calculateMinutesTextWidth();
        int i = (int) (fCalculateMinutesTextWidth + ((int) (((this.clockScaleFactor + this.systemFontScale) - 1.0f) * fCalculateMinutesTextWidth)) + this.mPaddingEnd);
        if (getMinimumWidth() != i) {
            setMinimumWidth(i);
        }
    }

    public SimpleQsClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ SimpleQsClock(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public SimpleQsClock(Context context) {
        this(context, null, 0, 6, null);
    }
}
