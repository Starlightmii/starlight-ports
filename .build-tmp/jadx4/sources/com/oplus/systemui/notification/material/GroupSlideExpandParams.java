package com.oplus.systemui.notification.material;

import android.content.Context;
import android.provider.Settings;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GroupSlideExpandParams {
    private static final String SERVER_NAME = "通知组下滑展开";
    private static final String SETTINGS_KEY_OPLUS_DEBUG_GROUP_SLIDE_EXPAND = "oplus_debug_group_slide_expand";
    private static final String SLIDE_EXPAND_GROUP = "通知组下滑展开";
    public volatile boolean cachedDebugOliveMode;
    public volatile boolean tuningCacheInitialized;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(new GroupSlideExpandParams$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final GroupSlideExpandParams getInstance() {
            return (GroupSlideExpandParams) GroupSlideExpandParams.instance$delegate.getValue();
        }

        public final float getSlideExpandThreshold(Context context) {
            GroupSlideExpandParams companion = getInstance();
            if (!companion.cachedDebugOliveMode) {
                return 90.0f;
            }
            if (companion.cachedDebugOliveMode) {
                OliveLinkConnector.INSTANCE.start(context.getApplicationContext(), "通知组下滑展开");
            }
            return new SlideExpandOliveScalarGroup("通知组下滑展开").getSlideExpandThreshold().get().floatValue();
        }

        public final float getSlideExpandVelocityThreshold(Context context) {
            GroupSlideExpandParams companion = getInstance();
            if (!companion.cachedDebugOliveMode) {
                return 1000.0f;
            }
            if (companion.cachedDebugOliveMode) {
                OliveLinkConnector.INSTANCE.start(context.getApplicationContext(), "通知组下滑展开");
            }
            return new SlideExpandOliveScalarGroup("通知组下滑展开").getSlideExpandVelocityThreshold().get().floatValue();
        }

        public final void init(Context context) {
            GroupSlideExpandParams companion = getInstance();
            if (companion.tuningCacheInitialized) {
                return;
            }
            GroupSlideExpandParams$initTuningCache$1 groupSlideExpandParams$initTuningCache$1 = new GroupSlideExpandParams$initTuningCache$1();
            groupSlideExpandParams$initTuningCache$1.this$0 = companion;
            groupSlideExpandParams$initTuningCache$1.$context = context;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(groupSlideExpandParams$initTuningCache$1);
        }

        public final boolean isOliveDebugMode() {
            return getInstance().cachedDebugOliveMode;
        }
    }

    public static final void access$refreshTuningEnabled(GroupSlideExpandParams groupSlideExpandParams, Context context) {
        Boolean booleanStrictOrNull;
        String stringForUser = Settings.System.getStringForUser(context.getContentResolver(), SETTINGS_KEY_OPLUS_DEBUG_GROUP_SLIDE_EXPAND, context.getUserId());
        groupSlideExpandParams.cachedDebugOliveMode = (stringForUser == null || (booleanStrictOrNull = StringsKt__StringsKt.toBooleanStrictOrNull(stringForUser)) == null) ? false : booleanStrictOrNull.booleanValue();
    }
}
