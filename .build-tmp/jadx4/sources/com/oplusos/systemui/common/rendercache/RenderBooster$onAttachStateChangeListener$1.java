package com.oplusos.systemui.common.rendercache;

import android.util.Log;
import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RenderBooster$onAttachStateChangeListener$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ RenderBooster this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        RenderBooster renderBooster = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "RenderBooster-->".concat("[onViewAttachedToWindow], autoRecoverWhenAttached for " + renderBooster));
        }
        RenderBooster renderBooster2 = this.this$0;
        RenderBooster.requestBooster$default(renderBooster2, renderBooster2.backupInvalidateType, false, 6);
        this.this$0.backupInvalidateType = 0;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        RenderBooster renderBooster = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "RenderBooster-->".concat("[onViewDetachedFromWindow], requestStop for " + renderBooster));
        }
        RenderBooster renderBooster2 = this.this$0;
        renderBooster2.backupInvalidateType = renderBooster2.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon();
        this.this$0.requestStop(0);
    }
}
