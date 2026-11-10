package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class MiniGameModeObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final String IN_MINI_GAME_MODE = "mini_gamemode_value";
    private static final String TAG = "MiniGameModeObserver";
    public boolean isInMiniGameMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return IN_MINI_GAME_MODE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    public final boolean inMiniGameMode() {
        int i;
        Context context = getContext();
        if (context == null) {
            return false;
        }
        String globalStringValue = MultiUserContentHelper.Companion.getGlobalStringValue(context, IN_MINI_GAME_MODE);
        if (TextUtils.isEmpty(globalStringValue) || globalStringValue == null) {
            i = 0;
        } else {
            try {
                i = Integer.parseInt(globalStringValue);
            } catch (NumberFormatException unused) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "fail to get mini game mode setting", null, 8);
                i = 0;
            }
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "Notification--miniGameMode "), null, 8);
        return (i & 1) != 0;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onChange(boolean z) {
        this.isInMiniGameMode = inMiniGameMode();
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        this.isInMiniGameMode = inMiniGameMode();
    }
}
