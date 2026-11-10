package com.oplusos.systemui.common.rendercache;

import android.util.Log;
import android.view.View;
import android.view.ViewRootImpl;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RenderBooster$requestBooster$$inlined$doOnAttach$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ boolean $onlyScanVisible$inlined;
    public /* synthetic */ View $this_doOnAttach;
    public /* synthetic */ int $type$inlined;
    public /* synthetic */ RenderBooster this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
        ViewRootImpl viewRootImpl = this.this$0.getTargetView().getViewRootImpl();
        if (viewRootImpl == null) {
            return;
        }
        int invalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon = this.this$0.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon();
        RenderBooster renderBooster = this.this$0;
        renderBooster.invalidateType = renderBooster.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() | this.$type$inlined;
        if (this.this$0.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() != 0) {
            if (invalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon != 0) {
                RenderBooster.Companion.getClass();
                if (RenderBooster.Companion.canResueCache(invalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon) == RenderBooster.Companion.canResueCache(this.this$0.getInvalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon())) {
                    return;
                }
            }
            RenderBooster renderBooster2 = this.this$0;
            renderBooster2.onlyScanVisible = this.$onlyScanVisible$inlined;
            renderBooster2.markAncestorsDirty(true);
            RenderBooster.Companion companion = RenderBooster.Companion;
            RenderBooster.Companion.access$applyRenderBoosterOnPreDraw(companion, viewRootImpl);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "RenderBooster-->".concat("[requestBooster] request rebuild boost network from " + this.this$0));
            }
            if (invalidateType$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon == 0) {
                RenderBooster.Companion.access$addBoosterTarget(companion, viewRootImpl, this.this$0);
            }
            this.this$0.setDirty$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(true);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
