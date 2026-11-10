package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hidl.base.V1_0.DebugInfo$;
import android.net.Uri;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.keyguard.widget.LockscreenWidget;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PictorialWallpaperInfo extends KeyguardWallpaperInfo {
    public PictorialBrightness mBrightnessValue;
    public Intent mClickIntent;
    public String mClickText;
    public int mContentColor;
    public int mContentSize;
    public String mContentString;
    public String mCopyrightDesc;
    public String mDeepLink;
    public String mFilePath;
    public String mFileUnzipUri;
    public Uri mFileUri;
    public String mGroupId;
    public int mGroupType;
    public String mImageId;
    public String mInstantAppLink;
    public LockscreenWidget mLockscreenWidget;
    public Drawable mMoreButtonBg;
    public int mMoreButtonBgRes;
    public int mMoreButtonColor;
    public int mMoreButtonMarginLeft;
    public Drawable mMoreButtonPressedBg;
    public int mMoreButtonPressedBgRes;
    public int mMoreButtonSize;
    public PictorialWallpaperInfo mNextWallPaper;
    public String mPackageNameLink;
    public int mPictorialImageType;
    public String mSourceName;
    public String mTitle;
    public int mTitleColor;
    public int mTitleSize;
    public String mTransparent;
    public String mTxtGroupId;
    public Drawable mWallpaperDrawable;
    public String mWallpaperId;
    public String mWallpaperTitle;
    public String mWallpaperWebType;
    public String mWallpaperWebUrl;
    public String mWebUrl;
    public WallpaperType mImageType = WallpaperType.IDLE;
    public Map mDataMap = new HashMap();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class WallpaperType {
        public static final /* synthetic */ WallpaperType[] $VALUES;
        public static final WallpaperType COVER;
        public static final WallpaperType IDLE;
        public static final WallpaperType NEXT;

        static {
            WallpaperType wallpaperType = new WallpaperType("IDLE", 0);
            IDLE = wallpaperType;
            WallpaperType wallpaperType2 = new WallpaperType("COVER", 1);
            COVER = wallpaperType2;
            WallpaperType wallpaperType3 = new WallpaperType("NEXT", 2);
            NEXT = wallpaperType3;
            $VALUES = new WallpaperType[]{wallpaperType, wallpaperType2, wallpaperType3};
        }

        public static WallpaperType valueOf(String str) {
            return (WallpaperType) Enum.valueOf(WallpaperType.class, str);
        }

        public static WallpaperType[] values() {
            return (WallpaperType[]) $VALUES.clone();
        }
    }

    public PictorialWallpaperInfo(PictorialWallpaperInfo pictorialWallpaperInfo) {
        setMType(1);
        setColor(pictorialWallpaperInfo.getMColor());
        setImageId(pictorialWallpaperInfo.getImageId());
        setGroupId(pictorialWallpaperInfo.getGroupId());
        setGroupType(pictorialWallpaperInfo.getGroupType());
        setFileUri(pictorialWallpaperInfo.getFileUri());
        setClickText(pictorialWallpaperInfo.getClickText());
        setTextTitle(pictorialWallpaperInfo.getTextTitle());
        setTextContent(pictorialWallpaperInfo.getTextContent());
        setWebUrl(pictorialWallpaperInfo.getWebUrl());
        setDeepLink(pictorialWallpaperInfo.getDeepLink());
        setInstantAppLink(pictorialWallpaperInfo.getInstantAppLink());
        setFilePath(pictorialWallpaperInfo.getFilePath());
        setCopyrightDesc(pictorialWallpaperInfo.getCopyrightDesc());
        setTitleSize(pictorialWallpaperInfo.getTitleSize());
        setTitleColor(pictorialWallpaperInfo.getTitleColor());
        setContentSize(pictorialWallpaperInfo.getContentSize());
        setContentColor(pictorialWallpaperInfo.getContentColor());
        setMoreButtonSize(pictorialWallpaperInfo.getMoreButtonSize());
        setMoreButtonColor(pictorialWallpaperInfo.getMoreButtonColor());
        setMoreButtonBgRes(pictorialWallpaperInfo.getMoreButtonBgRes());
        setMoreButtonBg(pictorialWallpaperInfo.getMoreButtonBg());
        setMoreButtonBgRes(pictorialWallpaperInfo.getMoreButtonPressedBgRes());
        setMoreButtonPressedBg(pictorialWallpaperInfo.getMoreButtonPressedBg());
        setMoreButtonMarginLeft(pictorialWallpaperInfo.getMoreButtonMarginLeft());
        setNextWallpaper(pictorialWallpaperInfo.getPreloadWallpaper());
        setWallpaperDrawable(pictorialWallpaperInfo.getWallpaperDrawable());
        setClickDetailIntent(pictorialWallpaperInfo.getClickDetailIntent());
        setSourceName(pictorialWallpaperInfo.getSourceName());
    }

    public final void copyWallpaperDrawable(PictorialWallpaperInfo pictorialWallpaperInfo) {
        if (pictorialWallpaperInfo == null || pictorialWallpaperInfo.getWallpaperDrawable() == null) {
            return;
        }
        setWallpaperDrawable(pictorialWallpaperInfo.getWallpaperDrawable().getConstantState().newDrawable().mutate());
    }

    public int getBrightnessClockValue() {
        PictorialBrightness pictorialBrightness = this.mBrightnessValue;
        if (pictorialBrightness == null) {
            return 0;
        }
        return pictorialBrightness.getClockBrightnessValue();
    }

    public int getBrightnessLeftValue() {
        PictorialBrightness pictorialBrightness = this.mBrightnessValue;
        if (pictorialBrightness == null) {
            return 0;
        }
        return pictorialBrightness.getLeftBottomBrightnessValue();
    }

    public int getBrightnessRightValue() {
        PictorialBrightness pictorialBrightness = this.mBrightnessValue;
        if (pictorialBrightness == null) {
            return 0;
        }
        return pictorialBrightness.getRightBottomBrightnessValue();
    }

    public Intent getClickDetailIntent() {
        return this.mClickIntent;
    }

    public String getClickText() {
        return this.mClickText;
    }

    public int getContentColor() {
        return this.mContentColor;
    }

    public int getContentSize() {
        return this.mContentSize;
    }

    public String getCopyrightDesc() {
        return this.mCopyrightDesc;
    }

    public Map<String, Object> getDataMap() {
        return this.mDataMap;
    }

    public String getDeepLink() {
        return this.mDeepLink;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public String getFileUnzipUri() {
        return this.mFileUnzipUri;
    }

    public Uri getFileUri() {
        return this.mFileUri;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    public int getGroupType() {
        return this.mGroupType;
    }

    public String getImageId() {
        return this.mImageId;
    }

    public String getInstantAppLink() {
        return this.mInstantAppLink;
    }

    public LockscreenWidget getLockscreenWidget() {
        return this.mLockscreenWidget;
    }

    public Drawable getMoreButtonBg() {
        return this.mMoreButtonBg;
    }

    public int getMoreButtonBgRes() {
        return this.mMoreButtonBgRes;
    }

    public int getMoreButtonColor() {
        return this.mMoreButtonColor;
    }

    public int getMoreButtonMarginLeft() {
        return this.mMoreButtonMarginLeft;
    }

    public Drawable getMoreButtonPressedBg() {
        return this.mMoreButtonPressedBg;
    }

    public int getMoreButtonPressedBgRes() {
        return this.mMoreButtonPressedBgRes;
    }

    public int getMoreButtonSize() {
        return this.mMoreButtonSize;
    }

    public String getPackageNameLink() {
        return this.mPackageNameLink;
    }

    public int getPictorialImageType() {
        return this.mPictorialImageType;
    }

    public PictorialWallpaperInfo getPreloadWallpaper() {
        return this.mNextWallPaper;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public String getTextContent() {
        return this.mContentString;
    }

    public String getTextTitle() {
        return this.mTitle;
    }

    public int getTitleColor() {
        return this.mTitleColor;
    }

    public int getTitleSize() {
        return this.mTitleSize;
    }

    public String getTransparent() {
        return this.mTransparent;
    }

    public String getTxtGroupId() {
        return this.mTxtGroupId;
    }

    public Drawable getWallpaperDrawable() {
        return this.mWallpaperDrawable;
    }

    public String getWallpaperId() {
        return this.mWallpaperId;
    }

    public String getWallpaperTitle() {
        return this.mWallpaperTitle;
    }

    public WallpaperType getWallpaperType() {
        return this.mImageType;
    }

    public String getWallpaperWebType() {
        return this.mWallpaperWebType;
    }

    public String getWallpaperWebUrl() {
        return this.mWallpaperWebUrl;
    }

    public String getWebUrl() {
        return this.mWebUrl;
    }

    public void setBrightnessValue(PictorialBrightness pictorialBrightness) {
        this.mBrightnessValue = pictorialBrightness;
    }

    public void setClickDetailIntent(Intent intent) {
        this.mClickIntent = intent;
    }

    public void setClickText(String str) {
        this.mClickText = str;
    }

    public void setContentColor(int i) {
        this.mContentColor = i;
    }

    public void setContentSize(int i) {
        this.mContentSize = i;
    }

    public void setCopyrightDesc(String str) {
        this.mCopyrightDesc = str;
    }

    public void setDataMap(Map<String, Object> map) {
        this.mDataMap = map;
    }

    public void setDeepLink(String str) {
        this.mDeepLink = str;
    }

    public void setFilePath(String str) {
        this.mFilePath = str;
    }

    public void setFileUnzipUri(String str) {
        this.mFileUnzipUri = str;
    }

    public void setFileUri(Uri uri) {
        this.mFileUri = uri;
    }

    public void setGroupId(String str) {
        this.mGroupId = str;
    }

    public void setGroupType(int i) {
        this.mGroupType = i;
    }

    public void setImageId(String str) {
        this.mImageId = str;
    }

    public void setInstantAppLink(String str) {
        this.mInstantAppLink = str;
    }

    public void setLockscreenWidget(LockscreenWidget lockscreenWidget) {
        this.mLockscreenWidget = lockscreenWidget;
    }

    public void setMoreButtonBg(Drawable drawable) {
        this.mMoreButtonBg = drawable;
    }

    public void setMoreButtonBgRes(int i) {
        this.mMoreButtonBgRes = i;
    }

    public void setMoreButtonColor(int i) {
        this.mMoreButtonColor = i;
    }

    public void setMoreButtonMarginLeft(int i) {
        this.mMoreButtonMarginLeft = i;
    }

    public void setMoreButtonPressedBg(Drawable drawable) {
        this.mMoreButtonPressedBg = drawable;
    }

    public void setMoreButtonPressedBgRes(int i) {
        this.mMoreButtonPressedBgRes = i;
    }

    public void setMoreButtonSize(int i) {
        this.mMoreButtonSize = i;
    }

    public void setNextWallpaper(PictorialWallpaperInfo pictorialWallpaperInfo) {
        this.mNextWallPaper = pictorialWallpaperInfo;
    }

    public void setPackageNameLink(String str) {
        this.mPackageNameLink = str;
    }

    public void setPictorialImageType(int i) {
        this.mPictorialImageType = i;
    }

    public void setSourceName(String str) {
        this.mSourceName = str;
    }

    public void setTextContent(String str) {
        this.mContentString = str;
    }

    public void setTextTitle(String str) {
        this.mTitle = str;
    }

    public void setTitleColor(int i) {
        this.mTitleColor = i;
    }

    public void setTitleSize(int i) {
        this.mTitleSize = i;
    }

    public void setTransparent(String str) {
        this.mTransparent = str;
    }

    public void setTxtGroupId(String str) {
        this.mTxtGroupId = str;
    }

    public void setWallpaperDrawable(Drawable drawable) {
        this.mWallpaperDrawable = drawable;
    }

    public void setWallpaperId(String str) {
        this.mWallpaperId = str;
    }

    public void setWallpaperTitle(String str) {
        this.mWallpaperTitle = str;
    }

    public void setWallpaperType(WallpaperType wallpaperType) {
        this.mImageType = wallpaperType;
    }

    public void setWallpaperWebType(String str) {
        this.mWallpaperWebType = str;
    }

    public void setWallpaperWebUrl(String str) {
        this.mWallpaperWebUrl = str;
    }

    public void setWebUrl(String str) {
        this.mWebUrl = str;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("PictorialWallpaperInfo { mImageId='");
        sb.append(this.mImageId);
        sb.append("', mGroupId='");
        sb.append(this.mGroupId);
        sb.append("', mGroupType=");
        sb.append(this.mGroupType);
        sb.append(", mFilePath='");
        sb.append(this.mFilePath);
        sb.append("', mTitle='");
        sb.append(this.mTitle);
        sb.append("', mContentString='");
        sb.append(this.mContentString);
        sb.append("', mWebUrl='");
        sb.append(this.mWebUrl);
        sb.append("', mDeepLink='");
        sb.append(this.mDeepLink);
        sb.append("', mInstantAppLink='");
        sb.append(this.mInstantAppLink);
        sb.append("', mClickText='");
        sb.append(this.mClickText);
        sb.append("', mCopyrightDesc='");
        sb.append(this.mCopyrightDesc);
        sb.append("', mClickIntent=");
        sb.append(this.mClickIntent);
        sb.append(", mTitleSize=");
        sb.append(this.mTitleSize);
        sb.append(", mTitleColor=");
        sb.append(this.mTitleColor);
        sb.append(", mContentSize=");
        sb.append(this.mContentSize);
        sb.append(", mContentColor=");
        sb.append(this.mContentColor);
        sb.append(", mMoreButtonBgRes=");
        sb.append(this.mMoreButtonBgRes);
        sb.append(", mMoreButtonPressedBgRes=");
        sb.append(this.mMoreButtonPressedBgRes);
        sb.append(", mMoreButtonBg=");
        sb.append(this.mMoreButtonBg != null);
        sb.append(", mMoreButtonPressedBg=");
        sb.append(this.mMoreButtonPressedBg != null);
        sb.append(", mWallpaperDrawable=");
        sb.append(this.mWallpaperDrawable != null);
        sb.append(", mMoreButtonSize=");
        sb.append(this.mMoreButtonSize);
        sb.append(", mMoreButtonColor=");
        sb.append(this.mMoreButtonColor);
        sb.append(", mMoreButtonMarginLeft=");
        sb.append(this.mMoreButtonMarginLeft);
        sb.append(", mImageType=");
        sb.append(this.mImageType);
        sb.append(", mFileUnzipUri=");
        sb.append(this.mFileUnzipUri);
        sb.append(", mPictorialImageType=");
        sb.append(this.mPictorialImageType);
        sb.append(", mBrightnessValue=");
        sb.append(this.mBrightnessValue);
        sb.append(", mNextWallpaperImageId='");
        if (this.mNextWallPaper != null) {
            str = this.mNextWallPaper.getImageId() + ", " + this.mNextWallPaper.getTextContent();
        } else {
            str = "null";
        }
        return DebugInfo$.ExternalSyntheticOutline0.m(sb, str, "'}");
    }

    public PictorialWallpaperInfo() {
        setMType(1);
    }
}
