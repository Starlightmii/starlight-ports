package com.oplus.systemui.notification.lockscreen.capsule;

import android.util.Log;
import androidx.core.view.ViewGroupKt;
import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.aod.notification.NotificationDetailColorChanger;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import javax.inject.Provider;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleAodTransition implements OplusPanoramicAodRepository.PanoramicAodProgressChangeListener, CoreStartable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleAodTransition";
    public boolean alreadyResetDragScaleOnEnterAod;
    private final NotificationCapsuleContainer capsuleContainer;
    private NotificationDetailColorChanger colorChanger;
    private final Provider colorChangerProvider;
    private final InitController initController;
    private final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    public float progress;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.CapsuleAodTransition$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ CapsuleAodTransition this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.oplusPanoramicAodRepository.addCallback(this.this$0);
        }
    }

    public CapsuleAodTransition(NotificationCapsuleContainer notificationCapsuleContainer, InitController initController, OplusPanoramicAodRepository oplusPanoramicAodRepository, Provider provider) {
        this.capsuleContainer = notificationCapsuleContainer;
        this.initController = initController;
        this.oplusPanoramicAodRepository = oplusPanoramicAodRepository;
        this.colorChangerProvider = provider;
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onPanoramicAodStateChanged(boolean z, boolean z2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onPanoramicAodStateChanged, isPanoramicAod: ", ", shouldUpdateUi: ", "CapsuleAodTransition-->", z, z2));
        }
        if (!z) {
            this.alreadyResetDragScaleOnEnterAod = false;
        } else if (z2 && !this.alreadyResetDragScaleOnEnterAod) {
            this.alreadyResetDragScaleOnEnterAod = true;
            this.capsuleContainer.getScroller().cancelCapsuleDragScale("onEnterPanoramicAodState");
        }
        this.capsuleContainer.getAnimatorHelper().generateAodHdrAnimation(z);
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onProgressChanged(float f) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("onProgressChanged, progress: ", f, "CapsuleAodTransition-->", "SystemUi--Notification");
        }
        if (this.progress == f) {
            return;
        }
        this.progress = f;
        this.capsuleContainer.setScrimAlpha(f);
        NotificationDetailColorChanger notificationDetailColorChanger = this.colorChanger;
        if (notificationDetailColorChanger == null) {
            notificationDetailColorChanger = (NotificationDetailColorChanger) this.colorChangerProvider.get();
            this.colorChanger = notificationDetailColorChanger;
        }
        for (CapsuleItemView capsuleItemView : SequencesKt___SequencesKt.mapNotNull(ViewGroupKt.getChildren(this.capsuleContainer), new CapsuleAodTransition$.ExternalSyntheticLambda0())) {
            notificationDetailColorChanger.getClass();
            capsuleItemView.setColorChanger(notificationDetailColorChanger);
            notificationDetailColorChanger.changeCapsuleViewColor(capsuleItemView.getEntry().getContentView(), f);
        }
        if (f == 0.0f) {
            NotificationDetailColorChanger notificationDetailColorChanger2 = this.colorChanger;
            if (notificationDetailColorChanger2 != null) {
                notificationDetailColorChanger2.removeConfigurationCallback();
            }
            this.colorChanger = null;
        }
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }
}
