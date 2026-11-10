package com.oplus.systemui.qs.res.data.repository;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import androidx.appcompat.content.res.AppCompatResources;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SystemSettings;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSThemeConfigRepository {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSThemeConfigRepository";
    private final Context context;
    private final StateFlow isGlobalThemeApplied;
    private final StateFlow isLowGaussianLevel;
    private final CoroutineScope scope;
    private final SystemSettings systemSettings;
    private final SharedFlow themeChangeEvent;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean isGlobalThemeApplied(Context context) {
            return context.getResources().getBoolean(2131034330);
        }

        public final boolean isGlobalThemeSupportOutline(Context context) {
            return context.getResources().getBoolean(2131034331);
        }

        public final boolean isGradientGlobalTheme(Context context) {
            return AppCompatResources.getDrawable(context, 2131237227) instanceof GradientDrawable;
        }
    }

    public QSThemeConfigRepository(CoroutineScope coroutineScope, Context context, QSConfigurationRepository qSConfigurationRepository, SystemSettings systemSettings, SelectedUserInteractor selectedUserInteractor) {
        this.scope = coroutineScope;
        this.context = context;
        this.systemSettings = systemSettings;
        SharedFlow configuration = qSConfigurationRepository.getConfiguration();
        QSThemeConfigRepository$special$$inlined$map$1 qSThemeConfigRepository$special$$inlined$map$1 = new QSThemeConfigRepository$special$$inlined$map$1();
        qSThemeConfigRepository$special$$inlined$map$1.$this_unsafeTransform$inlined = configuration;
        qSThemeConfigRepository$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.isGlobalThemeApplied = FlowKt.stateIn(qSThemeConfigRepository$special$$inlined$map$1, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), Boolean.FALSE);
        this.themeChangeEvent = FlowKt.shareIn(FlowConflatedKt.conflatedCallbackFlow(new QSThemeConfigRepository$themeChangeEvent$1(2, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.isLowGaussianLevel = FlowKt.asStateFlow(StateFlowKt.MutableStateFlow(Boolean.valueOf(ScrimUtil.isLowGaussianLevel(context))));
    }

    public static final boolean isGlobalThemeApplied(Context context) {
        return Companion.isGlobalThemeApplied(context);
    }

    public static final boolean isGlobalThemeSupportOutline(Context context) {
        return Companion.isGlobalThemeSupportOutline(context);
    }

    public static final boolean isGradientGlobalTheme(Context context) {
        return Companion.isGradientGlobalTheme(context);
    }

    public final SharedFlow getThemeChangeEvent() {
        return this.themeChangeEvent;
    }

    public final StateFlow isLowGaussianLevel() {
        return this.isLowGaussianLevel;
    }

    public final StateFlow isGlobalThemeApplied() {
        return this.isGlobalThemeApplied;
    }
}
