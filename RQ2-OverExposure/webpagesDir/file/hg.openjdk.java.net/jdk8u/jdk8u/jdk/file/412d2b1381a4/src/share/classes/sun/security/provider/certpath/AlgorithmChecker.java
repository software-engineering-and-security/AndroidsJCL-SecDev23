<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/provider/certpath/AlgorithmChecker.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/provider/certpath/AlgorithmChecker.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/provider/certpath/AlgorithmChecker.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/provider/certpath/AlgorithmChecker.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/provider/certpath/AlgorithmChecker.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/provider/certpath/AlgorithmChecker.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/AlgorithmChecker.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/provider/certpath/AlgorithmChecker.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/83b863df0737/src/share/classes/sun/security/provider/certpath/AlgorithmChecker.java">83b863df0737</a> </td>
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
<span id="l2"> * Copyright (c) 2009, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.security.AlgorithmConstraints;</span><a href="#l28"></a>
<span id="l29">import java.security.CryptoPrimitive;</span><a href="#l29"></a>
<span id="l30">import java.util.Collection;</span><a href="#l30"></a>
<span id="l31">import java.util.Collections;</span><a href="#l31"></a>
<span id="l32">import java.util.Date;</span><a href="#l32"></a>
<span id="l33">import java.util.Set;</span><a href="#l33"></a>
<span id="l34">import java.util.EnumSet;</span><a href="#l34"></a>
<span id="l35">import java.math.BigInteger;</span><a href="#l35"></a>
<span id="l36">import java.security.PublicKey;</span><a href="#l36"></a>
<span id="l37">import java.security.KeyFactory;</span><a href="#l37"></a>
<span id="l38">import java.security.AlgorithmParameters;</span><a href="#l38"></a>
<span id="l39">import java.security.GeneralSecurityException;</span><a href="#l39"></a>
<span id="l40">import java.security.cert.Certificate;</span><a href="#l40"></a>
<span id="l41">import java.security.cert.X509CRL;</span><a href="#l41"></a>
<span id="l42">import java.security.cert.X509Certificate;</span><a href="#l42"></a>
<span id="l43">import java.security.cert.PKIXCertPathChecker;</span><a href="#l43"></a>
<span id="l44">import java.security.cert.TrustAnchor;</span><a href="#l44"></a>
<span id="l45">import java.security.cert.CRLException;</span><a href="#l45"></a>
<span id="l46">import java.security.cert.CertificateException;</span><a href="#l46"></a>
<span id="l47">import java.security.cert.CertPathValidatorException;</span><a href="#l47"></a>
<span id="l48">import java.security.cert.CertPathValidatorException.BasicReason;</span><a href="#l48"></a>
<span id="l49">import java.security.cert.PKIXReason;</span><a href="#l49"></a>
<span id="l50">import java.security.interfaces.DSAParams;</span><a href="#l50"></a>
<span id="l51">import java.security.interfaces.DSAPublicKey;</span><a href="#l51"></a>
<span id="l52">import java.security.spec.DSAPublicKeySpec;</span><a href="#l52"></a>
<span id="l53"></span><a href="#l53"></a>
<span id="l54">import sun.security.util.ConstraintsParameters;</span><a href="#l54"></a>
<span id="l55">import sun.security.util.Debug;</span><a href="#l55"></a>
<span id="l56">import sun.security.util.DisabledAlgorithmConstraints;</span><a href="#l56"></a>
<span id="l57">import sun.security.validator.Validator;</span><a href="#l57"></a>
<span id="l58">import sun.security.x509.AlgorithmId;</span><a href="#l58"></a>
<span id="l59">import sun.security.x509.X509CertImpl;</span><a href="#l59"></a>
<span id="l60">import sun.security.x509.X509CRLImpl;</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">/**</span><a href="#l62"></a>
<span id="l63"> * A {@code PKIXCertPathChecker} implementation to check whether a</span><a href="#l63"></a>
<span id="l64"> * specified certificate contains the required algorithm constraints.</span><a href="#l64"></a>
<span id="l65"> * &lt;p&gt;</span><a href="#l65"></a>
<span id="l66"> * Certificate fields such as the subject public key, the signature</span><a href="#l66"></a>
<span id="l67"> * algorithm, key usage, extended key usage, etc. need to conform to</span><a href="#l67"></a>
<span id="l68"> * the specified algorithm constraints.</span><a href="#l68"></a>
<span id="l69"> *</span><a href="#l69"></a>
<span id="l70"> * @see PKIXCertPathChecker</span><a href="#l70"></a>
<span id="l71"> * @see PKIXParameters</span><a href="#l71"></a>
<span id="l72"> */</span><a href="#l72"></a>
<span id="l73">public final class AlgorithmChecker extends PKIXCertPathChecker {</span><a href="#l73"></a>
<span id="l74">    private static final Debug debug = Debug.getInstance(&quot;certpath&quot;);</span><a href="#l74"></a>
<span id="l75"></span><a href="#l75"></a>
<span id="l76">    private final AlgorithmConstraints constraints;</span><a href="#l76"></a>
<span id="l77">    private final PublicKey trustedPubKey;</span><a href="#l77"></a>
<span id="l78">    private final Date date;</span><a href="#l78"></a>
<span id="l79">    private PublicKey prevPubKey;</span><a href="#l79"></a>
<span id="l80">    private final String variant;</span><a href="#l80"></a>
<span id="l81">    private TrustAnchor anchor;</span><a href="#l81"></a>
<span id="l82"></span><a href="#l82"></a>
<span id="l83">    private static final Set&lt;CryptoPrimitive&gt; SIGNATURE_PRIMITIVE_SET =</span><a href="#l83"></a>
<span id="l84">        Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.SIGNATURE));</span><a href="#l84"></a>
<span id="l85"></span><a href="#l85"></a>
<span id="l86">    private static final Set&lt;CryptoPrimitive&gt; KU_PRIMITIVE_SET =</span><a href="#l86"></a>
<span id="l87">        Collections.unmodifiableSet(EnumSet.of(</span><a href="#l87"></a>
<span id="l88">            CryptoPrimitive.SIGNATURE,</span><a href="#l88"></a>
<span id="l89">            CryptoPrimitive.KEY_ENCAPSULATION,</span><a href="#l89"></a>
<span id="l90">            CryptoPrimitive.PUBLIC_KEY_ENCRYPTION,</span><a href="#l90"></a>
<span id="l91">            CryptoPrimitive.KEY_AGREEMENT));</span><a href="#l91"></a>
<span id="l92"></span><a href="#l92"></a>
<span id="l93">    private static final DisabledAlgorithmConstraints</span><a href="#l93"></a>
<span id="l94">        certPathDefaultConstraints =</span><a href="#l94"></a>
<span id="l95">            DisabledAlgorithmConstraints.certPathConstraints();</span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97">    /**</span><a href="#l97"></a>
<span id="l98">     * Create a new {@code AlgorithmChecker} with the given</span><a href="#l98"></a>
<span id="l99">     * {@code TrustAnchor} and {@code String} variant.</span><a href="#l99"></a>
<span id="l100">     *</span><a href="#l100"></a>
<span id="l101">     * @param anchor the trust anchor selected to validate the target</span><a href="#l101"></a>
<span id="l102">     *     certificate</span><a href="#l102"></a>
<span id="l103">     * @param variant the Validator variant of the operation. A null value</span><a href="#l103"></a>
<span id="l104">     *                passed will set it to Validator.GENERIC.</span><a href="#l104"></a>
<span id="l105">     */</span><a href="#l105"></a>
<span id="l106">    public AlgorithmChecker(TrustAnchor anchor, String variant) {</span><a href="#l106"></a>
<span id="l107">        this(anchor, certPathDefaultConstraints, null, variant);</span><a href="#l107"></a>
<span id="l108">    }</span><a href="#l108"></a>
<span id="l109"></span><a href="#l109"></a>
<span id="l110">    /**</span><a href="#l110"></a>
<span id="l111">     * Create a new {@code AlgorithmChecker} with the given</span><a href="#l111"></a>
<span id="l112">     * {@code AlgorithmConstraints} and {@code String} variant.</span><a href="#l112"></a>
<span id="l113">     *</span><a href="#l113"></a>
<span id="l114">     * Note that this constructor can initialize a variation of situations where</span><a href="#l114"></a>
<span id="l115">     * the AlgorithmConstraints or Variant maybe known.</span><a href="#l115"></a>
<span id="l116">     *</span><a href="#l116"></a>
<span id="l117">     * @param constraints the algorithm constraints (or null)</span><a href="#l117"></a>
<span id="l118">     * @param variant the Validator variant of the operation. A null value</span><a href="#l118"></a>
<span id="l119">     *                passed will set it to Validator.GENERIC.</span><a href="#l119"></a>
<span id="l120">     */</span><a href="#l120"></a>
<span id="l121">    public AlgorithmChecker(AlgorithmConstraints constraints, String variant) {</span><a href="#l121"></a>
<span id="l122">        this(null, constraints, null, variant);</span><a href="#l122"></a>
<span id="l123">    }</span><a href="#l123"></a>
<span id="l124"></span><a href="#l124"></a>
<span id="l125">    /**</span><a href="#l125"></a>
<span id="l126">     * Create a new {@code AlgorithmChecker} with the</span><a href="#l126"></a>
<span id="l127">     * given {@code TrustAnchor}, {@code AlgorithmConstraints}, {@code Date},</span><a href="#l127"></a>
<span id="l128">     * and {@code String} variant.</span><a href="#l128"></a>
<span id="l129">     *</span><a href="#l129"></a>
<span id="l130">     * @param anchor the trust anchor selected to validate the target</span><a href="#l130"></a>
<span id="l131">     *     certificate</span><a href="#l131"></a>
<span id="l132">     * @param constraints the algorithm constraints (or null)</span><a href="#l132"></a>
<span id="l133">     * @param date the date specified by the PKIXParameters date, or the</span><a href="#l133"></a>
<span id="l134">     *             JAR timestamp if jar files are being validated and the</span><a href="#l134"></a>
<span id="l135">     *             JAR is timestamped. May be null if no timestamp or</span><a href="#l135"></a>
<span id="l136">     *             PKIXParameter date is set.</span><a href="#l136"></a>
<span id="l137">     * @param variant the Validator variant of the operation. A null value</span><a href="#l137"></a>
<span id="l138">     *                passed will set it to Validator.GENERIC.</span><a href="#l138"></a>
<span id="l139">     */</span><a href="#l139"></a>
<span id="l140">    public AlgorithmChecker(TrustAnchor anchor,</span><a href="#l140"></a>
<span id="l141">            AlgorithmConstraints constraints, Date date, String variant) {</span><a href="#l141"></a>
<span id="l142"></span><a href="#l142"></a>
<span id="l143">        if (anchor != null) {</span><a href="#l143"></a>
<span id="l144">            if (anchor.getTrustedCert() != null) {</span><a href="#l144"></a>
<span id="l145">                this.trustedPubKey = anchor.getTrustedCert().getPublicKey();</span><a href="#l145"></a>
<span id="l146">            } else {</span><a href="#l146"></a>
<span id="l147">                this.trustedPubKey = anchor.getCAPublicKey();</span><a href="#l147"></a>
<span id="l148">            }</span><a href="#l148"></a>
<span id="l149">            this.anchor = anchor;</span><a href="#l149"></a>
<span id="l150">        } else {</span><a href="#l150"></a>
<span id="l151">            this.trustedPubKey = null;</span><a href="#l151"></a>
<span id="l152">        }</span><a href="#l152"></a>
<span id="l153"></span><a href="#l153"></a>
<span id="l154">        this.prevPubKey = this.trustedPubKey;</span><a href="#l154"></a>
<span id="l155">        this.constraints = (constraints == null ? certPathDefaultConstraints :</span><a href="#l155"></a>
<span id="l156">                constraints);</span><a href="#l156"></a>
<span id="l157">        this.date = date;</span><a href="#l157"></a>
<span id="l158">        this.variant = (variant == null ? Validator.VAR_GENERIC : variant);</span><a href="#l158"></a>
<span id="l159">    }</span><a href="#l159"></a>
<span id="l160"></span><a href="#l160"></a>
<span id="l161">    /**</span><a href="#l161"></a>
<span id="l162">     * Create a new {@code AlgorithmChecker} with the given {@code TrustAnchor},</span><a href="#l162"></a>
<span id="l163">     * {@code PKIXParameter} date, and {@code varient}</span><a href="#l163"></a>
<span id="l164">     *</span><a href="#l164"></a>
<span id="l165">     * @param anchor the trust anchor selected to validate the target</span><a href="#l165"></a>
<span id="l166">     *     certificate</span><a href="#l166"></a>
<span id="l167">     * @param pkixdate Date the constraints are checked against. The value is</span><a href="#l167"></a>
<span id="l168">     *             either the PKIXParameters date or null for the current date.</span><a href="#l168"></a>
<span id="l169">     * @param variant the Validator variant of the operation. A null value</span><a href="#l169"></a>
<span id="l170">     *                passed will set it to Validator.GENERIC.</span><a href="#l170"></a>
<span id="l171">     */</span><a href="#l171"></a>
<span id="l172">    public AlgorithmChecker(TrustAnchor anchor, Date pkixdate, String variant) {</span><a href="#l172"></a>
<span id="l173">        this(anchor, certPathDefaultConstraints, pkixdate, variant);</span><a href="#l173"></a>
<span id="l174">    }</span><a href="#l174"></a>
<span id="l175"></span><a href="#l175"></a>
<span id="l176">    @Override</span><a href="#l176"></a>
<span id="l177">    public void init(boolean forward) throws CertPathValidatorException {</span><a href="#l177"></a>
<span id="l178">        //  Note that this class does not support forward mode.</span><a href="#l178"></a>
<span id="l179">        if (!forward) {</span><a href="#l179"></a>
<span id="l180">            if (trustedPubKey != null) {</span><a href="#l180"></a>
<span id="l181">                prevPubKey = trustedPubKey;</span><a href="#l181"></a>
<span id="l182">            } else {</span><a href="#l182"></a>
<span id="l183">                prevPubKey = null;</span><a href="#l183"></a>
<span id="l184">            }</span><a href="#l184"></a>
<span id="l185">        } else {</span><a href="#l185"></a>
<span id="l186">            throw new</span><a href="#l186"></a>
<span id="l187">                CertPathValidatorException(&quot;forward checking not supported&quot;);</span><a href="#l187"></a>
<span id="l188">        }</span><a href="#l188"></a>
<span id="l189">    }</span><a href="#l189"></a>
<span id="l190"></span><a href="#l190"></a>
<span id="l191">    @Override</span><a href="#l191"></a>
<span id="l192">    public boolean isForwardCheckingSupported() {</span><a href="#l192"></a>
<span id="l193">        //  Note that as this class does not support forward mode, the method</span><a href="#l193"></a>
<span id="l194">        //  will always returns false.</span><a href="#l194"></a>
<span id="l195">        return false;</span><a href="#l195"></a>
<span id="l196">    }</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">    @Override</span><a href="#l198"></a>
<span id="l199">    public Set&lt;String&gt; getSupportedExtensions() {</span><a href="#l199"></a>
<span id="l200">        return null;</span><a href="#l200"></a>
<span id="l201">    }</span><a href="#l201"></a>
<span id="l202"></span><a href="#l202"></a>
<span id="l203">    @Override</span><a href="#l203"></a>
<span id="l204">    public void check(Certificate cert,</span><a href="#l204"></a>
<span id="l205">            Collection&lt;String&gt; unresolvedCritExts)</span><a href="#l205"></a>
<span id="l206">            throws CertPathValidatorException {</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">        if (!(cert instanceof X509Certificate) || constraints == null) {</span><a href="#l208"></a>
<span id="l209">            // ignore the check for non-x.509 certificate or null constraints</span><a href="#l209"></a>
<span id="l210">            return;</span><a href="#l210"></a>
<span id="l211">        }</span><a href="#l211"></a>
<span id="l212"></span><a href="#l212"></a>
<span id="l213">        // check the key usage and key size</span><a href="#l213"></a>
<span id="l214">        boolean[] keyUsage = ((X509Certificate) cert).getKeyUsage();</span><a href="#l214"></a>
<span id="l215">        if (keyUsage != null &amp;&amp; keyUsage.length &lt; 9) {</span><a href="#l215"></a>
<span id="l216">            throw new CertPathValidatorException(</span><a href="#l216"></a>
<span id="l217">                &quot;incorrect KeyUsage extension&quot;,</span><a href="#l217"></a>
<span id="l218">                null, null, -1, PKIXReason.INVALID_KEY_USAGE);</span><a href="#l218"></a>
<span id="l219">        }</span><a href="#l219"></a>
<span id="l220"></span><a href="#l220"></a>
<span id="l221">        X509CertImpl x509Cert;</span><a href="#l221"></a>
<span id="l222">        AlgorithmId algorithmId;</span><a href="#l222"></a>
<span id="l223">        try {</span><a href="#l223"></a>
<span id="l224">            x509Cert = X509CertImpl.toImpl((X509Certificate)cert);</span><a href="#l224"></a>
<span id="l225">            algorithmId = (AlgorithmId)x509Cert.get(X509CertImpl.SIG_ALG);</span><a href="#l225"></a>
<span id="l226">        } catch (CertificateException ce) {</span><a href="#l226"></a>
<span id="l227">            throw new CertPathValidatorException(ce);</span><a href="#l227"></a>
<span id="l228">        }</span><a href="#l228"></a>
<span id="l229"></span><a href="#l229"></a>
<span id="l230">        AlgorithmParameters currSigAlgParams = algorithmId.getParameters();</span><a href="#l230"></a>
<span id="l231">        PublicKey currPubKey = cert.getPublicKey();</span><a href="#l231"></a>
<span id="l232">        String currSigAlg = x509Cert.getSigAlgName();</span><a href="#l232"></a>
<span id="l233"></span><a href="#l233"></a>
<span id="l234">        // Check the signature algorithm and parameters against constraints.</span><a href="#l234"></a>
<span id="l235">        if (!constraints.permits(SIGNATURE_PRIMITIVE_SET, currSigAlg,</span><a href="#l235"></a>
<span id="l236">                currSigAlgParams)) {</span><a href="#l236"></a>
<span id="l237">            throw new CertPathValidatorException(</span><a href="#l237"></a>
<span id="l238">                    &quot;Algorithm constraints check failed on signature &quot; +</span><a href="#l238"></a>
<span id="l239">                            &quot;algorithm: &quot; + currSigAlg, null, null, -1,</span><a href="#l239"></a>
<span id="l240">                    BasicReason.ALGORITHM_CONSTRAINED);</span><a href="#l240"></a>
<span id="l241">        }</span><a href="#l241"></a>
<span id="l242"></span><a href="#l242"></a>
<span id="l243">        // Assume all key usage bits are set if key usage is not present</span><a href="#l243"></a>
<span id="l244">        Set&lt;CryptoPrimitive&gt; primitives = KU_PRIMITIVE_SET;</span><a href="#l244"></a>
<span id="l245"></span><a href="#l245"></a>
<span id="l246">        if (keyUsage != null) {</span><a href="#l246"></a>
<span id="l247">                primitives = EnumSet.noneOf(CryptoPrimitive.class);</span><a href="#l247"></a>
<span id="l248"></span><a href="#l248"></a>
<span id="l249">            if (keyUsage[0] || keyUsage[1] || keyUsage[5] || keyUsage[6]) {</span><a href="#l249"></a>
<span id="l250">                // keyUsage[0]: KeyUsage.digitalSignature</span><a href="#l250"></a>
<span id="l251">                // keyUsage[1]: KeyUsage.nonRepudiation</span><a href="#l251"></a>
<span id="l252">                // keyUsage[5]: KeyUsage.keyCertSign</span><a href="#l252"></a>
<span id="l253">                // keyUsage[6]: KeyUsage.cRLSign</span><a href="#l253"></a>
<span id="l254">                primitives.add(CryptoPrimitive.SIGNATURE);</span><a href="#l254"></a>
<span id="l255">            }</span><a href="#l255"></a>
<span id="l256"></span><a href="#l256"></a>
<span id="l257">            if (keyUsage[2]) {      // KeyUsage.keyEncipherment</span><a href="#l257"></a>
<span id="l258">                primitives.add(CryptoPrimitive.KEY_ENCAPSULATION);</span><a href="#l258"></a>
<span id="l259">            }</span><a href="#l259"></a>
<span id="l260"></span><a href="#l260"></a>
<span id="l261">            if (keyUsage[3]) {      // KeyUsage.dataEncipherment</span><a href="#l261"></a>
<span id="l262">                primitives.add(CryptoPrimitive.PUBLIC_KEY_ENCRYPTION);</span><a href="#l262"></a>
<span id="l263">            }</span><a href="#l263"></a>
<span id="l264"></span><a href="#l264"></a>
<span id="l265">            if (keyUsage[4]) {      // KeyUsage.keyAgreement</span><a href="#l265"></a>
<span id="l266">                primitives.add(CryptoPrimitive.KEY_AGREEMENT);</span><a href="#l266"></a>
<span id="l267">            }</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">            // KeyUsage.encipherOnly and KeyUsage.decipherOnly are</span><a href="#l269"></a>
<span id="l270">            // undefined in the absence of the keyAgreement bit.</span><a href="#l270"></a>
<span id="l271"></span><a href="#l271"></a>
<span id="l272">            if (primitives.isEmpty()) {</span><a href="#l272"></a>
<span id="l273">                throw new CertPathValidatorException(</span><a href="#l273"></a>
<span id="l274">                    &quot;incorrect KeyUsage extension bits&quot;,</span><a href="#l274"></a>
<span id="l275">                    null, null, -1, PKIXReason.INVALID_KEY_USAGE);</span><a href="#l275"></a>
<span id="l276">            }</span><a href="#l276"></a>
<span id="l277">        }</span><a href="#l277"></a>
<span id="l278"></span><a href="#l278"></a>
<span id="l279">        ConstraintsParameters cp =</span><a href="#l279"></a>
<span id="l280">            new CertPathConstraintsParameters(x509Cert, variant,</span><a href="#l280"></a>
<span id="l281">                    anchor, date);</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">        // Check against local constraints if it is DisabledAlgorithmConstraints</span><a href="#l283"></a>
<span id="l284">        if (constraints instanceof DisabledAlgorithmConstraints) {</span><a href="#l284"></a>
<span id="l285">            ((DisabledAlgorithmConstraints)constraints).permits(currSigAlg,</span><a href="#l285"></a>
<span id="l286">                currSigAlgParams, cp);</span><a href="#l286"></a>
<span id="l287">            // DisabledAlgorithmsConstraints does not check primitives, so key</span><a href="#l287"></a>
<span id="l288">            // additional key check.</span><a href="#l288"></a>
<span id="l289"></span><a href="#l289"></a>
<span id="l290">        } else {</span><a href="#l290"></a>
<span id="l291">            // Perform the default constraints checking anyway.</span><a href="#l291"></a>
<span id="l292">            certPathDefaultConstraints.permits(currSigAlg, currSigAlgParams, cp);</span><a href="#l292"></a>
<span id="l293">            // Call locally set constraints to check key with primitives.</span><a href="#l293"></a>
<span id="l294">            if (!constraints.permits(primitives, currPubKey)) {</span><a href="#l294"></a>
<span id="l295">                throw new CertPathValidatorException(</span><a href="#l295"></a>
<span id="l296">                        &quot;Algorithm constraints check failed on key &quot; +</span><a href="#l296"></a>
<span id="l297">                                currPubKey.getAlgorithm() + &quot; with size of &quot; +</span><a href="#l297"></a>
<span id="l298">                                sun.security.util.KeyUtil.getKeySize(currPubKey) +</span><a href="#l298"></a>
<span id="l299">                                &quot;bits&quot;,</span><a href="#l299"></a>
<span id="l300">                        null, null, -1, BasicReason.ALGORITHM_CONSTRAINED);</span><a href="#l300"></a>
<span id="l301">            }</span><a href="#l301"></a>
<span id="l302">        }</span><a href="#l302"></a>
<span id="l303"></span><a href="#l303"></a>
<span id="l304">        // If there is no previous key, set one and exit</span><a href="#l304"></a>
<span id="l305">        if (prevPubKey == null) {</span><a href="#l305"></a>
<span id="l306">            prevPubKey = currPubKey;</span><a href="#l306"></a>
<span id="l307">            return;</span><a href="#l307"></a>
<span id="l308">        }</span><a href="#l308"></a>
<span id="l309"></span><a href="#l309"></a>
<span id="l310">        // Check with previous cert for signature algorithm and public key</span><a href="#l310"></a>
<span id="l311">        if (!constraints.permits(</span><a href="#l311"></a>
<span id="l312">                SIGNATURE_PRIMITIVE_SET,</span><a href="#l312"></a>
<span id="l313">                currSigAlg, prevPubKey, currSigAlgParams)) {</span><a href="#l313"></a>
<span id="l314">            throw new CertPathValidatorException(</span><a href="#l314"></a>
<span id="l315">                    &quot;Algorithm constraints check failed on &quot; +</span><a href="#l315"></a>
<span id="l316">                            &quot;signature algorithm: &quot; + currSigAlg,</span><a href="#l316"></a>
<span id="l317">                    null, null, -1, BasicReason.ALGORITHM_CONSTRAINED);</span><a href="#l317"></a>
<span id="l318">        }</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">        // Inherit key parameters from previous key</span><a href="#l320"></a>
<span id="l321">        if (PKIX.isDSAPublicKeyWithoutParams(currPubKey)) {</span><a href="#l321"></a>
<span id="l322">            // Inherit DSA parameters from previous key</span><a href="#l322"></a>
<span id="l323">            if (!(prevPubKey instanceof DSAPublicKey)) {</span><a href="#l323"></a>
<span id="l324">                throw new CertPathValidatorException(&quot;Input key is not &quot; +</span><a href="#l324"></a>
<span id="l325">                        &quot;of a appropriate type for inheriting parameters&quot;);</span><a href="#l325"></a>
<span id="l326">            }</span><a href="#l326"></a>
<span id="l327"></span><a href="#l327"></a>
<span id="l328">            DSAParams params = ((DSAPublicKey)prevPubKey).getParams();</span><a href="#l328"></a>
<span id="l329">            if (params == null) {</span><a href="#l329"></a>
<span id="l330">                throw new CertPathValidatorException(</span><a href="#l330"></a>
<span id="l331">                        &quot;Key parameters missing from public key.&quot;);</span><a href="#l331"></a>
<span id="l332">            }</span><a href="#l332"></a>
<span id="l333"></span><a href="#l333"></a>
<span id="l334">            try {</span><a href="#l334"></a>
<span id="l335">                BigInteger y = ((DSAPublicKey)currPubKey).getY();</span><a href="#l335"></a>
<span id="l336">                KeyFactory kf = KeyFactory.getInstance(&quot;DSA&quot;);</span><a href="#l336"></a>
<span id="l337">                DSAPublicKeySpec ks = new DSAPublicKeySpec(y, params.getP(),</span><a href="#l337"></a>
<span id="l338">                        params.getQ(), params.getG());</span><a href="#l338"></a>
<span id="l339">                currPubKey = kf.generatePublic(ks);</span><a href="#l339"></a>
<span id="l340">            } catch (GeneralSecurityException e) {</span><a href="#l340"></a>
<span id="l341">                throw new CertPathValidatorException(&quot;Unable to generate &quot; +</span><a href="#l341"></a>
<span id="l342">                        &quot;key with inherited parameters: &quot; + e.getMessage(), e);</span><a href="#l342"></a>
<span id="l343">            }</span><a href="#l343"></a>
<span id="l344">        }</span><a href="#l344"></a>
<span id="l345"></span><a href="#l345"></a>
<span id="l346">        // reset the previous public key</span><a href="#l346"></a>
<span id="l347">        prevPubKey = currPubKey;</span><a href="#l347"></a>
<span id="l348">    }</span><a href="#l348"></a>
<span id="l349"></span><a href="#l349"></a>
<span id="l350">    /**</span><a href="#l350"></a>
<span id="l351">     * Try to set the trust anchor of the checker.</span><a href="#l351"></a>
<span id="l352">     * &lt;p&gt;</span><a href="#l352"></a>
<span id="l353">     * If there is no trust anchor specified and the checker has not started,</span><a href="#l353"></a>
<span id="l354">     * set the trust anchor.</span><a href="#l354"></a>
<span id="l355">     *</span><a href="#l355"></a>
<span id="l356">     * @param anchor the trust anchor selected to validate the target</span><a href="#l356"></a>
<span id="l357">     *     certificate</span><a href="#l357"></a>
<span id="l358">     */</span><a href="#l358"></a>
<span id="l359">    void trySetTrustAnchor(TrustAnchor anchor) {</span><a href="#l359"></a>
<span id="l360">        // Don't bother if the check has started or trust anchor has already</span><a href="#l360"></a>
<span id="l361">        // specified.</span><a href="#l361"></a>
<span id="l362">        if (prevPubKey == null) {</span><a href="#l362"></a>
<span id="l363">            if (anchor == null) {</span><a href="#l363"></a>
<span id="l364">                throw new IllegalArgumentException(</span><a href="#l364"></a>
<span id="l365">                        &quot;The trust anchor cannot be null&quot;);</span><a href="#l365"></a>
<span id="l366">            }</span><a href="#l366"></a>
<span id="l367"></span><a href="#l367"></a>
<span id="l368">            // Don't bother to change the trustedPubKey.</span><a href="#l368"></a>
<span id="l369">            if (anchor.getTrustedCert() != null) {</span><a href="#l369"></a>
<span id="l370">                prevPubKey = anchor.getTrustedCert().getPublicKey();</span><a href="#l370"></a>
<span id="l371">            } else {</span><a href="#l371"></a>
<span id="l372">                prevPubKey = anchor.getCAPublicKey();</span><a href="#l372"></a>
<span id="l373">            }</span><a href="#l373"></a>
<span id="l374">            this.anchor = anchor;</span><a href="#l374"></a>
<span id="l375">        }</span><a href="#l375"></a>
<span id="l376">    }</span><a href="#l376"></a>
<span id="l377"></span><a href="#l377"></a>
<span id="l378">    /**</span><a href="#l378"></a>
<span id="l379">     * Check the signature algorithm with the specified public key.</span><a href="#l379"></a>
<span id="l380">     *</span><a href="#l380"></a>
<span id="l381">     * @param key the public key to verify the CRL signature</span><a href="#l381"></a>
<span id="l382">     * @param crl the target CRL</span><a href="#l382"></a>
<span id="l383">     * @param variant the Validator variant of the operation. A null value</span><a href="#l383"></a>
<span id="l384">     *                passed will set it to Validator.GENERIC.</span><a href="#l384"></a>
<span id="l385">     * @param anchor the trust anchor selected to validate the CRL issuer</span><a href="#l385"></a>
<span id="l386">     */</span><a href="#l386"></a>
<span id="l387">    static void check(PublicKey key, X509CRL crl, String variant,</span><a href="#l387"></a>
<span id="l388">                      TrustAnchor anchor) throws CertPathValidatorException {</span><a href="#l388"></a>
<span id="l389"></span><a href="#l389"></a>
<span id="l390">        X509CRLImpl x509CRLImpl = null;</span><a href="#l390"></a>
<span id="l391">        try {</span><a href="#l391"></a>
<span id="l392">            x509CRLImpl = X509CRLImpl.toImpl(crl);</span><a href="#l392"></a>
<span id="l393">        } catch (CRLException ce) {</span><a href="#l393"></a>
<span id="l394">            throw new CertPathValidatorException(ce);</span><a href="#l394"></a>
<span id="l395">        }</span><a href="#l395"></a>
<span id="l396"></span><a href="#l396"></a>
<span id="l397">        AlgorithmId algorithmId = x509CRLImpl.getSigAlgId();</span><a href="#l397"></a>
<span id="l398">        check(key, algorithmId, variant, anchor);</span><a href="#l398"></a>
<span id="l399">    }</span><a href="#l399"></a>
<span id="l400"></span><a href="#l400"></a>
<span id="l401">    /**</span><a href="#l401"></a>
<span id="l402">     * Check the signature algorithm with the specified public key.</span><a href="#l402"></a>
<span id="l403">     *</span><a href="#l403"></a>
<span id="l404">     * @param key the public key to verify the CRL signature</span><a href="#l404"></a>
<span id="l405">     * @param algorithmId signature algorithm Algorithm ID</span><a href="#l405"></a>
<span id="l406">     * @param variant the Validator variant of the operation. A null</span><a href="#l406"></a>
<span id="l407">     *                value passed will set it to Validator.GENERIC.</span><a href="#l407"></a>
<span id="l408">     * @param anchor the trust anchor selected to validate the public key</span><a href="#l408"></a>
<span id="l409">     */</span><a href="#l409"></a>
<span id="l410">    static void check(PublicKey key, AlgorithmId algorithmId, String variant,</span><a href="#l410"></a>
<span id="l411">                      TrustAnchor anchor) throws CertPathValidatorException {</span><a href="#l411"></a>
<span id="l412"></span><a href="#l412"></a>
<span id="l413">        certPathDefaultConstraints.permits(algorithmId.getName(),</span><a href="#l413"></a>
<span id="l414">            algorithmId.getParameters(),</span><a href="#l414"></a>
<span id="l415">            new CertPathConstraintsParameters(key, variant, anchor));</span><a href="#l415"></a>
<span id="l416">    }</span><a href="#l416"></a>
<span id="l417">}</span><a href="#l417"></a>
<span id="l418"></span><a href="#l418"></a></pre>
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

