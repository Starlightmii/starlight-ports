package com.oplus.systemui.viewdialog.ui.binder;

import android.graphics.Insets;
import android.view.View;
import android.view.WindowInsets;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContainerBinder$windowInsetsListener$1 implements View.OnApplyWindowInsetsListener {
    public static final ViewDialogContainerBinder$windowInsetsListener$1 INSTANCE = new ViewDialogContainerBinder$windowInsetsListener$1();

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        Insets insets = windowInsets.getInsets(WindowInsets.Type.navigationBars());
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("windowInsetsListener: left=", paddingLeft, paddingTop, " top=", " right=");
        RectList$.ExternalSyntheticOutline0.m(paddingRight, paddingBottom, " bottom=", " insets=", sbM);
        sbM.append(insets);
        LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", sbM.toString(), null, 8);
        view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
        return WindowInsets.CONSUMED;
    }
}
