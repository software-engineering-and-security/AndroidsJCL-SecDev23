<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/provider/certpath/CertPathConstraintsParameters.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/provider/certpath/CertPathConstraintsParameters.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/provider/certpath/CertPathConstraintsParameters.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/provider/certpath/CertPathConstraintsParameters.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/provider/certpath/CertPathConstraintsParameters.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/provider/certpath/CertPathConstraintsParameters.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/provider/certpath/CertPathConstraintsParameters.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/provider/certpath/CertPathConstraintsParameters.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"></td>
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
<span id="l2"> * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.security.Key;</span><a href="#l28"></a>
<span id="l29">import java.security.cert.TrustAnchor;</span><a href="#l29"></a>
<span id="l30">import java.security.cert.X509Certificate;</span><a href="#l30"></a>
<span id="l31">import java.util.Date;</span><a href="#l31"></a>
<span id="l32">import java.util.Set;</span><a href="#l32"></a>
<span id="l33">import java.util.Collections;</span><a href="#l33"></a>
<span id="l34"></span><a href="#l34"></a>
<span id="l35">import sun.security.util.ConstraintsParameters;</span><a href="#l35"></a>
<span id="l36">import sun.security.validator.Validator;</span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38">/**</span><a href="#l38"></a>
<span id="l39"> * This class contains parameters for checking certificates against</span><a href="#l39"></a>
<span id="l40"> * constraints specified in the jdk.certpath.disabledAlgorithms security</span><a href="#l40"></a>
<span id="l41"> * property.</span><a href="#l41"></a>
<span id="l42"> */</span><a href="#l42"></a>
<span id="l43">class CertPathConstraintsParameters implements ConstraintsParameters {</span><a href="#l43"></a>
<span id="l44">    // The public key of the certificate</span><a href="#l44"></a>
<span id="l45">    private final Key key;</span><a href="#l45"></a>
<span id="l46">    // The certificate's trust anchor which will be checked against the</span><a href="#l46"></a>
<span id="l47">    // jdkCA constraint, if specified.</span><a href="#l47"></a>
<span id="l48">    private final TrustAnchor anchor;</span><a href="#l48"></a>
<span id="l49">    // The PKIXParameter validity date or the timestamp of the signed JAR</span><a href="#l49"></a>
<span id="l50">    // file, if this chain is associated with a timestamped signed JAR.</span><a href="#l50"></a>
<span id="l51">    private final Date date;</span><a href="#l51"></a>
<span id="l52">    // The variant or usage of this certificate</span><a href="#l52"></a>
<span id="l53">    private final String variant;</span><a href="#l53"></a>
<span id="l54">    // The certificate being checked (may be null if a CRL or OCSPResponse is</span><a href="#l54"></a>
<span id="l55">    // being checked)</span><a href="#l55"></a>
<span id="l56">    private final X509Certificate cert;</span><a href="#l56"></a>
<span id="l57"></span><a href="#l57"></a>
<span id="l58">    public CertPathConstraintsParameters(X509Certificate cert,</span><a href="#l58"></a>
<span id="l59">            String variant, TrustAnchor anchor, Date date) {</span><a href="#l59"></a>
<span id="l60">        this(cert.getPublicKey(), variant, anchor, date, cert);</span><a href="#l60"></a>
<span id="l61">    }</span><a href="#l61"></a>
<span id="l62"></span><a href="#l62"></a>
<span id="l63">    public CertPathConstraintsParameters(Key key, String variant,</span><a href="#l63"></a>
<span id="l64">            TrustAnchor anchor) {</span><a href="#l64"></a>
<span id="l65">        this(key, variant, anchor, null, null);</span><a href="#l65"></a>
<span id="l66">    }</span><a href="#l66"></a>
<span id="l67"></span><a href="#l67"></a>
<span id="l68">    private CertPathConstraintsParameters(Key key, String variant,</span><a href="#l68"></a>
<span id="l69">            TrustAnchor anchor, Date date, X509Certificate cert) {</span><a href="#l69"></a>
<span id="l70">        this.key = key;</span><a href="#l70"></a>
<span id="l71">        this.variant = (variant == null ? Validator.VAR_GENERIC : variant);</span><a href="#l71"></a>
<span id="l72">        this.anchor = anchor;</span><a href="#l72"></a>
<span id="l73">        this.date = date;</span><a href="#l73"></a>
<span id="l74">        this.cert = cert;</span><a href="#l74"></a>
<span id="l75">    }</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">    @Override</span><a href="#l77"></a>
<span id="l78">    public boolean anchorIsJdkCA() {</span><a href="#l78"></a>
<span id="l79">        return CertPathHelper.isJdkCA(anchor);</span><a href="#l79"></a>
<span id="l80">    }</span><a href="#l80"></a>
<span id="l81"></span><a href="#l81"></a>
<span id="l82">    @Override</span><a href="#l82"></a>
<span id="l83">    public Set&lt;Key&gt; getKeys() {</span><a href="#l83"></a>
<span id="l84">        return (key == null) ? Collections.emptySet()</span><a href="#l84"></a>
<span id="l85">                             : Collections.singleton(key);</span><a href="#l85"></a>
<span id="l86">    }</span><a href="#l86"></a>
<span id="l87"></span><a href="#l87"></a>
<span id="l88">    @Override</span><a href="#l88"></a>
<span id="l89">    public Date getDate() {</span><a href="#l89"></a>
<span id="l90">        return date;</span><a href="#l90"></a>
<span id="l91">    }</span><a href="#l91"></a>
<span id="l92"></span><a href="#l92"></a>
<span id="l93">    @Override</span><a href="#l93"></a>
<span id="l94">    public String getVariant() {</span><a href="#l94"></a>
<span id="l95">        return variant;</span><a href="#l95"></a>
<span id="l96">    }</span><a href="#l96"></a>
<span id="l97"></span><a href="#l97"></a>
<span id="l98">    @Override</span><a href="#l98"></a>
<span id="l99">    public String extendedExceptionMsg() {</span><a href="#l99"></a>
<span id="l100">        return (cert == null ? &quot;.&quot;</span><a href="#l100"></a>
<span id="l101">                 : &quot; used with certificate: &quot; +</span><a href="#l101"></a>
<span id="l102">                   cert.getSubjectX500Principal());</span><a href="#l102"></a>
<span id="l103">    }</span><a href="#l103"></a>
<span id="l104"></span><a href="#l104"></a>
<span id="l105">    @Override</span><a href="#l105"></a>
<span id="l106">    public String toString() {</span><a href="#l106"></a>
<span id="l107">        StringBuilder sb = new StringBuilder(&quot;[\n&quot;);</span><a href="#l107"></a>
<span id="l108">        sb.append(&quot;\n  Variant: &quot;).append(variant);</span><a href="#l108"></a>
<span id="l109">        if (anchor != null) {</span><a href="#l109"></a>
<span id="l110">            sb.append(&quot;\n  Anchor: &quot;).append(anchor);</span><a href="#l110"></a>
<span id="l111">        }</span><a href="#l111"></a>
<span id="l112">        if (cert != null) {</span><a href="#l112"></a>
<span id="l113">            sb.append(&quot;\n  Cert Issuer: &quot;)</span><a href="#l113"></a>
<span id="l114">              .append(cert.getIssuerX500Principal());</span><a href="#l114"></a>
<span id="l115">            sb.append(&quot;\n  Cert Subject: &quot;)</span><a href="#l115"></a>
<span id="l116">              .append(cert.getSubjectX500Principal());</span><a href="#l116"></a>
<span id="l117">        }</span><a href="#l117"></a>
<span id="l118">        if (key != null) {</span><a href="#l118"></a>
<span id="l119">            sb.append(&quot;\n  Key: &quot;).append(key.getAlgorithm());</span><a href="#l119"></a>
<span id="l120">        }</span><a href="#l120"></a>
<span id="l121">        if (date != null) {</span><a href="#l121"></a>
<span id="l122">            sb.append(&quot;\n  Date: &quot;).append(date);</span><a href="#l122"></a>
<span id="l123">        }</span><a href="#l123"></a>
<span id="l124">        sb.append(&quot;\n]&quot;);</span><a href="#l124"></a>
<span id="l125">        return sb.toString();</span><a href="#l125"></a>
<span id="l126">    }</span><a href="#l126"></a>
<span id="l127">}</span><a href="#l127"></a></pre>
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

