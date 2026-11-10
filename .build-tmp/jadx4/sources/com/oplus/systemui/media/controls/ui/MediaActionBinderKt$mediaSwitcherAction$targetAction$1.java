package com.oplus.systemui.media.controls.ui;

import android.media.session.MediaSession;
import android.view.View;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.dialog.MediaOutputDialogManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaActionBinderKt$mediaSwitcherAction$targetAction$1 implements Runnable {
    public /* synthetic */ MediaData $data;
    public /* synthetic */ MediaOutputDialogManager $mediaOutputDialogManager;

    @Override // java.lang.Runnable
    public final void run() {
        MediaOutputDialogManager.create$default(this.$mediaOutputDialogManager, this.$data.getPackageName(), true, (View) null, false, true, (MediaSession.Token) null, false, 236);
    }
}
