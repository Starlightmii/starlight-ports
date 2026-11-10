package com.oplus.systemui.qs.base.widget.recyclerview;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PlaceHolder$onAttachStateChangeListener$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ PlaceHolder this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        RecyclerView hostRecyclerView;
        ViewTreeObserver viewTreeObserver;
        EmptyPlaceHolderHandler emptyPlaceHolderHandler$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = this.this$0.getEmptyPlaceHolderHandler$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib();
        if (emptyPlaceHolderHandler$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib == null || (hostRecyclerView = emptyPlaceHolderHandler$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.getHostRecyclerView()) == null || (viewTreeObserver = hostRecyclerView.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.removeOnPreDrawListener(this.this$0.updateLastPlaceHolderBoundsToCell);
    }
}
