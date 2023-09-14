<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 412d2b1381a4 src/share/classes/java/security/cert/CertPathHelperImpl.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/412d2b1381a4/src/share/classes/java/security/cert/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/java/security/cert/CertPathHelperImpl.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/412d2b1381a4/src/share/classes/java/security/cert/CertPathHelperImpl.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/412d2b1381a4/src/share/classes/java/security/cert/CertPathHelperImpl.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/412d2b1381a4/src/share/classes/java/security/cert/CertPathHelperImpl.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/412d2b1381a4/src/share/classes/java/security/cert/CertPathHelperImpl.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/412d2b1381a4/src/share/classes/java/security/cert/CertPathHelperImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/security/cert/CertPathHelperImpl.java @ 14391:412d2b1381a4</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/00cd9dc3c2b5/src/share/classes/java/security/cert/CertPathHelperImpl.java">00cd9dc3c2b5</a> </td>
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
<span id="l26">package java.security.cert;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.util.*;</span><a href="#l28"></a>
<span id="l29"></span><a href="#l29"></a>
<span id="l30">import sun.security.provider.certpath.CertPathHelper;</span><a href="#l30"></a>
<span id="l31"></span><a href="#l31"></a>
<span id="l32">import sun.security.x509.GeneralNameInterface;</span><a href="#l32"></a>
<span id="l33"></span><a href="#l33"></a>
<span id="l34">/**</span><a href="#l34"></a>
<span id="l35"> * Helper class that allows the Sun CertPath provider to access</span><a href="#l35"></a>
<span id="l36"> * implementation dependent APIs in CertPath framework.</span><a href="#l36"></a>
<span id="l37"> *</span><a href="#l37"></a>
<span id="l38"> * @author Andreas Sterbenz</span><a href="#l38"></a>
<span id="l39"> */</span><a href="#l39"></a>
<span id="l40">class CertPathHelperImpl extends CertPathHelper {</span><a href="#l40"></a>
<span id="l41"></span><a href="#l41"></a>
<span id="l42">    private CertPathHelperImpl() {</span><a href="#l42"></a>
<span id="l43">        // empty</span><a href="#l43"></a>
<span id="l44">    }</span><a href="#l44"></a>
<span id="l45"></span><a href="#l45"></a>
<span id="l46">    /**</span><a href="#l46"></a>
<span id="l47">     * Initialize the helper framework. This method must be called from</span><a href="#l47"></a>
<span id="l48">     * the static initializer of each class that is the target of one of</span><a href="#l48"></a>
<span id="l49">     * the methods in this class. This ensures that the helper is initialized</span><a href="#l49"></a>
<span id="l50">     * prior to a tunneled call from the Sun provider.</span><a href="#l50"></a>
<span id="l51">     */</span><a href="#l51"></a>
<span id="l52">    synchronized static void initialize() {</span><a href="#l52"></a>
<span id="l53">        if (CertPathHelper.instance == null) {</span><a href="#l53"></a>
<span id="l54">            CertPathHelper.instance = new CertPathHelperImpl();</span><a href="#l54"></a>
<span id="l55">        }</span><a href="#l55"></a>
<span id="l56">    }</span><a href="#l56"></a>
<span id="l57"></span><a href="#l57"></a>
<span id="l58">    protected void implSetPathToNames(X509CertSelector sel,</span><a href="#l58"></a>
<span id="l59">            Set&lt;GeneralNameInterface&gt; names) {</span><a href="#l59"></a>
<span id="l60">        sel.setPathToNamesInternal(names);</span><a href="#l60"></a>
<span id="l61">    }</span><a href="#l61"></a>
<span id="l62"></span><a href="#l62"></a>
<span id="l63">    protected void implSetDateAndTime(X509CRLSelector sel, Date date, long skew) {</span><a href="#l63"></a>
<span id="l64">        sel.setDateAndTime(date, skew);</span><a href="#l64"></a>
<span id="l65">    }</span><a href="#l65"></a>
<span id="l66"></span><a href="#l66"></a>
<span id="l67">    protected boolean implIsJdkCA(TrustAnchor anchor) {</span><a href="#l67"></a>
<span id="l68">        return anchor.isJdkCA();</span><a href="#l68"></a>
<span id="l69">    }</span><a href="#l69"></a>
<span id="l70">}</span><a href="#l70"></a></pre>
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

