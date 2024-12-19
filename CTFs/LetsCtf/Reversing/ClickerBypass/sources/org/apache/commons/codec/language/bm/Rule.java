package org.apache.commons.codec.language.bm;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages;

public class Rule {
    public static final String ALL = "ALL";
    public static final RPattern ALL_STRINGS_RMATCHER = new RPattern() {
        public boolean isMatch(CharSequence charSequence) {
            return true;
        }
    };
    private static final String DOUBLE_QUOTE = "\"";
    private static final String HASH_INCLUDE = "#include";
    private static final Map<NameType, Map<RuleType, Map<String, Map<String, List<Rule>>>>> RULES = new EnumMap(NameType.class);
    private final RPattern lContext;
    private final String pattern;
    private final PhonemeExpr phoneme;
    private final RPattern rContext;

    public interface PhonemeExpr {
        Iterable<Phoneme> getPhonemes();
    }

    public interface RPattern {
        boolean isMatch(CharSequence charSequence);
    }

    public static final class Phoneme implements PhonemeExpr {
        public static final Comparator<Phoneme> COMPARATOR = new Comparator<Phoneme>() {
            public int compare(Phoneme phoneme, Phoneme phoneme2) {
                for (int i = 0; i < phoneme.phonemeText.length(); i++) {
                    if (i >= phoneme2.phonemeText.length()) {
                        return 1;
                    }
                    int charAt = phoneme.phonemeText.charAt(i) - phoneme2.phonemeText.charAt(i);
                    if (charAt != 0) {
                        return charAt;
                    }
                }
                if (phoneme.phonemeText.length() < phoneme2.phonemeText.length()) {
                    return -1;
                }
                return 0;
            }
        };
        private final Languages.LanguageSet languages;
        /* access modifiers changed from: private */
        public final StringBuilder phonemeText;

        public Phoneme(CharSequence charSequence, Languages.LanguageSet languageSet) {
            this.phonemeText = new StringBuilder(charSequence);
            this.languages = languageSet;
        }

        public Phoneme(Phoneme phoneme, Phoneme phoneme2) {
            this((CharSequence) phoneme.phonemeText, phoneme.languages);
            this.phonemeText.append(phoneme2.phonemeText);
        }

        public Phoneme(Phoneme phoneme, Phoneme phoneme2, Languages.LanguageSet languageSet) {
            this((CharSequence) phoneme.phonemeText, languageSet);
            this.phonemeText.append(phoneme2.phonemeText);
        }

        public Phoneme append(CharSequence charSequence) {
            this.phonemeText.append(charSequence);
            return this;
        }

        public Languages.LanguageSet getLanguages() {
            return this.languages;
        }

        public Iterable<Phoneme> getPhonemes() {
            return Collections.singleton(this);
        }

        public CharSequence getPhonemeText() {
            return this.phonemeText;
        }

        @Deprecated
        public Phoneme join(Phoneme phoneme) {
            return new Phoneme((CharSequence) this.phonemeText.toString() + phoneme.phonemeText.toString(), this.languages.restrictTo(phoneme.languages));
        }

        public Phoneme mergeWithLanguage(Languages.LanguageSet languageSet) {
            return new Phoneme((CharSequence) this.phonemeText.toString(), this.languages.merge(languageSet));
        }

        public String toString() {
            return this.phonemeText.toString() + "[" + this.languages + "]";
        }
    }

    public static final class PhonemeList implements PhonemeExpr {
        private final List<Phoneme> phonemes;

        public PhonemeList(List<Phoneme> list) {
            this.phonemes = list;
        }

