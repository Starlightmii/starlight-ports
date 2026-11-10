package com.oplus.systemui.qs.base.spotlight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SpotLightFrameLayout extends FrameLayout implements IQSSpotLightConfig {
    public /* synthetic */ SpotLightFrameLayout(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        SharedSpotLightEffect.draw(this, canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        SharedSpotLightEffect.onTouchEvent(this, motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getSpotLightType() {
        return 1;
    }

    public void updateSpotLightPath(Path path, Object obj) {
        float fMin = Math.min(getWidth(), getHeight()) / 2.0f;
        path.addRoundRect(0.0f, 0.0f, getWidth(), getHeight(), fMin, fMin, Path.Direction.CW);
    }

    public SpotLightFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public SpotLightFrameLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public SpotLightFrameLayout(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    public SpotLightFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
