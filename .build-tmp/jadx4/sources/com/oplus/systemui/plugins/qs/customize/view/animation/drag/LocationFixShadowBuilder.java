package com.oplus.systemui.plugins.qs.customize.view.animation.drag;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.HardwareRenderer;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RenderNode;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class LocationFixShadowBuilder extends View.DragShadowBuilder {
    private Bitmap bitmap;
    public final int initialShadowPointX;
    public final int initialShadowPointY;

    public LocationFixShadowBuilder(int i, int i2, View view, boolean z) {
        super(view);
        this.initialShadowPointX = i;
        this.initialShadowPointY = i2;
        if (z) {
            RenderNode renderNode = new RenderNode("Bitmap CloneContent");
            renderNode.setPosition(0, 0, view.getWidth(), view.getHeight());
            view.draw(renderNode.beginRecording());
            renderNode.endRecording();
            this.bitmap = HardwareRenderer.createHardwareBitmap(renderNode, view.getWidth(), view.getHeight());
        }
    }

    @Override // android.view.View.DragShadowBuilder
    public void onDrawShadow(Canvas canvas) {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
    }

    @Override // android.view.View.DragShadowBuilder
    public void onProvideShadowMetrics(Point point, Point point2) {
        point.set(getView().getWidth(), getView().getHeight());
        point2.set(this.initialShadowPointX, this.initialShadowPointY);
    }
}
