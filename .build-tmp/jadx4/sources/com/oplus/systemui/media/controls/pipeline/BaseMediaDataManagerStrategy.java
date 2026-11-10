package com.oplus.systemui.media.controls.pipeline;

import android.media.session.MediaController;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface BaseMediaDataManagerStrategy {
    MediaButton createActionsFromState(String str, MediaData mediaData, MediaController mediaController);

    default MediaAction getHeartAction(MediaController mediaController, String str) {
        return null;
    }

    boolean onMatching(String str);
}
