package com.oplus.systemui.notification.lockscreen.seedling;

import android.view.View;
import android.widget.ImageView;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleSeedingData$Companion$setupSeedlingClickListener$2 implements View.OnClickListener {
    public /* synthetic */ ImageView $btnTest;
    public /* synthetic */ String $key;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ImageView imageView = this.$btnTest;
        imageView.setSelected(!imageView.isSelected());
        CapsuleSeedingData.Companion companion = CapsuleSeedingData.Companion;
        ImageView imageView2 = this.$btnTest;
        companion.getClass();
        if (imageView2.isSelected()) {
            imageView2.setImageResource(2131232485);
        } else {
            imageView2.setImageResource(2131232484);
        }
        String str = this.$key;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("SeedlingClicked-Operation:[", str, "]", "CapsuleSeedingData-->", "SystemUi--Notification");
        }
    }
}
