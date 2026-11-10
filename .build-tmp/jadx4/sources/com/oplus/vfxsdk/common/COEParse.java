package com.oplus.vfxsdk.common;

import android.os.Trace;
import android.util.Base64;
import android.util.Log;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.qs.slider.detail.data.EarphoneController;
import com.oplus.systemui.seedlingservice.utils.SeedlingMediaDataHandleUtils;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.dynamicevent.DynamicEventRepository;
import com.oplus.vfxsdk.common.perf.FileStat;
import com.oplus.vfxsdk.common.perf.StatUtil;
import com.oplus.zoom.common.ZoomDCSManager;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class COEParse {
    public static final Companion Companion = new Companion();
    private static final Map<String, String> builtinAnimLineMap = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to("_translate_", "builtin_translate"), TuplesKt.to("_scale_", "builtin_scale"), TuplesKt.to("_layout_", "builtin_layout"), TuplesKt.to("_rotate_", "builtin_rotate")});
    private final String TAG = "COEParse";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Map<String, String> getBuiltinAnimLineMap() {
            return COEParse.builtinAnimLineMap;
        }
    }

    private final int getId(JSONObject jSONObject) {
        try {
            return jSONObject.optInt(PictorialAppInfo.ID);
        } catch (Error unused) {
            return 0;
        }
    }

    private final boolean parseAnimKey(JSONObject jSONObject, String str, ArrayList<AnimKey> arrayList) throws JSONException {
        if (jSONObject.optJSONArray(str) == null) {
            return false;
        }
        int length = jSONObject.getJSONArray(str).length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONObject.getJSONArray(str).getJSONObject(i);
            float fOptDouble = (float) jSONObject2.optDouble("time");
            float fOptDouble2 = (float) jSONObject2.optDouble(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
            String strOptString = jSONObject2.optString("type");
            String strOptString2 = jSONObject2.optString("expr", null);
            String strOptString3 = jSONObject2.optString("ipol", "bezier");
            float[] floatArray = parseFloatArray(jSONObject2, "bezier", COEParseKt.getDefaultBezier());
            float[] floatArray2 = parseFloatArray(jSONObject2, "spring", COEParseKt.getDefaultSpring());
            COEParseKt.correctBezier(floatArray);
            strOptString.getClass();
            strOptString3.getClass();
            arrayList.add(new AnimKey(fOptDouble, fOptDouble2, strOptString, fOptDouble2, strOptString3, floatArray, floatArray2, strOptString2, null, 256, null));
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001c  */
    /* JADX WARN: Code duplicated, block: B:78:0x029a  */
    private final HashMap<String, AnimatorValue> parseAnimator(JSONArray jSONArray) throws JSONException {
        int i;
        int i2;
        AnimLine animLine;
        JSONArray jSONArrayOptJSONArray;
        JSONArray jSONArray2 = jSONArray;
        HashMap<String, AnimatorValue> map = jSONArray2 != null ? new HashMap<>() : null;
        if (jSONArray2 != null) {
            int length = jSONArray2.length();
            int i3 = 0;
            while (i3 < length) {
                JSONObject jSONObjectOptJSONObject = jSONArray2.optJSONObject(i3);
                if (jSONObjectOptJSONObject == null) {
                    i = length;
                    i2 = i3;
                } else {
                    int id = getId(jSONObjectOptJSONObject);
                    String strOptString = jSONObjectOptJSONObject.optString(EarphoneController.COLUMN_NAME);
                    float fOptDouble = (float) jSONObjectOptJSONObject.optDouble("duration");
                    ArrayList arrayList = new ArrayList();
                    JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("animLines");
                    if (jSONArrayOptJSONArray2 == null) {
                        i = length;
                        i2 = i3;
                    } else {
                        int length2 = jSONArrayOptJSONArray2.length();
                        int i4 = 0;
                        while (i4 < length2) {
                            int i5 = length;
                            JSONObject jSONObject = jSONObjectOptJSONObject.getJSONArray("animLines").getJSONObject(i4);
                            String strOptString2 = jSONObject.optString("nodeId");
                            String strOptString3 = jSONObject.optString("key");
                            int i6 = i3;
                            int i7 = id;
                            String str = strOptString;
                            String str2 = (String) CollectionsKt___CollectionsKt.last(StringsKt__StringsKt.split$default(jSONObject.optString(EarphoneController.COLUMN_NAME), new char[]{'.'}, 6));
                            UniformType uniformType = parseUniformType(jSONObject);
                            float fOptDouble2 = (float) jSONObject.optDouble("lastTime");
                            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("animKeys");
                            if (jSONObjectOptJSONObject2 != null) {
                                Map<String, String> map2 = builtinAnimLineMap;
                                if (map2.get(strOptString3) != null) {
                                    String str3 = map2.get(strOptString3);
                                    str3.getClass();
                                    str2 = str3;
                                }
                                String str4 = str2;
                                switch (uniformType.ordinal()) {
                                    case 0:
                                    case 1:
                                    case 2:
                                    case 3:
                                        ArrayList arrayList2 = new ArrayList();
                                        ChannelData channelData = new ChannelData();
                                        UniformType uniformType2 = UniformType.Color;
                                        if (parseAnimKey(jSONObjectOptJSONObject2, uniformType == uniformType2 ? "r" : "x", arrayList2)) {
                                            strOptString2.getClass();
                                            strOptString3.getClass();
                                            arrayList.add(new AnimLine(strOptString2, strOptString3, str4, uniformType, 0, fOptDouble2, (IKey[]) arrayList2.toArray(new IKey[0]), channelData));
                                        }
                                        ArrayList arrayList3 = new ArrayList();
                                        if (parseAnimKey(jSONObjectOptJSONObject2, uniformType == uniformType2 ? "g" : "y", arrayList3)) {
                                            strOptString2.getClass();
                                            strOptString3.getClass();
                                            arrayList.add(new AnimLine(strOptString2, strOptString3, str4, uniformType, 1, fOptDouble2, (IKey[]) arrayList3.toArray(new IKey[0]), channelData));
                                        }
                                        if (uniformType == UniformType.Vec3 || uniformType == UniformType.Vec4 || uniformType == uniformType2) {
                                            ArrayList arrayList4 = new ArrayList();
                                            if (parseAnimKey(jSONObjectOptJSONObject2, uniformType == uniformType2 ? "b" : "z", arrayList4)) {
                                                strOptString2.getClass();
                                                strOptString3.getClass();
                                                arrayList.add(new AnimLine(strOptString2, strOptString3, str4, uniformType, 2, fOptDouble2, (IKey[]) arrayList4.toArray(new IKey[0]), channelData));
                                            }
                                        }
                                        if (uniformType == UniformType.Vec4 || uniformType == uniformType2) {
                                            ArrayList arrayList5 = new ArrayList();
                                            if (parseAnimKey(jSONObjectOptJSONObject2, uniformType == uniformType2 ? "a" : "w", arrayList5)) {
                                                strOptString2.getClass();
                                                strOptString3.getClass();
                                                arrayList.add(new AnimLine(strOptString2, strOptString3, str4, uniformType, 3, fOptDouble2, (IKey[]) arrayList5.toArray(new IKey[0]), channelData));
                                            }
                                        }
                                        break;
                                    case 4:
                                    case 5:
                                    case 6:
                                        ArrayList<AnimKey> arrayList6 = new ArrayList<>();
                                        parseAnimKey(jSONObjectOptJSONObject2, SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE, arrayList6);
                                        strOptString2.getClass();
                                        strOptString3.getClass();
                                        arrayList.add(new AnimLine(strOptString2, strOptString3, str4, uniformType, 0, fOptDouble2, (IKey[]) arrayList6.toArray(new IKey[0]), null));
                                        break;
                                }
                            }
                            i4++;
                            length = i5;
                            i3 = i6;
                            id = i7;
                            strOptString = str;
                        }
                        i = length;
                        i2 = i3;
                        int i8 = id;
                        String str5 = strOptString;
                        JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject("eventLine");
                        if (jSONObjectOptJSONObject3 != null) {
                            String strOptString4 = jSONObjectOptJSONObject3.optString("nodeId");
                            float fOptDouble3 = (float) jSONObjectOptJSONObject3.optDouble("lastTime");
                            JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject3.optJSONObject("animKeys");
                            if (jSONObjectOptJSONObject4 == null || (jSONArrayOptJSONArray = jSONObjectOptJSONObject4.optJSONArray("event")) == null) {
                                animLine = null;
                            } else {
                                ArrayList arrayList7 = new ArrayList();
                                int length3 = jSONArrayOptJSONArray.length();
                                for (int i9 = 0; i9 < length3; i9++) {
                                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i9);
                                    float fOptDouble4 = (float) jSONObject2.optDouble("time");
                                    String strOptString5 = jSONObject2.optString("type");
                                    String strOptString6 = jSONObject2.optString(DynamicEventRepository.EXTRA_EVENT_TYPE);
                                    String strOptString7 = jSONObject2.optString("action");
                                    String strOptString8 = jSONObject2.optString(EarphoneController.COLUMN_NAME);
                                    JSONArray jSONArrayOptJSONArray3 = jSONObject2.optJSONArray("linkIds");
                                    ArrayList arrayList8 = new ArrayList();
                                    int length4 = jSONArrayOptJSONArray3.length();
                                    for (int i10 = 0; i10 < length4; i10++) {
                                        arrayList8.add(Integer.valueOf(jSONArrayOptJSONArray3.optInt(i10)));
                                    }
                                    String strOptString9 = jSONObject2.optString("custom");
                                    strOptString5.getClass();
                                    strOptString6.getClass();
                                    arrayList7.add(new EventKey(fOptDouble4, strOptString5, 0.0f, 0.0f, strOptString6, strOptString7, strOptString8, (Integer[]) arrayList8.toArray(new Integer[0]), strOptString9));
                                }
                                strOptString4.getClass();
                                animLine = new AnimLine(strOptString4, "event", "event", UniformType.Event, 0, fOptDouble3, (IKey[]) arrayList7.toArray(new IKey[0]), null);
                            }
                        } else {
                            animLine = null;
                        }
                        str5.getClass();
                        AnimatorValue animatorValue = new AnimatorValue(i8, str5, fOptDouble, (AnimLine[]) arrayList.toArray(new AnimLine[0]), animLine);
                        if (map != null) {
                            map.put(str5, animatorValue);
                        }
                    }
                }
                jSONArray2 = jSONArray;
                i3 = i2 + 1;
                length = i;
            }
        }
        return map;
    }

    private final Effect[] parseEffects(JSONArray jSONArray, boolean z, HashMap<String, Object> map) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return new Effect[0];
        }
        int length = jSONArray.length();
        Effect[] effectArr = new Effect[length];
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String strOptString = jSONObject.optString("cs");
            int iOptInt = jSONObject.optInt(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("uniforms");
            HashMap map2 = new HashMap();
            if (jSONArrayOptJSONArray != null) {
                int length2 = jSONArrayOptJSONArray.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                    jSONObject2.getClass();
                    Pair<String, Uniform> uniform = parseUniform(jSONObject2, z, map);
                    map2.put((String) uniform.component1(), (Uniform) uniform.component2());
                }
            }
            effectArr[i] = new Effect(iOptInt, strOptString, map2);
        }
        return effectArr;
    }

    private final HashMap<String, String[]> parseExprRecord(JSONObject jSONObject) throws JSONException {
        Pair pair;
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("exprRecord");
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        IntRange intRangeUntil = RangesKt___RangesKt.until(0, jSONArrayOptJSONArray.length());
        ArrayList arrayList = new ArrayList();
        IntIterator it = intRangeUntil.iterator();
        while (it.hasNext()) {
            JSONArray jSONArray = jSONArrayOptJSONArray.getJSONArray(it.nextInt());
            if (jSONArray.length() >= 2) {
                ArrayList arrayList2 = new ArrayList();
                int length = jSONArray.length();
                for (int i = 1; i < length; i++) {
                    arrayList2.add(jSONArray.get(i).toString());
                }
                pair = TuplesKt.to(jSONArray.getString(0), arrayList2.toArray(new String[0]));
            } else {
                pair = null;
            }
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return (HashMap) MapsKt__MapsKt.toMutableMap(MapsKt__MapsKt.toMap(arrayList));
    }

    private final float[] parseFloatArray(JSONObject jSONObject, String str, float[] fArr) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
        if (jSONArrayOptJSONArray == null) {
            return fArr;
        }
        IntRange intRangeUntil = RangesKt___RangesKt.until(0, jSONArrayOptJSONArray.length());
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil, 10));
        IntIterator it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(Float.valueOf((float) jSONArrayOptJSONArray.getDouble(it.nextInt())));
        }
        return CollectionsKt___CollectionsKt.toFloatArray(arrayList);
    }

    private final Layer parseLayer(JSONObject jSONObject, boolean z, HashMap<String, Object> map, int i) throws JSONException {
        HashMap<String, PassParams[]> params;
        boolean zOptBoolean = jSONObject.optBoolean("enableBlend", false);
        boolean zOptBoolean2 = jSONObject.optBoolean(PictorialAppInfo.TAG_ENABLE, true);
        int iOptInt = jSONObject.optInt("blendSfactor");
        int iOptInt2 = jSONObject.optInt("blendDfactor");
        boolean zOptBoolean3 = jSONObject.optBoolean("enableBlendA", false);
        int iOptInt3 = zOptBoolean3 ? jSONObject.optInt("blendSfactorA") : iOptInt;
        int iOptInt4 = zOptBoolean3 ? jSONObject.optInt("blendDfactorA") : iOptInt2;
        String strOptString = jSONObject.optString(PictorialAppInfo.ID, "");
        String strOptString2 = jSONObject.optString("type", "Node");
        String strOptString3 = jSONObject.optString(EarphoneController.COLUMN_NAME, "");
        int iOptInt5 = jSONObject.optInt("order", i);
        TransformData transform = parseTransform(jSONObject);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("postProcessor");
        PostProcessorData[] postProcessor = jSONArrayOptJSONArray != null ? parsePostProcessor(jSONArrayOptJSONArray) : null;
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("render");
        RendPass[] renders = jSONArrayOptJSONArray2 != null ? parseRenders(jSONArrayOptJSONArray2, z, map) : null;
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("params");
        if (jSONObjectOptJSONObject != null) {
            renders.getClass();
            params = parseParams(jSONObjectOptJSONObject, renders);
        } else {
            params = null;
        }
        JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("animators");
        HashMap<String, AnimatorValue> animator = jSONArrayOptJSONArray3 != null ? parseAnimator(jSONArrayOptJSONArray3) : null;
        strOptString.getClass();
        strOptString3.getClass();
        strOptString2.getClass();
        renders.getClass();
        return new Layer(strOptString, strOptString3, strOptString2, transform, postProcessor, renders, params, animator, zOptBoolean, zOptBoolean2, iOptInt, iOptInt2, iOptInt3, iOptInt4, iOptInt5);
    }

    public static /* synthetic */ Layer parseLayer$default(COEParse cOEParse, JSONObject jSONObject, boolean z, HashMap map, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 0;
        }
        return cOEParse.parseLayer(jSONObject, z, map, i);
    }

    private final HashMap<String, PassParams[]> parseParams(JSONObject jSONObject, RendPass[] rendPassArr) throws JSONException {
        Iterator<String> itKeys;
        String str;
        String str2;
        JSONObject jSONObject2 = jSONObject;
        String str3 = "bezier";
        HashMap map = jSONObject2 != null ? new HashMap() : null;
        if (jSONObject2 != null && (itKeys = jSONObject2.keys()) != null) {
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                JSONArray jSONArray = jSONObject2.getJSONArray(next);
                ArrayList arrayList = new ArrayList();
                int length = jSONArray.length();
                int i = 0;
                while (i < length) {
                    ArrayList arrayList2 = new ArrayList();
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                    Iterator<String> itKeys2 = jSONObject3.keys();
                    while (itKeys2.hasNext()) {
                        String next2 = itKeys2.next();
                        try {
                            Uniform uniform = rendPassArr[i].getUniforms().get(next2);
                            UniformType type = uniform != null ? uniform.getType() : null;
                            JSONObject jSONObject4 = jSONObject3.getJSONObject(next2);
                            int iOptInt = jSONObject4.optInt("delay");
                            long jOptLong = jSONObject4.optLong("duration");
                            String strOptString = jSONObject4.optString("ipol", str3);
                            float[] floatArray = parseFloatArray(jSONObject4, str3, COEParseKt.getDefaultBezier());
                            COEParseKt.correctBezier(floatArray);
                            str = str3;
                            try {
                                float[] floatArray2 = parseFloatArray(jSONObject4, "spring", COEParseKt.getDefaultSpring());
                                Object[] uniformValue = parseUniformValue(type, jSONObject4);
                                if (!(uniformValue.length == 0) && type != null) {
                                    next2.getClass();
                                    strOptString.getClass();
                                    try {
                                        UniformValue uniformValue2 = new UniformValue(next2, type, uniformValue, iOptInt, jOptLong, strOptString, floatArray, floatArray2);
                                        str2 = next2;
                                        try {
                                            arrayList2.add(uniformValue2);
                                        } catch (JSONException e) {
                                            e = e;
                                            e.printStackTrace();
                                            Log.e(Utils.TAG, this.TAG + "=>Error parsing JSON for uniform " + str2, e);
                                        } catch (Exception e2) {
                                            e = e2;
                                            e.printStackTrace();
                                            Log.e(Utils.TAG, this.TAG + "=>Error processing uniform " + str2, e);
                                        }
                                    } catch (JSONException e3) {
                                        e = e3;
                                        str2 = next2;
                                    } catch (Exception e4) {
                                        e = e4;
                                        str2 = next2;
                                    }
                                }
                            } catch (JSONException e5) {
                                e = e5;
                                str2 = next2;
                                e.printStackTrace();
                                Log.e(Utils.TAG, this.TAG + "=>Error parsing JSON for uniform " + str2, e);
                                str3 = str;
                            } catch (Exception e6) {
                                e = e6;
                                str2 = next2;
                                e.printStackTrace();
                                Log.e(Utils.TAG, this.TAG + "=>Error processing uniform " + str2, e);
                                str3 = str;
                            }
                        } catch (JSONException e7) {
                            e = e7;
                            str = str3;
                        } catch (Exception e8) {
                            e = e8;
                            str = str3;
                        }
                        str3 = str;
                    }
                    arrayList.add(new PassParams((UniformValue[]) arrayList2.toArray(new UniformValue[0])));
                    i++;
                    str3 = str3;
                }
                String str4 = str3;
                if (map != null) {
                    next.getClass();
                    map.put(next, arrayList.toArray(new PassParams[0]));
                }
                jSONObject2 = jSONObject;
                str3 = str4;
            }
        }
        return map;
    }

    private final PostProcessorData[] parsePostProcessor(JSONArray jSONArray) throws JSONException {
        if (jSONArray.length() == 0) {
            return null;
        }
        int length = jSONArray.length();
        PostProcessorData[] postProcessorDataArr = new PostProcessorData[length];
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String strOptString = jSONObject.optString(PictorialAppInfo.ID);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("properties");
            HashMap map = new HashMap();
            if (jSONArrayOptJSONArray != null) {
                int length2 = jSONArrayOptJSONArray.length();
                Unit[] unitArr = new Unit[length2];
                for (int i2 = 0; i2 < length2; i2++) {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                    jSONObject2.getClass();
                    Pair uniform$default = parseUniform$default(this, jSONObject2, false, null, 4, null);
                    map.put((String) uniform$default.component1(), (Uniform) uniform$default.component2());
                    unitArr[i2] = Unit.INSTANCE;
                }
            }
            strOptString.getClass();
            postProcessorDataArr[i] = new PostProcessorData(strOptString, map);
        }
        return postProcessorDataArr;
    }

    private final RendPass parseRender(JSONObject jSONObject, boolean z, HashMap<String, Object> map, int i) throws JSONException {
        String str;
        String str2;
        int i2;
        String str3;
        StatusAnim[] statusAnimArr;
        String strOptString = jSONObject.optString("vs");
        String strOptString2 = jSONObject.optString("fs");
        String strOptString3 = jSONObject.optString("cs");
        int iOptInt = jSONObject.optInt("order", i);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("uniforms");
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(ZoomDCSManager.STATUS);
        Effect[] effects = parseEffects(jSONObject.optJSONArray("effects"), z, map);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (jSONArrayOptJSONArray2 != null) {
            int length = jSONArrayOptJSONArray2.length();
            statusAnimArr = new StatusAnim[length];
            int i3 = 0;
            while (i3 < length) {
                JSONObject jSONObject2 = jSONArrayOptJSONArray2.getJSONObject(i3);
                String strOptString4 = jSONObject2.optString(EarphoneController.COLUMN_NAME);
                JSONArray jSONArray = jSONArrayOptJSONArray2;
                JSONArray jSONArrayOptJSONArray3 = jSONObject2.optJSONArray("anims");
                int length2 = jSONArrayOptJSONArray3.length();
                String str4 = strOptString;
                Anim[] animArr = new Anim[length2];
                String str5 = strOptString2;
                int i4 = 0;
                while (i4 < length2) {
                    int i5 = iOptInt;
                    JSONObject jSONObject3 = jSONArrayOptJSONArray3.getJSONObject(i4);
                    JSONArray jSONArray2 = jSONArrayOptJSONArray3;
                    String strOptString5 = jSONObject3.optString("uniformName");
                    String strOptString6 = jSONObject3.optString("type");
                    JSONArray jSONArrayOptJSONArray4 = jSONObject3.optJSONArray("beizer");
                    int i6 = i4;
                    Object objOpt = jSONObject3.opt(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
                    long jOptLong = jSONObject3.optLong("duration");
                    int length3 = jSONArrayOptJSONArray4.length();
                    Float[] fArr = new Float[length3];
                    int i7 = 0;
                    while (i7 < length3) {
                        fArr[i7] = Float.valueOf((float) jSONArrayOptJSONArray4.getDouble(i7));
                        i7++;
                        strOptString3 = strOptString3;
                        length = length;
                    }
                    strOptString5.getClass();
                    strOptString6.getClass();
                    objOpt.getClass();
                    animArr[i6] = new Anim(strOptString5, strOptString6, fArr, objOpt, jOptLong);
                    i4 = i6 + 1;
                    iOptInt = i5;
                    jSONArrayOptJSONArray3 = jSONArray2;
                }
                strOptString4.getClass();
                statusAnimArr[i3] = new StatusAnim(strOptString4, animArr);
                i3++;
                jSONArrayOptJSONArray2 = jSONArray;
                strOptString = str4;
                strOptString2 = str5;
            }
            str = strOptString;
            str2 = strOptString2;
            i2 = iOptInt;
            str3 = strOptString3;
        } else {
            str = strOptString;
            str2 = strOptString2;
            i2 = iOptInt;
            str3 = strOptString3;
            statusAnimArr = null;
        }
        StatusAnim[] statusAnimArr2 = statusAnimArr;
        if (jSONArrayOptJSONArray != null) {
            int length4 = jSONArrayOptJSONArray.length();
            for (int i8 = 0; i8 < length4; i8++) {
                JSONObject jSONObject4 = jSONArrayOptJSONArray.getJSONObject(i8);
                jSONObject4.getClass();
                Pair<String, Uniform> uniform = parseUniform(jSONObject4, z, map);
                linkedHashMap.put((String) uniform.component1(), (Uniform) uniform.component2());
            }
        }
        str.getClass();
        str2.getClass();
        return new RendPass(str, str2, i2, linkedHashMap, statusAnimArr2, str3, effects);
    }

    public static /* synthetic */ RendPass parseRender$default(COEParse cOEParse, JSONObject jSONObject, boolean z, HashMap map, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 0;
        }
        return cOEParse.parseRender(jSONObject, z, map, i);
    }

    private final RendPass[] parseRenders(JSONArray jSONArray, boolean z, HashMap<String, Object> map) throws JSONException {
        int length = jSONArray.length();
        RendPass[] rendPassArr = new RendPass[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            jSONObject.getClass();
            rendPassArr[i] = parseRender(jSONObject, z, map, i2);
            i++;
            i2++;
        }
        return rendPassArr;
    }

    private final TransformData parseTransform(JSONObject jSONObject) {
        Float[] fArr;
        String[] strArr;
        Float[] fArr2;
        Float[] fArr3;
        Float[] fArr4;
        Float[] fArr5;
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("layout");
        if (jSONArrayOptJSONArray != null) {
            IntRange intRangeUntil = RangesKt___RangesKt.until(0, jSONArrayOptJSONArray.length());
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil, 10));
            IntIterator it = intRangeUntil.iterator();
            while (it.hasNext()) {
                arrayList.add(Float.valueOf((float) jSONArrayOptJSONArray.getDouble(it.nextInt())));
            }
            fArr = (Float[]) arrayList.toArray(new Float[0]);
        } else {
            fArr = null;
        }
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("layout_expr");
        if (jSONArrayOptJSONArray2 != null) {
            IntRange intRangeUntil2 = RangesKt___RangesKt.until(0, jSONArrayOptJSONArray2.length());
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil2, 10));
            IntIterator it2 = intRangeUntil2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(jSONArrayOptJSONArray2.optString(it2.nextInt()));
            }
            strArr = (String[]) arrayList2.toArray(new String[0]);
        } else {
            strArr = null;
        }
        JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("scale");
        if (jSONArrayOptJSONArray3 != null) {
            IntRange intRangeUntil3 = RangesKt___RangesKt.until(0, jSONArrayOptJSONArray3.length());
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil3, 10));
            IntIterator it3 = intRangeUntil3.iterator();
            while (it3.hasNext()) {
                arrayList3.add(Float.valueOf((float) jSONArrayOptJSONArray3.getDouble(it3.nextInt())));
            }
            fArr2 = (Float[]) arrayList3.toArray(new Float[0]);
        } else {
            fArr2 = null;
        }
        JSONArray jSONArrayOptJSONArray4 = jSONObject.optJSONArray("rotate");
        if (jSONArrayOptJSONArray4 != null) {
            IntRange intRangeUntil4 = RangesKt___RangesKt.until(0, jSONArrayOptJSONArray4.length());
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil4, 10));
            IntIterator it4 = intRangeUntil4.iterator();
            while (it4.hasNext()) {
                arrayList4.add(Float.valueOf((float) jSONArrayOptJSONArray4.getDouble(it4.nextInt())));
            }
            fArr3 = (Float[]) arrayList4.toArray(new Float[0]);
        } else {
            fArr3 = null;
        }
        JSONArray jSONArrayOptJSONArray5 = jSONObject.optJSONArray("margin");
        if (jSONArrayOptJSONArray5 != null) {
            IntRange intRangeUntil5 = RangesKt___RangesKt.until(0, jSONArrayOptJSONArray5.length());
            ArrayList arrayList5 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil5, 10));
            IntIterator it5 = intRangeUntil5.iterator();
            while (it5.hasNext()) {
                arrayList5.add(Float.valueOf((float) jSONArrayOptJSONArray5.getDouble(it5.nextInt())));
            }
            fArr4 = (Float[]) arrayList5.toArray(new Float[0]);
        } else {
            fArr4 = null;
        }
        JSONArray jSONArrayOptJSONArray6 = jSONObject.optJSONArray("padding");
        if (jSONArrayOptJSONArray6 != null) {
            IntRange intRangeUntil6 = RangesKt___RangesKt.until(0, jSONArrayOptJSONArray6.length());
            ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil6, 10));
            IntIterator it6 = intRangeUntil6.iterator();
            while (it6.hasNext()) {
                arrayList6.add(Float.valueOf((float) jSONArrayOptJSONArray6.getDouble(it6.nextInt())));
            }
            fArr5 = (Float[]) arrayList6.toArray(new Float[0]);
        } else {
            fArr5 = null;
        }
        String strOptString = jSONObject.optString("positionType", null);
        String strOptString2 = jSONObject.optString("valign", null);
        String strOptString3 = jSONObject.optString("halign", null);
        if (fArr == null || fArr2 == null || fArr3 == null) {
            return null;
        }
        return new TransformData(fArr, strArr, fArr2, fArr3, fArr4, fArr5, strOptString, strOptString2, strOptString3);
    }

    private final Pair<String, Uniform> parseUniform(JSONObject jSONObject, boolean z, HashMap<String, Object> map) {
        float f;
        Object objValueOf;
        float f2;
        Float fValueOf = Float.valueOf(0.0f);
        int id = getId(jSONObject);
        String strOptString = jSONObject.optString("uniformName");
        UniformType uniformType = parseUniformType(jSONObject);
        int iOptInt = jSONObject.optInt("w");
        if (iOptInt == 0) {
            iOptInt = jSONObject.optInt("width");
        }
        int iOptInt2 = jSONObject.optInt("h");
        if (iOptInt2 == 0) {
            iOptInt2 = jSONObject.optInt("height");
        }
        float fOptDouble = (float) jSONObject.optDouble("x");
        float fOptDouble2 = (float) jSONObject.optDouble("y");
        float fOptDouble3 = (float) jSONObject.optDouble("z");
        float fOptDouble4 = (float) jSONObject.optDouble("w");
        int iOptInt3 = jSONObject.optInt("format", 0);
        boolean zOptBoolean = jSONObject.optBoolean("flip", true);
        int iOptInt4 = jSONObject.optInt("wrapMode", 10497);
        String strOptString2 = jSONObject.optString("mediaType");
        int iOptInt5 = jSONObject.optInt("colorMode", 32856);
        int iOrdinal = uniformType.ordinal();
        if (iOrdinal != 3) {
            if (iOrdinal == 4) {
                objValueOf = Integer.valueOf(jSONObject.optInt(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE, 0));
            } else if (iOrdinal != 9) {
                objValueOf = iOrdinal != 10 ? Float.valueOf((float) jSONObject.optDouble(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE)) : parseFloatArray(jSONObject, SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE, ArraysKt___ArraysKt.toFloatArray(new Float[]{fValueOf, fValueOf, fValueOf, fValueOf}));
            } else if (z) {
                String strOptString3 = jSONObject.optString(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
                objValueOf = (strOptString3 == null || !StringsKt__StringsJVMKt.startsWith(strOptString3, "url://id/", false) || map == null) ? null : map.get(strOptString3.substring(9));
            } else {
                objValueOf = jSONObject.optString(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
            }
            f2 = fOptDouble2;
            f = fOptDouble4;
        } else {
            fOptDouble = (float) jSONObject.optDouble("x");
            float fOptDouble5 = (float) jSONObject.optDouble("y");
            fOptDouble3 = (float) jSONObject.optDouble("z");
            float fOptDouble6 = (float) jSONObject.optDouble("w");
            f = fOptDouble6;
            objValueOf = new Float[]{Float.valueOf(fOptDouble), Float.valueOf(fOptDouble5), Float.valueOf(fOptDouble3), Float.valueOf(fOptDouble6)};
            f2 = fOptDouble5;
        }
        if (uniformType == UniformType.FBO) {
            if (iOptInt == 0) {
                iOptInt = 100;
            }
            if (iOptInt2 == 0) {
                iOptInt2 = 100;
            }
        }
        strOptString.getClass();
        Integer numValueOf = Integer.valueOf(iOptInt4);
        strOptString2.getClass();
        return new Pair<>(strOptString, new Uniform(id, strOptString, uniformType, objValueOf, fOptDouble, f2, fOptDouble3, f, iOptInt, iOptInt2, iOptInt3, zOptBoolean, numValueOf, strOptString2, null, Integer.valueOf(iOptInt5), 16384, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Pair parseUniform$default(COEParse cOEParse, JSONObject jSONObject, boolean z, HashMap map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = null;
        }
        return cOEParse.parseUniform(jSONObject, z, map);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x002e  */
    /* JADX WARN: Code duplicated, block: B:12:0x0031 A[RETURN] */
    private final UniformType parseUniformType(JSONObject jSONObject) {
        String lowerCase = jSONObject.optString("type").toLowerCase(Locale.ROOT);
        for (UniformType uniformType : UniformType.values()) {
            if (Intrinsics.areEqual(uniformType.name().toLowerCase(Locale.ROOT), lowerCase)) {
                if (uniformType == null) {
                    return UniformType.Float;
                }
                return uniformType;
            }
        }
        uniformType = null;
        if (uniformType == null) {
            return UniformType.Float;
        }
        return uniformType;
    }

    private final Object[] parseUniformValue(UniformType uniformType, JSONObject jSONObject) throws JSONException {
        switch (uniformType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[uniformType.ordinal()]) {
            case 1:
                return new Object[]{Integer.valueOf(jSONObject.getInt(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE))};
            case 2:
            case 3:
                return new Object[]{Float.valueOf((float) jSONObject.getDouble(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE))};
            case 4:
                JSONArray jSONArray = jSONObject.getJSONArray(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
                return new Object[]{Float.valueOf((float) jSONArray.getDouble(0)), Float.valueOf((float) jSONArray.getDouble(1)), Float.valueOf((float) jSONArray.getDouble(2)), Float.valueOf((float) jSONArray.getDouble(3))};
            case 5:
                JSONArray jSONArray2 = jSONObject.getJSONArray(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
                return new Object[]{Float.valueOf((float) jSONArray2.getDouble(0)), Float.valueOf((float) jSONArray2.getDouble(1))};
            case 6:
                JSONArray jSONArray3 = jSONObject.getJSONArray(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
                return new Object[]{Float.valueOf((float) jSONArray3.getDouble(0)), Float.valueOf((float) jSONArray3.getDouble(1)), Float.valueOf((float) jSONArray3.getDouble(2))};
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                JSONArray jSONArray4 = jSONObject.getJSONArray(SeedlingMediaDataHandleUtils.PARAM_SLIDER_VALUE);
                return new Object[]{Float.valueOf((float) jSONArray4.getDouble(0)), Float.valueOf((float) jSONArray4.getDouble(1)), Float.valueOf((float) jSONArray4.getDouble(2)), Float.valueOf((float) jSONArray4.getDouble(3))};
            default:
                return new Object[0];
        }
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final COEData parse(byte[] bArr, boolean z) throws JSONException {
        HashMap<String, AnimatorValue> animator;
        int length;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONObject jSONObject;
        String str;
        Layer layer;
        Trace.beginSection("parseProtocl");
        long jCurrentTimeMillis = System.currentTimeMillis();
        HashMap<String, Object> map = new HashMap<>();
        String str2 = "";
        if (z) {
            ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bArr));
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    }
                    if (!nextEntry.isDirectory()) {
                        String name = nextEntry.getName();
                        if ("data".equals(name)) {
                            StringBuilder sb = new StringBuilder();
                            byte[] bArr2 = new byte[20480];
                            while (true) {
                                int i = zipInputStream.read(bArr2);
                                if (i <= 0) {
                                    break;
                                }
                                sb.append(new String(bArr2, 0, i, StandardCharsets.UTF_8));
                            }
                            str2 = StringsKt__StringsJVMKt.decodeToString(Base64.decode(sb.toString().substring(2), 0));
                        } else if (((int) nextEntry.getSize()) != 0) {
                            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect((int) nextEntry.getSize());
                            Channels.newChannel(zipInputStream).read(byteBufferAllocateDirect);
                            name.getClass();
                            byteBufferAllocateDirect.getClass();
                            map.put(name, byteBufferAllocateDirect);
                        }
                    }
                    zipInputStream.closeEntry();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(zipInputStream, th);
                        throw th2;
                    }
                }
            }
            CloseableKt.closeFinally(zipInputStream, (Throwable) null);
        }
        if (!z) {
            str2 = new String(bArr, Charsets.UTF_8);
        }
        JSONObject jSONObject2 = new JSONObject(str2);
        String strOptString = jSONObject2.optString(EarphoneController.COLUMN_NAME);
        int iOptInt = jSONObject2.optInt("v");
        int iOptInt2 = jSONObject2.optInt("mV");
        long jOptLong = jSONObject2.optLong("cT");
        long jOptLong2 = jSONObject2.optLong("coeV");
        TransformData transform = parseTransform(jSONObject2);
        JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("postProcessor");
        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("render");
        JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("params");
        JSONArray jSONArrayOptJSONArray3 = jSONObject2.optJSONArray("animators");
        HashMap<String, String[]> exprRecord = parseExprRecord(jSONObject2);
        JSONArray jSONArrayOptJSONArray4 = jSONObject2.optJSONArray("layers");
        if (jSONArrayOptJSONArray2 != null) {
            length = 1;
            animator = null;
        } else {
            animator = parseAnimator(jSONArrayOptJSONArray3);
            length = 0;
        }
        if (jSONArrayOptJSONArray4 != null) {
            length = jSONArrayOptJSONArray4.length() + length;
        }
        Layer[] layerArr = new Layer[length];
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            if (i3 != 0 || jSONArrayOptJSONArray2 == null) {
                jSONArray = jSONArrayOptJSONArray;
                jSONArray2 = jSONArrayOptJSONArray2;
                jSONObject = jSONObjectOptJSONObject;
                str = strOptString;
                jSONArrayOptJSONArray4.getClass();
                layer = parseLayer(jSONArrayOptJSONArray4.getJSONObject(i3), z, map, i2);
                i2++;
            } else {
                PostProcessorData[] postProcessor = jSONArrayOptJSONArray != null ? parsePostProcessor(jSONArrayOptJSONArray) : null;
                RendPass[] renders = parseRenders(jSONArrayOptJSONArray2, z, map);
                HashMap<String, PassParams[]> params = parseParams(jSONObjectOptJSONObject, renders);
                HashMap<String, AnimatorValue> animator2 = parseAnimator(jSONArrayOptJSONArray3);
                strOptString.getClass();
                JSONArray jSONArray3 = jSONArrayOptJSONArray2;
                str = strOptString;
                jSONArray2 = jSONArray3;
                jSONObject = jSONObjectOptJSONObject;
                jSONArray = jSONArrayOptJSONArray;
                layer = new Layer("", str, "Node", transform, postProcessor, renders, params, animator2, false, false, 0, 0, 0, 0, 0, 32256, null);
            }
            layerArr[i3] = layer;
            i3++;
            jSONArrayOptJSONArray3 = jSONArrayOptJSONArray3;
            strOptString = str;
            iOptInt = iOptInt;
            jSONArrayOptJSONArray2 = jSONArray2;
            jSONObjectOptJSONObject = jSONObject;
            jSONArrayOptJSONArray = jSONArray;
        }
        int i4 = iOptInt;
        String str3 = strOptString;
        map.clear();
        Log.d(Utils.TAG, this.TAG + "=>parse time: " + (System.currentTimeMillis() - jCurrentTimeMillis));
        StatUtil.Companion companion = StatUtil.Companion;
        str3.getClass();
        FileStat fileStat = companion.getFileStat(str3);
        if (fileStat != null) {
            fileStat.setFileName(str3);
            fileStat.setFileSize(bArr.length);
            fileStat.setLoadProtocolTime((int) (System.currentTimeMillis() - jCurrentTimeMillis));
        }
        Trace.endSection();
        return new COEData(str3, i4, iOptInt2, jOptLong, jOptLong2, layerArr, new COEExpressions(exprRecord), animator);
    }
}
