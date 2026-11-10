package com.oplus.systemui.notification.lockscreen.capsule.stroke;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.oplus.systemui.notification.lockscreen.effect.AutoRoundRectPathProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StrokeView extends View {
    public float desiredDrawHeight;
    public float desiredDrawWidth;
    private final List<StrokeLayer> layers;
    private AutoRoundRectPathProvider pathProvider;

    public /* synthetic */ StrokeView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final StrokeLayer addLayer(Function1 function1) {
        StrokeLayer strokeLayer = new StrokeLayer(function1);
        this.layers.add(strokeLayer);
        return strokeLayer;
    }

    public final void clearAllLayer() {
        Iterator<T> it = this.layers.iterator();
        while (it.hasNext()) {
            ((StrokeLayer) it.next()).getPaint().setShader(null);
        }
        this.layers.clear();
    }

    public final List<StrokeLayer> getAllLayers() {
        return CollectionsKt___CollectionsKt.toList(this.layers);
    }

    public final void initPathProvider(AutoRoundRectPathProvider autoRoundRectPathProvider) {
        this.pathProvider = autoRoundRectPathProvider;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Rect clipRect;
        super.onDraw(canvas);
        float f = this.desiredDrawWidth;
        float f2 = this.desiredDrawHeight;
        float width = (getWidth() - f) / 2.0f;
        AutoRoundRectPathProvider autoRoundRectPathProvider = this.pathProvider;
        float f3 = (autoRoundRectPathProvider == null || (clipRect = autoRoundRectPathProvider.getClipRect()) == null) ? 0.0f : clipRect.top;
        if (f <= 0.0f || f2 <= 0.0f) {
            return;
        }
        for (StrokeLayer strokeLayer : this.layers) {
            int iSave = canvas.save();
            try {
                canvas.translate(width, f3);
                strokeLayer.draw(canvas, f, f2);
                canvas.restoreToCount(iSave);
            } catch (Throwable th) {
                canvas.restoreToCount(iSave);
                throw th;
            }
        }
    }

    public final void updateViewSize(int i, int i2) {
        if (i < 0 || i2 < 0) {
            return;
        }
        float f = i;
        this.desiredDrawWidth = f;
        float f2 = i2;
        this.desiredDrawHeight = f2;
        if (f <= 0.0f || f2 <= 0.0f) {
            return;
        }
        for (StrokeLayer strokeLayer : this.layers) {
            strokeLayer.getClass();
            if (f > 0.0f && f2 > 0.0f) {
                strokeLayer.setResolution(f, f2);
                strokeLayer.setSize(f, f2);
                strokeLayer.setOffset(0.0f, 0.0f);
            }
            strokeLayer.setCornerRadius(f2 / 2.0f);
        }
    }

    public StrokeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public StrokeView(Context context) {
        this(context, null, 0, 6, null);
    }

    public StrokeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.layers = new ArrayList();
    }
}
