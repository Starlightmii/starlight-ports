package com.oplus.systemui.plugins.qs.customize.view.animation.drag.util;

import android.content.ClipData;
import android.view.View;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.LocationFixShadowBuilder;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.data.InputDragData;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DragUtil {
    public static final DragUtil INSTANCE = new DragUtil();
    private static final String TAG = "DragUtil";

    public static final <DATA> boolean startDrag(View view, ClipData clipData, DATA data, int i, int i2) {
        if (i < 0 || i2 < 0) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i2, "startDrag init error fixInitialTouchX=", " fixInitialTouchY="), null, 8);
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        return view.startDragAndDrop(clipData, new LocationFixShadowBuilder(i, i2, view, false), new InputDragData(data, i, i2), OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION);
    }

    public static final void updateShadowBuilder(View view, boolean z, int i, int i2) {
        view.updateDragShadow(new LocationFixShadowBuilder(i, i2, view, z));
    }
}
