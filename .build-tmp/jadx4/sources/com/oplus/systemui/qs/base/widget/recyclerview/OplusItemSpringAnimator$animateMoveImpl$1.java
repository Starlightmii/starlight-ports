package com.oplus.systemui.qs.base.widget.recyclerview;

import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusItemSpringAnimator$animateMoveImpl$1 extends OplusItemSpringAnimationHolder$OplusSpringAnimationListenerWrapper {
    public /* synthetic */ int $deltaX;
    public /* synthetic */ int $deltaY;
    public /* synthetic */ RecyclerView.ViewHolder $holder;
    public /* synthetic */ boolean $moveInScreen;
    public /* synthetic */ boolean $moveOutScreen;
    public /* synthetic */ OplusItemSpringAnimationHolder $springAnimationHolder;
    public /* synthetic */ View $view;
    public /* synthetic */ OplusItemSpringAnimator this$0;

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimationHolder$OplusSpringAnimationListenerWrapper
    public final void onAnimationEnd(boolean z) {
        OplusItemSpringAnimator oplusItemSpringAnimator = this.this$0;
        RecyclerView.ViewHolder viewHolder = this.$holder;
        if (this.$moveOutScreen || z) {
            this.$view.setAlpha(1.0f);
            this.$view.setScaleX(1.0f);
            this.$view.setScaleY(1.0f);
            if (this.$deltaX != 0) {
                this.$view.setTranslationX(0.0f);
            }
            if (this.$deltaY != 0) {
                this.$view.setTranslationY(0.0f);
            }
        }
        if (this.$moveInScreen) {
            this.$view.setAlpha(1.0f);
            this.$view.setScaleX(1.0f);
            this.$view.setScaleY(1.0f);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->".concat("animateMoveImpl: onAnimationEnd ," + viewHolder + " ,isCancel: " + z));
        }
        this.$springAnimationHolder.removeListener();
        OplusItemSpringAnimationHolder.Companion.clearSpringAnimationHolder(this.$view);
        oplusItemSpringAnimator.dispatchAnimationFinished(viewHolder);
        oplusItemSpringAnimator.moveAnimations.remove(viewHolder);
        oplusItemSpringAnimator.dispatchFinishedWhenDone();
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimationHolder$OplusSpringAnimationListenerWrapper
    public final void onAnimationStart() {
        RecyclerView.ViewHolder viewHolder = this.$holder;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusItemSpringAnimator-->".concat("animateMoveImpl: onAnimationStart ," + viewHolder));
        }
        if (this.$moveInScreen) {
            if (this.$deltaX != 0) {
                this.$view.setTranslationX(0.0f);
            }
            if (this.$deltaY != 0) {
                this.$view.setTranslationY(0.0f);
            }
            this.$view.setAlpha(0.0f);
            this.$view.setScaleX(0.0f);
            this.$view.setScaleY(0.0f);
        }
    }
}
