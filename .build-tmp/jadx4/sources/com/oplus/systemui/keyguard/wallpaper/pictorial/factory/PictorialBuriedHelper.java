package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.ContentValues;
import android.content.Context;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface PictorialBuriedHelper {
    default void addWallPaper(Context context, String str, String str2, long j) {
    }

    default void addWallPaper(Context context, String str, String str2, boolean z) {
    }

    default void collectSwitchData(ContentValues contentValues) {
    }

    default void onStatistic(Context context) {
    }

    default void swipeToPictorialApp(Context context) {
    }

    default void switchPictorialApp(Context context) {
    }

    default void uploadActiveEvent(Context context, String str) {
    }

    default void uploadInstalledEvent(Context context) {
    }

    default void uploadState(String str) {
    }

    default void uploadSwitch() {
    }
}
