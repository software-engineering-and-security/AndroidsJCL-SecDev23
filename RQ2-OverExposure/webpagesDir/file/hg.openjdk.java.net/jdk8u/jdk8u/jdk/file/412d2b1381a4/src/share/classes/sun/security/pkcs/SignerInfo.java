<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/pkcs/SignerInfo.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/sun/security/pkcs/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/pkcs/SignerInfo.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/pkcs/SignerInfo.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/pkcs/SignerInfo.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/pkcs/SignerInfo.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/pkcs/SignerInfo.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/pkcs/SignerInfo.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/pkcs/SignerInfo.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/3054a00b5333/src/share/classes/sun/security/pkcs/SignerInfo.java">3054a00b5333</a> </td>
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
<span id="l2"> * Copyright (c) 1996, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.security.pkcs;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.OutputStream;</span><a href="#l28"></a>
<span id="l29">import java.io.IOException;</span><a href="#l29"></a>
<span id="l30">import java.math.BigInteger;</span><a href="#l30"></a>
<span id="l31">import java.security.cert.CertPathValidatorException;</span><a href="#l31"></a>
<span id="l32">import java.security.cert.CertificateException;</span><a href="#l32"></a>
<span id="l33">import java.security.cert.CertificateFactory;</span><a href="#l33"></a>
<span id="l34">import java.security.cert.CertPath;</span><a href="#l34"></a>
<span id="l35">import java.security.cert.X509Certificate;</span><a href="#l35"></a>
<span id="l36">import java.security.*;</span><a href="#l36"></a>
<span id="l37">import java.util.ArrayList;</span><a href="#l37"></a>
<span id="l38">import java.util.Collections;</span><a href="#l38"></a>
<span id="l39">import java.util.Date;</span><a href="#l39"></a>
<span id="l40">import java.util.HashMap;</span><a href="#l40"></a>
<span id="l41">import java.util.HashSet;</span><a href="#l41"></a>
<span id="l42">import java.util.Map;</span><a href="#l42"></a>
<span id="l43">import java.util.Set;</span><a href="#l43"></a>
<span id="l44"></span><a href="#l44"></a>
<span id="l45">import sun.misc.HexDumpEncoder;</span><a href="#l45"></a>
<span id="l46">import sun.security.timestamp.TimestampToken;</span><a href="#l46"></a>
<span id="l47">import sun.security.util.Debug;</span><a href="#l47"></a>
<span id="l48">import sun.security.util.DerEncoder;</span><a href="#l48"></a>
<span id="l49">import sun.security.util.DerInputStream;</span><a href="#l49"></a>
<span id="l50">import sun.security.util.DerOutputStream;</span><a href="#l50"></a>
<span id="l51">import sun.security.util.DerValue;</span><a href="#l51"></a>
<span id="l52">import sun.security.util.DisabledAlgorithmConstraints;</span><a href="#l52"></a>
<span id="l53">import sun.security.util.JarConstraintsParameters;</span><a href="#l53"></a>
<span id="l54">import sun.security.util.KeyUtil;</span><a href="#l54"></a>
<span id="l55">import sun.security.util.ObjectIdentifier;</span><a href="#l55"></a>
<span id="l56">import sun.security.util.SignatureUtil;</span><a href="#l56"></a>
<span id="l57">import sun.security.x509.AlgorithmId;</span><a href="#l57"></a>
<span id="l58">import sun.security.x509.X500Name;</span><a href="#l58"></a>
<span id="l59">import sun.security.x509.KeyUsageExtension;</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">/**</span><a href="#l61"></a>
<span id="l62"> * A SignerInfo, as defined in PKCS#7's signedData type.</span><a href="#l62"></a>
<span id="l63"> *</span><a href="#l63"></a>
<span id="l64"> * @author Benjamin Renaud</span><a href="#l64"></a>
<span id="l65"> */</span><a href="#l65"></a>
<span id="l66">public class SignerInfo implements DerEncoder {</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    private static final DisabledAlgorithmConstraints JAR_DISABLED_CHECK =</span><a href="#l68"></a>
<span id="l69">            DisabledAlgorithmConstraints.jarConstraints();</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    BigInteger version;</span><a href="#l71"></a>
<span id="l72">    X500Name issuerName;</span><a href="#l72"></a>
<span id="l73">    BigInteger certificateSerialNumber;</span><a href="#l73"></a>
<span id="l74">    AlgorithmId digestAlgorithmId;</span><a href="#l74"></a>
<span id="l75">    AlgorithmId digestEncryptionAlgorithmId;</span><a href="#l75"></a>
<span id="l76">    byte[] encryptedDigest;</span><a href="#l76"></a>
<span id="l77">    Timestamp timestamp;</span><a href="#l77"></a>
<span id="l78">    private boolean hasTimestamp = true;</span><a href="#l78"></a>
<span id="l79">    private static final Debug debug = Debug.getInstance(&quot;jar&quot;);</span><a href="#l79"></a>
<span id="l80"></span><a href="#l80"></a>
<span id="l81">    PKCS9Attributes authenticatedAttributes;</span><a href="#l81"></a>
<span id="l82">    PKCS9Attributes unauthenticatedAttributes;</span><a href="#l82"></a>
<span id="l83"></span><a href="#l83"></a>
<span id="l84">    /**</span><a href="#l84"></a>
<span id="l85">     * A map containing the algorithms in this SignerInfo. This is used to</span><a href="#l85"></a>
<span id="l86">     * avoid checking algorithms to see if they are disabled more than once.</span><a href="#l86"></a>
<span id="l87">     * The key is the AlgorithmId of the algorithm, and the value is the name of</span><a href="#l87"></a>
<span id="l88">     * the field or attribute.</span><a href="#l88"></a>
<span id="l89">     */</span><a href="#l89"></a>
<span id="l90">    private Map&lt;AlgorithmId, String&gt; algorithms = new HashMap&lt;&gt;();</span><a href="#l90"></a>
<span id="l91"></span><a href="#l91"></a>
<span id="l92">    public SignerInfo(X500Name  issuerName,</span><a href="#l92"></a>
<span id="l93">                      BigInteger serial,</span><a href="#l93"></a>
<span id="l94">                      AlgorithmId digestAlgorithmId,</span><a href="#l94"></a>
<span id="l95">                      AlgorithmId digestEncryptionAlgorithmId,</span><a href="#l95"></a>
<span id="l96">                      byte[] encryptedDigest) {</span><a href="#l96"></a>
<span id="l97">        this.version = BigInteger.ONE;</span><a href="#l97"></a>
<span id="l98">        this.issuerName = issuerName;</span><a href="#l98"></a>
<span id="l99">        this.certificateSerialNumber = serial;</span><a href="#l99"></a>
<span id="l100">        this.digestAlgorithmId = digestAlgorithmId;</span><a href="#l100"></a>
<span id="l101">        this.digestEncryptionAlgorithmId = digestEncryptionAlgorithmId;</span><a href="#l101"></a>
<span id="l102">        this.encryptedDigest = encryptedDigest;</span><a href="#l102"></a>
<span id="l103">    }</span><a href="#l103"></a>
<span id="l104"></span><a href="#l104"></a>
<span id="l105">    public SignerInfo(X500Name  issuerName,</span><a href="#l105"></a>
<span id="l106">                      BigInteger serial,</span><a href="#l106"></a>
<span id="l107">                      AlgorithmId digestAlgorithmId,</span><a href="#l107"></a>
<span id="l108">                      PKCS9Attributes authenticatedAttributes,</span><a href="#l108"></a>
<span id="l109">                      AlgorithmId digestEncryptionAlgorithmId,</span><a href="#l109"></a>
<span id="l110">                      byte[] encryptedDigest,</span><a href="#l110"></a>
<span id="l111">                      PKCS9Attributes unauthenticatedAttributes) {</span><a href="#l111"></a>
<span id="l112">        this.version = BigInteger.ONE;</span><a href="#l112"></a>
<span id="l113">        this.issuerName = issuerName;</span><a href="#l113"></a>
<span id="l114">        this.certificateSerialNumber = serial;</span><a href="#l114"></a>
<span id="l115">        this.digestAlgorithmId = digestAlgorithmId;</span><a href="#l115"></a>
<span id="l116">        this.authenticatedAttributes = authenticatedAttributes;</span><a href="#l116"></a>
<span id="l117">        this.digestEncryptionAlgorithmId = digestEncryptionAlgorithmId;</span><a href="#l117"></a>
<span id="l118">        this.encryptedDigest = encryptedDigest;</span><a href="#l118"></a>
<span id="l119">        this.unauthenticatedAttributes = unauthenticatedAttributes;</span><a href="#l119"></a>
<span id="l120">    }</span><a href="#l120"></a>
<span id="l121"></span><a href="#l121"></a>
<span id="l122">    /**</span><a href="#l122"></a>
<span id="l123">     * Parses a PKCS#7 signer info.</span><a href="#l123"></a>
<span id="l124">     */</span><a href="#l124"></a>
<span id="l125">    public SignerInfo(DerInputStream derin)</span><a href="#l125"></a>
<span id="l126">        throws IOException, ParsingException</span><a href="#l126"></a>
<span id="l127">    {</span><a href="#l127"></a>
<span id="l128">        this(derin, false);</span><a href="#l128"></a>
<span id="l129">    }</span><a href="#l129"></a>
<span id="l130"></span><a href="#l130"></a>
<span id="l131">    /**</span><a href="#l131"></a>
<span id="l132">     * Parses a PKCS#7 signer info.</span><a href="#l132"></a>
<span id="l133">     *</span><a href="#l133"></a>
<span id="l134">     * &lt;p&gt;This constructor is used only for backwards compatibility with</span><a href="#l134"></a>
<span id="l135">     * PKCS#7 blocks that were generated using JDK1.1.x.</span><a href="#l135"></a>
<span id="l136">     *</span><a href="#l136"></a>
<span id="l137">     * @param derin the ASN.1 encoding of the signer info.</span><a href="#l137"></a>
<span id="l138">     * @param oldStyle flag indicating whether or not the given signer info</span><a href="#l138"></a>
<span id="l139">     * is encoded according to JDK1.1.x.</span><a href="#l139"></a>
<span id="l140">     */</span><a href="#l140"></a>
<span id="l141">    public SignerInfo(DerInputStream derin, boolean oldStyle)</span><a href="#l141"></a>
<span id="l142">        throws IOException, ParsingException</span><a href="#l142"></a>
<span id="l143">    {</span><a href="#l143"></a>
<span id="l144">        // version</span><a href="#l144"></a>
<span id="l145">        version = derin.getBigInteger();</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">        // issuerAndSerialNumber</span><a href="#l147"></a>
<span id="l148">        DerValue[] issuerAndSerialNumber = derin.getSequence(2);</span><a href="#l148"></a>
<span id="l149">        if (issuerAndSerialNumber.length != 2) {</span><a href="#l149"></a>
<span id="l150">            throw new ParsingException(&quot;Invalid length for IssuerAndSerialNumber&quot;);</span><a href="#l150"></a>
<span id="l151">        }</span><a href="#l151"></a>
<span id="l152">        byte[] issuerBytes = issuerAndSerialNumber[0].toByteArray();</span><a href="#l152"></a>
<span id="l153">        issuerName = new X500Name(new DerValue(DerValue.tag_Sequence,</span><a href="#l153"></a>
<span id="l154">                                               issuerBytes));</span><a href="#l154"></a>
<span id="l155">        certificateSerialNumber = issuerAndSerialNumber[1].getBigInteger();</span><a href="#l155"></a>
<span id="l156"></span><a href="#l156"></a>
<span id="l157">        // digestAlgorithmId</span><a href="#l157"></a>
<span id="l158">        DerValue tmp = derin.getDerValue();</span><a href="#l158"></a>
<span id="l159"></span><a href="#l159"></a>
<span id="l160">        digestAlgorithmId = AlgorithmId.parse(tmp);</span><a href="#l160"></a>
<span id="l161"></span><a href="#l161"></a>
<span id="l162">        // authenticatedAttributes</span><a href="#l162"></a>
<span id="l163">        if (oldStyle) {</span><a href="#l163"></a>
<span id="l164">            // In JDK1.1.x, the authenticatedAttributes are always present,</span><a href="#l164"></a>
<span id="l165">            // encoded as an empty Set (Set of length zero)</span><a href="#l165"></a>
<span id="l166">            derin.getSet(0);</span><a href="#l166"></a>
<span id="l167">        } else {</span><a href="#l167"></a>
<span id="l168">            // check if set of auth attributes (implicit tag) is provided</span><a href="#l168"></a>
<span id="l169">            // (auth attributes are OPTIONAL)</span><a href="#l169"></a>
<span id="l170">            if ((byte)(derin.peekByte()) == (byte)0xA0) {</span><a href="#l170"></a>
<span id="l171">                authenticatedAttributes = new PKCS9Attributes(derin);</span><a href="#l171"></a>
<span id="l172">            }</span><a href="#l172"></a>
<span id="l173">        }</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">        // digestEncryptionAlgorithmId - little RSA naming scheme -</span><a href="#l175"></a>
<span id="l176">        // signature == encryption...</span><a href="#l176"></a>
<span id="l177">        tmp = derin.getDerValue();</span><a href="#l177"></a>
<span id="l178"></span><a href="#l178"></a>
<span id="l179">        digestEncryptionAlgorithmId = AlgorithmId.parse(tmp);</span><a href="#l179"></a>
<span id="l180"></span><a href="#l180"></a>
<span id="l181">        // encryptedDigest</span><a href="#l181"></a>
<span id="l182">        encryptedDigest = derin.getOctetString();</span><a href="#l182"></a>
<span id="l183"></span><a href="#l183"></a>
<span id="l184">        // unauthenticatedAttributes</span><a href="#l184"></a>
<span id="l185">        if (oldStyle) {</span><a href="#l185"></a>
<span id="l186">            // In JDK1.1.x, the unauthenticatedAttributes are always present,</span><a href="#l186"></a>
<span id="l187">            // encoded as an empty Set (Set of length zero)</span><a href="#l187"></a>
<span id="l188">            derin.getSet(0);</span><a href="#l188"></a>
<span id="l189">        } else {</span><a href="#l189"></a>
<span id="l190">            // check if set of unauth attributes (implicit tag) is provided</span><a href="#l190"></a>
<span id="l191">            // (unauth attributes are OPTIONAL)</span><a href="#l191"></a>
<span id="l192">            if (derin.available() != 0</span><a href="#l192"></a>
<span id="l193">                &amp;&amp; (byte)(derin.peekByte()) == (byte)0xA1) {</span><a href="#l193"></a>
<span id="l194">                unauthenticatedAttributes =</span><a href="#l194"></a>
<span id="l195">                    new PKCS9Attributes(derin, true);// ignore unsupported attrs</span><a href="#l195"></a>
<span id="l196">            }</span><a href="#l196"></a>
<span id="l197">        }</span><a href="#l197"></a>
<span id="l198"></span><a href="#l198"></a>
<span id="l199">        // all done</span><a href="#l199"></a>
<span id="l200">        if (derin.available() != 0) {</span><a href="#l200"></a>
<span id="l201">            throw new ParsingException(&quot;extra data at the end&quot;);</span><a href="#l201"></a>
<span id="l202">        }</span><a href="#l202"></a>
<span id="l203">    }</span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">    public void encode(DerOutputStream out) throws IOException {</span><a href="#l205"></a>
<span id="l206"></span><a href="#l206"></a>
<span id="l207">        derEncode(out);</span><a href="#l207"></a>
<span id="l208">    }</span><a href="#l208"></a>
<span id="l209"></span><a href="#l209"></a>
<span id="l210">    /**</span><a href="#l210"></a>
<span id="l211">     * DER encode this object onto an output stream.</span><a href="#l211"></a>
<span id="l212">     * Implements the {@code DerEncoder} interface.</span><a href="#l212"></a>
<span id="l213">     *</span><a href="#l213"></a>
<span id="l214">     * @param out</span><a href="#l214"></a>
<span id="l215">     * the output stream on which to write the DER encoding.</span><a href="#l215"></a>
<span id="l216">     *</span><a href="#l216"></a>
<span id="l217">     * @exception IOException on encoding error.</span><a href="#l217"></a>
<span id="l218">     */</span><a href="#l218"></a>
<span id="l219">    public void derEncode(OutputStream out) throws IOException {</span><a href="#l219"></a>
<span id="l220">        DerOutputStream seq = new DerOutputStream();</span><a href="#l220"></a>
<span id="l221">        seq.putInteger(version);</span><a href="#l221"></a>
<span id="l222">        DerOutputStream issuerAndSerialNumber = new DerOutputStream();</span><a href="#l222"></a>
<span id="l223">        issuerName.encode(issuerAndSerialNumber);</span><a href="#l223"></a>
<span id="l224">        issuerAndSerialNumber.putInteger(certificateSerialNumber);</span><a href="#l224"></a>
<span id="l225">        seq.write(DerValue.tag_Sequence, issuerAndSerialNumber);</span><a href="#l225"></a>
<span id="l226"></span><a href="#l226"></a>
<span id="l227">        digestAlgorithmId.encode(seq);</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">        // encode authenticated attributes if there are any</span><a href="#l229"></a>
<span id="l230">        if (authenticatedAttributes != null)</span><a href="#l230"></a>
<span id="l231">            authenticatedAttributes.encode((byte)0xA0, seq);</span><a href="#l231"></a>
<span id="l232"></span><a href="#l232"></a>
<span id="l233">        digestEncryptionAlgorithmId.encode(seq);</span><a href="#l233"></a>
<span id="l234"></span><a href="#l234"></a>
<span id="l235">        seq.putOctetString(encryptedDigest);</span><a href="#l235"></a>
<span id="l236"></span><a href="#l236"></a>
<span id="l237">        // encode unauthenticated attributes if there are any</span><a href="#l237"></a>
<span id="l238">        if (unauthenticatedAttributes != null)</span><a href="#l238"></a>
<span id="l239">            unauthenticatedAttributes.encode((byte)0xA1, seq);</span><a href="#l239"></a>
<span id="l240"></span><a href="#l240"></a>
<span id="l241">        DerOutputStream tmp = new DerOutputStream();</span><a href="#l241"></a>
<span id="l242">        tmp.write(DerValue.tag_Sequence, seq);</span><a href="#l242"></a>
<span id="l243"></span><a href="#l243"></a>
<span id="l244">        out.write(tmp.toByteArray());</span><a href="#l244"></a>
<span id="l245">    }</span><a href="#l245"></a>
<span id="l246"></span><a href="#l246"></a>
<span id="l247"></span><a href="#l247"></a>
<span id="l248"></span><a href="#l248"></a>
<span id="l249">    /*</span><a href="#l249"></a>
<span id="l250">     * Returns the (user) certificate pertaining to this SignerInfo.</span><a href="#l250"></a>
<span id="l251">     */</span><a href="#l251"></a>
<span id="l252">    public X509Certificate getCertificate(PKCS7 block)</span><a href="#l252"></a>
<span id="l253">        throws IOException</span><a href="#l253"></a>
<span id="l254">    {</span><a href="#l254"></a>
<span id="l255">        return block.getCertificate(certificateSerialNumber, issuerName);</span><a href="#l255"></a>
<span id="l256">    }</span><a href="#l256"></a>
<span id="l257"></span><a href="#l257"></a>
<span id="l258">    /*</span><a href="#l258"></a>
<span id="l259">     * Returns the certificate chain pertaining to this SignerInfo.</span><a href="#l259"></a>
<span id="l260">     */</span><a href="#l260"></a>
<span id="l261">    public ArrayList&lt;X509Certificate&gt; getCertificateChain(PKCS7 block)</span><a href="#l261"></a>
<span id="l262">        throws IOException</span><a href="#l262"></a>
<span id="l263">    {</span><a href="#l263"></a>
<span id="l264">        X509Certificate userCert;</span><a href="#l264"></a>
<span id="l265">        userCert = block.getCertificate(certificateSerialNumber, issuerName);</span><a href="#l265"></a>
<span id="l266">        if (userCert == null)</span><a href="#l266"></a>
<span id="l267">            return null;</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">        ArrayList&lt;X509Certificate&gt; certList = new ArrayList&lt;&gt;();</span><a href="#l269"></a>
<span id="l270">        certList.add(userCert);</span><a href="#l270"></a>
<span id="l271"></span><a href="#l271"></a>
<span id="l272">        X509Certificate[] pkcsCerts = block.getCertificates();</span><a href="#l272"></a>
<span id="l273">        if (pkcsCerts == null</span><a href="#l273"></a>
<span id="l274">            || userCert.getSubjectDN().equals(userCert.getIssuerDN())) {</span><a href="#l274"></a>
<span id="l275">            return certList;</span><a href="#l275"></a>
<span id="l276">        }</span><a href="#l276"></a>
<span id="l277"></span><a href="#l277"></a>
<span id="l278">        Principal issuer = userCert.getIssuerDN();</span><a href="#l278"></a>
<span id="l279">        int start = 0;</span><a href="#l279"></a>
<span id="l280">        while (true) {</span><a href="#l280"></a>
<span id="l281">            boolean match = false;</span><a href="#l281"></a>
<span id="l282">            int i = start;</span><a href="#l282"></a>
<span id="l283">            while (i &lt; pkcsCerts.length) {</span><a href="#l283"></a>
<span id="l284">                if (issuer.equals(pkcsCerts[i].getSubjectDN())) {</span><a href="#l284"></a>
<span id="l285">                    // next cert in chain found</span><a href="#l285"></a>
<span id="l286">                    certList.add(pkcsCerts[i]);</span><a href="#l286"></a>
<span id="l287">                    // if selected cert is self-signed, we're done</span><a href="#l287"></a>
<span id="l288">                    // constructing the chain</span><a href="#l288"></a>
<span id="l289">                    if (pkcsCerts[i].getSubjectDN().equals(</span><a href="#l289"></a>
<span id="l290">                                            pkcsCerts[i].getIssuerDN())) {</span><a href="#l290"></a>
<span id="l291">                        start = pkcsCerts.length;</span><a href="#l291"></a>
<span id="l292">                    } else {</span><a href="#l292"></a>
<span id="l293">                        issuer = pkcsCerts[i].getIssuerDN();</span><a href="#l293"></a>
<span id="l294">                        X509Certificate tmpCert = pkcsCerts[start];</span><a href="#l294"></a>
<span id="l295">                        pkcsCerts[start] = pkcsCerts[i];</span><a href="#l295"></a>
<span id="l296">                        pkcsCerts[i] = tmpCert;</span><a href="#l296"></a>
<span id="l297">                        start++;</span><a href="#l297"></a>
<span id="l298">                    }</span><a href="#l298"></a>
<span id="l299">                    match = true;</span><a href="#l299"></a>
<span id="l300">                    break;</span><a href="#l300"></a>
<span id="l301">                } else {</span><a href="#l301"></a>
<span id="l302">                    i++;</span><a href="#l302"></a>
<span id="l303">                }</span><a href="#l303"></a>
<span id="l304">            }</span><a href="#l304"></a>
<span id="l305">            if (!match)</span><a href="#l305"></a>
<span id="l306">                break;</span><a href="#l306"></a>
<span id="l307">        }</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">        return certList;</span><a href="#l309"></a>
<span id="l310">    }</span><a href="#l310"></a>
<span id="l311"></span><a href="#l311"></a>
<span id="l312">    /* Returns null if verify fails, this signerInfo if</span><a href="#l312"></a>
<span id="l313">       verify succeeds. */</span><a href="#l313"></a>
<span id="l314">    SignerInfo verify(PKCS7 block, byte[] data)</span><a href="#l314"></a>
<span id="l315">    throws NoSuchAlgorithmException, SignatureException {</span><a href="#l315"></a>
<span id="l316"></span><a href="#l316"></a>
<span id="l317">        try {</span><a href="#l317"></a>
<span id="l318">            Timestamp timestamp = getTimestamp();</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">            ContentInfo content = block.getContentInfo();</span><a href="#l320"></a>
<span id="l321">            if (data == null) {</span><a href="#l321"></a>
<span id="l322">                data = content.getContentBytes();</span><a href="#l322"></a>
<span id="l323">            }</span><a href="#l323"></a>
<span id="l324"></span><a href="#l324"></a>
<span id="l325">            String digestAlgName = digestAlgorithmId.getName();</span><a href="#l325"></a>
<span id="l326">            algorithms.put(digestAlgorithmId, &quot;SignerInfo digestAlgorithm field&quot;);</span><a href="#l326"></a>
<span id="l327"></span><a href="#l327"></a>
<span id="l328">            byte[] dataSigned;</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">            // if there are authenticate attributes, get the message</span><a href="#l330"></a>
<span id="l331">            // digest and compare it with the digest of data</span><a href="#l331"></a>
<span id="l332">            if (authenticatedAttributes == null) {</span><a href="#l332"></a>
<span id="l333">                dataSigned = data;</span><a href="#l333"></a>
<span id="l334">            } else {</span><a href="#l334"></a>
<span id="l335"></span><a href="#l335"></a>
<span id="l336">                // first, check content type</span><a href="#l336"></a>
<span id="l337">                ObjectIdentifier contentType = (ObjectIdentifier)</span><a href="#l337"></a>
<span id="l338">                       authenticatedAttributes.getAttributeValue(</span><a href="#l338"></a>
<span id="l339">                         PKCS9Attribute.CONTENT_TYPE_OID);</span><a href="#l339"></a>
<span id="l340">                if (contentType == null ||</span><a href="#l340"></a>
<span id="l341">                    !contentType.equals((Object)content.contentType))</span><a href="#l341"></a>
<span id="l342">                    return null;  // contentType does not match, bad SignerInfo</span><a href="#l342"></a>
<span id="l343"></span><a href="#l343"></a>
<span id="l344">                // now, check message digest</span><a href="#l344"></a>
<span id="l345">                byte[] messageDigest = (byte[])</span><a href="#l345"></a>
<span id="l346">                    authenticatedAttributes.getAttributeValue(</span><a href="#l346"></a>
<span id="l347">                         PKCS9Attribute.MESSAGE_DIGEST_OID);</span><a href="#l347"></a>
<span id="l348"></span><a href="#l348"></a>
<span id="l349">                if (messageDigest == null) // fail if there is no message digest</span><a href="#l349"></a>
<span id="l350">                    return null;</span><a href="#l350"></a>
<span id="l351"></span><a href="#l351"></a>
<span id="l352">                MessageDigest md = MessageDigest.getInstance(digestAlgName);</span><a href="#l352"></a>
<span id="l353">                byte[] computedMessageDigest = md.digest(data);</span><a href="#l353"></a>
<span id="l354"></span><a href="#l354"></a>
<span id="l355">                if (!MessageDigest.isEqual(messageDigest, computedMessageDigest)) {</span><a href="#l355"></a>
<span id="l356">                    return null;</span><a href="#l356"></a>
<span id="l357">                }</span><a href="#l357"></a>
<span id="l358"></span><a href="#l358"></a>
<span id="l359">                // message digest attribute matched</span><a href="#l359"></a>
<span id="l360">                // digest of original data</span><a href="#l360"></a>
<span id="l361"></span><a href="#l361"></a>
<span id="l362">                // the data actually signed is the DER encoding of</span><a href="#l362"></a>
<span id="l363">                // the authenticated attributes (tagged with</span><a href="#l363"></a>
<span id="l364">                // the &quot;SET OF&quot; tag, not 0xA0).</span><a href="#l364"></a>
<span id="l365">                dataSigned = authenticatedAttributes.getDerEncoding();</span><a href="#l365"></a>
<span id="l366">            }</span><a href="#l366"></a>
<span id="l367"></span><a href="#l367"></a>
<span id="l368">            // put together digest algorithm and encryption algorithm</span><a href="#l368"></a>
<span id="l369">            // to form signing algorithm</span><a href="#l369"></a>
<span id="l370">            String encryptionAlgName =</span><a href="#l370"></a>
<span id="l371">                getDigestEncryptionAlgorithmId().getName();</span><a href="#l371"></a>
<span id="l372"></span><a href="#l372"></a>
<span id="l373">            // Workaround: sometimes the encryptionAlgname is actually</span><a href="#l373"></a>
<span id="l374">            // a signature name</span><a href="#l374"></a>
<span id="l375">            String tmp = AlgorithmId.getEncAlgFromSigAlg(encryptionAlgName);</span><a href="#l375"></a>
<span id="l376">            if (tmp != null) encryptionAlgName = tmp;</span><a href="#l376"></a>
<span id="l377">            String sigAlgName = AlgorithmId.makeSigAlg(</span><a href="#l377"></a>
<span id="l378">                    digestAlgName, encryptionAlgName);</span><a href="#l378"></a>
<span id="l379">            try {</span><a href="#l379"></a>
<span id="l380">                ObjectIdentifier oid = AlgorithmId.get(sigAlgName).getOID();</span><a href="#l380"></a>
<span id="l381">                AlgorithmId sigAlgId =</span><a href="#l381"></a>
<span id="l382">                    new AlgorithmId(oid,</span><a href="#l382"></a>
<span id="l383">                            digestEncryptionAlgorithmId.getParameters());</span><a href="#l383"></a>
<span id="l384">                algorithms.put(sigAlgId,</span><a href="#l384"></a>
<span id="l385">                    &quot;SignerInfo digestEncryptionAlgorithm field&quot;);</span><a href="#l385"></a>
<span id="l386">            } catch (NoSuchAlgorithmException ignore) {</span><a href="#l386"></a>
<span id="l387">            }</span><a href="#l387"></a>
<span id="l388"></span><a href="#l388"></a>
<span id="l389">            X509Certificate cert = getCertificate(block);</span><a href="#l389"></a>
<span id="l390">            if (cert == null) {</span><a href="#l390"></a>
<span id="l391">                return null;</span><a href="#l391"></a>
<span id="l392">            }</span><a href="#l392"></a>
<span id="l393">            PublicKey key = cert.getPublicKey();</span><a href="#l393"></a>
<span id="l394"></span><a href="#l394"></a>
<span id="l395">            if (cert.hasUnsupportedCriticalExtension()) {</span><a href="#l395"></a>
<span id="l396">                throw new SignatureException(&quot;Certificate has unsupported &quot;</span><a href="#l396"></a>
<span id="l397">                                             + &quot;critical extension(s)&quot;);</span><a href="#l397"></a>
<span id="l398">            }</span><a href="#l398"></a>
<span id="l399"></span><a href="#l399"></a>
<span id="l400">            // Make sure that if the usage of the key in the certificate is</span><a href="#l400"></a>
<span id="l401">            // restricted, it can be used for digital signatures.</span><a href="#l401"></a>
<span id="l402">            // XXX We may want to check for additional extensions in the</span><a href="#l402"></a>
<span id="l403">            // future.</span><a href="#l403"></a>
<span id="l404">            boolean[] keyUsageBits = cert.getKeyUsage();</span><a href="#l404"></a>
<span id="l405">            if (keyUsageBits != null) {</span><a href="#l405"></a>
<span id="l406">                KeyUsageExtension keyUsage;</span><a href="#l406"></a>
<span id="l407">                try {</span><a href="#l407"></a>
<span id="l408">                    // We don't care whether or not this extension was marked</span><a href="#l408"></a>
<span id="l409">                    // critical in the certificate.</span><a href="#l409"></a>
<span id="l410">                    // We're interested only in its value (i.e., the bits set)</span><a href="#l410"></a>
<span id="l411">                    // and treat the extension as critical.</span><a href="#l411"></a>
<span id="l412">                    keyUsage = new KeyUsageExtension(keyUsageBits);</span><a href="#l412"></a>
<span id="l413">                } catch (IOException ioe) {</span><a href="#l413"></a>
<span id="l414">                    throw new SignatureException(&quot;Failed to parse keyUsage &quot;</span><a href="#l414"></a>
<span id="l415">                                                 + &quot;extension&quot;);</span><a href="#l415"></a>
<span id="l416">                }</span><a href="#l416"></a>
<span id="l417"></span><a href="#l417"></a>
<span id="l418">                boolean digSigAllowed = keyUsage.get(</span><a href="#l418"></a>
<span id="l419">                        KeyUsageExtension.DIGITAL_SIGNATURE).booleanValue();</span><a href="#l419"></a>
<span id="l420"></span><a href="#l420"></a>
<span id="l421">                boolean nonRepuAllowed = keyUsage.get(</span><a href="#l421"></a>
<span id="l422">                        KeyUsageExtension.NON_REPUDIATION).booleanValue();</span><a href="#l422"></a>
<span id="l423"></span><a href="#l423"></a>
<span id="l424">                if (!digSigAllowed &amp;&amp; !nonRepuAllowed) {</span><a href="#l424"></a>
<span id="l425">                    throw new SignatureException(&quot;Key usage restricted: &quot;</span><a href="#l425"></a>
<span id="l426">                                                 + &quot;cannot be used for &quot;</span><a href="#l426"></a>
<span id="l427">                                                 + &quot;digital signatures&quot;);</span><a href="#l427"></a>
<span id="l428">                }</span><a href="#l428"></a>
<span id="l429">            }</span><a href="#l429"></a>
<span id="l430"></span><a href="#l430"></a>
<span id="l431">            Signature sig = Signature.getInstance(sigAlgName);</span><a href="#l431"></a>
<span id="l432"></span><a href="#l432"></a>
<span id="l433">            AlgorithmParameters ap =</span><a href="#l433"></a>
<span id="l434">                digestEncryptionAlgorithmId.getParameters();</span><a href="#l434"></a>
<span id="l435">            try {</span><a href="#l435"></a>
<span id="l436">                SignatureUtil.initVerifyWithParam(sig, key,</span><a href="#l436"></a>
<span id="l437">                    SignatureUtil.getParamSpec(sigAlgName, ap));</span><a href="#l437"></a>
<span id="l438">            } catch (ProviderException | InvalidAlgorithmParameterException |</span><a href="#l438"></a>
<span id="l439">                     InvalidKeyException e) {</span><a href="#l439"></a>
<span id="l440">                throw new SignatureException(e.getMessage(), e);</span><a href="#l440"></a>
<span id="l441">            }</span><a href="#l441"></a>
<span id="l442"></span><a href="#l442"></a>
<span id="l443">            sig.update(dataSigned);</span><a href="#l443"></a>
<span id="l444">            if (sig.verify(encryptedDigest)) {</span><a href="#l444"></a>
<span id="l445">                return this;</span><a href="#l445"></a>
<span id="l446">            }</span><a href="#l446"></a>
<span id="l447">        } catch (IOException | CertificateException e) {</span><a href="#l447"></a>
<span id="l448">            throw new SignatureException(&quot;Error verifying signature&quot;, e);</span><a href="#l448"></a>
<span id="l449">        }</span><a href="#l449"></a>
<span id="l450">        return null;</span><a href="#l450"></a>
<span id="l451">    }</span><a href="#l451"></a>
<span id="l452"></span><a href="#l452"></a>
<span id="l453">    /* Verify the content of the pkcs7 block. */</span><a href="#l453"></a>
<span id="l454">    SignerInfo verify(PKCS7 block)</span><a href="#l454"></a>
<span id="l455">        throws NoSuchAlgorithmException, SignatureException {</span><a href="#l455"></a>
<span id="l456">        return verify(block, null);</span><a href="#l456"></a>
<span id="l457">    }</span><a href="#l457"></a>
<span id="l458"></span><a href="#l458"></a>
<span id="l459">    public BigInteger getVersion() {</span><a href="#l459"></a>
<span id="l460">            return version;</span><a href="#l460"></a>
<span id="l461">    }</span><a href="#l461"></a>
<span id="l462"></span><a href="#l462"></a>
<span id="l463">    public X500Name getIssuerName() {</span><a href="#l463"></a>
<span id="l464">        return issuerName;</span><a href="#l464"></a>
<span id="l465">    }</span><a href="#l465"></a>
<span id="l466"></span><a href="#l466"></a>
<span id="l467">    public BigInteger getCertificateSerialNumber() {</span><a href="#l467"></a>
<span id="l468">        return certificateSerialNumber;</span><a href="#l468"></a>
<span id="l469">    }</span><a href="#l469"></a>
<span id="l470"></span><a href="#l470"></a>
<span id="l471">    public AlgorithmId getDigestAlgorithmId() {</span><a href="#l471"></a>
<span id="l472">        return digestAlgorithmId;</span><a href="#l472"></a>
<span id="l473">    }</span><a href="#l473"></a>
<span id="l474"></span><a href="#l474"></a>
<span id="l475">    public PKCS9Attributes getAuthenticatedAttributes() {</span><a href="#l475"></a>
<span id="l476">        return authenticatedAttributes;</span><a href="#l476"></a>
<span id="l477">    }</span><a href="#l477"></a>
<span id="l478"></span><a href="#l478"></a>
<span id="l479">    public AlgorithmId getDigestEncryptionAlgorithmId() {</span><a href="#l479"></a>
<span id="l480">        return digestEncryptionAlgorithmId;</span><a href="#l480"></a>
<span id="l481">    }</span><a href="#l481"></a>
<span id="l482"></span><a href="#l482"></a>
<span id="l483">    public byte[] getEncryptedDigest() {</span><a href="#l483"></a>
<span id="l484">        return encryptedDigest;</span><a href="#l484"></a>
<span id="l485">    }</span><a href="#l485"></a>
<span id="l486"></span><a href="#l486"></a>
<span id="l487">    public PKCS9Attributes getUnauthenticatedAttributes() {</span><a href="#l487"></a>
<span id="l488">        return unauthenticatedAttributes;</span><a href="#l488"></a>
<span id="l489">    }</span><a href="#l489"></a>
<span id="l490"></span><a href="#l490"></a>
<span id="l491">    /**</span><a href="#l491"></a>
<span id="l492">     * Returns the timestamp PKCS7 data unverified.</span><a href="#l492"></a>
<span id="l493">     * @return a PKCS7 object</span><a href="#l493"></a>
<span id="l494">     */</span><a href="#l494"></a>
<span id="l495">    public PKCS7 getTsToken() throws IOException {</span><a href="#l495"></a>
<span id="l496">        if (unauthenticatedAttributes == null) {</span><a href="#l496"></a>
<span id="l497">            return null;</span><a href="#l497"></a>
<span id="l498">        }</span><a href="#l498"></a>
<span id="l499">        PKCS9Attribute tsTokenAttr =</span><a href="#l499"></a>
<span id="l500">                unauthenticatedAttributes.getAttribute(</span><a href="#l500"></a>
<span id="l501">                        PKCS9Attribute.SIGNATURE_TIMESTAMP_TOKEN_OID);</span><a href="#l501"></a>
<span id="l502">        if (tsTokenAttr == null) {</span><a href="#l502"></a>
<span id="l503">            return null;</span><a href="#l503"></a>
<span id="l504">        }</span><a href="#l504"></a>
<span id="l505">        return new PKCS7((byte[])tsTokenAttr.getValue());</span><a href="#l505"></a>
<span id="l506">    }</span><a href="#l506"></a>
<span id="l507"></span><a href="#l507"></a>
<span id="l508">    /*</span><a href="#l508"></a>
<span id="l509">     * Extracts a timestamp from a PKCS7 SignerInfo.</span><a href="#l509"></a>
<span id="l510">     *</span><a href="#l510"></a>
<span id="l511">     * Examines the signer's unsigned attributes for a</span><a href="#l511"></a>
<span id="l512">     * {@code signatureTimestampToken} attribute. If present,</span><a href="#l512"></a>
<span id="l513">     * then it is parsed to extract the date and time at which the</span><a href="#l513"></a>
<span id="l514">     * timestamp was generated.</span><a href="#l514"></a>
<span id="l515">     *</span><a href="#l515"></a>
<span id="l516">     * @param info A signer information element of a PKCS 7 block.</span><a href="#l516"></a>
<span id="l517">     *</span><a href="#l517"></a>
<span id="l518">     * @return A timestamp token or null if none is present.</span><a href="#l518"></a>
<span id="l519">     * @throws IOException if an error is encountered while parsing the</span><a href="#l519"></a>
<span id="l520">     *         PKCS7 data.</span><a href="#l520"></a>
<span id="l521">     * @throws NoSuchAlgorithmException if an error is encountered while</span><a href="#l521"></a>
<span id="l522">     *         verifying the PKCS7 object.</span><a href="#l522"></a>
<span id="l523">     * @throws SignatureException if an error is encountered while</span><a href="#l523"></a>
<span id="l524">     *         verifying the PKCS7 object.</span><a href="#l524"></a>
<span id="l525">     * @throws CertificateException if an error is encountered while generating</span><a href="#l525"></a>
<span id="l526">     *         the TSA's certpath.</span><a href="#l526"></a>
<span id="l527">     */</span><a href="#l527"></a>
<span id="l528">    public Timestamp getTimestamp()</span><a href="#l528"></a>
<span id="l529">        throws IOException, NoSuchAlgorithmException, SignatureException,</span><a href="#l529"></a>
<span id="l530">               CertificateException</span><a href="#l530"></a>
<span id="l531">    {</span><a href="#l531"></a>
<span id="l532">        if (timestamp != null || !hasTimestamp)</span><a href="#l532"></a>
<span id="l533">            return timestamp;</span><a href="#l533"></a>
<span id="l534"></span><a href="#l534"></a>
<span id="l535">        PKCS7 tsToken = getTsToken();</span><a href="#l535"></a>
<span id="l536">        if (tsToken == null) {</span><a href="#l536"></a>
<span id="l537">            hasTimestamp = false;</span><a href="#l537"></a>
<span id="l538">            return null;</span><a href="#l538"></a>
<span id="l539">        }</span><a href="#l539"></a>
<span id="l540"></span><a href="#l540"></a>
<span id="l541">        // Extract the content (an encoded timestamp token info)</span><a href="#l541"></a>
<span id="l542">        byte[] encTsTokenInfo = tsToken.getContentInfo().getData();</span><a href="#l542"></a>
<span id="l543">        // Extract the signer (the Timestamping Authority)</span><a href="#l543"></a>
<span id="l544">        // while verifying the content</span><a href="#l544"></a>
<span id="l545">        SignerInfo[] tsa = tsToken.verify(encTsTokenInfo);</span><a href="#l545"></a>
<span id="l546">        if (tsa == null || tsa.length == 0) {</span><a href="#l546"></a>
<span id="l547">            throw new SignatureException(&quot;Unable to verify timestamp&quot;);</span><a href="#l547"></a>
<span id="l548">        }</span><a href="#l548"></a>
<span id="l549">        // Expect only one signer</span><a href="#l549"></a>
<span id="l550">        ArrayList&lt;X509Certificate&gt; chain = tsa[0].getCertificateChain(tsToken);</span><a href="#l550"></a>
<span id="l551">        CertificateFactory cf = CertificateFactory.getInstance(&quot;X.509&quot;);</span><a href="#l551"></a>
<span id="l552">        CertPath tsaChain = cf.generateCertPath(chain);</span><a href="#l552"></a>
<span id="l553">        // Create a timestamp token info object</span><a href="#l553"></a>
<span id="l554">        TimestampToken tsTokenInfo = new TimestampToken(encTsTokenInfo);</span><a href="#l554"></a>
<span id="l555">        // Check that the signature timestamp applies to this signature</span><a href="#l555"></a>
<span id="l556">        verifyTimestamp(tsTokenInfo);</span><a href="#l556"></a>
<span id="l557">        algorithms.putAll(tsa[0].algorithms);</span><a href="#l557"></a>
<span id="l558">        // Create a timestamp object</span><a href="#l558"></a>
<span id="l559">        timestamp = new Timestamp(tsTokenInfo.getDate(), tsaChain);</span><a href="#l559"></a>
<span id="l560">        return timestamp;</span><a href="#l560"></a>
<span id="l561">    }</span><a href="#l561"></a>
<span id="l562"></span><a href="#l562"></a>
<span id="l563">    /*</span><a href="#l563"></a>
<span id="l564">     * Check that the signature timestamp applies to this signature.</span><a href="#l564"></a>
<span id="l565">     * Match the hash present in the signature timestamp token against the hash</span><a href="#l565"></a>
<span id="l566">     * of this signature.</span><a href="#l566"></a>
<span id="l567">     */</span><a href="#l567"></a>
<span id="l568">    private void verifyTimestamp(TimestampToken token)</span><a href="#l568"></a>
<span id="l569">        throws NoSuchAlgorithmException, SignatureException {</span><a href="#l569"></a>
<span id="l570"></span><a href="#l570"></a>
<span id="l571">        AlgorithmId digestAlgId = token.getHashAlgorithm();</span><a href="#l571"></a>
<span id="l572">        algorithms.put(digestAlgId, &quot;TimestampToken digestAlgorithm field&quot;);</span><a href="#l572"></a>
<span id="l573"></span><a href="#l573"></a>
<span id="l574">        MessageDigest md = MessageDigest.getInstance(digestAlgId.getName());</span><a href="#l574"></a>
<span id="l575"></span><a href="#l575"></a>
<span id="l576">        if (!MessageDigest.isEqual(token.getHashedMessage(),</span><a href="#l576"></a>
<span id="l577">            md.digest(encryptedDigest))) {</span><a href="#l577"></a>
<span id="l578"></span><a href="#l578"></a>
<span id="l579">            throw new SignatureException(&quot;Signature timestamp (#&quot; +</span><a href="#l579"></a>
<span id="l580">                token.getSerialNumber() + &quot;) generated on &quot; + token.getDate() +</span><a href="#l580"></a>
<span id="l581">                &quot; is inapplicable&quot;);</span><a href="#l581"></a>
<span id="l582">        }</span><a href="#l582"></a>
<span id="l583"></span><a href="#l583"></a>
<span id="l584">        if (debug != null) {</span><a href="#l584"></a>
<span id="l585">            debug.println();</span><a href="#l585"></a>
<span id="l586">            debug.println(&quot;Detected signature timestamp (#&quot; +</span><a href="#l586"></a>
<span id="l587">                token.getSerialNumber() + &quot;) generated on &quot; + token.getDate());</span><a href="#l587"></a>
<span id="l588">            debug.println();</span><a href="#l588"></a>
<span id="l589">        }</span><a href="#l589"></a>
<span id="l590">    }</span><a href="#l590"></a>
<span id="l591"></span><a href="#l591"></a>
<span id="l592">    public String toString() {</span><a href="#l592"></a>
<span id="l593">        HexDumpEncoder hexDump = new HexDumpEncoder();</span><a href="#l593"></a>
<span id="l594"></span><a href="#l594"></a>
<span id="l595">        String out = &quot;&quot;;</span><a href="#l595"></a>
<span id="l596"></span><a href="#l596"></a>
<span id="l597">        out += &quot;Signer Info for (issuer): &quot; + issuerName + &quot;\n&quot;;</span><a href="#l597"></a>
<span id="l598">        out += &quot;\tversion: &quot; + Debug.toHexString(version) + &quot;\n&quot;;</span><a href="#l598"></a>
<span id="l599">        out += &quot;\tcertificateSerialNumber: &quot; +</span><a href="#l599"></a>
<span id="l600">               Debug.toHexString(certificateSerialNumber) + &quot;\n&quot;;</span><a href="#l600"></a>
<span id="l601">        out += &quot;\tdigestAlgorithmId: &quot; + digestAlgorithmId + &quot;\n&quot;;</span><a href="#l601"></a>
<span id="l602">        if (authenticatedAttributes != null) {</span><a href="#l602"></a>
<span id="l603">            out += &quot;\tauthenticatedAttributes: &quot; + authenticatedAttributes +</span><a href="#l603"></a>
<span id="l604">                   &quot;\n&quot;;</span><a href="#l604"></a>
<span id="l605">        }</span><a href="#l605"></a>
<span id="l606">        out += &quot;\tdigestEncryptionAlgorithmId: &quot; + digestEncryptionAlgorithmId +</span><a href="#l606"></a>
<span id="l607">            &quot;\n&quot;;</span><a href="#l607"></a>
<span id="l608"></span><a href="#l608"></a>
<span id="l609">        out += &quot;\tencryptedDigest: &quot; + &quot;\n&quot; +</span><a href="#l609"></a>
<span id="l610">            hexDump.encodeBuffer(encryptedDigest) + &quot;\n&quot;;</span><a href="#l610"></a>
<span id="l611">        if (unauthenticatedAttributes != null) {</span><a href="#l611"></a>
<span id="l612">            out += &quot;\tunauthenticatedAttributes: &quot; +</span><a href="#l612"></a>
<span id="l613">                   unauthenticatedAttributes + &quot;\n&quot;;</span><a href="#l613"></a>
<span id="l614">        }</span><a href="#l614"></a>
<span id="l615">        return out;</span><a href="#l615"></a>
<span id="l616">    }</span><a href="#l616"></a>
<span id="l617"></span><a href="#l617"></a>
<span id="l618">    /**</span><a href="#l618"></a>
<span id="l619">     * Verify all of the algorithms in the array of SignerInfos against the</span><a href="#l619"></a>
<span id="l620">     * constraints in the jdk.jar.disabledAlgorithms security property.</span><a href="#l620"></a>
<span id="l621">     *</span><a href="#l621"></a>
<span id="l622">     * @param infos array of SignerInfos</span><a href="#l622"></a>
<span id="l623">     * @param params constraint parameters</span><a href="#l623"></a>
<span id="l624">     * @param name the name of the signer's PKCS7 file</span><a href="#l624"></a>
<span id="l625">     * @return a set of algorithms that passed the checks and are not disabled</span><a href="#l625"></a>
<span id="l626">     */</span><a href="#l626"></a>
<span id="l627">    public static Set&lt;String&gt; verifyAlgorithms(SignerInfo[] infos,</span><a href="#l627"></a>
<span id="l628">        JarConstraintsParameters params, String name) throws SignatureException {</span><a href="#l628"></a>
<span id="l629">        Map&lt;AlgorithmId, String&gt; algorithms = new HashMap&lt;&gt;();</span><a href="#l629"></a>
<span id="l630">        for (SignerInfo info : infos) {</span><a href="#l630"></a>
<span id="l631">            algorithms.putAll(info.algorithms);</span><a href="#l631"></a>
<span id="l632">        }</span><a href="#l632"></a>
<span id="l633"></span><a href="#l633"></a>
<span id="l634">        Set&lt;String&gt; enabledAlgorithms = new HashSet&lt;&gt;();</span><a href="#l634"></a>
<span id="l635">        try {</span><a href="#l635"></a>
<span id="l636">            for (Map.Entry&lt;AlgorithmId, String&gt; algorithm : algorithms.entrySet()) {</span><a href="#l636"></a>
<span id="l637">                params.setExtendedExceptionMsg(name, algorithm.getValue());</span><a href="#l637"></a>
<span id="l638">                AlgorithmId algId = algorithm.getKey();</span><a href="#l638"></a>
<span id="l639">                JAR_DISABLED_CHECK.permits(algId.getName(),</span><a href="#l639"></a>
<span id="l640">                    algId.getParameters(), params);</span><a href="#l640"></a>
<span id="l641">                enabledAlgorithms.add(algId.getName());</span><a href="#l641"></a>
<span id="l642">            }</span><a href="#l642"></a>
<span id="l643">        } catch (CertPathValidatorException e) {</span><a href="#l643"></a>
<span id="l644">            throw new SignatureException(e);</span><a href="#l644"></a>
<span id="l645">        }</span><a href="#l645"></a>
<span id="l646">        return enabledAlgorithms;</span><a href="#l646"></a>
<span id="l647">    }</span><a href="#l647"></a>
<span id="l648">}</span><a href="#l648"></a></pre>
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

