<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/provider/certpath/PKIXCertPathValidator.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/provider/certpath/PKIXCertPathValidator.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIXCertPathValidator.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIXCertPathValidator.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIXCertPathValidator.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIXCertPathValidator.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/PKIXCertPathValidator.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/provider/certpath/PKIXCertPathValidator.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/707ea8cc6462/src/share/classes/sun/security/provider/certpath/PKIXCertPathValidator.java">707ea8cc6462</a> </td>
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
<span id="l2"> * Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.security.provider.certpath;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.security.InvalidAlgorithmParameterException;</span><a href="#l29"></a>
<span id="l30">import java.security.cert.*;</span><a href="#l30"></a>
<span id="l31">import java.util.*;</span><a href="#l31"></a>
<span id="l32"></span><a href="#l32"></a>
<span id="l33">import sun.security.provider.certpath.PKIX.ValidatorParams;</span><a href="#l33"></a>
<span id="l34">import sun.security.x509.X509CertImpl;</span><a href="#l34"></a>
<span id="l35">import sun.security.util.Debug;</span><a href="#l35"></a>
<span id="l36"></span><a href="#l36"></a>
<span id="l37">/**</span><a href="#l37"></a>
<span id="l38"> * This class implements the PKIX validation algorithm for certification</span><a href="#l38"></a>
<span id="l39"> * paths consisting exclusively of &lt;code&gt;X509Certificates&lt;/code&gt;. It uses</span><a href="#l39"></a>
<span id="l40"> * the specified input parameter set (which must be a</span><a href="#l40"></a>
<span id="l41"> * &lt;code&gt;PKIXParameters&lt;/code&gt; object).</span><a href="#l41"></a>
<span id="l42"> *</span><a href="#l42"></a>
<span id="l43"> * @since       1.4</span><a href="#l43"></a>
<span id="l44"> * @author      Yassir Elley</span><a href="#l44"></a>
<span id="l45"> */</span><a href="#l45"></a>
<span id="l46">public final class PKIXCertPathValidator extends CertPathValidatorSpi {</span><a href="#l46"></a>
<span id="l47"></span><a href="#l47"></a>
<span id="l48">    private static final Debug debug = Debug.getInstance(&quot;certpath&quot;);</span><a href="#l48"></a>
<span id="l49"></span><a href="#l49"></a>
<span id="l50">    /**</span><a href="#l50"></a>
<span id="l51">     * Default constructor.</span><a href="#l51"></a>
<span id="l52">     */</span><a href="#l52"></a>
<span id="l53">    public PKIXCertPathValidator() {}</span><a href="#l53"></a>
<span id="l54"></span><a href="#l54"></a>
<span id="l55">    @Override</span><a href="#l55"></a>
<span id="l56">    public CertPathChecker engineGetRevocationChecker() {</span><a href="#l56"></a>
<span id="l57">        return new RevocationChecker();</span><a href="#l57"></a>
<span id="l58">    }</span><a href="#l58"></a>
<span id="l59"></span><a href="#l59"></a>
<span id="l60">    /**</span><a href="#l60"></a>
<span id="l61">     * Validates a certification path consisting exclusively of</span><a href="#l61"></a>
<span id="l62">     * &lt;code&gt;X509Certificate&lt;/code&gt;s using the PKIX validation algorithm,</span><a href="#l62"></a>
<span id="l63">     * which uses the specified input parameter set.</span><a href="#l63"></a>
<span id="l64">     * The input parameter set must be a &lt;code&gt;PKIXParameters&lt;/code&gt; object.</span><a href="#l64"></a>
<span id="l65">     *</span><a href="#l65"></a>
<span id="l66">     * @param cp the X509 certification path</span><a href="#l66"></a>
<span id="l67">     * @param params the input PKIX parameter set</span><a href="#l67"></a>
<span id="l68">     * @return the result</span><a href="#l68"></a>
<span id="l69">     * @throws CertPathValidatorException if cert path does not validate.</span><a href="#l69"></a>
<span id="l70">     * @throws InvalidAlgorithmParameterException if the specified</span><a href="#l70"></a>
<span id="l71">     *         parameters are inappropriate for this CertPathValidator</span><a href="#l71"></a>
<span id="l72">     */</span><a href="#l72"></a>
<span id="l73">    @Override</span><a href="#l73"></a>
<span id="l74">    public CertPathValidatorResult engineValidate(CertPath cp,</span><a href="#l74"></a>
<span id="l75">                                                  CertPathParameters params)</span><a href="#l75"></a>
<span id="l76">        throws CertPathValidatorException, InvalidAlgorithmParameterException</span><a href="#l76"></a>
<span id="l77">    {</span><a href="#l77"></a>
<span id="l78">        ValidatorParams valParams = PKIX.checkParams(cp, params);</span><a href="#l78"></a>
<span id="l79">        return validate(valParams);</span><a href="#l79"></a>
<span id="l80">    }</span><a href="#l80"></a>
<span id="l81"></span><a href="#l81"></a>
<span id="l82">    private static PKIXCertPathValidatorResult validate(ValidatorParams params)</span><a href="#l82"></a>
<span id="l83">        throws CertPathValidatorException</span><a href="#l83"></a>
<span id="l84">    {</span><a href="#l84"></a>
<span id="l85">        if (debug != null)</span><a href="#l85"></a>
<span id="l86">            debug.println(&quot;PKIXCertPathValidator.engineValidate()...&quot;);</span><a href="#l86"></a>
<span id="l87"></span><a href="#l87"></a>
<span id="l88">        // Retrieve the first certificate in the certpath</span><a href="#l88"></a>
<span id="l89">        // (to be used later in pre-screening)</span><a href="#l89"></a>
<span id="l90">        AdaptableX509CertSelector selector = null;</span><a href="#l90"></a>
<span id="l91">        List&lt;X509Certificate&gt; certList = params.certificates();</span><a href="#l91"></a>
<span id="l92">        if (!certList.isEmpty()) {</span><a href="#l92"></a>
<span id="l93">            selector = new AdaptableX509CertSelector();</span><a href="#l93"></a>
<span id="l94">            X509Certificate firstCert = certList.get(0);</span><a href="#l94"></a>
<span id="l95">            // check trusted certificate's subject</span><a href="#l95"></a>
<span id="l96">            selector.setSubject(firstCert.getIssuerX500Principal());</span><a href="#l96"></a>
<span id="l97">            /*</span><a href="#l97"></a>
<span id="l98">             * Facilitate certification path construction with authority</span><a href="#l98"></a>
<span id="l99">             * key identifier and subject key identifier.</span><a href="#l99"></a>
<span id="l100">             */</span><a href="#l100"></a>
<span id="l101">            try {</span><a href="#l101"></a>
<span id="l102">                X509CertImpl firstCertImpl = X509CertImpl.toImpl(firstCert);</span><a href="#l102"></a>
<span id="l103">                selector.setSkiAndSerialNumber(</span><a href="#l103"></a>
<span id="l104">                            firstCertImpl.getAuthorityKeyIdentifierExtension());</span><a href="#l104"></a>
<span id="l105">            } catch (CertificateException | IOException e) {</span><a href="#l105"></a>
<span id="l106">                // ignore</span><a href="#l106"></a>
<span id="l107">            }</span><a href="#l107"></a>
<span id="l108">        }</span><a href="#l108"></a>
<span id="l109"></span><a href="#l109"></a>
<span id="l110">        CertPathValidatorException lastException = null;</span><a href="#l110"></a>
<span id="l111"></span><a href="#l111"></a>
<span id="l112">        // We iterate through the set of trust anchors until we find</span><a href="#l112"></a>
<span id="l113">        // one that works at which time we stop iterating</span><a href="#l113"></a>
<span id="l114">        for (TrustAnchor anchor : params.trustAnchors()) {</span><a href="#l114"></a>
<span id="l115">            X509Certificate trustedCert = anchor.getTrustedCert();</span><a href="#l115"></a>
<span id="l116">            if (trustedCert != null) {</span><a href="#l116"></a>
<span id="l117">                // if this trust anchor is not worth trying,</span><a href="#l117"></a>
<span id="l118">                // we move on to the next one</span><a href="#l118"></a>
<span id="l119">                if (selector != null &amp;&amp; !selector.match(trustedCert)) {</span><a href="#l119"></a>
<span id="l120">                    if (debug != null) {</span><a href="#l120"></a>
<span id="l121">                        debug.println(&quot;NO - don't try this trustedCert&quot;);</span><a href="#l121"></a>
<span id="l122">                    }</span><a href="#l122"></a>
<span id="l123">                    continue;</span><a href="#l123"></a>
<span id="l124">                }</span><a href="#l124"></a>
<span id="l125"></span><a href="#l125"></a>
<span id="l126">                if (debug != null) {</span><a href="#l126"></a>
<span id="l127">                    debug.println(&quot;YES - try this trustedCert&quot;);</span><a href="#l127"></a>
<span id="l128">                    debug.println(&quot;anchor.getTrustedCert().&quot;</span><a href="#l128"></a>
<span id="l129">                        + &quot;getSubjectX500Principal() = &quot;</span><a href="#l129"></a>
<span id="l130">                        + trustedCert.getSubjectX500Principal());</span><a href="#l130"></a>
<span id="l131">                }</span><a href="#l131"></a>
<span id="l132">            } else {</span><a href="#l132"></a>
<span id="l133">                if (debug != null) {</span><a href="#l133"></a>
<span id="l134">                    debug.println(&quot;PKIXCertPathValidator.engineValidate(): &quot;</span><a href="#l134"></a>
<span id="l135">                        + &quot;anchor.getTrustedCert() == null&quot;);</span><a href="#l135"></a>
<span id="l136">                }</span><a href="#l136"></a>
<span id="l137">            }</span><a href="#l137"></a>
<span id="l138"></span><a href="#l138"></a>
<span id="l139">            try {</span><a href="#l139"></a>
<span id="l140">                return validate(anchor, params);</span><a href="#l140"></a>
<span id="l141">            } catch (CertPathValidatorException cpe) {</span><a href="#l141"></a>
<span id="l142">                // remember this exception</span><a href="#l142"></a>
<span id="l143">                lastException = cpe;</span><a href="#l143"></a>
<span id="l144">            }</span><a href="#l144"></a>
<span id="l145">        }</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">        // could not find a trust anchor that verified</span><a href="#l147"></a>
<span id="l148">        // (a) if we did a validation and it failed, use that exception</span><a href="#l148"></a>
<span id="l149">        if (lastException != null) {</span><a href="#l149"></a>
<span id="l150">            throw lastException;</span><a href="#l150"></a>
<span id="l151">        }</span><a href="#l151"></a>
<span id="l152">        // (b) otherwise, generate new exception</span><a href="#l152"></a>
<span id="l153">        throw new CertPathValidatorException</span><a href="#l153"></a>
<span id="l154">            (&quot;Path does not chain with any of the trust anchors&quot;,</span><a href="#l154"></a>
<span id="l155">             null, null, -1, PKIXReason.NO_TRUST_ANCHOR);</span><a href="#l155"></a>
<span id="l156">    }</span><a href="#l156"></a>
<span id="l157"></span><a href="#l157"></a>
<span id="l158">    private static PKIXCertPathValidatorResult validate(TrustAnchor anchor,</span><a href="#l158"></a>
<span id="l159">                                                        ValidatorParams params)</span><a href="#l159"></a>
<span id="l160">        throws CertPathValidatorException</span><a href="#l160"></a>
<span id="l161">    {</span><a href="#l161"></a>
<span id="l162">        // check if anchor is untrusted</span><a href="#l162"></a>
<span id="l163">        UntrustedChecker untrustedChecker = new UntrustedChecker();</span><a href="#l163"></a>
<span id="l164">        X509Certificate anchorCert = anchor.getTrustedCert();</span><a href="#l164"></a>
<span id="l165">        if (anchorCert != null) {</span><a href="#l165"></a>
<span id="l166">            untrustedChecker.check(anchorCert);</span><a href="#l166"></a>
<span id="l167">        }</span><a href="#l167"></a>
<span id="l168"></span><a href="#l168"></a>
<span id="l169">        int certPathLen = params.certificates().size();</span><a href="#l169"></a>
<span id="l170"></span><a href="#l170"></a>
<span id="l171">        // create PKIXCertPathCheckers</span><a href="#l171"></a>
<span id="l172">        List&lt;PKIXCertPathChecker&gt; certPathCheckers = new ArrayList&lt;&gt;();</span><a href="#l172"></a>
<span id="l173">        // add standard checkers that we will be using</span><a href="#l173"></a>
<span id="l174">        certPathCheckers.add(untrustedChecker);</span><a href="#l174"></a>
<span id="l175">        certPathCheckers.add(new AlgorithmChecker(anchor, null, params.date(),</span><a href="#l175"></a>
<span id="l176">                params.variant()));</span><a href="#l176"></a>
<span id="l177">        certPathCheckers.add(new KeyChecker(certPathLen,</span><a href="#l177"></a>
<span id="l178">                                            params.targetCertConstraints()));</span><a href="#l178"></a>
<span id="l179">        certPathCheckers.add(new ConstraintsChecker(certPathLen));</span><a href="#l179"></a>
<span id="l180">        PolicyNodeImpl rootNode =</span><a href="#l180"></a>
<span id="l181">            new PolicyNodeImpl(null, PolicyChecker.ANY_POLICY, null, false,</span><a href="#l181"></a>
<span id="l182">                               Collections.singleton(PolicyChecker.ANY_POLICY),</span><a href="#l182"></a>
<span id="l183">                               false);</span><a href="#l183"></a>
<span id="l184">        PolicyChecker pc = new PolicyChecker(params.initialPolicies(),</span><a href="#l184"></a>
<span id="l185">                                             certPathLen,</span><a href="#l185"></a>
<span id="l186">                                             params.explicitPolicyRequired(),</span><a href="#l186"></a>
<span id="l187">                                             params.policyMappingInhibited(),</span><a href="#l187"></a>
<span id="l188">                                             params.anyPolicyInhibited(),</span><a href="#l188"></a>
<span id="l189">                                             params.policyQualifiersRejected(),</span><a href="#l189"></a>
<span id="l190">                                             rootNode);</span><a href="#l190"></a>
<span id="l191">        certPathCheckers.add(pc);</span><a href="#l191"></a>
<span id="l192"></span><a href="#l192"></a>
<span id="l193">        BasicChecker bc = new BasicChecker(anchor, params.date(),</span><a href="#l193"></a>
<span id="l194">                                           params.sigProvider(), false);</span><a href="#l194"></a>
<span id="l195">        certPathCheckers.add(bc);</span><a href="#l195"></a>
<span id="l196"></span><a href="#l196"></a>
<span id="l197">        boolean revCheckerAdded = false;</span><a href="#l197"></a>
<span id="l198">        List&lt;PKIXCertPathChecker&gt; checkers = params.certPathCheckers();</span><a href="#l198"></a>
<span id="l199">        for (PKIXCertPathChecker checker : checkers) {</span><a href="#l199"></a>
<span id="l200">            if (checker instanceof PKIXRevocationChecker) {</span><a href="#l200"></a>
<span id="l201">                if (revCheckerAdded) {</span><a href="#l201"></a>
<span id="l202">                    throw new CertPathValidatorException(</span><a href="#l202"></a>
<span id="l203">                        &quot;Only one PKIXRevocationChecker can be specified&quot;);</span><a href="#l203"></a>
<span id="l204">                }</span><a href="#l204"></a>
<span id="l205">                revCheckerAdded = true;</span><a href="#l205"></a>
<span id="l206">                // if it's our own, initialize it</span><a href="#l206"></a>
<span id="l207">                if (checker instanceof RevocationChecker) {</span><a href="#l207"></a>
<span id="l208">                    ((RevocationChecker)checker).init(anchor, params);</span><a href="#l208"></a>
<span id="l209">                }</span><a href="#l209"></a>
<span id="l210">            }</span><a href="#l210"></a>
<span id="l211">        }</span><a href="#l211"></a>
<span id="l212">        // only add a RevocationChecker if revocation is enabled and</span><a href="#l212"></a>
<span id="l213">        // a PKIXRevocationChecker has not already been added</span><a href="#l213"></a>
<span id="l214">        if (params.revocationEnabled() &amp;&amp; !revCheckerAdded) {</span><a href="#l214"></a>
<span id="l215">            certPathCheckers.add(new RevocationChecker(anchor, params));</span><a href="#l215"></a>
<span id="l216">        }</span><a href="#l216"></a>
<span id="l217">        // add user-specified checkers</span><a href="#l217"></a>
<span id="l218">        certPathCheckers.addAll(checkers);</span><a href="#l218"></a>
<span id="l219"></span><a href="#l219"></a>
<span id="l220">        PKIXMasterCertPathValidator.validate(params.certPath(),</span><a href="#l220"></a>
<span id="l221">                                             params.certificates(),</span><a href="#l221"></a>
<span id="l222">                                             certPathCheckers);</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224">        return new PKIXCertPathValidatorResult(anchor, pc.getPolicyTree(),</span><a href="#l224"></a>
<span id="l225">                                               bc.getPublicKey());</span><a href="#l225"></a>
<span id="l226">    }</span><a href="#l226"></a>
<span id="l227">}</span><a href="#l227"></a></pre>
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

