package com.oplus.systemui.volume;

import android.view.View;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.ActivityStarter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeSnackBarController$showSnackBar$1$1 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusVolumeSnackBarController this$0;

    public /* synthetic */ OplusVolumeSnackBarController$showSnackBar$1$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                ((ActivityStarter) Dependency.sDependency.getDependencyInner(ActivityStarter.class)).startActivity(this.this$0.getZenModeIntent(), true);
                break;
            default:
                ((ActivityStarter) Dependency.sDependency.getDependencyInner(ActivityStarter.class)).startActivity(this.this$0.getVolumeSettingIntent(), true);
                break;
        }
    }
}
