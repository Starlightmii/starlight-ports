package com.oplus.systemui.statusbar.notification.flashlight.seeding;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import androidx.core.os.BundleKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.seedling.livealert.LiveAlertPendingIntentService;
import com.oplus.systemui.statusbar.seeding.StartSeedlingService;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlashlightSeedling {
    public static final Companion Companion = new Companion();
    public static final String FLASHLIGHT_EVENT = "flashlightEvent";
    public static final String KEY_FLASHLIGHT_OPTION_FROM = "flashlightOptionFrom";
    private static final String KEY_FLASHLIGHT_SEEDLING_CARD_STATE = "flashlightSeedlingCardState";
    public static final String KEY_FLASHLIGHT_STATE = "flashlightState";
    public static final String KEY_FLASHLIGHT_TITLE = "flashlightTitle";
    private static final String TAG = "FlashlightSeedling";
    private final FlashlightController flashlightController;
    private final FlashlightSeedling$flashlightListener$1 flashlightListener = new FlashlightSeedling$flashlightListener$1();
    public boolean isFlashlightSeedlingExist;
    public boolean setFlashlightListener;
    private final StartSeedlingService startSeedlingService;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$jFfsvmwffO6xAqnRo6-KTbXnkXE, reason: not valid java name */
    public static Unit m388$r8$lambda$jFfsvmwffO6xAqnRo6KTbXnkXE(FlashlightSeedling flashlightSeedling, Message message) {
        Bundle data = message.getData();
        if (Intrinsics.areEqual(data != null ? data.getString("flashlightEvent") : null, "flashlightEvent")) {
            int i = data.getInt(KEY_FLASHLIGHT_STATE, -1);
            if (i == 1) {
                flashlightSeedling.flashlightController.setFlashlightWithUsageTrack(true);
            } else if (i == 2) {
                flashlightSeedling.flashlightController.setFlashlightWithUsageTrack(false);
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "illegal flashlight state: ", "FlashlightSeedling-->", "SystemUi--Common");
            }
            int i2 = data.getInt(KEY_FLASHLIGHT_SEEDLING_CARD_STATE, -1);
            if (i2 == 0) {
                flashlightSeedling.isFlashlightSeedlingExist = false;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("set FlashlightSeedlingExist: ", "FlashlightSeedling-->", "SystemUi--Common", flashlightSeedling.isFlashlightSeedlingExist);
                }
            } else if (i2 == 1) {
                flashlightSeedling.isFlashlightSeedlingExist = true;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("set FlashlightSeedlingExist: ", "FlashlightSeedling-->", "SystemUi--Common", flashlightSeedling.isFlashlightSeedlingExist);
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i2, "illegal flashlight cardExist: ", "FlashlightSeedling-->", "SystemUi--Common");
            }
        }
        return Unit.INSTANCE;
    }

    public FlashlightSeedling(StartSeedlingService startSeedlingService, FlashlightController flashlightController) {
        this.startSeedlingService = startSeedlingService;
        this.flashlightController = flashlightController;
        FlashlightSeedling$$ExternalSyntheticLambda0 flashlightSeedling$$ExternalSyntheticLambda0 = new FlashlightSeedling$$ExternalSyntheticLambda0();
        flashlightSeedling$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        startSeedlingService.registerServerEventListener("flashlightEvent", flashlightSeedling$$ExternalSyntheticLambda0);
        LiveAlertPendingIntentService.Companion.registerServerEvent("flashlightEvent", new FlashlightSeedling$$ExternalSyntheticLambda1());
    }

    public final boolean getFlashlightCardState() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("getFlashlightCardState: ", "FlashlightSeedling-->", "SystemUi--Common", this.isFlashlightSeedlingExist);
        }
        return this.isFlashlightSeedlingExist;
    }

    public final void updateFlashlightSeeding(Context context, int i, int i2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("updateFlashlightSeeding flashlight state: ", i, i2, ", optionFrom=", "FlashlightSeedling-->"));
        }
        Bundle bundleBundleOf = BundleKt.bundleOf(new Pair[]{TuplesKt.to(KEY_FLASHLIGHT_STATE, Integer.valueOf(i))});
        bundleBundleOf.putInt(KEY_FLASHLIGHT_OPTION_FROM, i2);
        this.startSeedlingService.updateData(context, "flashlightEvent", bundleBundleOf);
        if (i != 1 || this.setFlashlightListener) {
            return;
        }
        this.setFlashlightListener = true;
        this.flashlightController.addCallback(this.flashlightListener);
    }

    public final void updateFlashlightTitleSeeding(Context context, String str, int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("updateFlashlightSeeding flashlight title: ", str, "FlashlightSeedling-->", "SystemUi--Common");
        }
        Bundle bundleBundleOf = BundleKt.bundleOf(new Pair[]{TuplesKt.to(KEY_FLASHLIGHT_TITLE, str)});
        bundleBundleOf.putInt(KEY_FLASHLIGHT_STATE, i);
        this.startSeedlingService.updateData(context, "flashlightEvent", bundleBundleOf);
    }
}
