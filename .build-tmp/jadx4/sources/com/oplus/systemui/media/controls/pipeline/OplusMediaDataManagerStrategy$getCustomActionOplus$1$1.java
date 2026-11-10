package com.oplus.systemui.media.controls.pipeline;

import android.content.Intent;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataManagerStrategy$getCustomActionOplus$1$1 implements Runnable {
    public /* synthetic */ Object $controller;
    public /* synthetic */ Object $customAction;
    public /* synthetic */ Object $it;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ OplusMediaDataManagerStrategy$getCustomActionOplus$1$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        switch (this.$r8$classId) {
            case 0:
                OplusNotificationLogger.logAodMediaPanel("OplusMediaDataManagerSt", "click media panel oplus customAction: click Action-" + ((PlaybackState.CustomAction) this.$customAction));
                MediaController.TransportControls transportControls = ((MediaController) this.$controller).getTransportControls();
                PlaybackState.CustomAction customAction = (PlaybackState.CustomAction) this.$it;
                transportControls.sendCustomAction(customAction, customAction.getExtras());
                break;
            default:
                OplusMediaDataManagerStrategy oplusMediaDataManagerStrategy = (OplusMediaDataManagerStrategy) this.$customAction;
                Ref.ObjectRef objectRef = (Ref.ObjectRef) this.$it;
                MediaAction mediaAction = (MediaAction) this.$controller;
                try {
                    Intent uri = Intent.parseUri((String) objectRef.element, 0);
                    uri.setFlags(268435456);
                    oplusMediaDataManagerStrategy.context.startActivity(uri);
                    LogUtil.i$default(LogUtil.TAG_MEDIA, "OplusMediaDataManagerSt", "click customaction " + ((Object) mediaAction.getContentDescription()) + ", startActivity success", null, 8);
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                MediaAction mediaAction2 = (MediaAction) this.$controller;
                Throwable th2 = Result.exceptionOrNull-impl(obj);
                if (th2 != null) {
                    CharSequence contentDescription = mediaAction2.getContentDescription();
                    LogUtil.e$default(8, LogUtil.TAG_MEDIA, "OplusMediaDataManagerSt", "action:" + ((Object) contentDescription) + ", startActivity failed run: " + th2.getMessage());
                }
                break;
        }
    }
}
