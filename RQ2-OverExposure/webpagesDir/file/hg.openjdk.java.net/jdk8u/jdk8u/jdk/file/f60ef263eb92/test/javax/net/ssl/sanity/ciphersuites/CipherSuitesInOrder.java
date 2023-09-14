<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: f60ef263eb92 test/javax/net/ssl/sanity/ciphersuites/CipherSuitesInOrder.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/f60ef263eb92">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/f60ef263eb92">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/f60ef263eb92">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/test/javax/net/ssl/sanity/ciphersuites/CipherSuitesInOrder.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CipherSuitesInOrder.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CipherSuitesInOrder.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CipherSuitesInOrder.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CipherSuitesInOrder.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/f60ef263eb92/test/javax/net/ssl/sanity/ciphersuites/CipherSuitesInOrder.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view test/javax/net/ssl/sanity/ciphersuites/CipherSuitesInOrder.java @ 14554:f60ef263eb92</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8163326: Update the default enabled cipher suites preference
Reviewed-by: avoitylov, andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#109;&#98;&#97;&#108;&#97;&#111;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 01 Jul 2021 07:04:53 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/466b007eedbb/test/javax/net/ssl/sanity/ciphersuites/CipherSuitesInOrder.java">466b007eedbb</a> </td>
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
<span id="l2"> * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.</span><a href="#l7"></a>
<span id="l8"> *</span><a href="#l8"></a>
<span id="l9"> * This code is distributed in the hope that it will be useful, but WITHOUT</span><a href="#l9"></a>
<span id="l10"> * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or</span><a href="#l10"></a>
<span id="l11"> * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License</span><a href="#l11"></a>
<span id="l12"> * version 2 for more details (a copy is included in the LICENSE file that</span><a href="#l12"></a>
<span id="l13"> * accompanied this code).</span><a href="#l13"></a>
<span id="l14"> *</span><a href="#l14"></a>
<span id="l15"> * You should have received a copy of the GNU General Public License version</span><a href="#l15"></a>
<span id="l16"> * 2 along with this work; if not, write to the Free Software Foundation,</span><a href="#l16"></a>
<span id="l17"> * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.</span><a href="#l17"></a>
<span id="l18"> *</span><a href="#l18"></a>
<span id="l19"> * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA</span><a href="#l19"></a>
<span id="l20"> * or visit www.oracle.com if you need additional information or have any</span><a href="#l20"></a>
<span id="l21"> * questions.</span><a href="#l21"></a>
<span id="l22"> */</span><a href="#l22"></a>
<span id="l23"></span><a href="#l23"></a>
<span id="l24">//</span><a href="#l24"></a>
<span id="l25">// SunJSSE does not support dynamic system properties, no way to re-use</span><a href="#l25"></a>
<span id="l26">// system properties in samevm/agentvm mode.</span><a href="#l26"></a>
<span id="l27">//</span><a href="#l27"></a>
<span id="l28"></span><a href="#l28"></a>
<span id="l29">/*</span><a href="#l29"></a>
<span id="l30"> * @test</span><a href="#l30"></a>
<span id="l31"> * @bug 7174244 8234728</span><a href="#l31"></a>
<span id="l32"> * @summary Test for ciphersuites order</span><a href="#l32"></a>
<span id="l33"> * @run main/othervm CipherSuitesInOrder</span><a href="#l33"></a>
<span id="l34"> */</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36">import java.util.*;</span><a href="#l36"></a>
<span id="l37">import javax.net.ssl.*;</span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">public class CipherSuitesInOrder {</span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">    // Supported ciphersuites</span><a href="#l41"></a>
<span id="l42">    private final static List&lt;String&gt; supportedCipherSuites</span><a href="#l42"></a>
<span id="l43">            = Arrays.&lt;String&gt;asList(</span><a href="#l43"></a>
<span id="l44">                    // TLS 1.3 cipher suites.</span><a href="#l44"></a>
<span id="l45">                    &quot;TLS_AES_256_GCM_SHA384&quot;,</span><a href="#l45"></a>
<span id="l46">                    &quot;TLS_AES_128_GCM_SHA256&quot;,</span><a href="#l46"></a>
<span id="l47">                    // Suite B compliant cipher suites, see RFC 6460.</span><a href="#l47"></a>
<span id="l48">                    &quot;TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l48"></a>
<span id="l49">                    &quot;TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l49"></a>
<span id="l50">                    //</span><a href="#l50"></a>
<span id="l51">                    // Forward secrecy cipher suites.</span><a href="#l51"></a>
<span id="l52">                    //</span><a href="#l52"></a>
<span id="l53">                    // AES_256(GCM) - ECDHE</span><a href="#l53"></a>
<span id="l54">                    &quot;TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l54"></a>
<span id="l55">                    // AES_128(GCM) - ECDHE</span><a href="#l55"></a>
<span id="l56">                    &quot;TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l56"></a>
<span id="l57">                    // AES_256(GCM) - DHE</span><a href="#l57"></a>
<span id="l58">                    &quot;TLS_DHE_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l58"></a>
<span id="l59">                    &quot;TLS_DHE_DSS_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l59"></a>
<span id="l60">                    // AES_128(GCM) - DHE</span><a href="#l60"></a>
<span id="l61">                    &quot;TLS_DHE_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l61"></a>
<span id="l62">                    &quot;TLS_DHE_DSS_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l62"></a>
<span id="l63">                    // AES_256(CBC) - ECDHE</span><a href="#l63"></a>
<span id="l64">                    &quot;TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l64"></a>
<span id="l65">                    &quot;TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l65"></a>
<span id="l66">                    // AES_128(CBC) - ECDHE</span><a href="#l66"></a>
<span id="l67">                    &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l67"></a>
<span id="l68">                    &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l68"></a>
<span id="l69">                    // AES_256(CBC) - DHE</span><a href="#l69"></a>
<span id="l70">                    &quot;TLS_DHE_RSA_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l70"></a>
<span id="l71">                    &quot;TLS_DHE_DSS_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l71"></a>
<span id="l72">                    // AES_128(CBC) - DHE</span><a href="#l72"></a>
<span id="l73">                    &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l73"></a>
<span id="l74">                    &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l74"></a>
<span id="l75">                    //</span><a href="#l75"></a>
<span id="l76">                    // Not forward secret cipher suites.</span><a href="#l76"></a>
<span id="l77">                    //</span><a href="#l77"></a>
<span id="l78">                    // AES_256(GCM)</span><a href="#l78"></a>
<span id="l79">                    &quot;TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l79"></a>
<span id="l80">                    &quot;TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l80"></a>
<span id="l81">                    // AES_128(GCM)</span><a href="#l81"></a>
<span id="l82">                    &quot;TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l82"></a>
<span id="l83">                    &quot;TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l83"></a>
<span id="l84">                    // AES_256(CBC)</span><a href="#l84"></a>
<span id="l85">                    &quot;TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l85"></a>
<span id="l86">                    &quot;TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384&quot;,</span><a href="#l86"></a>
<span id="l87">                    // AES_128(CBC)</span><a href="#l87"></a>
<span id="l88">                    &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l88"></a>
<span id="l89">                    &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l89"></a>
<span id="l90">                    //</span><a href="#l90"></a>
<span id="l91">                    // Legacy, used for compatibility</span><a href="#l91"></a>
<span id="l92">                    //</span><a href="#l92"></a>
<span id="l93">                    // AES_256(CBC) - ECDHE - Using SHA</span><a href="#l93"></a>
<span id="l94">                    &quot;TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l94"></a>
<span id="l95">                    &quot;TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l95"></a>
<span id="l96">                    // AES_128(CBC) - ECDHE - using SHA</span><a href="#l96"></a>
<span id="l97">                    &quot;TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l97"></a>
<span id="l98">                    &quot;TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l98"></a>
<span id="l99">                    // AES_256(CBC) - DHE - Using SHA</span><a href="#l99"></a>
<span id="l100">                    &quot;TLS_DHE_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l100"></a>
<span id="l101">                    &quot;TLS_DHE_DSS_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l101"></a>
<span id="l102">                    // AES_128(CBC) - DHE - using SHA</span><a href="#l102"></a>
<span id="l103">                    &quot;TLS_DHE_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l103"></a>
<span id="l104">                    &quot;TLS_DHE_DSS_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l104"></a>
<span id="l105">                    // AES_256(CBC) - using SHA, not forward secrecy</span><a href="#l105"></a>
<span id="l106">                    &quot;TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l106"></a>
<span id="l107">                    &quot;TLS_ECDH_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l107"></a>
<span id="l108">                    // AES_128(CBC) - using SHA, not forward secrecy</span><a href="#l108"></a>
<span id="l109">                    &quot;TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l109"></a>
<span id="l110">                    &quot;TLS_ECDH_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l110"></a>
<span id="l111">                    //</span><a href="#l111"></a>
<span id="l112">                    // Deprecated, used for compatibility</span><a href="#l112"></a>
<span id="l113">                    //</span><a href="#l113"></a>
<span id="l114">                    // RSA, AES_256(GCM)</span><a href="#l114"></a>
<span id="l115">                    &quot;TLS_RSA_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l115"></a>
<span id="l116">                    // RSA, AES_128(GCM)</span><a href="#l116"></a>
<span id="l117">                    &quot;TLS_RSA_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l117"></a>
<span id="l118">                    // RSA, AES_256(CBC)</span><a href="#l118"></a>
<span id="l119">                    &quot;TLS_RSA_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l119"></a>
<span id="l120">                    // RSA, AES_128(CBC)</span><a href="#l120"></a>
<span id="l121">                    &quot;TLS_RSA_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l121"></a>
<span id="l122">                    // RSA, AES_256(CBC) - using SHA, not forward secrecy</span><a href="#l122"></a>
<span id="l123">                    &quot;TLS_RSA_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l123"></a>
<span id="l124">                    // RSA, AES_128(CBC) - using SHA, not forward secrecy</span><a href="#l124"></a>
<span id="l125">                    &quot;TLS_RSA_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l125"></a>
<span id="l126">                    // 3DES_EDE, forward secrecy.</span><a href="#l126"></a>
<span id="l127">                    &quot;TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l127"></a>
<span id="l128">                    &quot;TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l128"></a>
<span id="l129">                    &quot;SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l129"></a>
<span id="l130">                    &quot;SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l130"></a>
<span id="l131">                    // 3DES_EDE, not forward secrecy.</span><a href="#l131"></a>
<span id="l132">                    &quot;TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l132"></a>
<span id="l133">                    &quot;TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l133"></a>
<span id="l134">                    &quot;SSL_RSA_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l134"></a>
<span id="l135">                    // Renegotiation protection request Signalling</span><a href="#l135"></a>
<span id="l136">                    // Cipher Suite Value (SCSV).</span><a href="#l136"></a>
<span id="l137">                    &quot;TLS_EMPTY_RENEGOTIATION_INFO_SCSV&quot;,</span><a href="#l137"></a>
<span id="l138">                    // Definition of the Cipher Suites that are supported but not</span><a href="#l138"></a>
<span id="l139">                    // enabled by default.</span><a href="#l139"></a>
<span id="l140">                    &quot;TLS_DH_anon_WITH_AES_256_GCM_SHA384&quot;,</span><a href="#l140"></a>
<span id="l141">                    &quot;TLS_DH_anon_WITH_AES_128_GCM_SHA256&quot;,</span><a href="#l141"></a>
<span id="l142">                    &quot;TLS_DH_anon_WITH_AES_256_CBC_SHA256&quot;,</span><a href="#l142"></a>
<span id="l143">                    &quot;TLS_ECDH_anon_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l143"></a>
<span id="l144">                    &quot;TLS_DH_anon_WITH_AES_256_CBC_SHA&quot;,</span><a href="#l144"></a>
<span id="l145">                    &quot;TLS_DH_anon_WITH_AES_128_CBC_SHA256&quot;,</span><a href="#l145"></a>
<span id="l146">                    &quot;TLS_ECDH_anon_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l146"></a>
<span id="l147">                    &quot;TLS_DH_anon_WITH_AES_128_CBC_SHA&quot;,</span><a href="#l147"></a>
<span id="l148">                    &quot;TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l148"></a>
<span id="l149">                    &quot;SSL_DH_anon_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l149"></a>
<span id="l150">                    // RC4</span><a href="#l150"></a>
<span id="l151">                    &quot;TLS_ECDHE_ECDSA_WITH_RC4_128_SHA&quot;,</span><a href="#l151"></a>
<span id="l152">                    &quot;TLS_ECDHE_RSA_WITH_RC4_128_SHA&quot;,</span><a href="#l152"></a>
<span id="l153">                    &quot;SSL_RSA_WITH_RC4_128_SHA&quot;,</span><a href="#l153"></a>
<span id="l154">                    &quot;TLS_ECDH_ECDSA_WITH_RC4_128_SHA&quot;,</span><a href="#l154"></a>
<span id="l155">                    &quot;TLS_ECDH_RSA_WITH_RC4_128_SHA&quot;,</span><a href="#l155"></a>
<span id="l156">                    &quot;SSL_RSA_WITH_RC4_128_MD5&quot;,</span><a href="#l156"></a>
<span id="l157">                    &quot;TLS_ECDH_anon_WITH_RC4_128_SHA&quot;,</span><a href="#l157"></a>
<span id="l158">                    &quot;SSL_DH_anon_WITH_RC4_128_MD5&quot;,</span><a href="#l158"></a>
<span id="l159">                    // Weak cipher suites obsoleted in TLS 1.2 [RFC 5246]</span><a href="#l159"></a>
<span id="l160">                    &quot;SSL_RSA_WITH_DES_CBC_SHA&quot;,</span><a href="#l160"></a>
<span id="l161">                    &quot;SSL_DHE_RSA_WITH_DES_CBC_SHA&quot;,</span><a href="#l161"></a>
<span id="l162">                    &quot;SSL_DHE_DSS_WITH_DES_CBC_SHA&quot;,</span><a href="#l162"></a>
<span id="l163">                    &quot;SSL_DH_anon_WITH_DES_CBC_SHA&quot;,</span><a href="#l163"></a>
<span id="l164">                    // Weak cipher suites obsoleted in TLS 1.1  [RFC 4346]</span><a href="#l164"></a>
<span id="l165">                    &quot;SSL_RSA_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l165"></a>
<span id="l166">                    &quot;SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l166"></a>
<span id="l167">                    &quot;SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l167"></a>
<span id="l168">                    &quot;SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA&quot;,</span><a href="#l168"></a>
<span id="l169">                    &quot;SSL_RSA_EXPORT_WITH_RC4_40_MD5&quot;,</span><a href="#l169"></a>
<span id="l170">                    &quot;SSL_DH_anon_EXPORT_WITH_RC4_40_MD5&quot;,</span><a href="#l170"></a>
<span id="l171">                    // No traffic encryption cipher suites</span><a href="#l171"></a>
<span id="l172">                    &quot;TLS_RSA_WITH_NULL_SHA256&quot;,</span><a href="#l172"></a>
<span id="l173">                    &quot;TLS_ECDHE_ECDSA_WITH_NULL_SHA&quot;,</span><a href="#l173"></a>
<span id="l174">                    &quot;TLS_ECDHE_RSA_WITH_NULL_SHA&quot;,</span><a href="#l174"></a>
<span id="l175">                    &quot;SSL_RSA_WITH_NULL_SHA&quot;,</span><a href="#l175"></a>
<span id="l176">                    &quot;TLS_ECDH_ECDSA_WITH_NULL_SHA&quot;,</span><a href="#l176"></a>
<span id="l177">                    &quot;TLS_ECDH_RSA_WITH_NULL_SHA&quot;,</span><a href="#l177"></a>
<span id="l178">                    &quot;TLS_ECDH_anon_WITH_NULL_SHA&quot;,</span><a href="#l178"></a>
<span id="l179">                    &quot;SSL_RSA_WITH_NULL_MD5&quot;,</span><a href="#l179"></a>
<span id="l180"></span><a href="#l180"></a>
<span id="l181">                    &quot;TLS_KRB5_WITH_3DES_EDE_CBC_SHA&quot;,</span><a href="#l181"></a>
<span id="l182">                    &quot;TLS_KRB5_WITH_3DES_EDE_CBC_MD5&quot;,</span><a href="#l182"></a>
<span id="l183">                    &quot;TLS_KRB5_WITH_RC4_128_SHA&quot;,</span><a href="#l183"></a>
<span id="l184">                    &quot;TLS_KRB5_WITH_RC4_128_MD5&quot;,</span><a href="#l184"></a>
<span id="l185">                    &quot;TLS_KRB5_WITH_DES_CBC_SHA&quot;,</span><a href="#l185"></a>
<span id="l186">                    &quot;TLS_KRB5_WITH_DES_CBC_MD5&quot;,</span><a href="#l186"></a>
<span id="l187">                    &quot;TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA&quot;,</span><a href="#l187"></a>
<span id="l188">                    &quot;TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5&quot;,</span><a href="#l188"></a>
<span id="l189">                    &quot;TLS_KRB5_EXPORT_WITH_RC4_40_SHA&quot;,</span><a href="#l189"></a>
<span id="l190">                    &quot;TLS_KRB5_EXPORT_WITH_RC4_40_MD5&quot;,</span><a href="#l190"></a>
<span id="l191">                    // Definition of the cipher suites that are not supported but the names</span><a href="#l191"></a>
<span id="l192">                    // are known.</span><a href="#l192"></a>
<span id="l193">                    &quot;TLS_CHACHA20_POLY1305_SHA256&quot;,</span><a href="#l193"></a>
<span id="l194">                    &quot;TLS_AES_128_CCM_SHA256&quot;,</span><a href="#l194"></a>
<span id="l195">                    &quot;TLS_AES_128_CCM_8_SHA256&quot;</span><a href="#l195"></a>
<span id="l196">            );</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">    private final static String[] protocols = {</span><a href="#l198"></a>
<span id="l199">        &quot;&quot;, &quot;SSL&quot;, &quot;TLS&quot;, &quot;SSLv3&quot;, &quot;TLSv1&quot;, &quot;TLSv1.1&quot;, &quot;TLSv1.2&quot;, &quot;TLSv1.3&quot;</span><a href="#l199"></a>
<span id="l200">    };</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202"></span><a href="#l202"></a>
<span id="l203">    public static void main(String[] args) throws Exception {</span><a href="#l203"></a>
<span id="l204">        // show all of the supported cipher suites</span><a href="#l204"></a>
<span id="l205">        showSuites(supportedCipherSuites.toArray(new String[0]),</span><a href="#l205"></a>
<span id="l206">                &quot;All supported cipher suites&quot;);</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">        for (String protocol : protocols) {</span><a href="#l208"></a>
<span id="l209">            System.out.println(&quot;//&quot;);</span><a href="#l209"></a>
<span id="l210">            System.out.println(&quot;// &quot;</span><a href="#l210"></a>
<span id="l211">                    + &quot;Testing for SSLContext of &quot; + protocol);</span><a href="#l211"></a>
<span id="l212">            System.out.println(&quot;//&quot;);</span><a href="#l212"></a>
<span id="l213">            checkForProtocols(protocol);</span><a href="#l213"></a>
<span id="l214">        }</span><a href="#l214"></a>
<span id="l215">    }</span><a href="#l215"></a>
<span id="l216"></span><a href="#l216"></a>
<span id="l217">    public static void checkForProtocols(String protocol) throws Exception {</span><a href="#l217"></a>
<span id="l218">        SSLContext context;</span><a href="#l218"></a>
<span id="l219">        if (protocol.isEmpty()) {</span><a href="#l219"></a>
<span id="l220">            context = SSLContext.getDefault();</span><a href="#l220"></a>
<span id="l221">        } else {</span><a href="#l221"></a>
<span id="l222">            context = SSLContext.getInstance(protocol);</span><a href="#l222"></a>
<span id="l223">            context.init(null, null, null);</span><a href="#l223"></a>
<span id="l224">        }</span><a href="#l224"></a>
<span id="l225"></span><a href="#l225"></a>
<span id="l226">        // check the order of default cipher suites of SSLContext</span><a href="#l226"></a>
<span id="l227">        SSLParameters parameters = context.getDefaultSSLParameters();</span><a href="#l227"></a>
<span id="l228">        checkSuites(parameters.getCipherSuites(),</span><a href="#l228"></a>
<span id="l229">                &quot;Default cipher suites in SSLContext&quot;);</span><a href="#l229"></a>
<span id="l230"></span><a href="#l230"></a>
<span id="l231">        // check the order of supported cipher suites of SSLContext</span><a href="#l231"></a>
<span id="l232">        parameters = context.getSupportedSSLParameters();</span><a href="#l232"></a>
<span id="l233">        checkSuites(parameters.getCipherSuites(),</span><a href="#l233"></a>
<span id="l234">                &quot;Supported cipher suites in SSLContext&quot;);</span><a href="#l234"></a>
<span id="l235"></span><a href="#l235"></a>
<span id="l236">        //</span><a href="#l236"></a>
<span id="l237">        // Check the cipher suites order of SSLEngine</span><a href="#l237"></a>
<span id="l238">        //</span><a href="#l238"></a>
<span id="l239">        SSLEngine engine = context.createSSLEngine();</span><a href="#l239"></a>
<span id="l240"></span><a href="#l240"></a>
<span id="l241">        // check the order of endabled cipher suites</span><a href="#l241"></a>
<span id="l242">        String[] ciphers = engine.getEnabledCipherSuites();</span><a href="#l242"></a>
<span id="l243">        checkSuites(ciphers,</span><a href="#l243"></a>
<span id="l244">                &quot;Enabled cipher suites in SSLEngine&quot;);</span><a href="#l244"></a>
<span id="l245"></span><a href="#l245"></a>
<span id="l246">        // check the order of supported cipher suites</span><a href="#l246"></a>
<span id="l247">        ciphers = engine.getSupportedCipherSuites();</span><a href="#l247"></a>
<span id="l248">        checkSuites(ciphers,</span><a href="#l248"></a>
<span id="l249">                &quot;Supported cipher suites in SSLEngine&quot;);</span><a href="#l249"></a>
<span id="l250"></span><a href="#l250"></a>
<span id="l251">        //</span><a href="#l251"></a>
<span id="l252">        // Check the cipher suites order of SSLSocket</span><a href="#l252"></a>
<span id="l253">        //</span><a href="#l253"></a>
<span id="l254">        SSLSocketFactory factory = context.getSocketFactory();</span><a href="#l254"></a>
<span id="l255">        try (SSLSocket socket = (SSLSocket) factory.createSocket()) {</span><a href="#l255"></a>
<span id="l256"></span><a href="#l256"></a>
<span id="l257">            // check the order of endabled cipher suites</span><a href="#l257"></a>
<span id="l258">            ciphers = socket.getEnabledCipherSuites();</span><a href="#l258"></a>
<span id="l259">            checkSuites(ciphers,</span><a href="#l259"></a>
<span id="l260">                    &quot;Enabled cipher suites in SSLSocket&quot;);</span><a href="#l260"></a>
<span id="l261"></span><a href="#l261"></a>
<span id="l262">            // check the order of supported cipher suites</span><a href="#l262"></a>
<span id="l263">            ciphers = socket.getSupportedCipherSuites();</span><a href="#l263"></a>
<span id="l264">            checkSuites(ciphers,</span><a href="#l264"></a>
<span id="l265">                    &quot;Supported cipher suites in SSLSocket&quot;);</span><a href="#l265"></a>
<span id="l266">        }</span><a href="#l266"></a>
<span id="l267"></span><a href="#l267"></a>
<span id="l268">        //</span><a href="#l268"></a>
<span id="l269">        // Check the cipher suites order of SSLServerSocket</span><a href="#l269"></a>
<span id="l270">        //</span><a href="#l270"></a>
<span id="l271">        SSLServerSocketFactory serverFactory = context.getServerSocketFactory();</span><a href="#l271"></a>
<span id="l272">        try (SSLServerSocket serverSocket</span><a href="#l272"></a>
<span id="l273">                = (SSLServerSocket) serverFactory.createServerSocket()) {</span><a href="#l273"></a>
<span id="l274">            // check the order of endabled cipher suites</span><a href="#l274"></a>
<span id="l275">            ciphers = serverSocket.getEnabledCipherSuites();</span><a href="#l275"></a>
<span id="l276">            checkSuites(ciphers,</span><a href="#l276"></a>
<span id="l277">                    &quot;Enabled cipher suites in SSLServerSocket&quot;);</span><a href="#l277"></a>
<span id="l278"></span><a href="#l278"></a>
<span id="l279">            // check the order of supported cipher suites</span><a href="#l279"></a>
<span id="l280">            ciphers = serverSocket.getSupportedCipherSuites();</span><a href="#l280"></a>
<span id="l281">            checkSuites(ciphers,</span><a href="#l281"></a>
<span id="l282">                    &quot;Supported cipher suites in SSLServerSocket&quot;);</span><a href="#l282"></a>
<span id="l283">        }</span><a href="#l283"></a>
<span id="l284">    }</span><a href="#l284"></a>
<span id="l285"></span><a href="#l285"></a>
<span id="l286">    private static void checkSuites(String[] suites, String title) {</span><a href="#l286"></a>
<span id="l287">        showSuites(suites, title);</span><a href="#l287"></a>
<span id="l288"></span><a href="#l288"></a>
<span id="l289">        int loc = -1;</span><a href="#l289"></a>
<span id="l290">        int index = 0;</span><a href="#l290"></a>
<span id="l291">        for (String suite : suites) {</span><a href="#l291"></a>
<span id="l292">            index = supportedCipherSuites.indexOf(suite);</span><a href="#l292"></a>
<span id="l293">            if (index &lt;= loc) {</span><a href="#l293"></a>
<span id="l294">                throw new RuntimeException(suite + &quot; is not in order&quot;);</span><a href="#l294"></a>
<span id="l295">            }</span><a href="#l295"></a>
<span id="l296">            loc = index;</span><a href="#l296"></a>
<span id="l297">        }</span><a href="#l297"></a>
<span id="l298">    }</span><a href="#l298"></a>
<span id="l299"></span><a href="#l299"></a>
<span id="l300">    private static void showSuites(String[] suites, String title) {</span><a href="#l300"></a>
<span id="l301">        System.out.println(title + &quot;[&quot; + suites.length + &quot;]:&quot;);</span><a href="#l301"></a>
<span id="l302">        for (String suite : suites) {</span><a href="#l302"></a>
<span id="l303">            System.out.println(&quot;  &quot; + suite);</span><a href="#l303"></a>
<span id="l304">        }</span><a href="#l304"></a>
<span id="l305">    }</span><a href="#l305"></a>
<span id="l306">}</span><a href="#l306"></a></pre>
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

