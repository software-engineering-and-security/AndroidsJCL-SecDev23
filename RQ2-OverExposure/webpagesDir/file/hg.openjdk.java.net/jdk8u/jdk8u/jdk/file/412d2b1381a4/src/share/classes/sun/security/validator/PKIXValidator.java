<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/validator/PKIXValidator.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/sun/security/validator/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/validator/PKIXValidator.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/validator/PKIXValidator.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/validator/PKIXValidator.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/validator/PKIXValidator.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/validator/PKIXValidator.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/validator/PKIXValidator.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/validator/PKIXValidator.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/896bbc5499ff/src/share/classes/sun/security/validator/PKIXValidator.java">896bbc5499ff</a> </td>
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
<span id="l2"> * Copyright (c) 2002, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.security.validator;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.util.*;</span><a href="#l28"></a>
<span id="l29"></span><a href="#l29"></a>
<span id="l30">import java.security.*;</span><a href="#l30"></a>
<span id="l31">import java.security.cert.*;</span><a href="#l31"></a>
<span id="l32"></span><a href="#l32"></a>
<span id="l33">import javax.security.auth.x500.X500Principal;</span><a href="#l33"></a>
<span id="l34">import sun.security.action.GetBooleanAction;</span><a href="#l34"></a>
<span id="l35">import sun.security.provider.certpath.AlgorithmChecker;</span><a href="#l35"></a>
<span id="l36">import sun.security.provider.certpath.PKIXExtendedParameters;</span><a href="#l36"></a>
<span id="l37">import sun.security.util.SecurityProperties;</span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">/**</span><a href="#l39"></a>
<span id="l40"> * Validator implementation built on the PKIX CertPath API. This</span><a href="#l40"></a>
<span id="l41"> * implementation will be emphasized going forward.</span><a href="#l41"></a>
<span id="l42"> * &lt;p&gt;</span><a href="#l42"></a>
<span id="l43"> * Note that the validate() implementation tries to use a PKIX validator</span><a href="#l43"></a>
<span id="l44"> * if that appears possible and a PKIX builder otherwise. This increases</span><a href="#l44"></a>
<span id="l45"> * performance and currently also leads to better exception messages</span><a href="#l45"></a>
<span id="l46"> * in case of failures.</span><a href="#l46"></a>
<span id="l47"> * &lt;p&gt;</span><a href="#l47"></a>
<span id="l48"> * {@code PKIXValidator} objects are immutable once they have been created.</span><a href="#l48"></a>
<span id="l49"> * Please DO NOT add methods that can change the state of an instance once</span><a href="#l49"></a>
<span id="l50"> * it has been created.</span><a href="#l50"></a>
<span id="l51"> *</span><a href="#l51"></a>
<span id="l52"> * @author Andreas Sterbenz</span><a href="#l52"></a>
<span id="l53"> */</span><a href="#l53"></a>
<span id="l54">public final class PKIXValidator extends Validator {</span><a href="#l54"></a>
<span id="l55"></span><a href="#l55"></a>
<span id="l56">    /**</span><a href="#l56"></a>
<span id="l57">     * Flag indicating whether to enable revocation check for the PKIX trust</span><a href="#l57"></a>
<span id="l58">     * manager. Typically, this will only work if the PKIX implementation</span><a href="#l58"></a>
<span id="l59">     * supports CRL distribution points as we do not manually setup CertStores.</span><a href="#l59"></a>
<span id="l60">     */</span><a href="#l60"></a>
<span id="l61">    private final static boolean checkTLSRevocation =</span><a href="#l61"></a>
<span id="l62">        AccessController.doPrivileged</span><a href="#l62"></a>
<span id="l63">            (new GetBooleanAction(&quot;com.sun.net.ssl.checkRevocation&quot;));</span><a href="#l63"></a>
<span id="l64"></span><a href="#l64"></a>
<span id="l65">    // enable use of the validator if possible</span><a href="#l65"></a>
<span id="l66">    private final static boolean TRY_VALIDATOR = true;</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    /**</span><a href="#l68"></a>
<span id="l69">     * System or security property that if set (or set to &quot;true&quot;), allows trust</span><a href="#l69"></a>
<span id="l70">     * anchor certificates to be used if they do not have the proper CA</span><a href="#l70"></a>
<span id="l71">     * extensions. Set to false if prop is not set, or set to any other value.</span><a href="#l71"></a>
<span id="l72">     */</span><a href="#l72"></a>
<span id="l73">    private static final boolean ALLOW_NON_CA_ANCHOR = allowNonCaAnchor();</span><a href="#l73"></a>
<span id="l74">    private static boolean allowNonCaAnchor() {</span><a href="#l74"></a>
<span id="l75">        String prop = SecurityProperties</span><a href="#l75"></a>
<span id="l76">                .privilegedGetOverridable(&quot;jdk.security.allowNonCaAnchor&quot;);</span><a href="#l76"></a>
<span id="l77">        return prop != null &amp;&amp; (prop.isEmpty() || prop.equalsIgnoreCase(&quot;true&quot;));</span><a href="#l77"></a>
<span id="l78">    }</span><a href="#l78"></a>
<span id="l79"></span><a href="#l79"></a>
<span id="l80">    private final Set&lt;X509Certificate&gt; trustedCerts;</span><a href="#l80"></a>
<span id="l81">    private final PKIXBuilderParameters parameterTemplate;</span><a href="#l81"></a>
<span id="l82">    private int certPathLength = -1;</span><a href="#l82"></a>
<span id="l83"></span><a href="#l83"></a>
<span id="l84">    // needed only for the validator</span><a href="#l84"></a>
<span id="l85">    private final Map&lt;X500Principal, List&lt;PublicKey&gt;&gt; trustedSubjects;</span><a href="#l85"></a>
<span id="l86">    private final CertificateFactory factory;</span><a href="#l86"></a>
<span id="l87"></span><a href="#l87"></a>
<span id="l88">    private final boolean plugin;</span><a href="#l88"></a>
<span id="l89"></span><a href="#l89"></a>
<span id="l90">    PKIXValidator(String variant, Collection&lt;X509Certificate&gt; trustedCerts) {</span><a href="#l90"></a>
<span id="l91">        super(TYPE_PKIX, variant);</span><a href="#l91"></a>
<span id="l92">        if (trustedCerts instanceof Set) {</span><a href="#l92"></a>
<span id="l93">            this.trustedCerts = (Set&lt;X509Certificate&gt;)trustedCerts;</span><a href="#l93"></a>
<span id="l94">        } else {</span><a href="#l94"></a>
<span id="l95">            this.trustedCerts = new HashSet&lt;X509Certificate&gt;(trustedCerts);</span><a href="#l95"></a>
<span id="l96">        }</span><a href="#l96"></a>
<span id="l97">        Set&lt;TrustAnchor&gt; trustAnchors = new HashSet&lt;TrustAnchor&gt;();</span><a href="#l97"></a>
<span id="l98">        for (X509Certificate cert : trustedCerts) {</span><a href="#l98"></a>
<span id="l99">            trustAnchors.add(new TrustAnchor(cert, null));</span><a href="#l99"></a>
<span id="l100">        }</span><a href="#l100"></a>
<span id="l101">        try {</span><a href="#l101"></a>
<span id="l102">            parameterTemplate = new PKIXBuilderParameters(trustAnchors, null);</span><a href="#l102"></a>
<span id="l103">        } catch (InvalidAlgorithmParameterException e) {</span><a href="#l103"></a>
<span id="l104">            throw new RuntimeException(&quot;Unexpected error: &quot; + e.toString(), e);</span><a href="#l104"></a>
<span id="l105">        }</span><a href="#l105"></a>
<span id="l106">        setDefaultParameters(variant);</span><a href="#l106"></a>
<span id="l107"></span><a href="#l107"></a>
<span id="l108">        // initCommon();</span><a href="#l108"></a>
<span id="l109">        if (TRY_VALIDATOR) {</span><a href="#l109"></a>
<span id="l110">            if (TRY_VALIDATOR == false) {</span><a href="#l110"></a>
<span id="l111">                return;</span><a href="#l111"></a>
<span id="l112">            }</span><a href="#l112"></a>
<span id="l113">            trustedSubjects = new HashMap&lt;X500Principal, List&lt;PublicKey&gt;&gt;();</span><a href="#l113"></a>
<span id="l114">            for (X509Certificate cert : trustedCerts) {</span><a href="#l114"></a>
<span id="l115">                X500Principal dn = cert.getSubjectX500Principal();</span><a href="#l115"></a>
<span id="l116">                List&lt;PublicKey&gt; keys;</span><a href="#l116"></a>
<span id="l117">                if (trustedSubjects.containsKey(dn)) {</span><a href="#l117"></a>
<span id="l118">                    keys = trustedSubjects.get(dn);</span><a href="#l118"></a>
<span id="l119">                } else {</span><a href="#l119"></a>
<span id="l120">                    keys = new ArrayList&lt;PublicKey&gt;();</span><a href="#l120"></a>
<span id="l121">                    trustedSubjects.put(dn, keys);</span><a href="#l121"></a>
<span id="l122">                }</span><a href="#l122"></a>
<span id="l123">                keys.add(cert.getPublicKey());</span><a href="#l123"></a>
<span id="l124">            }</span><a href="#l124"></a>
<span id="l125">            try {</span><a href="#l125"></a>
<span id="l126">                factory = CertificateFactory.getInstance(&quot;X.509&quot;);</span><a href="#l126"></a>
<span id="l127">            } catch (CertificateException e) {</span><a href="#l127"></a>
<span id="l128">                throw new RuntimeException(&quot;Internal error&quot;, e);</span><a href="#l128"></a>
<span id="l129">            }</span><a href="#l129"></a>
<span id="l130">            plugin = variant.equals(VAR_PLUGIN_CODE_SIGNING);</span><a href="#l130"></a>
<span id="l131">        } else {</span><a href="#l131"></a>
<span id="l132">            plugin = false;</span><a href="#l132"></a>
<span id="l133">        }</span><a href="#l133"></a>
<span id="l134">    }</span><a href="#l134"></a>
<span id="l135"></span><a href="#l135"></a>
<span id="l136">    PKIXValidator(String variant, PKIXBuilderParameters params) {</span><a href="#l136"></a>
<span id="l137">        super(TYPE_PKIX, variant);</span><a href="#l137"></a>
<span id="l138">        trustedCerts = new HashSet&lt;X509Certificate&gt;();</span><a href="#l138"></a>
<span id="l139">        for (TrustAnchor anchor : params.getTrustAnchors()) {</span><a href="#l139"></a>
<span id="l140">            X509Certificate cert = anchor.getTrustedCert();</span><a href="#l140"></a>
<span id="l141">            if (cert != null) {</span><a href="#l141"></a>
<span id="l142">                trustedCerts.add(cert);</span><a href="#l142"></a>
<span id="l143">            }</span><a href="#l143"></a>
<span id="l144">        }</span><a href="#l144"></a>
<span id="l145">        parameterTemplate = params;</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">        // initCommon();</span><a href="#l147"></a>
<span id="l148">        if (TRY_VALIDATOR) {</span><a href="#l148"></a>
<span id="l149">            if (TRY_VALIDATOR == false) {</span><a href="#l149"></a>
<span id="l150">                return;</span><a href="#l150"></a>
<span id="l151">            }</span><a href="#l151"></a>
<span id="l152">            trustedSubjects = new HashMap&lt;X500Principal, List&lt;PublicKey&gt;&gt;();</span><a href="#l152"></a>
<span id="l153">            for (X509Certificate cert : trustedCerts) {</span><a href="#l153"></a>
<span id="l154">                X500Principal dn = cert.getSubjectX500Principal();</span><a href="#l154"></a>
<span id="l155">                List&lt;PublicKey&gt; keys;</span><a href="#l155"></a>
<span id="l156">                if (trustedSubjects.containsKey(dn)) {</span><a href="#l156"></a>
<span id="l157">                    keys = trustedSubjects.get(dn);</span><a href="#l157"></a>
<span id="l158">                } else {</span><a href="#l158"></a>
<span id="l159">                    keys = new ArrayList&lt;PublicKey&gt;();</span><a href="#l159"></a>
<span id="l160">                    trustedSubjects.put(dn, keys);</span><a href="#l160"></a>
<span id="l161">                }</span><a href="#l161"></a>
<span id="l162">                keys.add(cert.getPublicKey());</span><a href="#l162"></a>
<span id="l163">            }</span><a href="#l163"></a>
<span id="l164">            try {</span><a href="#l164"></a>
<span id="l165">                factory = CertificateFactory.getInstance(&quot;X.509&quot;);</span><a href="#l165"></a>
<span id="l166">            } catch (CertificateException e) {</span><a href="#l166"></a>
<span id="l167">                throw new RuntimeException(&quot;Internal error&quot;, e);</span><a href="#l167"></a>
<span id="l168">            }</span><a href="#l168"></a>
<span id="l169">            plugin = variant.equals(VAR_PLUGIN_CODE_SIGNING);</span><a href="#l169"></a>
<span id="l170">        } else {</span><a href="#l170"></a>
<span id="l171">            plugin = false;</span><a href="#l171"></a>
<span id="l172">        }</span><a href="#l172"></a>
<span id="l173">    }</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">    public Collection&lt;X509Certificate&gt; getTrustedCertificates() {</span><a href="#l175"></a>
<span id="l176">        return trustedCerts;</span><a href="#l176"></a>
<span id="l177">    }</span><a href="#l177"></a>
<span id="l178"></span><a href="#l178"></a>
<span id="l179">    /**</span><a href="#l179"></a>
<span id="l180">     * Returns the length of the last certification path that is validated by</span><a href="#l180"></a>
<span id="l181">     * CertPathValidator. This is intended primarily as a callback mechanism</span><a href="#l181"></a>
<span id="l182">     * for PKIXCertPathCheckers to determine the length of the certification</span><a href="#l182"></a>
<span id="l183">     * path that is being validated. It is necessary since engineValidate()</span><a href="#l183"></a>
<span id="l184">     * may modify the length of the path.</span><a href="#l184"></a>
<span id="l185">     *</span><a href="#l185"></a>
<span id="l186">     * @return the length of the last certification path passed to</span><a href="#l186"></a>
<span id="l187">     *   CertPathValidator.validate, or -1 if it has not been invoked yet</span><a href="#l187"></a>
<span id="l188">     */</span><a href="#l188"></a>
<span id="l189">    public int getCertPathLength() { // mutable, should be private</span><a href="#l189"></a>
<span id="l190">        return certPathLength;</span><a href="#l190"></a>
<span id="l191">    }</span><a href="#l191"></a>
<span id="l192"></span><a href="#l192"></a>
<span id="l193">    /**</span><a href="#l193"></a>
<span id="l194">     * Set J2SE global default PKIX parameters. Currently, hardcoded to disable</span><a href="#l194"></a>
<span id="l195">     * revocation checking. In the future, this should be configurable.</span><a href="#l195"></a>
<span id="l196">     */</span><a href="#l196"></a>
<span id="l197">    private void setDefaultParameters(String variant) {</span><a href="#l197"></a>
<span id="l198">        if ((variant == Validator.VAR_TLS_SERVER) ||</span><a href="#l198"></a>
<span id="l199">                (variant == Validator.VAR_TLS_CLIENT)) {</span><a href="#l199"></a>
<span id="l200">            parameterTemplate.setRevocationEnabled(checkTLSRevocation);</span><a href="#l200"></a>
<span id="l201">        } else {</span><a href="#l201"></a>
<span id="l202">            parameterTemplate.setRevocationEnabled(false);</span><a href="#l202"></a>
<span id="l203">        }</span><a href="#l203"></a>
<span id="l204">    }</span><a href="#l204"></a>
<span id="l205"></span><a href="#l205"></a>
<span id="l206">    /**</span><a href="#l206"></a>
<span id="l207">     * Return the PKIX parameters used by this instance. An application may</span><a href="#l207"></a>
<span id="l208">     * modify the parameters but must make sure not to perform any concurrent</span><a href="#l208"></a>
<span id="l209">     * validations.</span><a href="#l209"></a>
<span id="l210">     */</span><a href="#l210"></a>
<span id="l211">    public PKIXBuilderParameters getParameters() { // mutable, should be private</span><a href="#l211"></a>
<span id="l212">        return parameterTemplate;</span><a href="#l212"></a>
<span id="l213">    }</span><a href="#l213"></a>
<span id="l214"></span><a href="#l214"></a>
<span id="l215">    @Override</span><a href="#l215"></a>
<span id="l216">    X509Certificate[] engineValidate(X509Certificate[] chain,</span><a href="#l216"></a>
<span id="l217">            Collection&lt;X509Certificate&gt; otherCerts,</span><a href="#l217"></a>
<span id="l218">            List&lt;byte[]&gt; responseList,</span><a href="#l218"></a>
<span id="l219">            AlgorithmConstraints constraints,</span><a href="#l219"></a>
<span id="l220">            Object parameter) throws CertificateException {</span><a href="#l220"></a>
<span id="l221">        if ((chain == null) || (chain.length == 0)) {</span><a href="#l221"></a>
<span id="l222">            throw new CertificateException</span><a href="#l222"></a>
<span id="l223">                (&quot;null or zero-length certificate chain&quot;);</span><a href="#l223"></a>
<span id="l224">        }</span><a href="#l224"></a>
<span id="l225"></span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">        // Use PKIXExtendedParameters for timestamp and variant additions</span><a href="#l227"></a>
<span id="l228">        PKIXBuilderParameters pkixParameters = null;</span><a href="#l228"></a>
<span id="l229">        try {</span><a href="#l229"></a>
<span id="l230">            pkixParameters = new PKIXExtendedParameters(</span><a href="#l230"></a>
<span id="l231">                    (PKIXBuilderParameters) parameterTemplate.clone(),</span><a href="#l231"></a>
<span id="l232">                    (parameter instanceof Timestamp) ?</span><a href="#l232"></a>
<span id="l233">                            (Timestamp) parameter : null,</span><a href="#l233"></a>
<span id="l234">                    variant);</span><a href="#l234"></a>
<span id="l235">        } catch (InvalidAlgorithmParameterException e) {</span><a href="#l235"></a>
<span id="l236">            // ignore exception</span><a href="#l236"></a>
<span id="l237">        }</span><a href="#l237"></a>
<span id="l238"></span><a href="#l238"></a>
<span id="l239">        // add a new algorithm constraints checker</span><a href="#l239"></a>
<span id="l240">        if (constraints != null) {</span><a href="#l240"></a>
<span id="l241">            pkixParameters.addCertPathChecker(</span><a href="#l241"></a>
<span id="l242">                    new AlgorithmChecker(constraints, variant));</span><a href="#l242"></a>
<span id="l243">        }</span><a href="#l243"></a>
<span id="l244"></span><a href="#l244"></a>
<span id="l245">        // attach it to the PKIXBuilderParameters.</span><a href="#l245"></a>
<span id="l246">        if (!responseList.isEmpty()) {</span><a href="#l246"></a>
<span id="l247">            addResponses(pkixParameters, chain, responseList);</span><a href="#l247"></a>
<span id="l248">        }</span><a href="#l248"></a>
<span id="l249"></span><a href="#l249"></a>
<span id="l250">        if (TRY_VALIDATOR) {</span><a href="#l250"></a>
<span id="l251">            // check that chain is in correct order and check if chain contains</span><a href="#l251"></a>
<span id="l252">            // trust anchor</span><a href="#l252"></a>
<span id="l253">            X500Principal prevIssuer = null;</span><a href="#l253"></a>
<span id="l254">            for (int i = 0; i &lt; chain.length; i++) {</span><a href="#l254"></a>
<span id="l255">                X509Certificate cert = chain[i];</span><a href="#l255"></a>
<span id="l256">                X500Principal dn = cert.getSubjectX500Principal();</span><a href="#l256"></a>
<span id="l257"></span><a href="#l257"></a>
<span id="l258">                if (i == 0) {</span><a href="#l258"></a>
<span id="l259">                    if (trustedCerts.contains(cert)) {</span><a href="#l259"></a>
<span id="l260">                        return new X509Certificate[] {chain[0]};</span><a href="#l260"></a>
<span id="l261">                    }</span><a href="#l261"></a>
<span id="l262">                } else {</span><a href="#l262"></a>
<span id="l263">                    if (!dn.equals(prevIssuer)) {</span><a href="#l263"></a>
<span id="l264">                        // chain is not ordered correctly, call builder instead</span><a href="#l264"></a>
<span id="l265">                        return doBuild(chain, otherCerts, pkixParameters);</span><a href="#l265"></a>
<span id="l266">                    }</span><a href="#l266"></a>
<span id="l267">                    // Check if chain[i] is already trusted. It may be inside</span><a href="#l267"></a>
<span id="l268">                    // trustedCerts, or has the same dn and public key as a cert</span><a href="#l268"></a>
<span id="l269">                    // inside trustedCerts. The latter happens when a CA has</span><a href="#l269"></a>
<span id="l270">                    // updated its cert with a stronger signature algorithm in JRE</span><a href="#l270"></a>
<span id="l271">                    // but the weak one is still in circulation.</span><a href="#l271"></a>
<span id="l272">                    if (trustedCerts.contains(cert) ||          // trusted cert</span><a href="#l272"></a>
<span id="l273">                            (trustedSubjects.containsKey(dn) &amp;&amp; // replacing ...</span><a href="#l273"></a>
<span id="l274">                             trustedSubjects.get(dn).contains(  // ... weak cert</span><a href="#l274"></a>
<span id="l275">                                cert.getPublicKey()))) {</span><a href="#l275"></a>
<span id="l276">                        // Remove and call validator on partial chain [0 .. i-1]</span><a href="#l276"></a>
<span id="l277">                        X509Certificate[] newChain = new X509Certificate[i];</span><a href="#l277"></a>
<span id="l278">                        System.arraycopy(chain, 0, newChain, 0, i);</span><a href="#l278"></a>
<span id="l279">                        return doValidate(newChain, pkixParameters);</span><a href="#l279"></a>
<span id="l280">                    }</span><a href="#l280"></a>
<span id="l281">                }</span><a href="#l281"></a>
<span id="l282">                prevIssuer = cert.getIssuerX500Principal();</span><a href="#l282"></a>
<span id="l283">            }</span><a href="#l283"></a>
<span id="l284"></span><a href="#l284"></a>
<span id="l285">            // apparently issued by trust anchor?</span><a href="#l285"></a>
<span id="l286">            X509Certificate last = chain[chain.length - 1];</span><a href="#l286"></a>
<span id="l287">            X500Principal issuer = last.getIssuerX500Principal();</span><a href="#l287"></a>
<span id="l288">            X500Principal subject = last.getSubjectX500Principal();</span><a href="#l288"></a>
<span id="l289">            if (trustedSubjects.containsKey(issuer) &amp;&amp;</span><a href="#l289"></a>
<span id="l290">                    isSignatureValid(trustedSubjects.get(issuer), last)) {</span><a href="#l290"></a>
<span id="l291">                return doValidate(chain, pkixParameters);</span><a href="#l291"></a>
<span id="l292">            }</span><a href="#l292"></a>
<span id="l293"></span><a href="#l293"></a>
<span id="l294">            // don't fallback to builder if called from plugin/webstart</span><a href="#l294"></a>
<span id="l295">            if (plugin) {</span><a href="#l295"></a>
<span id="l296">                // Validate chain even if no trust anchor is found. This</span><a href="#l296"></a>
<span id="l297">                // allows plugin/webstart to make sure the chain is</span><a href="#l297"></a>
<span id="l298">                // otherwise valid</span><a href="#l298"></a>
<span id="l299">                if (chain.length &gt; 1) {</span><a href="#l299"></a>
<span id="l300">                    X509Certificate[] newChain =</span><a href="#l300"></a>
<span id="l301">                        new X509Certificate[chain.length-1];</span><a href="#l301"></a>
<span id="l302">                    System.arraycopy(chain, 0, newChain, 0, newChain.length);</span><a href="#l302"></a>
<span id="l303"></span><a href="#l303"></a>
<span id="l304">                    // temporarily set last cert as sole trust anchor</span><a href="#l304"></a>
<span id="l305">                    try {</span><a href="#l305"></a>
<span id="l306">                        pkixParameters.setTrustAnchors</span><a href="#l306"></a>
<span id="l307">                            (Collections.singleton(new TrustAnchor</span><a href="#l307"></a>
<span id="l308">                                (chain[chain.length-1], null)));</span><a href="#l308"></a>
<span id="l309">                    } catch (InvalidAlgorithmParameterException iape) {</span><a href="#l309"></a>
<span id="l310">                        // should never occur, but ...</span><a href="#l310"></a>
<span id="l311">                        throw new CertificateException(iape);</span><a href="#l311"></a>
<span id="l312">                    }</span><a href="#l312"></a>
<span id="l313">                    doValidate(newChain, pkixParameters);</span><a href="#l313"></a>
<span id="l314">                }</span><a href="#l314"></a>
<span id="l315">                // if the rest of the chain is valid, throw exception</span><a href="#l315"></a>
<span id="l316">                // indicating no trust anchor was found</span><a href="#l316"></a>
<span id="l317">                throw new ValidatorException</span><a href="#l317"></a>
<span id="l318">                    (ValidatorException.T_NO_TRUST_ANCHOR);</span><a href="#l318"></a>
<span id="l319">            }</span><a href="#l319"></a>
<span id="l320">            // otherwise, fall back to builder</span><a href="#l320"></a>
<span id="l321">        }</span><a href="#l321"></a>
<span id="l322"></span><a href="#l322"></a>
<span id="l323">        return doBuild(chain, otherCerts, pkixParameters);</span><a href="#l323"></a>
<span id="l324">    }</span><a href="#l324"></a>
<span id="l325"></span><a href="#l325"></a>
<span id="l326">    private boolean isSignatureValid(List&lt;PublicKey&gt; keys,</span><a href="#l326"></a>
<span id="l327">            X509Certificate sub) {</span><a href="#l327"></a>
<span id="l328">        if (plugin) {</span><a href="#l328"></a>
<span id="l329">            for (PublicKey key: keys) {</span><a href="#l329"></a>
<span id="l330">                try {</span><a href="#l330"></a>
<span id="l331">                    sub.verify(key);</span><a href="#l331"></a>
<span id="l332">                    return true;</span><a href="#l332"></a>
<span id="l333">                } catch (Exception ex) {</span><a href="#l333"></a>
<span id="l334">                    continue;</span><a href="#l334"></a>
<span id="l335">                }</span><a href="#l335"></a>
<span id="l336">            }</span><a href="#l336"></a>
<span id="l337">            return false;</span><a href="#l337"></a>
<span id="l338">        }</span><a href="#l338"></a>
<span id="l339">        return true; // only check if PLUGIN is set</span><a href="#l339"></a>
<span id="l340">    }</span><a href="#l340"></a>
<span id="l341"></span><a href="#l341"></a>
<span id="l342">    private static X509Certificate[] toArray(CertPath path, TrustAnchor anchor)</span><a href="#l342"></a>
<span id="l343">            throws CertificateException {</span><a href="#l343"></a>
<span id="l344">        X509Certificate trustedCert = anchor.getTrustedCert();</span><a href="#l344"></a>
<span id="l345">        if (trustedCert == null) {</span><a href="#l345"></a>
<span id="l346">            throw new ValidatorException</span><a href="#l346"></a>
<span id="l347">                (&quot;TrustAnchor must be specified as certificate&quot;);</span><a href="#l347"></a>
<span id="l348">        }</span><a href="#l348"></a>
<span id="l349"></span><a href="#l349"></a>
<span id="l350">        verifyTrustAnchor(trustedCert);</span><a href="#l350"></a>
<span id="l351"></span><a href="#l351"></a>
<span id="l352">        List&lt;? extends java.security.cert.Certificate&gt; list =</span><a href="#l352"></a>
<span id="l353">                                                path.getCertificates();</span><a href="#l353"></a>
<span id="l354">        X509Certificate[] chain = new X509Certificate[list.size() + 1];</span><a href="#l354"></a>
<span id="l355">        list.toArray(chain);</span><a href="#l355"></a>
<span id="l356">        chain[chain.length - 1] = trustedCert;</span><a href="#l356"></a>
<span id="l357">        return chain;</span><a href="#l357"></a>
<span id="l358">    }</span><a href="#l358"></a>
<span id="l359"></span><a href="#l359"></a>
<span id="l360">    /**</span><a href="#l360"></a>
<span id="l361">     * Set the check date (for debugging).</span><a href="#l361"></a>
<span id="l362">     */</span><a href="#l362"></a>
<span id="l363">    private void setDate(PKIXBuilderParameters params) {</span><a href="#l363"></a>
<span id="l364">        @SuppressWarnings(&quot;deprecation&quot;)</span><a href="#l364"></a>
<span id="l365">        Date date = validationDate;</span><a href="#l365"></a>
<span id="l366">        if (date != null) {</span><a href="#l366"></a>
<span id="l367">            params.setDate(date);</span><a href="#l367"></a>
<span id="l368">        }</span><a href="#l368"></a>
<span id="l369">    }</span><a href="#l369"></a>
<span id="l370"></span><a href="#l370"></a>
<span id="l371">    private X509Certificate[] doValidate(X509Certificate[] chain,</span><a href="#l371"></a>
<span id="l372">            PKIXBuilderParameters params) throws CertificateException {</span><a href="#l372"></a>
<span id="l373">        try {</span><a href="#l373"></a>
<span id="l374">            setDate(params);</span><a href="#l374"></a>
<span id="l375"></span><a href="#l375"></a>
<span id="l376">            // do the validation</span><a href="#l376"></a>
<span id="l377">            CertPathValidator validator = CertPathValidator.getInstance(&quot;PKIX&quot;);</span><a href="#l377"></a>
<span id="l378">            CertPath path = factory.generateCertPath(Arrays.asList(chain));</span><a href="#l378"></a>
<span id="l379">            certPathLength = chain.length;</span><a href="#l379"></a>
<span id="l380">            PKIXCertPathValidatorResult result =</span><a href="#l380"></a>
<span id="l381">                (PKIXCertPathValidatorResult)validator.validate(path, params);</span><a href="#l381"></a>
<span id="l382"></span><a href="#l382"></a>
<span id="l383">            return toArray(path, result.getTrustAnchor());</span><a href="#l383"></a>
<span id="l384">        } catch (GeneralSecurityException e) {</span><a href="#l384"></a>
<span id="l385">            throw new ValidatorException</span><a href="#l385"></a>
<span id="l386">                (&quot;PKIX path validation failed: &quot; + e.toString(), e);</span><a href="#l386"></a>
<span id="l387">        }</span><a href="#l387"></a>
<span id="l388">    }</span><a href="#l388"></a>
<span id="l389"></span><a href="#l389"></a>
<span id="l390">    /**</span><a href="#l390"></a>
<span id="l391">     * Verify that a trust anchor certificate is a CA certificate.</span><a href="#l391"></a>
<span id="l392">     */</span><a href="#l392"></a>
<span id="l393">    private static void verifyTrustAnchor(X509Certificate trustedCert)</span><a href="#l393"></a>
<span id="l394">        throws ValidatorException {</span><a href="#l394"></a>
<span id="l395"></span><a href="#l395"></a>
<span id="l396">        // skip check if jdk.security.allowNonCAAnchor system property is set</span><a href="#l396"></a>
<span id="l397">        if (ALLOW_NON_CA_ANCHOR) {</span><a href="#l397"></a>
<span id="l398">            return;</span><a href="#l398"></a>
<span id="l399">        }</span><a href="#l399"></a>
<span id="l400"></span><a href="#l400"></a>
<span id="l401">        // allow v1 trust anchor certificates</span><a href="#l401"></a>
<span id="l402">        if (trustedCert.getVersion() &lt; 3) {</span><a href="#l402"></a>
<span id="l403">            return;</span><a href="#l403"></a>
<span id="l404">        }</span><a href="#l404"></a>
<span id="l405"></span><a href="#l405"></a>
<span id="l406">        // check that the BasicConstraints cA field is not set to false</span><a href="#l406"></a>
<span id="l407">        if (trustedCert.getBasicConstraints() == -1) {</span><a href="#l407"></a>
<span id="l408">            throw new ValidatorException</span><a href="#l408"></a>
<span id="l409">                (&quot;TrustAnchor with subject \&quot;&quot; +</span><a href="#l409"></a>
<span id="l410">                 trustedCert.getSubjectX500Principal() +</span><a href="#l410"></a>
<span id="l411">                 &quot;\&quot; is not a CA certificate&quot;);</span><a href="#l411"></a>
<span id="l412">        }</span><a href="#l412"></a>
<span id="l413"></span><a href="#l413"></a>
<span id="l414">        // check that the KeyUsage extension, if included, asserts the</span><a href="#l414"></a>
<span id="l415">        // keyCertSign bit</span><a href="#l415"></a>
<span id="l416">        boolean[] keyUsageBits = trustedCert.getKeyUsage();</span><a href="#l416"></a>
<span id="l417">        if (keyUsageBits != null &amp;&amp; !keyUsageBits[5]) {</span><a href="#l417"></a>
<span id="l418">            throw new ValidatorException</span><a href="#l418"></a>
<span id="l419">                (&quot;TrustAnchor with subject \&quot;&quot; +</span><a href="#l419"></a>
<span id="l420">                 trustedCert.getSubjectX500Principal() +</span><a href="#l420"></a>
<span id="l421">                 &quot;\&quot; does not have keyCertSign bit set in KeyUsage extension&quot;);</span><a href="#l421"></a>
<span id="l422">        }</span><a href="#l422"></a>
<span id="l423">    }</span><a href="#l423"></a>
<span id="l424"></span><a href="#l424"></a>
<span id="l425">    private X509Certificate[] doBuild(X509Certificate[] chain,</span><a href="#l425"></a>
<span id="l426">        Collection&lt;X509Certificate&gt; otherCerts,</span><a href="#l426"></a>
<span id="l427">        PKIXBuilderParameters params) throws CertificateException {</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">        try {</span><a href="#l429"></a>
<span id="l430">            setDate(params);</span><a href="#l430"></a>
<span id="l431"></span><a href="#l431"></a>
<span id="l432">            // setup target constraints</span><a href="#l432"></a>
<span id="l433">            X509CertSelector selector = new X509CertSelector();</span><a href="#l433"></a>
<span id="l434">            selector.setCertificate(chain[0]);</span><a href="#l434"></a>
<span id="l435">            params.setTargetCertConstraints(selector);</span><a href="#l435"></a>
<span id="l436"></span><a href="#l436"></a>
<span id="l437">            // setup CertStores</span><a href="#l437"></a>
<span id="l438">            Collection&lt;X509Certificate&gt; certs =</span><a href="#l438"></a>
<span id="l439">                                        new ArrayList&lt;X509Certificate&gt;();</span><a href="#l439"></a>
<span id="l440">            certs.addAll(Arrays.asList(chain));</span><a href="#l440"></a>
<span id="l441">            if (otherCerts != null) {</span><a href="#l441"></a>
<span id="l442">                certs.addAll(otherCerts);</span><a href="#l442"></a>
<span id="l443">            }</span><a href="#l443"></a>
<span id="l444">            CertStore store = CertStore.getInstance(&quot;Collection&quot;,</span><a href="#l444"></a>
<span id="l445">                                new CollectionCertStoreParameters(certs));</span><a href="#l445"></a>
<span id="l446">            params.addCertStore(store);</span><a href="#l446"></a>
<span id="l447"></span><a href="#l447"></a>
<span id="l448">            // do the build</span><a href="#l448"></a>
<span id="l449">            CertPathBuilder builder = CertPathBuilder.getInstance(&quot;PKIX&quot;);</span><a href="#l449"></a>
<span id="l450">            PKIXCertPathBuilderResult result =</span><a href="#l450"></a>
<span id="l451">                (PKIXCertPathBuilderResult)builder.build(params);</span><a href="#l451"></a>
<span id="l452"></span><a href="#l452"></a>
<span id="l453">            return toArray(result.getCertPath(), result.getTrustAnchor());</span><a href="#l453"></a>
<span id="l454">        } catch (GeneralSecurityException e) {</span><a href="#l454"></a>
<span id="l455">            throw new ValidatorException</span><a href="#l455"></a>
<span id="l456">                (&quot;PKIX path building failed: &quot; + e.toString(), e);</span><a href="#l456"></a>
<span id="l457">        }</span><a href="#l457"></a>
<span id="l458">    }</span><a href="#l458"></a>
<span id="l459"></span><a href="#l459"></a>
<span id="l460">    /**</span><a href="#l460"></a>
<span id="l461">     * For OCSP Stapling, add responses that came in during the handshake</span><a href="#l461"></a>
<span id="l462">     * into a {@code PKIXRevocationChecker} so we can evaluate them.</span><a href="#l462"></a>
<span id="l463">     *</span><a href="#l463"></a>
<span id="l464">     * @param pkixParams the pkixParameters object that will be used in</span><a href="#l464"></a>
<span id="l465">     * path validation.</span><a href="#l465"></a>
<span id="l466">     * @param chain the chain of certificates to verify</span><a href="#l466"></a>
<span id="l467">     * @param responseList a {@code List} of zero or more byte arrays, each</span><a href="#l467"></a>
<span id="l468">     * one being a DER-encoded OCSP response (per RFC 6960).  Entries</span><a href="#l468"></a>
<span id="l469">     * in the List must match the order of the certificates in the</span><a href="#l469"></a>
<span id="l470">     * chain parameter.</span><a href="#l470"></a>
<span id="l471">     */</span><a href="#l471"></a>
<span id="l472">    private static void addResponses(PKIXBuilderParameters pkixParams,</span><a href="#l472"></a>
<span id="l473">            X509Certificate[] chain, List&lt;byte[]&gt; responseList) {</span><a href="#l473"></a>
<span id="l474"></span><a href="#l474"></a>
<span id="l475">        if (pkixParams.isRevocationEnabled()) {</span><a href="#l475"></a>
<span id="l476">            try {</span><a href="#l476"></a>
<span id="l477">                // Make a modifiable copy of the CertPathChecker list</span><a href="#l477"></a>
<span id="l478">                PKIXRevocationChecker revChecker = null;</span><a href="#l478"></a>
<span id="l479">                List&lt;PKIXCertPathChecker&gt; checkerList =</span><a href="#l479"></a>
<span id="l480">                        new ArrayList&lt;&gt;(pkixParams.getCertPathCheckers());</span><a href="#l480"></a>
<span id="l481"></span><a href="#l481"></a>
<span id="l482">                // Find the first PKIXRevocationChecker in the list</span><a href="#l482"></a>
<span id="l483">                for (PKIXCertPathChecker checker : checkerList) {</span><a href="#l483"></a>
<span id="l484">                    if (checker instanceof PKIXRevocationChecker) {</span><a href="#l484"></a>
<span id="l485">                        revChecker = (PKIXRevocationChecker)checker;</span><a href="#l485"></a>
<span id="l486">                        break;</span><a href="#l486"></a>
<span id="l487">                    }</span><a href="#l487"></a>
<span id="l488">                }</span><a href="#l488"></a>
<span id="l489"></span><a href="#l489"></a>
<span id="l490">                // If we still haven't found one, make one</span><a href="#l490"></a>
<span id="l491">                if (revChecker == null) {</span><a href="#l491"></a>
<span id="l492">                    revChecker = (PKIXRevocationChecker)CertPathValidator.</span><a href="#l492"></a>
<span id="l493">                            getInstance(&quot;PKIX&quot;).getRevocationChecker();</span><a href="#l493"></a>
<span id="l494">                    checkerList.add(revChecker);</span><a href="#l494"></a>
<span id="l495">                }</span><a href="#l495"></a>
<span id="l496"></span><a href="#l496"></a>
<span id="l497">                // Each response in the list should be in parallel with</span><a href="#l497"></a>
<span id="l498">                // the certificate list.  If there is a zero-length response</span><a href="#l498"></a>
<span id="l499">                // treat it as being absent.  If the user has provided their</span><a href="#l499"></a>
<span id="l500">                // own PKIXRevocationChecker with pre-populated responses, do</span><a href="#l500"></a>
<span id="l501">                // not overwrite them with the ones from the handshake.</span><a href="#l501"></a>
<span id="l502">                Map&lt;X509Certificate, byte[]&gt; responseMap =</span><a href="#l502"></a>
<span id="l503">                        revChecker.getOcspResponses();</span><a href="#l503"></a>
<span id="l504">                int limit = Integer.min(chain.length, responseList.size());</span><a href="#l504"></a>
<span id="l505">                for (int idx = 0; idx &lt; limit; idx++) {</span><a href="#l505"></a>
<span id="l506">                    byte[] respBytes = responseList.get(idx);</span><a href="#l506"></a>
<span id="l507">                    if (respBytes != null &amp;&amp; respBytes.length &gt; 0 &amp;&amp;</span><a href="#l507"></a>
<span id="l508">                            !responseMap.containsKey(chain[idx])) {</span><a href="#l508"></a>
<span id="l509">                        responseMap.put(chain[idx], respBytes);</span><a href="#l509"></a>
<span id="l510">                    }</span><a href="#l510"></a>
<span id="l511">                }</span><a href="#l511"></a>
<span id="l512"></span><a href="#l512"></a>
<span id="l513">                // Add the responses and push it all back into the</span><a href="#l513"></a>
<span id="l514">                // PKIXBuilderParameters</span><a href="#l514"></a>
<span id="l515">                revChecker.setOcspResponses(responseMap);</span><a href="#l515"></a>
<span id="l516">                pkixParams.setCertPathCheckers(checkerList);</span><a href="#l516"></a>
<span id="l517">            } catch (NoSuchAlgorithmException exc) {</span><a href="#l517"></a>
<span id="l518">                // This should not occur, but if it does happen then</span><a href="#l518"></a>
<span id="l519">                // stapled OCSP responses won't be part of revocation checking.</span><a href="#l519"></a>
<span id="l520">                // Clients can still fall back to other means of revocation</span><a href="#l520"></a>
<span id="l521">                // checking.</span><a href="#l521"></a>
<span id="l522">            }</span><a href="#l522"></a>
<span id="l523">        }</span><a href="#l523"></a>
<span id="l524">    }</span><a href="#l524"></a>
<span id="l525">}</span><a href="#l525"></a></pre>
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

