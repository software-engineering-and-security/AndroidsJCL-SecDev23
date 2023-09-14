<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/util/DisabledAlgorithmConstraints.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/412d2b1381a4">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/412d2b1381a4">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/412d2b1381a4">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/sun/security/util/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/util/DisabledAlgorithmConstraints.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/util/DisabledAlgorithmConstraints.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/util/DisabledAlgorithmConstraints.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/util/DisabledAlgorithmConstraints.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/util/DisabledAlgorithmConstraints.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/util/DisabledAlgorithmConstraints.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/util/DisabledAlgorithmConstraints.java @ 14391:412d2b1381a4</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8249906: Enhance opening JARs
8258247: Couple of issues in fix for JDK-8249906
8259428: AlgorithmId.getEncodedParams() should return copy
Reviewed-by: mbalao, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#118;&#111;&#105;&#116;&#121;&#108;&#111;&#118;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Wed, 07 Apr 2021 05:57:56 +0100</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/78875da14d05/src/share/classes/sun/security/util/DisabledAlgorithmConstraints.java">78875da14d05</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"></td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 2010, 2019, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.  Oracle designates this</span><a href="#l7"></a>
<span id="l8"> * particular file as subject to the &quot;Classpath&quot; exception as provided</span><a href="#l8"></a>
<span id="l9"> * by Oracle in the LICENSE file that accompanied this code.</span><a href="#l9"></a>
<span id="l10"> *</span><a href="#l10"></a>
<span id="l11"> * This code is distributed in the hope that it will be useful, but WITHOUT</span><a href="#l11"></a>
<span id="l12"> * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or</span><a href="#l12"></a>
<span id="l13"> * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License</span><a href="#l13"></a>
<span id="l14"> * version 2 for more details (a copy is included in the LICENSE file that</span><a href="#l14"></a>
<span id="l15"> * accompanied this code).</span><a href="#l15"></a>
<span id="l16"> *</span><a href="#l16"></a>
<span id="l17"> * You should have received a copy of the GNU General Public License version</span><a href="#l17"></a>
<span id="l18"> * 2 along with this work; if not, write to the Free Software Foundation,</span><a href="#l18"></a>
<span id="l19"> * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.</span><a href="#l19"></a>
<span id="l20"> *</span><a href="#l20"></a>
<span id="l21"> * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA</span><a href="#l21"></a>
<span id="l22"> * or visit www.oracle.com if you need additional information or have any</span><a href="#l22"></a>
<span id="l23"> * questions.</span><a href="#l23"></a>
<span id="l24"> */</span><a href="#l24"></a>
<span id="l25"></span><a href="#l25"></a>
<span id="l26">package sun.security.util;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import sun.security.validator.Validator;</span><a href="#l28"></a>
<span id="l29"></span><a href="#l29"></a>
<span id="l30">import java.io.ByteArrayOutputStream;</span><a href="#l30"></a>
<span id="l31">import java.io.PrintStream;</span><a href="#l31"></a>
<span id="l32">import java.security.AlgorithmParameters;</span><a href="#l32"></a>
<span id="l33">import java.security.CryptoPrimitive;</span><a href="#l33"></a>
<span id="l34">import java.security.Key;</span><a href="#l34"></a>
<span id="l35">import java.security.cert.CertPathValidatorException;</span><a href="#l35"></a>
<span id="l36">import java.security.cert.CertPathValidatorException.BasicReason;</span><a href="#l36"></a>
<span id="l37">import java.security.interfaces.ECKey;</span><a href="#l37"></a>
<span id="l38">import java.security.spec.AlgorithmParameterSpec;</span><a href="#l38"></a>
<span id="l39">import java.security.spec.InvalidParameterSpecException;</span><a href="#l39"></a>
<span id="l40">import java.security.spec.MGF1ParameterSpec;</span><a href="#l40"></a>
<span id="l41">import java.security.spec.PSSParameterSpec;</span><a href="#l41"></a>
<span id="l42">import java.text.SimpleDateFormat;</span><a href="#l42"></a>
<span id="l43">import java.util.ArrayList;</span><a href="#l43"></a>
<span id="l44">import java.util.Arrays;</span><a href="#l44"></a>
<span id="l45">import java.util.Calendar;</span><a href="#l45"></a>
<span id="l46">import java.util.Date;</span><a href="#l46"></a>
<span id="l47">import java.util.HashMap;</span><a href="#l47"></a>
<span id="l48">import java.util.HashSet;</span><a href="#l48"></a>
<span id="l49">import java.util.List;</span><a href="#l49"></a>
<span id="l50">import java.util.Locale;</span><a href="#l50"></a>
<span id="l51">import java.util.Map;</span><a href="#l51"></a>
<span id="l52">import java.util.Set;</span><a href="#l52"></a>
<span id="l53">import java.util.Collection;</span><a href="#l53"></a>
<span id="l54">import java.util.Collections;</span><a href="#l54"></a>
<span id="l55">import java.util.StringTokenizer;</span><a href="#l55"></a>
<span id="l56">import java.util.TimeZone;</span><a href="#l56"></a>
<span id="l57">import java.util.regex.Pattern;</span><a href="#l57"></a>
<span id="l58">import java.util.regex.Matcher;</span><a href="#l58"></a>
<span id="l59"></span><a href="#l59"></a>
<span id="l60">/**</span><a href="#l60"></a>
<span id="l61"> * Algorithm constraints for disabled algorithms property</span><a href="#l61"></a>
<span id="l62"> *</span><a href="#l62"></a>
<span id="l63"> * See the &quot;jdk.certpath.disabledAlgorithms&quot; specification in java.security</span><a href="#l63"></a>
<span id="l64"> * for the syntax of the disabled algorithm string.</span><a href="#l64"></a>
<span id="l65"> */</span><a href="#l65"></a>
<span id="l66">public class DisabledAlgorithmConstraints extends AbstractAlgorithmConstraints {</span><a href="#l66"></a>
<span id="l67">    private static final Debug debug = Debug.getInstance(&quot;certpath&quot;);</span><a href="#l67"></a>
<span id="l68"></span><a href="#l68"></a>
<span id="l69">    // Disabled algorithm security property for certificate path</span><a href="#l69"></a>
<span id="l70">    public static final String PROPERTY_CERTPATH_DISABLED_ALGS =</span><a href="#l70"></a>
<span id="l71">            &quot;jdk.certpath.disabledAlgorithms&quot;;</span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">    // Legacy algorithm security property for certificate path and jar</span><a href="#l73"></a>
<span id="l74">    public static final String PROPERTY_SECURITY_LEGACY_ALGS =</span><a href="#l74"></a>
<span id="l75">            &quot;jdk.security.legacyAlgorithms&quot;;</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">    // Disabled algorithm security property for TLS</span><a href="#l77"></a>
<span id="l78">    public static final String PROPERTY_TLS_DISABLED_ALGS =</span><a href="#l78"></a>
<span id="l79">            &quot;jdk.tls.disabledAlgorithms&quot;;</span><a href="#l79"></a>
<span id="l80"></span><a href="#l80"></a>
<span id="l81">    // Disabled algorithm security property for jar</span><a href="#l81"></a>
<span id="l82">    public static final String PROPERTY_JAR_DISABLED_ALGS =</span><a href="#l82"></a>
<span id="l83">            &quot;jdk.jar.disabledAlgorithms&quot;;</span><a href="#l83"></a>
<span id="l84"></span><a href="#l84"></a>
<span id="l85">    // Property for disabled EC named curves</span><a href="#l85"></a>
<span id="l86">    private static final String PROPERTY_DISABLED_EC_CURVES =</span><a href="#l86"></a>
<span id="l87">            &quot;jdk.disabled.namedCurves&quot;;</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">    private static class CertPathHolder {</span><a href="#l89"></a>
<span id="l90">        static final DisabledAlgorithmConstraints CONSTRAINTS =</span><a href="#l90"></a>
<span id="l91">            new DisabledAlgorithmConstraints(PROPERTY_CERTPATH_DISABLED_ALGS);</span><a href="#l91"></a>
<span id="l92">    }</span><a href="#l92"></a>
<span id="l93"></span><a href="#l93"></a>
<span id="l94">    private static class JarHolder {</span><a href="#l94"></a>
<span id="l95">        static final DisabledAlgorithmConstraints CONSTRAINTS =</span><a href="#l95"></a>
<span id="l96">            new DisabledAlgorithmConstraints(PROPERTY_JAR_DISABLED_ALGS);</span><a href="#l96"></a>
<span id="l97">    }</span><a href="#l97"></a>
<span id="l98"></span><a href="#l98"></a>
<span id="l99">    private final List&lt;String&gt; disabledAlgorithms;</span><a href="#l99"></a>
<span id="l100">    private final Constraints algorithmConstraints;</span><a href="#l100"></a>
<span id="l101"></span><a href="#l101"></a>
<span id="l102">    public static DisabledAlgorithmConstraints certPathConstraints() {</span><a href="#l102"></a>
<span id="l103">        return CertPathHolder.CONSTRAINTS;</span><a href="#l103"></a>
<span id="l104">    }</span><a href="#l104"></a>
<span id="l105"></span><a href="#l105"></a>
<span id="l106">    public static DisabledAlgorithmConstraints jarConstraints() {</span><a href="#l106"></a>
<span id="l107">        return JarHolder.CONSTRAINTS;</span><a href="#l107"></a>
<span id="l108">    }</span><a href="#l108"></a>
<span id="l109"></span><a href="#l109"></a>
<span id="l110">    /**</span><a href="#l110"></a>
<span id="l111">     * Initialize algorithm constraints with the specified security property.</span><a href="#l111"></a>
<span id="l112">     *</span><a href="#l112"></a>
<span id="l113">     * @param propertyName the security property name that define the disabled</span><a href="#l113"></a>
<span id="l114">     *        algorithm constraints</span><a href="#l114"></a>
<span id="l115">     */</span><a href="#l115"></a>
<span id="l116">    public DisabledAlgorithmConstraints(String propertyName) {</span><a href="#l116"></a>
<span id="l117">        this(propertyName, new AlgorithmDecomposer());</span><a href="#l117"></a>
<span id="l118">    }</span><a href="#l118"></a>
<span id="l119"></span><a href="#l119"></a>
<span id="l120">    /**</span><a href="#l120"></a>
<span id="l121">     * Initialize algorithm constraints with the specified security property</span><a href="#l121"></a>
<span id="l122">     * for a specific usage type.</span><a href="#l122"></a>
<span id="l123">     *</span><a href="#l123"></a>
<span id="l124">     * @param propertyName the security property name that define the disabled</span><a href="#l124"></a>
<span id="l125">     *        algorithm constraints</span><a href="#l125"></a>
<span id="l126">     * @param decomposer an alternate AlgorithmDecomposer.</span><a href="#l126"></a>
<span id="l127">     */</span><a href="#l127"></a>
<span id="l128">    public DisabledAlgorithmConstraints(String propertyName,</span><a href="#l128"></a>
<span id="l129">            AlgorithmDecomposer decomposer) {</span><a href="#l129"></a>
<span id="l130">        super(decomposer);</span><a href="#l130"></a>
<span id="l131">        disabledAlgorithms = getAlgorithms(propertyName);</span><a href="#l131"></a>
<span id="l132"></span><a href="#l132"></a>
<span id="l133">        // Check for alias</span><a href="#l133"></a>
<span id="l134">        int ecindex = -1, i = 0;</span><a href="#l134"></a>
<span id="l135">        for (String s : disabledAlgorithms) {</span><a href="#l135"></a>
<span id="l136">            if (s.regionMatches(true, 0,&quot;include &quot;, 0, 8)) {</span><a href="#l136"></a>
<span id="l137">                if (s.regionMatches(true, 8, PROPERTY_DISABLED_EC_CURVES, 0,</span><a href="#l137"></a>
<span id="l138">                        PROPERTY_DISABLED_EC_CURVES.length())) {</span><a href="#l138"></a>
<span id="l139">                    ecindex = i;</span><a href="#l139"></a>
<span id="l140">                    break;</span><a href="#l140"></a>
<span id="l141">                }</span><a href="#l141"></a>
<span id="l142">            }</span><a href="#l142"></a>
<span id="l143">            i++;</span><a href="#l143"></a>
<span id="l144">        }</span><a href="#l144"></a>
<span id="l145">        if (ecindex &gt; -1) {</span><a href="#l145"></a>
<span id="l146">            disabledAlgorithms.remove(ecindex);</span><a href="#l146"></a>
<span id="l147">            disabledAlgorithms.addAll(ecindex,</span><a href="#l147"></a>
<span id="l148">                    getAlgorithms(PROPERTY_DISABLED_EC_CURVES));</span><a href="#l148"></a>
<span id="l149">        }</span><a href="#l149"></a>
<span id="l150">        algorithmConstraints = new Constraints(propertyName, disabledAlgorithms);</span><a href="#l150"></a>
<span id="l151">    }</span><a href="#l151"></a>
<span id="l152"></span><a href="#l152"></a>
<span id="l153">    /*</span><a href="#l153"></a>
<span id="l154">     * This only checks if the algorithm has been completely disabled.  If</span><a href="#l154"></a>
<span id="l155">     * there are keysize or other limit, this method allow the algorithm.</span><a href="#l155"></a>
<span id="l156">     */</span><a href="#l156"></a>
<span id="l157">    @Override</span><a href="#l157"></a>
<span id="l158">    public final boolean permits(Set&lt;CryptoPrimitive&gt; primitives,</span><a href="#l158"></a>
<span id="l159">            String algorithm, AlgorithmParameters parameters) {</span><a href="#l159"></a>
<span id="l160">        if (!checkAlgorithm(disabledAlgorithms, algorithm, decomposer)) {</span><a href="#l160"></a>
<span id="l161">            return false;</span><a href="#l161"></a>
<span id="l162">        }</span><a href="#l162"></a>
<span id="l163"></span><a href="#l163"></a>
<span id="l164">        if (parameters != null) {</span><a href="#l164"></a>
<span id="l165">            return algorithmConstraints.permits(algorithm, parameters);</span><a href="#l165"></a>
<span id="l166">        }</span><a href="#l166"></a>
<span id="l167"></span><a href="#l167"></a>
<span id="l168">        return true;</span><a href="#l168"></a>
<span id="l169">    }</span><a href="#l169"></a>
<span id="l170"></span><a href="#l170"></a>
<span id="l171">    /*</span><a href="#l171"></a>
<span id="l172">     * Checks if the key algorithm has been disabled or constraints have been</span><a href="#l172"></a>
<span id="l173">     * placed on the key.</span><a href="#l173"></a>
<span id="l174">     */</span><a href="#l174"></a>
<span id="l175">    @Override</span><a href="#l175"></a>
<span id="l176">    public final boolean permits(Set&lt;CryptoPrimitive&gt; primitives, Key key) {</span><a href="#l176"></a>
<span id="l177">        return checkConstraints(primitives, &quot;&quot;, key, null);</span><a href="#l177"></a>
<span id="l178">    }</span><a href="#l178"></a>
<span id="l179"></span><a href="#l179"></a>
<span id="l180">    /*</span><a href="#l180"></a>
<span id="l181">     * Checks if the key algorithm has been disabled or if constraints have</span><a href="#l181"></a>
<span id="l182">     * been placed on the key.</span><a href="#l182"></a>
<span id="l183">     */</span><a href="#l183"></a>
<span id="l184">    @Override</span><a href="#l184"></a>
<span id="l185">    public final boolean permits(Set&lt;CryptoPrimitive&gt; primitives,</span><a href="#l185"></a>
<span id="l186">            String algorithm, Key key, AlgorithmParameters parameters) {</span><a href="#l186"></a>
<span id="l187"></span><a href="#l187"></a>
<span id="l188">        if (algorithm == null || algorithm.length() == 0) {</span><a href="#l188"></a>
<span id="l189">            throw new IllegalArgumentException(&quot;No algorithm name specified&quot;);</span><a href="#l189"></a>
<span id="l190">        }</span><a href="#l190"></a>
<span id="l191"></span><a href="#l191"></a>
<span id="l192">        return checkConstraints(primitives, algorithm, key, parameters);</span><a href="#l192"></a>
<span id="l193">    }</span><a href="#l193"></a>
<span id="l194"></span><a href="#l194"></a>
<span id="l195">    public final void permits(String algorithm, AlgorithmParameters ap,</span><a href="#l195"></a>
<span id="l196">        ConstraintsParameters cp) throws CertPathValidatorException {</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">        permits(algorithm, cp);</span><a href="#l198"></a>
<span id="l199">        if (ap != null) {</span><a href="#l199"></a>
<span id="l200">            permits(ap, cp);</span><a href="#l200"></a>
<span id="l201">        }</span><a href="#l201"></a>
<span id="l202">    }</span><a href="#l202"></a>
<span id="l203"></span><a href="#l203"></a>
<span id="l204">    private void permits(AlgorithmParameters ap, ConstraintsParameters cp)</span><a href="#l204"></a>
<span id="l205">        throws CertPathValidatorException {</span><a href="#l205"></a>
<span id="l206"></span><a href="#l206"></a>
<span id="l207">        switch (ap.getAlgorithm().toUpperCase(Locale.ENGLISH)) {</span><a href="#l207"></a>
<span id="l208">            case &quot;RSASSA-PSS&quot;:</span><a href="#l208"></a>
<span id="l209">                permitsPSSParams(ap, cp);</span><a href="#l209"></a>
<span id="l210">                break;</span><a href="#l210"></a>
<span id="l211">            default:</span><a href="#l211"></a>
<span id="l212">                // unknown algorithm, just ignore</span><a href="#l212"></a>
<span id="l213">        }</span><a href="#l213"></a>
<span id="l214">    }</span><a href="#l214"></a>
<span id="l215"></span><a href="#l215"></a>
<span id="l216">    private void permitsPSSParams(AlgorithmParameters ap,</span><a href="#l216"></a>
<span id="l217">        ConstraintsParameters cp) throws CertPathValidatorException {</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">        try {</span><a href="#l219"></a>
<span id="l220">            PSSParameterSpec pssParams =</span><a href="#l220"></a>
<span id="l221">                ap.getParameterSpec(PSSParameterSpec.class);</span><a href="#l221"></a>
<span id="l222">            String digestAlg = pssParams.getDigestAlgorithm();</span><a href="#l222"></a>
<span id="l223">            permits(digestAlg, cp);</span><a href="#l223"></a>
<span id="l224">            AlgorithmParameterSpec mgfParams = pssParams.getMGFParameters();</span><a href="#l224"></a>
<span id="l225">            if (mgfParams instanceof MGF1ParameterSpec) {</span><a href="#l225"></a>
<span id="l226">                String mgfDigestAlg =</span><a href="#l226"></a>
<span id="l227">                    ((MGF1ParameterSpec)mgfParams).getDigestAlgorithm();</span><a href="#l227"></a>
<span id="l228">                if (!mgfDigestAlg.equalsIgnoreCase(digestAlg)) {</span><a href="#l228"></a>
<span id="l229">                    permits(mgfDigestAlg, cp);</span><a href="#l229"></a>
<span id="l230">                }</span><a href="#l230"></a>
<span id="l231">            }</span><a href="#l231"></a>
<span id="l232">        } catch (InvalidParameterSpecException ipse) {</span><a href="#l232"></a>
<span id="l233">            // ignore</span><a href="#l233"></a>
<span id="l234">        }</span><a href="#l234"></a>
<span id="l235">    }</span><a href="#l235"></a>
<span id="l236"></span><a href="#l236"></a>
<span id="l237">    public final void permits(String algorithm, ConstraintsParameters cp)</span><a href="#l237"></a>
<span id="l238">            throws CertPathValidatorException {</span><a href="#l238"></a>
<span id="l239"></span><a href="#l239"></a>
<span id="l240">        // Check if named curves in the key are disabled.</span><a href="#l240"></a>
<span id="l241">        for (Key key : cp.getKeys()) {</span><a href="#l241"></a>
<span id="l242">            for (String curve : getNamedCurveFromKey(key)) {</span><a href="#l242"></a>
<span id="l243">                if (!checkAlgorithm(disabledAlgorithms, curve, decomposer)) {</span><a href="#l243"></a>
<span id="l244">                    throw new CertPathValidatorException(</span><a href="#l244"></a>
<span id="l245">                            &quot;Algorithm constraints check failed on disabled &quot; +</span><a href="#l245"></a>
<span id="l246">                                    &quot;algorithm: &quot; + curve,</span><a href="#l246"></a>
<span id="l247">                            null, null, -1, BasicReason.ALGORITHM_CONSTRAINED);</span><a href="#l247"></a>
<span id="l248">                }</span><a href="#l248"></a>
<span id="l249">            }</span><a href="#l249"></a>
<span id="l250">        }</span><a href="#l250"></a>
<span id="l251"></span><a href="#l251"></a>
<span id="l252">        algorithmConstraints.permits(algorithm, cp);</span><a href="#l252"></a>
<span id="l253">    }</span><a href="#l253"></a>
<span id="l254"></span><a href="#l254"></a>
<span id="l255">    private static List&lt;String&gt; getNamedCurveFromKey(Key key) {</span><a href="#l255"></a>
<span id="l256">        if (key instanceof ECKey) {</span><a href="#l256"></a>
<span id="l257">            NamedCurve nc = CurveDB.lookup(((ECKey)key).getParams());</span><a href="#l257"></a>
<span id="l258">            return (nc == null ? Collections.emptyList()</span><a href="#l258"></a>
<span id="l259">                               : Arrays.asList(CurveDB.getNamesByOID(nc.getObjectId())));</span><a href="#l259"></a>
<span id="l260">        } else {</span><a href="#l260"></a>
<span id="l261">            return Collections.emptyList();</span><a href="#l261"></a>
<span id="l262">        }</span><a href="#l262"></a>
<span id="l263">    }</span><a href="#l263"></a>
<span id="l264"></span><a href="#l264"></a>
<span id="l265">    // Check algorithm constraints with key and algorithm</span><a href="#l265"></a>
<span id="l266">    private boolean checkConstraints(Set&lt;CryptoPrimitive&gt; primitives,</span><a href="#l266"></a>
<span id="l267">            String algorithm, Key key, AlgorithmParameters parameters) {</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">        // check the key parameter, it cannot be null.</span><a href="#l269"></a>
<span id="l270">        if (key == null) {</span><a href="#l270"></a>
<span id="l271">            throw new IllegalArgumentException(&quot;The key cannot be null&quot;);</span><a href="#l271"></a>
<span id="l272">        }</span><a href="#l272"></a>
<span id="l273"></span><a href="#l273"></a>
<span id="l274">        // check the signature algorithm with parameters</span><a href="#l274"></a>
<span id="l275">        if (algorithm != null &amp;&amp; algorithm.length() != 0) {</span><a href="#l275"></a>
<span id="l276">            if (!permits(primitives, algorithm, parameters)) {</span><a href="#l276"></a>
<span id="l277">                return false;</span><a href="#l277"></a>
<span id="l278">            }</span><a href="#l278"></a>
<span id="l279">        }</span><a href="#l279"></a>
<span id="l280"></span><a href="#l280"></a>
<span id="l281">        // check the key algorithm</span><a href="#l281"></a>
<span id="l282">        if (!permits(primitives, key.getAlgorithm(), null)) {</span><a href="#l282"></a>
<span id="l283">            return false;</span><a href="#l283"></a>
<span id="l284">        }</span><a href="#l284"></a>
<span id="l285"></span><a href="#l285"></a>
<span id="l286">        // If this is an elliptic curve, check if it is disabled</span><a href="#l286"></a>
<span id="l287">        for (String curve : getNamedCurveFromKey(key)) {</span><a href="#l287"></a>
<span id="l288">            if (!permits(primitives, curve, null)) {</span><a href="#l288"></a>
<span id="l289">                return false;</span><a href="#l289"></a>
<span id="l290">            }</span><a href="#l290"></a>
<span id="l291">        }</span><a href="#l291"></a>
<span id="l292"></span><a href="#l292"></a>
<span id="l293">        // check the key constraints</span><a href="#l293"></a>
<span id="l294">        return algorithmConstraints.permits(key);</span><a href="#l294"></a>
<span id="l295">    }</span><a href="#l295"></a>
<span id="l296"></span><a href="#l296"></a>
<span id="l297"></span><a href="#l297"></a>
<span id="l298">    /**</span><a href="#l298"></a>
<span id="l299">     * Key and Certificate Constraints</span><a href="#l299"></a>
<span id="l300">     *</span><a href="#l300"></a>
<span id="l301">     * The complete disabling of an algorithm is not handled by Constraints or</span><a href="#l301"></a>
<span id="l302">     * Constraint classes.  That is addressed with</span><a href="#l302"></a>
<span id="l303">     *   permit(Set&lt;CryptoPrimitive&gt;, String, AlgorithmParameters)</span><a href="#l303"></a>
<span id="l304">     *</span><a href="#l304"></a>
<span id="l305">     * When passing a Key to permit(), the boolean return values follow the</span><a href="#l305"></a>
<span id="l306">     * same as the interface class AlgorithmConstraints.permit().  This is to</span><a href="#l306"></a>
<span id="l307">     * maintain compatibility:</span><a href="#l307"></a>
<span id="l308">     * 'true' means the operation is allowed.</span><a href="#l308"></a>
<span id="l309">     * 'false' means it failed the constraints and is disallowed.</span><a href="#l309"></a>
<span id="l310">     *</span><a href="#l310"></a>
<span id="l311">     * When passing ConstraintsParameters through permit(), an exception</span><a href="#l311"></a>
<span id="l312">     * will be thrown on a failure to better identify why the operation was</span><a href="#l312"></a>
<span id="l313">     * disallowed.</span><a href="#l313"></a>
<span id="l314">     */</span><a href="#l314"></a>
<span id="l315"></span><a href="#l315"></a>
<span id="l316">    private static class Constraints {</span><a href="#l316"></a>
<span id="l317">        private Map&lt;String, List&lt;Constraint&gt;&gt; constraintsMap = new HashMap&lt;&gt;();</span><a href="#l317"></a>
<span id="l318"></span><a href="#l318"></a>
<span id="l319">        private static class Holder {</span><a href="#l319"></a>
<span id="l320">            private static final Pattern DENY_AFTER_PATTERN = Pattern.compile(</span><a href="#l320"></a>
<span id="l321">                    &quot;denyAfter\\s+(\\d{4})-(\\d{2})-(\\d{2})&quot;);</span><a href="#l321"></a>
<span id="l322">        }</span><a href="#l322"></a>
<span id="l323"></span><a href="#l323"></a>
<span id="l324">        public Constraints(String propertyName, List&lt;String&gt; constraintArray) {</span><a href="#l324"></a>
<span id="l325">            for (String constraintEntry : constraintArray) {</span><a href="#l325"></a>
<span id="l326">                if (constraintEntry == null || constraintEntry.isEmpty()) {</span><a href="#l326"></a>
<span id="l327">                    continue;</span><a href="#l327"></a>
<span id="l328">                }</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">                constraintEntry = constraintEntry.trim();</span><a href="#l330"></a>
<span id="l331">                if (debug != null) {</span><a href="#l331"></a>
<span id="l332">                    debug.println(&quot;Constraints: &quot; + constraintEntry);</span><a href="#l332"></a>
<span id="l333">                }</span><a href="#l333"></a>
<span id="l334"></span><a href="#l334"></a>
<span id="l335">                // Check if constraint is a complete disabling of an</span><a href="#l335"></a>
<span id="l336">                // algorithm or has conditions.</span><a href="#l336"></a>
<span id="l337">                int space = constraintEntry.indexOf(' ');</span><a href="#l337"></a>
<span id="l338">                String algorithm = AlgorithmDecomposer.hashName(</span><a href="#l338"></a>
<span id="l339">                        ((space &gt; 0 ? constraintEntry.substring(0, space) :</span><a href="#l339"></a>
<span id="l340">                                constraintEntry)));</span><a href="#l340"></a>
<span id="l341">                List&lt;Constraint&gt; constraintList =</span><a href="#l341"></a>
<span id="l342">                        constraintsMap.getOrDefault(</span><a href="#l342"></a>
<span id="l343">                                algorithm.toUpperCase(Locale.ENGLISH),</span><a href="#l343"></a>
<span id="l344">                                new ArrayList&lt;&gt;(1));</span><a href="#l344"></a>
<span id="l345"></span><a href="#l345"></a>
<span id="l346">                // Consider the impact of algorithm aliases.</span><a href="#l346"></a>
<span id="l347">                for (String alias : AlgorithmDecomposer.getAliases(algorithm)) {</span><a href="#l347"></a>
<span id="l348">                    constraintsMap.putIfAbsent(</span><a href="#l348"></a>
<span id="l349">                            alias.toUpperCase(Locale.ENGLISH), constraintList);</span><a href="#l349"></a>
<span id="l350">                }</span><a href="#l350"></a>
<span id="l351"></span><a href="#l351"></a>
<span id="l352">                // If there is no whitespace, it is a algorithm name; however,</span><a href="#l352"></a>
<span id="l353">                // if there is a whitespace, could be a multi-word EC curve too.</span><a href="#l353"></a>
<span id="l354">                if (space &lt;= 0 || CurveDB.lookup(constraintEntry) != null) {</span><a href="#l354"></a>
<span id="l355">                    constraintList.add(new DisabledConstraint(algorithm));</span><a href="#l355"></a>
<span id="l356">                    continue;</span><a href="#l356"></a>
<span id="l357">                }</span><a href="#l357"></a>
<span id="l358"></span><a href="#l358"></a>
<span id="l359">                String policy = constraintEntry.substring(space + 1);</span><a href="#l359"></a>
<span id="l360"></span><a href="#l360"></a>
<span id="l361">                // Convert constraint conditions into Constraint classes</span><a href="#l361"></a>
<span id="l362">                Constraint c, lastConstraint = null;</span><a href="#l362"></a>
<span id="l363">                // Allow only one jdkCA entry per constraint entry</span><a href="#l363"></a>
<span id="l364">                boolean jdkCALimit = false;</span><a href="#l364"></a>
<span id="l365">                // Allow only one denyAfter entry per constraint entry</span><a href="#l365"></a>
<span id="l366">                boolean denyAfterLimit = false;</span><a href="#l366"></a>
<span id="l367"></span><a href="#l367"></a>
<span id="l368">                for (String entry : policy.split(&quot;&amp;&quot;)) {</span><a href="#l368"></a>
<span id="l369">                    entry = entry.trim();</span><a href="#l369"></a>
<span id="l370"></span><a href="#l370"></a>
<span id="l371">                    Matcher matcher;</span><a href="#l371"></a>
<span id="l372">                    if (entry.startsWith(&quot;keySize&quot;)) {</span><a href="#l372"></a>
<span id="l373">                        if (debug != null) {</span><a href="#l373"></a>
<span id="l374">                            debug.println(&quot;Constraints set to keySize: &quot; +</span><a href="#l374"></a>
<span id="l375">                                    entry);</span><a href="#l375"></a>
<span id="l376">                        }</span><a href="#l376"></a>
<span id="l377">                        StringTokenizer tokens = new StringTokenizer(entry);</span><a href="#l377"></a>
<span id="l378">                        if (!&quot;keySize&quot;.equals(tokens.nextToken())) {</span><a href="#l378"></a>
<span id="l379">                            throw new IllegalArgumentException(&quot;Error in &quot; +</span><a href="#l379"></a>
<span id="l380">                                    &quot;security property. Constraint unknown: &quot; +</span><a href="#l380"></a>
<span id="l381">                                    entry);</span><a href="#l381"></a>
<span id="l382">                        }</span><a href="#l382"></a>
<span id="l383">                        c = new KeySizeConstraint(algorithm,</span><a href="#l383"></a>
<span id="l384">                                KeySizeConstraint.Operator.of(tokens.nextToken()),</span><a href="#l384"></a>
<span id="l385">                                Integer.parseInt(tokens.nextToken()));</span><a href="#l385"></a>
<span id="l386"></span><a href="#l386"></a>
<span id="l387">                    } else if (entry.equalsIgnoreCase(&quot;jdkCA&quot;)) {</span><a href="#l387"></a>
<span id="l388">                        if (debug != null) {</span><a href="#l388"></a>
<span id="l389">                            debug.println(&quot;Constraints set to jdkCA.&quot;);</span><a href="#l389"></a>
<span id="l390">                        }</span><a href="#l390"></a>
<span id="l391">                        if (jdkCALimit) {</span><a href="#l391"></a>
<span id="l392">                            throw new IllegalArgumentException(&quot;Only one &quot; +</span><a href="#l392"></a>
<span id="l393">                                    &quot;jdkCA entry allowed in property. &quot; +</span><a href="#l393"></a>
<span id="l394">                                    &quot;Constraint: &quot; + constraintEntry);</span><a href="#l394"></a>
<span id="l395">                        }</span><a href="#l395"></a>
<span id="l396">                        c = new jdkCAConstraint(algorithm);</span><a href="#l396"></a>
<span id="l397">                        jdkCALimit = true;</span><a href="#l397"></a>
<span id="l398"></span><a href="#l398"></a>
<span id="l399">                    } else if (entry.startsWith(&quot;denyAfter&quot;) &amp;&amp;</span><a href="#l399"></a>
<span id="l400">                            (matcher = Holder.DENY_AFTER_PATTERN.matcher(entry))</span><a href="#l400"></a>
<span id="l401">                                    .matches()) {</span><a href="#l401"></a>
<span id="l402">                        if (debug != null) {</span><a href="#l402"></a>
<span id="l403">                            debug.println(&quot;Constraints set to denyAfter&quot;);</span><a href="#l403"></a>
<span id="l404">                        }</span><a href="#l404"></a>
<span id="l405">                        if (denyAfterLimit) {</span><a href="#l405"></a>
<span id="l406">                            throw new IllegalArgumentException(&quot;Only one &quot; +</span><a href="#l406"></a>
<span id="l407">                                    &quot;denyAfter entry allowed in property. &quot; +</span><a href="#l407"></a>
<span id="l408">                                    &quot;Constraint: &quot; + constraintEntry);</span><a href="#l408"></a>
<span id="l409">                        }</span><a href="#l409"></a>
<span id="l410">                        int year = Integer.parseInt(matcher.group(1));</span><a href="#l410"></a>
<span id="l411">                        int month = Integer.parseInt(matcher.group(2));</span><a href="#l411"></a>
<span id="l412">                        int day = Integer.parseInt(matcher.group(3));</span><a href="#l412"></a>
<span id="l413">                        c = new DenyAfterConstraint(algorithm, year, month,</span><a href="#l413"></a>
<span id="l414">                                day);</span><a href="#l414"></a>
<span id="l415">                        denyAfterLimit = true;</span><a href="#l415"></a>
<span id="l416">                    } else if (entry.startsWith(&quot;usage&quot;)) {</span><a href="#l416"></a>
<span id="l417">                        String s[] = (entry.substring(5)).trim().split(&quot; &quot;);</span><a href="#l417"></a>
<span id="l418">                        c = new UsageConstraint(algorithm, s);</span><a href="#l418"></a>
<span id="l419">                        if (debug != null) {</span><a href="#l419"></a>
<span id="l420">                            debug.println(&quot;Constraints usage length is &quot; + s.length);</span><a href="#l420"></a>
<span id="l421">                        }</span><a href="#l421"></a>
<span id="l422">                    } else {</span><a href="#l422"></a>
<span id="l423">                        throw new IllegalArgumentException(&quot;Error in security&quot; +</span><a href="#l423"></a>
<span id="l424">                                &quot; property. Constraint unknown: &quot; + entry);</span><a href="#l424"></a>
<span id="l425">                    }</span><a href="#l425"></a>
<span id="l426"></span><a href="#l426"></a>
<span id="l427">                    // Link multiple conditions for a single constraint</span><a href="#l427"></a>
<span id="l428">                    // into a linked list.</span><a href="#l428"></a>
<span id="l429">                    if (lastConstraint == null) {</span><a href="#l429"></a>
<span id="l430">                        constraintList.add(c);</span><a href="#l430"></a>
<span id="l431">                    } else {</span><a href="#l431"></a>
<span id="l432">                        lastConstraint.nextConstraint = c;</span><a href="#l432"></a>
<span id="l433">                    }</span><a href="#l433"></a>
<span id="l434">                    lastConstraint = c;</span><a href="#l434"></a>
<span id="l435">                }</span><a href="#l435"></a>
<span id="l436">            }</span><a href="#l436"></a>
<span id="l437">        }</span><a href="#l437"></a>
<span id="l438"></span><a href="#l438"></a>
<span id="l439">        // Get applicable constraints based off the algorithm</span><a href="#l439"></a>
<span id="l440">        private List&lt;Constraint&gt; getConstraints(String algorithm) {</span><a href="#l440"></a>
<span id="l441">            return constraintsMap.get(algorithm.toUpperCase(Locale.ENGLISH));</span><a href="#l441"></a>
<span id="l442">        }</span><a href="#l442"></a>
<span id="l443"></span><a href="#l443"></a>
<span id="l444">        // Check if KeySizeConstraints permit the specified key</span><a href="#l444"></a>
<span id="l445">        public boolean permits(Key key) {</span><a href="#l445"></a>
<span id="l446">            List&lt;Constraint&gt; list = getConstraints(key.getAlgorithm());</span><a href="#l446"></a>
<span id="l447">            if (list == null) {</span><a href="#l447"></a>
<span id="l448">                return true;</span><a href="#l448"></a>
<span id="l449">            }</span><a href="#l449"></a>
<span id="l450">            for (Constraint constraint : list) {</span><a href="#l450"></a>
<span id="l451">                if (!constraint.permits(key)) {</span><a href="#l451"></a>
<span id="l452">                    if (debug != null) {</span><a href="#l452"></a>
<span id="l453">                        debug.println(&quot;Constraints: failed key size&quot; +</span><a href="#l453"></a>
<span id="l454">                                &quot;constraint check &quot; + KeyUtil.getKeySize(key));</span><a href="#l454"></a>
<span id="l455">                    }</span><a href="#l455"></a>
<span id="l456">                    return false;</span><a href="#l456"></a>
<span id="l457">                }</span><a href="#l457"></a>
<span id="l458">            }</span><a href="#l458"></a>
<span id="l459">            return true;</span><a href="#l459"></a>
<span id="l460">        }</span><a href="#l460"></a>
<span id="l461"></span><a href="#l461"></a>
<span id="l462">        // Check if constraints permit this AlgorithmParameters.</span><a href="#l462"></a>
<span id="l463">        public boolean permits(String algorithm, AlgorithmParameters aps) {</span><a href="#l463"></a>
<span id="l464">            List&lt;Constraint&gt; list = getConstraints(algorithm);</span><a href="#l464"></a>
<span id="l465">            if (list == null) {</span><a href="#l465"></a>
<span id="l466">                return true;</span><a href="#l466"></a>
<span id="l467">            }</span><a href="#l467"></a>
<span id="l468"></span><a href="#l468"></a>
<span id="l469">            for (Constraint constraint : list) {</span><a href="#l469"></a>
<span id="l470">                if (!constraint.permits(aps)) {</span><a href="#l470"></a>
<span id="l471">                    if (debug != null) {</span><a href="#l471"></a>
<span id="l472">                        debug.println(&quot;Constraints: failed algorithm &quot; +</span><a href="#l472"></a>
<span id="l473">                                &quot;parameters constraint check &quot; + aps);</span><a href="#l473"></a>
<span id="l474">                    }</span><a href="#l474"></a>
<span id="l475"></span><a href="#l475"></a>
<span id="l476">                    return false;</span><a href="#l476"></a>
<span id="l477">                }</span><a href="#l477"></a>
<span id="l478">            }</span><a href="#l478"></a>
<span id="l479"></span><a href="#l479"></a>
<span id="l480">            return true;</span><a href="#l480"></a>
<span id="l481">        }</span><a href="#l481"></a>
<span id="l482"></span><a href="#l482"></a>
<span id="l483">        public void permits(String algorithm, ConstraintsParameters cp)</span><a href="#l483"></a>
<span id="l484">                throws CertPathValidatorException {</span><a href="#l484"></a>
<span id="l485"></span><a href="#l485"></a>
<span id="l486">            if (debug != null) {</span><a href="#l486"></a>
<span id="l487">                debug.println(&quot;Constraints.permits(): &quot; + algorithm + &quot;, &quot;</span><a href="#l487"></a>
<span id="l488">                              + cp.toString());</span><a href="#l488"></a>
<span id="l489">            }</span><a href="#l489"></a>
<span id="l490"></span><a href="#l490"></a>
<span id="l491">            // Get all signature algorithms to check for constraints</span><a href="#l491"></a>
<span id="l492">            Set&lt;String&gt; algorithms = new HashSet&lt;&gt;();</span><a href="#l492"></a>
<span id="l493">            if (algorithm != null) {</span><a href="#l493"></a>
<span id="l494">                algorithms.addAll(AlgorithmDecomposer.decomposeOneHash(algorithm));</span><a href="#l494"></a>
<span id="l495">                algorithms.add(algorithm);</span><a href="#l495"></a>
<span id="l496">            }</span><a href="#l496"></a>
<span id="l497"></span><a href="#l497"></a>
<span id="l498">            for (Key key : cp.getKeys()) {</span><a href="#l498"></a>
<span id="l499">                algorithms.add(key.getAlgorithm());</span><a href="#l499"></a>
<span id="l500">            }</span><a href="#l500"></a>
<span id="l501"></span><a href="#l501"></a>
<span id="l502">            // Check all applicable constraints</span><a href="#l502"></a>
<span id="l503">            for (String alg : algorithms) {</span><a href="#l503"></a>
<span id="l504">                List&lt;Constraint&gt; list = getConstraints(alg);</span><a href="#l504"></a>
<span id="l505">                if (list == null) {</span><a href="#l505"></a>
<span id="l506">                    continue;</span><a href="#l506"></a>
<span id="l507">                }</span><a href="#l507"></a>
<span id="l508">                for (Constraint constraint : list) {</span><a href="#l508"></a>
<span id="l509">                    constraint.permits(cp);</span><a href="#l509"></a>
<span id="l510">                }</span><a href="#l510"></a>
<span id="l511">            }</span><a href="#l511"></a>
<span id="l512">        }</span><a href="#l512"></a>
<span id="l513">    }</span><a href="#l513"></a>
<span id="l514"></span><a href="#l514"></a>
<span id="l515">    /**</span><a href="#l515"></a>
<span id="l516">     * This abstract Constraint class for algorithm-based checking</span><a href="#l516"></a>
<span id="l517">     * may contain one or more constraints.  If the '&amp;' on the {@Security}</span><a href="#l517"></a>
<span id="l518">     * property is used, multiple constraints have been grouped together</span><a href="#l518"></a>
<span id="l519">     * requiring all the constraints to fail for the check to be disallowed.</span><a href="#l519"></a>
<span id="l520">     *</span><a href="#l520"></a>
<span id="l521">     * If the class contains multiple constraints, the next constraint</span><a href="#l521"></a>
<span id="l522">     * is stored in {@code nextConstraint} in linked-list fashion.</span><a href="#l522"></a>
<span id="l523">     */</span><a href="#l523"></a>
<span id="l524">    private abstract static class Constraint {</span><a href="#l524"></a>
<span id="l525">        String algorithm;</span><a href="#l525"></a>
<span id="l526">        Constraint nextConstraint = null;</span><a href="#l526"></a>
<span id="l527"></span><a href="#l527"></a>
<span id="l528">        // operator</span><a href="#l528"></a>
<span id="l529">        enum Operator {</span><a href="#l529"></a>
<span id="l530">            EQ,         // &quot;==&quot;</span><a href="#l530"></a>
<span id="l531">            NE,         // &quot;!=&quot;</span><a href="#l531"></a>
<span id="l532">            LT,         // &quot;&lt;&quot;</span><a href="#l532"></a>
<span id="l533">            LE,         // &quot;&lt;=&quot;</span><a href="#l533"></a>
<span id="l534">            GT,         // &quot;&gt;&quot;</span><a href="#l534"></a>
<span id="l535">            GE;         // &quot;&gt;=&quot;</span><a href="#l535"></a>
<span id="l536"></span><a href="#l536"></a>
<span id="l537">            static Operator of(String s) {</span><a href="#l537"></a>
<span id="l538">                switch (s) {</span><a href="#l538"></a>
<span id="l539">                    case &quot;==&quot;:</span><a href="#l539"></a>
<span id="l540">                        return EQ;</span><a href="#l540"></a>
<span id="l541">                    case &quot;!=&quot;:</span><a href="#l541"></a>
<span id="l542">                        return NE;</span><a href="#l542"></a>
<span id="l543">                    case &quot;&lt;&quot;:</span><a href="#l543"></a>
<span id="l544">                        return LT;</span><a href="#l544"></a>
<span id="l545">                    case &quot;&lt;=&quot;:</span><a href="#l545"></a>
<span id="l546">                        return LE;</span><a href="#l546"></a>
<span id="l547">                    case &quot;&gt;&quot;:</span><a href="#l547"></a>
<span id="l548">                        return GT;</span><a href="#l548"></a>
<span id="l549">                    case &quot;&gt;=&quot;:</span><a href="#l549"></a>
<span id="l550">                        return GE;</span><a href="#l550"></a>
<span id="l551">                }</span><a href="#l551"></a>
<span id="l552"></span><a href="#l552"></a>
<span id="l553">                throw new IllegalArgumentException(&quot;Error in security &quot; +</span><a href="#l553"></a>
<span id="l554">                        &quot;property. &quot; + s + &quot; is not a legal Operator&quot;);</span><a href="#l554"></a>
<span id="l555">            }</span><a href="#l555"></a>
<span id="l556">        }</span><a href="#l556"></a>
<span id="l557"></span><a href="#l557"></a>
<span id="l558">        /**</span><a href="#l558"></a>
<span id="l559">         * Check if an algorithm constraint is permitted with a given key.</span><a href="#l559"></a>
<span id="l560">         *</span><a href="#l560"></a>
<span id="l561">         * If the check inside of {@code permit()} fails, it must call</span><a href="#l561"></a>
<span id="l562">         * {@code next()} with the same {@code Key} parameter passed if</span><a href="#l562"></a>
<span id="l563">         * multiple constraints need to be checked.</span><a href="#l563"></a>
<span id="l564">         *</span><a href="#l564"></a>
<span id="l565">         * @param key Public key</span><a href="#l565"></a>
<span id="l566">         * @return 'true' if constraint is allowed, 'false' if disallowed.</span><a href="#l566"></a>
<span id="l567">         */</span><a href="#l567"></a>
<span id="l568">        public boolean permits(Key key) {</span><a href="#l568"></a>
<span id="l569">            return true;</span><a href="#l569"></a>
<span id="l570">        }</span><a href="#l570"></a>
<span id="l571"></span><a href="#l571"></a>
<span id="l572">        /**</span><a href="#l572"></a>
<span id="l573">         * Check if the algorithm constraint permits a given cryptographic</span><a href="#l573"></a>
<span id="l574">         * parameters.</span><a href="#l574"></a>
<span id="l575">         *</span><a href="#l575"></a>
<span id="l576">         * @param parameters the cryptographic parameters</span><a href="#l576"></a>
<span id="l577">         * @return 'true' if the cryptographic parameters is allowed,</span><a href="#l577"></a>
<span id="l578">         *         'false' ortherwise.</span><a href="#l578"></a>
<span id="l579">         */</span><a href="#l579"></a>
<span id="l580">        public boolean permits(AlgorithmParameters parameters) {</span><a href="#l580"></a>
<span id="l581">            return true;</span><a href="#l581"></a>
<span id="l582">        }</span><a href="#l582"></a>
<span id="l583"></span><a href="#l583"></a>
<span id="l584">        /**</span><a href="#l584"></a>
<span id="l585">         * Check if an algorithm constraint is permitted with a given</span><a href="#l585"></a>
<span id="l586">         * ConstraintsParameters.</span><a href="#l586"></a>
<span id="l587">         *</span><a href="#l587"></a>
<span id="l588">         * If the check inside of {@code permits()} fails, it must call</span><a href="#l588"></a>
<span id="l589">         * {@code next()} with the same {@code ConstraintsParameters}</span><a href="#l589"></a>
<span id="l590">         * parameter passed if multiple constraints need to be checked.</span><a href="#l590"></a>
<span id="l591">         *</span><a href="#l591"></a>
<span id="l592">         * @param cp ConstraintsParameter containing certificate info</span><a href="#l592"></a>
<span id="l593">         * @throws CertPathValidatorException if constraint disallows.</span><a href="#l593"></a>
<span id="l594">         *</span><a href="#l594"></a>
<span id="l595">         */</span><a href="#l595"></a>
<span id="l596">        public abstract void permits(ConstraintsParameters cp)</span><a href="#l596"></a>
<span id="l597">                throws CertPathValidatorException;</span><a href="#l597"></a>
<span id="l598"></span><a href="#l598"></a>
<span id="l599">        /**</span><a href="#l599"></a>
<span id="l600">         * Recursively check if the constraints are allowed.</span><a href="#l600"></a>
<span id="l601">         *</span><a href="#l601"></a>
<span id="l602">         * If {@code nextConstraint} is non-null, this method will</span><a href="#l602"></a>
<span id="l603">         * call {@code nextConstraint}'s {@code permits()} to check if the</span><a href="#l603"></a>
<span id="l604">         * constraint is allowed or denied.  If the constraint's</span><a href="#l604"></a>
<span id="l605">         * {@code permits()} is allowed, this method will exit this and any</span><a href="#l605"></a>
<span id="l606">         * recursive next() calls, returning 'true'.  If the constraints called</span><a href="#l606"></a>
<span id="l607">         * were disallowed, the last constraint will throw</span><a href="#l607"></a>
<span id="l608">         * {@code CertPathValidatorException}.</span><a href="#l608"></a>
<span id="l609">         *</span><a href="#l609"></a>
<span id="l610">         * @param cp ConstraintsParameters</span><a href="#l610"></a>
<span id="l611">         * @return 'true' if constraint allows the operation, 'false' if</span><a href="#l611"></a>
<span id="l612">         * we are at the end of the constraint list or,</span><a href="#l612"></a>
<span id="l613">         * {@code nextConstraint} is null.</span><a href="#l613"></a>
<span id="l614">         */</span><a href="#l614"></a>
<span id="l615">        boolean next(ConstraintsParameters cp)</span><a href="#l615"></a>
<span id="l616">                throws CertPathValidatorException {</span><a href="#l616"></a>
<span id="l617">            if (nextConstraint != null) {</span><a href="#l617"></a>
<span id="l618">                nextConstraint.permits(cp);</span><a href="#l618"></a>
<span id="l619">                return true;</span><a href="#l619"></a>
<span id="l620">            }</span><a href="#l620"></a>
<span id="l621">            return false;</span><a href="#l621"></a>
<span id="l622">        }</span><a href="#l622"></a>
<span id="l623"></span><a href="#l623"></a>
<span id="l624">        /**</span><a href="#l624"></a>
<span id="l625">         * Recursively check if this constraint is allowed,</span><a href="#l625"></a>
<span id="l626">         *</span><a href="#l626"></a>
<span id="l627">         * If {@code nextConstraint} is non-null, this method will</span><a href="#l627"></a>
<span id="l628">         * call {@code nextConstraint}'s {@code permit()} to check if the</span><a href="#l628"></a>
<span id="l629">         * constraint is allowed or denied.  If the constraint's</span><a href="#l629"></a>
<span id="l630">         * {@code permit()} is allowed, this method will exit this and any</span><a href="#l630"></a>
<span id="l631">         * recursive next() calls, returning 'true'.  If the constraints</span><a href="#l631"></a>
<span id="l632">         * called were disallowed the check will exit with 'false'.</span><a href="#l632"></a>
<span id="l633">         *</span><a href="#l633"></a>
<span id="l634">         * @param key Public key</span><a href="#l634"></a>
<span id="l635">         * @return 'true' if constraint allows the operation, 'false' if</span><a href="#l635"></a>
<span id="l636">         * the constraint denies the operation.</span><a href="#l636"></a>
<span id="l637">         */</span><a href="#l637"></a>
<span id="l638">        boolean next(Key key) {</span><a href="#l638"></a>
<span id="l639">            return nextConstraint != null &amp;&amp; nextConstraint.permits(key);</span><a href="#l639"></a>
<span id="l640">        }</span><a href="#l640"></a>
<span id="l641">    }</span><a href="#l641"></a>
<span id="l642"></span><a href="#l642"></a>
<span id="l643">    /*</span><a href="#l643"></a>
<span id="l644">     * This class contains constraints dealing with the certificate chain</span><a href="#l644"></a>
<span id="l645">     * of the certificate.</span><a href="#l645"></a>
<span id="l646">     */</span><a href="#l646"></a>
<span id="l647">    private static class jdkCAConstraint extends Constraint {</span><a href="#l647"></a>
<span id="l648">        jdkCAConstraint(String algo) {</span><a href="#l648"></a>
<span id="l649">            algorithm = algo;</span><a href="#l649"></a>
<span id="l650">        }</span><a href="#l650"></a>
<span id="l651"></span><a href="#l651"></a>
<span id="l652">        /*</span><a href="#l652"></a>
<span id="l653">         * Check if ConstraintsParameters has a trusted match, if it does</span><a href="#l653"></a>
<span id="l654">         * call next() for any following constraints. If it does not, exit</span><a href="#l654"></a>
<span id="l655">         * as this constraint(s) does not restrict the operation.</span><a href="#l655"></a>
<span id="l656">         */</span><a href="#l656"></a>
<span id="l657">        @Override</span><a href="#l657"></a>
<span id="l658">        public void permits(ConstraintsParameters cp)</span><a href="#l658"></a>
<span id="l659">                throws CertPathValidatorException {</span><a href="#l659"></a>
<span id="l660">            if (debug != null) {</span><a href="#l660"></a>
<span id="l661">                debug.println(&quot;jdkCAConstraints.permits(): &quot; + algorithm);</span><a href="#l661"></a>
<span id="l662">            }</span><a href="#l662"></a>
<span id="l663"></span><a href="#l663"></a>
<span id="l664">            // Check if any certs chain back to at least one trust anchor in</span><a href="#l664"></a>
<span id="l665">            // cacerts</span><a href="#l665"></a>
<span id="l666">            if (cp.anchorIsJdkCA()) {</span><a href="#l666"></a>
<span id="l667">                if (next(cp)) {</span><a href="#l667"></a>
<span id="l668">                    return;</span><a href="#l668"></a>
<span id="l669">                }</span><a href="#l669"></a>
<span id="l670">                throw new CertPathValidatorException(</span><a href="#l670"></a>
<span id="l671">                        &quot;Algorithm constraints check failed on certificate &quot; +</span><a href="#l671"></a>
<span id="l672">                        &quot;anchor limits. &quot; + algorithm + cp.extendedExceptionMsg(),</span><a href="#l672"></a>
<span id="l673">                        null, null, -1, BasicReason.ALGORITHM_CONSTRAINED);</span><a href="#l673"></a>
<span id="l674">            }</span><a href="#l674"></a>
<span id="l675">        }</span><a href="#l675"></a>
<span id="l676">    }</span><a href="#l676"></a>
<span id="l677"></span><a href="#l677"></a>
<span id="l678">    /*</span><a href="#l678"></a>
<span id="l679">     * This class handles the denyAfter constraint.  The date is in the UTC/GMT</span><a href="#l679"></a>
<span id="l680">     * timezone.</span><a href="#l680"></a>
<span id="l681">     */</span><a href="#l681"></a>
<span id="l682">    private static class DenyAfterConstraint extends Constraint {</span><a href="#l682"></a>
<span id="l683">        private Date denyAfterDate;</span><a href="#l683"></a>
<span id="l684">        private static final SimpleDateFormat dateFormat =</span><a href="#l684"></a>
<span id="l685">                new SimpleDateFormat(&quot;EEE, MMM d HH:mm:ss z yyyy&quot;);</span><a href="#l685"></a>
<span id="l686"></span><a href="#l686"></a>
<span id="l687">        DenyAfterConstraint(String algo, int year, int month, int day) {</span><a href="#l687"></a>
<span id="l688">            Calendar c;</span><a href="#l688"></a>
<span id="l689"></span><a href="#l689"></a>
<span id="l690">            algorithm = algo;</span><a href="#l690"></a>
<span id="l691"></span><a href="#l691"></a>
<span id="l692">            if (debug != null) {</span><a href="#l692"></a>
<span id="l693">                debug.println(&quot;DenyAfterConstraint read in as:  year &quot; +</span><a href="#l693"></a>
<span id="l694">                        year + &quot;, month = &quot; + month + &quot;, day = &quot; + day);</span><a href="#l694"></a>
<span id="l695">            }</span><a href="#l695"></a>
<span id="l696"></span><a href="#l696"></a>
<span id="l697">            c = new Calendar.Builder().setTimeZone(TimeZone.getTimeZone(&quot;GMT&quot;))</span><a href="#l697"></a>
<span id="l698">                    .setDate(year, month - 1, day).build();</span><a href="#l698"></a>
<span id="l699"></span><a href="#l699"></a>
<span id="l700">            if (year &gt; c.getActualMaximum(Calendar.YEAR) ||</span><a href="#l700"></a>
<span id="l701">                    year &lt; c.getActualMinimum(Calendar.YEAR)) {</span><a href="#l701"></a>
<span id="l702">                throw new IllegalArgumentException(</span><a href="#l702"></a>
<span id="l703">                        &quot;Invalid year given in constraint: &quot; + year);</span><a href="#l703"></a>
<span id="l704">            }</span><a href="#l704"></a>
<span id="l705">            if ((month - 1) &gt; c.getActualMaximum(Calendar.MONTH) ||</span><a href="#l705"></a>
<span id="l706">                    (month - 1) &lt; c.getActualMinimum(Calendar.MONTH)) {</span><a href="#l706"></a>
<span id="l707">                throw new IllegalArgumentException(</span><a href="#l707"></a>
<span id="l708">                        &quot;Invalid month given in constraint: &quot; + month);</span><a href="#l708"></a>
<span id="l709">            }</span><a href="#l709"></a>
<span id="l710">            if (day &gt; c.getActualMaximum(Calendar.DAY_OF_MONTH) ||</span><a href="#l710"></a>
<span id="l711">                    day &lt; c.getActualMinimum(Calendar.DAY_OF_MONTH)) {</span><a href="#l711"></a>
<span id="l712">                throw new IllegalArgumentException(</span><a href="#l712"></a>
<span id="l713">                        &quot;Invalid Day of Month given in constraint: &quot; + day);</span><a href="#l713"></a>
<span id="l714">            }</span><a href="#l714"></a>
<span id="l715"></span><a href="#l715"></a>
<span id="l716">            denyAfterDate = c.getTime();</span><a href="#l716"></a>
<span id="l717">            if (debug != null) {</span><a href="#l717"></a>
<span id="l718">                debug.println(&quot;DenyAfterConstraint date set to: &quot; +</span><a href="#l718"></a>
<span id="l719">                        dateFormat.format(denyAfterDate));</span><a href="#l719"></a>
<span id="l720">            }</span><a href="#l720"></a>
<span id="l721">        }</span><a href="#l721"></a>
<span id="l722"></span><a href="#l722"></a>
<span id="l723">        /*</span><a href="#l723"></a>
<span id="l724">         * Checking that the provided date is not beyond the constraint date.</span><a href="#l724"></a>
<span id="l725">         * The provided date can be the PKIXParameter date if given,</span><a href="#l725"></a>
<span id="l726">         * otherwise it is the current date.</span><a href="#l726"></a>
<span id="l727">         *</span><a href="#l727"></a>
<span id="l728">         * If the constraint disallows, call next() for any following</span><a href="#l728"></a>
<span id="l729">         * constraints. Throw an exception if this is the last constraint.</span><a href="#l729"></a>
<span id="l730">         */</span><a href="#l730"></a>
<span id="l731">        @Override</span><a href="#l731"></a>
<span id="l732">        public void permits(ConstraintsParameters cp)</span><a href="#l732"></a>
<span id="l733">                throws CertPathValidatorException {</span><a href="#l733"></a>
<span id="l734">            Date currentDate;</span><a href="#l734"></a>
<span id="l735">            String errmsg;</span><a href="#l735"></a>
<span id="l736"></span><a href="#l736"></a>
<span id="l737">            if (cp.getDate() != null) {</span><a href="#l737"></a>
<span id="l738">                currentDate = cp.getDate();</span><a href="#l738"></a>
<span id="l739">            } else {</span><a href="#l739"></a>
<span id="l740">                currentDate = new Date();</span><a href="#l740"></a>
<span id="l741">            }</span><a href="#l741"></a>
<span id="l742"></span><a href="#l742"></a>
<span id="l743">            if (!denyAfterDate.after(currentDate)) {</span><a href="#l743"></a>
<span id="l744">                if (next(cp)) {</span><a href="#l744"></a>
<span id="l745">                    return;</span><a href="#l745"></a>
<span id="l746">                }</span><a href="#l746"></a>
<span id="l747">                throw new CertPathValidatorException(</span><a href="#l747"></a>
<span id="l748">                        &quot;denyAfter constraint check failed: &quot; + algorithm +</span><a href="#l748"></a>
<span id="l749">                        &quot; used with Constraint date: &quot; +</span><a href="#l749"></a>
<span id="l750">                        dateFormat.format(denyAfterDate) + &quot;; params date: &quot; +</span><a href="#l750"></a>
<span id="l751">                        dateFormat.format(currentDate) + cp.extendedExceptionMsg(),</span><a href="#l751"></a>
<span id="l752">                        null, null, -1, BasicReason.ALGORITHM_CONSTRAINED);</span><a href="#l752"></a>
<span id="l753">            }</span><a href="#l753"></a>
<span id="l754">        }</span><a href="#l754"></a>
<span id="l755"></span><a href="#l755"></a>
<span id="l756">        /*</span><a href="#l756"></a>
<span id="l757">         * Return result if the constraint's date is beyond the current date</span><a href="#l757"></a>
<span id="l758">         * in UTC timezone.</span><a href="#l758"></a>
<span id="l759">         */</span><a href="#l759"></a>
<span id="l760">        @Override</span><a href="#l760"></a>
<span id="l761">        public boolean permits(Key key) {</span><a href="#l761"></a>
<span id="l762">            if (next(key)) {</span><a href="#l762"></a>
<span id="l763">                return true;</span><a href="#l763"></a>
<span id="l764">            }</span><a href="#l764"></a>
<span id="l765">            if (debug != null) {</span><a href="#l765"></a>
<span id="l766">                debug.println(&quot;DenyAfterConstraints.permits(): &quot; + algorithm);</span><a href="#l766"></a>
<span id="l767">            }</span><a href="#l767"></a>
<span id="l768"></span><a href="#l768"></a>
<span id="l769">            return denyAfterDate.after(new Date());</span><a href="#l769"></a>
<span id="l770">        }</span><a href="#l770"></a>
<span id="l771">    }</span><a href="#l771"></a>
<span id="l772"></span><a href="#l772"></a>
<span id="l773">    /*</span><a href="#l773"></a>
<span id="l774">     * The usage constraint is for the &quot;usage&quot; keyword.  It checks against the</span><a href="#l774"></a>
<span id="l775">     * variant value in ConstraintsParameters.</span><a href="#l775"></a>
<span id="l776">     */</span><a href="#l776"></a>
<span id="l777">    private static class UsageConstraint extends Constraint {</span><a href="#l777"></a>
<span id="l778">        String[] usages;</span><a href="#l778"></a>
<span id="l779"></span><a href="#l779"></a>
<span id="l780">        UsageConstraint(String algorithm, String[] usages) {</span><a href="#l780"></a>
<span id="l781">            this.algorithm = algorithm;</span><a href="#l781"></a>
<span id="l782">            this.usages = usages;</span><a href="#l782"></a>
<span id="l783">        }</span><a href="#l783"></a>
<span id="l784"></span><a href="#l784"></a>
<span id="l785">        @Override</span><a href="#l785"></a>
<span id="l786">        public void permits(ConstraintsParameters cp)</span><a href="#l786"></a>
<span id="l787">                throws CertPathValidatorException {</span><a href="#l787"></a>
<span id="l788">            String variant = cp.getVariant();</span><a href="#l788"></a>
<span id="l789">            for (String usage : usages) {</span><a href="#l789"></a>
<span id="l790"></span><a href="#l790"></a>
<span id="l791">                boolean match = false;</span><a href="#l791"></a>
<span id="l792">                switch (usage.toLowerCase()) {</span><a href="#l792"></a>
<span id="l793">                    case &quot;tlsserver&quot;:</span><a href="#l793"></a>
<span id="l794">                        match = variant.equals(Validator.VAR_TLS_SERVER);</span><a href="#l794"></a>
<span id="l795">                        break;</span><a href="#l795"></a>
<span id="l796">                    case &quot;tlsclient&quot;:</span><a href="#l796"></a>
<span id="l797">                        match = variant.equals(Validator.VAR_TLS_CLIENT);</span><a href="#l797"></a>
<span id="l798">                        break;</span><a href="#l798"></a>
<span id="l799">                    case &quot;signedjar&quot;:</span><a href="#l799"></a>
<span id="l800">                        match =</span><a href="#l800"></a>
<span id="l801">                            variant.equals(Validator.VAR_PLUGIN_CODE_SIGNING) ||</span><a href="#l801"></a>
<span id="l802">                            variant.equals(Validator.VAR_CODE_SIGNING) ||</span><a href="#l802"></a>
<span id="l803">                            variant.equals(Validator.VAR_TSA_SERVER);</span><a href="#l803"></a>
<span id="l804">                        break;</span><a href="#l804"></a>
<span id="l805">                }</span><a href="#l805"></a>
<span id="l806"></span><a href="#l806"></a>
<span id="l807">                if (debug != null) {</span><a href="#l807"></a>
<span id="l808">                    debug.println(&quot;Checking if usage constraint \&quot;&quot; + usage +</span><a href="#l808"></a>
<span id="l809">                            &quot;\&quot; matches \&quot;&quot; + cp.getVariant() + &quot;\&quot;&quot;);</span><a href="#l809"></a>
<span id="l810">                    // Because usage checking can come from many places</span><a href="#l810"></a>
<span id="l811">                    // a stack trace is very helpful.</span><a href="#l811"></a>
<span id="l812">                    ByteArrayOutputStream ba = new ByteArrayOutputStream();</span><a href="#l812"></a>
<span id="l813">                    PrintStream ps = new PrintStream(ba);</span><a href="#l813"></a>
<span id="l814">                    (new Exception()).printStackTrace(ps);</span><a href="#l814"></a>
<span id="l815">                    debug.println(ba.toString());</span><a href="#l815"></a>
<span id="l816">                }</span><a href="#l816"></a>
<span id="l817">                if (match) {</span><a href="#l817"></a>
<span id="l818">                    if (next(cp)) {</span><a href="#l818"></a>
<span id="l819">                        return;</span><a href="#l819"></a>
<span id="l820">                    }</span><a href="#l820"></a>
<span id="l821">                    throw new CertPathValidatorException(&quot;Usage constraint &quot; +</span><a href="#l821"></a>
<span id="l822">                            usage + &quot; check failed: &quot; + algorithm +</span><a href="#l822"></a>
<span id="l823">                            cp.extendedExceptionMsg(),</span><a href="#l823"></a>
<span id="l824">                            null, null, -1, BasicReason.ALGORITHM_CONSTRAINED);</span><a href="#l824"></a>
<span id="l825">                }</span><a href="#l825"></a>
<span id="l826">            }</span><a href="#l826"></a>
<span id="l827">        }</span><a href="#l827"></a>
<span id="l828">    }</span><a href="#l828"></a>
<span id="l829"></span><a href="#l829"></a>
<span id="l830">    /*</span><a href="#l830"></a>
<span id="l831">     * This class contains constraints dealing with the key size</span><a href="#l831"></a>
<span id="l832">     * support limits per algorithm.   e.g.  &quot;keySize &lt;= 1024&quot;</span><a href="#l832"></a>
<span id="l833">     */</span><a href="#l833"></a>
<span id="l834">    private static class KeySizeConstraint extends Constraint {</span><a href="#l834"></a>
<span id="l835"></span><a href="#l835"></a>
<span id="l836">        private int minSize;            // the minimal available key size</span><a href="#l836"></a>
<span id="l837">        private int maxSize;            // the maximal available key size</span><a href="#l837"></a>
<span id="l838">        private int prohibitedSize = -1;    // unavailable key sizes</span><a href="#l838"></a>
<span id="l839">        private int size;</span><a href="#l839"></a>
<span id="l840"></span><a href="#l840"></a>
<span id="l841">        public KeySizeConstraint(String algo, Operator operator, int length) {</span><a href="#l841"></a>
<span id="l842">            algorithm = algo;</span><a href="#l842"></a>
<span id="l843">            switch (operator) {</span><a href="#l843"></a>
<span id="l844">                case EQ:      // an unavailable key size</span><a href="#l844"></a>
<span id="l845">                    this.minSize = 0;</span><a href="#l845"></a>
<span id="l846">                    this.maxSize = Integer.MAX_VALUE;</span><a href="#l846"></a>
<span id="l847">                    prohibitedSize = length;</span><a href="#l847"></a>
<span id="l848">                    break;</span><a href="#l848"></a>
<span id="l849">                case NE:</span><a href="#l849"></a>
<span id="l850">                    this.minSize = length;</span><a href="#l850"></a>
<span id="l851">                    this.maxSize = length;</span><a href="#l851"></a>
<span id="l852">                    break;</span><a href="#l852"></a>
<span id="l853">                case LT:</span><a href="#l853"></a>
<span id="l854">                    this.minSize = length;</span><a href="#l854"></a>
<span id="l855">                    this.maxSize = Integer.MAX_VALUE;</span><a href="#l855"></a>
<span id="l856">                    break;</span><a href="#l856"></a>
<span id="l857">                case LE:</span><a href="#l857"></a>
<span id="l858">                    this.minSize = length + 1;</span><a href="#l858"></a>
<span id="l859">                    this.maxSize = Integer.MAX_VALUE;</span><a href="#l859"></a>
<span id="l860">                    break;</span><a href="#l860"></a>
<span id="l861">                case GT:</span><a href="#l861"></a>
<span id="l862">                    this.minSize = 0;</span><a href="#l862"></a>
<span id="l863">                    this.maxSize = length;</span><a href="#l863"></a>
<span id="l864">                    break;</span><a href="#l864"></a>
<span id="l865">                case GE:</span><a href="#l865"></a>
<span id="l866">                    this.minSize = 0;</span><a href="#l866"></a>
<span id="l867">                    this.maxSize = length &gt; 1 ? (length - 1) : 0;</span><a href="#l867"></a>
<span id="l868">                    break;</span><a href="#l868"></a>
<span id="l869">                default:</span><a href="#l869"></a>
<span id="l870">                    // unlikely to happen</span><a href="#l870"></a>
<span id="l871">                    this.minSize = Integer.MAX_VALUE;</span><a href="#l871"></a>
<span id="l872">                    this.maxSize = -1;</span><a href="#l872"></a>
<span id="l873">            }</span><a href="#l873"></a>
<span id="l874">        }</span><a href="#l874"></a>
<span id="l875"></span><a href="#l875"></a>
<span id="l876">        /*</span><a href="#l876"></a>
<span id="l877">         * For each key, check if each constraint fails and check if there is</span><a href="#l877"></a>
<span id="l878">         * a linked constraint. Any permitted constraint will exit the linked</span><a href="#l878"></a>
<span id="l879">         * list to allow the operation.</span><a href="#l879"></a>
<span id="l880">         */</span><a href="#l880"></a>
<span id="l881">        @Override</span><a href="#l881"></a>
<span id="l882">        public void permits(ConstraintsParameters cp)</span><a href="#l882"></a>
<span id="l883">                throws CertPathValidatorException {</span><a href="#l883"></a>
<span id="l884">            for (Key key : cp.getKeys()) {</span><a href="#l884"></a>
<span id="l885">                if (!permitsImpl(key)) {</span><a href="#l885"></a>
<span id="l886">                    if (nextConstraint != null) {</span><a href="#l886"></a>
<span id="l887">                        nextConstraint.permits(cp);</span><a href="#l887"></a>
<span id="l888">                        continue;</span><a href="#l888"></a>
<span id="l889">                    }</span><a href="#l889"></a>
<span id="l890">                    throw new CertPathValidatorException(</span><a href="#l890"></a>
<span id="l891">                        &quot;Algorithm constraints check failed on keysize limits: &quot; +</span><a href="#l891"></a>
<span id="l892">                        algorithm + &quot; &quot; + KeyUtil.getKeySize(key) + &quot; bit key&quot; +</span><a href="#l892"></a>
<span id="l893">                        cp.extendedExceptionMsg(),</span><a href="#l893"></a>
<span id="l894">                        null, null, -1, BasicReason.ALGORITHM_CONSTRAINED);</span><a href="#l894"></a>
<span id="l895">                }</span><a href="#l895"></a>
<span id="l896">            }</span><a href="#l896"></a>
<span id="l897">        }</span><a href="#l897"></a>
<span id="l898"></span><a href="#l898"></a>
<span id="l899"></span><a href="#l899"></a>
<span id="l900">        // Check if key constraint disable the specified key</span><a href="#l900"></a>
<span id="l901">        // Uses old style permit()</span><a href="#l901"></a>
<span id="l902">        @Override</span><a href="#l902"></a>
<span id="l903">        public boolean permits(Key key) {</span><a href="#l903"></a>
<span id="l904">            // If we recursively find a constraint that permits us to use</span><a href="#l904"></a>
<span id="l905">            // this key, return true and skip any other constraint checks.</span><a href="#l905"></a>
<span id="l906">            if (nextConstraint != null &amp;&amp; nextConstraint.permits(key)) {</span><a href="#l906"></a>
<span id="l907">                return true;</span><a href="#l907"></a>
<span id="l908">            }</span><a href="#l908"></a>
<span id="l909">            if (debug != null) {</span><a href="#l909"></a>
<span id="l910">                debug.println(&quot;KeySizeConstraints.permits(): &quot; + algorithm);</span><a href="#l910"></a>
<span id="l911">            }</span><a href="#l911"></a>
<span id="l912"></span><a href="#l912"></a>
<span id="l913">            return permitsImpl(key);</span><a href="#l913"></a>
<span id="l914">        }</span><a href="#l914"></a>
<span id="l915"></span><a href="#l915"></a>
<span id="l916">        @Override</span><a href="#l916"></a>
<span id="l917">        public boolean permits(AlgorithmParameters parameters) {</span><a href="#l917"></a>
<span id="l918">            String paramAlg = parameters.getAlgorithm();</span><a href="#l918"></a>
<span id="l919">            if (!algorithm.equalsIgnoreCase(parameters.getAlgorithm())) {</span><a href="#l919"></a>
<span id="l920">                // Consider the impact of the algorithm aliases.</span><a href="#l920"></a>
<span id="l921">                Collection&lt;String&gt; aliases =</span><a href="#l921"></a>
<span id="l922">                        AlgorithmDecomposer.getAliases(algorithm);</span><a href="#l922"></a>
<span id="l923">                if (!aliases.contains(paramAlg)) {</span><a href="#l923"></a>
<span id="l924">                    return true;</span><a href="#l924"></a>
<span id="l925">                }</span><a href="#l925"></a>
<span id="l926">            }</span><a href="#l926"></a>
<span id="l927"></span><a href="#l927"></a>
<span id="l928">            int keySize = KeyUtil.getKeySize(parameters);</span><a href="#l928"></a>
<span id="l929">            if (keySize == 0) {</span><a href="#l929"></a>
<span id="l930">                return false;</span><a href="#l930"></a>
<span id="l931">            } else if (keySize &gt; 0) {</span><a href="#l931"></a>
<span id="l932">                return !((keySize &lt; minSize) || (keySize &gt; maxSize) ||</span><a href="#l932"></a>
<span id="l933">                    (prohibitedSize == keySize));</span><a href="#l933"></a>
<span id="l934">            }   // Otherwise, the key size is not accessible or determined.</span><a href="#l934"></a>
<span id="l935">                // Conservatively, please don't disable such keys.</span><a href="#l935"></a>
<span id="l936"></span><a href="#l936"></a>
<span id="l937">            return true;</span><a href="#l937"></a>
<span id="l938">        }</span><a href="#l938"></a>
<span id="l939"></span><a href="#l939"></a>
<span id="l940">        private boolean permitsImpl(Key key) {</span><a href="#l940"></a>
<span id="l941">            // Verify this constraint is for this public key algorithm</span><a href="#l941"></a>
<span id="l942">            if (algorithm.compareToIgnoreCase(key.getAlgorithm()) != 0) {</span><a href="#l942"></a>
<span id="l943">                return true;</span><a href="#l943"></a>
<span id="l944">            }</span><a href="#l944"></a>
<span id="l945"></span><a href="#l945"></a>
<span id="l946">            size = KeyUtil.getKeySize(key);</span><a href="#l946"></a>
<span id="l947">            if (size == 0) {</span><a href="#l947"></a>
<span id="l948">                return false;    // we don't allow any key of size 0.</span><a href="#l948"></a>
<span id="l949">            } else if (size &gt; 0) {</span><a href="#l949"></a>
<span id="l950">                return !((size &lt; minSize) || (size &gt; maxSize) ||</span><a href="#l950"></a>
<span id="l951">                    (prohibitedSize == size));</span><a href="#l951"></a>
<span id="l952">            }   // Otherwise, the key size is not accessible. Conservatively,</span><a href="#l952"></a>
<span id="l953">                // please don't disable such keys.</span><a href="#l953"></a>
<span id="l954"></span><a href="#l954"></a>
<span id="l955">            return true;</span><a href="#l955"></a>
<span id="l956">        }</span><a href="#l956"></a>
<span id="l957">    }</span><a href="#l957"></a>
<span id="l958"></span><a href="#l958"></a>
<span id="l959">    /*</span><a href="#l959"></a>
<span id="l960">     * This constraint is used for the complete disabling of the algorithm.</span><a href="#l960"></a>
<span id="l961">     */</span><a href="#l961"></a>
<span id="l962">    private static class DisabledConstraint extends Constraint {</span><a href="#l962"></a>
<span id="l963">        DisabledConstraint(String algo) {</span><a href="#l963"></a>
<span id="l964">            algorithm = algo;</span><a href="#l964"></a>
<span id="l965">        }</span><a href="#l965"></a>
<span id="l966"></span><a href="#l966"></a>
<span id="l967">        @Override</span><a href="#l967"></a>
<span id="l968">        public void permits(ConstraintsParameters cp)</span><a href="#l968"></a>
<span id="l969">                throws CertPathValidatorException {</span><a href="#l969"></a>
<span id="l970">            throw new CertPathValidatorException(</span><a href="#l970"></a>
<span id="l971">                    &quot;Algorithm constraints check failed on disabled &quot; +</span><a href="#l971"></a>
<span id="l972">                            &quot;algorithm: &quot; + algorithm + cp.extendedExceptionMsg(),</span><a href="#l972"></a>
<span id="l973">                    null, null, -1, BasicReason.ALGORITHM_CONSTRAINED);</span><a href="#l973"></a>
<span id="l974">        }</span><a href="#l974"></a>
<span id="l975"></span><a href="#l975"></a>
<span id="l976">        @Override</span><a href="#l976"></a>
<span id="l977">        public boolean permits(Key key) {</span><a href="#l977"></a>
<span id="l978">            return false;</span><a href="#l978"></a>
<span id="l979">        }</span><a href="#l979"></a>
<span id="l980">    }</span><a href="#l980"></a>
<span id="l981">}</span><a href="#l981"></a></pre>
<div class="sourcelast"></div>
</div>
</div>
</div>



<div class="container"><div class="main footer">
&copy 2007, <script>document.write(new Date().getFullYear())</script> Oracle and/or its affiliates<br/>
<a href="http://openjdk.java.net/legal/tou/">Terms of Use</a>
&#183; <a href="http://www.oracle.com/us/legal/privacy/">Privacy</a>
&#183; <a href="https://openjdk.java.net/legal/openjdk-trademark-notice.html">Trademarks</a>
</div></div>

</body>
</html>

