package com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper;

import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.ItemDragCallBack;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class RecyclerViewDragHelper$$ExternalSyntheticLambda3 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ItemDragCallBack f$0;
    public /* synthetic */ Object f$1;
    public /* synthetic */ RecyclerViewDragHelper f$2;
    public /* synthetic */ RecyclerView.ViewHolder f$3;

    public /* synthetic */ RecyclerViewDragHelper$$ExternalSyntheticLambda3(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                ItemDragCallBack itemDragCallBack = this.f$0;
                Object obj = this.f$1;
                return RecyclerViewDragHelper.m160$r8$lambda$6avmqme2alIqWlHx6afW63fhJs(this.f$3, itemDragCallBack, this.f$2, obj);
            default:
                RecyclerViewDragHelper recyclerViewDragHelper = this.f$2;
                Object obj2 = this.f$1;
                return RecyclerViewDragHelper.$r8$lambda$W7IPzKo1xn5mTsMOj2d6W87ivMQ(this.f$3, this.f$0, recyclerViewDragHelper, obj2);
        }
    }
}
