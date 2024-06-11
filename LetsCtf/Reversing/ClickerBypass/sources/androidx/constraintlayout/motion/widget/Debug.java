package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import ctf.clickergame.BuildConfig;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Debug {
    public static void logStack(String str, String str2, int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int min = Math.min(i, stackTrace.length - 1);
        String str3 = " ";
        for (int i2 = 1; i2 <= min; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            String fileName = stackTrace[i2].getFileName();
            int lineNumber = stackTrace[i2].getLineNumber();
            String methodName = stackTrace[i2].getMethodName();
            String sb = new StringBuilder(String.valueOf(fileName).length() + 16 + String.valueOf(methodName).length()).append(".(").append(fileName).append(":").append(lineNumber).append(") ").append(methodName).toString();
            str3 = String.valueOf(str3).concat(" ");
            Log.v(str, new StringBuilder(String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(sb).length() + String.valueOf(str3).length()).append(str2).append(str3).append(sb).append(str3).toString());
        }
    }

    public static void printStack(String str, int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int min = Math.min(i, stackTrace.length - 1);
        String str2 = " ";
        for (int i2 = 1; i2 <= min; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            String fileName = stackTrace[i2].getFileName();
            String sb = new StringBuilder(String.valueOf(fileName).length() + 16).append(".(").append(fileName).append(":").append(stackTrace[i2].getLineNumber()).append(") ").toString();
            str2 = String.valueOf(str2).concat(" ");
            System.out.println(new StringBuilder(String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(sb).length() + String.valueOf(str2).length()).append(str).append(str2).append(sb).append(str2).toString());
        }
    }

    public static String getName(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static void dumpPoc(Object obj) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String fileName = stackTraceElement.getFileName();
        String sb = new StringBuilder(String.valueOf(fileName).length() + 15).append(".(").append(fileName).append(":").append(stackTraceElement.getLineNumber()).append(")").toString();
        Class<?> cls = obj.getClass();
        PrintStream printStream = System.out;
        String name = cls.getName();
        printStream.println(new StringBuilder(String.valueOf(sb).length() + 35 + String.valueOf(name).length()).append(sb).append("------------- ").append(name).append(" --------------------").toString());
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            try {
                Object obj2 = field.get(obj);
                if (field.getName().startsWith("layout_constraint")) {
                    if (!(obj2 instanceof Integer) || !obj2.toString().equals("-1")) {
                        if (!(obj2 instanceof Integer) || !obj2.toString().equals("0")) {
                            if (!(obj2 instanceof Float) || !obj2.toString().equals(BuildConfig.VERSION_NAME)) {
                                if (!(obj2 instanceof Float) || !obj2.toString().equals("0.5")) {
                                    PrintStream printStream2 = System.out;
                                    String name2 = field.getName();
                                    String valueOf = String.valueOf(obj2);
                                    printStream2.println(new StringBuilder(String.valueOf(sb).length() + 5 + String.valueOf(name2).length() + String.valueOf(valueOf).length()).append(sb).append("    ").append(name2).append(" ").append(valueOf).toString());
                                }
                            }
                        }
                    }
                }
            } catch (IllegalAccessException unused) {
            }
        }
        PrintStream printStream3 = System.out;
        String simpleName = cls.getSimpleName();
        printStream3.println(new StringBuilder(String.valueOf(sb).length() + 35 + String.valueOf(simpleName).length()).append(sb).append("------------- ").append(simpleName).append(" --------------------").toString());
    }

    public static String getName(Context context, int i) {
        if (i == -1) {
            return "UNKNOWN";
        }
        try {
            return context.getResources().getResourceEntryName(i);
        } catch (Exception unused) {
            return new StringBuilder(12).append("?").append(i).toString();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r2 = new java.lang.StringBuilder(14).append("? ").append(r7[r1]).append(" ").toString();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0042 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getName(android.content.Context r6, int[] r7) {
        /*
            int r0 = r7.length     // Catch:{ Exception -> 0x0084 }
            r1 = 12
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0084 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0084 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r1 = "["
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0084 }
            r1 = 0
        L_0x0017:
            int r2 = r7.length     // Catch:{ Exception -> 0x0084 }
            if (r1 >= r2) goto L_0x0079
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r2 = " "
            if (r1 != 0) goto L_0x0025
            java.lang.String r3 = ""
            goto L_0x0026
        L_0x0025:
            r3 = r2
        L_0x0026:
            int r4 = r3.length()     // Catch:{ Exception -> 0x0084 }
            if (r4 == 0) goto L_0x0031
            java.lang.String r0 = r0.concat(r3)     // Catch:{ Exception -> 0x0084 }
            goto L_0x0037
        L_0x0031:
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0084 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0084 }
            r0 = r3
        L_0x0037:
            android.content.res.Resources r3 = r6.getResources()     // Catch:{ NotFoundException -> 0x0042 }
            r4 = r7[r1]     // Catch:{ NotFoundException -> 0x0042 }
            java.lang.String r2 = r3.getResourceEntryName(r4)     // Catch:{ NotFoundException -> 0x0042 }
            goto L_0x005d
        L_0x0042:
            r3 = r7[r1]     // Catch:{ Exception -> 0x0084 }
            r4 = 14
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0084 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r4 = "? "
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ Exception -> 0x0084 }
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ Exception -> 0x0084 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0084 }
        L_0x005d:
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0084 }
            int r3 = r2.length()     // Catch:{ Exception -> 0x0084 }
            if (r3 == 0) goto L_0x0070
            java.lang.String r0 = r0.concat(r2)     // Catch:{ Exception -> 0x0084 }
            goto L_0x0076
        L_0x0070:
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x0084 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0084 }
            r0 = r2
        L_0x0076:
            int r1 = r1 + 1
            goto L_0x0017
        L_0x0079:
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r7 = "]"
            java.lang.String r6 = r6.concat(r7)     // Catch:{ Exception -> 0x0084 }
            return r6
        L_0x0084:
            r6 = move-exception
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "DEBUG"
            android.util.Log.v(r7, r6)
            java.lang.String r6 = "UNKNOWN"
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.Debug.getName(android.content.Context, int[]):java.lang.String");
    }

    public static String getState(MotionLayout motionLayout, int i) {
        return getState(motionLayout, i, -1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r2 = r1.replaceAll("[^_]", "").length();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getState(androidx.constraintlayout.motion.widget.MotionLayout r1, int r2, int r3) {
        /*
            r0 = -1
            if (r2 != r0) goto L_0x0006
            java.lang.String r1 = "UNDEFINED"
            return r1
        L_0x0006:
            android.content.Context r1 = r1.getContext()
            android.content.res.Resources r1 = r1.getResources()
            java.lang.String r1 = r1.getResourceEntryName(r2)
            if (r3 == r0) goto L_0x0059
            int r2 = r1.length()
            if (r2 <= r3) goto L_0x0022
            java.lang.String r2 = "([^_])[aeiou]+"
            java.lang.String r0 = "$1"
            java.lang.String r1 = r1.replaceAll(r2, r0)
        L_0x0022:
            int r2 = r1.length()
            if (r2 <= r3) goto L_0x0059
            java.lang.String r2 = "[^_]"
            java.lang.String r0 = ""
            java.lang.String r2 = r1.replaceAll(r2, r0)
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x0059
            int r0 = r1.length()
            int r0 = r0 - r3
            int r0 = r0 / r2
            java.nio.CharBuffer r2 = java.nio.CharBuffer.allocate(r0)
            java.lang.String r2 = r2.toString()
            r3 = 0
            r0 = 46
            java.lang.String r2 = r2.replace(r3, r0)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r3 = "_"
            java.lang.String r2 = r2.concat(r3)
            java.lang.String r1 = r1.replaceAll(r2, r3)
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.Debug.getState(androidx.constraintlayout.motion.widget.MotionLayout, int, int):java.lang.String");
    }

    public static String getActionType(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        Field[] fields = MotionEvent.class.getFields();
        for (Field field : fields) {
            try {
                if (Modifier.isStatic(field.getModifiers()) && field.getType().equals(Integer.TYPE) && field.getInt((Object) null) == action) {
                    return field.getName();
                }
            } catch (IllegalAccessException unused) {
            }
        }
        return "---";
    }

    public static String getLocation() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String fileName = stackTraceElement.getFileName();
        return new StringBuilder(String.valueOf(fileName).length() + 15).append(".(").append(fileName).append(":").append(stackTraceElement.getLineNumber()).append(")").toString();
    }

    public static String getLoc() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        String methodName = stackTraceElement.getMethodName();
        return new StringBuilder(String.valueOf(fileName).length() + 18 + String.valueOf(methodName).length()).append(".(").append(fileName).append(":").append(lineNumber).append(") ").append(methodName).append("()").toString();
    }

    public static String getLocation2() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        String fileName = stackTraceElement.getFileName();
        return new StringBuilder(String.valueOf(fileName).length() + 15).append(".(").append(fileName).append(":").append(stackTraceElement.getLineNumber()).append(")").toString();
    }

    public static String getCallFrom(int i) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[i + 2];
        String fileName = stackTraceElement.getFileName();
        return new StringBuilder(String.valueOf(fileName).length() + 15).append(".(").append(fileName).append(":").append(stackTraceElement.getLineNumber()).append(")").toString();
    }

    public static void dumpLayoutParams(ViewGroup viewGroup, String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String fileName = stackTraceElement.getFileName();
        String sb = new StringBuilder(String.valueOf(fileName).length() + 18 + String.valueOf(str).length()).append(".(").append(fileName).append(":").append(stackTraceElement.getLineNumber()).append(") ").append(str).append("  ").toString();
        int childCount = viewGroup.getChildCount();
        System.out.println(new StringBuilder(String.valueOf(str).length() + 21).append(str).append(" children ").append(childCount).toString());
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            PrintStream printStream = System.out;
            String name = getName(childAt);
            printStream.println(new StringBuilder(String.valueOf(sb).length() + 5 + String.valueOf(name).length()).append(sb).append("     ").append(name).toString());
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            Field[] fields = layoutParams.getClass().getFields();
            for (Field field : fields) {
                try {
                    Object obj = field.get(layoutParams);
                    if (field.getName().contains("To")) {
                        if (!obj.toString().equals("-1")) {
                            PrintStream printStream2 = System.out;
                            String name2 = field.getName();
                            String valueOf = String.valueOf(obj);
                            printStream2.println(new StringBuilder(String.valueOf(sb).length() + 8 + String.valueOf(name2).length() + String.valueOf(valueOf).length()).append(sb).append("       ").append(name2).append(" ").append(valueOf).toString());
                        }
                    }
                } catch (IllegalAccessException unused) {
                }
            }
        }
    }

    public static void dumpLayoutParams(ViewGroup.LayoutParams layoutParams, String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String fileName = stackTraceElement.getFileName();
        String sb = new StringBuilder(String.valueOf(fileName).length() + 18 + String.valueOf(str).length()).append(".(").append(fileName).append(":").append(stackTraceElement.getLineNumber()).append(") ").append(str).append("  ").toString();
        PrintStream printStream = System.out;
        String name = layoutParams.getClass().getName();
        printStream.println(new StringBuilder(String.valueOf(sb).length() + 28 + String.valueOf(name).length()).append(" >>>>>>>>>>>>>>>>>>. dump ").append(sb).append("  ").append(name).toString());
        Field[] fields = layoutParams.getClass().getFields();
        for (Field field : fields) {
            try {
                Object obj = field.get(layoutParams);
                String name2 = field.getName();
                if (name2.contains("To")) {
                    if (!obj.toString().equals("-1")) {
                        PrintStream printStream2 = System.out;
                        String valueOf = String.valueOf(obj);
                        printStream2.println(new StringBuilder(String.valueOf(sb).length() + 8 + String.valueOf(name2).length() + String.valueOf(valueOf).length()).append(sb).append("       ").append(name2).append(" ").append(valueOf).toString());
                    }
                }
            } catch (IllegalAccessException unused) {
            }
        }
        PrintStream printStream3 = System.out;
        String valueOf2 = String.valueOf(sb);
        printStream3.println(valueOf2.length() != 0 ? " <<<<<<<<<<<<<<<<< dump ".concat(valueOf2) : new String(" <<<<<<<<<<<<<<<<< dump "));
    }
}
