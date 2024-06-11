package com.google.android.material.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;

public final class DrawableUtils {
    private DrawableUtils() {
    }

    public static PorterDuffColorFilter updateTintFilter(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(drawable.getState(), 0), mode);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0042 A[Catch:{ IOException | XmlPullParserException -> 0x004a }] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0014 A[Catch:{ IOException | XmlPullParserException -> 0x004a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.AttributeSet parseDrawableXml(android.content.Context r3, int r4, java.lang.CharSequence r5) {
        /*
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            android.content.res.XmlResourceParser r3 = r3.getXml(r4)     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
        L_0x0008:
            int r0 = r3.next()     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            r1 = 2
            if (r0 == r1) goto L_0x0012
            r2 = 1
            if (r0 != r2) goto L_0x0008
        L_0x0012:
            if (r0 != r1) goto L_0x0042
            java.lang.String r0 = r3.getName()     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            boolean r0 = android.text.TextUtils.equals(r0, r5)     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            if (r0 == 0) goto L_0x0023
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r3)     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            return r3
        L_0x0023:
            org.xmlpull.v1.XmlPullParserException r3 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            r0.<init>()     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            java.lang.String r1 = "Must have a <"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            java.lang.StringBuilder r5 = r0.append(r5)     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            java.lang.String r0 = "> start tag"
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            java.lang.String r5 = r5.toString()     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            r3.<init>(r5)     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            throw r3     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
        L_0x0042:
            org.xmlpull.v1.XmlPullParserException r3 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            java.lang.String r5 = "No start tag found"
            r3.<init>(r5)     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
            throw r3     // Catch:{ XmlPullParserException -> 0x004c, IOException -> 0x004a }
        L_0x004a:
            r3 = move-exception
            goto L_0x004d
        L_0x004c:
            r3 = move-exception
        L_0x004d:
            android.content.res.Resources$NotFoundException r5 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Can't load badge resource ID #0x"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r4 = java.lang.Integer.toHexString(r4)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r4 = r4.toString()
            r5.<init>(r4)
            r5.initCause(r3)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.drawable.DrawableUtils.parseDrawableXml(android.content.Context, int, java.lang.CharSequence):android.util.AttributeSet");
    }

    public static void setRippleDrawableRadius(RippleDrawable rippleDrawable, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            rippleDrawable.setRadius(i);
            return;
        }
        Class<RippleDrawable> cls = RippleDrawable.class;
        try {
            cls.getDeclaredMethod("setMaxRadius", new Class[]{Integer.TYPE}).invoke(rippleDrawable, new Object[]{Integer.valueOf(i)});
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Couldn't set RippleDrawable radius", e);
        }
    }
}
