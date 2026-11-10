package com.oplus.systemui.shutdown;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.airbnb.lottie.LottieAnimationView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OpAssistNavigationDialog$$ExternalSyntheticLambda2 implements Runnable {
    public /* synthetic */ OpAssistNavigationDialog f$0;
    public /* synthetic */ LottieAnimationView f$1;
    public /* synthetic */ int f$2;

    @Override // java.lang.Runnable
    public final void run() {
        OpAssistNavigationDialog opAssistNavigationDialog = this.f$0;
        LottieAnimationView lottieAnimationView = this.f$1;
        int i = this.f$2;
        if (lottieAnimationView.getHeight() <= opAssistNavigationDialog.mCardBottom.getHeight()) {
            return;
        }
        lottieAnimationView.setOutlineProvider(new ViewOutlineProvider() { // from class: com.oplus.systemui.shutdown.OpAssistNavigationDialog.4
            public final /* synthetic */ int val$radius;

            public AnonymousClass4() {
                i = i;
            }

            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), OpAssistNavigationDialog.this.mCardBottom.getHeight(), i);
            }
        });
        lottieAnimationView.setClipToOutline(true);
    }
}
