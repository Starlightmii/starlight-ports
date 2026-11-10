package com.oplus.systemui.media.seedling.client;

import android.media.session.MediaSession;
import android.view.View;
import com.android.systemui.media.dialog.MediaOutputDialogManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AbsMediaClient$loadAppIconAndUpdate$1 implements Runnable {
    public /* synthetic */ Object $appIcon;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ AbsMediaClient this$0;

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                AbsMediaClient absMediaClient = this.this$0;
                if (!absMediaClient.isDestroyed) {
                    absMediaClient.getAppIconLoader().update(this.$appIcon, this.this$0.getMediaData());
                    break;
                }
                break;
            default:
                MediaOutputDialogManager.create$default(this.this$0.getOutputDialogFactory(), (String) this.$appIcon, true, (View) null, false, false, (MediaSession.Token) null, false, 248);
                break;
        }
    }
}
