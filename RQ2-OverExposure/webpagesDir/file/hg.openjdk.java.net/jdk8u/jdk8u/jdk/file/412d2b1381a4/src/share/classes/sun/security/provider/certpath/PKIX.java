<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/provider/certpath/PKIX.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/provider/certpath/PKIX.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIX.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIX.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIX.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIX.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIX.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/provider/certpath/PKIX.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/cc7f20a9beb2/src/share/classes/sun/security/provider/certpath/PKIX.java">cc7f20a9beb2</a> </td>
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
<span id="l2"> * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l25">package sun.security.provider.certpath;</span><a href="#l25"></a>
<span id="l26"></span><a href="#l26"></a>
<span id="l27">import java.security.InvalidAlgorithmParameterException;</span><a href="#l27"></a>
<span id="l28">import java.security.PublicKey;</span><a href="#l28"></a>
<span id="l29">import java.security.Timestamp;</span><a href="#l29"></a>
<span id="l30">import java.security.cert.*;</span><a href="#l30"></a>
<span id="l31">import java.security.interfaces.DSAPublicKey;</span><a href="#l31"></a>
<span id="l32">import java.util.*;</span><a href="#l32"></a>
<span id="l33">import javax.security.auth.x500.X500Principal;</span><a href="#l33"></a>
<span id="l34"></span><a href="#l34"></a>
<span id="l35">import sun.security.util.Debug;</span><a href="#l35"></a>
<span id="l36">import sun.security.validator.Validator;</span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38">/**</span><a href="#l38"></a>
<span id="l39"> * Common utility methods and classes used by the PKIX CertPathValidator and</span><a href="#l39"></a>
<span id="l40"> * CertPathBuilder implementation.</span><a href="#l40"></a>
<span id="l41"> */</span><a href="#l41"></a>
<span id="l42">class PKIX {</span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">    private static final Debug debug = Debug.getInstance(&quot;certpath&quot;);</span><a href="#l44"></a>
<span id="l45"></span><a href="#l45"></a>
<span id="l46">    private PKIX() { }</span><a href="#l46"></a>
<span id="l47"></span><a href="#l47"></a>
<span id="l48">    static boolean isDSAPublicKeyWithoutParams(PublicKey publicKey) {</span><a href="#l48"></a>
<span id="l49">        return (publicKey instanceof DSAPublicKey &amp;&amp;</span><a href="#l49"></a>
<span id="l50">               ((DSAPublicKey)publicKey).getParams() == null);</span><a href="#l50"></a>
<span id="l51">    }</span><a href="#l51"></a>
<span id="l52"></span><a href="#l52"></a>
<span id="l53">    static ValidatorParams checkParams(CertPath cp, CertPathParameters params)</span><a href="#l53"></a>
<span id="l54">        throws InvalidAlgorithmParameterException</span><a href="#l54"></a>
<span id="l55">    {</span><a href="#l55"></a>
<span id="l56">        if (!(params instanceof PKIXParameters)) {</span><a href="#l56"></a>
<span id="l57">            throw new InvalidAlgorithmParameterException(&quot;inappropriate &quot;</span><a href="#l57"></a>
<span id="l58">                + &quot;params, must be an instance of PKIXParameters&quot;);</span><a href="#l58"></a>
<span id="l59">        }</span><a href="#l59"></a>
<span id="l60">        return new ValidatorParams(cp, (PKIXParameters)params);</span><a href="#l60"></a>
<span id="l61">    }</span><a href="#l61"></a>
<span id="l62"></span><a href="#l62"></a>
<span id="l63">    static BuilderParams checkBuilderParams(CertPathParameters params)</span><a href="#l63"></a>
<span id="l64">        throws InvalidAlgorithmParameterException</span><a href="#l64"></a>
<span id="l65">    {</span><a href="#l65"></a>
<span id="l66">        if (!(params instanceof PKIXBuilderParameters)) {</span><a href="#l66"></a>
<span id="l67">            throw new InvalidAlgorithmParameterException(&quot;inappropriate &quot;</span><a href="#l67"></a>
<span id="l68">                + &quot;params, must be an instance of PKIXBuilderParameters&quot;);</span><a href="#l68"></a>
<span id="l69">        }</span><a href="#l69"></a>
<span id="l70">        return new BuilderParams((PKIXBuilderParameters)params);</span><a href="#l70"></a>
<span id="l71">    }</span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">    /**</span><a href="#l73"></a>
<span id="l74">     * PKIXParameters that are shared by the PKIX CertPathValidator</span><a href="#l74"></a>
<span id="l75">     * implementation. Provides additional functionality and avoids</span><a href="#l75"></a>
<span id="l76">     * unnecessary cloning.</span><a href="#l76"></a>
<span id="l77">     */</span><a href="#l77"></a>
<span id="l78">    static class ValidatorParams {</span><a href="#l78"></a>
<span id="l79">        private final PKIXParameters params;</span><a href="#l79"></a>
<span id="l80">        private CertPath certPath;</span><a href="#l80"></a>
<span id="l81">        private List&lt;PKIXCertPathChecker&gt; checkers;</span><a href="#l81"></a>
<span id="l82">        private List&lt;CertStore&gt; stores;</span><a href="#l82"></a>
<span id="l83">        private boolean gotDate;</span><a href="#l83"></a>
<span id="l84">        private Date date;</span><a href="#l84"></a>
<span id="l85">        private Set&lt;String&gt; policies;</span><a href="#l85"></a>
<span id="l86">        private boolean gotConstraints;</span><a href="#l86"></a>
<span id="l87">        private CertSelector constraints;</span><a href="#l87"></a>
<span id="l88">        private Set&lt;TrustAnchor&gt; anchors;</span><a href="#l88"></a>
<span id="l89">        private List&lt;X509Certificate&gt; certs;</span><a href="#l89"></a>
<span id="l90">        private Timestamp timestamp;</span><a href="#l90"></a>
<span id="l91">        private String variant = Validator.VAR_GENERIC;</span><a href="#l91"></a>
<span id="l92"></span><a href="#l92"></a>
<span id="l93">        ValidatorParams(CertPath cp, PKIXParameters params)</span><a href="#l93"></a>
<span id="l94">            throws InvalidAlgorithmParameterException</span><a href="#l94"></a>
<span id="l95">        {</span><a href="#l95"></a>
<span id="l96">            this(params);</span><a href="#l96"></a>
<span id="l97">            if (!cp.getType().equals(&quot;X.509&quot;) &amp;&amp; !cp.getType().equals(&quot;X509&quot;)) {</span><a href="#l97"></a>
<span id="l98">                throw new InvalidAlgorithmParameterException(&quot;inappropriate &quot;</span><a href="#l98"></a>
<span id="l99">                    + &quot;CertPath type specified, must be X.509 or X509&quot;);</span><a href="#l99"></a>
<span id="l100">            }</span><a href="#l100"></a>
<span id="l101">            this.certPath = cp;</span><a href="#l101"></a>
<span id="l102">        }</span><a href="#l102"></a>
<span id="l103"></span><a href="#l103"></a>
<span id="l104">        ValidatorParams(PKIXParameters params)</span><a href="#l104"></a>
<span id="l105">            throws InvalidAlgorithmParameterException</span><a href="#l105"></a>
<span id="l106">        {</span><a href="#l106"></a>
<span id="l107">            if (params instanceof PKIXExtendedParameters) {</span><a href="#l107"></a>
<span id="l108">                timestamp = ((PKIXExtendedParameters) params).getTimestamp();</span><a href="#l108"></a>
<span id="l109">                variant = ((PKIXExtendedParameters) params).getVariant();</span><a href="#l109"></a>
<span id="l110">            }</span><a href="#l110"></a>
<span id="l111"></span><a href="#l111"></a>
<span id="l112">            this.anchors = params.getTrustAnchors();</span><a href="#l112"></a>
<span id="l113">            // Make sure that none of the trust anchors include name constraints</span><a href="#l113"></a>
<span id="l114">            // (not supported).</span><a href="#l114"></a>
<span id="l115">            for (TrustAnchor anchor : this.anchors) {</span><a href="#l115"></a>
<span id="l116">                if (anchor.getNameConstraints() != null) {</span><a href="#l116"></a>
<span id="l117">                    throw new InvalidAlgorithmParameterException</span><a href="#l117"></a>
<span id="l118">                        (&quot;name constraints in trust anchor not supported&quot;);</span><a href="#l118"></a>
<span id="l119">                }</span><a href="#l119"></a>
<span id="l120">            }</span><a href="#l120"></a>
<span id="l121">            this.params = params;</span><a href="#l121"></a>
<span id="l122">        }</span><a href="#l122"></a>
<span id="l123"></span><a href="#l123"></a>
<span id="l124">        CertPath certPath() {</span><a href="#l124"></a>
<span id="l125">            return certPath;</span><a href="#l125"></a>
<span id="l126">        }</span><a href="#l126"></a>
<span id="l127">        // called by CertPathBuilder after path has been built</span><a href="#l127"></a>
<span id="l128">        void setCertPath(CertPath cp) {</span><a href="#l128"></a>
<span id="l129">            this.certPath = cp;</span><a href="#l129"></a>
<span id="l130">        }</span><a href="#l130"></a>
<span id="l131">        List&lt;X509Certificate&gt; certificates() {</span><a href="#l131"></a>
<span id="l132">            if (certs == null) {</span><a href="#l132"></a>
<span id="l133">                if (certPath == null) {</span><a href="#l133"></a>
<span id="l134">                    certs = Collections.emptyList();</span><a href="#l134"></a>
<span id="l135">                } else {</span><a href="#l135"></a>
<span id="l136">                    // Reverse the ordering for validation so that the target</span><a href="#l136"></a>
<span id="l137">                    // cert is the last certificate</span><a href="#l137"></a>
<span id="l138">                    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l138"></a>
<span id="l139">                    List&lt;X509Certificate&gt; xc = new ArrayList&lt;&gt;</span><a href="#l139"></a>
<span id="l140">                        ((List&lt;X509Certificate&gt;)certPath.getCertificates());</span><a href="#l140"></a>
<span id="l141">                    Collections.reverse(xc);</span><a href="#l141"></a>
<span id="l142">                    certs = xc;</span><a href="#l142"></a>
<span id="l143">                }</span><a href="#l143"></a>
<span id="l144">            }</span><a href="#l144"></a>
<span id="l145">            return certs;</span><a href="#l145"></a>
<span id="l146">        }</span><a href="#l146"></a>
<span id="l147">        List&lt;PKIXCertPathChecker&gt; certPathCheckers() {</span><a href="#l147"></a>
<span id="l148">            if (checkers == null)</span><a href="#l148"></a>
<span id="l149">                checkers = params.getCertPathCheckers();</span><a href="#l149"></a>
<span id="l150">            return checkers;</span><a href="#l150"></a>
<span id="l151">        }</span><a href="#l151"></a>
<span id="l152">        List&lt;CertStore&gt; certStores() {</span><a href="#l152"></a>
<span id="l153">            if (stores == null)</span><a href="#l153"></a>
<span id="l154">                stores = params.getCertStores();</span><a href="#l154"></a>
<span id="l155">            return stores;</span><a href="#l155"></a>
<span id="l156">        }</span><a href="#l156"></a>
<span id="l157">        Date date() {</span><a href="#l157"></a>
<span id="l158">            if (!gotDate) {</span><a href="#l158"></a>
<span id="l159">                // use timestamp if checking signed code that is</span><a href="#l159"></a>
<span id="l160">                // timestamped, otherwise use date parameter</span><a href="#l160"></a>
<span id="l161">                if (timestamp != null &amp;&amp;</span><a href="#l161"></a>
<span id="l162">                    (variant.equals(Validator.VAR_CODE_SIGNING) ||</span><a href="#l162"></a>
<span id="l163">                     variant.equals(Validator.VAR_PLUGIN_CODE_SIGNING))) {</span><a href="#l163"></a>
<span id="l164">                    date = timestamp.getTimestamp();</span><a href="#l164"></a>
<span id="l165">                } else {</span><a href="#l165"></a>
<span id="l166">                    date = params.getDate();</span><a href="#l166"></a>
<span id="l167">                    if (date == null)</span><a href="#l167"></a>
<span id="l168">                        date = new Date();</span><a href="#l168"></a>
<span id="l169">                }</span><a href="#l169"></a>
<span id="l170">                gotDate = true;</span><a href="#l170"></a>
<span id="l171">            }</span><a href="#l171"></a>
<span id="l172">            return date;</span><a href="#l172"></a>
<span id="l173">        }</span><a href="#l173"></a>
<span id="l174">        Set&lt;String&gt; initialPolicies() {</span><a href="#l174"></a>
<span id="l175">            if (policies == null)</span><a href="#l175"></a>
<span id="l176">                policies = params.getInitialPolicies();</span><a href="#l176"></a>
<span id="l177">            return policies;</span><a href="#l177"></a>
<span id="l178">        }</span><a href="#l178"></a>
<span id="l179">        CertSelector targetCertConstraints() {</span><a href="#l179"></a>
<span id="l180">            if (!gotConstraints) {</span><a href="#l180"></a>
<span id="l181">                constraints = params.getTargetCertConstraints();</span><a href="#l181"></a>
<span id="l182">                gotConstraints = true;</span><a href="#l182"></a>
<span id="l183">            }</span><a href="#l183"></a>
<span id="l184">            return constraints;</span><a href="#l184"></a>
<span id="l185">        }</span><a href="#l185"></a>
<span id="l186">        Set&lt;TrustAnchor&gt; trustAnchors() {</span><a href="#l186"></a>
<span id="l187">            return anchors;</span><a href="#l187"></a>
<span id="l188">        }</span><a href="#l188"></a>
<span id="l189">        boolean revocationEnabled() {</span><a href="#l189"></a>
<span id="l190">            return params.isRevocationEnabled();</span><a href="#l190"></a>
<span id="l191">        }</span><a href="#l191"></a>
<span id="l192">        boolean policyMappingInhibited() {</span><a href="#l192"></a>
<span id="l193">            return params.isPolicyMappingInhibited();</span><a href="#l193"></a>
<span id="l194">        }</span><a href="#l194"></a>
<span id="l195">        boolean explicitPolicyRequired() {</span><a href="#l195"></a>
<span id="l196">            return params.isExplicitPolicyRequired();</span><a href="#l196"></a>
<span id="l197">        }</span><a href="#l197"></a>
<span id="l198">        boolean policyQualifiersRejected() {</span><a href="#l198"></a>
<span id="l199">            return params.getPolicyQualifiersRejected();</span><a href="#l199"></a>
<span id="l200">        }</span><a href="#l200"></a>
<span id="l201">        String sigProvider() { return params.getSigProvider(); }</span><a href="#l201"></a>
<span id="l202">        boolean anyPolicyInhibited() { return params.isAnyPolicyInhibited(); }</span><a href="#l202"></a>
<span id="l203"></span><a href="#l203"></a>
<span id="l204">        // in rare cases we need access to the original params, for example</span><a href="#l204"></a>
<span id="l205">        // in order to clone CertPathCheckers before building a new chain</span><a href="#l205"></a>
<span id="l206">        PKIXParameters getPKIXParameters() {</span><a href="#l206"></a>
<span id="l207">            return params;</span><a href="#l207"></a>
<span id="l208">        }</span><a href="#l208"></a>
<span id="l209"></span><a href="#l209"></a>
<span id="l210">        String variant() {</span><a href="#l210"></a>
<span id="l211">            return variant;</span><a href="#l211"></a>
<span id="l212">        }</span><a href="#l212"></a>
<span id="l213">    }</span><a href="#l213"></a>
<span id="l214"></span><a href="#l214"></a>
<span id="l215">    static class BuilderParams extends ValidatorParams {</span><a href="#l215"></a>
<span id="l216">        private PKIXBuilderParameters params;</span><a href="#l216"></a>
<span id="l217">        private List&lt;CertStore&gt; stores;</span><a href="#l217"></a>
<span id="l218">        private X500Principal targetSubject;</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">        BuilderParams(PKIXBuilderParameters params)</span><a href="#l220"></a>
<span id="l221">            throws InvalidAlgorithmParameterException</span><a href="#l221"></a>
<span id="l222">        {</span><a href="#l222"></a>
<span id="l223">            super(params);</span><a href="#l223"></a>
<span id="l224">            checkParams(params);</span><a href="#l224"></a>
<span id="l225">        }</span><a href="#l225"></a>
<span id="l226">        private void checkParams(PKIXBuilderParameters params)</span><a href="#l226"></a>
<span id="l227">            throws InvalidAlgorithmParameterException</span><a href="#l227"></a>
<span id="l228">        {</span><a href="#l228"></a>
<span id="l229">            CertSelector sel = targetCertConstraints();</span><a href="#l229"></a>
<span id="l230">            if (!(sel instanceof X509CertSelector)) {</span><a href="#l230"></a>
<span id="l231">                throw new InvalidAlgorithmParameterException(&quot;the &quot;</span><a href="#l231"></a>
<span id="l232">                    + &quot;targetCertConstraints parameter must be an &quot;</span><a href="#l232"></a>
<span id="l233">                    + &quot;X509CertSelector&quot;);</span><a href="#l233"></a>
<span id="l234">            }</span><a href="#l234"></a>
<span id="l235">            this.params = params;</span><a href="#l235"></a>
<span id="l236">            this.targetSubject = getTargetSubject(</span><a href="#l236"></a>
<span id="l237">                certStores(), (X509CertSelector)targetCertConstraints());</span><a href="#l237"></a>
<span id="l238">        }</span><a href="#l238"></a>
<span id="l239">        @Override List&lt;CertStore&gt; certStores() {</span><a href="#l239"></a>
<span id="l240">            if (stores == null) {</span><a href="#l240"></a>
<span id="l241">                // reorder CertStores so that local CertStores are tried first</span><a href="#l241"></a>
<span id="l242">                stores = new ArrayList&lt;&gt;(params.getCertStores());</span><a href="#l242"></a>
<span id="l243">                Collections.sort(stores, new CertStoreComparator());</span><a href="#l243"></a>
<span id="l244">            }</span><a href="#l244"></a>
<span id="l245">            return stores;</span><a href="#l245"></a>
<span id="l246">        }</span><a href="#l246"></a>
<span id="l247">        int maxPathLength() { return params.getMaxPathLength(); }</span><a href="#l247"></a>
<span id="l248">        PKIXBuilderParameters params() { return params; }</span><a href="#l248"></a>
<span id="l249">        X500Principal targetSubject() { return targetSubject; }</span><a href="#l249"></a>
<span id="l250"></span><a href="#l250"></a>
<span id="l251">        /**</span><a href="#l251"></a>
<span id="l252">         * Returns the target subject DN from the first X509Certificate that</span><a href="#l252"></a>
<span id="l253">         * is fetched that matches the specified X509CertSelector.</span><a href="#l253"></a>
<span id="l254">         */</span><a href="#l254"></a>
<span id="l255">        private static X500Principal getTargetSubject(List&lt;CertStore&gt; stores,</span><a href="#l255"></a>
<span id="l256">                                                      X509CertSelector sel)</span><a href="#l256"></a>
<span id="l257">            throws InvalidAlgorithmParameterException</span><a href="#l257"></a>
<span id="l258">        {</span><a href="#l258"></a>
<span id="l259">            X500Principal subject = sel.getSubject();</span><a href="#l259"></a>
<span id="l260">            if (subject != null) {</span><a href="#l260"></a>
<span id="l261">                return subject;</span><a href="#l261"></a>
<span id="l262">            }</span><a href="#l262"></a>
<span id="l263">            X509Certificate cert = sel.getCertificate();</span><a href="#l263"></a>
<span id="l264">            if (cert != null) {</span><a href="#l264"></a>
<span id="l265">                subject = cert.getSubjectX500Principal();</span><a href="#l265"></a>
<span id="l266">            }</span><a href="#l266"></a>
<span id="l267">            if (subject != null) {</span><a href="#l267"></a>
<span id="l268">                return subject;</span><a href="#l268"></a>
<span id="l269">            }</span><a href="#l269"></a>
<span id="l270">            for (CertStore store : stores) {</span><a href="#l270"></a>
<span id="l271">                try {</span><a href="#l271"></a>
<span id="l272">                    Collection&lt;? extends Certificate&gt; certs =</span><a href="#l272"></a>
<span id="l273">                        (Collection&lt;? extends Certificate&gt;)</span><a href="#l273"></a>
<span id="l274">                            store.getCertificates(sel);</span><a href="#l274"></a>
<span id="l275">                    if (!certs.isEmpty()) {</span><a href="#l275"></a>
<span id="l276">                        X509Certificate xc =</span><a href="#l276"></a>
<span id="l277">                            (X509Certificate)certs.iterator().next();</span><a href="#l277"></a>
<span id="l278">                        return xc.getSubjectX500Principal();</span><a href="#l278"></a>
<span id="l279">                    }</span><a href="#l279"></a>
<span id="l280">                } catch (CertStoreException e) {</span><a href="#l280"></a>
<span id="l281">                    // ignore but log it</span><a href="#l281"></a>
<span id="l282">                    if (debug != null) {</span><a href="#l282"></a>
<span id="l283">                        debug.println(&quot;BuilderParams.getTargetSubjectDN: &quot; +</span><a href="#l283"></a>
<span id="l284">                            &quot;non-fatal exception retrieving certs: &quot; + e);</span><a href="#l284"></a>
<span id="l285">                        e.printStackTrace();</span><a href="#l285"></a>
<span id="l286">                    }</span><a href="#l286"></a>
<span id="l287">                }</span><a href="#l287"></a>
<span id="l288">            }</span><a href="#l288"></a>
<span id="l289">            throw new InvalidAlgorithmParameterException</span><a href="#l289"></a>
<span id="l290">                (&quot;Could not determine unique target subject&quot;);</span><a href="#l290"></a>
<span id="l291">        }</span><a href="#l291"></a>
<span id="l292">    }</span><a href="#l292"></a>
<span id="l293"></span><a href="#l293"></a>
<span id="l294">    /**</span><a href="#l294"></a>
<span id="l295">     * A CertStoreException with additional information about the type of</span><a href="#l295"></a>
<span id="l296">     * CertStore that generated the exception.</span><a href="#l296"></a>
<span id="l297">     */</span><a href="#l297"></a>
<span id="l298">    static class CertStoreTypeException extends CertStoreException {</span><a href="#l298"></a>
<span id="l299">        private static final long serialVersionUID = 7463352639238322556L;</span><a href="#l299"></a>
<span id="l300"></span><a href="#l300"></a>
<span id="l301">        private final String type;</span><a href="#l301"></a>
<span id="l302"></span><a href="#l302"></a>
<span id="l303">        CertStoreTypeException(String type, CertStoreException cse) {</span><a href="#l303"></a>
<span id="l304">            super(cse.getMessage(), cse.getCause());</span><a href="#l304"></a>
<span id="l305">            this.type = type;</span><a href="#l305"></a>
<span id="l306">        }</span><a href="#l306"></a>
<span id="l307">        String getType() {</span><a href="#l307"></a>
<span id="l308">            return type;</span><a href="#l308"></a>
<span id="l309">        }</span><a href="#l309"></a>
<span id="l310">    }</span><a href="#l310"></a>
<span id="l311"></span><a href="#l311"></a>
<span id="l312">    /**</span><a href="#l312"></a>
<span id="l313">     * Comparator that orders CertStores so that local CertStores come before</span><a href="#l313"></a>
<span id="l314">     * remote CertStores.</span><a href="#l314"></a>
<span id="l315">     */</span><a href="#l315"></a>
<span id="l316">    private static class CertStoreComparator implements Comparator&lt;CertStore&gt; {</span><a href="#l316"></a>
<span id="l317">        @Override</span><a href="#l317"></a>
<span id="l318">        public int compare(CertStore store1, CertStore store2) {</span><a href="#l318"></a>
<span id="l319">            if (store1.getType().equals(&quot;Collection&quot;) ||</span><a href="#l319"></a>
<span id="l320">                store1.getCertStoreParameters() instanceof</span><a href="#l320"></a>
<span id="l321">                CollectionCertStoreParameters) {</span><a href="#l321"></a>
<span id="l322">                return -1;</span><a href="#l322"></a>
<span id="l323">            } else {</span><a href="#l323"></a>
<span id="l324">                return 1;</span><a href="#l324"></a>
<span id="l325">            }</span><a href="#l325"></a>
<span id="l326">        }</span><a href="#l326"></a>
<span id="l327">    }</span><a href="#l327"></a>
<span id="l328">}</span><a href="#l328"></a></pre>
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

