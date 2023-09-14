<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/sun/security/util/AnchorCertificates.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/util/AnchorCertificates.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/sun/security/util/AnchorCertificates.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/sun/security/util/AnchorCertificates.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/sun/security/util/AnchorCertificates.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/sun/security/util/AnchorCertificates.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/sun/security/util/AnchorCertificates.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/util/AnchorCertificates.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/4899154e9817/src/share/classes/sun/security/util/AnchorCertificates.java">4899154e9817</a> </td>
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
<span id="l2"> * Copyright (c) 2016, 2020, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.io.File;</span><a href="#l28"></a>
<span id="l29">import java.io.FileInputStream;</span><a href="#l29"></a>
<span id="l30">import java.security.AccessController;</span><a href="#l30"></a>
<span id="l31">import java.security.KeyStore;</span><a href="#l31"></a>
<span id="l32">import java.security.PrivilegedAction;</span><a href="#l32"></a>
<span id="l33">import java.security.cert.X509Certificate;</span><a href="#l33"></a>
<span id="l34">import java.util.Collections;</span><a href="#l34"></a>
<span id="l35">import java.util.Enumeration;</span><a href="#l35"></a>
<span id="l36">import java.util.HashSet;</span><a href="#l36"></a>
<span id="l37">import java.util.Set;</span><a href="#l37"></a>
<span id="l38">import javax.security.auth.x500.X500Principal;</span><a href="#l38"></a>
<span id="l39"></span><a href="#l39"></a>
<span id="l40">import sun.security.x509.X509CertImpl;</span><a href="#l40"></a>
<span id="l41"></span><a href="#l41"></a>
<span id="l42">/**</span><a href="#l42"></a>
<span id="l43"> * The purpose of this class is to determine the trust anchor certificates is in</span><a href="#l43"></a>
<span id="l44"> * the cacerts file.  This is used for PKIX CertPath checking.</span><a href="#l44"></a>
<span id="l45"> */</span><a href="#l45"></a>
<span id="l46">public class AnchorCertificates {</span><a href="#l46"></a>
<span id="l47"></span><a href="#l47"></a>
<span id="l48">    private static final Debug debug = Debug.getInstance(&quot;certpath&quot;);</span><a href="#l48"></a>
<span id="l49">    private static final String HASH = &quot;SHA-256&quot;;</span><a href="#l49"></a>
<span id="l50">    private static Set&lt;String&gt; certs = Collections.emptySet();</span><a href="#l50"></a>
<span id="l51">    private static Set&lt;X500Principal&gt; certIssuers = Collections.emptySet();</span><a href="#l51"></a>
<span id="l52"></span><a href="#l52"></a>
<span id="l53">    static  {</span><a href="#l53"></a>
<span id="l54">        AccessController.doPrivileged(new PrivilegedAction&lt;Void&gt;() {</span><a href="#l54"></a>
<span id="l55">            @Override</span><a href="#l55"></a>
<span id="l56">            public Void run() {</span><a href="#l56"></a>
<span id="l57">                File f = new File(System.getProperty(&quot;java.home&quot;),</span><a href="#l57"></a>
<span id="l58">                        &quot;lib/security/cacerts&quot;);</span><a href="#l58"></a>
<span id="l59">                KeyStore cacerts;</span><a href="#l59"></a>
<span id="l60">                try {</span><a href="#l60"></a>
<span id="l61">                    cacerts = KeyStore.getInstance(&quot;JKS&quot;);</span><a href="#l61"></a>
<span id="l62">                    try (FileInputStream fis = new FileInputStream(f)) {</span><a href="#l62"></a>
<span id="l63">                        cacerts.load(fis, null);</span><a href="#l63"></a>
<span id="l64">                        certs = new HashSet&lt;&gt;();</span><a href="#l64"></a>
<span id="l65">                        certIssuers = new HashSet&lt;&gt;();</span><a href="#l65"></a>
<span id="l66">                        Enumeration&lt;String&gt; list = cacerts.aliases();</span><a href="#l66"></a>
<span id="l67">                        while (list.hasMoreElements()) {</span><a href="#l67"></a>
<span id="l68">                            String alias = list.nextElement();</span><a href="#l68"></a>
<span id="l69">                            // Check if this cert is labeled a trust anchor.</span><a href="#l69"></a>
<span id="l70">                            if (alias.contains(&quot; [jdk&quot;)) {</span><a href="#l70"></a>
<span id="l71">                                X509Certificate cert = (X509Certificate) cacerts</span><a href="#l71"></a>
<span id="l72">                                        .getCertificate(alias);</span><a href="#l72"></a>
<span id="l73">                                certs.add(X509CertImpl.getFingerprint(HASH, cert));</span><a href="#l73"></a>
<span id="l74">                                certIssuers.add(cert.getSubjectX500Principal());</span><a href="#l74"></a>
<span id="l75">                            }</span><a href="#l75"></a>
<span id="l76">                        }</span><a href="#l76"></a>
<span id="l77">                    }</span><a href="#l77"></a>
<span id="l78">                } catch (Exception e) {</span><a href="#l78"></a>
<span id="l79">                    if (debug != null) {</span><a href="#l79"></a>
<span id="l80">                        debug.println(&quot;Error parsing cacerts&quot;);</span><a href="#l80"></a>
<span id="l81">                        e.printStackTrace();</span><a href="#l81"></a>
<span id="l82">                    }</span><a href="#l82"></a>
<span id="l83">                }</span><a href="#l83"></a>
<span id="l84">                return null;</span><a href="#l84"></a>
<span id="l85">            }</span><a href="#l85"></a>
<span id="l86">        });</span><a href="#l86"></a>
<span id="l87">    }</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">    /**</span><a href="#l89"></a>
<span id="l90">     * Checks if a certificate is a JDK trust anchor.</span><a href="#l90"></a>
<span id="l91">     *</span><a href="#l91"></a>
<span id="l92">     * @param cert the certificate to check</span><a href="#l92"></a>
<span id="l93">     * @return true if the certificate is a JDK trust anchor</span><a href="#l93"></a>
<span id="l94">     */</span><a href="#l94"></a>
<span id="l95">    public static boolean contains(X509Certificate cert) {</span><a href="#l95"></a>
<span id="l96">        String key = X509CertImpl.getFingerprint(HASH, cert);</span><a href="#l96"></a>
<span id="l97">        boolean result = certs.contains(key);</span><a href="#l97"></a>
<span id="l98">        if (result &amp;&amp; debug != null) {</span><a href="#l98"></a>
<span id="l99">            debug.println(&quot;AnchorCertificate.contains: matched &quot; +</span><a href="#l99"></a>
<span id="l100">                    cert.getSubjectDN());</span><a href="#l100"></a>
<span id="l101">        }</span><a href="#l101"></a>
<span id="l102">        return result;</span><a href="#l102"></a>
<span id="l103">    }</span><a href="#l103"></a>
<span id="l104"></span><a href="#l104"></a>
<span id="l105">    /**</span><a href="#l105"></a>
<span id="l106">     * Checks if a JDK trust anchor is the issuer of a certificate.</span><a href="#l106"></a>
<span id="l107">     *</span><a href="#l107"></a>
<span id="l108">     * @param cert the certificate to check</span><a href="#l108"></a>
<span id="l109">     * @return true if the certificate is issued by a trust anchor</span><a href="#l109"></a>
<span id="l110">     */</span><a href="#l110"></a>
<span id="l111">    public static boolean issuerOf(X509Certificate cert) {</span><a href="#l111"></a>
<span id="l112">        return certIssuers.contains(cert.getIssuerX500Principal());</span><a href="#l112"></a>
<span id="l113">    }</span><a href="#l113"></a>
<span id="l114"></span><a href="#l114"></a>
<span id="l115">    private AnchorCertificates() {}</span><a href="#l115"></a>
<span id="l116">}</span><a href="#l116"></a></pre>
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

