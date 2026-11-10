package com.oplus.systemui.qs.base.spotlight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatImageButton;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpotLightImageButton extends AppCompatImageButton implements IQSSpotLightConfig {
    public /* synthetic */ SpotLightImageButton(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) {
        super/*android.widget.ImageButton*/.dispatchDraw(canvas);
        SharedSpotLightEffect.draw(this, canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        SharedSpotLightEffect.onTouchEvent(this, motionEvent);
        return super/*android.widget.ImageButton*/.dispatchTouchEvent(motionEvent);
    }

    public int getSpotLightType() {
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateSpotLightPath(Path path, Object obj) {
        float fMin = Math.min(getWidth(), getHeight()) / 2.0f;
        path.addRoundRect(0.0f, 0.0f, getWidth(), getHeight(), fMin, fMin, Path.Direction.CW);
    }

    public SpotLightImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public SpotLightImageButton(Context context) {
        this(context, null, 0, 6, null);
    }

    public SpotLightImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
