package com.oplus.systemui.qs;

import android.content.res.Configuration;
import android.content.res.Resources;
import com.android.systemui.util.ViewController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSFooterViewController$$ExternalSyntheticLambda5 implements ViewController.OnConfigurationChangedListener {
    public /* synthetic */ OplusQSFooterViewController f$0;

    public final void onConfigurationChange(Configuration configuration) {
        OplusQSFooterViewController oplusQSFooterViewController = this.f$0;
        Resources resources = ((OplusQSFooterImpl) ((ViewController) oplusQSFooterViewController).mView).getResources();
        oplusQSFooterViewController.mCollapseHeight = resources.getDimensionPixelSize(2131171866);
        oplusQSFooterViewController.mExpandHeight = resources.getDimensionPixelSize(2131171875);
        oplusQSFooterViewController.mGapTop = resources.getDimensionPixelSize(2131171876);
    }
}
