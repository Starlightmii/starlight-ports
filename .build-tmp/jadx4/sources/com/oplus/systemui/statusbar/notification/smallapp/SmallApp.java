package com.oplus.systemui.statusbar.notification.smallapp;

import android.database.Cursor;
import android.util.Log;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.slider.detail.data.EarphoneController;
import com.oplus.systemui.qs.tiles.LockTile;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmallApp {
    public String mAppName;
    public boolean mBadge;
    public boolean mBanner;
    public boolean mChangeableFold;
    public boolean mChangeableShowIcon;
    public boolean mCircleBadge;
    public boolean mFold;
    public boolean mHideDetail;
    public byte[] mIcon;
    public boolean mLight;
    public boolean mLockScreen;
    public boolean mNotify;
    public String mPackageName;
    public boolean mPriority;
    public boolean mPropertiesOnOs6NotAvailable;
    public boolean mPropertiesOnOs7NotAvailable;
    public boolean mShowIcon;
    public boolean mSound;
    public int mStowOption;
    public boolean mVibrate;

    public static SmallApp createByCursor(Cursor cursor) {
        SmallApp smallApp = new SmallApp();
        smallApp.mStowOption = 0;
        smallApp.setPackageName(cursor.getString(cursor.getColumnIndex("pkg")));
        smallApp.setAppName(cursor.getString(cursor.getColumnIndex(EarphoneController.COLUMN_NAME)));
        smallApp.setIcon(cursor.getBlob(cursor.getColumnIndex("icon")));
        smallApp.setNotify(cursor.getInt(cursor.getColumnIndex(SeparateQSStatisicUtil.VALUE_PANEL_NOTIFY)) == 1);
        smallApp.setLockScreen(cursor.getInt(cursor.getColumnIndex(LockTile.TILE_SPEC)) == 1);
        smallApp.setHideDetail(cursor.getInt(cursor.getColumnIndex("hidedetail")) == 1);
        smallApp.setBadge(cursor.getInt(cursor.getColumnIndex("badge")) == 1);
        smallApp.setCircleBadge(cursor.getInt(cursor.getColumnIndex("dot")) == 1);
        smallApp.setBanner(cursor.getInt(cursor.getColumnIndex("banner")) == 1);
        smallApp.setSound(cursor.getInt(cursor.getColumnIndex("ring")) == 1);
        smallApp.setVibrate(cursor.getInt(cursor.getColumnIndex("vibrate")) == 1);
        smallApp.setLight(cursor.getInt(cursor.getColumnIndex("light")) == 1);
        smallApp.setPriprity(cursor.getInt(cursor.getColumnIndex("priority")) == 1);
        if (cursor.getColumnIndex("statusbar") != -1) {
            smallApp.setShowIcon(cursor.getInt(cursor.getColumnIndex("statusbar")) == 1);
            smallApp.setChangeableShowIcon(cursor.getInt(cursor.getColumnIndex("statusbar_user")) == 1);
            smallApp.setFold(cursor.getInt(cursor.getColumnIndex("fold")) == 1);
            smallApp.setChangeableFold(cursor.getInt(cursor.getColumnIndex("fold_user")) == 1);
        } else {
            smallApp.setPropertiesOnOs6NotAvailable(true);
            if (LogUtil.getNormalDebug()) {
                Log.d("Notification_SA", "createByCursor: the instant app engine is old->");
            }
        }
        if (cursor.getColumnIndex("smart_fold") == -1) {
            smallApp.setPropertiesOnOs7NotAvailable(true);
            return smallApp;
        }
        smallApp.setStowOption(cursor.getInt(cursor.getColumnIndex("smart_fold")));
        smallApp.setPropertiesOnOs7NotAvailable(false);
        return smallApp;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public String getChannelName() {
        return "";
    }

    public byte[] getIcon() {
        return this.mIcon;
    }

    public int getLockscreenVisibility() {
        if (this.mLockScreen) {
            return this.mHideDetail ? 0 : 1;
        }
        return -1;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public int getStowOption() {
        return this.mStowOption;
    }

    public void setAppName(String str) {
        this.mAppName = str;
    }

    public void setBadge(boolean z) {
        this.mBadge = z;
    }

    public void setBanner(boolean z) {
        this.mBanner = z;
    }

    public void setChangeableFold(boolean z) {
        this.mChangeableFold = z;
    }

    public void setChangeableShowIcon(boolean z) {
        this.mChangeableShowIcon = z;
    }

    public void setCircleBadge(boolean z) {
        this.mCircleBadge = z;
    }

    public void setFold(boolean z) {
        this.mFold = z;
    }

    public void setHideDetail(boolean z) {
        this.mHideDetail = z;
    }

    public void setIcon(byte[] bArr) {
        this.mIcon = bArr;
    }

    public void setLight(boolean z) {
        this.mLight = z;
    }

    public void setLockScreen(boolean z) {
        this.mLockScreen = z;
    }

    public void setNotify(boolean z) {
        this.mNotify = z;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public void setPriprity(boolean z) {
        this.mPriority = z;
    }

    public void setPropertiesOnOs6NotAvailable(boolean z) {
        this.mPropertiesOnOs6NotAvailable = z;
    }

    public void setPropertiesOnOs7NotAvailable(boolean z) {
        this.mPropertiesOnOs7NotAvailable = z;
    }

    public void setShowIcon(boolean z) {
        this.mShowIcon = z;
    }

    public void setSound(boolean z) {
        this.mSound = z;
    }

    public void setStowOption(int i) {
        this.mStowOption = i;
    }

    public void setVibrate(boolean z) {
        this.mVibrate = z;
    }

    public final String toString() {
        return "packageName:" + this.mPackageName + ",appName:" + this.mAppName + ",mNotify:" + this.mNotify + ",mLockScreen:" + this.mLockScreen + ",mHideDetail:" + this.mHideDetail + ",mBadge:" + this.mBadge + ",light:" + this.mLight + ",mBanner:" + this.mBanner + ",mSound:" + this.mSound + ",mVibrate:" + this.mVibrate + ",priority:" + this.mPriority + ",mCircleBadge:" + this.mCircleBadge + ",mShowIcon:" + this.mShowIcon + ",mChangeableShowIcon:" + this.mChangeableShowIcon + ",mFold:" + this.mFold + ",mChangeableFold:" + this.mChangeableFold + ",mStowOption:" + this.mStowOption;
    }
}
