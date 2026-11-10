package com.oplus.systemui.keyguard.wallpaper.pictorial.tips;

import android.content.Context;
import android.widget.ImageView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageViewInfo;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PictorialEntryView extends PictorialTipsView {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public PictorialEntryView(Context context) {
        super(context);
        KgdLogUtil.i("TipsView-PictorialEntryView", "init");
        PictorialEntryView$$ExternalSyntheticLambda0 pictorialEntryView$$ExternalSyntheticLambda0 = new PictorialEntryView$$ExternalSyntheticLambda0();
        pictorialEntryView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        setOnClickListener(pictorialEntryView$$ExternalSyntheticLambda0);
        setContentDescription(((ImageView) this).mContext.getResources().getString(2131954128));
        setClickable(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateTipsState() {
        PictorialEntryView$$ExternalSyntheticLambda1 pictorialEntryView$$ExternalSyntheticLambda1 = new PictorialEntryView$$ExternalSyntheticLambda1();
        pictorialEntryView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        KgdLogUtil.d("TipsView-PictorialEntryView", "tips_need_show", pictorialEntryView$$ExternalSyntheticLambda1);
        PictorialMessageViewInfo pictorialMessageViewInfo = this.mViewInfo;
        if (pictorialMessageViewInfo == null || !pictorialMessageViewInfo.mEntryShow || !this.mAllowState || !this.mCreate) {
            if (this.mShow) {
                setVisibility(8);
                this.mShow = false;
                return;
            }
            return;
        }
        if (this.mShow) {
            return;
        }
        setVisibility(0);
        setAlpha(1.0f);
        invalidate();
        this.mShow = true;
    }
}
