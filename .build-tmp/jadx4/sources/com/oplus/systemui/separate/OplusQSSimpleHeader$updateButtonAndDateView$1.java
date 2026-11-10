package com.oplus.systemui.separate;

import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.systemui.statusbar.policy.Clock;
import com.oplus.systemui.qs.widget.OplusQSDateView;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSimpleHeader$updateButtonAndDateView$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSSimpleHeader this$0;

    public /* synthetic */ OplusQSSimpleHeader$updateButtonAndDateView$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OplusQSSimpleHeader oplusQSSimpleHeader = this.this$0;
                OplusQSSimpleHeader$updateButtonAndDateView$1 oplusQSSimpleHeader$updateButtonAndDateView$1 = new OplusQSSimpleHeader$updateButtonAndDateView$1(1);
                oplusQSSimpleHeader$updateButtonAndDateView$1.this$0 = oplusQSSimpleHeader;
                VarHandle.storeStoreFence();
                oplusQSSimpleHeader.post(oplusQSSimpleHeader$updateButtonAndDateView$1);
                break;
            default:
                OplusQSSimpleHeader oplusQSSimpleHeader2 = this.this$0;
                OplusQSSimpleHeader.Companion companion = OplusQSSimpleHeader.Companion;
                int iFixedDimen = oplusQSSimpleHeader2.fixedDimen(2131172639);
                int iFixedDimen2 = this.this$0.fixedDimen(2131172638);
                ImageView imageView = this.this$0.settingsButton;
                if (imageView != null) {
                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                    layoutParams.height = iFixedDimen;
                    layoutParams.width = iFixedDimen;
                    imageView.setLayoutParams(layoutParams);
                    imageView.setPadding(iFixedDimen2, iFixedDimen2, iFixedDimen2, iFixedDimen2);
                }
                int dimensionPixelSize = this.this$0.getResources().getDimensionPixelSize(2131171870);
                OplusQSDateView oplusQSDateView = this.this$0.dateView;
                if (oplusQSDateView != null) {
                    ViewGroup.LayoutParams layoutParams2 = oplusQSDateView.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
                    if (marginLayoutParams != null) {
                        marginLayoutParams.setMarginStart(dimensionPixelSize);
                        oplusQSDateView.setLayoutParams(marginLayoutParams);
                    }
                }
                int iFixedDimen3 = this.this$0.fixedDimen(2131171864);
                Clock clock = this.this$0.clockView;
                if (clock != null) {
                    ViewGroup.LayoutParams layoutParams3 = clock.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : null;
                    if (marginLayoutParams2 != null && marginLayoutParams2.topMargin != iFixedDimen3) {
                        marginLayoutParams2.topMargin = iFixedDimen3;
                        clock.setLayoutParams(marginLayoutParams2);
                        break;
                    }
                }
                break;
        }
    }
}
