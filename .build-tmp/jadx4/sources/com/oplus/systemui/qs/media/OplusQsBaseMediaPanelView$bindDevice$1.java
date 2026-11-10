package com.oplus.systemui.qs.media;

import android.os.Handler;
import android.view.View;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBaseMediaPanelView$bindDevice$1 implements View.OnClickListener {
    public /* synthetic */ MediaData $data;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQsBaseMediaPanelView this$0;

    public /* synthetic */ OplusQsBaseMediaPanelView$bindDevice$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                if (!QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
                    OplusQsBaseMediaPanelView.access$handleClickMediaOutputBtn(this.this$0, this.$data);
                    break;
                }
                break;
            default:
                OplusQsBaseMediaPanelView.access$avoidRefreshActionButton(this.this$0);
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView = this.this$0;
                oplusQsBaseMediaPanelView.expectPlayState = 3;
                oplusQsBaseMediaPanelView.handler.post(this.this$0.loadingRunnable);
                this.this$0.handler.postDelayed(this.this$0.stopLoadingRunnable, 20000L);
                Handler bgHandler = this.this$0.getBgHandler();
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView2 = this.this$0;
                MediaData mediaData = this.$data;
                OplusQsBaseMediaPanelView$handleDefaultClickListener$2$1 oplusQsBaseMediaPanelView$handleDefaultClickListener$2$1 = new OplusQsBaseMediaPanelView$handleDefaultClickListener$2$1(0);
                oplusQsBaseMediaPanelView$handleDefaultClickListener$2$1.this$0 = oplusQsBaseMediaPanelView2;
                oplusQsBaseMediaPanelView$handleDefaultClickListener$2$1.$selectMediaData = mediaData;
                VarHandle.storeStoreFence();
                bgHandler.post(oplusQsBaseMediaPanelView$handleDefaultClickListener$2$1);
                break;
        }
    }
}
