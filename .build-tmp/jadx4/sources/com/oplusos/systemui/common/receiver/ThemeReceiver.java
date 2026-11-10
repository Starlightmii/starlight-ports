package com.oplusos.systemui.common.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.systemui.common.settings.others.ThemeBundleUtils;
import com.oplus.theme.OplusThirdPartUtil;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThemeReceiver extends AbstractReceiver {
    public static final String MATERIAL_COLOR_MODE = "material_color_mode";
    private static final String TAG = "ThemeReceiver";
    private static final String THEME_APPLIED_FLAG = "theme_applied_flag";
    public static final String THEME_CHANGE_MODE = "theme_change_mode";
    public static final String THEME_SKIN_CHANGED = "theme_skin_changed";
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new ThemeReceiver$$ExternalSyntheticLambda0());

    /* JADX INFO: renamed from: com.oplusos.systemui.common.receiver.ThemeReceiver$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IReceiverListener {
        public /* synthetic */ ThemeReceiver this$0;

        @Override // com.oplusos.systemui.common.receiver.IReceiverListener
        public final void onReceive(Context context, Intent intent) {
            ThemeReceiver.init(context);
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.receiver.ThemeReceiver$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements IReceiverListener {
        @Override // com.oplusos.systemui.common.receiver.IReceiverListener
        public final void onReceive(Context context, Intent intent) {
            ThemeBundleUtils.Companion.init(context);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final ThemeReceiver getInstance() {
            return (ThemeReceiver) ThemeReceiver.instance$delegate.getValue();
        }
    }

    public static void init(Context context) {
        try {
            int globalIntValue = MultiUserContentHelper.Companion.getGlobalIntValue(context, THEME_APPLIED_FLAG, 0);
            boolean defaultTheme = OplusThirdPartUtil.getDefaultTheme();
            boolean z = (globalIntValue & 256) == 256;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "ThemeReceiver-->".concat("getCurrentThemeBuildInOS isDefaultTheme:" + defaultTheme + " isBuildInTheme:" + z + " themeAppliedFlag:" + globalIntValue));
            }
        } catch (Throwable th) {
            Log.e(TAG, "getCurrentThemeBuildInOS Throwable:" + th);
        }
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public void afterRegister(Context context) {
        super.afterRegister(context);
        init(context);
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"oplus.intent.action.SKIN_CHANGED"};
    }
}
