package com.oplus.systemui.qs.res.data.repository;

import android.content.Context;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.util.OnePlusQsColorUtil;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSColorfulIconRepository {
    public static final Companion Companion = new Companion();
    private static final String KEY = "qs_tile_colorful_config";
    private static final String TAG = "QSColorfulIconRepository";
    private static final String TWO_TONE_KEY = "oos_theme_customization_two_tone";
    private final StateFlow accentColor;
    private final StateFlow colorfulIconAvailable;
    private final Context context;
    private final CoroutineScope scope;
    private final SecureSettings secureSettings;
    private final StateFlow tintTileIconEnable;
    private final StateFlow twoToneThemeEnabled;

    public QSColorfulIconRepository(Context context, CoroutineScope coroutineScope, SecureSettings secureSettings, SelectedUserInteractor selectedUserInteractor, QSConfigurationRepository qSConfigurationRepository) {
        this.context = context;
        this.scope = coroutineScope;
        this.secureSettings = secureSettings;
        Flow flowFlowOf = QsColorUtil.INSTANCE.getWallpaperBlurDisable() ? FlowKt.flowOf(Boolean.FALSE) : FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new QSColorfulIconRepository$special$$inlined$flatMapLatest$1(this, null));
        CoroutineScope settingsScope = secureSettings.getSettingsScope();
        SharingStarted.Companion companion = SharingStarted.Companion;
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        this.colorfulIconAvailable = FlowKt.stateIn(flowFlowOf, settingsScope, sharingStartedWhileSubscribed$default, bool);
        this.accentColor = FlowKt.asStateFlow(StateFlowKt.MutableStateFlow(Integer.valueOf(Companion.getColorfulIconAccentColor(context))));
        this.tintTileIconEnable = FlowKt.stateIn(FlowKt.mapLatest(qSConfigurationRepository.getConfiguration(), new QSColorfulIconRepository$tintTileIconEnable$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), Boolean.TRUE);
        FlavorTwoFeatureOption.INSTANCE.getClass();
        this.twoToneThemeEnabled = FlowKt.stateIn(!FlavorTwoFeatureOption.isFlavorTwoDeviceExp() ? FlowKt.flowOf(bool) : FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new QSColorfulIconRepository$special$$inlined$flatMapLatest$2(this, null)), secureSettings.getSettingsScope(), SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
    }

    public static final boolean access$isColorfulIconSettingEnabled(QSColorfulIconRepository qSColorfulIconRepository, int i) {
        return qSColorfulIconRepository.secureSettings.getIntForUser(KEY, 0, i) == 0;
    }

    public static final int getColorfulIconAccentColor(Context context) {
        return Companion.getColorfulIconAccentColor(context);
    }

    public static final int getSepActiveIconColor(Context context, boolean z) {
        return Companion.getSepActiveIconColor(context, z);
    }

    public static final int getSepThemeActiveIconColor(Context context, boolean z) {
        return Companion.getSepThemeActiveIconColor(context, z);
    }

    public static final int getSepThreeStageInactiveIconColor(Context context, boolean z) {
        return Companion.getSepThreeStageInactiveIconColor(context, z);
    }

    public static final int getSepTwoToneAccentColor(Context context, boolean z) {
        return Companion.getSepTwoToneAccentColor(context, z);
    }

    public static final int getSepUnavailableIconColor(Context context, boolean z) {
        return Companion.getSepUnavailableIconColor(context, z);
    }

    public static final int getStdActiveIconColor(Context context, boolean z) {
        return Companion.getStdActiveIconColor(context, z);
    }

    public static final int getStdInactiveIconColor(Context context) {
        return Companion.getStdInactiveIconColor(context);
    }

    public static final int getStdInactiveIconDarkColor(Context context) {
        return Companion.getStdInactiveIconDarkColor(context);
    }

    public static final int getStdTwoToneAccentColor(Context context, boolean z) {
        return Companion.getStdTwoToneAccentColor(context, z);
    }

    public static final int getStdUnavailableIconColor(Context context) {
        return Companion.getStdUnavailableIconColor(context);
    }

    public static final int getStdUnavailableIconDarkColor(Context context) {
        return Companion.getStdUnavailableIconDarkColor(context);
    }

    public static final boolean isTintIconAllowed(Context context) {
        return Companion.isTintIconAllowed(context);
    }

    public final StateFlow getAccentColor() {
        return this.accentColor;
    }

    public final StateFlow getColorfulIconAvailable() {
        return this.colorfulIconAvailable;
    }

    public final StateFlow getTintTileIconEnable() {
        return this.tintTileIconEnable;
    }

    public final StateFlow getTwoToneThemeEnabled() {
        return this.twoToneThemeEnabled;
    }

    public static final int getSepThreeStageInactiveIconColor(Context context, boolean z, boolean z2) {
        return Companion.getSepThreeStageInactiveIconColor(context, z, z2);
    }

    public static final int getSepUnavailableIconColor(Context context, boolean z, boolean z2) {
        return Companion.getSepUnavailableIconColor(context, z, z2);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getColorfulIconAccentColor(Context context) {
            return context.getColor(2131103735);
        }

        public final int getSepActiveIconColor(Context context, boolean z) {
            FlavorTwoFeatureOption.INSTANCE.getClass();
            if (!FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
                return QsColorUtil.QS_ICON_INACTIVE_DARK_COLOR;
            }
            if (OnePlusQsColorUtil.INSTANCE.getEnableTwoToneTheme()) {
                return z ? context.getColor(2131103743) : context.getColor(2131102960);
            }
            return context.getColor(2131103744);
        }

        public final int getSepThemeActiveIconColor(Context context, boolean z) {
            FlavorTwoFeatureOption.INSTANCE.getClass();
            if (FlavorTwoFeatureOption.isFlavorTwoDeviceExp() && OnePlusQsColorUtil.INSTANCE.getEnableTwoToneTheme()) {
                return z ? context.getColor(2131103743) : context.getColor(2131102960);
            }
            return context.getColor(2131103743);
        }

        public final int getSepThreeStageInactiveIconColor(Context context, boolean z, boolean z2) {
            if (z2) {
                return z ? QsColorUtil.QS_ICON_INACTIVE_DARK_COLOR : QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR;
            }
            return context.getColor(2131103299);
        }

        public final int getSepTwoToneAccentColor(Context context, boolean z) {
            return z ? context.getColor(2131102963) : context.getColor(2131102964);
        }

        public final int getSepUnavailableIconColor(Context context, boolean z, boolean z2) {
            return (!z2 || z) ? context.getColor(2131103293) : context.getColor(2131103750);
        }

        public final int getStdActiveIconColor(Context context, boolean z) {
            if (OnePlusQsColorUtil.INSTANCE.getEnableTwoToneTheme()) {
                return z ? context.getColor(2131102967) : context.getColor(2131102960);
            }
            return context.getColor(2131103743);
        }

        public final int getStdInactiveIconColor(Context context) {
            return context.getColor(2131103746);
        }

        public final int getStdInactiveIconDarkColor(Context context) {
            return context.getColor(2131103748);
        }

        public final int getStdTwoToneAccentColor(Context context, boolean z) {
            return z ? context.getColor(2131102966) : context.getColor(2131102965);
        }

        public final int getStdUnavailableIconColor(Context context) {
            return context.getColor(2131103749);
        }

        public final int getStdUnavailableIconDarkColor(Context context) {
            return context.getColor(2131103751);
        }

        public final boolean isTintIconAllowed(Context context) {
            return context.getResources().getBoolean(2131034332);
        }

        public final int getSepThreeStageInactiveIconColor(Context context, boolean z) {
            return getSepThreeStageInactiveIconColor(context, z, QsColorUtil.INSTANCE.getWallpaperBlurDisable());
        }

        public final int getSepUnavailableIconColor(Context context, boolean z) {
            return getSepUnavailableIconColor(context, z, QsColorUtil.INSTANCE.getWallpaperBlurDisable());
        }
    }
}
