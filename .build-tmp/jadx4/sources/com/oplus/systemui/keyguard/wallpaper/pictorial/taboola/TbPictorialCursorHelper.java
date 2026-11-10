package com.oplus.systemui.keyguard.wallpaper.pictorial.taboola;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.android.keyguard.widget.LockscreenWidget;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialGuideEntity;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialCursorHelper;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TbPictorialCursorHelper extends PictorialCursorHelper {
    public static void fitPictorialDetailsIntent(TbPictorialWallpaperInfo tbPictorialWallpaperInfo) {
        Intent intent = new Intent();
        intent.putExtra("imageId", tbPictorialWallpaperInfo.getImageId());
        intent.putExtra("type", 4);
        intent.setAction(((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getActionSeePictorialDetails());
        tbPictorialWallpaperInfo.setClickDetailIntent(intent);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public final PictorialGuideEntity getPictorialGuidInfo(Context context, ContentResolver contentResolver, Uri uri) {
        try {
            Cursor cursorQuery = contentResolver.query(uri, null, null, null, null);
            try {
                if (cursorQuery == null) {
                    KgdLogUtil.i("TbPictorialCursorHelper", "getPictorialGuidInfo cursor is null");
                    if (cursorQuery == null) {
                        return null;
                    }
                } else if (cursorQuery.moveToFirst()) {
                    PictorialGuideEntity pictorialGuideEntity = new PictorialGuideEntity();
                    int columnIndex = cursorQuery.getColumnIndex("guide_content");
                    if (columnIndex == -1 || TextUtils.isEmpty(cursorQuery.getString(columnIndex))) {
                        pictorialGuideEntity.setContent("");
                    } else {
                        pictorialGuideEntity.setContent(((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getContext().getResources().getString(2131956777));
                    }
                    pictorialGuideEntity.setTextSize(PictorialCursorHelper.getSize(cursorQuery, context, "guide_size", 16));
                    KgdLogUtil.i("TbPictorialCursorHelper", "getPictorialGuidInfo successful guideEntity=" + pictorialGuideEntity);
                    cursorQuery.close();
                    return pictorialGuideEntity;
                }
                cursorQuery.close();
                return null;
            } catch (Throwable th) {
                if (cursorQuery == null) {
                    throw th;
                }
                try {
                    cursorQuery.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        } catch (Exception e) {
            KgdLogUtil.e("TbPictorialCursorHelper", "getPictorialGuidInfo get an exception = ", e);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:126:0x044f  */
    /* JADX WARN: Code duplicated, block: B:131:0x046b  */
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public final List getPictorialImageDataList(OplusPictorialCursorHelper.Params params) throws Throwable {
        Cursor cursor;
        String str;
        ArrayList arrayList;
        Cursor cursor2;
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        Context context;
        int i;
        ContentResolver contentResolver = params.getContentResolver();
        Uri imageUri = params.getImageUri();
        PictorialWallpaperLoader pictorialWallpaperLoader = params.getPictorialWallpaperLoader();
        Context context2 = params.getContext();
        params.getDefaultDensityContext();
        params.getPictorialResources();
        String str2 = "taglineCtaBackgroundColor";
        String str3 = "taglineBackgroundColor";
        String str4 = "liftImg";
        String str5 = "subTitle";
        String str6 = "logoText";
        String str7 = "logoImg";
        String str8 = "TbPictorialCursorHelper";
        TbPictorialWallpaperInfo tbPictorialWallpaperInfo = null;
        if (contentResolver != null) {
            ArrayList arrayList2 = new ArrayList();
            try {
                try {
                    Cursor cursorQuery = contentResolver.query(imageUri, null, null, null);
                    try {
                        if (cursorQuery != null) {
                            TbPictorialWallpaperInfo tbPictorialWallpaperInfo2 = null;
                            int i2 = 0;
                            while (cursorQuery.moveToNext()) {
                                try {
                                    try {
                                        int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("imageId");
                                        int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("groupId");
                                        PictorialWallpaperLoader pictorialWallpaperLoader2 = pictorialWallpaperLoader;
                                        int columnIndexOrThrow3 = cursorQuery.getColumnIndexOrThrow("groupType");
                                        TbPictorialWallpaperInfo tbPictorialWallpaperInfo3 = tbPictorialWallpaperInfo2;
                                        int columnIndexOrThrow4 = cursorQuery.getColumnIndexOrThrow("fileUri");
                                        ArrayList arrayList3 = arrayList2;
                                        try {
                                            int columnIndexOrThrow5 = cursorQuery.getColumnIndexOrThrow("filePath");
                                            TbPictorialWallpaperInfo tbPictorialWallpaperInfo4 = tbPictorialWallpaperInfo;
                                            int columnIndexOrThrow6 = cursorQuery.getColumnIndexOrThrow("clickText");
                                            Context context3 = context2;
                                            int columnIndexOrThrow7 = cursorQuery.getColumnIndexOrThrow("webUrl");
                                            int i3 = i2;
                                            int columnIndexOrThrow8 = cursorQuery.getColumnIndexOrThrow("instantApp");
                                            String str9 = str8;
                                            try {
                                                int columnIndexOrThrow9 = cursorQuery.getColumnIndexOrThrow("title");
                                                String str10 = str2;
                                                int columnIndexOrThrow10 = cursorQuery.getColumnIndexOrThrow("content");
                                                String str11 = str3;
                                                int columnIndexOrThrow11 = cursorQuery.getColumnIndexOrThrow("titleSize");
                                                String str12 = str4;
                                                int columnIndexOrThrow12 = cursorQuery.getColumnIndexOrThrow("titleColor");
                                                String str13 = str5;
                                                int columnIndex = cursorQuery.getColumnIndex("contentType");
                                                String str14 = str6;
                                                int columnIndexOrThrow13 = cursorQuery.getColumnIndexOrThrow("contentSize");
                                                String str15 = str7;
                                                int columnIndexOrThrow14 = cursorQuery.getColumnIndexOrThrow("contentColor");
                                                String string7 = cursorQuery.getString(columnIndexOrThrow);
                                                String string8 = cursorQuery.getString(columnIndexOrThrow2);
                                                String string9 = cursorQuery.getString(columnIndexOrThrow3);
                                                String string10 = cursorQuery.getString(columnIndexOrThrow4);
                                                String string11 = cursorQuery.getString(columnIndexOrThrow5);
                                                String string12 = cursorQuery.getString(columnIndexOrThrow6);
                                                String string13 = cursorQuery.getString(columnIndexOrThrow7);
                                                String string14 = cursorQuery.getString(columnIndexOrThrow8);
                                                String string15 = cursorQuery.getString(columnIndexOrThrow9);
                                                String string16 = cursorQuery.getString(columnIndexOrThrow10);
                                                int i4 = cursorQuery.getInt(columnIndexOrThrow11);
                                                String string17 = cursorQuery.getString(columnIndexOrThrow12);
                                                int i5 = cursorQuery.getInt(columnIndexOrThrow13);
                                                String string18 = cursorQuery.getString(columnIndexOrThrow14);
                                                if (columnIndex == -1) {
                                                    columnIndex = -1;
                                                }
                                                TbPictorialWallpaperInfo tbPictorialWallpaperInfo5 = new TbPictorialWallpaperInfo();
                                                int i6 = columnIndex;
                                                if (cursorQuery.getColumnIndex(str15) != -1) {
                                                    try {
                                                        string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(str15));
                                                        tbPictorialWallpaperInfo5.setLogoImg(string);
                                                    } catch (IllegalArgumentException e) {
                                                        e = e;
                                                        cursor = cursorQuery;
                                                        arrayList = arrayList3;
                                                        str = str9;
                                                    }
                                                } else {
                                                    string = "";
                                                }
                                                String str16 = string;
                                                if (cursorQuery.getColumnIndex(str14) != -1) {
                                                    string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(str14));
                                                    tbPictorialWallpaperInfo5.setLogoText(string2);
                                                } else {
                                                    string2 = "";
                                                }
                                                String str17 = string2;
                                                if (cursorQuery.getColumnIndex(str13) != -1) {
                                                    string3 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(str13));
                                                    tbPictorialWallpaperInfo5.setSubtitle(string3);
                                                } else {
                                                    string3 = "";
                                                }
                                                String str18 = string3;
                                                if (cursorQuery.getColumnIndex(str12) != -1) {
                                                    string4 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(str12));
                                                    tbPictorialWallpaperInfo5.setLiftIcon(string4);
                                                } else {
                                                    string4 = "";
                                                }
                                                String str19 = string4;
                                                if (cursorQuery.getColumnIndex(str11) != -1) {
                                                    string5 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(str11));
                                                    tbPictorialWallpaperInfo5.setTaglineBackgroundColor(string5);
                                                } else {
                                                    string5 = "";
                                                }
                                                String str20 = string5;
                                                if (cursorQuery.getColumnIndex(str10) != -1) {
                                                    string6 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(str10));
                                                    tbPictorialWallpaperInfo5.setTaglineCtaBackgroundColor(string6);
                                                } else {
                                                    string6 = "";
                                                }
                                                StringBuilder sb = new StringBuilder();
                                                cursor2 = cursorQuery;
                                                try {
                                                    try {
                                                        sb.append("cursor.moveToFirst()=id");
                                                        sb.append(string7);
                                                        sb.append("=groupId=");
                                                        sb.append(string8);
                                                        sb.append("=groupType=");
                                                        sb.append(string9);
                                                        sb.append("=fileUri=");
                                                        sb.append(string10);
                                                        sb.append("=filePath=");
                                                        sb.append(string11);
                                                        sb.append("=clickText=");
                                                        sb.append(string12);
                                                        sb.append("=webUrl=");
                                                        sb.append(string13);
                                                        sb.append("=instantApp=");
                                                        sb.append(string14);
                                                        sb.append("=title=");
                                                        sb.append(string15);
                                                        sb.append("=content=");
                                                        sb.append(string16);
                                                        sb.append("=titleSize=");
                                                        sb.append(i4);
                                                        sb.append("=titleColor=");
                                                        sb.append(string17);
                                                        sb.append("=contentSize=");
                                                        sb.append(i5);
                                                        sb.append("=contentColor=");
                                                        sb.append(string18);
                                                        sb.append("=logoImg=");
                                                        sb.append(str16);
                                                        sb.append("=logoText=");
                                                        sb.append(str17);
                                                        sb.append("=subTitle=");
                                                        sb.append(str18);
                                                        sb.append("=lifeIcon=");
                                                        sb.append(str19);
                                                        sb.append("=taglineBackgroundColor=");
                                                        sb.append(str20);
                                                        sb.append("=taglineCtaBackgroundColor=");
                                                        sb.append(string6);
                                                        str = str9;
                                                        try {
                                                            KgdLogUtil.i(str, sb.toString());
                                                            tbPictorialWallpaperInfo5.setImageId(string7);
                                                            tbPictorialWallpaperInfo5.setGroupId(string8);
                                                            tbPictorialWallpaperInfo5.setFileUri(Uri.parse(string10));
                                                            tbPictorialWallpaperInfo5.setFilePath(string11);
                                                            tbPictorialWallpaperInfo5.setClickText(string12);
                                                            tbPictorialWallpaperInfo5.setWebUrl(string13);
                                                            tbPictorialWallpaperInfo5.setTextTitle(string15);
                                                            tbPictorialWallpaperInfo5.setTextContent(string16);
                                                            tbPictorialWallpaperInfo5.setTitleSize(i4);
                                                            tbPictorialWallpaperInfo5.setContentSize(i5);
                                                            tbPictorialWallpaperInfo5.setContentType(i6);
                                                            fitPictorialDetailsIntent(tbPictorialWallpaperInfo5);
                                                            try {
                                                                Bundle extras = cursor2.getExtras();
                                                                extras.setClassLoader(LockscreenWidget.class.getClassLoader());
                                                                tbPictorialWallpaperInfo5.setLockscreenWidget((LockscreenWidget) extras.getParcelable("lockscreenWidget", LockscreenWidget.class));
                                                            } catch (Exception e2) {
                                                                KgdLogUtil.i(str, "get LockscreenWidget error:" + e2.getMessage());
                                                            }
                                                            Bundle bundle = new Bundle();
                                                            if (i3 % 2 == 0) {
                                                                tbPictorialWallpaperInfo5.setWallpaperType(PictorialWallpaperInfo.WallpaperType.COVER);
                                                                if (i3 == 0) {
                                                                    StringBuilder sb2 = new StringBuilder();
                                                                    sb2.append("");
                                                                    i = i3;
                                                                    sb2.append(i);
                                                                    ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) bundle.getParcelable(sb2.toString());
                                                                    pictorialWallpaperLoader2.getClass();
                                                                    context = context3;
                                                                    tbPictorialWallpaperInfo5.setWallpaperDrawable(PictorialWallpaperLoader.loadWallpaperSync(context, tbPictorialWallpaperInfo5, parcelFileDescriptor, true));
                                                                } else {
                                                                    context = context3;
                                                                    i = i3;
                                                                }
                                                                if (PictorialUtils.isSameImage(tbPictorialWallpaperInfo4, tbPictorialWallpaperInfo5)) {
                                                                    tbPictorialWallpaperInfo5.copyWallpaperDrawable(tbPictorialWallpaperInfo4);
                                                                }
                                                                arrayList = arrayList3;
                                                                try {
                                                                    arrayList.add(tbPictorialWallpaperInfo5);
                                                                    if (tbPictorialWallpaperInfo5.getImageId().contains("BuiltInItem_")) {
                                                                        ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialFactory().getPictorialBuriedHelper().uploadActiveEvent(context, "taboola_active_0");
                                                                    } else {
                                                                        ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialFactory().getPictorialBuriedHelper().uploadActiveEvent(context, "taboola_active_1");
                                                                    }
                                                                    KgdLogUtil.i(str, "getPictorialImageDataList result coverInfo=" + tbPictorialWallpaperInfo5.toString());
                                                                    tbPictorialWallpaperInfo2 = tbPictorialWallpaperInfo5;
                                                                    tbPictorialWallpaperInfo = null;
                                                                } catch (IllegalArgumentException e3) {
                                                                    e = e3;
                                                                    cursor = cursor2;
                                                                    KgdLogUtil.i(str, "Exception = " + e.getMessage());
                                                                    if (cursor != null) {
                                                                        cursor.close();
                                                                    }
                                                                    KgdLogUtil.i(str, "getPictorialImageDataList result size" + arrayList.size());
                                                                    return arrayList;
                                                                }
                                                            } else {
                                                                arrayList = arrayList3;
                                                                tbPictorialWallpaperInfo = tbPictorialWallpaperInfo4;
                                                                context = context3;
                                                                i = i3;
                                                                if (i % 2 != 1 || tbPictorialWallpaperInfo3 == null) {
                                                                    tbPictorialWallpaperInfo2 = tbPictorialWallpaperInfo3;
                                                                } else {
                                                                    tbPictorialWallpaperInfo5.setWallpaperType(PictorialWallpaperInfo.WallpaperType.NEXT);
                                                                    tbPictorialWallpaperInfo3.setNextWallpaper(tbPictorialWallpaperInfo5);
                                                                    if (i == 1) {
                                                                        ParcelFileDescriptor parcelFileDescriptor2 = (ParcelFileDescriptor) bundle.getParcelable("" + i);
                                                                        pictorialWallpaperLoader2.getClass();
                                                                        tbPictorialWallpaperInfo5.setWallpaperDrawable(PictorialWallpaperLoader.loadWallpaperSync(context, tbPictorialWallpaperInfo5, parcelFileDescriptor2, false));
                                                                    }
                                                                    KgdLogUtil.i(str, "getPictorialImageDataList result preloadInfo=" + tbPictorialWallpaperInfo5.toString());
                                                                    tbPictorialWallpaperInfo = tbPictorialWallpaperInfo5;
                                                                    tbPictorialWallpaperInfo2 = null;
                                                                }
                                                            }
                                                            i2 = i + 1;
                                                            context2 = context;
                                                            arrayList2 = arrayList;
                                                            str8 = str;
                                                            str2 = str10;
                                                            pictorialWallpaperLoader = pictorialWallpaperLoader2;
                                                            str3 = str11;
                                                            str4 = str12;
                                                            str5 = str13;
                                                            str6 = str14;
                                                            str7 = str15;
                                                            cursorQuery = cursor2;
                                                        } catch (IllegalArgumentException e4) {
                                                            e = e4;
                                                            arrayList = arrayList3;
                                                            cursor = cursor2;
                                                            KgdLogUtil.i(str, "Exception = " + e.getMessage());
                                                            if (cursor != null) {
                                                                cursor.close();
                                                            }
                                                            KgdLogUtil.i(str, "getPictorialImageDataList result size" + arrayList.size());
                                                            return arrayList;
                                                        }
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        cursor = cursor2;
                                                        if (cursor != null) {
                                                            cursor.close();
                                                        }
                                                        throw th;
                                                    }
                                                } catch (IllegalArgumentException e5) {
                                                    e = e5;
                                                    arrayList = arrayList3;
                                                    str = str9;
                                                    cursor = cursor2;
                                                    KgdLogUtil.i(str, "Exception = " + e.getMessage());
                                                    if (cursor != null) {
                                                        cursor.close();
                                                    }
                                                    KgdLogUtil.i(str, "getPictorialImageDataList result size" + arrayList.size());
                                                    return arrayList;
                                                }
                                            } catch (IllegalArgumentException e6) {
                                                e = e6;
                                                cursor2 = cursorQuery;
                                            }
                                        } catch (IllegalArgumentException e7) {
                                            e = e7;
                                            cursor2 = cursorQuery;
                                            str = str8;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        cursor2 = cursorQuery;
                                    }
                                } catch (IllegalArgumentException e8) {
                                    e = e8;
                                    cursor2 = cursorQuery;
                                    str = str8;
                                    arrayList = arrayList2;
                                }
                            }
                            str = str8;
                            arrayList = arrayList2;
                            cursorQuery.close();
                            KgdLogUtil.i(str, "getPictorialImageDataList result size" + arrayList.size());
                            return arrayList;
                        }
                        try {
                            KgdLogUtil.i("TbPictorialCursorHelper", "getPictorialImageDataList,cursor is null");
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                        } catch (IllegalArgumentException e9) {
                            e = e9;
                            cursor = cursorQuery;
                            str = "TbPictorialCursorHelper";
                            arrayList = arrayList2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        cursor = cursorQuery;
                    }
                } catch (IllegalArgumentException e10) {
                    e = e10;
                    str = "TbPictorialCursorHelper";
                    arrayList = arrayList2;
                    cursor = null;
                } catch (Throwable th4) {
                    th = th4;
                    cursor = null;
                }
                KgdLogUtil.i(str, "Exception = " + e.getMessage());
                if (cursor != null) {
                    cursor.close();
                }
                KgdLogUtil.i(str, "getPictorialImageDataList result size" + arrayList.size());
                return arrayList;
            } catch (Throwable th5) {
                th = th5;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public final boolean getPictorialSupportTaglineStatus(ContentResolver contentResolver, Uri uri) {
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        if (pictorialMediator == null) {
            return false;
        }
        return PictorialUtils.isWidgetTaglineSupport(pictorialMediator.getContext(), pictorialMediator.getPictorialPackageName());
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public final void insert(ContentResolver contentResolver, Uri uri, ContentValues contentValues) {
        if (contentResolver == null || uri == null || contentValues.isEmpty()) {
            return;
        }
        contentResolver.insert(uri, contentValues);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public final void reportStatisticData(ContentResolver contentResolver, Uri uri, String str, Bundle bundle) {
        if (contentResolver == null || uri == null || bundle.isEmpty()) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        for (String str2 : bundle.keySet()) {
            contentValues.put(str2, String.valueOf(bundle.get(str2)));
        }
        contentResolver.insert(uri, contentValues);
    }
}
