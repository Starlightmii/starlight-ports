package com.oplus.systemui.media.controls.pipeline;

import android.content.Intent;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import com.android.systemui.DependencyEx;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.plugins.ActivityStarter;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataManagerStrategy$getCustomAction$1$1 implements Runnable {
    public /* synthetic */ MediaController $controller;
    public /* synthetic */ Object $it;
    public final /* synthetic */ int $r8$classId;

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        Object obj2;
        switch (this.$r8$classId) {
            case 0:
                OplusNotificationLogger.logAodMediaPanel("OplusMediaDataManagerSt", "click media panel customAction: click Action-" + ((PlaybackState.CustomAction) this.$it));
                MediaController.TransportControls transportControls = this.$controller.getTransportControls();
                PlaybackState.CustomAction customAction = (PlaybackState.CustomAction) this.$it;
                transportControls.sendCustomAction(customAction, customAction.getExtras());
                break;
            case 1:
                OplusNotificationLogger.logAodMediaPanel("OplusMediaDataManagerSt", "click media heart notLike button, intentUri: " + ((Ref.ObjectRef) this.$it).element);
                CharSequence charSequence = (CharSequence) ((Ref.ObjectRef) this.$it).element;
                if (charSequence == null || charSequence.length() == 0) {
                    this.$controller.getTransportControls().setRating(Rating.newHeartRating(false));
                } else {
                    try {
                        Intent uri = Intent.parseUri((String) ((Ref.ObjectRef) this.$it).element, 0);
                        uri.setFlags(268435456);
                        ((ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class)).postStartActivityDismissingKeyguard(uri, 0);
                        obj = Result.constructor-impl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                    Throwable th2 = Result.exceptionOrNull-impl(obj);
                    if (th2 != null) {
                        GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "startActivity failed run: ", th2.getMessage(), LogUtil.TAG_MEDIA, "OplusMediaDataManagerSt");
                    }
                }
                break;
            default:
                OplusNotificationLogger.logAodMediaPanel("OplusMediaDataManagerSt", "click media heart like button, intentUri: " + ((Ref.ObjectRef) this.$it).element);
                CharSequence charSequence2 = (CharSequence) ((Ref.ObjectRef) this.$it).element;
                if (charSequence2 == null || charSequence2.length() == 0) {
                    this.$controller.getTransportControls().setRating(Rating.newHeartRating(true));
                } else {
                    try {
                        Intent uri2 = Intent.parseUri((String) ((Ref.ObjectRef) this.$it).element, 0);
                        uri2.setFlags(268435456);
                        ((ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class)).postStartActivityDismissingKeyguard(uri2, 0);
                        obj2 = Result.constructor-impl(Unit.INSTANCE);
                    } catch (Throwable th3) {
                        obj2 = Result.constructor-impl(ResultKt.createFailure(th3));
                    }
                    Throwable th4 = Result.exceptionOrNull-impl(obj2);
                    if (th4 != null) {
                        GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "startActivity failed run: ", th4.getMessage(), LogUtil.TAG_MEDIA, "OplusMediaDataManagerSt");
                    }
                }
                break;
        }
    }
}
