package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialGuideEntity;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageViewInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialSpaceBean;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialCursorHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PictorialCursorHelper {
    public static int getColor(Cursor cursor, String str, String str2) {
        try {
            int color = Color.parseColor(cursor.getString(cursor.getColumnIndex(str)));
            return color == -1 ? Color.parseColor(str2) : color;
        } catch (Exception unused) {
            return Color.parseColor(str2);
        }
    }

    public static Map getCursorData(Cursor cursor) {
        HashMap map = new HashMap(cursor.getColumnCount());
        for (String str : cursor.getColumnNames()) {
            int columnIndex = cursor.getColumnIndex(str);
            int type = cursor.getType(columnIndex);
            if (type == 0) {
                map.put(str, null);
            } else if (type == 1) {
                map.put(str, Long.valueOf(cursor.getLong(columnIndex)));
            } else if (type == 2) {
                map.put(str, Float.valueOf(cursor.getFloat(columnIndex)));
            } else if (type == 3) {
                map.put(str, cursor.getString(columnIndex));
            } else if (type == 4) {
                map.put(str, cursor.getBlob(columnIndex));
            }
        }
        return map;
    }

    public static Drawable getPictorialDrawable(Context context, Resources resources, int i, int i2) {
        if (resources == null) {
            return context.getResources().getDrawable(i2, null);
        }
        try {
            Drawable drawable = resources.getDrawable(i, null);
            return drawable == null ? context.getResources().getDrawable(i2, null) : drawable;
        } catch (Exception unused) {
            return context.getResources().getDrawable(i2, null);
        }
    }

    public static int getSize(JSONObject jSONObject, Context context, String str, int i) {
        try {
            int iOptInt = jSONObject.optInt(str);
            return iOptInt > 0 ? PictorialUtils.dp2px(context, iOptInt) : PictorialUtils.dp2px(context, i);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getPictorialActivateNotShow(ContentResolver contentResolver, Uri uri) {
        return -2;
    }

    public PictorialGuideEntity getPictorialGuidInfo(Context context, ContentResolver contentResolver, Uri uri) {
        return null;
    }

    public abstract List getPictorialImageDataList(OplusPictorialCursorHelper.Params params);

    public PictorialMessageViewInfo getPictorialMessageViewInfo(ContentResolver contentResolver, Uri uri, Context context, Resources resources) {
        return null;
    }

    public PictorialSpaceBean getPictorialSpaceBean(Context context, ContentResolver contentResolver, Uri uri) {
        return null;
    }

    public boolean getPictorialSupportTaglineStatus(ContentResolver contentResolver, Uri uri) {
        return false;
    }

    public abstract void insert(ContentResolver contentResolver, Uri uri, ContentValues contentValues);

    public abstract void reportStatisticData(ContentResolver contentResolver, Uri uri, String str, Bundle bundle);

    public void update(ContentResolver contentResolver, Uri uri, ContentValues contentValues) {
    }

    public static int getSize(Cursor cursor, Context context, String str, int i) {
        int i2 = cursor.getInt(cursor.getColumnIndex(str));
        if (i2 > 0) {
            return PictorialUtils.dp2px(context, i2);
        }
        return PictorialUtils.dp2px(context, i);
    }
}
