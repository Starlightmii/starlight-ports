package com.oplus.systemui.notification.interruption.gamespace;

import android.app.Notification;
import android.content.Context;
import android.provider.Settings;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.oplus.confinemode.OplusConfineModeManager;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplus.systemui.statusbar.observer.GameBarrageObserver;
import com.oplus.systemui.statusbar.observer.GameSpaceObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Arrays;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PeekGameSpaceFilter extends VisualInterruptionFilter {
    private static final String BARRAGE_ONLY_SUPPORT_LANDSCAPE_SWITCH = "barrage_only_support_landscape_switch";
    public static final Companion Companion = new Companion();
    private static final String GAME_SPACE_PKG_NAME = "com.oplus.games";
    private static final String PREVIEW_NOTIFICATION_CHANNEL = "game_space_barrage_preview_android_u";
    private static final String TAG = "PeekGameSpaceFilter";
    private final Context context;
    private final GameSpaceObserver gameSpaceObserver;
    private final HeadsUpHelper headsUpHelper;
    private final QSNoDisturbObserver qsNoDisturbObserver;
    private String reason;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PeekGameSpaceFilter(Context context, HeadsUpHelper headsUpHelper, QSNoDisturbObserver qSNoDisturbObserver, GameSpaceObserver gameSpaceObserver) {
        super(SetsKt__SetsJVMKt.setOf(VisualInterruptionType.PEEK), "game space");
        this.context = context;
        this.headsUpHelper = headsUpHelper;
        this.qsNoDisturbObserver = qSNoDisturbObserver;
        this.gameSpaceObserver = gameSpaceObserver;
        this.reason = "game space";
    }

    public String getReason() {
        return this.reason;
    }

    public boolean shouldSuppress(NotificationEntry notificationEntry) {
        if (GAME_SPACE_PKG_NAME.equals(notificationEntry.getSbn().getPackageName())) {
            Notification notification = notificationEntry.getSbn().getNotification();
            if (PREVIEW_NOTIFICATION_CHANNEL.equals(notification != null ? notification.getChannelId() : null)) {
                this.reason = "suppress by game space notification";
                return true;
            }
        }
        boolean zIsThirdPartInCallNotification = this.headsUpHelper.isThirdPartInCallNotification(notificationEntry.getSbn());
        HeadsUpHelper headsUpHelper = this.headsUpHelper;
        if (headsUpHelper.mGameModeObserver.isInGameMode && headsUpHelper.mGameBarrageObserver.getBarrageEnable()) {
            String string = Settings.Secure.getString(this.context.getContentResolver(), BARRAGE_ONLY_SUPPORT_LANDSCAPE_SWITCH);
            boolean z = Intrinsics.areEqual(string, "0") || this.headsUpHelper.mOrientation == 2;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isShouldShowBarrage: ", string, "  ", z), null, 8);
            if (z && !zIsThirdPartInCallNotification) {
                HeadsUpHelper headsUpHelper2 = this.headsUpHelper;
                String packageName = notificationEntry.getSbn().getPackageName();
                GameBarrageObserver gameBarrageObserver = headsUpHelper2.mGameBarrageObserver;
                if (headsUpHelper2.mSupportDanmakuPkg == null) {
                    headsUpHelper2.mSupportDanmakuPkg = headsUpHelper2.mContext.getResources().getStringArray(2130903190);
                }
                if (Arrays.asList(headsUpHelper2.mSupportDanmakuPkg).contains(packageName) && gameBarrageObserver.getAllowedPkgs() != null) {
                    try {
                        JSONArray jSONArray = new JSONArray(gameBarrageObserver.getAllowedPkgs());
                        for (int i = 0; i < jSONArray.length(); i++) {
                            String string2 = jSONArray.getJSONObject(i).getString("pkg_name");
                            String string3 = jSONArray.getJSONObject(i).getString("pkg_switch");
                            if (TextUtils.equals(packageName, string2) && TextUtils.equals(string3, "1")) {
                                this.reason = "suppress by game space danmaku";
                                return true;
                            }
                        }
                    } catch (JSONException unused) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "HeadsUpHelper", "isSupportDanmaku: JsonException");
                    }
                }
            }
        }
        if (Arrays.asList(this.headsUpHelper.mGameSpaceWhitelist).contains(notificationEntry.getSbn().getPackageName())) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, FileProvider$.ExternalSyntheticOutline0.m("App is in gameSpace whitelist: ", notificationEntry.getSbn().getPackageName()), null, 8);
            return false;
        }
        if (this.headsUpHelper.mGameModeObserver.isInGameMode) {
            if (this.gameSpaceObserver.isInGameSpaceDisturb) {
                if (!zIsThirdPartInCallNotification) {
                    this.reason = "suppress by disturb in game: not third call";
                    return true;
                }
                if (OplusConfineModeManager.getInstance().getConfineMode() == 8) {
                    this.reason = "suppress by disturb in game: third call in confineMode";
                    return true;
                }
            }
        } else if (this.qsNoDisturbObserver.isInGameSpaceDisturb) {
            this.reason = "suppress by game space disturb";
            return true;
        }
        return false;
    }
}
