package com.oplusos.systemui.common.outline;

import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.oplus.graphics.OplusPath;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OvalOutlineProvider extends CornerOutlineProvider {
    public static final OvalOutlineProvider INSTANCE = new OvalOutlineProvider();

    @Override // com.oplusos.systemui.common.outline.CornerOutlineProvider
    public float getCornerRadius(Drawable drawable) {
        return drawable.getBounds().height() / 2.0f;
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View view, Outline outline) {
        outline.setOval(0, 0, view.getWidth(), view.getHeight());
    }

    @Override // com.oplusos.systemui.common.outline.CornerOutlineProvider
    public void updatePath(Drawable drawable, Path path, OplusPath oplusPath, Object obj) {
        Rect bounds = drawable.getBounds();
        path.reset();
        path.addOval(bounds.left, bounds.top, bounds.right, bounds.bottom, Path.Direction.CW);
    }
}
