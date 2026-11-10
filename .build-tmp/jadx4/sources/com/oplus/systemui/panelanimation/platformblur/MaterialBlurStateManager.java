package com.oplus.systemui.panelanimation.platformblur;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MaterialBlurStateManager {
    public static final Companion Companion = new Companion();
    private static final String SYSTEM_MATERIAL_BLUR_ENABLE = "system_material_blur_enable";
    private static final String TAG = "MaterialBlurStateManager";
    private final MutableStateFlow _enableMaterialBlur;
    private final MaterialBlurStateManager$contentObserver$1 contentObserver;
    private final Context context;
    private final StateFlow enableMaterialBlur;
    private final Handler handler;
    private final OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.ContentObserver, com.oplus.systemui.panelanimation.platformblur.MaterialBlurStateManager$contentObserver$1] */
    public MaterialBlurStateManager(Context context, final Handler handler, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState) {
        this.context = context;
        this.handler = handler;
        this.oplusSeparateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._enableMaterialBlur = MutableStateFlow;
        this.enableMaterialBlur = FlowKt.asStateFlow(MutableStateFlow);
        ?? r0 = new ContentObserver(handler) { // from class: com.oplus.systemui.panelanimation.platformblur.MaterialBlurStateManager$contentObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                boolean zEnableMaterialBlur = this.this$0.enableMaterialBlur();
                LogUtil.i$default(LogUtil.TAG_COMMON, "MaterialBlurStateManager", KeyguardLogger$.ExternalSyntheticOutline0.m("onChange enableMaterialBlur=", zEnableMaterialBlur), null, 8);
                this.this$0._enableMaterialBlur.setValue(Boolean.valueOf(zEnableMaterialBlur));
            }
        };
        this.contentObserver = r0;
        boolean zEnableMaterialBlur = enableMaterialBlur();
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("init enableMaterialBlur=", zEnableMaterialBlur), null, 8);
        MutableStateFlow.setValue(Boolean.valueOf(zEnableMaterialBlur));
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(SYSTEM_MATERIAL_BLUR_ENABLE), true, r0);
    }

    public final boolean enableMaterialBlur() {
        return Settings.System.getInt(this.context.getContentResolver(), SYSTEM_MATERIAL_BLUR_ENABLE, 0) == 1;
    }

    public final StateFlow getEnableMaterialBlur() {
        return this.enableMaterialBlur;
    }

    public final boolean getTiltShiftBlurEnabled() {
        return ((Boolean) this.enableMaterialBlur.getValue()).booleanValue() && ScrimUtil.isHighGaussianLevel(this.context) && ((Boolean) this.oplusSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue();
    }
}
