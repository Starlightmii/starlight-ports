package com.oplus.systemui.toast;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusToastLayout$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ OplusToastLayout f$0;
    public /* synthetic */ OplusToastLayout.LineStyle f$1;
    public /* synthetic */ View f$2;

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        int i3;
        OplusToastLayout oplusToastLayout = this.f$0;
        OplusToastLayout.LineStyle lineStyle = this.f$1;
        View view = this.f$2;
        int i4 = OplusToastLayout.$r8$clinit;
        OplusToastLayout.LineStyle lineStyle2 = OplusToastLayout.LineStyle.ONE;
        OplusToastLayout.LineStyle lineStyle3 = OplusToastLayout.LineStyle.MULTI;
        if (lineStyle == lineStyle3 || lineStyle == lineStyle2) {
            if (oplusToastLayout.mIsSubDisPlayShow) {
                view.setBackgroundResource(2131237507);
            } else {
                view.setBackgroundResource(2131237503);
            }
        } else if (oplusToastLayout.mIsSubDisPlayShow) {
            view.setBackgroundResource(2131237506);
        } else {
            view.setBackgroundResource(2131237502);
        }
        boolean z = oplusToastLayout.mIconView.getVisibility() == 0;
        if (oplusToastLayout.mIsSubDisPlayShow) {
            i = (lineStyle == lineStyle3 || lineStyle == lineStyle2) ? oplusToastLayout.mSubSingleMarginTopVertical : oplusToastLayout.mSubMarginTopVertical;
            i2 = z ? oplusToastLayout.mSubPddingWidth : oplusToastLayout.mSubPddingWidthNoIcon;
            i3 = i2;
        } else {
            i = (lineStyle == lineStyle3 || lineStyle == lineStyle2) ? oplusToastLayout.mToastMutlContentPaddingTop : oplusToastLayout.mToastDoubleContentPaddingTop;
            i2 = z ? oplusToastLayout.mToastContentPaddingLeft : oplusToastLayout.mToastContentPaddingRight;
            i3 = oplusToastLayout.mToastContentPaddingRight;
        }
        view.setPaddingRelative(i2, i, i3, i);
        int i5 = oplusToastLayout.mShadowSize;
        int color = oplusToastLayout.getResources().getColor(2131101244);
        view.getResources().getDimensionPixelOffset(2131173615);
        view.setOutlineSpotShadowColor(color);
        view.setElevation(i5);
        oplusToastLayout.setVisibility(0);
    }
}
