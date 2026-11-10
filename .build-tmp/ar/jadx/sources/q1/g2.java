package q1;

import android.view.RenderNode;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g2 f5213a = new g2();

    public final int a(RenderNode renderNode) {
        return renderNode.getAmbientShadowColor();
    }

    public final int b(RenderNode renderNode) {
        return renderNode.getSpotShadowColor();
    }

    public final void c(RenderNode renderNode, int i5) {
        renderNode.setAmbientShadowColor(i5);
    }

    public final void d(RenderNode renderNode, int i5) {
        renderNode.setSpotShadowColor(i5);
    }
}
