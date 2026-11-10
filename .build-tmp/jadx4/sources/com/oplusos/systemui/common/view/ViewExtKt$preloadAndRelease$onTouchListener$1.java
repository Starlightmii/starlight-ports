package com.oplusos.systemui.common.view;

import android.view.MotionEvent;
import android.view.View;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewExtKt$preloadAndRelease$onTouchListener$1 implements View.OnTouchListener {
    public /* synthetic */ Function0 $preloadAction;
    public /* synthetic */ Function0 $preloadCancelAction;
    public /* synthetic */ View $this_preloadAndRelease;

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            ViewExtKt.setPerformClickedFlag(this.$this_preloadAndRelease, false);
            Object tag = this.$this_preloadAndRelease.getTag(2131362814);
            this.$this_preloadAndRelease.removeCallbacks(tag instanceof Runnable ? (Runnable) tag : null);
            this.$preloadCancelAction.invoke();
            this.$preloadAction.invoke();
        } else if (actionMasked == 1 || actionMasked == 3) {
            View view2 = this.$this_preloadAndRelease;
            Function0 function0 = this.$preloadCancelAction;
            ViewExtKt$preloadAndRelease$onTouchListener$1$delayTask$1 viewExtKt$preloadAndRelease$onTouchListener$1$delayTask$1 = new ViewExtKt$preloadAndRelease$onTouchListener$1$delayTask$1();
            viewExtKt$preloadAndRelease$onTouchListener$1$delayTask$1.$this_preloadAndRelease = view2;
            viewExtKt$preloadAndRelease$onTouchListener$1$delayTask$1.$preloadCancelAction = function0;
            VarHandle.storeStoreFence();
            view2.setTag(2131362814, viewExtKt$preloadAndRelease$onTouchListener$1$delayTask$1);
            this.$this_preloadAndRelease.postDelayed(viewExtKt$preloadAndRelease$onTouchListener$1$delayTask$1, 1000L);
        }
        return false;
    }
}
