package com.oplus.systemui.qs.base.spotlight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.oplus.graphics.OplusPathAdapter;
import com.oplus.posteffect.util.OplusPathAdapterCompatUtils;
import com.oplusos.systemui.common.util.OplusRoundParams;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SpotLightLinearLayout extends LinearLayout implements IQSSpotLightConfig {
    public static final Companion Companion = new Companion();
    private static final String KEY_TOUCH_ACTION = "motion_action";
    private static final String KEY_TOUCH_X = "motion_x";
    private static final String KEY_TOUCH_Y = "motion_y";
    public long bundleTouchDownTime;
    private Path lastPath;
    private Object oplusPathAdapter;
    private final RectF tmpRoundRectF;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ SpotLightLinearLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        SharedSpotLightEffect.draw(this, canvas);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x004d  */
    public final void dispatchSpotLightBundle(Bundle bundle) {
        MotionEvent motionEventObtain;
        long j;
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey("motion_action") && bundle.containsKey(KEY_TOUCH_X) && bundle.containsKey(KEY_TOUCH_Y)) {
            int i = bundle.getInt("motion_action", 3);
            float f = bundle.getFloat(KEY_TOUCH_X);
            float f2 = bundle.getFloat(KEY_TOUCH_Y);
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (i != 0) {
                if (i == 1 || i == 2 || i == 3) {
                    if (this.bundleTouchDownTime == 0) {
                        this.bundleTouchDownTime = jUptimeMillis;
                    }
                    j = this.bundleTouchDownTime;
                }
                if (i != 1 || i == 3) {
                    this.bundleTouchDownTime = 0L;
                }
                motionEventObtain = MotionEvent.obtain(j, jUptimeMillis, i, f, f2, 0);
            } else {
                this.bundleTouchDownTime = jUptimeMillis;
            }
            j = jUptimeMillis;
            if (i != 1) {
                this.bundleTouchDownTime = 0L;
            } else {
                this.bundleTouchDownTime = 0L;
            }
            motionEventObtain = MotionEvent.obtain(j, jUptimeMillis, i, f, f2, 0);
        } else {
            motionEventObtain = null;
        }
        if (motionEventObtain == null) {
            return;
        }
        try {
            SharedSpotLightEffect.onTouchEvent(this, motionEventObtain);
        } finally {
            motionEventObtain.recycle();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        SharedSpotLightEffect.onTouchEvent(this, motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public OplusRoundParams getSpotLightRoundParams() {
        return null;
    }

    public int getSpotLightType() {
        return 3;
    }

    public boolean isViewSupportSpotLight() {
        return false;
    }

    public void updateSpotLightPath(Path path, Object obj) {
        OplusRoundParams spotLightRoundParams = getSpotLightRoundParams();
        if (spotLightRoundParams != null) {
            if (obj != null) {
                this.oplusPathAdapter = obj;
            } else if (this.oplusPathAdapter == null || !Intrinsics.areEqual(this.lastPath, path)) {
                this.lastPath = path;
                this.oplusPathAdapter = new OplusPathAdapter(path, 1);
            }
            float[] fArr = new float[8];
            ArraysKt___ArraysJvmKt.fill(fArr, spotLightRoundParams.getRadius(), 0, 8);
            this.tmpRoundRectF.set(0.0f, 0.0f, getWidth(), getHeight());
            OplusPathAdapterCompatUtils.INSTANCE.addSmoothRoundRect(this.oplusPathAdapter, this.tmpRoundRectF, fArr, Path.Direction.CW, spotLightRoundParams.getWeight());
        }
    }

    public SpotLightLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 12, 0);
    }

    public SpotLightLinearLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 8, 0);
    }

    public SpotLightLinearLayout(Context context) {
        this(context, null, 0, 14, 0);
    }

    public SpotLightLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.tmpRoundRectF = new RectF();
    }
}