        public List<Phoneme> getPhonemes() {
            return this.phonemes;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0060, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
        if (r13 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ab, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ac, code lost:
        if (r12 != null) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b2, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b3, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b6, code lost:
        throw r1;
     */
    static {
        /*
            org.apache.commons.codec.language.bm.Rule$1 r0 = new org.apache.commons.codec.language.bm.Rule$1
            r0.<init>()
            ALL_STRINGS_RMATCHER = r0
            java.util.EnumMap r0 = new java.util.EnumMap
            java.lang.Class<org.apache.commons.codec.language.bm.NameType> r1 = org.apache.commons.codec.language.bm.NameType.class
            r0.<init>(r1)
            RULES = r0
            org.apache.commons.codec.language.bm.NameType[] r0 = org.apache.commons.codec.language.bm.NameType.values()
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L_0x0017:
            if (r3 >= r1) goto L_0x00cf
            r4 = r0[r3]
            java.util.EnumMap r5 = new java.util.EnumMap
            java.lang.Class<org.apache.commons.codec.language.bm.RuleType> r6 = org.apache.commons.codec.language.bm.RuleType.class
            r5.<init>(r6)
            org.apache.commons.codec.language.bm.RuleType[] r6 = org.apache.commons.codec.language.bm.RuleType.values()
            int r7 = r6.length
            r8 = r2
        L_0x0028:
            if (r8 >= r7) goto L_0x00c2
            r9 = r6[r8]
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            org.apache.commons.codec.language.bm.Languages r11 = org.apache.commons.codec.language.bm.Languages.getInstance((org.apache.commons.codec.language.bm.NameType) r4)
            java.util.Set r11 = r11.getLanguages()
            java.util.Iterator r11 = r11.iterator()
        L_0x003d:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x008a
            java.lang.Object r12 = r11.next()
            java.lang.String r12 = (java.lang.String) r12
            java.util.Scanner r13 = createScanner(r4, r9, r12)     // Catch:{ IllegalStateException -> 0x006c }
            java.lang.String r14 = createResourceName(r4, r9, r12)     // Catch:{ all -> 0x005e }
            java.util.Map r14 = parseRules(r13, r14)     // Catch:{ all -> 0x005e }
            r10.put(r12, r14)     // Catch:{ all -> 0x005e }
            if (r13 == 0) goto L_0x003d
            r13.close()     // Catch:{ IllegalStateException -> 0x006c }
            goto L_0x003d
        L_0x005e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r1 = move-exception
            if (r13 == 0) goto L_0x006b
            r13.close()     // Catch:{ all -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ IllegalStateException -> 0x006c }
        L_0x006b:
            throw r1     // Catch:{ IllegalStateException -> 0x006c }
        L_0x006c:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Problem processing "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = createResourceName(r4, r9, r12)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x008a:
            org.apache.commons.codec.language.bm.RuleType r11 = org.apache.commons.codec.language.bm.RuleType.RULES
            boolean r11 = r9.equals(r11)
            if (r11 != 0) goto L_0x00b7
            java.lang.String r11 = "common"
            java.util.Scanner r12 = createScanner(r4, r9, r11)
            java.lang.String r13 = createResourceName(r4, r9, r11)     // Catch:{ all -> 0x00a9 }
            java.util.Map r13 = parseRules(r12, r13)     // Catch:{ all -> 0x00a9 }
            r10.put(r11, r13)     // Catch:{ all -> 0x00a9 }
            if (r12 == 0) goto L_0x00b7
            r12.close()
            goto L_0x00b7
        L_0x00a9:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00ab }
        L_0x00ab:
            r1 = move-exception
            if (r12 == 0) goto L_0x00b6
            r12.close()     // Catch:{ all -> 0x00b2 }
            goto L_0x00b6
        L_0x00b2:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x00b6:
            throw r1
        L_0x00b7:
            java.util.Map r10 = java.util.Collections.unmodifiableMap(r10)
            r5.put(r9, r10)
            int r8 = r8 + 1
            goto L_0x0028
        L_0x00c2:
            java.util.Map<org.apache.commons.codec.language.bm.NameType, java.util.Map<org.apache.commons.codec.language.bm.RuleType, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.util.List<org.apache.commons.codec.language.bm.Rule>>>>> r6 = RULES
            java.util.Map r5 = java.util.Collections.unmodifiableMap(r5)
            r6.put(r4, r5)
            int r3 = r3 + 1
            goto L_0x0017
        L_0x00cf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.bm.Rule.<clinit>():void");
    }

    /* access modifiers changed from: private */
    public static boolean contains(CharSequence charSequence, char c) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    private static String createResourceName(NameType nameType, RuleType ruleType, String str) {
        return String.format("org/apache/commons/codec/language/bm/%s_%s_%s.txt", new Object[]{nameType.getName(), ruleType.getName(), str});
    }

    private static Scanner createScanner(NameType nameType, RuleType ruleType, String str) {
        String createResourceName = createResourceName(nameType, ruleType, str);
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(createResourceName);
        if (resourceAsStream != null) {
            return new Scanner(resourceAsStream, "UTF-8");
        }
        throw new IllegalArgumentException("Unable to load resource: " + createResourceName);
    }

    private static Scanner createScanner(String str) {
        String format = String.format("org/apache/commons/codec/language/bm/%s.txt", new Object[]{str});
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(format);
        if (resourceAsStream != null) {
            return new Scanner(resourceAsStream, "UTF-8");
        }
        throw new IllegalArgumentException("Unable to load resource: " + format);
    }

    /* access modifiers changed from: private */
    public static boolean endsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        int length = charSequence.length() - 1;
        for (int length2 = charSequence2.length() - 1; length2 >= 0; length2--) {
            if (charSequence.charAt(length) != charSequence2.charAt(length2)) {
                return false;
            }
            length--;
        }
        return true;
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, Languages.LanguageSet languageSet) {
        Map<String, List<Rule>> instanceMap = getInstanceMap(nameType, ruleType, languageSet);
        ArrayList arrayList = new ArrayList();
        for (List<Rule> addAll : instanceMap.values()) {
            arrayList.addAll(addAll);
        }
        return arrayList;
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, String str) {
        return getInstance(nameType, ruleType, Languages.LanguageSet.from(new HashSet(Arrays.asList(new String[]{str}))));
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType ruleType, Languages.LanguageSet languageSet) {
        if (languageSet.isSingleton()) {
            return getInstanceMap(nameType, ruleType, languageSet.getAny());
        }
        return getInstanceMap(nameType, ruleType, Languages.ANY);
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType ruleType, String str) {
        Map<String, List<Rule>> map = (Map) ((Map) RULES.get(nameType).get(ruleType)).get(str);
        if (map != null) {
            return map;
        }
        throw new IllegalArgumentException(String.format("No rules found for %s, %s, %s.", new Object[]{nameType.getName(), ruleType.getName(), str}));
    }

    private static Phoneme parsePhoneme(String str) {
        int indexOf = str.indexOf("[");
        if (indexOf < 0) {
            return new Phoneme((CharSequence) str, Languages.ANY_LANGUAGE);
        }
        if (str.endsWith("]")) {
            return new Phoneme((CharSequence) str.substring(0, indexOf), Languages.LanguageSet.from(new HashSet(Arrays.asList(str.substring(indexOf + 1, str.length() - 1).split("[+]")))));
        }
        throw new IllegalArgumentException("Phoneme expression contains a '[' but does not end in ']'");
    }

    private static PhonemeExpr parsePhonemeExpr(String str) {
        if (!str.startsWith("(")) {
            return parsePhoneme(str);
        }
        if (str.endsWith(")")) {
            ArrayList arrayList = new ArrayList();
            String substring = str.substring(1, str.length() - 1);
            for (String parsePhoneme : substring.split("[|]")) {
                arrayList.add(parsePhoneme(parsePhoneme));
            }
            if (substring.startsWith("|") || substring.endsWith("|")) {
                arrayList.add(new Phoneme((CharSequence) "", Languages.ANY_LANGUAGE));
            }
            return new PhonemeList(arrayList);
        }
        throw new IllegalArgumentException("Phoneme starts with '(' so must end with ')'");
    }

    private static Map<String, List<Rule>> parseRules(Scanner scanner, String str) {
        int i;
        String str2;
        Throwable th;
        String str3 = str;
        HashMap hashMap = new HashMap();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (scanner.hasNextLine()) {
            int i5 = i3 + 1;
            String nextLine = scanner.nextLine();
            if (i4 == 0) {
                if (nextLine.startsWith("/*")) {
                    i = i2;
                    i4 = 1;
                } else {
                    int indexOf = nextLine.indexOf("//");
                    String trim = (indexOf >= 0 ? nextLine.substring(i2, indexOf) : nextLine).trim();
                    if (trim.length() == 0) {
                        continue;
                        i3 = i5;
                    } else if (trim.startsWith(HASH_INCLUDE)) {
                        String trim2 = trim.substring(8).trim();
                        if (!trim2.contains(" ")) {
                            Scanner createScanner = createScanner(trim2);
                            try {
                                hashMap.putAll(parseRules(createScanner, str3 + "->" + trim2));
                                if (createScanner != null) {
                                    createScanner.close();
                                }
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                if (createScanner != null) {
                                    try {
                                        createScanner.close();
                                    } catch (Throwable th4) {
                                        th.addSuppressed(th4);
                                    }
                                }
                                throw th3;
                            }
                        } else {
                            throw new IllegalArgumentException("Malformed import statement '" + nextLine + "' in " + str3);
                        }
                    } else {
                        String[] split = trim.split("\\s+");
                        if (split.length == 4) {
                            try {
                                String stripQuotes = stripQuotes(split[i2]);
                                String stripQuotes2 = stripQuotes(split[1]);
                                String stripQuotes3 = stripQuotes(split[2]);
                                AnonymousClass2 r12 = r1;
                                str2 = "' in ";
                                try {
                                    AnonymousClass2 r1 = new Rule(stripQuotes, stripQuotes2, stripQuotes3, parsePhonemeExpr(stripQuotes(split[3])), i5, str, stripQuotes, stripQuotes2, stripQuotes3) {
                                        private final String loc;
                                        private final int myLine;
                                        final /* synthetic */ int val$cLine;
                                        final /* synthetic */ String val$lCon;
                                        final /* synthetic */ String val$location;
                                        final /* synthetic */ String val$pat;
                                        final /* synthetic */ String val$rCon;

                                        {
                                            this.val$cLine = r5;
                                            this.val$location = r6;
                                            this.val$pat = r7;
                                            this.val$lCon = r8;
                                            this.val$rCon = r9;
                                            this.myLine = r5;
                                            this.loc = r6;
                                        }

                                        public String toString() {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("Rule");
                                            sb.append("{line=").append(this.myLine);
                                            sb.append(", loc='").append(this.loc).append('\'');
                                            sb.append(", pat='").append(this.val$pat).append('\'');
                                            sb.append(", lcon='").append(this.val$lCon).append('\'');
                                            sb.append(", rcon='").append(this.val$rCon).append('\'');
                                            sb.append('}');
                                            return sb.toString();
                                        }
                                    };
                                    i = 0;
                                    String substring = r12.pattern.substring(0, 1);
                                    List list = (List) hashMap.get(substring);
                                    if (list == null) {
                                        list = new ArrayList();
                                        hashMap.put(substring, list);
                                    }
                                    list.add(r12);
                                } catch (IllegalArgumentException e) {
                                    e = e;
                                    throw new IllegalStateException("Problem parsing line '" + i5 + str2 + str3, e);
                                }
                            } catch (IllegalArgumentException e2) {
                                e = e2;
                                str2 = "' in ";
                                throw new IllegalStateException("Problem parsing line '" + i5 + str2 + str3, e);
                            }
                        } else {
                            throw new IllegalArgumentException("Malformed rule statement split into " + split.length + " parts: " + nextLine + " in " + str3);
                        }
                    }
                }
                i2 = i;
                i3 = i5;
            } else if (nextLine.endsWith("*/")) {
                i = i2;
                i4 = i;
                i2 = i;
                i3 = i5;
            }
            i = i2;
            i2 = i;
            i3 = i5;
        }
        return hashMap;
    }

    private static RPattern pattern(String str) {
        boolean startsWith = str.startsWith("^");
        boolean endsWith = str.endsWith("$");
        int length = str.length();
        if (endsWith) {
            length--;
        }
        final String substring = str.substring(startsWith ? 1 : 0, length);
        if (substring.contains("[")) {
            boolean startsWith2 = substring.startsWith("[");
            boolean endsWith2 = substring.endsWith("]");
            if (startsWith2 && endsWith2) {
                final String substring2 = substring.substring(1, substring.length() - 1);
                if (!substring2.contains("[")) {
                    boolean startsWith3 = substring2.startsWith("^");
                    if (startsWith3) {
                        substring2 = substring2.substring(1);
                    }
                    final boolean z = !startsWith3;
                    if (startsWith && endsWith) {
                        return new RPattern() {
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() == 1 && Rule.contains(substring2, charSequence.charAt(0)) == z;
                            }
                        };
                    }
                    if (startsWith) {
                        return new RPattern() {
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() > 0 && Rule.contains(substring2, charSequence.charAt(0)) == z;
                            }
                        };
                    }
                    if (endsWith) {
                        return new RPattern() {
                            public boolean isMatch(CharSequence charSequence) {
                                if (charSequence.length() <= 0 || Rule.contains(substring2, charSequence.charAt(charSequence.length() - 1)) != z) {
                                    return false;
                                }
                                return true;
                            }
                        };
                    }
                }
            }
        } else if (!startsWith || !endsWith) {
            if ((startsWith || endsWith) && substring.length() == 0) {
                return ALL_STRINGS_RMATCHER;
            }
            if (startsWith) {
                return new RPattern() {
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.startsWith(charSequence, substring);
                    }
                };
            }
            if (endsWith) {
                return new RPattern() {
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.endsWith(charSequence, substring);
                    }
                };
            }
        } else if (substring.length() == 0) {
            return new RPattern() {
                public boolean isMatch(CharSequence charSequence) {
                    return charSequence.length() == 0;
                }
            };
        } else {
            return new RPattern() {
                public boolean isMatch(CharSequence charSequence) {
                    return charSequence.equals(substring);
                }
            };
        }
        return new RPattern(str) {
            Pattern pattern;
            final /* synthetic */ String val$regex;

            {
                this.val$regex = r1;
                this.pattern = Pattern.compile(r1);
            }

            public boolean isMatch(CharSequence charSequence) {
                return this.pattern.matcher(charSequence).find();
            }
        };
    }

    /* access modifiers changed from: private */
    public static boolean startsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        for (int i = 0; i < charSequence2.length(); i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String stripQuotes(String str) {
        if (str.startsWith(DOUBLE_QUOTE)) {
            str = str.substring(1);
        }
        return str.endsWith(DOUBLE_QUOTE) ? str.substring(0, str.length() - 1) : str;
    }

    public Rule(String str, String str2, String str3, PhonemeExpr phonemeExpr) {
        this.pattern = str;
        this.lContext = pattern(str2 + "$");
        this.rContext = pattern("^" + str3);
        this.phoneme = phonemeExpr;
    }

    public RPattern getLContext() {
        return this.lContext;
    }

    public String getPattern() {
        return this.pattern;
    }

    public PhonemeExpr getPhoneme() {
        return this.phoneme;
    }

    public RPattern getRContext() {
        return this.rContext;
    }

    public boolean patternAndContextMatches(CharSequence charSequence, int i) {
        if (i >= 0) {
            int length = this.pattern.length() + i;
            if (length <= charSequence.length() && charSequence.subSequence(i, length).equals(this.pattern) && this.rContext.isMatch(charSequence.subSequence(length, charSequence.length()))) {
                return this.lContext.isMatch(charSequence.subSequence(0, i));
            }
            return false;
        }
        throw new IndexOutOfBoundsException("Can not match pattern at negative indexes");
    }
}
